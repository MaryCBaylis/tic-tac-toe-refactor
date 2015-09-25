(ns tic-tac-toe-refactor.computer
  (:gen-class)
  (:require [tic-tac-toe-refactor.data :as data]
    [tic-tac-toe-refactor.board :as board]
    [clojure.set :as set]))

(defn possible-wins
  [board mark]
  (def opponent-squares (set/difference 
                          (into #{} data/initial-board)
                          (board/unclaimed-squares board)
                          (board/claimed-squares-by-mark board mark)))  
  (filter #(empty? 
            (set/intersection 
              opponent-squares
              (into #{} %))) data/possible-wins))

(defn possible-immediate-wins
  [board mark]
  (def wins (possible-wins board mark))
  (filter #(= 2 
            (count 
              (set/intersection 
                (into #{} (board/claimed-squares-by-mark board mark)) 
                (into #{} %)))) wins))

(defn square-to-win
  [board mark]
  (def poss-wins (possible-immediate-wins board mark))
  (def win (nth poss-wins (rand-int (count poss-wins))))
  (set/difference (into #{} (board/claimed-squares-by-mark board mark)) (into #{} win))
  (nth (into [] 
        (set/difference 
          (into #{} win) 
          (into #{} (board/claimed-squares-by-mark board mark)))) 0))

(defn most-potent-squares
  [board mark]
  (def squares-and-count (remove 
                            #(set/subset? 
                              (set [(first %)]) 
                              (into #{} (board/claimed-squares-by-mark board mark))) 
                            (frequencies (flatten (possible-wins board mark)))))
  (def highest-count (apply max (vals squares-and-count)))
  (keys (filter #(= highest-count (val %)) squares-and-count)))

(defn potent-square-choice
  [board mark]
  (def potent-squares (most-potent-squares board mark))
  (nth potent-squares (rand-int (count potent-squares))))
