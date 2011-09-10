(def items ["Clojure" "Java" "Scala" "Visual Basic"])
(def colors ["white" "blue"])
(zipmap items (cycle colors))


(import '(javax.swing JFrame JLabel))
(def frm (JFrame.))
(def lbl (JLabel. "Hello, Clojure!"))
(doto frm (. add lbl) (. pack) (. show))

(doto (JFrame.) (. add (JLabel. "Hello, Clojure!")) (. pack) (. show))

