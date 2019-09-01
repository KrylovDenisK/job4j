package ru.job4j.io;

import java.io.File;
import java.util.*;

public class Search {
    private Queue<File> data = new LinkedList<>();

    /**
     * File search by given formats
     * @param parent path to the root search folder
     * @param exts list formats
     * @return list of found files
     */
    public List<File> files(String parent, List<String> exts) {
        List<File> filesResult = new ArrayList<>();
        File files = new File(parent);
        addFolderContents(files);
        while (!data.isEmpty()) {
            File file = data.poll();
            if (file.isDirectory()) {
                addFolderContents(file);
            } else {
                if (exts.stream().anyMatch(x -> file.getName().endsWith(x))) {
                    filesResult.add(file);
                }
            }
        }
        return filesResult;
    }

    /**
     * add the contents of the folder to the queue
     * @param folder
     */
    private void addFolderContents(File folder) {
        File[] listChild = folder.listFiles();
        for (File child : listChild) {
            data.offer(child);
        }
    }
}
