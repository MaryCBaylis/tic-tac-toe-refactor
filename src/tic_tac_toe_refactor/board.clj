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