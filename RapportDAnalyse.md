Rapport d'analyse du projet ( Gym Tonik )
=========

#Analyse de la demande
##1. Nature du projet
###Type de projet
Nous devons réaliser un outil permettant le suivi d’entraînements sportif.
###Expression des besoins et objectifs du projet
Proposer une application web permettant d’améliorer ses performances pour la pratique d’un sport. Cela s’articulera principalement sur le suivi des progrès réalisés, afin de pouvoir mieux connaître ses performances actuelles par rapport  à soi-même et aux autres. 

Elle proposera différents exercices, les séances pourront être pré-crées à partir d’une suite d’exercice. 
Les séances pourront aussi être assemblées par un utilisateur, qui renseignera les exercices, le nombre de répétitions pour chaque exercice, l’ordre des exercices, et les éventuels temps de pose entre chaque exercice. 
En fonction de tout ça, un indice sera calculé afin de dire si oui ou non, la séance crée est adapté à l’entraînement désiré (par exemple : entraînement sur un ou plusieurs muscles). Cette dernière phrase implique que chaque exercice aura une liste de muscles travaillés, ce qui permettra d’adapter au maximum les séances. 

On pourra aussi proposer une partie gestion de l’alimentation, où chaque utilisateur pourra renseigner son alimentation, et en fonction de ça, évaluer l’évolution possible sur ses performances/poids, ou s’il le manque un type d’aliment dans son alimentation (par exemple : des protéines), on lui conseillera d’ajouter cet aliment, il aura ensuite la possibilité de choisir entre son régime alimentaire, et le régime alimentaire proposé. 

Il pourra aussi renseigner son nombre d’heures de sommeil et lui conseiller un nombre d’heures de sommeil adapté à ses séances.

Le choix des heures de sommeil, et du niveau d’alimentation sera optionnel, tout comme l’élaboration d’une séance à partir d’exercices par l’utilisateur.

L’application donnera la possibilité à l’utilisateur d’avoir un aperçu plus concret des progrès réalisés grâce à diverses représentations graphiques de ces performances.
###Délais
Une première version sera remis le 22/03/2015.

La version final du projet sera remis le 26/03/2015.

##2. Moyens mis à disposition
###Intervenants affectes au projet
Nous serons une équipe de cinq à intervenir sur le projet: 
* Chef de projet : Sharaf Gabel
* Responsable modélisation : Axel leroy 
* Responsable technique : Christian Hiroz
* Responsable qualité : Kugasanth Kugathasan
* Responsable communication : Driss Kaaouass

Chaque intervenant participera à tous les domaines du projet.

#Traduction de la demande

##1. Présentation du contexte
Permettre le suivi et la mise en perspective des progrès pour la pratique d’un sport.

##2. Définition du projet
###Problématique
Comment optimiser le suivi d’entraînement sportif pour un utilisateur ?
###Fonctionnalités attendues
L’application doit permettre de gérer les entraînements sportifs, elle devra comporter :
* Une interface textuelle/graphique agréable qui doit permettre à  l’utilisateur de :
* Enregistrer un exercice (temps, répétition, …)
* Enregistrer le travail réellement effectué 
* Enregistrer d’autres mesures dans le temps (poids, sommeil, …)
* Visualiser ses progrès dans le temps et les mettre en perspective avec les autres éléments enregistrés (autres exercices ou autre mesures)
* Définir un plan d’entraînement 
* Visualiser son prochain exercice
* Visualiser un plan d’entraînement

###Début du projet
Mois de janvier 2015
###Fin du projet
Mois de mars 2015

##3. Acteurs du projet
###Maître d’œuvre
Le maître d’œuvre est le chef de projet, Sharaf Gabel, qui a pour rôle de répartir les tâches et de planifier la réalisation du projet.
###Compétences nécessaires
**Gestion du projet** : planification, définition des objectifs, répartition des tâches, développement.

**Modélisation de la base de données** : modélisation de systèmes (UML), normes sur la modélisation, connaissance du langage SQL (responsable modélisation).

**Implémentation de la base de données** : implémenter le modèle (responsable technique).

**Test de qualité** : tester le bon fonctionnement de la base de données et des interfaces à travers de test d’utilisation afin de retrouver les erreurs possibles.
###Produit du projet
Le projet doit aboutir à la réalisation d’une application qui permet le suivi et la mise en perspective des progrès d’un utilisateur à travers l’implémentation d’une base de données et des interfaces nécessaires à l’utilisateur.
###Analyse des risques

| Numéro          | Description   | Cause     | Conséquences | Criticité  Gravité;Fréquence            |
| ----------- |:-------------:|:---------:|:------------:|:------------------------|
| **1**       | Gestion interaction entre l’utilisateur et l’application          |Il ne faut pas que l’utilisateur soit lassé rapidement du nombre d’information à entrer |Fermeture de l’application        |8      ;      1           |
| **2**       | Mise en œuvre d’un entraînement fixé par l’application          |Le niveau doit correspondre au profil de l’utilisateur|Plainte de l’utilisateur et programme non adapté à l’utilisateur. (danger)         |8     ;       5           |
| **3**       | Adapter l’application pour une version mobile        |    Mauvais affichage |Mauvais rendu visuel provoquant l’agacement de l’utilisateur.   |5      ;      5           |
| **4**       | Calcul des algorithmes des pertes de poids         |     Mauvaises formules utilisées|Objectif non atteint par l’utilisateur.         |9      ;      8           |

## Description des cas d'utilisation
### Enregistrer un exercice
 
### Enregistrer le travail effectué
 
### Enregistrer d'autres mesures
 
### Visualiser les progrès
 
### Définir un plan d'entrainement
 

##4. Diagramme de Cas d'utilisation
![image Use Case](http://www.plantuml.com/plantuml/png/ZPB1Ja8n44NtVCNia0L_O4W0esvq8T74bIbzLwoqCo_JlY0n_ejRleCViw3WKqf25-tYddllPD8uTrmRDO6Q26iYwBr3-OoCdSzK6gx6uaNf0gWdy-N8D_ZMHIleeLOodv_JZN5CWm7Hj13GqlVozXggogBSFxH9j3t4bGBy2EzbyGjS2gTR4_17RVaFvhnkWjaEqZPyT3htoNaNJKAZJuQ5bpcptn_GsQGBLinSJ0hjjRaV7MI56JOLvR6kLr1g61JNAyCML7Nn1hNEei0FNwL9MmFjA5f_aZSxUy7p84uf8b8DVHOD6ooMPf6GVm9EOk_04Mx66rgOQd5LF5RjSrpUGUPz9EDT02VZz9HSbZ3LpKfUCf7Vl2K98GSTx_hdsLVxlEZO-m80 "USE CASE")
