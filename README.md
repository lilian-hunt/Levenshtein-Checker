# Levenshtein-Checker
The program takes two filenames from the command line, the first is a list of valid words separated by a newline character, the second is text file which needs spell checking.

The program then replaces each word in the second file with the closest match in the first file and print out the content, preserving case and punctuation.

The word is either UPPERCASE, lowercase or Capital Case

The metric for distance will be the Levenshtein distance

If two words have the same distance, the one that appears earliest in the file is used
