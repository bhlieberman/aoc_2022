(ns slothrop.day-one.rock-paper-scissors
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(def pt-one-points [4 8 3 1 5 9 7 2 6])

(def pt-two-points [3 4 8 1 5 9 2 6 7])

(def outcomes [["A" "X"] ["A" "Y"] ["A" "Z"]
               ["B" "X"] ["B" "Y"] ["B" "Z"]
               ["C" "X"] ["C" "Y"] ["C" "Z"]])

(def pt-one-scores (zipmap outcomes pt-one-points))
(def pt-two-scores (zipmap outcomes pt-two-points))

(with-open [rdr (-> "public/puzzle_inputs/day_two.txt" io/resource io/reader)]
  (->> (line-seq rdr)
       (transduce (map (comp #(get pt-one-scores %)
                             #(string/split % #"\s"))) + 0)))
