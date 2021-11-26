package com.company;

public class Kmp {
    public static void KMP(String text, String pattern) {
        if (pattern == null || pattern.length() == 0) {
            System.out.println("Wrong input");
        }
        if (text == null || pattern.length() > text.length()) {
            System.out.println("Wrong input");
        }

        int[] lsp = computeLspTable(pattern);

        int i = 0;
        int j = 0;
        while (i < text.length()) {
            if (pattern.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            }
            if (j == pattern.length()) {
                System.out.println("Found pattern at index: " + (i - j));
                j = lsp[j - 1];
            }
            if (i < text.length() && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0)
                    j = lsp[j - 1];
                else
                    i++;
            }
        }
    }

    public static int[] computeLspTable(String pattern) {
        int[] lsp = new int[pattern.length()];
        lsp[0] = 0;
        for (int i = 1; i < pattern.length(); i++) {
            int j = lsp[i - 1];
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j))
                j = lsp[j - 1];
            if (pattern.charAt(i) == pattern.charAt(j))
                j++;
            lsp[i] = j;
        }
        return lsp;
    }

    public static void main(String[] args) {
        String text = "ABCABAABCABAC";
        String pattern = "CAB";

        KMP(text, pattern);
    }
}
