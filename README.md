# TranslatorExercise

Consider this tree-structured document format:  A document begins with an ASCII character, which tells you how to interpret what follows.  Possibilities are:
s<N>:<S>  where <N> is a decimal number written in ASCII, and <S> is an ASCII string of length <N>.  Note that whitespace counts towards the length.
b<N1>:<N2>:<D1><D2>  where <N1> and <N2> are decimal numbers written in ASCII, <D1> is another document N1 bytes long, and <D2> is another document N2bytes long.
In other words, "b" indicates a binary tree node, while "s" indicates a terminal/literal node.  Example document:
    
    b8:17:s5:hellob4:8:s1: s5:world
    
This document contains 3 terminal nodes, "hello", " " (single whitespace character), and "world".
Your task has two parts:

Part 1:  Write a program to read a document from a file, and output a "translation set".  The translation set consists of key-value pairs, where the values are all the strings in the document, and the keys are identifiers of your choosing.  You may choose any representation for the translation set that's convenient for you (including simply serializing a data structure).  You can also write any auxiliary data that is helpful, and assume that you can read it back in step 2.
The idea is that the translation set will be given to a translator, and the translator will replace the string values with translated string values.  The keys will not be touched, so you can use them to identify the strings that were translated.

Part 2:  Write a program to read a translated translation set, along with the document that it was created from, and any auxiliary data that you created, and output a document with the translations in place of the original strings.  The output must have the same tree structure as the original document, and it must be correctly formatted.  This means that that <N> values must be updated to account for strings that have changed length in translation, rather than blindly substituting in the new string values.
Do not make use of the ordering of the translated translation set, that is, do not assume that the translations will come back in the same order as you wrote them out.  Also, do not assume that all keys in the original translation set will be present in the translated translation set; if a key is missing, take that to mean that that string should be left untranslated.
Ideally, your solution will scale well to large documents.  Ideally, it will be efficient when the translated translation set contains only a few entries.  Of course, concentrate on correctness first.
If you need any clarification, please ask, or just make an assumption, but be sure to document any assumptions you make.
You can use any language or platform you need.
