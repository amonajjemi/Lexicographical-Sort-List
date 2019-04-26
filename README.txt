Aaron Monajjemi
amonajje
Programming Assignment 1

Files submitted: List.java, ListClient.java, Lex.java, Makefile, README

The List.java file contains the List data structure and is used to sort the elements of an array
in lexicographical order using the array indices. Thus making the List object itself sorted but
the array left as is.

The ListClient.java file is the test file given as is to do some rudimentary tests of the List class.
It tests some, but not all, of the functions in List.java.

The Lex.java file is the file that is actually executed and that sorts the indices of an array into
a List in lexicographical order. It also reads in a text file that fills the array with strings, and
then prints out to a text file all the same strings but in lexicographical order.
(NOTE: When testing Lex on the 105 computer lab desktops it printed correctly, however upon testing it
at home on my own computer it does not print out to the file correctly. I have changed nothing between
the two instances and do not know why this is happening. Hopefully I will figure it out before the deadline
in which case this note may be ignored, otherwise it is just to inform you of this issue.)

The Makefile is the given Makefile with only two additional lines used for executing Lex for testing
purposes. It compiles all the files in the directory and links things appropriately.

README is this file and is used to list the files that I am submitting with brief descriptions.