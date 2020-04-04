(ns video-recording-sample-app.events
  (:require
   [re-frame.core :as re-frame]
   [video-recording-sample-app.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))
