(ns tic-tac-toe-refactor.core-test
  (:require [clojure.test :refer :all]
            [tic-tac-toe-refactor.core :refer :all]
            [tic-tac-toe-refactor.board :refer :all]))
;;example
; (deftest a-test
;   (testing "FIXME, I fail."
;     (is (= 0 1))))



(deftest test-displayable-board
  (testing "Correct string should be returned"
    (is (= "1 2 3\n4 5 6\n7 8 9" (displayable-board (create-board))))))