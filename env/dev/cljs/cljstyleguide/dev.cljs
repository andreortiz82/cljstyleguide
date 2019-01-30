(ns ^:figwheel-no-load cljstyleguide.dev
  (:require
    [cljstyleguide.core :as core]
    [devtools.core :as devtools]))

(devtools/install!)

(enable-console-print!)

(core/init!)
