(ns day04.core
  (:require
   [clojure.java.io :as io]
   [instaparse.core :as insta]))


(def room-parser
  (insta/parser
    "room = key id checksum
     key = (word <separator>)+
     <separator> = '-'
     word = #'[a-zA-Z]+'
     checksum = '[' word ']'
     whitespace = #'\\s+'
     id = #'[0-9]+'"))
