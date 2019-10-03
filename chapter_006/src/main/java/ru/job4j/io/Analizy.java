package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;


public class Analizy {
    private Map<String, String> values = new TreeMap<>();
    private StringJoiner outJoiner = new StringJoiner(System.lineSeparator());

    public void unavailable(String source, String target) {
        convertDataFileToMap(source);
        searchErrorsLogFile();
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            out.println(outJoiner.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(String time, String error) {
        values.put(time, error);
    }

    public StringJoiner getOutJoiner() {
        return outJoiner;
    }

    /**
     * Write data from file to card
     */
    public void convertDataFileToMap(String source) {
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            read.lines().map(String::trim).filter(x -> !x.isEmpty()).map(x -> x.split(" ")).forEach(x -> values.put(x[1], x[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *To find in logs intervals with errors
     */
    public void searchErrorsLogFile() {
        String timeStart = "", timeEnd;
        boolean interval = false;
        for (Map.Entry<String, String> entry : values.entrySet()) {
            String error = entry.getValue();
            if (!interval && error.equals("400") || !interval && error.equals("500")) {
                timeStart = entry.getKey();
                interval = true;
            }
            if (interval && error.equals("200")) {
                timeEnd = entry.getKey();
                interval = false;
                outJoiner.add(timeStart + ";" + timeEnd);
            }
        }
    }
}
