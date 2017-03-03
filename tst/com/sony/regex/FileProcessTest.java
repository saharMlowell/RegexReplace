package com.sony.regex;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by slowell on 1/10/17.
 */
public class FileProcessTest {
    FileProcess process;
    String regexPattern;
    @Before
    public void setUp() throws Exception {
        process = new FileProcess();
        regexPattern = "(ab+)";
    }
    @Test
    public void filterFileName() throws Exception {
        List<Path> fileNames = new ArrayList<>();
        Path directory = Paths.get("testFiles");
        List<Path> expectedPath = Arrays.asList(Paths.get("testFiles/sample1.txt"), Paths.get("testFiles/test/sample2.txt"));
        Assert.assertEquals(expectedPath, process.filterFileName(fileNames, directory,"*.txt"));


    }

    @Test
    public void filterFileNameWithFileAsRoot() throws Exception {
        List<Path> fileNames = new ArrayList<>();
        Path filePath = Paths.get("testFiles/sample1.txt");
        List<Path> expectedPath = Arrays.asList(Paths.get("testFiles/sample1.txt"));
        Assert.assertEquals(expectedPath, process.filterFileName(fileNames, filePath,"*.txt"));


    }

    @Test
    public void filterFileNameWithNullWildCard() throws Exception {
        List<Path> fileNames = new ArrayList<>();
        Path filePath = Paths.get("testFiles");
        List<Path> expectedPath = Arrays.asList(Paths.get("testFiles/.DS_Store"), Paths.get("testFiles/sample1.doc"), Paths.get("testFiles/sample1.txt"),Paths.get("testFiles/test/.DS_Store"),Paths.get("testFiles/test/sample2.txt"));
        Assert.assertEquals(expectedPath, process.filterFileName(fileNames, filePath, null));


    }

    @Test
    public void filterFileNameWithEmptyWildCard() throws Exception {
        List<Path> fileNames = new ArrayList<>();
        Path filePath = Paths.get("testFiles");
        List<Path> expectedPath = Arrays.asList(Paths.get("testFiles/.DS_Store"), Paths.get("testFiles/sample1.doc"), Paths.get("testFiles/sample1.txt"),Paths.get("testFiles/test/.DS_Store"),Paths.get("testFiles/test/sample2.txt"));
        Assert.assertEquals(expectedPath, process.filterFileName(fileNames, filePath, ""));


    }


    @Test
    public void readFiles() throws Exception {
        List<String> contents = Arrays.asList("Sahar aabbbbbcccc jaan!!!");
        List<Path> filePath = Arrays.asList(Paths.get("testFiles/test/sample2.txt"));
        Assert.assertEquals(contents, process.readFiles(filePath));

    }


}