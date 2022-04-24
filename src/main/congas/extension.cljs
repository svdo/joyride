(ns congas.extension
  (:require
   ["path" :as path]
   ["vscode" :as vscode]
   [nbb.core :as nbb]
   [promesa.core :as p]))

(defn register-disposable [^js context ^js disposable]
  (.push context.subscriptions disposable))

(defn- register-command [command]
  (vscode/commands.registerCommand (-> command meta :command) #(command)))

(defn- setup-command [^js context command]
  (->> (register-command command)
       (register-disposable context)))

(defn ^{:command "congas.runScript"} run-script [& script]
  (println "BOOM!")
  (let [ws-folder ^js (first js/vscode.workspace.workspaceFolders)
        ws-root ws-folder.uri.fsPath]
    (nbb/load-file (path/resolve ws-root ".congas/scripts/hello.cljs")))
  (js/vscode.window.showInformationMessage (str "Hello " script)))
; /Users/pez/Desktop/empty/congas/scripts/hello.cljs
(defn activate [^js context]
  (aset js/globalThis "vscode" vscode)
  (setup-command context #'run-script))

(comment)
