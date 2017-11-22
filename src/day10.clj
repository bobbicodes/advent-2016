(ns advent.core
  (:require [instaparse.core :as insta]
            [clojure.java.io :as io]))

; Just start with creating a parser for this:

(def bots (str (slurp (io/resource "day10input"))))

(def bot-parser
  (insta/parser
    "handoff = bot+
     bot = word id <word*>
     word = #'[a-zA-Z]+' whitespace
     whitespace = #'\\s+'
     id = #'[0-9]+' whitespace?"))

bot 147 gives low to bot 67 and high to bot 71
bot 142 gives low to bot 128 and high to bot 164
bot 47 gives low to bot 4 and high to bot 209
bot 107 gives low to bot 194 and high to bot 103
bot 102 gives low to bot 82 and high to bot 3
bot 101 gives low to bot 46 and high to bot 111
value 23 goes to bot 76
