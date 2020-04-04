(ns video-recording-sample-app.events
  (:require
   [re-frame.core :as rf]
   [video-recording-sample-app.db :as db]))

(rf/reg-event-db
 ::initialize-db
 (fn [_ _]
   (db/default-db)))

(rf/reg-event-db
  ::set-route
  [rf/trim-v]
  (fn [db [match]]
    (assoc db :route match)))
