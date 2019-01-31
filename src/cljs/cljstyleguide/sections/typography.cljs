(ns cljstyleguide.sections.typography
    (:require
      [reagent.core :as reagent :refer [atom]]
      [talltale.core :as fake]))

(defn headings []
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
     [:tr
      [:td [:h1 "h1: Main heading"]]
      [:td [:h1 "36"]]
      [:td [:h1 "400"]]
      [:td
       [:code (str "[:h1 \"value\"]")]]]
     [:tr
      [:td [:h2 "h2: Subpage title"]]
      [:td [:h2 "32"]]
      [:td [:h2 "400"]]
      [:td
       [:code (str "[:h2 \"value\"]")]]]
     [:tr
      [:td [:h3 "h3: Section header"]]
      [:td [:h3 "26"]]
      [:td [:h3 "400"]]
      [:td
       [:code (str "[:h3 \"value\"]")]]]
     [:tr
      [:td [:h4 "h4: Article heading"]]
      [:td [:h4 "22"]]
      [:td [:h4 "400"]]
      [:td
       [:code (str "[:h4 \"value\"]")]]]
     [:tr
      [:td [:h5 "h5: Lead heading"]]
      [:td [:h5 "18"]]
      [:td [:h5 "400"]]
      [:td
       [:code (str "[:h5 \"value\"]")]]]]]])

(defn paragraphs []
  [:article
    [:div
     [:h2 "Paragraphs"]
     [:h4 "Lead"]
     [:p.lead (fake/text)]
     [:code (str "[:p.lead \"value\"]")]]
    [:div
     [:h4 "Normal"]
     [:p (fake/text)]
     [:code (str "[:p \"value\"]")]]
    [:div
     [:h4 "Subtle"]
     [:p.subtle (fake/text)]
     [:code (str "[:p.subtle \"value\"]")]]])

(defn links []
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

(defn text-formatting []
  [:article
   [:table
    [:thead
     [:tr
      [:th "Format"]
      [:th "Utility Class"]]]
    [:tbody
     [:tr
      [:td [:span.bold "I am bold"]]
      [:td
       [:code ".bold"]]]
     [:tr
      [:td [:span.italic "I am italic"]]
      [:td
       [:code ".italic"]]]
     [:tr
      [:td [:span.uppercase "I am uppercase"]]
      [:td
       [:code ".uppercase"]]]
     [:tr
      [:td [:span.downcase "I am downcase"]]
      [:td
       [:code ".downcase"]]]
     [:tr
      [:td [:span.underline "I am underlined"]]
      [:td
       [:code ".underline"]]]
     [:tr
      [:td [:span.capitalize "I am capitalized"]]
      [:td
       [:code ".capitalize"]]]
     [:tr
      [:td [:span.strike "I am striked"]]
      [:td
       [:code ".strike"]]]]]])

(defn lists []
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

(defn labels-badges []
  [:article
   [:table
    [:thead
     [:tr
      [:th "Badge"]
      [:th "Example"]
      [:th "Code"]]]
    [:tbody
     [:tr
      [:td "Default"]
      [:td
       [:span.badge.mr-10.inline-block 3]
       [:span.badge.mr-10.inline-block "default"]]
      [:td
       [:code
        (str [:span.badge])]]]
     [:tr
      [:td "Primary"]
      [:td
       [:span.badge.bg-primary.mr-10.inline-block 3]
       [:span.badge.bg-primary.mr-10.inline-block "default"]]
      [:td
       [:code
        (str [:span.badge.bg-primary])]]]
     [:tr
      [:td "Success"]
      [:td
       [:span.badge.bg-success.mr-10.inline-block 3]
       [:span.badge.bg-success.mr-10.inline-block "default"]]
      [:td
       [:code
        (str [:span.badge.bg-success])]]]
     [:tr
      [:td "Warning"]
      [:td
       [:span.badge.bg-warning.mr-10.inline-block 3]
       [:span.badge.bg-warning.mr-10.inline-block "default"]]
      [:td
       [:code
        (str [:span.badge.bg-warning])]]]
     [:tr
      [:td "Info"]
      [:td
       [:span.badge.bg-info.mr-10.inline-block 3]
       [:span.badge.bg-info.mr-10.inline-block "default"]]
      [:td
       [:code
        (str [:span.badge.bg-info])]]]
     [:tr
      [:td "Error"]
      [:td
       [:span.badge.bg-error.mr-10.inline-block 3]
       [:span.badge.bg-error.mr-10.inline-block "default"]]
      [:td
       [:code
        (str [:span.badge.bg-error])]]]]]])

(defn quotes [])

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
