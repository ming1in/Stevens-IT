#lang eopl
;;-------------------------------------------------------------------------------
;; Name:
;; Pledge:
;;-------------------------------------------------------------------------------

;; In this lab, you will write various functions which operate over relations.
;; We'll represent a relation as a set of pairs of numbers.
;; (NOTE: references to "pairs" in this lab refer to ordered lists of two numbers,
;;        not the Scheme data structure of pairs.)
;; A set containing the pair (x y) means an edge points from x to y in the relation.
;;
;; Every relation in this lab will have a subset of the positive integers as its domain,
;;   which will be written as [1, n]. Such a domain is the integers 1, 2, ..., n.
;;
;; Again, like in last lab, we'll use lists to represent sets,
;;   but we won't let there be duplicate elements
;;   and we won't care about the element order.
;; If a function you write outputs a relation
;;   and your output doesn't have the same order as the examples, that's okay.
;;
;; This lab will build off of the previous lab.
;; At the bottom of this file, you'll find many helper functions,
;;   all from the previous lab, to help you complete this one.




;; Implement "id" to accept an integer (which you may assume is positive)
;;   and return the "identity relation" from 1 to n.
;; This means it should create a set
;;   containing the pairs (1 1), (2 2), ..., (n n).
;;
;; Examples:
;; (id 1) -> '((1 1))
;; (id 5) -> '((1 1) (2 2) (3 3) (4 4) (5 5))
;;
;; Type Signature: (id int) -> relation
(define (id n)
   (if (equal? n 0) '() 
  (append (id (- n 1)) (list (append (list n) (list n)))))
)




;; Implement "reflexive?" to accept a relation R and a positive integer n,
;;   and return whether R is reflexive over the domain [1, n].
;; In other words, R is reflexive iff it contains the the id relation up to n.
;; This can be implemented easily using id and one of the provided helper functions.
;;
;; Examples:
;; (reflexive? '((1 1) (2 2) (3 3)) 3) -> #t
;; (reflexive? '((1 1) (2 2) (3 3) (3 2) (2 3)) 3) -> #t
;; (reflexive? '((1 1) (2 2) (3 3)) 4) -> #f
;;
;; Type Signature: (reflexive? relation int) -> boolean
(define (reflexive? R n)
  (subset? (id n) R)
)




;; Implement "reflexive-closure" to accept a relation R and a positive integer n,
;;   and return the reflexive closure of R with respect to the domain [1, n].
;; The reflexive closure of R is the smallest relation which
;;   contains R and is reflextive over [1, n].
;;
;; Examples:
;; (reflexive-closure '() 3) -> '((1 1) (2 2) (3 3))
;; (reflexive-closure '((3 2) (2 3)) 3) -> '((1 1) (2 2) (3 3) (3 2) (2 3))
;; (reflexive-closure '((1 1) (2 2) (3 3)) 4) -> '((1 1) (2 2) (3 3) (4 4))
;;
;; Type Signature: (reflexive-closure relation int) -> relation
(define (reflexive-closure R n)
  (union (id n) R)
  )




;; Implement "inverse", which accepts a relation R
;;   and returns the inverse relation of R.
;; In other words, inverse changes every edge (x y) in R into (y x).
;; Scheme's "reverse" and "map" functions may be useful here.
;;
;; Examples:
;; (inverse '((1 2) (3 2) (4 5))) -> '((2 1) (2 3) (5 4))
;; (inverse '((1 1) (1 2) (1 3))) -> '((1 1) (2 1) (3 1))
;;
;; Type Signature: (inverse relation) -> relation
(define (inverse R)
  (if (equal? R '()) '()
    (append (list (reverse (car R))) (inverse (cdr R))))
  )




;; Implement "symmetric?", which accepts a relation R
;;   and returns whether R is symmetric.
;; R is symmetric iff for every pair (x y) in R,
;;   R also contains the pair (y x).
;; This can be implemented easily using inverse
;;   and one of the provided helper functions.
;;
;; Examples:
;; (symmetric? '((1 1) (2 1) (1 2))) -> #t
;; (symmetric? '((1 1) (2 4) (3 7) (3 5) (5 3))) -> #f
;; (symmetric? '((2 4) (4 3) (3 4) (4 2))) -> #t
;;
;; Type Signature: (reflexive? relation int) -> boolean
(define (symmetric? R)
  (subset? (inverse R) R)
  )




;; Implement "symmetric-closure" to return the symmetric closure
;;   of a given relation R.
;; The symmetric closure of R is the smallest relation
;;   which is symmetric and contains R.
;; In other words, if R contains an edge (x y) but not the edge (y x),
;;   the symmetric closure ought to contain both (x y) and (y x).
;; This can be implemented easily using inverse
;;   and one of the provided helper functions.
;;
;; Examples:
;; (symmetric-closure '()) -> '()
;; (symmetric-closure '((3 2) (2 3))) -> '((3 2) (2 3))
;; (symmetric-closure '((1 2) (2 7) (3 4))) -> '((1 2) (2 7) (3 4) (2 1) (7 2) (4 3))
;;
;; Type Signature: (reflexive-closure relation) -> relation
(define (symmetric-closure R)
  (union (inverse R) R)
  )




;; Implement "relates-to", which accepts a relation R and a vertex v
;;   and returns the set of vertices to which v relates through R.
;; v relates to a vertex y through R iff the pair (v y) is in R.
;;
;; Examples:
;; (relates-to 3 '((3 3) (3 4) (3 5))) -> '(3 4 5)
;; (relates-to 1 '((1 2) (2 3) (3 4) (4 5))) -> '(2)
;; (relates-to 2 '((1 3) (3 5) (4 6) (8 7))) -> '()
;;
;; Type Signature: (relates-to vertex relation) -> set
(define (relates-to v R)
  (if (null? R) '()
   (if (equal? (car (car R)) v) (append (append (cdr (car R))) (relates-to v (cdr R)))
   (relates-to v (cdr R)))))




;;__________________________________________________________________________

;; Below are helper functions you may utilize for the functions you write!


;; Returns e ∈ L.
;; Type signature: (element? item list) -> boolean
(define (element? e L)
  (member e L))

;; Returns L as a set (removes duplicates).
;; Type signature: (make-set list) -> set
(define (make-set L)
  (cond [(null? L) '()]
        [(member (car L) (cdr L)) (make-set (cdr L))]
        [else (cons (car L) (make-set (cdr L)))]))

;; Returns the set of LA unioned with the set of LB.
;; Type signature: (union list list) -> set
(define (union LA LB)
  (make-set (append LA LB)))

;; Returns the set of LA intersected with the set of LB.
;; Type signature: (intersection list list) -> set
(define (intersection LA LB)
  (make-set (intersection-helper LA LB)))
(define (intersection-helper LA LB)
  (cond [(null? LA) '()]
        [(element? (car LA) LB)
         (cons (car LA) (intersection-helper (cdr LA) LB))]
        [else (intersection-helper (cdr LA) LB)]))

;; Returns SA ⊆ SB.
;; Type signature: (subset? set set) -> boolean
(define (subset? SA SB)
  (cond [(null? SA) #t]
        [(element? (car SA) SB)
         (subset? (cdr SA) SB)]
        [else #f]))

;; Returns whether SA and SB contain the same elements.
;; Type signature: (set-equal? set set) -> boolean
(define (set-equal? SA SB)
  (and (subset? SA SB)
       (subset? SB SA)))

;; Returns the difference of LA as a set and LB as a set.
;; Type signature: (set-difference list list) -> set
(define (set-difference LA LB)
  (make-set (set-difference-helper LA LB)))
(define (set-difference-helper LA LB)
  (cond [(null? LA) '()]
        [(element? (car LA) LB)
         (set-difference-helper (cdr LA) LB)]
        [else (cons (car LA)
                    (set-difference-helper (cdr LA) LB))]))

;; Returns the symmetric difference of LA as a set and LB as a set.
;; Type signature: (sym-diff list list) -> set
(define (sym-diff LA LB)
  (union (set-difference LA LB)
         (set-difference LB LA)))

;; Returns the cardinality of L as a set.
;; Type signature: (cardinality list) -> int
(define (cardinality L)
  (length (make-set L)))

;; Returns whether sets SA and SB are disjoint.
;; Type signature: (disjoint? set set) -> boolean
(define (disjoint? SA SB)
  (null? (intersection SA SB)))

;; Returns SA ⊇ SB.
;; Type signature: (superset? set set) -> boolean
(define (superset? SA SB)
  (subset? SB SA))

;; Returns the set of L, with e added to it.
;; Type signature: (insert element list) -> set
(define (insert e L)
  (make-set (cons e L)))

;; Returns set S without element e.
;; Type signature: (remove element set) -> set
(define (remove e S)
  (set-difference S (list e)))