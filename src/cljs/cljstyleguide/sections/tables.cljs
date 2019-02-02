(ns cljstyleguide.sections.tables
    (:require [reagent.core :as reagent :refer [atom]]))

(defn View
  "Main view for this section"
  []
  [:section.cljstyleguide-section
   [:h1 "Tables"]
   [:p.lead "Tables get the job done (for tabular data, of course)."]])
