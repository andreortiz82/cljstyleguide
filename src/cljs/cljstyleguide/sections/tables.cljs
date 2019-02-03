(ns cljstyleguide.sections.tables
    (:require
      [reagent.core :as reagent :refer [atom]]
      [talltale.core :as fake]
      [cljstyleguide.state.app-state :as state]))

(def tableDisabled (reagent/atom false))
(def pageYOffset (reagent/atom 0))
(def active-row (reagent/atom 0))
(def active-cell (reagent/atom 0))
(def active-cell-data (reagent/atom nil))

(defn change-active-cell
  [{:keys [tri tdi] :as coordinates}]
  (let [table-row (aget (js/document.getElementsByTagName "tr") @active-row)
        table-cell (aget (.-childNodes table-row) @active-cell)]
    (js/console.log {:active-row @active-row
                     :active-cell @active-cell})
    (try
      (.click table-cell)
      (catch js/Object e
        (js/console.log "oob")))))

(defn inline-cell-form
  [{:keys [data key x y]}]
  (let [value (key data)]
    [:div.edit-cell-widget {:key (:id data) :style {:top y :left x}}
     [:div.field
      [:input {:autoFocus true :type "text" :defaultValue value}]]]))

(defn dismiss-cell-focus []
  (reset! tableDisabled false)
  (reset! active-cell-data nil)
  (-> js/document
    (.-body)
    (.classList.remove "no-scroll")))

(defn keydown-handler [event]
  (let [keyCode (.-keyCode event)]
    (case keyCode
      ; 13: Enter Key
      13 (dismiss-cell-focus)
      37 (do
           (swap! active-cell dec)
           (change-active-cell nil))
      38 (do
           (swap! active-row dec)
           (change-active-cell nil))
      39 (do
           (swap! active-cell inc)
           (change-active-cell nil))
      40 (do
           (swap! active-row inc)
           (change-active-cell nil))
      nil)))

(defn cell-click-handler
  [event row cell tr-index td-index]
  (let [position (-> event .-target .getBoundingClientRect)]
    (reset! tableDisabled true)
    (reset! tableDisabled true)
    (reset! active-cell-data {:data row
                              :key cell
                              :x (.-left position)
                              :y (.-top position)})
    ; (change-active-cell {:tri tr-index :tdi td-index})
    (-> js/document
      (.-body)
      (.classList.add "no-scroll"))))

(defn advanced-table
  [collection]
  (let [topPos 170 ; Make dynamic
        currentPos (- @pageYOffset topPos)]
    [:article.table-container {:role "table-component"}
     (when @tableDisabled
       [:div.table-active-overlay {:on-click dismiss-cell-focus}
        (inline-cell-form @active-cell-data)])
     [:table.advanced
      [:thead {:class (when (> currentPos topPos) "sticky") :style {:top currentPos}}
       [:tr
        (map-indexed (fn [index th]
                      [:th {:key index} (str th)])
                     (keys (first collection)))]]
      [:tbody
       (map-indexed (fn [tr-index tr]
                      [:tr {:key (str "tr-" tr-index)}
                       (map-indexed (fn [td-index td]
                                      [:td {:key (str "td-" td-index)
                                            :on-click #(cell-click-handler % tr td tr-index td-index)}
                                       (str (td tr))])
                                    (keys tr))])
                    collection)]]]))

(defn View
  "Main view for this section"
  []
  (reagent/create-class {
                         :component-will-unmount (fn []
                                                  (.removeEventListener js/window "keydown" #(keydown-handler %) false)
                                                  (.removeEventListener js/window "scroll" #(reset! pageYOffset (.-scrollY js/window)) true))
                         :component-did-mount (fn []
                                                (.addEventListener js/window "keydown" #(keydown-handler %) false)
                                                (.addEventListener js/window "scroll" #(reset! pageYOffset (.-scrollY js/window)) true)
                                                (state/api "patients")
                                                (change-active-cell nil))

                         :render (fn []
                                  [:section.cljstyleguide-section
                                   [:h1 "Tables"]
                                   [:p.lead "Tables get the job done (for tabular data, of course)."]
                                   [:article
                                    (advanced-table (:patients @state/patients))]])}))
