(ns slothrop.day-one.calorie-count
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(with-open [rdr (-> "public/puzzle_inputs/day_one.txt" io/resource io/reader)]
  (->> (line-seq rdr)
       (partition-by string/blank?)
       (filter #(not= "" (first %))) 
       (map (fn [vals] (apply + (map #(Integer/parseInt %) vals))))
       #_(apply max) ; pt 1 ends here
       (sort >)
       (take 3) 
       (apply +))) ; pt 2
