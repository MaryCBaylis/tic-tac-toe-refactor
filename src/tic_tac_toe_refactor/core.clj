(ns tic-tac-toe-refactor.core
  (:gen-class)
  (:require [tic-tac-toe-refactor.data :as data])
  (:require [clojure.string :as string]))

(defn get-input
  []
  (def input (str (read-line)))
  (if (some #{input} data/quit-list)
    (System/exit 0)
    input))

(defn play-again?
  [input]
  (some #{input} data/affirmative-list))

(defn create-board
  []
  data/initial-board)

(defn displayable-board
  [board]
  (str (string/join " " (subvec board 0 3)) "\n" (string/join " " (subvec board 3 6)) "\n" (string/join " " (subvec board 6)))
    )

(defn -main
  "I play tic-tac-toe!"
  [& args]
  (println "Welcome to Tic-Tac-Toe!")
  (println "Shall we play a game?  (Y)es/(N)o?")

  (if (play-again? (get-input))
      (println (displayable-board (create-board)))
      (println "Why'd you even?....."))
  (println "See you next time!"))
