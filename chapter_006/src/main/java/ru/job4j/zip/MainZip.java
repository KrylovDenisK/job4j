package ru.job4j.zip;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MainZip {
    private Queue<File> data = new LinkedList<>();

    public List<File> seekBy(String root, String ext) {
        List<File> result = new ArrayList<>();
        File files = new File(root);
        addFolderContents(files);
        while (!data.isEmpty()) {
            File file = data.poll();
            if (file.isDirectory()) {
                addFolderContents(file);
            } else {
                if (!file.getName().endsWith(ext.substring(1))) {
                    result.add(file);
                }
            }
        }
        return result;
    }

    private void addFolderContents(File folder) {
        File[] listChild = folder.listFiles();
        for (File child : listChild) {
            data.offer(child);
        }
    }
    public void pack(List<File> source, File target, String directory) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : source) {
                ZipEntry entry = new ZipEntry(file.getAbsolutePath().substring(directory.length()));
                zip.putNextEntry(entry);
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file.getPath()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
       Arqs arqs = new Arqs(args);
       MainZip zip = new MainZip();
       zip.pack(zip.seekBy(arqs.directory(), arqs.exclude()), new File(System.getProperty("java.io.tmpdir") + File.separator + arqs.output()), arqs.directory());
    }
}