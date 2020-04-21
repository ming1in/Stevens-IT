#lang eopl

;;-------------------------------------------------------------------------------
;; Name:Ming Lin
;; Pledge: I pledge my honor that i have abided by the Stevens Honor System
;;-------------------------------------------------------------------------------

;; This lab serves as an introduction to using recursion in Scheme.
;; You should implement every function in this program recursively.
;;
;; NOTE: the empty list is a valid input for each function!
;;
;; Also, just because your code works for provided test cases
;;   doesn't mean it always works.
;; Always come up with your own test cases too!



;; Implement "nth", which returns the element at index n of the provided list.
;; The first element of the list is at index 0, the second at index 1, etc.
;; You may assume that the "n" provided is non-negative and less than the list's length.

;; Examples:
;; (nth 1 '(Sandeep Owen Jared Sarvani)) -> Owen
;; (nth 5 '("zero" "one" "two" "three" "four" "five")) -> "five"
;; (nth 0 '(a b c)) -> 'a

;; Type signature: (nth integer list) -> element from list
(define (nth n lst)
  (if (equal? n 0)
      (car lst)
  (nth (- n 1)(cdr lst)))
  )




;; Implement "sum", which returns the summation of all the elements in the given list.
;; You may assume that the list will only contain numbers.
;; Note: mathematical convention is that the sum of nothing is 0.

;; Examples:
;; (sum '(1 3 5 1)) -> 10
;; (sum '(75050 344 0 -70 125 -9999999 6 0)) -> 10075454

;; Type signature: (sum number-list) -> number
(define (sum lst)
  (if (equal? lst '()) 0
      (+ (car lst) (sum (cdr lst))))
  )




;; Implement "product", which returns the result of multiplying all elements in the list.
;; You may assume that the list will only contain numbers.
;; Note: mathematical convention is that the product of nothing is 1.

;; Examples:
;; (product (list 1 3 5 4)) -> 60
;; (product '(100 -50 6789 4183457)) -> -142007447865000

;; Type signature: (product number-list) -> number
(define (product lst)
  (if (equal? lst '()) 1
      (* (car lst) (product (cdr lst))))
  )




;; Implement "map", which accepts a function and a list,
;;   and returns a list with the function applied to every element.
;; Note that EOPL has a built-in function called "map",
;;   but the function defined here automatically overrides the EOPL one.
;; Observe in the examples that when passing through a function as an argument to "map",
;;   you don't have to enclose it in parentheses.

;; Examples:
;; (define (double x) (* x 2))
;; (map double '(1 2 3 4)) -> '(2 4 6 8)
;; (map zero? '(0 0 1 2)) -> '(#t #t #f #f)

;; Type signature: (map function list) -> list
(define (map f lst)
  (if (equal? lst '()) '()
  (append (list(f (car lst))) (map f (cdr lst))))
  )




;; Implement "filter", which accepts a predicate (a function that returns a boolean) and a list,
;;   and returns a sublist of all elements in the list which satisfy the predicate.
;; In other words, "filter" keeps elements of the list for which the predicate returns #t,
;;   and throws out elements for which the predicate returns #f.

;; Examples
;; (filter zero? '(1 0 2 34 56 1 0)) -> '(0 0)
;; (filter even? '(0 1 2 3 4 5 6 7 8 9)) -> '(0 2 4 6 8)
;; (filter number? '(shave and 1 haircut 2 bits)) -> '(1 2)

;; Type signature: (filter predicate list) -> list
(define (filter pred lst)
  (if (equal? lst '()) '()
     (if (equal? (pred (car lst)) #t)
         (append (list(car lst)) (filter pred (cdr lst)))
         (filter pred (cdr lst))))
  )



;; Created January 2018 by Samuel Kraus and Edward Minnix
;; Updated January 2020 by Jared Pincus