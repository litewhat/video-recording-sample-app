(ns video-recording-sample-app.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   [video-recording-sample-app.events :as events]
   [video-recording-sample-app.views :as views]
   [video-recording-sample-app.config :as config]
   ))


(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
