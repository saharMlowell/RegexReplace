package com.sony.regex;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by slowell on 1/10/17.
 */
public class ContentProcessTest {
    ContentProcess content;
    List<String> contents;
    String regexPattern;

    @Before
    public void setUp() throws Exception {
        content = new ContentProcess();
        contents = Arrays.asList("past abcsdfabcasd past abbbcsdf adabbbc", "few years abbbc", "b");
        regexPattern = "(ab+)";
    }

    @Test
    public void replaceContentsWithReplacement() throws Exception {
        String replacement = "Sahar";;
        List<String> expectedOut = Arrays.asList("past SaharcsdfSaharcasd past Saharcsdf adSaharc", "few years Saharc", "b");
        Assert.assertEquals(expectedOut ,content.replaceContentsWithReplacement(contents, regexPattern, replacement));
    }

    @Test
    public void findRegexPatternInContents() throws Exception {
        List<String> expectedResult = Arrays.asList("ab", "ab", "abbb", "abbb", "abbb");
        Assert.assertEquals(expectedResult, content.findRegexPatternInContents(contents, regexPattern));


    }


}