(defproject movies-2017 "0.1.0-SNAPSHOT"
  :description "A review of movies seen in 2017"
  :url "http://github.com/tvirolai/movies-2017"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [incanter "1.5.7"]]
  :jvm-opts ["--add-modules" "java.xml.bind"]
  :main ^:skip-aot movies-2017.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
