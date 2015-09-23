(ns tic-tac-toe-refactor.board
  (:gen-class)
  (:require [tic-tac-toe-refactor.data :as data]
    [clojure.string :as string]))

(defn create
  []
  data/initial-board)

(defn prettify
  [board]
  (str (string/join " " (subvec board 0 3)) "\n" (string/join " " (subvec board 3 6)) "\n" (string/join " " (subvec board 6))))

(defn legal-square?
  [board square]
  (and (re-matches #"[1-9]" square) (some #{(Integer. square)} board)))

(defn get-mark
  [player]
  (if (= :player player)
    "X"
    "O"))

(defn refresh
  [board player square]
  (assoc board (- (Integer. square) 1) (get-mark player)))

(defn possible-moves
  [board]
  )
