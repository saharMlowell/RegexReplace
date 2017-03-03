package com.sony.regex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by slowell on 1/10/17.
 */
public class ContentProcess {

    /**
     * Replace matching regEx in all contents of the files with the given replacement string
     *
     * @param contents
     * @param regexPattern
     * @param replacement
     * @return List of replaced file content
     */
    List<String> replaceContentsWithReplacement(List<String> contents, String regexPattern, String replacement) {
        Pattern checkRegex = Pattern.compile(regexPattern);
        List<String> replacedContent = contents.stream().map(content -> {
            Matcher regexMatcher = checkRegex.matcher(content);
            return regexMatcher.replaceAll(replacement);
        }).collect(Collectors.toList());
        System.out.println("Processed " + contents.size() + "files. Replaced to "+ replacement);
        return replacedContent;
    }

    /**
     * Find each regularExpression pattern in all contents
     *
     * @param contents
     * @param regexPattern
     * @return List of all given pattern in all contents
     */
    List<String> findRegexPatternInContents(List<String> contents, String regexPattern) {
        Pattern checkRegex = Pattern.compile(regexPattern);
        List<String> allMatches = new ArrayList<String>();
        contents.stream().forEach(content -> {
            Matcher regexMatcher = checkRegex.matcher(content);
            while (regexMatcher.find()) {
                if (regexMatcher.group().length() != 0) {
                    allMatches.add(regexMatcher.group());
                }
            }
        });
        return allMatches;

    }

    /**
     * Write to Console total of number of each reg Ex group
     *
     * @param allRegex
     */
    void report(List<String> allRegex) {
        HashMap<String, Integer> regexGroupMapCount = new HashMap<String, Integer>();
        for (String singleRegex : allRegex) {
            if (regexGroupMapCount.containsKey(singleRegex)) {
                Integer count = regexGroupMapCount.get(singleRegex);
                regexGroupMapCount.put(singleRegex, ++count);

            } else {
                regexGroupMapCount.put(singleRegex, 1);
            }
        }
        System.out.println(regexGroupMapCount);

    }


}
