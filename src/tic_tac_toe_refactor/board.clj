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

(defn unclaimed-squares
  [board]
  (filter #(number? %) board))

(defn full?
  [board]
  (empty? (unclaimed-squares board)))

(defn claimed-squares-by-mark
  [board mark]
  (map inc (map first 
    (filter #(= (second %) mark) 
      (map-indexed vector board)))))

(defn check-for-win
  [squares]
  (set/subset? #{true} (into #{} (for [win data/possible-wins]
    (set/subset? (into #{} win) (into #{} squares))))))

(defn win-by-mark?
  [board mark]
  (check-for-win (claimed-squares-by-mark board mark)))

(defn game-over?
  [board mark-1 mark-2]
  (or (full? board) (win-by-mark? board mark-1) (win-by-mark? board mark-2)))
