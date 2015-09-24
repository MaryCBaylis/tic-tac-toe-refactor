(ns tic-tac-toe-refactor.player
  (:gen-class)
  (:require [tic-tac-toe-refactor.data :as data]
            [clojure.set :as set]))

(defn check-for-win
  [squares]
  (set/subset? #{true} (into #{} (for [win data/possible-wins]
    (set/subset? (into #{} win) (into #{} squares))))))

(defn claimed-squares
  [board mark]
  (map inc (map first 
    (filter #(= (second %) mark) 
      (map-indexed vector board)))))

(defn win?
  [board mark]
  (check-for-win (claimed-squares board mark)))