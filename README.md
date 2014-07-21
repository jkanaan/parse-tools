parse-tools
===========

Java tools for parsing and sorting data from files.

Parser.java:
You can either use this as a static class or as an object. Just create an array of String[] fields, pass it, and the class will loop through those fields and add to them from your file of choice when you use one of the parse functions. The class has 3 default delimiters or lets you choose your own.

ItemCompare.java:
Useful for Lists that come about as a result of the Parser.java class, but can also order any other List of HashMaps that uses keys and values that extend the Comparable class. Just input an array with a list of the keys you wish to order (in the order that you wish to sort the list by these fields) and all will be done. To sort one or more of the fields in reverse, simply pass a boolean[] array with the index of the particular key you want to reverse in your list of keys set to 'true'.
