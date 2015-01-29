#Rapport d'architecture du projet ( GymTonik )
##1. Présentation du projet
L’utilisateur souhaite avoir une application qui permet le suivi et la mise en perspective des progrès pour la pratique d’un sport.

L’application sera orienté coté web avec une base de données où elle proposera notamment les différents exercices adaptés à l’utilisateur en fonction de ses progrès.

##2. Technologies utilisées
* Java et le langage utilisé pour le développement de l’application GymTonik, TestNG sera l'outil utilisé pour les test unitaires.
* Markdown, un langage textuel, permettra d'avoir un aperçu immédiat sur github.
* SQL sera le langage utilisé pour le SGBD.


##3. Choix des outils de développement
* IntelliJ IDEA pour le développement.
* plantUML pour la modélisation UML.
* MySQL comme SGBD.
* TestNG un outil de test, de qualité de code. 

##4.Contraintes techniques
L'outil plantUML n’est pas installé sur l’environnement de travail ( les machines de la fac ), nous utilisons en attendant une possible installation, plantUML en ligne, ce qui ne permet pas une facilité de code comme ça l’aurait été sur l’outil plantUML en local, ce qui nous retarde un peu dans notre travail.

##5. Algorithme(s) complexe(s)
Un algorithme permettant en fonction du poids et du nombre d'heures de sommeil d'évaluer les progrès d'un utilisateur. 

La difficulté réside dans la façon de calculer cet indice de progrès en fonction d'indicateur comme le poids et le nombre d'heures de sommeil.

##6. Présentation des diagrammes
###a. Diagramme de séquence

#### Inscription
![Diagramme de séquence de l'inscription](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/raw/3572e507dfee0199ed3112e18ea06d3d930e99c1/diagrams/images/Inscription.png)

#### Connexion
![Diagramme de séquence de la connexion](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/raw/3572e507dfee0199ed3112e18ea06d3d930e99c1/diagrams/images/Connexion.png)

#### Enregistrement d'un exercice 
![Diagramme de séquence de l'enregistrement d'un exercice](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/raw/3572e507dfee0199ed3112e18ea06d3d930e99c1/diagrams/images/EnregistrementExercice.png)

#### Modification d'un exercice 
![Diagramme de séquence de la modification d'un exercice](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/raw/3572e507dfee0199ed3112e18ea06d3d930e99c1/diagrams/images/ModificationExercice.png)

#### Suppression d'un exercice 
![Diagramme de séquence de la suppression d'un exercice](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/raw/3572e507dfee0199ed3112e18ea06d3d930e99c1/diagrams/images/SuppressionExercice.png)

#### Enregistrement du travail effectué 
![Diagramme de séquence d'enregistrement du travail effectué](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/raw/3572e507dfee0199ed3112e18ea06d3d930e99c1/diagrams/images/EnregistrementTravailEffectue.png)

#### Enregistrement et modification des autres mesures 
![Diagramme de séquence d'enregistrement et modification des autres mesures](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/raw/3572e507dfee0199ed3112e18ea06d3d930e99c1/diagrams/images/EnregistrementAutresMesures.png)

#### Création et modifcation du plan d'entraînement 
![Diagramme de séquence de création et modifcation du plan d'entraînement](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/raw/3572e507dfee0199ed3112e18ea06d3d930e99c1/diagrams/images/CreationPlanEntrainement.png)

#### Visualisation des progrès 
![Diagramme de séquence de la visualisation des progrès](https://github.com/Miage-Paris-Ouest/m120142015-gymtonik/raw/3572e507dfee0199ed3112e18ea06d3d930e99c1/diagrams/images/VisualisationProgres.png)

###b. Diagramme de classe

##7. Versions textuelles des diagrammes



