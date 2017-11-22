(ns advent.core
  (:require
   [clojure.java.io :as io]))

; The input data consists of instructions like this:

bot 147 gives low to bot 67 and high to bot 71
bot 142 gives low to bot 128 and high to bot 164
bot 47 gives low to bot 4 and high to bot 209
bot 107 gives low to bot 194 and high to bot 103
bot 102 gives low to bot 82 and high to bot 3
bot 101 gives low to bot 46 and high to bot 111
value 23 goes to bot 76
We'll break it down to the first line:

(def bots "bot 147 gives low to bot 67 and high to bot 71")

; Now we'll express the relevant data in EBNF notation:

(def bot-parser
  (insta/parser
    "handoff = bot+
     bot = word whitespace id
     word = #'[a-zA-Z]+'
     whitespace = #'\\s+'
     id = #'[0-9]+'"))

; Each transaction performed by the bots is represented as a "handoff". A handoff is a messenger bot followed by one or more receiver bots.

; Let's use this parser to visualize this transaction:

(insta/visualize (bot-parser bots))
