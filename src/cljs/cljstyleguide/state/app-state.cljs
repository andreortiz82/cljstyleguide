(ns cljstyleguide.state.app-state
  (:require
    [reagent.core :as reagent]
    [talltale.core :as fake]
    [ajax.core :refer [GET POST]]))

; Utils
; --------------------
(def version "v1")

(def users-data "/data/users.json")
(def patients-data "/data/patients.json")
(def visits-data "/data/visits.json")
(def todos-data "/data/todos.json")
(def trials-data "/data/trials.json")
(def patient-log-data "/data/patient-log.json")
(def tags-data "/data/tags.json")

(def patients (reagent/atom {:patients []}))
(def patient (reagent/atom {}))
(def todos (reagent/atom {:todos []}))
(def todo (reagent/atom {}))
(def visits (reagent/atom {:visits []}))
(def visit (reagent/atom {}))
(def users (reagent/atom {:users []}))
(def trials (reagent/atom {:trials []}))
(def trial (reagent/atom {}))
(def patient-log (reagent/atom {:patient-log []}))
(def tags (reagent/atom {:tags []}))
(def tag (reagent/atom {}))
(def fake-notification-text (take 90 (fake/text)))
(def theme-colors ["maroon" "blue" "navy" "dark" "orange" "olive" "teal" "pink" "red"])



; TODO
; Make better!
; ------------------------------

; (defn api [resource]
;   (let [path (str "/data/" version "/" resource ".json")
;         atom (str resource)
;         akey (keyword resource)]
;     (GET path {:handler #(swap! (symbol atom) assoc (symbol akey) %)
;                :error-handler api-error-handler
;                :response-format :json
;                :keywords? true})))

(defn api-error-handler [response]
  (.log js/console (str response)))
  
(defn api
  "Makes GET request to local JSON files"
  [path]
  (cond
    (= path "patients")
    (GET patients-data {:handler #(swap! patients assoc :patients %)
                        :error-handler api-error-handler
                        :response-format :json
                        :keywords? true})
    (= path "todos")
    (GET todos-data {:handler #(swap! todos assoc :todos %)
                     :error-handler api-error-handler
                     :response-format :json
                     :keywords? true})

    (= path "visits")
    (GET visits-data {:handler #(swap! visits assoc :visits %)
                      :error-handler api-error-handler
                      :response-format :json
                      :keywords? true})

    (= path "users")
    (GET users-data {:handler #(swap! users assoc :users %)
                     :error-handler api-error-handler
                     :response-format :json
                     :keywords? true})

    (= path "trials")
    (GET trials-data {:handler #(swap! trials assoc :trials %)
                      :error-handler api-error-handler
                      :response-format :json
                      :keywords? true})

    (= path "patient-log")
    (GET patient-log-data {:handler #(swap! patient-log assoc :patient-log %)
                           :error-handler api-error-handler
                           :response-format :json
                           :keywords? true})

    (= path "tags")
    (GET tags-data {:handler #(swap! tags assoc :tags %)
                    :error-handler api-error-handler
                    :response-format :json
                    :keywords? true})))
