(ns video-recording-sample-app.views
  (:require
   [reagent.core :as r]
   [re-frame.core :as rf]
   [video-recording-sample-app.subs :as subs]
   ["videojs" :as videojs]
   ["videojs-record" :as videojs-record]))

(defn home []
  [:div
   [:h1 "Hello from HOME"]
   [:div [:a {:href "/"} "home"]]
   [:div [:a {:href "/videojs-record"} "videojs-record"]]])

(defn videojs-record []
  (let [component-id "VideoJSRecordComponent"
        player       (r/atom nil)
        default-opts (clj->js {:controls true
                               :loop     false
                               :fluid    false
                               :width    320
                               :height   240
                               :plugins  {:record
                                          {:image     false
                                           :audio     false
                                           :video     true
                                           :maxLength 5
                                           :debug     true}}})]
   (r/create-class
     {:component-did-mount
      (fn [this]
        (reset! player (videojs component-id default-opts)))
      :reagent-render
      (fn []
        [:div
         [:h1 "Record video using videojs-record library"]
         [:video.video-js.vjs-default-skin
          {:id component-id}
          ]])})))

(defn not-found []
  [:div
   [:h1 "Not found"]])

(def panels
  {:video-recording-sample-app.core/home
   home
   :video-recording-sample-app.core/videojs-record
   videojs-record
   ::not-found
   not-found})

(defn main-panel []
  (let [route-name @(rf/subscribe [::subs/route-name])
        panel      (get panels (or route-name ::not-found))]
    [panel]))
