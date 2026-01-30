# SAE – Développement & Déploiement d’une Application Web RESTful  
### Projet en cours — Sup Galilée (DDAW)

Cette application expose une API REST permettant de gérer un mini-système de tickets inspiré de JIRA :

* gestion des utilisateurs et de leur profil associé,
* gestion des projets,
* gestion des tickets,
* gestion des commentaires,
* gestion des tags (relation many-to-many).

Le projet utilise Java 17, Spring Boot 4, Spring Data JPA et une base MySQL.

---

## Objectifs du projet

Développer une application web disposant :

- d’un **backend Spring Boot** exposant une **API RESTful** ;
- d’une **base de données MySQL** ;
- d’un **ORM** pour gérer la persistance ;
- d’une **architecture conteneurisée** via Docker & Docker Compose.

---

## 1. Technologies utilisées

* Java 17
* Spring Boot 4
* Spring Web
* Spring Data JPA
* MySQL
* Maven

Script SQL : `src/main/resources/JIRA.sql`

---

## 2. Modèle de données

Le schéma SQL définit les entités suivantes :

* Utilisateur (avec un Profil en relation 1–1)
* Projet (avec un propriétaire)
* Ticket (lié à un projet, un créateur, un assigné)
* Commentaire (lié à un ticket)
* Tag (many-to-many avec Ticket)

Les entités Java sont situées dans :
`src/main/java/com/api/jira/Entities/`

---

## 3. Lancement du projet

1. Se placer dans le répertoire de docker-compose.yml
   [Télécharger le docker-compose.yml en ZIP](docker-compose.zip)
3. image backend : `docker pull merlinaarul/jira-api:latest`
4. Lancer `docker-compose up`

---

## 4. Principales routes disponibles

### Utilisateurs (`/utilisateurs`)

* POST, GET, GET/{id}, PUT/{id}, DELETE/{id}
* GET /{id}/projets
* GET /{id}/tickets
* GET /{id}/assigneticket

### Projets (`/projet`)

* POST, GET, GET/{id}, PUT/{id}, DELETE/{id}
* PATCH /{id}/status
* GET /{id}/tickets

### Tickets (`/tickets`)

* POST, GET, GET/{id}, PUT/{id}, DELETE/{id}
* PATCH status / assign
* Gestion des commentaires
* Gestion des tags

### Tags (`/tag`)

* CRUD complet

### Commentaires (`/commentaire`)

* CRUD complet

---

## 5. Auteurs

Projet réalisé par :

* Merlina ARUL
* Rosselle QUIZON
