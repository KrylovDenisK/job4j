package ru.job4j.zip;

import org.apache.commons.cli.*;

import java.util.HashMap;
import java.util.Map;


public class Arqs {

    private Map<String, String> argsMap = new HashMap<>();

    public Arqs(String[] args) throws Exception {
        splitArgs(args);
    }

    public String directory() {
        return argsMap.get("d");
    }

    public String exclude() {
        return argsMap.get("e");
    }

    public String output() {
        return argsMap.get("o");
    }

    private void splitArgs(String[] args) throws Exception {
        Options options = new Options();
        Option optionD = new Option("d", "directory", true, "Directory");
        options.addOption(optionD);
        Option optionE = new Option("e", "exclude", true, "Exclude files");
        options.addOption(optionE);
        Option optionO = new Option("o", "output", true, "Output");
        options.addOption(optionO);
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine commandLine = commandLineParser.parse(options, args);
        String[] arguments;
        if (commandLine.hasOption("d")) {
            arguments = commandLine.getOptionValues("d");
            argsMap.put("d", arguments[0]);
        }
        if (commandLine.hasOption("e")) {
            arguments = commandLine.getOptionValues("e");
            argsMap.put("e", arguments[0]);
        }
        if (commandLine.hasOption("o")) {
            arguments = commandLine.getOptionValues("o");
            argsMap.put("o", arguments[0]);
        }
    }
}
