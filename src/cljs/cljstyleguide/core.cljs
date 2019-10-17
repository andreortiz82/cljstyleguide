(ns cljstyleguide.core
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [reitit.frontend :as reitit]
              [clerk.core :as clerk]
              [accountant.core :as accountant]
              [cljstyleguide.sections.colors :as colors]
              [cljstyleguide.sections.typography :as typography]
              [cljstyleguide.sections.buttons :as buttons]
              [cljstyleguide.sections.layout :as layout]
              [cljstyleguide.sections.lists :as lists]
              [cljstyleguide.sections.data :as data]
              [cljstyleguide.sections.forms :as forms]
              [cljstyleguide.sections.navigation :as navigation]
              [cljstyleguide.sections.notifications :as notifications]
              [cljstyleguide.sections.panels :as panels]
              [cljstyleguide.sections.tables :as tables]
              [cljstyleguide.sections.modals :as modals]
              [cljstyleguide.sections.slats :as slats]
              [cljstyleguide.sections.kitchensink :refer [KitchenSink]]
              [cljstyleguide.ui.navigation :as menu]))

;; -------------------------
;; Routes

(def router
  (reitit/router
   [["/" :index]
    ["/about" :about]
    ["/colors" :colors]
    ["/typography" :typography]
    ["/layout" :layout]
    ["/buttons" :buttons]
    ["/forms" :forms]
    ["/tables" :tables]
    ["/lists" :lists]
    ["/slats" :slats]
    ["/navigation" :navigation]
    ["/panels" :panels]
    ["/notifications" :notifications]
    ["/data" :data]
    ["/modals" :modals]]))

(defn path-for [route & [params]]
  (if params
    (:path (reitit/match-by-name router route params))
    (:path (reitit/match-by-name router route))))

(path-for :about)
;; -------------------------
;; Page components

(defn home-page []
  (fn []
    [:section.cljstyleguide-section
     [:h1 "Welcome to cljstyleguide"]
     [:p.lead "A styleguide for CLJS projects. More details soon..."]
     [KitchenSink]]))


(defn about-page []
  (fn []
    [:section.cljstyleguide-section
     [:h1 "About cljstyleguide"]
     [:p "details soon..."]]))

;; -------------------------
;; Translate routes -> page components

(defn page-for [route]
  (case route
    :index #'home-page
    :about #'about-page
    :buttons #'buttons/View
    :colors #'colors/View
    :typography #'typography/View
    :layout #'layout/View
    :forms #'forms/View
    :tables #'tables/View
    :lists #'lists/View
    :slats #'slats/View
    :navigation #'navigation/View
    :notifications #'notifications/View
    :panels #'panels/View
    :data #'data/View
    :modals #'modals/View))

;; -------------------------
;; Page mounting component

(defn current-page []
  (fn []
    (let [page (:current-page (session/get :route))]
      [:div#application-wrapper
       (menu/Menu path-for)
       [:div#styleguide-content
        [page]]
       [:footer]])))


;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (clerk/initialize!)
  (accountant/configure-navigation!
   {:nav-handler
    (fn [path]
      (let [match (reitit/match-by-path router path)
            current-page (:name (:data  match))
            route-params (:path-params match)]
        (reagent/after-render clerk/after-render!)
        (session/put! :route {:current-page (page-for current-page)
                              :route-params route-params})
        (clerk/navigate-page! path)))

    :path-exists?
    (fn [path]
      (boolean (reitit/match-by-path router path)))})
  (accountant/dispatch-current!)
  (mount-root))
