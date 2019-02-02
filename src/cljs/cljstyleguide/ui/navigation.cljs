(ns cljstyleguide.ui.navigation
    (:require
     [reagent.core :as reagent :refer [atom]]
     [reitit.frontend :as reitit]))


(defn Menu
  [path-for]
  [:aside#styleguide-navigation
    [:ul
     [:li [:a {:href (path-for :index)} "Home"]]
     [:li [:a {:href (path-for :about)} "About"]]
     [:li [:a {:href (path-for :colors)} "Colors"]]
     [:li [:a {:href (path-for :typography)} "Typography"]]
     [:li [:a {:href (path-for :layout)} "Layout"]]
     [:li [:a {:href (path-for :tables)} "Tables"]]
     [:li [:a {:href (path-for :lists)} "Lists"]]
     [:li [:a {:href (path-for :forms)} "Forms"]]
     [:li [:a {:href (path-for :slats)} "Slats"]]
     [:li [:a {:href (path-for :panels)} "Panels"]]
     [:li [:a {:href (path-for :notifications)} "Notifications"]]
     [:li [:a {:href (path-for :navigation)} "Navigation"]]
     [:li [:a {:href (path-for :modals)} "Modals"]]
     [:li [:a {:href (path-for :data)} "Data"]]]])
