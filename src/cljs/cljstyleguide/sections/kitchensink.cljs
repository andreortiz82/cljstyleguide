(ns cljstyleguide.sections.kitchensink
    (:require [reagent.core :as reagent :refer [atom]]
              [re-com.core :as ui]))

(def theme-types ["primary" "defalut" "secondary" "danger" "warning" "info"])

(defn Buttons
  []
  (let [button-types theme-types]
   (fn []
    [:div.cljstyleguide__buttons
     [:h2 "Button"
      (map-indexed
       (fn [index value]
        [:div {:key index}
          [ui/button :label "Button"
                     :class (str "btn-" value)
                     :on-click #(js/alert "Clicked")
                     :tooltip (str "btn-" value)
                     :tooltip-position :right-center
                     :disabled? false
                     :style {}
                     :attr {:data-foo value}]])
       button-types)]])))

(defn KitchenSink
  []
  [:div
   [Buttons]])
