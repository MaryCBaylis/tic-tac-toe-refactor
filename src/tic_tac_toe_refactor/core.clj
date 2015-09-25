(ns tic-tac-toe-refactor.core
  (:gen-class)
  (:require [tic-tac-toe-refactor.data :as data]
    [clojure.string :as string]
    [tic-tac-toe-refactor.board :as board]
    [tic-tac-toe-refactor.computer :as comp]))

(defn get-input
  []
  (def input (str (read-line)))
  (if (some #{string/lower-case input} data/quit-list)
      (do (println data/farewell) (System/exit 0))
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
      (board/refresh board data/player-mark choice)
      (do (println data/woops)(recur board))))

(defn computer-phase
  [board]
  (println (board/prettify board))
  (def choice (comp/pick-square board data/computer-mark data/player-mark))
  (board/refresh board data/computer-mark choice))

(defn end-game-message
  [board]
  (if (board/win-by-mark? board data/player-mark)
      data/player-win
      (if (board/win-by-mark? board data/computer-mark)
          data/computer-win
          data/tie)))

(defn game-loop
  [board player message]
  (println)
  (if (board/game-over? board data/player-mark data/computer-mark)
      (do (println (end-game-message board))
          (println (board/prettify board))
          (println data/ask-again)
          (if (play? (get-input))
              (recur (board/create) :player data/start-message)))
      (do (println message)
          (if (= :player player)
              (recur (player-phase board) :computer data/comp-turn)
              (recur (computer-phase board) :player data/player-turn)))))

(defn -main
  [& args]
  (println data/welcome-message)

  (if (play? (get-input))
      (game-loop (board/create) :player data/start-message))
  (println data/farewell))
