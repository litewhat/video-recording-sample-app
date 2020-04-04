(ns video-recording-sample-app.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::route
 (fn [db]
   (:route db)))

(re-frame/reg-sub
  ::route-name
  :<- [::route]
  (fn [route]
    (->> route :data :name)))
