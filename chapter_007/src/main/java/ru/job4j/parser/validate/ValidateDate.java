package ru.job4j.parser.validate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.StringJoiner;

public class ValidateDate {
    public LocalDateTime getCurrentDateTime() {
        return currentDateTime;
    }

    private LocalDateTime currentDateTime = LocalDateTime.now().withSecond(0).withNano(0);
    private HashMap<String, String> mapMonth = new HashMap<>();

    public ValidateDate() {
        initCalendar();
    }

    public LocalDateTime transformDate(String date) {
        LocalDateTime result;
        String[] strDateTime = date.split(",");
        String[] strDate = strDateTime[0].split(" ");
        String vacTime = strDateTime[1];
        if (strDate.length > 2) {
            StringJoiner vacDate = new StringJoiner("-")
                    .add(strDate[0].length() == 1 ? "0" + strDate[0] : strDate[0])
                    .add(mapMonth.get(strDate[1]))
                    .add("20" + strDate[2]);
            String vacDateTime = vacDate + vacTime;
            result = formatDate(vacDateTime);
        } else {
            String[] vacTimeDate = vacTime.split(":");
            int hour = Integer.parseInt(vacTimeDate[0].trim());
            int minute = Integer.parseInt(vacTimeDate[1]);
            result = currentDateTime.withHour(hour).withMinute(minute);
            if (strDate[0].equals("вчера")) {
                result = result.minusDays(1);
            }
        }
        return result;
    }

    public LocalDateTime formatDate(String vacDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(vacDateTime, dateTimeFormatter);
    }

    public boolean checkDate(String date, LocalDateTime dateForParse) {
        LocalDateTime dateTime = transformDate(date);
        return dateForParse.isBefore(dateTime);
    }

    private void initCalendar() {
        mapMonth.put("янв", "01");
        mapMonth.put("фев", "02");
        mapMonth.put("мар", "03");
        mapMonth.put("апр", "04");
        mapMonth.put("май", "05");
        mapMonth.put("июн", "06");
        mapMonth.put("июл", "07");
        mapMonth.put("авг", "08");
        mapMonth.put("сен", "09");
        mapMonth.put("окт", "10");
        mapMonth.put("ноя", "11");
        mapMonth.put("дек", "12");
    }
}
