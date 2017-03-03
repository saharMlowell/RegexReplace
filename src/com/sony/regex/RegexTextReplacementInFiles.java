package com.sony.regex;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RegexTextReplacementInFiles {
    static FileProcess fileProcess = new FileProcess();
    static ContentProcess contentProcess = new ContentProcess();

    public static void process(String startingDir, String regexPattern, String replacement, String fileAcceptPattern) throws IOException {
        List<Path> fileNames = new ArrayList<>();
        List<Path> matchedFilePaths = fileProcess.filterFileName(fileNames, Paths.get(startingDir), fileAcceptPattern);
        List<String> fileContents = fileProcess.readFiles(matchedFilePaths);
        contentProcess.report(contentProcess.findRegexPatternInContents(fileContents, regexPattern));
        List<String> replacedContent = contentProcess.replaceContentsWithReplacement(fileContents, regexPattern, replacement);
        fileProcess.writeToFiles(replacedContent, matchedFilePaths);


    }

    public static void main(String[] args) throws IOException {
        String startingDir = null;
        String regexPattern = null;
        String replacement = null;
        String fileAcceptPattern = null;

        if(args.length >= 3){
            startingDir  = args[0];
            regexPattern = args[1];
            replacement  = args[2];
        }
        if(args.length >= 4){
            fileAcceptPattern = args[3];
        }
        if(startingDir != null) {
            process(startingDir, regexPattern, replacement, fileAcceptPattern);
        }
        else {
            System.out.println("Expected at least 3 parameters but got " + args.length);
        }
    }
}
