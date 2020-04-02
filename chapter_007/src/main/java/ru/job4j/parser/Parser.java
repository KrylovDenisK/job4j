package ru.job4j.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.parser.sql.VacancySQL;
import ru.job4j.parser.validate.ValidateDate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class Parser {
    private List<Vacancy> vacancies = new ArrayList<>();
    private VacancySQL vacancySQL;
    private ValidateDate validateDate = new ValidateDate();

    public Parser(VacancySQL vacancySQL) {
        this.vacancySQL = vacancySQL;
    }

    public void parsePage(String url) throws IOException {
        int pageNumber = 1;
        LocalDateTime dateForParse = searchDateForParse();
        LocalDateTime dateTimeVac = validateDate.getCurrentDateTime();
        while (dateForParse.isBefore(dateTimeVac)) {
            Document document = Jsoup.connect(url + pageNumber)
                    .referrer("http://www.google.com").get();
            Elements table = document.getElementsByClass("forumTable");
            Elements rowTable = table.select("tr");
            for (Element row : rowTable) {
                Element nameRow = row.select(".postslisttopic").select("a").first();
                Element timeRow = row.select("td.altCol").last();
                if (nameRow != null) {
                    String urlRow = nameRow.attr("href");
                    String name = nameRow.text();
                    String time = timeRow.text();
                    dateTimeVac = validateDate.transformDate(time);
                    if (checkName(name) && validateDate.checkDate(time, dateForParse)) {
                        Document doc = Jsoup.connect(urlRow)
                                .referrer("http://www.google.com").get();
                        String description = doc.select(".msgTable").first()
                                .select("tr").select("td.msgBody").last().text();
                        vacancies.add(new Vacancy(name, description, urlRow, dateTimeVac));
                    }
                }
            }
            pageNumber++;
        }

    }


    public LocalDateTime searchDateForParse() {
        LocalDateTime local = vacancySQL.searchLastDateOnDB();
        return local != null ? local : LocalDateTime.of(validateDate.getCurrentDateTime().getYear(), 1, 1, 0, 0);

    }

    private boolean checkName(String name) {
        String lowName = name.toLowerCase();
        return lowName.contains("java") && !lowName.contains("script");
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }
}
