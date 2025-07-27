# projet cresuss pour Olympp - Application bancaire
Bonjour,
ce projet a été réalisé dans le cadre d'un Kata soumis par Jean Blarel
Il est incomplet et non terminé, et j'en suis désolé, mais je vous ai tout de même joint ce que j'avais réussi à produire.

= I. Objectif
L'objectif de cette application est de fournir aux utilisateurs un moyen d'accéder à leurs comptes bancaires, et d'ajouter/supprimer des fonds.
A la connexion, les utilisateurs arrivent sur une page listant les "users" de la database
Ils peuvent ensuite supprimer ces Users, ou consulter les comptes de chaque Users

= II. Fonctionnalités manquantes
• La fonctionnalité majeure manquante est l'ajout et le retrait de fonds des différents comptes. Cela est dû à un bug que je n'arrive pas à corriger, erreur 415 - Unsupported Media Type. Cette erreur semble être dûe à mon emploi de jointures bidirectionnel @OneToMany/@ManyToOne dans les Model de mon API.
• Les règles de gestion particulières (pas de retrait vers une valeur négative, etc...) n'ont par conséquent pas été implémentées
• La fonctionnalité de modification des Utilisateurs a été retirée à cause de la même erreur 415 
• J'aurais aimé ajouter un système d'authentification avec User/Mdp, pour ne voir que ses comptes personnels, ou tous les comptes si on est Admin
• Enfin, il manque les fonctionnalité permettant d'agir sur les comptes depusi l'application (création, modification, suppression, ...)

= III. Les différents dossiers
Ce projet est divisé en 3 dossiers: un pour la DB, un pour l'API, et un pour la WebApp

== III.A. dossier cresussdatabase
• cresussdatabase est la database mysql que j'ai utilisée.
• elle comporte 4 tables:
  - User qui contient 6 colonnes: usr_id(PK), nom, prenom, password, mail, rol_id
  - Compte qui contient 4 colonnes: com_id(PK), balance, usr_id, typ_id
  - Role qui contient 2 colonnes: rol_id(PK), nom
  - Type qui contient 3 colonnes: typ_id(PK), nom, seuil

• Toutes les jointures sont faites sur les colonnes de mêmes noms.

• Voici les descriptions fonctionnelles des tables
- Les Users sont les personnes qui utilisent l'application et ont accès aux comptes. Ils ont un rôle, qui aurait dû déterminer s'ils avaient droits admin ou droits timples.
- Les Comptes représentent les comptes bancaires. Ils ont un type qui va déterminer des règles de gestion particulières, et sont rattachés à un User.
- Les Roles déterminent les droits de l'utilisateur sur l'application
- Les Types déterminent les règles de gestion particulières des comptes (ex. pas de retrait d'argent faisant passer sous le seuil déterminé)

== III.B. dossier projetCresuss
• Il s'agit de l'API du projet.
Elle se base sur une architecture avec Model, Repository, Service, Controller
Elle appelle la BDD MySQL située à: spring.datasource.url=jdbc:mysql://localhost:3306
et se connecte au port 9000

== III.C. dossier webapp
• Il s'agit de l'Application Web du projet.
Elle se base sur une architecture avec Model, Repository, Service, Controller
une custom properties a été mise en place pour taper sur l'URL
Pour se connecter à l'application: localhost:9001

Elle contient des templates pour afficher les pages
Il y a actuelelemnt 4 pages, dont une non utilisée:
• "localhost:9001" mène à home.html, c'est la page de base, affichant les users et permettant de les supprimer et de voir leurs comptes, ainsi que la création d'un nouvel user.
•• clic sur le bouton "Supprimer" d'un utilisateur le retire de la BDD
•• clic sur "Ajouter un nouveau user" mène à formNewUser.html, permettant de créer un nouvel utilisateur
••• possibilité de remplir les champs, puis cliquer sur "Créer" pour enregistrer ce nouvel utilisateur sur la BDD
••• Bouton RETOUR pour revenir au home.xtml
•• clic sur le bouton "VoiComptes" d'un utilisateur mène à formSeeUserAccounts.html de cet utilisateur, pour en voir les comptes.
••• affiche les comptes de cet utilisateur. Les boutons ne font rien (a cause d'une erreur 415, mes requetes PUT ne fonctionnent pas)
••• Bouton RETOUR pour revenir au home.xtml

= IV. Outils utilisés

• Visual Studio v1.102.1
https://code.visualstudio.com/
• Java SE Development Kit v24.0.2
https://www.oracle.com/fr/java/technologies/downloads/#jdk24-windows
• Apache Maven v3.9.11
https://maven.apache.org/download.cgi
• Spring Boot v3.5.3
https://docs.spring.io/spring-boot/installing.html
• MySQL 8.0.43
https://dev.mysql.com/downloads/installer/
• Postman
https://www.postman.com/downloads/

Merci de votre lecture
