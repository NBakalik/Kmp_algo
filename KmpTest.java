package com.company;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class KmpTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setStream() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void restoreInitialStream() {
        System.setOut(originalOut);
    }

    @Test
    public void shouldPrintIndexes() {
        String text = "abbacdfabbab";
        String pattern = "abba";
        Kmp.KMP(text, pattern);
        String expectedOutput = "Found pattern at index: 0" + System.lineSeparator() +
                "Found pattern at index: 7" + System.lineSeparator();
        Assert.assertEquals(expectedOutput, out.toString());
    }
}
