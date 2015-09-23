(ns tic-tac-toe-refactor.core
  (:gen-class)
  (:require [tic-tac-toe-refactor.data :as data]
    [clojure.string :as string]
    [tic-tac-toe-refactor.board :as board]))

(defn get-input
  []
  (def input (str (read-line)))
  (if (some #{string/lower-case input} data/quit-list)
      (System/exit 0)
      input))

(defn play?
  [input]
  (some #{input} data/affirmative-list))

(defn game-loop
  [board player message]
  (println message)
  (println (board/prettify board)))

(defn -main
  "I play tic-tac-toe!"
  [& args]
  (println data/welcome-message)

  (if (play? (get-input))
      (game-loop (board/create) :player "Let's play!"))
  (println data/farewell))
