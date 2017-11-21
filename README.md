# Advent of Code 2016 problems

Since they all involve parsing some kind of input data, this will be a job for ... [Instaparse](https://github.com/Engelberg/instaparse)!

With it we can use a context-free grammar to generate a tree-like structure from our data.

For example, here an input string for the day 4 problem. Each room is represented as a key, id and checksum:

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
        
 Now we pass the input to our shiny-new parser:

    (room-parser "aaaaa-bbb-z-y-x-123[abxyz]")
    
And we get our magical tree! Ooowee!
  
    => [:room [:key [:word "aaaaa"] [:word "bbb"] [:word "z"] [:word "y"] [:word "x"]]
    [:id "123"]
    [:checksum [:word "abxyz"]]]

We can even make a pretty little chart using rhizome and graphviz! Oooweee!

    (insta/visualize  (room-parser "aaaaa-bbb-z-y-x-123[abxyz]"))

![room](https://github.com/sdfwer124/interview-problems/blob/master/room.png)

Let's do another one!

Day 10 we are programming robots.

The input data consists of instructions like this:

    bot 147 gives low to bot 67 and high to bot 71
    bot 142 gives low to bot 128 and high to bot 164
    bot 47 gives low to bot 4 and high to bot 209
    bot 107 gives low to bot 194 and high to bot 103
    bot 102 gives low to bot 82 and high to bot 3
    bot 101 gives low to bot 46 and high to bot 111
    value 23 goes to bot 76
    
We'll break it down to the first line:

    (def bots "bot 147 gives low to bot 67 and high to bot 71")
    
Now we'll express the relevant data in EBNF notation: 

    (def bot-parser
      (insta/parser
        "handoff = bot+
         bot = word whitespace id
         word = #'[a-zA-Z]+'
         whitespace = #'\\s+'
         id = #'[0-9]+'"))
         
  Each transaction performed by the bots is represented as a "handoff".
  A handoff can involve any number of bots, a transmitter followed by one or more receivers.
  
  Let's use this parser to visualize this transaction:
  
    (insta/visualize (bot-parser bots))
    
![bots](https://github.com/sdfwer124/interview-problems/blob/master/src/advent/bots.png)
