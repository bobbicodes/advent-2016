(ns advent.core)

Start at the given coordinates
(where you just landed)

and face North.

Represent our location as cartesian coordinates

It seems like a specific state is involved,
as your direction of travel is dependent upon
which way you were facing before the transposition.

Then,
follow the provided sequence:

(def steps '(R2, L3, R2, R4, L2, L1, R2, R4, R1, L4, L5, R5, R5, R2, R2, R1, L2, L3, L2, L1, R3, L5, R187, R1, R4, L1, R5, L3, L4, R50, L4, R2, R70, L3, L2, R4, R3, R194, L3, L4, L4, L3, L4, R4, R5, L1, L5, L4, R1, L2, R4, L5, L3, R4, L5, L5, R5, R3, R5, L2, L4, R4, L1, R3, R1, L1, L2, R2, R2, L3, R3, R2, R5, R2, R5, L3, R2, L5, R1, R2, R2, L4, L5, L1, L4, R4, R3, R1, R2, L1, L2, R4, R5, L2, R3, L4, L5, L5, L4, R4, L2, R1, R1, L2, L3, L2, R2, L4, R3, R2, L1, L3, L2, L4, L4, R2, L3, L3, R2, L4, L3, R4, R3, L2, L1, L4, R4, R2, L4, L4, L5, L1, R2, L5, L2, L3, R2, L2))

; as we walk the seq, we change direction depending on the first item, and change the appropriate coordinate based on the second item, the number.

We start facing north. this will be 1.
an r will increment our direction,
an l will decrement it.
divide it by 4 and take the remainder to determine current direction. we will bind this as alocal variable as we iterate through the steps.

1 - north, increment y
2 - east, increment x
3 - south, decrement y
4 - west, decrement x

; This will return our new coordinates, which we can pass with [0 0] to our manhattan distance function below.  


(defn parse-data [d]
  (->> d
       (map name)
       (map (juxt first
                  #(Integer/parseInt (apply str (rest %)))))))

user> (parse-data steps)
([\R 2] [\L 3] [\R 2] [\R 4] [\L 2] [\L 1] [\R 2] [\R 4] [\R 1] [\L 4] [\L 5] [\R 5] [\R 5] [\R 2] [\R 2] [\R 1] [\L 2] [\L 3] [\L 2] [\L 1] [\R 3] [\L 5] [\R 187] [\R 1] [\R 4] [\L 1] [\R 5] [\L 3] [\L 4] [\R 50] [\L 4] [\R 2] [\R 70] [\L 3] [\L 2] [\R 4] [\R 3] [\R 194] [\L 3] [\L 4] [\L 4] [\L 3] [\L 4] [\R 4] [\R 5] [\L 1] [\L 5] [\L 4] [\R 1] [\L 2] [\R 4] [\L 5] [\L 3] [\R 4] [\L 5] [\L 5] [\R 5] [\R 3] [\R 5] [\L 2] [\L 4] [\R 4] [\L 1] [\R 3] [\R 1] [\L 1] [\L 2] [\R 2] [\R 2] [\L 3] [\R 3] [\R 2] [\R 5] [\R 2] [\R 5] [\L 3] [\R 2] [\L 5] [\R 1] [\R 2] [\R 2] [\L 4] [\L 5] [\L 1] [\L 4] [\R 4] [\R 3] [\R 1] [\R 2] [\L 1] [\L 2] [\R 4] [\R 5] [\L 2] [\R 3] [\L 4] [\L 5] [\L 5] [\L 4] [\R 4] [\L 2] [\R 1] [\R 1] [\L 2] [\L 3] [\L 2] [\R 2] [\L 4] [\R 3] [\R 2] [\L 1] [\L 3] [\L 2] [\L 4] [\L 4] [\R 2] [\L 3] [\L 3] [\R 2] [\L 4] [\L 3] [\R 4] [\R 3] [\L 2] [\L 1] [\L 4] [\R 4] [\R 2] [\L 4] [\L 4] [\L 5] [\L 1] [\R 2] [\L 5] [\L 2] [\L 3] [\R 2] [\L 2])


(defn positions [d]
  (->> (map first d)
       (reductions #(({\L dec \R inc} %2) %1) 0)
       rest
       (map #(mod % 4))
       (map [[0 1] [1 0] [0 -1] [-1 0]])
       (mapcat repeat (map second d))
(reductions (partial map +) (list 0 0))))

; then we take the last item, our final set of coordinates, which is:

(-123 -123)

and we pump that into our calculator func:

user> (defn bunny [u v]
  (reduce +
    (map (fn [[a b]] (Math/abs (- a b)))
         (map vector u v))))
#'user/bunny
user> (bunny [0 0] [-123 -123])
246
