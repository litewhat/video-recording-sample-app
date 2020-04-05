(ns video-recording-sample-app.core
  (:require
    [reagent.core :as r]
    [re-frame.core :as rf]
    [re-frame.db :as rfdb]
    [reitit.frontend :as rfd]
    [reitit.frontend.easy :as rfe]
    [reitit.frontend.controllers :as rfc]
    [reitit.coercion :as rc]
    [reitit.coercion.spec]
    [video-recording-sample-app.events :as events]
    [video-recording-sample-app.views :as views]
    [video-recording-sample-app.config :as config]))

(defn on-navigate!
  [match _history]
  (let [route (r/cursor rfdb/app-db [:route])]
    (swap! route
      (fn [old-match]
        (assoc match
          :controllers (rfc/apply-controllers (:controllers old-match) match))))
    (rf/dispatch [::events/set-route match])))

(def router
  (rfd/router
    ["" {:coercion reitit.coercion.spec/coercion}
     ["/" {:name ::home}]
     ["/videojs-record" {:name ::videojs-record}]]
    {:compile rc/compile-request-coercers}))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (rf/clear-subscription-cache!)
  (r/render [views/main-panel] (.getElementById js/document "app")))

(defn init! []
  (rfe/start! router on-navigate! {:use-fragment false})
  (rf/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
