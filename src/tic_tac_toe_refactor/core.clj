(ns tic-tac-toe-refactor.core
  (:gen-class)
  (:require [tic-tac-toe-refactor.data :as data]))

(defn get-input
  []
  (def input (str (read-line)))
  (if (some #{input} data/quit-list)
    (System/exit 0)
    input))

(defn play-again?
  [input]
  false
)

(defn -main
  "I play tic-tac-toe!"
  [& args]
  (println "Welcome to Tic-Tac-Toe!")
  (println "Shall we play a game?  (Y)es/(N)o?")

  (if (play-again? (get-input))
      (println "Play a game!")
      (println "Why'd you even?....."))
  (println "See you next time!")
)
