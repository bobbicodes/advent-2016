(ns advent.core
  (:require
   [advent-of-clojure-2016.utils :as u]
   [clojure.set :refer [difference]]
   [clojure.java.io :as io]
   [clojure.string :as string]))

; Just start with creating a parser for this:

(def bots "bot 147 gives low to bot 67 and high to bot 71")

(def bot-parser
  (insta/parser
    "handoff = bot+
     bot = 'bot ' id <word*>
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

(def data (string/split-lines (string/trim (slurp (io/resource "day10")))))

(defn addr-names [l]
  (->> (re-seq #"(bot|output)\s(\d+)" l)
       (map (comp keyword #(apply str %) rest))))

(defn parse-line [l]
  (let [addrs (addr-names l)]
    (condp = (first l)
      \v  (vector :to (first addrs) (u/to-int (first (re-seq #"\d+" l))))
      \b  (cons :from addrs))))

;; all commands originate at a unique bot
#_(let [froms (map second (filter #(= (first %) :from) input))]
    (= (count froms) (count (set froms))))

(defn give-to-addr [registers addr v]
  (update registers addr (fnil conj #{}) v))

(defn move-value [registers from to v]
  {:pre [((get registers from) v)]} ;; must have val to give
  (-> registers
      (update from disj v)
      (give-to-addr to v)))

(defn make-init [commands]
  (let [{:keys [from to]} (group-by first commands)]
    {:commands from
     :registers (reduce #(apply give-to-addr %1 (rest %2)) {} to)}))

(defn high-low-command [registers [_ from low-to high-to :as com]]
  (let [[lv hv] ((juxt first last) (sort (get registers from #{})))]
    (assert (and lv hv (not= lv hv)))
    (-> registers
        (move-value from low-to lv)
        (move-value from high-to hv))))

(defn active-registers [x] (->> x (filter #(>= (count (val %)) 2)) keys set))

(defn transition-state [{:keys [registers commands] :as state}]
  (let [active-regs                     (active-registers registers)
        [active-commands rest-commands] (u/pluck #(-> % second active-regs) commands)]
    (when-not (empty? active-commands)
      (-> state
          (update :registers #(reduce high-low-command % active-commands))
          (assoc :commands rest-commands)))))

;; part 1
#_(->> (iterate transition-state (make-init (map parse-line data)))
       (take-while #(not (nil? %)))
       (map :registers)
       (keep #(some (fn [[k v]] (when (empty? (difference #{61 17} v)) k)) %))
       first)
;;=> :bot161

;; part 2
#_(->> (iterate transition-state (make-init (map parse-line data)))
       (take-while #(not (nil? %)))
       last
       :registers
       (#(select-keys % [:output0 :output1 :output2]))
       vals
       (map first)
       (apply *))
;; => 133163
