(ns advent.core
  (:require [instaparse.core :as insta]))

; Our first task is to rescue Santa's sleigh which was stolen by the Easter bunny. We're dropped off in the middle of a city with an impossibly long list of directions and to reach the goal we have to calculate the "Manhattan distance", the shortest path along a grid,  which is equal to the sum of the absolute differences of the Cartesian coordinates. That's really just a fancy way of asking, "How far is it in each direction?"

; So I made that function:

(defn bunny [u v]
  (reduce +
    (map (fn [[a b]] (Math/abs (- a b)))
         (map vector u v))))

; But first we have to parse the directions into a sequence of steps and turns, and then appropriately incremented or decremented my coordinates according to the direction of travel, determined by dividing the value of the number of turns by 4 and taking the remainder.

; Start at the given coordinates (where you just landed) and face North.

; Represent our location as cartesian coordinates
; follow the provided sequence:

(def steps "R2, L3, R2, R4, L2, L1, R2, R4, R1, L4, L5, R5, R5, R2, R2, R1, L2, L3, L2, L1, R3, L5, R187, R1, R4, L1, R5, L3, L4, R50, L4, R2, R70, L3, L2, R4, R3, R194, L3, L4, L4, L3, L4, R4, R5, L1, L5, L4, R1, L2, R4, L5, L3, R4, L5, L5, R5, R3, R5, L2, L4, R4, L1, R3, R1, L1, L2, R2, R2, L3, R3, R2, R5, R2, R5, L3, R2, L5, R1, R2, R2, L4, L5, L1, L4, R4, R3, R1, R2, L1, L2, R4, R5, L2, R3, L4, L5, L5, L4, R4, L2, R1, R1, L2, L3, L2, R2, L4, R3, R2, L1, L3, L2, L4, L4, R2, L3, L3, R2, L4, L3, R4, R3, L2, L1, L4, R4, R2, L4, L4, L5, L1, R2, L5, L2, L3, R2, L2")

(def step-parser
  (insta/parser
    "<step> = (direction steps)+
     <direction> = #'[a-zA-Z]+'
     <steps> = #'[0-9]+' <', '?>"))

; This produces the following data structure:

(step-parser steps)
;=> ("R" "2" "L" "3" "R" "2" "R" "4" "L" "2" "L" "1" "R" "2" "R" "4" "R" "1" "L" "4" "L" "5" "R" "5" "R" "5" "R" "2" "R" "2" "R" "1" "L" "2" "L" "3" "L" "2" "L" "1" "R" "3" "L" "5" "R" "187" "R" "1" "R" "4" "L" "1" "R" "5" "L" "3" "L" "4" "R" "50" "L" "4" "R" "2" "R" "70" "L" "3" "L" "2" "R" "4" "R" "3" "R" "194" "L" "3" "L" "4" "L" "4" "L" "3" "L" "4" "R" "4" "R" "5" "L" "1" "L" "5" "L" "4" "R" "1" "L" "2" "R" "4" "L" "5" "L" "3" "R" "4" "L" "5" "L" "5" "R" "5" "R" "3" "R" "5" "L" "2" "L" "4" "R" "4" "L" "1" "R" "3" "R" "1" "L" "1" "L" "2" "R" "2" "R" "2" "L" "3" "R" "3" "R" "2" "R" "5" "R" "2" "R" "5" "L" "3" "R" "2" "L" "5" "R" "1" "R" "2" "R" "2" "L" "4" "L" "5" "L" "1" "L" "4" "R" "4" "R" "3" "R" "1" "R" "2" "L" "1" "L" "2" "R" "4" "R" "5" "L" "2" "R" "3" "L" "4" "L" "5" "L" "5" "L" "4" "R" "4" "L" "2" "R" "1" "R" "1" "L" "2" "L" "3" "L" "2" "R" "2" "L" "4" "R" "3" "R" "2" "L" "1" "L" "3" "L" "2" "L" "4" "L" "4" "R" "2" "L" "3" "L" "3" "R" "2" "L" "4" "L" "3" "R" "4" "R" "3" "L" "2" "L" "1" "L" "4" "R" "4" "R" "2" "L" "4" "L" "4" "L" "5" "L" "1" "R" "2" "L" "5" "L" "2" "L" "3" "R" "2" "L" "2")

; as we walk the seq, we change direction depending on the first item, and change the appropriate coordinate based on the second item, the number.
; We start facing north. this will be 1.
; an r will increment our direction,
; an l will decrement it.
; divide it by 4 and take the remainder to determine current direction. we will bind this as alocal variable as we iterate through the steps.

; 1 - north, increment y
; 2 - east, increment x
; 3 - south, decrement y
; 4 - west, decrement x

; This will return our new coordinates, which we can pass with [0 0] to our manhattan distance function below.  

; then we take the last item, our final set of coordinates, which is:

; (-123 -123)

; and we pump that into our calculator func:

(defn bunny [u v]
  (reduce +
    (map (fn [[a b]] (Math/abs (- a b)))
         (map vector u v))))
(bunny [0 0] [-123 -123])
;=> 246

; The output from our parser is a seq of alternating turns and steps. We will pop them off 2 at a time to modify a set of coordinates according to their values.

; Our direction begins at 1 which is north. 
; Our coordinates are [0 0].
; Our first instruction is ("R" "2"),
; which means our current direction (1) becomes 2.
; when our direction is 2, our steps increment x,
; so [0 0] becomes [2 0].

; (remember:)
; 1 - north, increment y
; 2 - east, increment x
; 3 - south, decrement y
; 4 - west, decrement x

; We will create a loop with local bindings for
; direction d and location l.

(loop [d 1 l [0 0]])

; first thing we grab is an "R", so we inc d.

(if (= x "R")
    (inc d)
    (dec d))

; then we receive a "2", so first we need to convert it into an integer.



; since d is at 2, we inc the x of l by (read-string "2")

; Let's write out our movements as functions:

; it will take a vector of coordinates and a number

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

; Great! Each func modifies the coordinates appropriately.

; we pull a number from the directions, adjust d, modulo 4 and call one of the above, just a 4-way spinner.
(defn step [l n d]
  (cond (= d 1) (go-north l n)
        (= d 2) (go-east l n)
        (= d 3) (go-south l n)
        (= d 4) (go-west l n)))
