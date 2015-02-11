#Rapport d'architecture du projet ( GymTonik )
##1. Présentation du projet
L’utilisateur souhaite avoir une application qui permet le suivi et la mise en perspective des progrès pour la pratique d’un sport. 
Cette application aura un rendu sous deux interfaces l'une sous forme de page web et l'autre sous console, ceci dans le but de respecter le cahier des charges.

L’application reposera sur une architecture J2EE.
Elle respectera l'architecture 3 tiers.
À noter que le paradigme MVC sera utilisé pour gérer les composants J2EE.

##2. Technologies utilisées
* Java est le langage utilisé pour le développement de l’application GymTonik, TestNG sera l'outil utilisé pour les test unitaires.
* Tomcat, comme serveur d'application J2EE.
* Markdown, un langage textuel, permettra d'avoir un aperçu immédiat sur Github.
* SQL sera le langage utilisé pour le SGBD.



##3. Choix des outils de développement
* IntelliJ IDEA pour le développement.
* plantUML pour la modélisation UML.
* MySQL comme SGBD (compatible avec le driver JDBC).
* Hibernate sera utilisé comme ORM.
* TestNG un outil de test, de qualité de code. 
* GIT pour la gestion des sources informatiques.


##4. Contraintes techniques
L'outil plantUML n’est pas installé sur l’environnement de travail ( les machines de la fac ), nous utilisons en attendant une possible installation, plantUML en ligne, d'utiliser l'outil le plus facilement possible, comme ça l’aurait été sur l’outil plantUML en local.

##5. Algorithme(s) complexe(s)
Un algorithme permettant en fonction du poids, du nombre d'heures de sommeil et de la taille d'évaluer les progrès d'un utilisateur. 

La difficulté réside dans la façon de calculer cet indice de performance en fonction d'indicateur comme le poids, le nombre d'heures de sommeil et la taille.

En effet, il va falloir que nos algorithmes prennent en considération de vrais analyses de la médecine sportive afin d'être le plus proche possible de la réalité.

Algorithme :

Integer calculatePerformance(User user)

  If REALISER FALSE || user.weight = 0
  
  RETURN
    
  RETURN user.weight / ( user.height + user.timeSleep )

##6. Présentation des diagrammes
###a. Diagramme de séquence

#### 1 - Inscription
![Diagramme de séquence de l'inscription](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/blob/master/diagrams/images/Inscription.png)

#### 2 - Connexion
![Diagramme de séquence de la connexion](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/blob/master/diagrams/images/Connexion.png)

#### 3 - Enregistrement d'un exercice 
![Diagramme de séquence de l'enregistrement d'un exercice](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/blob/master/diagrams/images/EnregistrementExercice.png)

#### 4 - Modification d'un exercice 
![Diagramme de séquence de la modification d'un exercice](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/blob/master/diagrams/images/ModificationExercice.png)

#### 5 - Suppression d'un exercice 
![Diagramme de séquence de la suppression d'un exercice](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/blob/master/diagrams/images/SuppressionExercice.png)

#### 6 - Enregistrement du travail effectué 
![Diagramme de séquence d'enregistrement du travail effectué](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/blob/master/diagrams/images/EnregistrementTravailEffectue.png)

#### 7 - Enregistrement et modification des autres mesures 
![Diagramme de séquence d'enregistrement et modification des autres mesures](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/blob/master/diagrams/images/EnregistrementAutresMesures.png)

#### 8 - Création du plan d'entraînement 
![Diagramme de séquence de création du plan d'entraînement](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/blob/master/diagrams/images/CreationPlanEntrainement.png)

#### 9 - Modification du plan d'entraînement
![Diagramme de séquence de modifcation du plan d'entraînement](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/blob/master/diagrams/images/ModificationPlanEntrainement.png)

#### 10 - Visualisation des progrès 
![Diagramme de séquence de la visualisation des progrès](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/blob/master/diagrams/images/VisualisationProgres.png)

###b. Diagramme de classe

#### 1 - Présentation du diagramme de classe
Ce diagramme permet de représenter l'architecture que nous allons utiliser.

Trois abstractions ont été mise en place dans le but de ne pas se fermer les portes d'évolutions futures du programme :

* Celle sur l'utilisateur, en effet, si dans le futur le programme doit pouvoir accueillir un autre type d'utilisateur, se sera possible.
* Sur les entraînements, pour l'instant il n'y a que des exercices, mais il pourra y avoir des étirements ou des pauses.
* Sur les parties du corps, pour l'instant on ne prend en compte que les muscles sur lesquels les entrainements font travailler, mais on pourrait aller plus loin, sur les parties du corps que les étirements font se détendre.

#### 2 - Diagramme
![Diagramme de séquence de la visualisation des progrès](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/blob/master/diagrams/images/DiagrammeClasse.png)

##7. Stockage des données

### 1 - Stockage
Le stockage de la base de donnée sera faite sur un host gratuit : AlwaysData (https://www.alwaysdata.com/)

Nous avons fait ce choix car il nous est impossible d'utiliser le serveur de la faculté de Nanterre et qu'un host payant n'est pas envisagable.

### 2 - Hibernate
Hiberate est un ORM. Nous avons fait le choix d'utiliser un ORM pour ne pas avoir à convertir les données relationnelles en objets.

Ainsi nous aurons des classes qui correspondent aux tables de notre base de données. Ce qui donnera l'illusion d'avoir une base de données objet et nous offriras une meilleur flexibilité lors de notre développement car nous n'aurons plus à faire la convertion décrite plus haut.
Les fonctions du CRUD seront donc beaucoup plus simple à mettre en oeuvre, car pas de conversion, et les requêtes vers la base seront simplifiées.

Le choix d'Hibernate plutôt qu'un autre est dû au fait qu'il est open source et que c'est un des plus connus des ORM JAVA.

### 3 - Diagramme
Vu que nous utilisons un ORM, le diagramme de donnée correspondant au diagramme de classe. De ce fait la modélisation des données se fera lors de la modélisation des classes.

##8. Glossaire

* J2EE   : Ensemble de technologies basé sur une approche multi-niveaux (JSP,Servlet,JDBC).
* MVC    : Méthode de conception qui organise l'interface homme-machine d'une application. 
* Github : Service web d'hébergement et de gestion de codes d'une application.
* SQL    : Structured Query Language , langage informatique normalisé pour gérer la base de données. 
* UML    : Unified Modelling Language, langage de modélisation graphique pour le développement d'une application.
* SGBD   : Système de gestions de bases de données. 
* GIT    : Logiciel de gestion de versions décentralisé.
* ORM    : Object Relationnal Mapping, mapping objet-relationnel en français. Permet de créer un lien entre la base de données et les objets utilisés dans le langage.




