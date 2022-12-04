(ns slothrop.day-three.rucksacks
  (:require [clojure.java.io :as io]
            [clojure.set :refer [intersection]]))

(def lower (zipmap (map char (range 97 123)) (range 1 27)))
(def upper (zipmap (map char (range 65 91)) (range 27 53)))

(with-open [rdr (-> "public/puzzle_inputs/day_three.txt" io/resource io/reader)]
  (letfn [(setify [s] (apply intersection (map set s)))
          (char->number [c] (or (get lower c) (get upper c)))]
    (->> rdr
         line-seq
         (partition 3) ; pt 2
         #_(map #(split-at (/ (count %) 2) %)) ; pt 1
         (map (comp char->number first setify))
         (apply +))))
