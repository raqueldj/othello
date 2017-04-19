Pour migrer :

Recréer la DataSource 'othello' dans Wildfly pour utiliser une base de données H2.

Dans la partie othello-web, mettre à jour le fichier environment.prod.ts avec l'url sur laquelle sera déployée le serveur

Compiler l'application angular avec `ng build --env=prod` pour la compiler dans sa version production.

Copier ensuite le répertoire `dist` dans le répertoire de déploiement du wildfly.

Lancer le wildfly avec la partie serveur également déployée.