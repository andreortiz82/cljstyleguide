(ns cljstyleguide.sections.layout
    (:require [reagent.core :as reagent :refer [atom]]))

(defn util-display
  "Example of classes"
  []
  [:article
   [:h4 "Utility classes"]
   [:table
    [:thead
     [:tr
      [:th "Utility class"]
      [:th "Examples"]
      [:th "Description"]]]
    [:tbody
     [:tr
      [:td [:code ".flex"]]
      [:td [:code (str [:div.flex])]]
      [:td {:width "300px"} "Meant to serve as the parent container. It's children will be displayed inline."]]
     [:tr
      [:td [:code ".box"]]
      [:td [:code (str [:div.flex [:div.box]])]]
      [:td {:width "300px"} "Child of the .flex container, the .box element will be desplayed inline."]]
     [:tr
      [:td [:code ".row"]]
      [:td [:code (str [:div.row])]]
      [:td {:width "300px"} "Meant to serve as the parent container. It's children will be displayed inline."]]
     [:tr
      [:td [:code ".col-[1 - 12]"]]
      [:td [:code (str [:div.row [:div.col-12]])]]
      [:td {:width "300px"} "Serves as basic grid columns."]]]]])


(defn nested-grids
  "Example"
  [props]
  [:article
   [:h4 "Nested Grids"]
   [:div.row.bg-light.p-10
    [:div.col-3.bg-pink.p-20.white
     "Col 2"
     [:div.flex
      [:div.box.bg-purple.p-20.white "Box"]
      [:div.box.bg-purple-10.p-20.white "Box"]
      [:div.box.bg-purple-20.p-20.white "Box"]
      [:div.box.bg-purple-30.p-20.white "Box"]]]
    [:div.col-9.bg-pink-10.p-20.white
     "Col 7"
     [:div.row
      [:div.col-6.bg-teal-10.p-20.white "Col 6"]
      [:div.col-6.bg-teal-20.p-20.white "Col 6"]]]]])

(defn three-column
  "Example"
  [props]
  [:article
   [:h4 "Three Column Layout example"]
   [:div.row.bg-light.p-10
    [:div.col-2.bg-orange.p-20.white "Col 2"]
    [:div.col-7.bg-orange-10.p-20.white "Col 7"]
    [:div.col-3.bg-orange.p-20.white "Col 3"]]])

(defn two-column
  "Example"
  [props]
  [:article
   [:h4 "Two Column Layout example"]
   [:div.row.bg-light.p-10
    [:div.col-4.bg-green.p-20.white "Col 4"]
    [:div.col-8.bg-green-10.p-20.white "Col 8"]]])

(defn simple
  "Example"
  [props]
  [:article
   [:h4 "Flexbox example"]
   [:div.flex.bg-light.p-10
    [:div.box.bg-maroon.p-20.white "1"]
    [:div.box.bg-maroon-10.p-20.white "2"]
    [:div.box.bg-maroon-20.p-20.white "3"]
    [:div.box.bg-maroon-30.p-20.white "4"]
    [:div.box.bg-navy.p-20.white "5"]
    [:div.box.bg-navy-10.p-20.white "6"]
    [:div.box.bg-navy-20.p-20.white "7"]
    [:div.box.bg-navy-30.p-20.white "8"]
    [:div.box.bg-blue.p-20.white "9"]
    [:div.box.bg-blue-10.p-20.white "10"]
    [:div.box.bg-blue-20.p-20.white "11"]
    [:div.box.bg-blue-30.p-20.white "12"]]])


(defn View
  "Main view for this section"
  []
  [:section.cljstyleguide-section
   [:h1 "Layouts + Grid"]
   [:p.lead "Flexbox-powered grid, which you can use instead of the traditional float grid."]
   [:article
    (simple {})
    [:code (str (simple {}))]]
   [:article
    (two-column {})
    [:code (str (two-column {}))]]
   [:article
    (three-column {})
    [:code (str (three-column {}))]]
   [:article
    (nested-grids {})
    [:code (str (nested-grids {}))]]
   [:article
    (util-display)]])
