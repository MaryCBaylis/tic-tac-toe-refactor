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
    (board/refresh board data/player-mark choice)
    (do (println data/woops)(recur board))))

(defn computer-phase
  [board]
  board)
  ;;THIS is where it gets fun.  Let's make a ruthless comp!
  ;;Steps to never lose, in order of priority:
  ;;1: If you're one move away from victory, take it
  ;;2: If your opponent is one move away from victory, block!
  ;;3: Take the move that will allow you the most lanes to victory)

;;If I have time, maybe make the compy learn from losses, assuming the player plays again and again

(defn game-loop
  [board player message]
  ;;Check for victory or stalemate, first thing
  (if (board/game-over? board data/player-mark data/computer-mark)
    (println ("It's all over!")))
  (if (board/winner? board "X")
    (println data/player-win))
  (if (board/winner? board "Y")
    (println data/computer-win))

  (println message)
  (if (= :player player)
    (recur (player-phase board) :computer data/comp-turn)
    (recur (computer-phase board) :player data/player-turn)))

(defn -main
  [& args]
  (println data/welcome-message)

  (if (play? (get-input))
      (game-loop (board/create) :player data/start-message))
  (println data/farewell))
