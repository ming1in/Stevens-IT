#lang eopl

;;-------------------------------------------------------------------------------
;; Name: Ming Lin
;; Pledge: I pledge my honor that i have abided by the stevens honor system
;;-------------------------------------------------------------------------------

;; In this lab, you'll implement the Chinese Remainder Theorem
;;   by breaking up the algorithm into several small functions.
;;
;; We'll represent systems of linear congruences with lists of integer pairs,
;;   where the pair (a b) represents the congruence x ≡ a (mod b).
;; For example, the "cong-sys" '((10 11) (4 12) (12 13)) represents the system:
;;   x ≡ 10 (mod 11)
;;   x ≡  4 (mod 12)
;;   x ≡ 12 (mod 13)
;;
;; For any cong-sys passed through to a function in this lab, you may assume:
;;  - The cong-sys is not empty (it contains at least one integer pair).
;;  - All of the moduli are positive (no need to check for modulo 0).


;;-------------------------------------------------------------------------------
;;                               GCD FUNCTIONS
;;-------------------------------------------------------------------------------

;; First, here's a function provided to you which
;;   performs the pulverizer process (Euclid's extended algorithm).
;; This will come in handy for the subsequent functions you write for CRT.
;; Given non-negative integers a and b,
;;   it returns the list '(g s t) where g = gcd(a,b) = s*a + t*b.
;; So, what this function returns is the gcd of a and b, along with
;;   the coefficients of the smallest positive linear combination of a and b.
;;
;; Type Signature: (pulverize int int) -> list
(define (pulverize a b)
  (if (zero? a)
      (list b 0 1)
      (let ([p (pulverize (modulo b a) a)])
        (list (car p)
              (- (caddr p)
                 (* (quotient b a) (cadr p)))
              (cadr p))))
  )

;; Even though (pulverizer a b) does compute gcd(a,b),
;;   it does so expensively because it also has to
;;   compute the linear combination of a and b.
;; So we should also have a function which only computes the GCD.
;;
;; Implement euclid-gcd, which accepts two integers
;;   and returns their greatest common divisor.
;; Don't use the built-in "gcd" function, or the "pulverize" helper function;
;;   instead, use Euclid's algorithm to efficiently compute the GCD!
;; You may assume the inputs are non-negative.
;;
;; Remember, the "modulo" function in EOPL computes mod!
;;
;; Examples:
;; (euclid-gcd 0 0) -> 0
;; (euclid-gcd 1 0) -> 1
;; (euclid-gcd 5 2) -> 1
;; (euclid-gcd 4 6) -> 2
;; (euclid-gcd 12 9) -> 3
;; (euclid-gcd 30 45) -> 15
;;
;; Type Signature: (euclid-gcd int int) -> int
(define (euclid-gcd a b)
   (if (zero? b)
      a
      (euclid-gcd b (modulo a b)))
  )

;; Now you technically have two functions which compute gcd,
;;   but one is more efficient than the other.
;; To write efficient code, you should only use "pulverize"
;;   when you need the linear combination coefficients;
;;   otherwise use "euclid-gcd" when you need to compute GCD.


;; Before we write the functions to compute CRT,
;;   let's figure out whether CRT can even be computed for a given input.
;; Implement "CRT-exists?" to accept a system of linear congruences
;;   and return a boolean stating if CRT is possible with this system.
;; CRT is possible iff all moduli in the system are pairwise relatively prime.
;; Recall that two numbers are relatively prime if their GCD is 1.
;;
;; For example, the cong-sys '((2 3) (3 5) (2 6))) has moduli 3, 5, and 6,
;;   which are not pairwise relatively prime. So CRT isn't possible.
;; By contrast, the moduli of '((10 11) (4 12) (12 13))) are 11, 12, and 13,
;;   which are pairwise relatively prime. So CRT is possible!
;;
;; You'll have to compare every modulo with every other modulo in the system.
;;
;; Examples:
;; (CRT-exists? '((2 3) (3 5) (2 6))) -> #f
;; (CRT-exists? '((10 11) (4 12) (12 13))) -> #t
;; (CRT-exists? '((1 5) (2 14) (5 23) (26 28))) -> #f
;; (CRT-exists? '((1 2) (1 3) (1 5) (1 7) (1 11) (1 13))) -> #t
;;
;; Type Signature: (CRT-exists? cong-sys) -> boolean
(define (CRT-exists? cong-sys)
  (if (null? cong-sys)
      #t
      (if (existhelper (cadar cong-sys)(cdr cong-sys))
          (CRT-exists?(cdr cong-sys))
          #f))
  )

(define(existhelper m0 cong-sys)
  (if(null? cong-sys)
     #t
     (if(equal? (euclid-gcd m0 (cadar cong-sys)) 1)
        (existhelper m0(cdr cong-sys))
        #f))
  )

;;-------------------------------------------------------------------------------
;;                               CRT FUNCTIONS
;;-------------------------------------------------------------------------------

;; Now we're ready to make the CRT calculator!


;; First, implement "mul-inv", which accepts non-negative integers a and b,
;;   and returns integer x such that a*x ≡ 1 (mod b).
;; In other words, it returns the modular multiplicative inverse of a (mod b).
;; You may assume that a and b are relatively prime, and that b is not 0.
;;
;; Hint: what process do we use to compute modular multiplicative inverses by hand?
;; What helper function do we have for this?
;;
;; Examples:
;; (mul-inv 31 76) -> 27
;; (mul-inv 127 555) -> 118
;; (mul-inv 1234 4321) -> -1082

;; Type Signature: (mul-inv int int) -> int
(define (mul-inv a b)
  (cadr(mulinv-helper a b))
  )

(define (mulinv-helper a b)
  (if (zero? a)
      (list b 0 1)
      (list (euclid-gcd a b)
            (- (caddr (mulinv-helper (modulo b a) a)) (* (quotient b a) (cadr (mulinv-helper (modulo b a) a))))
            (cadr (mulinv-helper (modulo b a) a))))
  )

;; Implement "m", which accepts a system of linear congruences
;;   and returns the value of "m" in the CRT process,
;;   which is the product of all moduli in the system.
;; Hint: look into EOPL's "apply" and "map" functions to make this really easy!
;;
;; Examples:
;; (m '((2 3) (3 5) (2 7))) -> 105
;; (m '((10 11) (4 12) (12 13))) -> 1716
;; (m '((1 5) (2 14) (5 23) (26 27))) -> 43470
;;
;; Type Signature: (m cong-sys) -> int
(define (m cong-sys)
  (if (null? cong-sys)
      1
      (*(cadar cong-sys)(m(cdr cong-sys))))
  )

;; Implement CRT-helper, which accepts a valid cong-sys (one where CRT exists)
;;   and m (the product of all the moduli),
;;   and returns the solution to the system via CRT without simplifying.
;;
;; To do this, you need to summate the values of ai*Mi*yi for each pair in the system.
;; For the ith congruence x ≡ ai (mod bi), represented by the pair (ai bi) in the cong-sys:
;;   Mi = m / bi.
;;   yi = the multiplicative inverse of Mi (mod bi).
;;
;; Examples:
;; (CRT-helper '((10 11) (4 12) (12 13)) 1716) -> -17876
;; (CRT-helper '((2 3) (3 5) (2 7)) 105) -> 23
;; (CRT-helper '((1 2) (2 3) (3 5) (4 7) (5 11) (6 13)) 30030) -> -30817
;;
;; Type Signature: (CRT-helper cong-sys int) -> int
(define (CRT-helper cong-sys m)
  (if (null? cong-sys)
    0
    (+ (* (car (car cong-sys) )(/ m (cadar cong-sys) ) ( mul-inv (/ m(cadar cong-sys)) (cadar cong-sys) ) ) (CRT-helper (cdr cong-sys) m)))

  )

;; Now we'll bring everything together and write the function
;;   to calculate CRT from start to finish.
;;
;; Implement CRT, which accepts a cong-sys which may or may not be valid.
;; First, check if CRT is possible with the given cong-sys.
;; If CRT isn't possible, return -1.
;; If CRT is possible, find the unsimplified solution X to the system with CRT-helper,
;;   then return the simplified solution, which is the smallest positive integer
;;   congruent to X (mod m).
;;
;; Examples:
;; (CRT '((10 11) (4 12) (12 13))) -> 1000
;; (CRT '((2 3) (3 5) (2 7))) -> 23
;; (CRT '((1 2) (2 3) (3 5) (4 7) (5 11) (6 13))) -> 29243
;; (CRT '((1 2) (4 8) (8 9))) -> -1
;;
;; Type Signature: (CRT cong-sys) -> int
(define (CRT cong-sys)
  (if(CRT-exists? cong-sys)
     (modulo (CRT-helper cong-sys(m cong-sys))(m cong-sys))
     -1)
  )