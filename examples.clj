; Higher-order functions

; Runnable
(. (new Thread #(println "Hello, Thread")) start)

; Comparator
(def dna-bases ["AG" "GGATAC" "CTG"])
(sort (comparator (fn [a b] (< (count a) (count b)))) dna-bases)
(sort-by #(count %) dna-bases)

; Iteration
(def iarray [1 2 3 4])


; Lazy sequences

(def *items* ["Clojure" "Java" "Scala" "Visual Basic"])
(def *colors* ["white" "blue"])
(defn map-line-colors [items colors]
  (zipmap items (cycle colors)))
(map-line-colors *items* *colors*)

(defn map-line-colors-2 [items colors]
  (loop [retval {} itm items clr colors]
    (if (empty? itm) 
      retval
      (recur (assoc retval (first itm) (first clr)) (next itm) (next clr)))))


; Java integration

(import '(javax.swing JFrame JLabel))
(def frm (JFrame.))
(def lbl (JLabel. "Hello, Clojure!"))
(doto frm (. add lbl) (. pack) (. show))

(doto (JFrame.) (. add (JLabel. "Hello, Clojure!")) (. pack) (. show))

