(def items ["Clojure" "Java" "Scala" "Visual Basic"])
(def colors ["white" "blue"])
(zipmap items (cycle colors))

