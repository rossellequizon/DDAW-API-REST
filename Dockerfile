# Utiliser une image de base OpenJDK (par exemple, OpenJDK 21)
FROM eclipse-temurin:21-jdk

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier JAR généré dans le conteneur
COPY target/jira-0.0.1-SNAPSHOT.jar /app/jira-api.jar

# Copier le fichier .env et le script start.sh dans le conteneur
COPY .env /app/.env
COPY start.sh /app/start.sh

# Donner les permissions d'exécution au script
RUN chmod +x /app/start.sh

# Exposer le port 8080 pour l'application
EXPOSE 8080

# Définir le point d'entrée pour exécuter le script start.sh
ENTRYPOINT ["/app/start.sh"]
