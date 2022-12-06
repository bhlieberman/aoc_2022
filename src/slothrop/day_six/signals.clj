(ns slothrop.day-six.signals
  (:require [clojure.java.io :as io]))

(def input (-> "public/puzzle_inputs/day_six.txt" io/resource slurp))

(defn process-data [n s]
  (let [packets (map (partial apply str) (partition n 1 s))
        unique-packets (map (comp (partial apply str) distinct) packets)
        nums (map #(if (= %1 %2) %3 nil) packets unique-packets (range))]
    (->> nums (filter some?) first (+ n))))

(process-data 4 input)
(process-data 14 input)
