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

(defn player-phase
  [board]
  (println (board/prettify board))
  (println data/instruction)
  (def choice (get-input))
  (if (board/legal-square? board choice)
    (board/refresh board :player choice)
    (do (println data/woops)(recur board))))

(defn game-loop
  [board player message]
  (println message)
  (if (= :player player)
    (recur (player-phase board) :computer data/comp-turn)
    (println "compy turn!"))
  )

(defn -main
  "I play tic-tac-toe!"
  [& args]
  (println data/welcome-message)

  (if (play? (get-input))
      (game-loop (board/create) :player data/start-message))
  (println data/farewell))
