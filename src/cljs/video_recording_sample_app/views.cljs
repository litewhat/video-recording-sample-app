(ns video-recording-sample-app.views
  (:require
   [re-frame.core :as rf]
   [video-recording-sample-app.subs :as subs]
   ["videojs-record" :as videojs-record]))

(defn home []
  [:div
   [:h1 "Hello from HOME"]
   [:div [:a {:href "/"} "home"]]
   [:div [:a {:href "/videojs-record"} "videojs-record"]]])

(defn not-found []
  [:div
   [:h1 "Not found"]])

(def panels
  {:video-recording-sample-app.core/home home
   ::not-found                           not-found})

(defn main-panel []
  (let [route-name @(rf/subscribe [::subs/route-name])
        panel      (get panels (or route-name ::not-found))]
    [panel]))
