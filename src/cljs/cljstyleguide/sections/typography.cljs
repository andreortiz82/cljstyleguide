(ns cljstyleguide.sections.typography
    (:require
      [reagent.core :as reagent :refer [atom]]
      [clojure.string :as str]
      [talltale.core :as fake]))

(defn headings
  "Example sections"
  []
  (let [headings-collection [{:type "h1" :size "36" :weight "400" :dom [:h1 (fake/city)]}
                             {:type "h2" :size "32" :weight "400" :dom [:h2 (fake/city)]}
                             {:type "h3" :size "26" :weight "400" :dom [:h3 (fake/city)]}
                             {:type "h4" :size "22" :weight "400" :dom [:h4 (fake/city)]}
                             {:type "h5" :size "18" :weight "400" :dom [:h5 (fake/city)]}]]
    [:article
     [:h2 "Headings"]
     [:table
      [:thead
       [:tr
        [:th "Selector"]
        [:th "Size (using rem() function)"]
        [:th "Font Weight"]
        [:th "Example"]]]
      [:tbody
       (map-indexed (fn [index heading]
                      [:tr {:key index}
                       [:td (:dom heading)]
                       [:td (:size heading)]
                       [:td (:weight heading)]
                       [:td
                        [:code (str (:dom heading))]]])
                    headings-collection)]]]))



(defn paragraphs
  "Example sections"
  []
  (let [paragraph-collection ["lead" "" "subtle"]]
    [:article
      [:h2 "Paragraphs"]
      (map-indexed (fn [index type]
                     [:div {:key index}
                       [:h3 (str/capitalize type)]
                       [:p {:class type} (fake/text)]
                       [:code (str [:p {:class type} "text here"])]])
                   paragraph-collection)]))

(defn links
  "Example sections"
  []
  [:article
   [:table
    [:thead
     [:tr
      [:th "Link"]
      [:th "Example"]]]
    [:tbody
     [:tr
      [:td [:a {:href "#"} "I am a link"]]
      [:td
       [:code (str "[:a {:href <PATH>} \"value\"]")]]]]]])

(defn text-formatting
  "Example sections"
  []
  (let [formatting-collection ["bold" "italic" "uppercase" "downcase" "underline" "capitalize" "strike"]]
    [:article
     [:table
      [:thead
       [:tr
        [:th "Format"]
        [:th "Utility Class"]]]
      [:tbody
       (map-indexed (fn [index type]
                      [:tr {:key index}
                       [:td [:span {:class type} (str "I am " type)]]
                       [:td
                        [:code (str "." type)]]])
                    formatting-collection)]]]))


(defn lists
  "Example sections"
  []
  [:article
   [:table
    [:thead
     [:tr
      [:th "List type"]
      [:th "Example"]
      [:th {:width "350px" } "Code"]]]
    [:tbody
     [:tr
      [:td "Unordered list"]
      [:td
       [:ul
        (map (fn [index]
               [:li {:key index} (str "List item: " index)])
             (range 5))
        [:li "Nested list"
         [:ul
          (map (fn [index]
                 [:li {:key index} (str "List item: " index)])
               (range 5))]]]]
      [:td
       [:code
        (str
         [:ul
          (map (fn [index]
                 [:li {:key index} (str "List item: " index)])
               (range 5))])]]]
     [:tr
      [:td "Ordered list"]
      [:td
       [:ol
        (map (fn [index]
               [:li {:key index} (str "List item")])
             (range 5))
        [:li "Nested list"
         [:ol
          (map (fn [index]
                 [:li {:key index} (str "List item")])
               (range 5))]]]]
      [:td
       [:code
        (str
         [:ol
          (map (fn [index]
                 [:li {:key index} (str "List item: " index)])
               (range 5))])]]]]]])

(defn labels-badges
  "Example sections"
  []
  (let [badge-collection ["default" "dark" "red" "orange" "yellow" "green" "blue" "pink" "purple"]]
    [:article
     [:table
      [:thead
       [:tr
        [:th "Badge"]
        [:th "Example"]
        [:th "Code"]]]
      [:tbody
       (map-indexed (fn [badge-index badge-type]
                      [:tr {:key badge-index}
                       [:td (str/capitalize badge-type)]
                       [:td
                        (map (fn [index]
                               [:span {:key index}
                                [:span.badge.mr-10 {:class badge-type} (fake/city)]])
                             (range 5))]
                       [:td
                        [:code
                         (str [:span.badge {:class badge-type} (fake/city)])]]])

                    badge-collection)]]]))

(defn quotes
  "Example sections"
  []
  (let [quotes-collection ["lead" "" "subtle"]]
    [:article
      [:h2 "Blockquotes"]
      (map-indexed (fn [index type]
                     [:div {:key index}
                       [:h3 (str/capitalize type)]
                       [:blockquote {:class type}
                        (fake/text)
                        [:cite (fake/username)]]
                       [:code (str [:blockquote {:class type}
                                    "Tacos are delicious!"
                                    [:cite "Everyone"]])]])
                   quotes-collection)]))

(defn View
  "Main view for this section"
  []
  [:section.cljstyleguide-section
   [:h1 "Typography"]
   [:p.lead "Meant to make your life easier by providing clean, attractive, simple default styles for all of the most basic typographical elements."]
   (headings)
   (paragraphs)
   (lists)
   (labels-badges)
   (links)
   (text-formatting)
   (quotes)])
