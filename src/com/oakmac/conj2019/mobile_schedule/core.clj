(ns com.oakmac.conj2019.mobile-schedule.core
  {:author "Chris Oakman <chris@oakmac.com>"
   :license "ISC"}
  (:require
    [clojure.string :as str]
    [hiccup.core :as hc]
    [hiccup.def :refer [defhtml]]
    [hiccup.page :as hp]))

;; TODO: this format would probably be easier...
; (def friday
;   {"2019-11-22 0900"
;    {:non-talk? true
;     :start-end "9:00 - 9:10"
;     :title "Welcome"}
;
;    "2019-11-22 0910"
;    {:start-end "9:10 - 9:50"
;     :speaker1 {:name "Gene Kim"
;                :title "TBD"
;                :url "https://2019.clojure-conj.org/speaker-gene-kim/"}}
;
;    "2019-11-22 1000"
;    {:start-end "10:00 - 10:40"
;     :speaker1 {:name "Chris Nuernberger"
;                :title "Extending Clojure with Python"
;                :url "https://2019.clojure-conj.org/chris-nuernberger/"}
;     :speaker2 {:name "Avi Flax"
;                 :title "(Architecture) Diagrams as Data"
;                 :url "https://2019.clojure-conj.org/speaker-avi-flax/"}}
;
;    "2019-11-22 1050"
;    {:start-end "10:50 - 11:30"
;     :speaker1 {:name "Alexander Yakushev"
;                :title "A New Age of JVM Garbage Collectors"
;                :url "https://2019.clojure-conj.org/speaker-alexander-yakushev/"}
;     :speaker2 {:name "Chris Oakman"
;                 :title "Probabilistic Record Linkage of Hospital Patients"
;                 :url "https://2019.clojure-conj.org/speaker-chris-oakman/"}}
;
;    "2019-11-22 1130"
;    {:non-talk? true
;     :start-end "11:30 - 1:00pm"
;     :title "Lunch"}})


; (def unsessions
;   {""
;    ["7:00 - 7:50PM" [{:speaker "Rui Hayashi"
;                        :title "Micro services interaction verification"
;                        :link "https://github.com/clojureconj/clojureconj2019/wiki/Unsessions#contract-based-micro-services-interaction-verification-with-clojure"
;                       {:speaker "Alan Thompson"
;                        :title "Processing Tree-like Data with Tupelo Forest"
;                        :link "https://github.com/clojureconj/clojureconj2019/wiki/Unsessions#zippers-and-lenses-and-postwalk-oh-my--processing-tree-like-data-with-tupelo-forest"}
;                       {:speaker "Room 1 Open"
;                        :link "https://github.com/clojureconj/clojureconj2019/wiki/Unsessions"}}]]
;    "b"
;    ["8:00 - 8:50PM" [{:speaker "Jason Felice"
;                        :title "Graal & Command-Line Tooling"
;                        :link "https://github.com/clojureconj/clojureconj2019/wiki/Unsessions#clojure-at-the-shell-graal--command-line-tooling"
;                        {:speaker "Jim Duey"
;                         :title "Update on Toccata"
;                         :link "https://github.com/clojureconj/clojureconj2019/wiki/Unsessions#update-on-toccata"}
;                        {:speaker "Room 1 Open"
;                         :link "https://github.com/clojureconj/clojureconj2019/wiki/Unsessions"}}]]
;
;    "a"
;    ["9:00 - 9:50PM" [{:speaker "Aaron Iba"
;                        :title "ClojureScript + React Native Apps"
;                        :link "https://github.com/clojureconj/clojureconj2019/wiki/Unsessions#clojure-at-the-shell-graal--command-line-tooling"
;                        {:speaker "Jim Duey"
;                         :title "zprint"
;                         :link "https://github.com/clojureconj/clojureconj2019/wiki/Unsessions#update-on-toccata"}
;                        {:speaker "Room 1 Open"
;                         :link "https://github.com/clojureconj/clojureconj2019/wiki/Unsessions"}}]]})


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
   ["7:00 - 10:00PM" [{:speaker "Unsessions / Lightning Talks"
                       :link "https://github.com/clojureconj/clojureconj2019/wiki/Unsessions"}]]]

  :nov-22
  [; ["9:00 - 9:10AM" [{:speaker "Welcome" :non-talk? true}]]
   {:time "2019-11-21 0900"
    :start-end "9:00 - 9:10"
    :speaker1 {:title "Welcome"
               :non-talk? true}}

   ; ["9:10 - 9:50AM" [{:speaker "Gene Kim"
   ;                    :title "TBD"
   ;                    :link}]]  "https://2019.clojure-conj.org/speaker-gene-kim/"
   {:time "2019-11-21 0910"
    :start-end "9:10 - 9:50"
    :speaker1 {:speaker "Gene Kim"
               :title "TBD"
               :link "https://2019.clojure-conj.org/speaker-gene-kim/"}}

   ; ["10:00 - 10:40AM" [{:speaker "Chris Nuernberger"
   ;                      :title "Extending Clojure with Python"
   ;                      :link "https://2019.clojure-conj.org/chris-nuernberger/"}
   ;                     {:speaker "Avi Flax"
   ;                      :title "(Architecture) Diagrams as Data"
   ;                      :link "https://2019.clojure-conj.org/speaker-avi-flax/"}]]
   {:time "2019-11-21 1000"
    :start-end "10:00 - 10:40"
    :speaker1 {:speaker "Chris Nuernberger"
               :title "Extending Clojure with Python"
               :link "https://2019.clojure-conj.org/chris-nuernberger/"}
    :speaker2 {:speaker "Avi Flax"
               :title "(Architecture) Diagrams as Data"
               :link "https://2019.clojure-conj.org/speaker-avi-flax/"}}


   ; ["10:50 - 11:30AM" [{:speaker "Alexander Yakushev"
   ;                      :title "A New Age of JVM Garbage Collectors"
   ;                      :link "https://2019.clojure-conj.org/speaker-alexander-yakushev/"}
   ;                     {:speaker "Chris Oakman"
   ;                      :title "Probabilistic Record Linkage of Hospital Patients"
   ;                      :link "https://2019.clojure-conj.org/speaker-chris-oakman/"}]]
   {:time "2019-11-21 1050"
    :start-end "10:50 - 11:30"
    :speaker1 {:speaker "Alexander Yakushev"
               :title "A New Age of JVM Garbage Collectors"
               :link "https://2019.clojure-conj.org/speaker-alexander-yakushev/"}
    :speaker2 {:speaker "Chris Oakman"
                :title "Probabilistic Record Linkage of Hospital Patients"
                :link "https://2019.clojure-conj.org/speaker-chris-oakman/"}}

   ; ["11:30 - 1:00PM" [{:speaker "Lunch" :title ""}]]
   {:time "2019-11-21 1130"
    :start-end "11:30 - 1:00"
    :speaker1 {:speaker "Lunch"
               :non-talk? true}}

   ; ["1:00 - 1:40PM" [{:speaker "Pier Federico Gherardini & Ben Kamphaus"
   ;                    :title "Clojure Where it Counts: Tidying Data Science Workflows"
   ;                    :link "https://2019.clojure-conj.org/speaker-pier-federico-gherardini/"}
   ;                   {:speaker "Wilker Silva"
   ;                    :title "The Maximal Graph"
   ;                    :link "https://2019.clojure-conj.org/speaker-wilker-silva/"}]]
   {:time "2019-11-21 1300"
    :start-end "1:00 - 1:40"
    :speaker1 {:speaker "Pier Federico Gherardini & Ben Kamphaus"
               :title "Clojure Where it Counts: Tidying Data Science Workflows"
               :link "https://2019.clojure-conj.org/speaker-pier-federico-gherardini/"}
    :speaker2 {:speaker "Wilker Silva"
                :title "The Maximal Graph"
                :link "https://2019.clojure-conj.org/speaker-wilker-silva/"}}

   ; ["1:50 - 2:30PM" [{:speaker "Dennis Heihoff"
   ;                    :title "The Embodied Runtime"
   ;                    :link "https://2019.clojure-conj.org/speaker-dennis-heihoff/"}
   ;                   {:speaker "Eno Compton & Tyler van Hensbergen"
   ;                    :title "Goodbye YAML: Infrastructure as Code in Clojure"
   ;                    :link "https://2019.clojure-conj.org/speaker-eno-compton/"}]]
   {:time "2019-11-21 1350"
    :start-end "1:50 - 2:30PM"
    :speaker1 {:speaker "Dennis Heihoff"
               :title "The Embodied Runtime"
               :link "https://2019.clojure-conj.org/speaker-dennis-heihoff/"}
    :speaker2 {:speaker "Eno Compton & Tyler van Hensbergen"
               :title "Goodbye YAML: Infrastructure as Code in Clojure"
               :link "https://2019.clojure-conj.org/speaker-eno-compton/"}}

   ["2:40 - 3:20PM" [{:speaker "Tom Toor"
                      :title "Why Build Solutions with Fulcro"
                      :link "https://2019.clojure-conj.org/speaker-tom-toor"}
                     {:speaker "Scarlet Spectacular"
                      :title "Ghost in the Generative Shell"
                      :link "https://2019.clojure-conj.org/speaker-scarlet-spectacular/"}]]

   ["3:20 - 3:50PM" [{:speaker "Break" :title "" :non-talk? true}]]

   ["3:50 - 4:30PM" [{:speaker "Thomas Gebert & Nick Misturak"
                      :title "Distributed Hash Tables, Video, and Fun!"
                      :link "https://2019.clojure-conj.org/speaker-thomas-gebert/"}]]

   ["4:40 - 5:30PM" [{:speaker "Matthew Flatt"
                      :title "A Racket Perspective on Research, Education, and Production"
                      :link "https://2019.clojure-conj.org/keynote-speaker-matthew-flatt/"}]]
   ["7:00 - 10:00PM" [{:speaker "Party at Boxcar Arcade" :non-talk? true
                       :link "https://goo.gl/maps/WQafmxzZZjYRNqtb8"}]]]

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
  [:div (when non-talk? {:style "font-style: italic; text-align: center;"})
   [:div (if link
           [:a {:href link} speaker]
           speaker)]
   [:div {:style "font-size: 14px"} title]])

(defn- build-search-txt [speakers]
  (let [first-speaker (first speakers)
        second-speaker (second speakers)]
    (-> (str (str/lower-case (:speaker first-speaker ""))
             (str/lower-case (:title first-speaker ""))
             (str/lower-case (:speaker second-speaker ""))
             (str/lower-case (:title second-speaker "")))
        (str/replace " " ""))))

(defn- extract-time [time-string]
  (-> (str/split time-string #" ")
      first))

(defhtml table-row [[time speakers]]
  (let [two-tracks? (nth speakers 1 false)]
    [:tr {:class "searchable"
          :data-searchtxt (build-search-txt speakers)}
          ; :data-time (extract-time time)}
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

; (defhtml unsessions-thead []
;   [:thead
;     [:tr
;       [:th]
;       [:th "Ballroom A-B"]
;       [:th "Ballroom C-D"]]])
;
; (defn unsessions-tbody [items]
;   [:tbody
;    (map table-row items)])
;
; (defhtml unsessions-table [schedule-items]
;   [:table.table.is-striped.is-narrow.is-fullwidth.is-bordered
;     (unsessions-thead)
;     (unsessions-tbody schedule-items)])

(def official-schedule-url "https://2019.clojure-conj.org/schedule/")
(def speakers-list-url "https://2019.clojure-conj.org/speakers/")

(def zepto-cdn-url "https://cdnjs.cloudflare.com/ajax/libs/zepto/1.2.0/zepto.min.js")
(def zepto-integrity-hash "sha256-vrn14y7WH7zgEElyQqm2uCGSQrX/xjYDjniRUQx3NyU=")

(def moment-cdn-url "https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js")
(def moment-integrity-hash "sha256-4iQZ6BVL4qNKlQ27TExEhBN1HFPvAvAMbFavKKosSWQ=")

(defhtml clientside-js []
  [:script {:src zepto-cdn-url :integrity zepto-integrity-hash :crossorigin "anonymous"}]
  [:script {:src moment-cdn-url :integrity moment-integrity-hash :crossorigin "anonymous"}]
  [:script {:src "index.js"}])

(defhtml toolbar []
  [:div {:style "display: flex; margin-top: -10px; margin-bottom: 20px;"}
    [:div {:style "flex: 2;"}
      [:input#searchBar.input {:type "text" :placeholder "Search …"}]]])
    ; [:div {:style "flex: 1; margin-left: 10px"}
    ;   [:button#hidePastEventsBtn.button.is-secondary {:style "display:none"} "Hide Past Events"]
    ;   [:button#showPastEventsBtn.button.is-secondary "Show Past Events"]]])

(defhtml no-search-results-msg []
  [:div#noSearchResultsMsg.notification {:style "display: none;"}
    "No search results :-("])

(defhtml day-section [section-id day-title schedule]
  [:div.day-section {:id section-id
                     :style "margin-bottom: 20px"}
    [:h4.title.is-6 day-title]
    (schedule-table schedule)])

; (defhtml unsessions-section [day-title schedule]
;   [:div.day-section {:style "margin-bottom: 20px"}
;     [:h4.title.is-6 day-title]
;     (unsessions-table schedule)])

(defhtml body []
  [:body
    [:section.section {:style "padding: 1rem 0.5rem"}
      [:div.container
        [:h1.title.is-4 "2019 Clojure/conj Schedule"]
        [:h5.subtitle.is-6
          [:a {:href official-schedule-url} "Official Schedule"]
          [:span {:style "display: inline-block; margin: 0 10px;"} "—"]
          [:a {:href speakers-list-url} "Speakers List"]]
        (toolbar)
        (no-search-results-msg)
        ; (day-section "Wednesday - Nov 20, 2019" (:nov-20 schedule))
        (day-section "thuSection" "Thursday - Nov 21, 2019" (:nov-21 schedule))
        ; (unsessions-section "Thursday Evening - Unsessions" (:unsessions schedule))
        (day-section "friSection" "Friday - Nov 22, 2019" (:nov-22 schedule))
        (day-section "satSection" "Saturday - Nov 23, 2019" (:nov-23 schedule))]]
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
