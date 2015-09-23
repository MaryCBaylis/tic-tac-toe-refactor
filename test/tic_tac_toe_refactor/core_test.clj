(ns tic-tac-toe-refactor.core-test
  (:require [clojure.test :refer :all]
            [tic-tac-toe-refactor.core :refer :all]
            [tic-tac-toe-refactor.board :refer :all :as board]))
;;example
; (deftest a-test
;   (testing "FIXME, I fail."
;     (is (= 0 1))))



(deftest test-displayable-board
  (testing "Correct string should be returned"
    (is (= "1 2 3\n4 5 6\n7 8 9" (board/prettify (board/create))))))

(deftest test-check-for-win
  (testing "Returns true if player has a win"
    (is (= true (check-for-win '(1 2 3)))))
  (testing "Returns false if player has no wins"
    (is (= false (check-for-win '(1 4 5))))))