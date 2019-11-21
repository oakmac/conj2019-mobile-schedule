(ns com.oakmac.conj2019.mobile-schedule.core
  {:author "Chris Oakman <chris@oakmac.com>"
   :license "ISC"}
  (:require
    [clojure.string :as str]
    [hiccup.core :as hc]
    [hiccup.def :refer [defhtml]]
    [hiccup.page :as hp]))

(def schedule
 {:nov-21
  [["9:00 - 9:10AM" [{:speaker "Welcome" :non-talk? true}]]
   ["9:10 - 9:50AM"
    [{:speaker "Derek Troy-West",
      :title "Follow the Data: Product Development in Clojure",
      :link "https://2019.clojure-conj.org/speaker-derek-troy-west/"}]]
   ["10:00 - 10:40AM"
    [{:speaker "Ariel Ortiz",
      :title "The Hitchhikers Guide to Multiparadigm Programming",
      :link "https://2019.clojure-conj.org/speaker-ariel-ortiz/"}]]
   ["10:50 - 11:30AM"
    [{:speaker "Lucas Cavalcanti",
      :title "Complex Made Bearable",
      :link "https://2019.clojure-conj.org/speaker-lucas-cavalcanti/"}]]
   ["11:30 - 1:00PM"
    [{:speaker "Lunch",
      :title "Food trucks on convention center plaza"
      :non-talk? true}]]
   ["1:00 - 1:40PM"
    [{:speaker "Santiago Gepigon III",
      :title "piggy: Specs for Breaking Changes",
      :link "https://2019.clojure-conj.org/speaker-santiago-gepigon-iii/"}
     {:speaker "Alexander Oloo",
      :title "From Lazy Lisper to Confident Clojurist",
      :link "https://2019.clojure-conj.org/alexander-oloo/"}]]
   ["1:50 - 2:30PM"
    [{:speaker "Mark Bastian",
      :title "Defeating the Four Horsemen of the Coding Apocalypse",
      :link "https://2019.clojure-conj.org/speaker-mark-bastian"}
     {:speaker "J.D. Hollis",
      :title "Datomic with Terraform",
      :link "https://2019.clojure-conj.org/speaker-j-d-hollis/"}]]
   ["2:40 - 3:20PM"
    [{:speaker "Dom Kiva-Meyer",
      :title "Fixing Airplanes with Clojure",
      :link "https://2019.clojure-conj.org/dom-kiva-meyer/"}
     {:speaker "John Collins",
      :title "Clojure for Robotics",
      :link "https://2019.clojure-conj.org/speaker-john-collins/"}]]
   ["3:20 - 3:50PM" [{:speaker "Break" :title "" :non-talk? true}]]
   ["3:50 - 4:30PM"
    [{:speaker "Dragan Djuric",
      :title "Interactive Programming for Artificial Intelligence",
      :link "https://2019.clojure-conj.org/speaker-dragan-djuric/"}]]
   ["4:40 - 5:30PM"
    [{:speaker "Stuart Halloway",
      :title "Sherlock Holmes, Consulting Developer",
      :link "https://2019.clojure-conj.org/speaker-stuart-halloway"}]]
   ["7:00 - 10:00PM" [{:speaker "Unsessions / Lightning Talks" :non-talk? true}]]]

  :nov-22
  [["9:00 - 9:10AM" [{:speaker "Welcome" :non-talk? true}]]
   ["9:10 - 9:50AM" [{:speaker "Gene Kim"
                      :title "TBD"
                      :link "https://2019.clojure-conj.org/speaker-gene-kim/"}]]
   ["10:00 - 10:40AM" [{:speaker "Chris Nuernberger"
                        :title "Extending Clojure with Python"
                        :link "https://2019.clojure-conj.org/chris-nuernberger/"}
                       {:speaker "Avi Flax"
                        :title "(Architecture) Diagrams as Data"
                        :link "https://2019.clojure-conj.org/speaker-avi-flax/"}]]
   ["10:50 - 11:30AM" [{:speaker "Alexander Yakushev"
                        :title "A New Age of JVM Garbage Collectors"
                        :link "https://2019.clojure-conj.org/speaker-alexander-yakushev/"}
                       {:speaker "Chris Oakman"
                        :title "Probabilistic Record Linkage of Hospital Patients"
                        :link "https://2019.clojure-conj.org/speaker-chris-oakman/"}]]
   ["11:30 - 1:00PM" [{:speaker "Lunch" :title ""}]]
   ["1:00 - 1:40PM" [{:speaker "Pier Federico Gherardini & Ben Kamphaus"
                      :title "Clojure Where it Counts: Tidying Data Science Workflows"
                      :link "https://2019.clojure-conj.org/speaker-pier-federico-gherardini/"}
                     {:speaker "Wilker Silva"
                      :title "The Maximal Graph"
                      :link "https://2019.clojure-conj.org/speaker-chris-oakman/"}]]
   ["1:50 - 2:30PM" [{:speaker "Dennis Heihoff"
                      :title "The Embodied Runtime"
                      :link "https://2019.clojure-conj.org/speaker-dennis-heihoff/"}
                     {:speaker "Eno Compton & Tyler van Hensbergen"
                      :title "Goodbye YAML: Infrastructure as Code in Clojure"
                      :link "https://2019.clojure-conj.org/speaker-wilker-silva/"}]]
   ["2:40 - 3:20PM" [{:speaker "Tom Toor"
                      :title "Why Build Solutions with Fulcro"
                      :link "https://2019.clojure-conj.org/speaker-tom-toor"}
                     {:speaker "Scarlet Spectacular"
                      :title "Ghost in the Generative Shell"
                      :link "https://2019.clojure-conj.org/speaker-eno-compton/"}]]
   ["3:20 - 3:50PM" [{:speaker "Break" :title "" :non-talk? true}]]
   ["3:50 - 4:30PM" [{:speaker "Thomas Gebert & Nick Misturak"
                      :title "Distributed Hash Tables, Video, and Fun!"
                      :link "https://2019.clojure-conj.org/speaker-thomas-gebert/"}]]
   ["4:40 - 5:30PM" [{:speaker "Matthew Flatt"
                      :title "A Racket Perspective on Research, Education, and Production"
                      :link "https://2019.clojure-conj.org/keynote-speaker-matthew-flatt/"}]]
   ["7:00 - 10:00PM" [{:speaker "Party at Boxcar Arcade" :non-talk? true}]]]

  :nov-23
  [["9:00 - 9:10AM" [{:speaker "Welcome" :non-talk? true}]]
   ["9:10 - 9:50AM" [{:speaker "Philipp Meier"
                      :title "A magic trashcan - microprocessors for full stack developers"
                      :link "https://2019.clojure-conj.org/speaker-philipp-meier/"}]]
   ["10:00 - 10:40AM" [{:speaker "Sara Kimmich"
                        :title "Rapid Prototyping for Software Development"
                        :link "https://2019.clojure-conj.org/speaker-sara-kimmich/"}]]
   ["10:50 - 11:30AM" [{:speaker "Ulrich Schaechtle"
                        :title "Inference QL:  Al for data engineers in Clojure"
                        :link "https://2019.clojure-conj.org/speaker-ulrich-schaechtle/"}]]
   ["11:40 - 12:10PM" [{:speaker "Alex Miller"
                        :title "Composable Tools"}]]]})

(defhtml talk-cell [{:keys [speaker title link non-talk?]}]
  [:div (when non-talk? {:style "font-style: italic;"})
   [:div (if link
           [:a {:href link} speaker]
           speaker)]
   [:div {:style "font-size: 14px"} title]])

(defhtml table-row [[time speakers]]
  (let [two-tracks? (= 2 (count speakers))]
    [:tr
      [:td {:style "text-align: left; font-size: 14px;"} time]
      [:td (when-not two-tracks? {:colspan 2}) (talk-cell (first speakers))]
      (when two-tracks?
        [:td (talk-cell (second speakers))])]))

(defhtml tbody [items]
  [:tbody
   (map table-row items)])

(defhtml thead []
  [:thead
    [:tr
      [:th]
      [:th "Ballroom A-B"]
      [:th "Ballroom C-D"]]])

(defhtml schedule-table [schedule-items]
  [:table.table.is-striped.is-narrow.is-fullwidth.is-bordered
    (thead)
    (tbody schedule-items)])

(def official-schedule-url "https://2019.clojure-conj.org/speakers/")
(def speakers-list-url "https://2019.clojure-conj.org/speakers/")

(def zepto-cdn-url "https://cdnjs.cloudflare.com/ajax/libs/zepto/1.2.0/zepto.min.js")
(def zepto-integrity-hash "sha256-vrn14y7WH7zgEElyQqm2uCGSQrX/xjYDjniRUQx3NyU=")

(defhtml clientside-js []
  [:script {:src zepto-cdn-url :integrity zepto-integrity-hash :crossorigin "anonymous"}]
  [:script {:src "index.js"}])

(defhtml toolbar []
  [:div {:style "display: flex; margin-top: -10px; margin-bottom: 20px;"}
    [:div {:style "flex: 3;"}
      [:input#searchBar.input {:type "text" :placeholder "Search …"}]]
    [:div {:style "flex: 1; margin-left: 10px"}
      [:button#hidePastEventsBtn.button.is-secondary {:style "display:none"} "Hide Past Events"]
      [:button#showPastEventsBtn.button.is-secondary "Show Past Events"]]])

(defhtml body []
  [:body
    [:section.section {:style "padding-top: 15px"}
      [:div.container
        [:h1.title.is-3 "2019 Clojure/conj Schedule"]
        [:h5.subtitle.is-6
          [:a {:href official-schedule-url} "Official Schedule"]
          [:span {:style "display: inline-block; margin: 0 10px;"} "—"]
          [:a {:href speakers-list-url} "Speakers List"]]
        ; (toolbar)
        ; [:h4.title.is-4 "Wednesday - Nov 20, 2019"]
        ; (schedule-table (:nov-20 schedule))
        [:h4.title.is-4 "Thursday - Nov 21, 2019"]
        (schedule-table (:nov-21 schedule))
        [:h4.title.is-4 "Friday - Nov 22, 2019"]
        (schedule-table (:nov-22 schedule))
        [:h4.title.is-4 "Saturday - Nov 23, 2019"]
        (schedule-table (:nov-23 schedule))]]
    (clientside-js)])

(def bulma-cdn-url "https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.5/css/bulma.min.css")
(def bulma-integrity-hash "sha256-vK3UTo/8wHbaUn+dTQD0X6dzidqc5l7gczvH+Bnowwk=")

(defhtml head []
  [:head
   [:meta {:http-equiv "Content-Type" :content "text/html; charset=UTF-8"}]
   [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
   [:title "Schedule - Clojure/conj 2019"]
   [:link {:rel "stylesheet" :href bulma-cdn-url :integrity bulma-integrity-hash :crossorigin "anonymous"}]])

(defn build-schedule-page []
  (hp/html5
    (head)
    (body)))

(defn write-schedule! []
  (spit "public/index.html" (build-schedule-page)))
