(ns cljstyleguide.sections.lists
    (:require
     [reagent.core :as reagent :refer [atom]]
     [cljstyleguide.state.app-state :as state]))


(state/api "visits")

(def active-item (reagent/atom nil))

(defn basic-list
  [data]
  (let [collection (take 10 data)]
    [:div.list
     (map-indexed (fn [index item]
                    [:div.list-item {:key index}
                      (str (:patient-name item))])
                  collection)]))

(defn split-list
  [data]
  (let [collection (take 10 data)]
    [:div.split-list-container
     [:div.list
       (doall
        (map-indexed (fn [index item]
                      [:div.list-item {:key index
                                       :class (when (= item @active-item) "active")
                                       :on-click #(reset! active-item item)}
                        (str (:patient-name item))])
                     collection))]
     [:div.details {:key (:id @active-item)}
      [:div.content
        [:code active-item]]]]))

(defn View
  "Main view for this section"
  []
  [:section.cljstyleguide-section
   [:h1 "Lists"]
   [:p.lead "The color system can be used to create a color theme that reflects your brand or style."]
   [:h2 "Basic list"]
   (basic-list (:visits @state/visits))
   [:h2 "Split list"]
   (split-list (:visits @state/visits))])
