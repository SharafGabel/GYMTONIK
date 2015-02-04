Rapport d'analyse du projet ( GymTonik )
=========

#Analyse de la demande
##1. Nature du projet
###Type de projet
Nous devons réaliser un outil permettant le suivi d’entraînements sportif.
###Expression des besoins et objectifs du projet
Proposer une application web permettant d’améliorer ses performances pour la pratique d’un sport. Cela s’articulera principalement sur le suivi des progrès réalisés, afin de pouvoir mieux connaître ses performances actuelles par rapport  à soi-même et aux autres. 

Elle proposera différents exercices, les séances pourront être pré-crées à partir d’une suite d’exercice. 
Les séances pourront aussi être assemblées par un utilisateur, qui renseignera les exercices, le nombre de répétitions pour chaque exercice, l’ordre des exercices, et les éventuels temps de pose entre chaque exercice. 
En fonction de tout ça, ainsi que du poid et de la taille de l'utilisateur, un indice sera calculé afin de dire si oui ou non, la séance crée est adapté à l’entraînement désiré (par exemple : entraînement sur un ou plusieurs muscles). Cette dernière phrase implique que chaque exercice aura une liste de muscles travaillés, ce qui permettra d’adapter au maximum les séances. 

Il pourra aussi renseigner son nombre d’heures de sommeil et lui conseiller un nombre d’heures de sommeil adapté à ses séances.

Le choix des heures de sommeil, et du niveau d’alimentation sera optionnel, tout comme l’élaboration d’une séance à partir d’exercices par l’utilisateur.

L’application donnera la possibilité à l’utilisateur d’avoir un aperçu plus concret des progrès réalisés grâce à diverses représentations graphiques de ces performances.

#Traduction de la demande

##1. Présentation du contexte
Permettre le suivi et la mise en perspective des progrès pour la pratique d’un sport.

##2. Définition du projet
###Fonctionnalités attendues
L’application doit permettre de gérer les entraînements sportifs, elle devra comporter une interface textuelle/graphique agréable qui doit permettre à  l’utilisateur :
* D'Enregistrer un exercice (temps, répétition, …)
* D'Enregistrer le travail réellement effectué 
* D'Enregistrer d’autres mesures dans le temps (poids, taille, sommeil, …)
* De Visualiser ses progrès dans le temps et les mettre en perspective avec les autres éléments enregistrés (autres exercices ou autre mesures)
* De Définir un plan d’entraînement 
* De Visualiser son prochain exercice
* De Visualiser un plan d’entraînement

###Début du projet
Mois de janvier 2015
###Fin du projet
Mois de mars 2015

##3. Acteurs du projet

###a. Utilisateur Anonyme
Il aura uniquement l'accès à l'inscription sur le site.

###b. Utilisateur en attente de confirmation
Il devra confirmer son inscription en cliquant sur un lien dans son e-mail afin de pouvoir accèder à l'application.

###c. Utilisateur confirmé

Il aura la possibilité :
* De se connecter à l'application.
* De créer une séance composé d'exercices.
* D'ajouter un exercice à une séance.
* De consulter ses performances.
* De comparer ses performances avec d'autres utilisateurs enregistrés ou lui-même.
* De renseigner son poids et sa taille.
* De renseigner son nombre d'heures de sommeil.
       
###Produit du projet
Le projet doit aboutir à la réalisation d’une application qui permet le suivi et la mise en perspective des progrès d’un utilisateur à travers l’implémentation d’une base de données et des interfaces nécessaires à l’utilisateur.
###Analyse des risques

| Numéro          | Description   | Cause     | Conséquences | Niveau de risque (/10)              |
| ----------- |:-------------:|:---------:|:------------:|:------------------------|
| **1**       | Mauvaise interaction entre l'utilisateur et l'application        |Eléments superflus à renseigner ou à faire  par l'utilisateur | Lassitude du client qui peut fermer l’application        |7           |
| **2**       | Entrainement non adapté à l'utilisateur          |Mauvaise corrélation entre l'historique du client et les objectifs qu'il s'est fixé|Plainte de l’utilisateur et programme non adapté à l’utilisateur. (danger)         |8         |
| **3**       | Non adaptation de l’application pour une version mobile        | Responsive Web Design non pris en compte dans l'application |Mauvais rendu visuel provoquant l’agacement de l’utilisateur.   |5           |
| **4**       | Objectifs de perte de poids non adapté à l'utilisateur        |     Mauvaises formules utilisées |Mauvais suivi de l’utilisateur et objectif biaisé.         |8          |


##4. Description des cas d'utilisation

### Inscription
 **Acteur(s) :** Utilisateur
 
 **Pré-conditions :** L'utilisateur n'est pas connecté et se trouve sur la page d'accueil
 
 **Scénario nominal :**
 
  1. L'utilisateur clique sur le bouton "Inscription"
  2. Un formulaire d'inscription est présenté à l'utilisateur. Il doit entrer [liste des informations nécessaires à l'inscription]
  3. L'utilisateur entre les informations qui lui sont demandées et soumets le formulaire
  4. Un e-mail est envoyé à l'adresse indiquée afin que l'utilisateur puisse valider son inscription en cliquant sur un lien fourni dans l'e-mail
  5. L'utilisateur clique sur le lien de validation
  6. L'inscription est validée, un message de confirmation est affiché.
  
 **Scénario alternatif :**
  
 &nbsp;&nbsp;&nbsp; 4.a. Les informations saisies sont invalides ou incomplètes. Retour à l'étape 2 et l'application indique les champs invalides ou vides  
   
### Connexion
 **Acteur(s) :** Utilisateur
  
 **Pré-conditions :** L'utilisateur n'est pas connecté et se trouve sur la page d'accueil
  
 **Scénario nominal :**
  
  1. L'utilisateur clique sur le bouton "Connexion"
  2. Un formulaire de connexion est présenté à l'utilisateur
  3. L'utilisateur entre son adresse e-mail, son mot de passe et soumets le formulaire
  4. L'utilisateur est connecté
   
 **Scénario alternatif :**
   
 &nbsp;&nbsp;&nbsp; 4.a. L'adresse e-mail n'existe pas ou le mot de passe est invalide. Retour à l'étape 2 et l'application indique que les informations saisies sont invalides. 
   
### Enregistrer un exercice
 **Acteur(s) :** Utilisateur
   
 **Pré-conditions :** L'utilisateur est connecté et se trouve sur la page d'accueil
   
 **Scénario nominal :**
  
  1. L'utilisateur sélectionne "Exercices"
  2. La liste des exercices disponibles s'affiche
  3. L'utilisateur sélectionne "Enregistrer un exercice"
  4. Un formulaire permettant d'entrer les détails de l'exerice est présenté à l'utilisateur
  5. L'utilisateur rempli le formulaire et le soumets
  6. L'exercice est enregistré
  
  **Scénario alternatif :**
     
 &nbsp;&nbsp;&nbsp; 6.a. Les informations saisies sont invalides ou incomplètes. Retour à l'étape 3 et l'application indique les champs invalides ou vides
   
### Modifier un exercice
 **Acteur(s) :** Utilisateur
   
 **Pré-conditions :** L'utilisateur est connecté et se trouve sur la page d'accueil
   
 **Scénario nominal :**
  
  1. L'utilisateur sélectionne "Exercices"
  2. La liste des exercices disponibles s'affiche
  3. L'utilisateur clique sur le bouton "Modifier" d'un exercice
  4. Un formulaire permettant de modifier les détails de l'exerice est présenté à l'utilisateur
  5. L'utilisateur rempli le formulaire et le soumets
  6. L'exercice est enregistré
  
  **Scénario alternatif :**
     
 &nbsp;&nbsp;&nbsp; 6.a. Les informations saisies sont invalides ou incomplètes. Retour à l'étape 3 et l'application indique les champs invalides ou vides
 
### Supprimer un exercice
 **Acteur(s) :** Utilisateur
   
 **Pré-conditions :** L'utilisateur est connecté et se trouve sur la page d'accueil
   
 **Scénario nominal :**
  
  1. L'utilisateur sélectionne "Exercices"
  2. La liste des exercices disponibles s'affiche
  3. L'utilisateur clique sur le bouton "Supprimer" d'un exercice
  4. L'exercice est supprimé
  
### Enregistrer le travail effectué
 **Acteur(s) :** Utilisateur
   
 **Pré-conditions :** L'utilisateur est connecté et se trouve sur la page d'accueil
   
 **Scénario nominal :**
  
  1. L'utilisateur sélectionne "Exercices"
  2. La liste des exercices enregistrés s'affiche 
  3. L'utilisateur sélectionne un exercice
  4. Un formulaire permettant d'entrer les valeurs liées à l'exerice est présenté à l'utilisateur
  5. L'utilisateur rempli le formulaire et le soumets
  6. L'exercice est enregistré
  
  **Scénario alternatif :**
     
 &nbsp;&nbsp;&nbsp; 6.a. Les informations saisies sont invalides ou incomplètes. Retour à l'étape 4 et l'application indique les champs invalides ou vides  
 
### Enregistrer et modifier d'autres mesures
 **Acteur(s) :** Utilisateur
   
 **Pré-conditions :** L'utilisateur est connecté et se trouve sur la page d'accueil
   
 **Scénario nominal :**
  
  1. L'utilisateur sélectionne "Condition physique"
  2. Un formulaire permettant d'entrer les détails sur sa condition physique est présenté à l'utilisateur
  3. L'utilisateur rempli le formulaire et le soumets
  4. Les informations sont enregistrées
  
  **Scénario alternatif :**
     
 &nbsp;&nbsp;&nbsp; 4.a. Les informations saisies sont invalides ou incomplètes. Retour à l'étape 2 et l'application indique les champs invalides ou vides  
 
### Définir et modifier un plan d'entraînement
 **Acteur(s) :** Utilisateur
   
 **Pré-conditions :** L'utilisateur est connecté et se trouve sur la page d'accueil
   
 **Scénario nominal :**
  
  1. L'utilisateur sélectionne "Plan d'entraînement"
  2. Une liste des exercices disponibles est affichée
  3. L'utilisateur sélectionne les exercices qu'il souhaite réaliser ou non et valide
  4. Le plan d'entraînement est enregistré
  
  **Scénario alternatif :**
     
 &nbsp;&nbsp;&nbsp; 4.a. L'utilisateur n'a sélectionné aucun exercice. Retour à l'étape 2 et l'application indique l'erreur

### Visualiser les progrès
 **Acteur(s) :** Utilisateur
   
 **Pré-conditions :** L'utilisateur est connecté et se trouve sur la page d'accueil
   
 **Scénario nominal :**
  
  1. L'utilisateur sélectionne "Progrès"
  2. Un graphe récapitulant les progrès de l'utilisateur est affiché
 
##4. Diagramme de Cas d'utilisation
![Diagramme de Cas d'utilisation](https://raw.githubusercontent.com/Miage-Paris-Ouest/m120142015-gymtonik/3572e507dfee0199ed3112e18ea06d3d930e99c1/diagrams/images/UseCase.png?token=AC_voBv9JYFtjOuS38jYxAO1s_YF9_45ks5U07JgwA%3D%3D)
