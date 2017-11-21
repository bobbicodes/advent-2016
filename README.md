# interview-problems

Solutions for Advent of Code 2016 problems.

Since they all involve parsing some kind of input data, this will be a job for ... [Instaparse](https://github.com/Engelberg/instaparse)!

With it we can use a context-free grammar to generate a tree-like structure.

For example, here is our parser for the day 4 problem.
Each room is represented as a key, id and checksum:

    aaaaa-bbb-z-y-x-123[abxyz]

The key is made of one or more strings of letters separated with hyphens.
The id is a string of numbers, and the checksum is a string of letters in brackets.
This is how we represent that in EBNF notation... In Clojure!:
  
    (def room-parser
      (insta/parser
        "room = key id checksum
        key = (word <separator>)+
        <separator> = '-'
        word = #'[a-zA-Z]+'
        checksum = <'['> word <']'>
        id = #'[0-9]+'"))
        
  Now we pass it our input string:

    (room-parser "aaaaa-bbb-z-y-x-123[abxyz]")
    
And we get our tree!
  
    => [:room [:key
    [:word "aaaaa"] [:word "bbb"] [:word "z"] [:word "y"] [:word "x"]]
    [:id "123"]
    [:checksum [:word "abxyz"]]]

We can even make a pretty little chart using rhizome and graphviz! Oooweee!
