(defproject conj2019-mobile-schedule "1.0.0"

  :description "Mobile-friendly version of the Clojure/conj 2019 schedule"
  :url "https://github.com/oakmac/conj2019-mobile-schedule"
  :author "Chris Oakman <chris@oakmac.com>"

  :license {:name "ISC"
            :url "https://github.com/oakmac/conj2019-mobile-schedule/blob/master/LICENSE.md"
            :distribution :repo}

  :dependencies [[org.clojure/clojure "1.10.0"]
                 [com.taoensso/timbre "4.10.0"]
                 [hiccup "1.0.5"]]

  :repl-options {:init-ns com.oakmac.conj2019.mobile-schedule.core})
