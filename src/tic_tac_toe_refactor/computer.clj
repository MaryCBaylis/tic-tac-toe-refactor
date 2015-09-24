(ns tic-tac-toe-refactor.computer
  (:gen-class)
  (:require [tic-tac-toe-refactor.data :as data]
    [tic-tac-toe-refactor.board :as board]))

(defn possible-immediate-win
    ;;Check to see if i have two out of three of each possible win
  [board])