#!/bin/bash

# Charger les variables depuis le fichier .env
export $(grep -v '^#' .env | xargs)

echo "URL de la base de données : $SPRING_DATASOURCE_URL"
echo "Nom d'utilisateur : $SPRING_DATASOURCE_USERNAME"

# Lancer l'application Spring Boot avec Maven
java -jar /app/jira-api.jar