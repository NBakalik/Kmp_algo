package com.company;

public class Kmp {
    public static void KMP(String text, String pattern) {
        if (pattern == null || pattern.length() == 0) {
            System.out.println("Wrong input");
        }
        if (text == null || pattern.length() > text.length()) {
            System.out.println("Wrong input");
        }

        int[] longestSuffixPrefix = computeLspTable(pattern);

        int textPos = 0;
        int patternPos = 0;
        while (textPos < text.length()) {
            if (pattern.charAt(patternPos) == text.charAt(textPos)) {
                patternPos++;
                textPos++;
            }
            if (patternPos == pattern.length()) {
                System.out.println("Found pattern at index: " + (textPos - patternPos));
                patternPos = longestSuffixPrefix[patternPos - 1];
            }
            if (textPos < text.length() && pattern.charAt(patternPos) != text.charAt(textPos)) {
                if (patternPos != 0)
                    patternPos = longestSuffixPrefix[patternPos - 1];
                else
                    textPos++;
            }
        }
    }

    public static int[] computeLspTable(String pattern) {
        int[] longestSuffixPrefix = new int[pattern.length()];
        longestSuffixPrefix[0] = 0;
        for (int suffixPos = 1; suffixPos < pattern.length(); suffixPos++) {
            int suffixLen = longestSuffixPrefix[suffixPos - 1];
            while (suffixLen > 0 && pattern.charAt(suffixPos) != pattern.charAt(suffixLen))
                suffixLen = longestSuffixPrefix[suffixLen - 1];
            if (pattern.charAt(suffixPos) == pattern.charAt(suffixLen))
                suffixLen++;
            longestSuffixPrefix[suffixPos] = suffixLen;
        }
        return longestSuffixPrefix;
    }

    public static void main(String[] args) {
        String text = "ABCABAABCABAC";
        String pattern = "CAB";

        KMP(text, pattern);
    }
}
