#lang eopl

;;-------------------------------------------------------------------------------
;; Name:Ming Lin
;; Pledge:I pledge my honor that I have abided by the Stevens Honor System
;;-------------------------------------------------------------------------------

;; In this lab, you'll implement some basic logic operators using
;;   Scheme's built-in "and", "or", and "not" functions.
;; Keep in mind that you can also define functions in terms of
;;   other functions you've already implemented in this file!
;;
;; Remember that Scheme uses prefix notation,
;;   so "p and q" is written "(and p q)".
;;
;; True and false are written in Scheme as "#t" and "#f".




;; Implement the function (nand p q) to return "p nand q":
;;
;; p q | p nand q
;; ---------------
;; T T | F
;; T F | T
;; F T | T
;; F F | T

;; Type signature: (nand boolean boolean) -> boolean
(define (nand p q)
  (not (and p q)))

;; Implement (implies p q) to return "p implies q" (if p, then q):
;;
;; p q | p implies q
;; ------------------
;; T T | T
;; T F | F
;; F T | T
;; F F | T

;; Type signature: (implies boolean boolean) -> boolean
(define (implies p q)
  (or (not p) q))

;; Implement (xor p q) to return "p xor q" (exclusive or):
;;
;; p q | p xor q
;; --------------
;; T T | F
;; T F | T
;; F T | T
;; F F | F

;; Type signature: (xor boolean boolean) -> boolean
(define (xor p q)
  (not (equal? p q)))

;; Implement (nor p q) to return "p nor q":
;;
;; p q | p nor q
;; --------------
;; T T | F
;; T F | F
;; F T | F
;; F F | T

;; Type signature: (nor boolean boolean) -> boolean
(define (nor p q)
  (not (or p q)))

;; Implement (3majority p q r) to return #t when
;;   a majority of its three arguments are #t:
;;
;; p q r | (3majority p q r)
;; --------------------------
;; T T T | T
;; T F T | T
;; F T T | T
;; F F T | F
;; T T F | T
;; T F F | F
;; F T F | F
;; F F F | F

;; Type signature: (3majority boolean boolean boolean) -> boolean
(define (3majority p q r)
  (or(and p q) (and p r) (and q r)))

;; Implement (isosceles p q r) to return #t when
;;   exactly two of its arguments are #t:
;;
;; p q r | (isosceles p q r)
;; --------------------------
;; T T T | F
;; T F T | T
;; F T T | T
;; F F T | F
;; T T F | T
;; T F F | F
;; F T F | F
;; F F F | F

;; Type signature: (isosceles boolean boolean boolean) -> boolean
(define (isosceles p q r)
  (or (and p q (not r))
      (and p r (not q))
      (and q r (not p))))

;; Created January 2018 by Samuel Kraus and Edward Minnix
;; Updated January 2020 by Jared Pincus