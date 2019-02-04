(ns cljstyleguide.sections.tables
    (:require
      [reagent.core :as reagent :refer [atom]]
      [talltale.core :as fake]
      [cljstyleguide.components.advanced-table :as advanced-table]
      [cljstyleguide.state.app-state :as state]))

(defn View
  "Main view for this section"
  []
  (reagent/create-class {
                         :component-did-mount (fn []
                                                (state/api "patients"))
                         :render (fn []
                                  [:section.cljstyleguide-section
                                   [:h1 "Tables"]
                                   [:p.lead "Tables get the job done (for tabular data, of course)."]
                                   [:article
                                    (advanced-table/MatrixTable (:patients @state/patients))]])}))
