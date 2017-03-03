# RegexTextReplacementInFiles 
performing string replacement for each of the qualified files under a starting directory. Display a summary report of the strings and occurrences count that were replaced before the program terminates. Save the processed output to files in the same directories of the source files with new file names by appending the original names with ".processed".

# parameter
 The program takes 3 required parameters and one optional parameter:  
 * Starting directory/file 
 *  String pattern to be replaced 
 *  String to be replaced to 
 *  File naming pattern - UNIX wild-card filename syntax, 
 
# Sample Input / Output 
java RegexTextReplacementInFiles sample-dir '(ab+c+)' REPLACEMENT '*.txt' <br />
processed 37 files. Replaced to "REPLACEMENT": <br />
abbc: 23 occurrences <br />
abbbc: 16 occurrences <br />
abc: 3 occurrences <br />
... <br />
$ ls sample_dir <br />
sample1.doc <br />
sample1.txt <br />
sample1.txt.processed <br />
sample2.doc <br />
sample2.txt <br />
sample2.txt.processed <br />
... <br />
