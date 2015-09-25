(ns tic-tac-toe-refactor.core-test
  (:require [clojure.test :refer :all]
            [tic-tac-toe-refactor.core :refer :all]
            [tic-tac-toe-refactor.board :refer :all]
            [tic-tac-toe-refactor.player :refer :all]
            [tic-tac-toe-refactor.computer :refer :all]))
;;example
; (deftest a-test
;   (testing "FIXME, I fail."
;     (is (= 0 1))))



(deftest test-displayable-board
  (testing "Correct string should be returned"
    (is (= "1 2 3\n4 5 6\n7 8 9" (prettify (create))))))

(deftest test-check-for-win
  (testing "Returns true if player has a win"
    (is (= true (check-for-win '(1 2 3 7 8)))))
  (testing "Returns false if player has no wins"
    (is (= false (check-for-win '(1 2 5 7))))))

(deftest test-win?
  (def x-board ["X" "O" 3 "X" "O" 6 "X" 8 9])
  (def o-board ["O" "X" 3 "O" "X" 6 "O" 8 9])
  (def v-board [1 "X" 3 4 "X" 6 7 "X" 9])
  (def h-board [1 2 3 "O" "O" "O" 7 8 9])
  (def d-board ["X" 2 3 4 "X" 6 7 8 "X"])
  (def no-board [1 2 3 4 5 6 7 8 9])
  (testing "X should win"
    (is (= true (win? x-board "X"))))
  (testing "O should lose"
    (is (= false (win? x-board "O"))))
  (testing "O should win"
    (is (= true (win? o-board "O"))))
  (testing "X should lose"
    (is (= false (win? o-board "X"))))
  (testing "Vertical wins should return true"
    (is (= true (win? v-board "X"))))
  (testing "Horizontal wins should return true"
    (is (= true (win? h-board "O"))))
  (testing "Diagonal wins should return true"
    (is (= true (win? d-board "X"))))
  (testing "No wins should return false"
    (is (= false (win? no-board "O")))))

(deftest test-full?
  (def full-board ["O" "X" "O" "X" "O" "X" "O" "X" "O"])
  (def no-moves-board [1 2 3 4 5 6 7 8 9] )
  (testing "Returns true if there are no more moves"
    (is (= true (full? full-board))))
  (testing "Returns false if there are available squares"
    (is (= false (full? no-moves-board)))))

(deftest test-game-over?
  (def full-board ["O" "X" "O" "X" "O" "X" "O" "X" "O"])
  (def x-board ["X" "O" 3 "X" "O" 6 "X" 8 9])
  (def o-board ["O" "X" 3 "O" "X" 6 "O" 8 9])
  (def board [1 2 3 4 5 6 7 8 9] )
  (testing "Game is over when board is full"
    (is (= true (game-over? full-board "X" "O"))))
  (testing "Game is over when X has won"
    (is (= true (game-over? x-board "X" "O"))))
  (testing "Game is over when Y has won"
    (is (= true (game-over? o-board "X" "O"))))
  (testing "Game is not over when no one has won and squares are still available"
    (is (= false (game-over? board "X" "O")))))

(deftest test-unclaimed-squares
  (def full-board ["O" "X" "O" "X" "O" "X" "O" "X" "O"])
  (def partial-board [1 2 3 4 5 6 7 "X" "O"])
  (testing "Returns empty list when no moves are left"
    (is (= '() (unclaimed-squares full-board))))
  (testing "Returns correct list of possible moves"
    (is (= '(1 2 3 4 5 6 7) (unclaimed-squares partial-board)))))

(deftest test-possible-wins
  (def empty-board [1 2 3 4 5 6 7 8 9])
  (def played-board ["O" "O" 3 4 "O" "X" 7 8 9])
  (testing "Returns correct possible wins for X on board with plays already made"
    (is (= '([7 8 9] [3 6 9]) (possible-wins played-board (unclaimed-squares played-board) "X"))))
  (testing "Returns correct possible wins for O on board with plays already made"
    (is (= '([1 2 3] [7 8 9] [1 4 7] [2 5 8] [1 5 9] [3 5 7]) (possible-wins played-board (unclaimed-squares played-board) "O"))))
  (testing "Returns all possible wins for empty board"
    (is (= '([1 2 3] [4 5 6] [7 8 9] [1 4 7] [2 5 8] [3 6 9] [1 5 9] [3 5 7]) (possible-wins empty-board (unclaimed-squares empty-board) "X")))))

(deftest test-possible-immediate-wins
  (def multi-win-board ["O" "O" 3 4 "O" "X" 7 8 9])
  (def no-wins-board ["X" 2 3 "O" 5 "O" 7 8 9])
  (testing "Returns correct immediate wins for board with multiple immediate wins available"
    (is (= '([1 2 3] [2 5 8] [1 5 9]) (possible-immediate-wins multi-win-board "O"))))
  (testing "Returns empty set for board with no immediate wins available"
    (is (= '() (possible-immediate-wins no-wins-board "X")))))