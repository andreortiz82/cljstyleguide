(ns cljstyleguide.sections.tables
    (:require
      [reagent.core :as reagent :refer [atom]]
      [talltale.core :as fake]
      [cljstyleguide.state.app-state :as state]))

(def tableDisabled (reagent/atom false))
(def pageYOffset (reagent/atom 0))

(defn dismiss-cell-focus []
  (reset! tableDisabled false)
  (-> js/document
    (.-body)
    (.classList.remove "no-scroll"))
  (js/console.log @tableDisabled))

(defn cell-click-handler
  [event row cell]
  (reset! tableDisabled true)
  (-> js/document
    (.-body)
    (.classList.add "no-scroll"))
  (js/console.log (str "Value: " (cell row))))

(defn simple-table
  [collection]
  (let [topPos 170 ; Make dynamic
        currentPos (- @pageYOffset topPos)]
    [:article.table-container
     (when @tableDisabled
       [:div.table-active-overlay {:on-click dismiss-cell-focus}])
     [:table.advanced
      [:thead {:class (when (> currentPos topPos) "sticky") :style {:top currentPos}}
       [:tr
        (map-indexed (fn [index th]
                      [:th {:key index} (str th)])
                     (keys (first collection)))]]
      [:tbody
       (map-indexed (fn [index tr]
                      [:tr {:key (:id tr)}
                       (map-indexed (fn [idx td]
                                      [:td {:key idx
                                            :on-click #(cell-click-handler % tr td)}

                                       (str (td tr))])
                                    (keys tr))])
                    collection)]]]))

(defn View
  "Main view for this section"
  []
  (reagent/create-class {
                         :component-will-unmount (fn []
                                                  (.removeEventListener js/window "scroll" #(reset! pageYOffset (.-scrollY js/window)) false))
                         :component-did-mount (fn []
                                                (.addEventListener js/window "scroll" #(reset! pageYOffset (.-scrollY js/window)) false)
                                                (state/api "patients"))

                         :render (fn []
                                  (js/console.log)
                                  [:section.cljstyleguide-section
                                   [:h1 "Tables"]
                                   [:p.lead "Tables get the job done (for tabular data, of course)."]
                                   [:article
                                    (simple-table (:patients @state/patients))]])}))
