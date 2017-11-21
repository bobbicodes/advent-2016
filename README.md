# interview-problems

In the process of preparing solutions for Advent of Code 2016 problems 1, 4 and 10.

Since they all involve parsing some kind of input data, this will be a perfect job for ... [Instaparse](https://github.com/Engelberg/instaparse)!

We use EBNF notation to generate a tree-like structure. For example, here is our parser for the day 4 problem.
Each room is represented as a key, id and checksum:

"aaaaa-bbb-z-y-x-123[abxyz]"

  (def room-parser
    (insta/parser
      "room = key id checksum
      key = (word < separator >)+
      < separator > = '-'
      word = #'[a-zA-Z]+'
      checksum = '[' word ']'
      id = #'[0-9]+'"))

  (room-parser "aaaaa-bbb-z-y-x-123[abxyz]")
  
  => [:room [:key [:word "aaaaa"]
  [:word "bbb"]
  [:word "z"]
  [:word "y"]
  [:word "x"]]
  [:id "123"]
  [:checksum "[" [:word "abxyz"] "]"]]
