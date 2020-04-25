#lang eopl
;;-------------------------------------------------------------------------------
;; Name: Ming Lin
;; Pledge: I pledge my honor that i have abided by the Stevens Honor System
;;-------------------------------------------------------------------------------


;; This lab is a continuation of last week's lab about relations.
;; All the functions you wrote last week are now available
;;   as helper functions at the bottom of this file,
;;   along with the same helper functions as before.
;;
;; Once again, the order of edges inside a relation doesn't matter
;;   for the correctness of your outputs.
;; There should also be no duplicate edges in the relations you output.
;;
;; For all of the functions you'll implement below,
;;   the empty relation '() is valid input.

;;______________________________________________________________________

;; Implement "compose", which accepts relations
;;   RO ("R-outer") and RI ("R-inner")
;;   and returns RO ∘ RI, or RO composed with RI.
;; RO ∘ RI = { (x z) | ∃y: (x y) ∈ R Λ (y z) ∈ R }
;;
;; Advice for implementing:
;;   Consider every edge (a b) in RI.
;;   For every edge (c d) in RO,
;;     if b == c then add (a d) to RO ∘ RI.
;;
;; Examples:
;; (compose '((2 4) (3 5) (4 6) (5 7)) '((1 2) (2 3) (3 4) (4 5)))
;;   -> '((1 4) (2 5) (3 6) (4 7))
;; (compose '((2 4) (5 8) (3 4)) '((2 4) (5 8) (3 4)))
;;   -> '()
;; (compose '((1 1) (2 2) (3 3)) '((1 1) (2 2) (3 3)))
;;   -> '((1 1) (2 2) (3 3))
;; (compose '((1 1) (2 1) (3 1) (4 1)) '((1 1) (1 2) (1 3)))
;;   -> '((1 1))
;; (compose '((1 1) (1 2) (1 3)) '((1 1) (2 1) (3 1) (4 1)))
;;   -> '((1 1) (1 2) (1 3) (2 1) (2 2) (2 3) (3 1) (3 2) (3 3) (4 1) (4 2) (4 3))
;;
;; Type Signature: (compose relation relation) -> relation
(define (compose RO RI)
  (if (null? RO) '()
      (append (compose_helper (car RO) RI) (compose (cdr RO) RI)))
  )

(define (compose_helper tuple RI)
  (if (null? RI) '()
      (if (equal? (cadar RI) (car tuple))
          (cons (list (caar RI) (cadr tuple)) (compose_helper tuple (cdr RI)))
          (compose_helper tuple (cdr RI)))))

;; Implement "power", which accepts a relation R and an integer k ≥ 0
;;   and returns R^k, which is R composed with itself (k - 1) times.
;;
;;   R^0 = '()
;;   R^1 = R
;;   R^2 = R ∘ R
;;   R^3 = R ∘ (R ∘ R)
;;   ...
;;   R^k = R ∘ R^(k-1)
;;
;; Advice:
;;   You'll need to keep track of the initial R to repeatedly compose it.
;;   To do this, you'll likely need a helper function.
;;
;; Examples:
;; (power '((1 2) (2 3) (3 4) (4 1)) 0)
;;   -> '()
;; (power '((1 2) (2 3) (3 4) (4 1)) 1)
;;   -> '((1 2) (2 3) (3 4) (4 1))
;; (power '((1 2) (2 3) (3 4) (4 1)) 2)
;;   -> '((1 3) (2 4) (3 1) (4 2))
;; (power '((1 2) (2 3) (3 4) (4 1)) 3)
;;   -> '((1 4) (2 1) (3 2) (4 3))
;; (power '((1 2) (3 3) (3 4) (4 2) (5 3)) 4)
;;   -> '((3 3) (3 4) (3 2) (5 3) (5 4) (5 2))
;;
;; Type Signature: (power relation int) -> relation
(define (power R k)
  (if (equal? k 0) '()
      (if (equal? k 1) R
          (compose R (power (power_helper R) (- k 1)))))
  )

(define (power_helper R)
  (if (null? R) '() R))

;; Implement "transitive-closure", which accepts a relation R
;;   and returns R+, the transitive closure of R.
;; R+ can be computed by "unioning" successive powers of R.
;; If e = |R| = the number of edges in R:
;;   R+ = R ⋃ R^2 ⋃ R^3 ⋃ ... ⋃ R^e.
;;
;; Examples:
;; (transitive-closure '((1 2) (2 3) (3 1)))
;;   -> '((1 1) (1 2) (1 3) (2 1) (2 2) (2 3) (3 1) (3 2) (3 3))
;; (transitive-closure '((1 3) (3 5) (2 4) (5 6) (2 3)))
;;   -> '((1 3) (3 5) (2 4) (5 6) (2 3) (1 5) (1 6) (3 6) (2 5) (2 6))
;; (transitive-closure '((1 2) (2 1) (3 4) (4 5)))
;;   -> '((1 1) (2 2) (3 5) (1 2) (2 1) (3 4) (4 5))
;;
;; Type Signature: (transitive-closure relation) -> relation
(define (transitive-closure R)
  (transitive_closure_helper R R (length R)))

(define (transitive_closure_helper R rel length)
  (if (equal? length 0) '()
      (if (equal? length 1) rel
      (union (transitive_closure_helper R (power R length) (- length 1)) rel))))

;; Implement "transitive?", which accepts a relation R
;;   and returns whether R is transitive.
;; This is easy to write if you utilize transitive-closure.
;;
;; Examples:
;; (transitive? '((1 1) (1 2) (1 3) (2 1) (2 2) (2 3) (3 1) (3 2) (3 3)))
;;   -> #t
;; (transitive? '((1 2) (2 3) (3 1)))
;;   -> #f
;; (transitive? '())
;;   -> #t
;; (transitive? '((1 2) (2 1) (1 1) (2 2)))
;;   -> #t
;;
;; Type Signature: (transitive? relation) -> boolean
(define (transitive? R)
  (if (set-equal? R (transitive-closure R)) #t #f)
)

;; Implement "EQ-relation?", which accepts a relation R and positive integer n
;;   and returns whether R is an equivalence relation over the domain [1, n].
;; Recall that a relation is an EQ-relation
;;   iff it is symmetric, reflexive, and transitive.

;; Examples:
;; (EQ-relation? '((1 1) (1 2) (1 3) (2 1) (2 2) (2 3) (3 1) (3 2) (3 3)) 3)
;;   -> #t
;; (EQ-relation? '((1 1) (1 2) (2 1)) 2)
;;   -> #f
;;
;; Type Signature: (EQ-relation? relation int) -> boolean
(define (EQ-relation? R n)
  (if (and (transitive? R) (symmetric? R) (reflexive? R n)) #t #f)
  )


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

;; Returns the relation ((1 1) (2 2) ... (n n))
;; Type Signature: (id int) -> relation
(define (id n)
  (if (zero? n) '()
      (cons (list n n)
            (id (- n 1)))))

;; Returns whether R is reflexive over the domain [1, n].
;; Type Signature: (reflexive? relation int) -> boolean
(define (reflexive? R n)
  (subset? (id n) R))

;; Returns the reflexive closure of R over the domain [1, n].
;; Type Signature: (reflexive-closure relation int) -> relation
(define (reflexive-closure R n)
  (union R (id n)))

;; Returns the inverse of R.
;; Type Signature: (inverse relation) -> relation
(define (inverse R)
  (map reverse R))

;; Returns whether R is symmetric.
;; Type Signature: (symmetric? relation int) -> boolean
(define (symmetric? R)
  (set-equal? R (inverse R)))

;; Returns the symmetric closure of R.
;; Type Signature: (symmetric-closure relation) -> relation
(define (symmetric-closure R)
  (union R (inverse R)))

;; Returns the set { y | (v, y) ∈ R }.
;; Type Signature: (relates-to vertex relation) -> set
(define (relates-to v R)
  (make-set (relates-to-helper v R)))
(define (relates-to-helper v R)
  (cond
    [(null? R) '()]
    [(= v (caar R))
     (cons (cadar R)
           (relates-to-helper v (cdr R)))]
    [else (relates-to-helper v (cdr R))]))