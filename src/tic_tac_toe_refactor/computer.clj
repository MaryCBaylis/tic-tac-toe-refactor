(ns tic-tac-toe-refactor.computer
  (:gen-class)
  (:require [tic-tac-toe-refactor.data :as data]
    [tic-tac-toe-refactor.board :as board]
    [tic-tac-toe-refactor.player :as player]
    [clojure.set :as set]))

(defn possible-immediate-wins
  [board mark]
  (def wins (player/possible-wins board (board/unclaimed-squares board) mark))
  (filter #(= 2 
            (count 
              (set/intersection 
                (into #{} (player/claimed-squares board mark)) 
                (into #{} %)))) wins))