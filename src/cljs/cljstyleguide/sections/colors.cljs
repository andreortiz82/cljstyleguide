(ns cljstyleguide.sections.colors
    (:require [reagent.core :as reagent :refer [atom]]))

(def semantic-collection ["brand"
                          "primary"
                          "secondary"
                          "success"
                          "info"
                          "warning"
                          "error"])

(def grey-scale-collection ["white"
                            "light"
                            "grey"
                            "dark"
                            "almost-black"
                            "black"])

(def color-collection ["red"
                       "orange"
                       "yellow"
                       "green"
                       "blue"
                       "pink"
                       "purple"
                       "navy"
                       "aqua"
                       "teal"
                       "maroon"
                       "lime"
                       "olive"])

(defn swatch
  "Renders a single swatch"
  [key color]
  [:div.swatch {:key key}
   [:div.display {:class (str "bg-" color)}]])


(defn swatch-group
  "Renders a group of swatches"
  [collection]
  [:table.swatch-group
   [:thead
    [:tr
     [:th "Example"]
     [:th "Sass"]
     [:th "Color Steps"]
     [:th "Utils"]]]
   [:tbody
     (map-indexed (fn [index color]
                    [:tr {:key index}
                     [:td {:width "200px"}
                      [:div.swatch {:class (str "bg-" color)} color]
                      [:div.swatch {:class (str "bg-" color "-10")} (str color "-10")]
                      [:div.swatch {:class (str "bg-" color "-20")} (str color "-20")]
                      [:div.swatch {:class (str "bg-" color "-30")} (str color "-30")]]
                     [:td [:code (str "$" color)]]
                     [:td
                      [:code (str "[10 20 30]")]]
                     [:td {:width "400px"}
                      [:code (str "." color)]
                      [:code (str ".bg-" color)]
                      [:code (str ".fill-" color)]
                      [:code (str ".bdr-" color)]
                      [:code (str ".[bg fill bdr]-" color "-[10 20 30]")]]])
                 collection)]])

(defn View
  "Main view for this section"
  []
  [:section.cljstyleguide-section
   [:h1 "Colors Collections"]
   [:p.lead "The color system can be used to create a color theme that reflects your brand or style."]
   [:h2 "Grey Scale"]
   (swatch-group grey-scale-collection)

   [:h2 "Colors"]
   (swatch-group color-collection)

   [:h2 "Semantics"]
   (swatch-group semantic-collection)])
