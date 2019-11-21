(ns com.oakmac.conj2019.mobile-schedule.core
  {:author "Chris Oakman <chris@oakmac.com>"
   :license "ISC"}
  (:require
    [clojure.string :as str]
    [hiccup.core :as hc]
    [hiccup.def :refer [defhtml]]
    [hiccup.page :as hp]))

(defhtml talk-cell []
  [:div
   [:div [:a {:href "#"} "John James"]]
   [:div {:style "font-size: 14px"} "Talking to the REPL"]])

(defhtml table-row []
  [:tr
    [:td {:style "text-align: center"} "9:10 - 9:40am"]
    [:td (talk-cell)]
    [:td (talk-cell)]])

(defhtml tbody []
  [:tbody
   (table-row)
   (table-row)
   (table-row)
   (table-row)
   (table-row)
   (table-row)])

(defhtml thead []
  [:thead
    [:tr
      [:th]
      [:th "Ballroom A-B"]
      [:th "Ballroom C-D"]]])

(defhtml the-table []
  [:table.table.is-striped.is-narrow.is-fullwidth
    (thead)
    (tbody)])

(def official-schedule-url "https://2019.clojure-conj.org/speakers/")
(def speakers-list-url "https://2019.clojure-conj.org/speakers/")

(defhtml body []
  [:body
    [:section.section {:style "padding-top: 15px"}
      [:div.container
        [:h1.title.is-2 "2019 Clojure/conj Schedule"]
        [:h5.subtitle.is-5
          [:a {:href official-schedule-url} "Official Schedule"]
          [:span {:style "display: inline-block; margin: 0 10px;"} "—"]
          [:a {:href speakers-list-url} "Speakers List"]]
        ;; TODO: ability to hide past event
        [:div {:style "height: 10px"}]
        [:h4.title.is-4 "Wednesday - Nov 20, 2019"]
        (the-table)
        [:h4.title.is-4 "Thursday - Nov 21, 2019"]
        (the-table)
        [:h4.title.is-4 "Friday - Nov 22, 2019"]
        (the-table)
        [:h4.title.is-4 "Saturday - Nov 23, 2019"]
        (the-table)]]])


(def bulma-cdn-url "https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.5/css/bulma.min.css")
(def bulma-integrity-hash "sha256-vK3UTo/8wHbaUn+dTQD0X6dzidqc5l7gczvH+Bnowwk=")

(defhtml head []
  [:head
   [:meta {:http-equiv "Content-Type" :content "text/html; charset=UTF-8"}]
   [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
   [:title "Schedule - Clojure/conj 2019"]
   [:link {:rel "stylesheet" :href bulma-cdn-url :integrity bulma-integrity-hash :crossorigin "anonymous"}]])

(defn build-schedule-html []
  (hp/html5
    (head)
    (body)))

(defn write-schedule!
  ""
  []
  (spit "public/schedule.html" (build-schedule-html)))
