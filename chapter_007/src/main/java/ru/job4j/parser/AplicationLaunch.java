package ru.job4j.parser;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import ru.job4j.parser.sql.DataSource;
import ru.job4j.parser.sql.VacancySQL;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import static org.quartz.TriggerBuilder.newTrigger;

public class AplicationLaunch implements Job {

    public String getCron() {
        Properties properties = new Properties();
        try (InputStream in = Vacancy.class.getClassLoader().getResourceAsStream("vacancy.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("cron.time");
    }

    public void quartz() {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDetail job = JobBuilder.newJob(AplicationLaunch.class).build();
            Trigger trigger = newTrigger().withIdentity("trigereveryday")
                    .startNow().withSchedule(CronScheduleBuilder.cronSchedule(getCron())).build();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
    public void mainLogic(String url) {
        try (VacancySQL vacancySQL = new VacancySQL(new DataSource().getConnection())) {
            Parser parser = new Parser(vacancySQL);
            parser.parsePage(url);
            List<Vacancy> list = parser.getVacancies();
            vacancySQL.add(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        this.mainLogic("https://www.sql.ru/forum/job-offers/");
    }
    public static void main(String...args) {
        AplicationLaunch aplicationLaunch = new AplicationLaunch();
        aplicationLaunch.quartz();
    }
}
