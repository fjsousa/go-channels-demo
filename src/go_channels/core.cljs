(ns go_channels.core
  (:require-macros [cljs.core.async.macros :as am :refer [go]])
  (:require [cljs.core.async :refer [<! >! put! alts! chan close! timeout]]))

(def colors ["blue" "red" "yellow" "green"])

(defn make-cell [canvas x y]
  (let [ctx (-> js/document
              (.getElementById canvas)
              (.getContext "2d"))]
    (go (while true
          (set! (.-fillStyle ctx) (rand-nth colors))
          (.fillRect ctx x y 10 10)
          (<! (timeout (rand-int 101)))))))

(defn make-scene [canvas rows cols]
  (dotimes [x cols]
    (dotimes [y rows]
      (make-cell canvas (* 10 x) (* 10 y)))))

(make-scene "go-div" 50 50)
