(ns slothrop.day-three.rucksacks
  (:require [clojure.java.io :as io]
            [clojure.string :as string]
            [clojure.set :refer [intersection]]))

(def lower (zipmap (map char (range 97 123)) (range 1 27)))
(def upper (zipmap (map char (range 65 91)) (range 27 53)))

(with-open [rdr (-> "public/puzzle_inputs/day_three.txt"
                    io/resource
                    io/reader)]
  (->> rdr
       line-seq
       (partition 3)
       #_(map #(split-at (/ (count %) 2) %))
       (map (fn [[s1 s2 s3]] (intersection (set s1) (set s2) (set s3))))
       (map #(if (Character/isUpperCase (first %)) 
               (get upper (first %)) 
               (get lower (first %))))
       (apply +)))
