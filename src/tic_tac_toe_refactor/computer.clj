(ns tic-tac-toe-refactor.computer
  (:gen-class)
  (:require [tic-tac-toe-refactor.data :as data]
    [tic-tac-toe-refactor.board :as board]
    [tic-tac-toe-refactor.player :as player]
    [clojure.set :as set]))

(defn possible-wins
  [board unclaimed-squares mark]
  (def opponent-squares (set/difference 
                          (into #{} data/initial-board)
                          unclaimed-squares 
                          (board/claimed-squares-by-mark board mark)))  
  (filter #(empty? 
            (set/intersection 
              opponent-squares
              (into #{} %))) data/possible-wins))

(defn possible-immediate-wins
  [board mark]
  (def wins (possible-wins board (board/unclaimed-squares board) mark))
  (filter #(= 2 
            (count 
              (set/intersection 
                (into #{} (board/claimed-squares-by-mark board mark)) 
                (into #{} %)))) wins))