# interview-problems

In the process of preparing solutions for Advent of Code 2016 problems 1, 4 and 10 for interview with Apple.

Since they all begin with parsing some kind of input data, I have decided this will be a perfect job for [Instaparse](https://github.com/Engelberg/instaparse). 

For example, here is our parser for the day 4 problem:

(def room-parser
  (insta/parser
    "room = key id checksum
     key = (word separator)+
     separator = '-'
     word = #'[a-zA-Z]+'
     checksum = '[' word ']'
     id = #'[0-9]+'"))
     

My complete ramblings are recorded in my [blog](https://crjunkie.blogspot.com/2017/11/my-interview-for-apple.html).
