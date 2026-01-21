#!/bin/bash

# Charger les variables depuis le fichier .env
export $(grep -v '^#' .env | xargs)

# Vérification (optionnel) : afficher les variables pour confirmer qu'elles sont chargées
echo "URL de la base de données : $SPRING_DATASOURCE_URL"
echo "Nom d'utilisateur : $SPRING_DATASOURCE_USERNAME"

# Lancer la commande Maven pour construire le projet
mvn clean package
