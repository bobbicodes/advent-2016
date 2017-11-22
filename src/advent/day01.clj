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
    "step = (direction steps)+
     direction = #'[a-zA-Z]+'
     steps = #'[0-9]+' ', '?"))

; This produces the following data structure:

(step-parser steps)
;=> [:step [:direction "R"] [:steps "2" ", "] [:direction "L"] [:steps "3" ", "] [:direction "R"] [:steps "2" ", "] [:direction "R"] [:steps "4" ", "] [:direction "L"] [:steps "2" ", "] [:direction "L"] [:steps "1" ", "] [:direction "R"] [:steps "2" ", "] [:direction "R"] [:steps "4" ", "] [:direction "R"] [:steps "1" ", "] [:direction "L"] [:steps "4" ", "] [:direction "L"] [:steps "5" ", "] [:direction "R"] [:steps "5" ", "] [:direction "R"] [:steps "5" ", "] [:direction "R"] [:steps "2" ", "] [:direction "R"] [:steps "2" ", "] [:direction "R"] [:steps "1" ", "] [:direction "L"] [:steps "2" ", "] [:direction "L"] [:steps "3" ", "] [:direction "L"] [:steps "2" ", "] [:direction "L"] [:steps "1" ", "] [:direction "R"] [:steps "3" ", "] [:direction "L"] [:steps "5" ", "] [:direction "R"] [:steps "187" ", "] [:direction "R"] [:steps "1" ", "] [:direction "R"] [:steps "4" ", "] [:direction "L"] [:steps "1" ", "] [:direction "R"] [:steps "5" ", "] [:direction "L"] [:steps "3" ", "] [:direction "L"] [:steps "4" ", "] [:direction "R"] [:steps "50" ", "] [:direction "L"] [:steps "4" ", "] [:direction "R"] [:steps "2" ", "] [:direction "R"] [:steps "70" ", "] [:direction "L"] [:steps "3" ", "] [:direction "L"] [:steps "2" ", "] [:direction "R"] [:steps "4" ", "] [:direction "R"] [:steps "3" ", "] [:direction "R"] [:steps "194" ", "] [:direction "L"] [:steps "3" ", "] [:direction "L"] [:steps "4" ", "] [:direction "L"] [:steps "4" ", "] [:direction "L"] [:steps "3" ", "] [:direction "L"] [:steps "4" ", "] [:direction "R"] [:steps "4" ", "] [:direction "R"] [:steps "5" ", "] [:direction "L"] [:steps "1" ", "] [:direction "L"] [:steps "5" ", "] [:direction "L"] [:steps "4" ", "] [:direction "R"] [:steps "1" ", "] [:direction "L"] [:steps "2" ", "] [:direction "R"] [:steps "4" ", "] [:direction "L"] [:steps "5" ", "] [:direction "L"] [:steps "3" ", "] [:direction "R"] [:steps "4" ", "] [:direction "L"] [:steps "5" ", "] [:direction "L"] [:steps "5" ", "] [:direction "R"] [:steps "5" ", "] [:direction "R"] [:steps "3" ", "] [:direction "R"] [:steps "5" ", "] [:direction "L"] [:steps "2" ", "] [:direction "L"] [:steps "4" ", "] [:direction "R"] [:steps "4" ", "] [:direction "L"] [:steps "1" ", "] [:direction "R"] [:steps "3" ", "] [:direction "R"] [:steps "1" ", "] [:direction "L"] [:steps "1" ", "] [:direction "L"] [:steps "2" ", "] [:direction "R"] [:steps "2" ", "] [:direction "R"] [:steps "2" ", "] [:direction "L"] [:steps "3" ", "] [:direction "R"] [:steps "3" ", "] [:direction "R"] [:steps "2" ", "] [:direction "R"] [:steps "5" ", "] [:direction "R"] [:steps "2" ", "] [:direction "R"] [:steps "5" ", "] [:direction "L"] [:steps "3" ", "] [:direction "R"] [:steps "2" ", "] [:direction "L"] [:steps "5" ", "] [:direction "R"] [:steps "1" ", "] [:direction "R"] [:steps "2" ", "] [:direction "R"] [:steps "2" ", "] [:direction "L"] [:steps "4" ", "] [:direction "L"] [:steps "5" ", "] [:direction "L"] [:steps "1" ", "] [:direction "L"] [:steps "4" ", "] [:direction "R"] [:steps "4" ", "] [:direction "R"] [:steps "3" ", "] [:direction "R"] [:steps "1" ", "] [:direction "R"] [:steps "2" ", "] [:direction "L"] [:steps "1" ", "] [:direction "L"] [:steps "2" ", "] [:direction "R"] [:steps "4" ", "] [:direction "R"] [:steps "5" ", "] [:direction "L"] [:steps "2" ", "] [:direction "R"] [:steps "3" ", "] [:direction "L"] [:steps "4" ", "] [:direction "L"] [:steps "5" ", "] [:direction "L"] [:steps "5" ", "] [:direction "L"] [:steps "4" ", "] [:direction "R"] [:steps "4" ", "] [:direction "L"] [:steps "2" ", "] [:direction "R"] [:steps "1" ", "] [:direction "R"] [:steps "1" ", "] [:direction "L"] [:steps "2" ", "] [:direction "L"] [:steps "3" ", "] [:direction "L"] [:steps "2" ", "] [:direction "R"] [:steps "2" ", "] [:direction "L"] [:steps "4" ", "] [:direction "R"] [:steps "3" ", "] [:direction "R"] [:steps "2" ", "] [:direction "L"] [:steps "1" ", "] [:direction "L"] [:steps "3" ", "] [:direction "L"] [:steps "2" ", "] [:direction "L"] [:steps "4" ", "] [:direction "L"] [:steps "4" ", "] [:direction "R"] [:steps "2" ", "] [:direction "L"] [:steps "3" ", "] [:direction "L"] [:steps "3" ", "] [:direction "R"] [:steps "2" ", "] [:direction "L"] [:steps "4" ", "] [:direction "L"] [:steps "3" ", "] [:direction "R"] [:steps "4" ", "] [:direction "R"] [:steps "3" ", "] [:direction "L"] [:steps "2" ", "] [:direction "L"] [:steps "1" ", "] [:direction "L"] [:steps "4" ", "] [:direction "R"] [:steps "4" ", "] [:direction "R"] [:steps "2" ", "] [:direction "L"] [:steps "4" ", "] [:direction "L"] [:steps "4" ", "] [:direction "L"] [:steps "5" ", "] [:direction "L"] [:steps "1" ", "] [:direction "R"] [:steps "2" ", "] [:direction "L"] [:steps "5" ", "] [:direction "L"] [:steps "2" ", "] [:direction "L"] [:steps "3" ", "] [:direction "R"] [:steps "2" ", "] [:direction "L"] [:steps "2"]]

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
