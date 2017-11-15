(ns advent.core)

Start at the given coordinates
(where you just landed)

and face North.


Then,
follow the provided sequence:

R2, L3, R2, R4, L2, L1, R2, R4, R1, L4, L5, R5, R5, R2, R2, R1, L2, L3, L2, L1, R3, L5, R187, R1, R4, L1, R5, L3, L4, R50, L4, R2, R70, L3, L2, R4, R3, R194, L3, L4, L4, L3, L4, R4, R5, L1, L5, L4, R1, L2, R4, L5, L3, R4, L5, L5, R5, R3, R5, L2, L4, R4, L1, R3, R1, L1, L2, R2, R2, L3, R3, R2, R5, R2, R5, L3, R2, L5, R1, R2, R2, L4, L5, L1, L4, R4, R3, R1, R2, L1, L2, R4, R5, L2, R3, L4, L5, L5, L4, R4, L2, R1, R1, L2, L3, L2, R2, L4, R3, R2, L1, L3, L2, L4, L4, R2, L3, L3, R2, L4, L3, R4, R3, L2, L1, L4, R4, R2, L4, L4, L5, L1, R2, L5, L2, L3, R2, L2


either turn left (L) or right (R) 90 degrees,
then walk forward the given number of blocks,
ending at a new intersection.

There's no time to follow such ridiculous instructions on foot, though, so you take a moment and work out the destination.


Given that you can only walk on the street grid of the city,
how far is the shortest path to the destination?


R2, L3

leaves you 2 blocks East and 3 blocks North, or 5 blocks away.

R2, R2, R2

leaves you 2 blocks due South of your starting position, which is 2 blocks away.

R5, L5, R5, R3 leaves you 12 blocks away.

How many blocks away is Easter Bunny HQ?


Calculating Manhattan Distance in Clojure

Takes two points u and v,
zips together the x and the y components to [[x1 x2] [y1 y2]],
subtracts them from each other, makes them absolute with Math/abs and then sums up the results.

; (manh-dist [2 3] [5 6]) => 6
(defn bunny [u v]
  (reduce +
    (map (fn [[a b]] (Math/abs (- a b)))
         (map vector u v))))


(map vector '(1 2 3) '(4 5 6))

does what you want:

=> ([1 4] [2 5] [3 6])

Note: it is fn [[a b]] and not fn [a b] because Destructuring was used.

Here is the calculation from inside to outside:

; zip x's and y's together:
(zip [2 3] [5 6])
=> ([2 5] [3 6])

; subtract them from each other, make them absolute:
(map (fn [[a b]] (Math/abs (- a b))) [[2 5] [3 6]])
=> (3 3)

; sum of the results
(reduce + [3 3]) => 6

As we can see, it does exactly the same that we did manually before.
It even returns the same result.
