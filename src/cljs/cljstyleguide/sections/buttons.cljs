(ns cljstyleguide.sections.buttons
    (:require
      [reagent.core :as reagent :refer [atom]]
      [re-com.core :as ui]))

(defn View
  "Main view for this section"
  []
  [:section.cljstyleguide-section
   [:h1 "Buttons"]
   [ui/button :label "Button"
              :class ""
              :on-click #(js/alert "Clicked")
              :tooltip "Yup!"
              :tooltip-position :above-center
              :disabled? false
              :style {}
              :attr {:data-foo "bar"}]])
