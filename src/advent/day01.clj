(ns advent.core
  (:require [instaparse.core :as insta]
            [clojure.java.io :as io]))

; Our first task is to rescue Santa's sleigh which was stolen by the Easter bunny. We're dropped off in the middle of a city with an impossibly long list of directions and to reach the goal we have to calculate the "Manhattan distance", the shortest path along a grid,  which is equal to the sum of the absolute differences of the Cartesian coordinates. That's really just a fancy way of asking, "How far is it in each direction?"

; So I made a manhattan distance function:

(defn bunny [u v]
  (reduce +
    (map (fn [[a b]] (Math/abs (- a b)))
         (map vector u v))))

; But first we have to parse the directions into a sequence of steps and turns, and then appropriately increment or decrement coordinates according to the direction of travel, determined by dividing the value of the number of turns by 4 and taking the remainder.

; start at location l = [0 0]
; direction d = 1 (North)

(step-parser steps)
;=> ("R" "2" "L" "3" "R" . . . )

; as we walk the seq, we change direction depending on the first item, and change the appropriate coordinate based on the second item, the number.

; We start facing north. this will be 1.
; an "R" will increment our direction,
; an "L" will decrement it.
; divide it by 4 and take the remainder to determine current direction. we will bind this as a local variable as we iterate through the steps.

; The output from our parser is a seq of alternating turns and steps. We could pop them off 2 at a time to modify a set of coordinates according to their values, but I kinda would rather split it into 2 lists, "turns" (letters) and "steps" (numbers). 

(def turns (first (#(apply map list (partition 2 %)) (step-parser steps))))

turns
;=> ("R" "L" "R" "R" "L" . . .)

(def steps (last (#(apply map list (partition 2 %)) (step-parser steps))))

steps
;=> ("2" "3" "2" "4" "2" . . .)

; Our first instruction is ("R" "2"),
; which means our current direction (1) becomes 2.
; when our direction is 2, our steps increment x,
; so [0 0] becomes [2 0].


; 1 - north, increment y of l
; 2 - east, increment x of l
; 3 - south, decrement y of l
; 4 - west, decrement x of l

(defn go-north [l n]
  (vector (first l)
          (+ n (last l))))
(defn go-east [l n]
  (vector (+ n (first l))
          (last l)))
(defn go-south [l n]
  (vector (first l)
          (- (last l) n)))
(defn go-west [l n]
  (vector (- (first l) n)
          (last l)))

(defn step [n])
(loop [d 1 l [0 0] t turns s steps]
  (cond (= d 1) (go-north l n)
        (= d 2) (go-east l n)
        (= d 3) (go-south l n)
        (= d 4) (go-west l n)))

; first thing we grab is an "R", so we inc d.
; then we receive a "2", so first we need to convert it into an integer.

; since d is at 2, we inc the x of l by (read-string "2")
; Let's write out our movements as functions:
; it will take a vector of coordinates and a number

; Great! Each func modifies the proper coordinate.
; we pull a number from the directions, adjust d, modulo 4 and call one of the above, just a 4-way spinner.

; This returns our new coordinates.
; repeat until end of lists, and pass the final set with [0 0] to our manhattan distance function.  

(defn bunny [u v]
  (reduce +
    (map (fn [[a b]] (Math/abs (- a b)))
         (map vector u v))))

(bunny [0 0] [-123 -123])
;=> 246
