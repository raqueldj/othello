Pour migrer :

Recr�er la DataSource 'othello' dans Wildfly pour utiliser une base de donn�es H2.

Dans la partie othello-web, mettre � jour le fichier environment.prod.ts avec l'url sur laquelle sera d�ploy�e le serveur

Compiler l'application angular avec `ng build --env=prod` pour la compiler dans sa version production.

Copier ensuite le r�pertoire `dist` dans le r�pertoire de d�ploiement du wildfly.

Lancer le wildfly avec la partie serveur �galement d�ploy�e.