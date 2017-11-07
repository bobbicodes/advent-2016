(def Datas [
               [1   2  3  4]
               [5   6  7  8]
               [9  10 11 12]
               [13 14 15 16]
               ])

; Datas[0][0] == 1
; Datas[0][1] == 2
;...
; Datas[1][3] == 8
; Datas[2][3] == 12
; 
;; Write a function to print this out in a spiral
;; Ex: 1,2,3,4,8,12,16,15,14,13,9,5,6...

(defn spiral [data] (if (= (* (count data) (count (first data))) (count (digits data))) (digits data) (recur (middle data))))

(spiral Datas)
;=> (10 11 6 7)

; This function returns the innermost block.

(last (first (rest (butlast Datas))))
;=> 8

(last (first (rest (rest (butlast Datas)))))
;=> 12

(reverse (last Datas))
;=> (16 15 14 13)

(first (first (reverse (rest (butlast Datas)))))
;=> 9

(first (first (rest (reverse (rest (butlast Datas))))))
;=> 5

(rest (butlast (first (next (butlast Datas)))))
;=> (6 7)

(rest (butlast (last (next (rest (butlast Datas))))))
;=> (10 11)


(defn spiral [n]
    
    ;; print first line
    (println (first n))
    ;; is it the last line? 
    ;(while (=))
    ;(count n)(last second Datas)
    ;;if not,
    ;; print last item of next line
    (while ())
        (println (last (first (rest n))))
    )

  ;;do this until last line, then
    ;; print last line backwards
    )

(spiral Datas)

;print last of first of rest until second to last row (going down)
; print last row backwards
; print first of second to last row until second row (going up)
;   



;(defn spiral [n]
;    (let [f (first n) l (last n)])
;    (if (=  (count (rest n)
;    (recur rest n)
;    
;    ;; read and drop line
;                   
;    ;; read and drop right
;                   
;    ;;
;    
;    (if (= 1 (count n))
;        (reverse n)
;        (last n)
;        ))
;(first Datas)
;(rest  Datas)
;(last Datas)

; We are going to get rid of the first and last rows,
; and deal with them later.

; (rest (butlast Datas))
;=> ([5 6 7 8] [9 10 11 12])

; Next we process the middle rows, rinse and repeat.

; What we now want is the first and last of each row,
; in the following order:

; last item of first row,
; last item of next row, until all rows are done.
; first item of last row, first of next to last row, until the first.

; that much should establish our loop, because we're now
; back where we started, at the top left on 5.
; so we go back to the beginning, the first step -

; to drop the first and last row, which at this point is [6 7 10 11].

; we are done, because...
; there are no more rows.

; more succinctly:


; take first row:

(first Datas)
;=> [1 2 3 4]

;; get rid of the first and last rows

(rest (butlast Datas))
;=> ([5 6 7 8] [9 10 11 12])

;; get last item of first row,

(last (first (rest (butlast Datas))))
;=> 8

; last item of next row,

(last (first (rest (rest (butlast Datas)))))
;=> 12

; until all rows are done.

(conj (conj [] (last (first (rest (butlast Datas))))) 12)
[8 12]

; first item of last row - we will reverse the rows and do as before.

(first (first (reverse (rest (butlast Datas)))))
;=> 9

first of next to last row, until the first.

(first (first (rest (reverse (rest (butlast Datas))))))
;=> 5

; now we repeat with

(rest (butlast (first (next (butlast Datas)))))

and

(rest (butlast (next (next (butlast Datas)))))

(rest (butlast (first (next (butlast Datas)))))
;=> (6 7)

(rest (butlast (last (next (rest (butlast Datas))))))
;=> (10 11)

; That's all the numbers we need to get for this dataset.

(def Datas [
               [1   2  3  4]
               [5   6  7  8]
               [9  10 11 12]
               [13 14 15 16]
               ])

(first Datas)
;=> [1 2 3 4]

(last (first (rest (butlast Datas))))
;=> 8

(last (first (rest (rest (butlast Datas)))))
;=> 12

(reverse (last Datas))
;=> (16 15 14 13)

(first (first (reverse (rest (butlast Datas)))))
;=> 9

(first (first (rest (reverse (rest (butlast Datas))))))
;=> 5

(rest (butlast (first (next (butlast Datas)))))
;=> (6 7)

(rest (butlast (last (next (rest (butlast Datas))))))
;=> (10 11)


;; Now putting it together:

(def spiral-1 (conj (conj (first Datas) (last (first (rest (butlast Datas)))))(last (first (rest (rest (butlast Datas)))))))

spiral-1
;=> [1 2 3 4 8 12]

(into spiral-1 (reverse (last Datas)))
;=> [1 2 3 4 8 12 16 15 14 13]

[(first (first (reverse (rest (butlast Datas))))) (first (first (rest (reverse (rest (butlast Datas))))))]
;=> [9 5]

(into (reverse (rest (butlast (last (next (rest (butlast Datas))))))) (reverse (rest (butlast (first (next (butlast Datas))))))) 
;=> (6 7 11 10)

(into [(first (first (reverse (rest (butlast Datas))))) (first (first (rest (reverse (rest (butlast Datas))))))] (into (reverse (rest (butlast (last (next (rest (butlast Datas))))))) (reverse (rest (butlast (first (next (butlast Datas))))))))
;=> [9 5 6 7 11 10]

; A working solution, but isn't recusive and will only work on 
; a dataset of the same size.

(into (into (conj (conj
  (first Datas)
  (last (first (rest (butlast Datas)))))
    (last (first (rest (rest (butlast Datas))))))
    (reverse (last Datas)))
  (into [(first (first (reverse (rest (butlast Datas)))))
         (first (first (rest (reverse (rest (butlast Datas))))))]
  (into (reverse (rest (butlast (last (next (rest (butlast Datas)))))))
    (reverse (rest (butlast (first (next (butlast Datas)))))))))
;=> [1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10]

; Create loops to extend this pattern.

; Consider the shape of the data.


(def Datas [
               [1   2  3  4]
               [5   6  7  8]
               [9  10 11 12]
               [13 14 15 16]
               ])

(first Datas)
;=> [1 2 3 4]

; We'll define the problem as dealing with
a vector of vectors containing the same number of elements,
but not necessarily square, that is, it may be rectangular.

; The first row establishes the row length.
; Here's where we make a "right turn", 
; proceeding to take the last digit of each subsequent row.

; We want a function called take-last,
; which will take a data structure and grab the last digit...
; that function already exists, it's called last!

(defn get-right [data] (map last data))
(get-right (rest Datas))
;=> (8 12 16)

(defn get-bottom [data] (reverse (last data)))
(rest (get-bottom Datas))
;=> (15 14 13)

(defn get-left [data] (reverse (map first data)))
(rest (butlast (get-left Datas)))
;=> (13 9 5 1)

(defn get-top [data] (first data))
;=> [1 2 3 4]

(defn drop-top [data] (rest data))
(defn drop-right [data] (map butlast data))
(defn drop-bottom [data] (butlast data))
(defn drop-left [data] (map rest data))

(defn digits [data]
  (into
    (into
      (into
        (get-top data)
        (get-right (rest data)))
    (rest (get-bottom data)))
    (rest (butlast (get-left data)))))

(defn digits2 [data]
  (into
    (into
      (into
        (get-right (rest data))
        (get-top data))
    (rest (get-bottom data)))
    (rest (butlast (get-left data)))))

; we'll check if there's more after we've done a complete square.

(digits Datas)
;=> [1 2 3 4 8 12 16 15 14 13 9 5]

; We can see that we are not done:

(count Datas)
;=> 4
(count (first Datas))
;=> 4

; multiply these to get total items

(count (digits Datas))
;=> 12

; So what we want to do now is to call digits on the middle numbers.
; What digits does is give us the outside numbers,
; so we need a function that will take a coll
; and return just the inside numbers.

(defn middle [data]
  (map butlast (map rest (rest (butlast data)))))

; These 4 functions define the spiral pattern.
; Each one will be fed the appropriate data.

; After the first step removes the top, it will return the rest
; as input to the top function.

; at every step we need to test if we are done.

; this one works for the first one:

(defn is-more? [data] (if (next data) true false))

; Implementing these functions:

; Begin by passing the coll to get-top. It should return

; Now let's try it with this:

(def big-data [
              [1  2  3  4  5  6  7  8]
              [9  10 11 12 13 14 15 16]
              [17 18 19 20 21 22 23 24]
              [25 26 27 28 29 30 31 32]
              [33 34 35 36 37 38 39 40]
              [41 42 43 44 45 46 47 48]
              [49 50 51 52 53 54 55 56]
              [57 58 59 60 61 62 63 64]])

; 1 2 3 4 5 6 7 8
; 16 24 32 40 48 56
; 64 63 62 61 60 59 58 57
; 49 41 33 25 17 9

(outside big-data)
;=> [1 2 3 4 5 6 7 8
;    16 24 32 40 48 56
;    64 63 62 61 60 59 58 57
;    49 41 33 25 17 9]

; And we can see that it checks out.
; going on -

; 10 11 12 13 14 15
; 23 31 39 47 55
; 54 53 52

; come to think of it, we could avoid going through these operations
; by simply rotating the whole structure counterclockwise.

; This can be accomplished with a transpose function:

(defn transpose [data]
  (apply mapv vector data))
(transpose big-data)
;=> [[1 9 17 25 33 41 49 57] [2 10 18 26 34 42 50 58] [3 11 19 27 35 43 51 59] [4 12 20 28 36 44 52 60] [5 13 21 29 37 45 53 61] [6 14 22 30 38 46 54 62] [7 15 23 31 39 47 55 63] [8 16 24 32 40 48 56 64]]

; The problem is, this rotates it the wrong way!

; No bother, we can fix this with reverse:

(reverse (transpose big-data))
;=> ([8 16 24 32 40 48 56 64] [7 15 23 31 39 47 55 63] [6 14 22 30 38 46 54 62] [5 13 21 29 37 45 53 61] [4 12 20 28 36 44 52 60] [3 11 19 27 35 43 51 59] [2 10 18 26 34 42 50 58] [1 9 17 25 33 41 49 57])

; Let's verify that this really works...

(def big-data [
              [1  2  3  4  5  6  7  8]
              [9  10 11 12 13 14 15 16]
              [17 18 19 20 21 22 23 24]
              [25 26 27 28 29 30 31 32]
              [33 34 35 36 37 38 39 40]
              [41 42 43 44 45 46 47 48]
              [49 50 51 52 53 54 55 56]
              [57 58 59 60 61 62 63 64]])

;[8 16 24 32 40 48 56 64]
;[7 15 23 31 39 47 55 63]
;...

; Yes!

; Now we will take the first row and then turn it:

(first big-data)
;=> [1 2 3 4 5 6 7 8]

(reverse (transpose (rest big-data)))
;=> ([16 24 32 40 48 56 64] [15 23 31 39 47 55 63] [14 22 30 38 46 54 62] [13 21 29 37 45 53 61] [12 20 28 36 44 52 60] [11 19 27 35 43 51 59] [10 18 26 34 42 50 58] [9 17 25 33 41 49 57])

(defn r-transpose [data]
  (reverse (transpose data)))


; Let's combine these operations into a func called peel-it:

(defn peel-it [data]
  (loop [x data spiral []]  
    (if (next x)
        (recur (reverse (transpose (rest x))) (into spiral (first x)))
        spiral)))
(peel-it big-data)
[1 2 3 4 5 6 7 8 16 24 32 40 48 56 64 63 62 61 60 59 58 57 49 41 33 25 17 9 10 11 12 13 14 15 23 31 39 47 55 54 53 52 51 50 42 34 26 18 19 20 21 22 30 38 46 45 44 43 35 27 28 29 37]

; Is this right?

(def big-data [
              [1  2  3  4  5  6  7  8]
              [9  10 11 12 13 14 15 16]
              [17 18 19 20 21 22 23 24]
              [25 26 27 28 29 30 31 32]
              [33 34 35 36 37 38 39 40]
              [41 42 43 44 45 46 47 48]
              [49 50 51 52 53 54 55 56]
              [57 58 59 60 61 62 63 64]])

1 2 3 4 5 6 7 8 16 24 32 40 48 56 64 63 62 61 60 59 58 57 49 41 33 25 17 9 ....

; Looks right!
