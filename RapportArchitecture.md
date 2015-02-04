#Rapport d'architecture du projet ( GymTonik )
##1. Présentation du projet
L’utilisateur souhaite avoir une application qui permet le suivi et la mise en perspective des progrès pour la pratique d’un sport.

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
* TestNG un outil de test, de qualité de code. 
* GIT pour la gestion des sources informatiques.


##4. Contraintes techniques
L'outil plantUML n’est pas installé sur l’environnement de travail ( les machines de la fac ), nous utilisons en attendant une possible installation, plantUML en ligne, d'utiliser l'outil le plus facilement possible, comme ça l’aurait été sur l’outil plantUML en local, ce qui a pour effet un léger retard dans notre travail.

##5. Algorithme(s) complexe(s)
Un algorithme permettant en fonction du poids et du nombre d'heures de sommeil d'évaluer les progrès d'un utilisateur. 

La difficulté réside dans la façon de calculer cet indice de progrès en fonction d'indicateur comme le poids et le nombre d'heures de sommeil.

En effet, il va falloir que nos algorithmes prennent en considération de vrais analyses de la médecine sportive afin d'être le plus proche possible de la réalité.

Un algorithme permettant d'adapter un entrainement à un utilisateur en fonction de son historique et de ces objectifs sera tout aussi délicat.

##6. Présentation des diagrammes
###a. Diagramme de séquence

#### 1 - Inscription
![Diagramme de séquence de l'inscription](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/raw/3572e507dfee0199ed3112e18ea06d3d930e99c1/diagrams/images/Inscription.png)

#### 2 - Connexion
![Diagramme de séquence de la connexion](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/raw/3572e507dfee0199ed3112e18ea06d3d930e99c1/diagrams/images/Connexion.png)

#### 3 - Enregistrement d'un exercice 
![Diagramme de séquence de l'enregistrement d'un exercice](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/raw/3572e507dfee0199ed3112e18ea06d3d930e99c1/diagrams/images/EnregistrementExercice.png)

#### 4 - Modification d'un exercice 
![Diagramme de séquence de la modification d'un exercice](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/raw/3572e507dfee0199ed3112e18ea06d3d930e99c1/diagrams/images/ModificationExercice.png)

#### 5 - Suppression d'un exercice 
![Diagramme de séquence de la suppression d'un exercice](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/raw/3572e507dfee0199ed3112e18ea06d3d930e99c1/diagrams/images/SuppressionExercice.png)

#### 6 - Enregistrement du travail effectué 
![Diagramme de séquence d'enregistrement du travail effectué](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/raw/3572e507dfee0199ed3112e18ea06d3d930e99c1/diagrams/images/EnregistrementTravailEffectue.png)

#### 7 - Enregistrement et modification des autres mesures 
![Diagramme de séquence d'enregistrement et modification des autres mesures](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/raw/3572e507dfee0199ed3112e18ea06d3d930e99c1/diagrams/images/EnregistrementAutresMesures.png)

#### 8 - Création et modification du plan d'entraînement 
![Diagramme de séquence de création et modifcation du plan d'entraînement](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/raw/3572e507dfee0199ed3112e18ea06d3d930e99c1/diagrams/images/CreationPlanEntrainement.png)

#### 9 - Visualisation des progrès 
![Diagramme de séquence de la visualisation des progrès](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/raw/3572e507dfee0199ed3112e18ea06d3d930e99c1/diagrams/images/VisualisationProgres.png)

###b. Diagramme de classe

#### 1 - Présentation du diagramme de classe
Ce diagramme permet de représenter l'architecture que nous allons utiliser.

Trois abstractions ont été mise en place dans le but de ne pas se fermer les portes d'évolutions futures du programme :

* Celle sur l'utilisateur, en effet, si dans le futur le programme doit pouvoir accueillir un autre type d'utilisateur, se sera possible.
* Sur les entraînements, pour l'instant il n'y a que des exercices, mais il pourra y avoir des étirements ou des pauses.
* Sur les parties du corps, pour l'instant on ne prend en compte que les muscles sur lesquels les entrainements font travailler, mais on pourrait aller plus loin, sur les parties du corps que les étirements font se détendre.

#### 2 - Diagramme
![Diagramme de séquence de la visualisation des progrès](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/blob/master/diagrams/images/DiagrammeClasse.png)


##8. Glossaire
J2EE   : Ensemble de technologies basé sur une approche multi-niveaux (JSP,Servlet,JDBC).
MVC    : Méthode de conception qui organise l'interface homme-machine d'une application. 
Github : Service web d'hébergement et de gestion de codes d'une application.
SQL    : Structured Query Language , langage informatique normalisé pour gérer la base de données. 
UML    : Unified Modelling Language, langage de modélisation graphique pour le développement d'une application.
SGBD   : Système de gestions de bases de données. 
GIT    : Logiciel de gestion de versions décentralisé.




