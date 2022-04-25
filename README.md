# koxo-log-formatter

Application pour reformatter les logs ayant été génerés via l'outil KoXo Log Sessions, voir: [koxo.net](https://koxo.net/). Retire l'en tête et les lignes "LOGOFF".
Son but est d'avoir des données plus exploitables et surtout plus rapidement pour ensuite les lire/trier dans un tableur.
Pour par exemple, verifier les dates de dernières connexions sur un poste.

## Utilisation
Lancez l'application puis sélectionnez le dossier contenant les logs. 
Un nouveau dossier sera créé avec le nom "formatted-logs" contenant tous les fichiers reformattés.
Chaque fichier valide est reformatté et porte le même nom que l'original.
Un fichier nommé "combined" avec tous les logs reformattés est également créé.
