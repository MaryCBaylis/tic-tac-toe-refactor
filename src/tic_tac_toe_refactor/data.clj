(ns tic-tac-toe-refactor.data
  (:gen-class))

(def affirmative-list ["y" "yes" "yep" "yeah"])
(def negative-list ["n" "no" "nope" "nah"])
(def quit-list ["q" "quit" "exit" "x"])
(def initial-board [1 2 3 4 5 6 7 8 9])
(def welcome-message "Welcome to Tic-Tac-Toe!\nShall we play a game?  (Y)es/(N)o?")
(def farewell "See you next time!")
(def start-message "Let's play!")
(def instruction "Enter the number of the square you would like to claim.\nYou can enter (Q)uit or E(x)it at anytime.")
(def woops "Er... That wasn't quite right. Try again!")
(def comp-turn "Now it's my turn to take a stab!")
(def player-turn "Gimme your best shot!")
(def possible-wins [[1 2 3] [4 5 6] [7 8 9] [1 4 7] [2 5 8] [3 6 9] [1 5 9] [3 5 7]])
(def player-mark "X")

