(ns tic-tac-toe-refactor.board
  (:gen-class)
  (:require [tic-tac-toe-refactor.data :as data]
    [clojure.string :as string]
    [clojure.set :as set]))

(defn create
  []
  data/initial-board)

(defn prettify
  [board]
  (str (string/join " " (subvec board 0 3)) "\n" (string/join " " (subvec board 3 6)) "\n" (string/join " " (subvec board 6))))

(defn legal-square?
  [board square]
  (and (re-matches #"[1-9]" square) (some #{(Integer. square)} board)))

(defn refresh
  [board mark square]
  (assoc board (- (Integer. square) 1) mark))

(defn possible-moves
  [board]
  (filter #(number? %) board))

(defn check-for-win
  [squares]
  (set/subset? #{true} (into #{} (for [win data/possible-wins]
    (set/subset? (into #{} win) (into #{} squares))))))

(defn winner?
  [board mark] 
  (def squares 
    (map inc (map first 
      (filter #(= (second %) mark) 
        (map-indexed vector board)))))
  (check-for-win squares))

(defn full?
  [board]
  (empty? (possible-moves board)))

(defn game-over?
  [board mark-1 mark-2]
  (or (full? board) (winner? board mark-1) (winner? board mark-2)))