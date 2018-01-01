(ns movies-2017.core
  (:require [incanter.core :as i]
            [incanter.io :as iio]
            [clojure.string :as s]
            [incanter.charts :as c]
            [incanter.stats :as stats])
  (:gen-class))

(defn load-data! []
  (->> (iio/read-dataset "./data/WATCHLIST.csv" :header true :keyword-headers false)
       (i/$where {"Title" {:ne "At Berkeley"}})))

(defn get-year [data year]
  (i/$where {"Created" {:$fn #(s/includes? % year)}} data))

(defn by-month [data]
  (->> (get-year data "2017")
       (i/add-derived-column "Month" ["Created"] #(subs % 0 7))
       (i/$rollup :count "Movies seen" "Month")
       (i/$order "Month" :asc)))

(defn months-to-bars [data]
  (let [res (by-month data)
        months (i/$ "Month" res)
        counts (i/$ "Movies seen" res)]
    (i/view
      (c/bar-chart months counts
                   :title "Movies seen in 2017 by month"
                   :x-label ""
                   :y-label "Movie count"))))

(defn total-counts [data]
  (let [mins (->> (get-year data "2017")
                  (i/$ "Runtime (mins)")
                  (reduce +))
        hours (float (/ mins 60))]
    {:mins mins
     :hours hours
     :days (/ hours 24)}))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
