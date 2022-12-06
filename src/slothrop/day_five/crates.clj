(ns slothrop.day-five.crates
  (:require [clojure.java.io :as io]
            [clojure.string :as string]
            [clojure.set :refer [difference]]))

(def input (-> "public/puzzle_inputs/day_five.txt"
               io/resource
               slurp
               string/split-lines))

(def crates (take 8 input))

(def instructions (map (comp #(map parse-long %)
                             #(re-seq #"\d+" %))
                       (drop 10 input)))

(def full-stacks (atom (->> crates
                            reverse
                            (apply map vector)
                            (filter (fn [[a b c]] (not= a b c)))
                            (map #(remove #{\space} %))
                            (map vec)
                            (into []))))

(defn move-crates [coll params]
  (letfn [(safe-pop [v] (if (empty? v) v (pop v)))]
    (loop [coll coll
           [qty from to] params]
      (if (< 0 qty)
        (let [from-val (get-in coll [(dec from)])
              to-val (update-in coll [(dec to)] conj (peek from-val))]
          (recur (update-in to-val [(dec from)] safe-pop) [(dec qty) from to]))
        coll))))

(defn move-crates-pt-2 [coll [qty from to]]
  (let [from-val (get-in coll [(dec from)])
        slice (subvec from-val (- (count from-val) qty))
        to-val (-> coll (assoc-in [(dec from)] 
                                  (into [] (difference (set from-val) (set slice)))) 
                   (update-in [(dec to)] concat slice))]
    to-val))

(comment
  (time (doseq [ins instructions]
          (swap! full-stacks move-crates-pt-2 ins)))
  @full-stacks
  (apply str (map (comp last #(filter some? %)) @full-stacks))
  
  (move-crates-pt-2 [[\D \E \F] [\G \H \I \J]] [2 1 2])
  
  
  (assoc-in [[\D \E \F]] [0] [\A \B \C])) 