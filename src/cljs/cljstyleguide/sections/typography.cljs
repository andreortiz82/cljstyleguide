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
      [:th "Rem"]
      [:th "Weight"]
      [:th (str "[:h1 'value']")]]]
    [:tbody
     [:tr
      [:td [:h1 "Main heading"]]
      [:td [:h1 "36"]]
      [:td [:h1 "700"]]]
     [:tr
      [:td [:h2 "Subpage title"]]
      [:td [:h2 "32"]]
      [:td [:h3 "700"]]]
     [:tr
      [:td [:h3 "Section header"]]
      [:td [:h3 "28"]]
      [:td [:h3 "700"]]]
     [:tr
      [:td [:h4 "Bold heading"]]
      [:td [:h4 "24"]]
      [:td [:h4 "700"]]]
     [:tr
      [:td [:h5 "Lead heading"]]
      [:td [:h5 "18"]]
      [:td [:h5 "700"]]]]]])

(def fake-notification-text (take 90 ))
(defn paragraphs []
  [:article
   [:h2 "Paragraphs"]
   [:h4 "Lead"]
   [:p.lead (fake/text)]
   [:h4 "Normal"]
   [:p (fake/text)]
   [:h4 "Subtle"]
   [:p.subtle (fake/text)]])


(defn View
  "Main view for this section"
  []
  [:section
   [:h1 "Typography"]
   (headings)
   (paragraphs )])
