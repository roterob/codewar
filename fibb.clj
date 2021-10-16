
;; Lazy seq
(def fib-lazy-seq ((fn fib [a b]
                     (lazy-seq (cons a (fib b (+ a b))))) 0 1))

;; Iterate (lazy seq)
(def fib-lazy-iter 
  (map first (iterate (fn [[a b]] [b (+ a b)]) [0 1])))

;; Lazy cat
(def fib-lazy-cat
  (lazy-cat [0 1] (map + (rest fib-lazy-cat) fib-lazy-cat)))

;; Thread version
(defn fib-n [n]
  (->> [0 1]
       (iterate (fn [[a b]] [b (+ a b)]))
       (map first)
       (take n)))

;; Recursion version
(defn fib-rec
  ([n] (fib-rec [0 1] n))
  ([l n] (if (= n (count l))
          l
          (fib-rec (conj l (reduce + (take-last 2 l))) n))))

;; Imperative version
(defn fib-iter [n]
  (loop [l [0 1]]
    (if (<= n (count l))
      (subvec l 0 n)
      (recur (conj l (reduce + (take-last 2 l)))))))




