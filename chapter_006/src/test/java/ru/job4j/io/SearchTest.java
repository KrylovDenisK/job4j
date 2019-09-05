package ru.job4j.io;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SearchTest {
    private Search search;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Before
    public void createStructure() throws IOException {
        File folderRoot = tempFolder.newFolder("root");
        File txt1 = tempFolder.newFile("1.txt");
        File txt2 = tempFolder.newFile("2.txt");
        File txt3 = tempFolder.newFile("3.txt");
        File txt4 = tempFolder.newFile(File.separator + folderRoot.getName() + File.separator + "4.txt");
        File txt5 = tempFolder.newFile(File.separator + folderRoot.getName() + File.separator + "5.txt");
        search = new Search();
    }

    @Test
    public void whenFileSearchByGivenFormatsThenResultTrue() {
        List<File> listFiles = search.files(tempFolder.getRoot().getPath(), List.of(".txt"));
        List<String> expected = List.of("1.txt", "2.txt", "3.txt", "4.txt", "5.txt");
        List<String> result = listFiles.stream().map(File::getName).collect(Collectors.toList());
        assertThat(result, is(expected));
    }
}