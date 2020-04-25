#lang eopl

;;-------------------------------------------------------------------------------
;; Name:Ming Lin
;; Pledge:I pledge my honor that i have abided by the stevens honor code
;;-------------------------------------------------------------------------------

;; In this lab, you'll implement the RSA cryptosystem.
;; If you ever need to write helper functions, feel free to do so.
;; Don't be too concerned with seeking out edge cases with this lab;
;;   if you get everything working for "reasonable" test cases
;;   such as the ones provided for each function, you'll be fine.

;;-----------------------------------------------------------
;;                    KEY GENERATION
;;-----------------------------------------------------------

;; First, we'll set up our generation of keys.
;; RSA keys come in pairs:
;;   a public key for encrypting and a corresponding private key for decrypting.
;; The two keys are generated from a "seed" of three values p, q, and e.
;; p and q must be unequal primes.
;; e must be coprime with lcm(p - 1, q - 1).
;; e must be in the range [ 2, lcm(p - 1, q - 1) ).


;; Here's a helper function "seed-valid?",
;;   which accepts seed values p, q, and e,
;;   and returns whether they make up a valid seed for key generation
;;   based on the restrictions laid out above.
;; You won't need to use this function anywhere in the code you write;
;;   it serves simply as a sanity check so that if your code fails,
;;   you can see whether it was due to a bad combination of seed values.
;;
;; Type signature: (seed-valid? int int int) -> bool
(define (seed-valid? p q e)
  (define (prime? n)
    (let ([cap (sqrt n)])
      (define (h i)
        (cond [(> i cap) #t]
              [(zero? (modulo n i)) #f]
              [else (h (+ i 1))]))
      (if (< n 2) #f (h 2))))
  (let ([L (lcm (- p 1) (- q 1))])
    (and (not (= p q))
         (< e L) (> e 1)
         (= 1 (gcd e L))
         (prime? p) (prime? q))))


;; Here's an efficient implementation of "mul-inv",
;;   which accepts relatively positive integers a and b
;;   and returns a inverse mod b.
;; You will actually need this one!
;;
;; Type signature: (mul-inv int int) -> int
(define (mul-inv a b)
  (define (pulverize a b)
    (if (zero? a) (list b 0 1)
        (let ([p (pulverize (modulo b a) a)])
          (list (car p)
                (- (caddr p)
                   (* (quotient b a) (cadr p)))
                (cadr p)))))
  (modulo (cadr (pulverize a b)) b))


;; Implement "generate", which accepts integers p, q, and e,
;;   and returns the public and private RSA keys generated from those seed values.
;; Don't worry about whether the seed is valid or not;
;;   even an invalid combination of seed values will still produce key values... they just won't work as keys.
;;
;; There are a few values we need to compute here:
;;   m = p * q.
;;   L = lcm(p - 1, q - 1). (lcm = least common multiple)
;;   d = e inverse mod L.
;;
;; Use the EOPL function (lcm a b) to compute lcm(a,b).
;;
;; The public key is the list '(m e). The private key is the list '(m d).
;; "generate" should return the list of these two keys: '((m e) (m d)).
;;
;; Examples:
;; (generate 17 7 5) -> '((119 5) (119 29))
;; (generate 1193 457 4033) -> '((545201 4033) (545201 46801))
;; (generate 5507 6547 77) -> '((36054329 77) (36054329 17084975))
;; (generate 10 5 2) -> '((50 2) (50 1))   <- These values aren't valid for RSA, but they generate keys just fine
;;
;; Type signature: (generate int int int) -> '((int int) (int int))
(define (generate p q e)
  (list (list (* p q) (* e 1)) (list (* p q) (mul-inv e (lcm (- p 1) (- q 1) ) )))
  )


;;-----------------------------------------------------------
;;                  ENCRYPTION/DECRYPTION
;;-----------------------------------------------------------

;; Now we'll write the encryption and decryption functions
;;   to convert between plaintext strings and ciphertext integer lists.
;; First, we need an efficient algorithm for modular exponentiation.


;; Implement "mod-exp", which accepts three integers b, e, and m,
;;   and returns (b^e) % m.
;; You may assume b >= 0, e >= 0, and m > 1.
;; We want this algorithm to run fast and not produce enormous numbers,
;;   so we'll use a special recursive procedure called "modular binary exponentiation".
;;
;; 1. The base case of the procedure is when the exponent is 0,
;;    in which case (b ^ e) % m = (b ^ 0) % m = 1 % m = 1.
;; 2. Otherwise, we consider two cases:
;;    a. If the exponent is even, return ([b^(e/2) % m] ^ 2) % m.
;;       We get this step because
;;         (b^e) % m
;;       = (b^(e/2) * b^(e/2)) % m
;;       = ([b^(e/2) % m] * [b^(e/2) % m]) % m (via distributive property of modulo)

;;       = ([b^(e/2) % m] ^ 2) % m.

;;       Use recursion to compute b^(e/2) % m.
;;    b. If the exponent is odd, return ([b^(e-1) % m] * b) % m.
;;       We get this step because
;;         (b^e) % m
;;       = (b^(e-1) * b) % m
;;       = ([b^(e-1) % m] * [b % m]) % m (due to the distributive property of modulo)

;;       = ([b^(e-1) % m] * b) % m. (due to modular identity)

;;       Use recursion to compute b^(e-1) % m.
;;
;; Helpful EOPL functions:
;;   (modulo a b) returns a % b.
;;   (expt a b) returns a ^ b.  (you should NOT be using this to compute b^e !!!)
;;
;; Examples:
;; (mod-exp 2 7 3) -> 2
;; (mod-exp 5493 62 2) -> 1
;; (mod-exp 2020 89 76) -> 16
;; (mod-exp 325 3467 357457) -> 143472
;; (mod-exp 123 456 789) -> 699
;; (mod-exp 13579 24680 124578) -> 76657
;;
;; Type signature: (mod-exp int int int) -> int
(define (mod-exp b e m)
  (cond
    [(= e 0) 1]
    [(= (modulo e 2) 0) (modulo (expt (mod-exp b (/ e 2) m) 2) m) ]
    [(= (modulo e 2) 1) (modulo (* (mod-exp b (- e 1) m) b) m) ]
    )
  )




;; Implement "encrypt", which accepts a plaintext string, and a public key as a list,
;;   and returns the ciphertext of the plaintext, encrypted using the public key.
;; The key will be formatted as the list '(m e), where m and e are integers.
;; The ciphertext will be a list of integers, which is found
;;   by mapping each character in the string to an integer via the RSA encryption function.
;; The encryption function to use for each character c is E(c) = (c^e) % m, given the key values e and m.
;; For this to work correctly, you'll have to split up the string into a list of characters
;;   and explicitly convert each character to an integer.
;;
;; When testing your own cases, be aware that the ASCII codes for the characters of the string you're encrypting
;;   must all be less than the value of m; otherwise, encryption and decryption will fail.
;;
;; Helpful EOPL functions:
;;   (string->list s) converts string s to a list of characters.
;;   (char->integer c) converts a character to its corresponding ASCII integer.
#|
   Examples:
   (encrypt "aaa" '(119 5))
   -> '(20 20 20)
   (encrypt "Hello World!" '(1363 71))
   -> '(1001 566 8 8 430 84 609 430 1104 8 506 932)
   (encrypt "CS-135 RSA" '(1147 53))
   -> '(428 699 948 441 732 747 311 763 699 1003)
   (equal? (encrypt "RACKET IS GREAT" '(24135217 999))
           '(9341462 9800674 1236858 11178682 5150537 17216396 13663157 12215098
             21249706 13663157 10223961 9341462 5150537 9800674 17216396))
   -> #t
|#
;; Type signature: (encrypt string '(int int)) -> int-list
(define (encrypt plaintext key)
  ;  (map (mod-exp (lambda(x) (char->integer x)) (cadr key) (car key)) (string->list plaintext))
  (encryptHelper (string->list plaintext) key)
  )
  
(define (encryptHelper plaintext key)
  (cond( (null? plaintext) '())
       (else (append (list ( mod-exp (char->integer (car plaintext)) (cadr key) (car key)))
                     (encryptHelper (cdr plaintext) key))))
  )




;; Implement "decrypt", which accepts a ciphertext (a list of integers), and a private key as a list,
;;   and returns the corresponding plaintext, decrypted using the private key.
;; The key will be formatted as the list '(m d), where m and d are integers.
;; The plaintext will be a string, which is found
;;   by mapping each integer in the ciphertext to a character via the RSA decryption function.
;; The decryption function to use for each integer i is D(i) = (i^d) % m, given the key values d and m.
;; For this to work correctly, you'll have to convert each decrypted integer (the result of D(i))
;;   back into a character, then collect the list of characters back into a string.
;;
;; Helpful EOPL functions:
;;   (list->string l) converts a list of characters l into a string made up of those characters.
;;   (integer->char i) converts an integer to its corresponding ASCII character.
#|
   Examples:
   (decrypt '(20 20 20) '(119 29))
   -> "aaa"
   (decrypt '(1001 566 8 8 430 84 609 430 1104 8 506 932) '(1363 127))
   -> "Hello World!"
   (decrypt '(428 699 948 441 732 747 311 763 699 1003) '(1147 17))
   -> "CS-135 RSA"
   (decrypt '(9341462 9800674 1236858 11178682 5150537 17216396 13663157
              12215098 21249706 13663157 10223961 9341462 5150537 9800674 17216396)
            '(24135217 1642119))
   -> "RACKET IS GREAT"
|#
;; Type signature: (decrypt int-list '(int int)) -> string
(define (decrypt ciphertext key)
;  (map(integer->char ((list->string (map (mod-exp (ciphertext)) (car (cdr key)) (car (car key)))))))
  (list->string (decryptHelper ciphertext (car key) (cadr key)))
)

(define (decryptHelper ciphertext m d)
  (cond ((null? ciphertext) '())
        (else (append (list (integer->char (mod-exp (car ciphertext) d m))) (decryptHelper (cdr ciphertext) m d))))
  )
  

;;-----------------------------------------------------------
;;                      EXTRA STUFF
;;-----------------------------------------------------------

;; If you correctly implemented the functions in this program,
;;   inputting any string s and any valid combination of values for p, q, and e,
;;   (check s p q e) should return s.
;;   "check" first encrypts s with a public key,
;;   then decrypts the resulting ciphertext with the corresponding private key.
;; Remember, the largest ASCII value of any character in the string
;;   needs to be smaller than p * q. When in doubt, use big primes for p and q.
;; Try it out!
;;
;; Type signature: (check string int int int) -> string
(define (check s p q e)
  (let ([keys (generate p q e)])
    (decrypt (encrypt s (car keys)) (cadr keys))))


;; As a bonus challenge, see if you can decrypt these messages encrypted with RSA:
;;
;; PUBLIC KEY                 CIPHERTEXT
;; m = 247    e = 23          '(159 80 85 138 128 49 80 91 128 201 80 91 154 237 128 86 43 67)
;; m = 145    e = 17          '(23 102 45 80 11 11 52 44 115 2 115 104 55 45 96 2 104 11 102 80)
;; m = 5963   e = 151         '(1080 4133 5460 4133 10 1117 4133 10 3635 4133 5605 5877 4133 1080 5605)