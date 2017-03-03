package com.sony.regex;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * Created by slowell on 1/10/17.
 */
public class FileProcess {


    /**
     * Filter FileNames in given directory and sub directory
     * In case of Empty fileAcceptPattern, It will return all files in given directory
     *
     * @param fileNames
     * @param directoryPath
     * @param fileAcceptPattern
     * @return List of filtered files
     */
    List<Path> filterFileName(List<Path> fileNames, Path directoryPath, String fileAcceptPattern) throws IOException {
        FileSystem fileSystem = FileSystems.getDefault();
        PathMatcher pathMatcher = fileSystem.getPathMatcher("glob:" + fileAcceptPattern);
        if (!(directoryPath.toFile().isDirectory())) {
            if (fileAcceptPattern == null || fileAcceptPattern.isEmpty() || pathMatcher.matches(directoryPath.getFileName())) {
                fileNames.add(directoryPath);
            }
        } else {
            DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath);
            for (Path path : stream) {
                if (path.toFile().isDirectory()) {
                    filterFileName(fileNames, path, fileAcceptPattern);
                } else {
                    if (fileAcceptPattern == null || fileAcceptPattern.isEmpty() || pathMatcher.matches(path.getFileName())) {
                        fileNames.add(path);
                    }
                }
            }

        }
        return fileNames;
    }


    /**
     * Read Content of all Files
     *
     * @param filesPath given files paths
     * @return
     */
    List<String> readFiles(List<Path> filesPath) {
        List<String> contents = new ArrayList<String>();
        filesPath.forEach(filePath -> {
            try {
                contents.add(new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return contents;
    }


    /**
     * Write All files with replaced contents
     *
     * @param replacedContents
     * @param filesPath
     * @throws IOException
     */
    void writeToFiles(List<String> replacedContents, List<Path> filesPath) throws IOException {
        int counter = 0;
        Iterator<Path> pathIterator = filesPath.iterator();
        while (pathIterator.hasNext()) {
            Path outPath = Paths.get(pathIterator.next().toString() + ".processed");
            String replacedContent = replacedContents.get(counter++);
            Files.write(outPath, replacedContent.getBytes(StandardCharsets.UTF_8));

        }
    }


}
