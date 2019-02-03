(ns cljstyleguide.sections.tables
    (:require
      [reagent.core :as reagent :refer [atom]]
      [talltale.core :as fake]
      [cljstyleguide.state.app-state :as state]))

(def tableDisabled (reagent/atom false))
(def pageYOffset (reagent/atom 0))
(def active-cell-data (reagent/atom nil))

(defn dismiss-cell-focus []
  (reset! tableDisabled false)
  (-> js/document
    (.-body)
    (.classList.remove "no-scroll"))
  (js/console.log @tableDisabled))

(defn cell-click-handler
  [event row cell]
  (let [xPos (-> event .-clientX)
        yPos (-> event .-clientY)]
    (reset! tableDisabled true)
    (reset! active-cell-data {:data row
                              :key cell
                              :x xPos
                              :y yPos})
    (-> js/document
      (.-body)
      (.classList.add "no-scroll"))))

(defn inline-cell-form
  [{:keys [data key x y]}]
  (let [value (key data)]
    [:div.edit-cell-widget {:on-key-press dismiss-cell-focus
                            :style {:top y :left x}}
     [:div.field
      [:input {:autoFocus true :type "text" :defaultValue value}]]
     (js/console.log data key x y)]))

(defn simple-table
  [collection]
  (let [topPos 170 ; Make dynamic
        currentPos (- @pageYOffset topPos)]
    [:article.table-container {:role "table-component"}
     (when @tableDisabled
       [:div.table-active-overlay
        (inline-cell-form @active-cell-data)])
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
