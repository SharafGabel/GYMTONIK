#USE CASE ( Format Textuel )

skinparam usecase{
	BackgroundColor DeepSkyBlue
	BorderColor DeepSkyBlue

	BackgroundColor<< Main >>  blue
	BorderColor<< Main >> blue
    
	ArrowColor DeepSkyBlue
	ActorBorderColor GreenYellow
	ActorFontName GreenYellow

	ActorBackgroundColor<< Human >> GreenYellow
}

Utilisateur_anonyme --> (S'enregistrer)
Utilisateur --> (Se connecter)
Utilisateur --> (Créer une séance d'exercice)
Utilisateur --> (Ajouter un exercice à la séance)
Utilisateur --> (Voir ses performances)
Utilisateur --> (Comparer ses performances)
Utilisateur --> (Renseigner son poids)
Utilisateur --> (Voir les conseils alimentaires)
Utilisateur --> (Renseigner son nombre d'heure de sommeil)
Utilisateur --> (Voir le nombre d'heures conseillées)
