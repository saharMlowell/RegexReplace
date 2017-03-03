# RegexTextReplacementInFiles 
performing string replacement for each of the qualified files under a starting directory. Display a summary report of the strings and occurrences count that were replaced before the program terminates. Save the processed output to files in the same directories of the source files with new file names by appending the original names with ".processed".

# parameter
 The program takes 3 required parameters and one optional parameter:  
 * Starting directory/file 
 *  String pattern to be replaced 
 *  String to be replaced to 
 *  File naming pattern - UNIX wild-card filename syntax, 
 
# Sample Input / Output 
java RegexTextReplacementInFiles sample-dir '(ab+c+)' REPLACEMENT '*.txt'
processed 37 files. Replaced to "REPLACEMENT":
abbc: 23 occurrences
abbbc: 16 occurrences
abc: 3 occurrences
...
$ ls sample_dir
sample1.doc
sample1.txt
sample1.txt.processed
sample2.doc
sample2.txt
sample2.txt.processed
...
