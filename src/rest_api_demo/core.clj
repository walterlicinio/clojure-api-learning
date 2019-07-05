(ns rest-api-demo.core
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.rout :as route]
            [ring.middleware.defaults :refer :all]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            [clojure.data.json :as json])

  (:gen-class))

(defrouts app-routes
  (GET "/" [] simple-body-page)
  (GET "/request" [] request-example)
  (route/not-found "Error, page not found!"))



(defn -main
  "This is our main entry point."
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
     ; Run the server with Ring.defaults middleware
    (server/run-server (wrap-defaults #'app-routes site-defaults)
                       {:port port})
     ; Run the server without Ring.defaults
     ; (server/run-server #'app-routes {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))
