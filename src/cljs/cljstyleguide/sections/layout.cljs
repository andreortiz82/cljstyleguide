(ns cljstyleguide.sections.layout
    (:require [reagent.core :as reagent :refer [atom]]))

(defn View
  "Main view for this section"
  []
  [:section.cljstyleguide-section
   [:h1 "Layouts + Grid"]
   [:p.lead "Flexbox-powered grid, which you can use instead of the traditional float grid."]])
