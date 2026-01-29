-- UTILISATEUR SYSTÈME (OBLIGATOIRE)
INSERT IGNORE INTO utilisateur (USERNAME, EMAIL, PWD, ACTIF)
SELECT 'deleted_user', 'deleted@system.local',
'deleted',
false
FROM dual
WHERE NOT EXISTS (
    SELECT 1 FROM utilisateur WHERE USERNAME = 'deleted_user'
);

-- DONNÉES DE TEST

-- Utilisateurs de démonstration
INSERT IGNORE INTO utilisateur (USERNAME, EMAIL, PWD, ACTIF)
SELECT 'admin', 'admin@jira.local',
'admin1234',
true
FROM dual
WHERE NOT EXISTS (
    SELECT 1 FROM utilisateur WHERE USERNAME = 'admin'
) AND (SELECT COUNT(*) FROM utilisateur) < 3;

INSERT IGNORE INTO utilisateur (USERNAME, EMAIL, PWD, ACTIF)
SELECT 'dev_user', 'dev@jira.local',
'dev1234',
true
FROM dual
WHERE NOT EXISTS (
    SELECT 1 FROM utilisateur WHERE USERNAME = 'dev_user'
) AND (SELECT COUNT(*) FROM utilisateur) < 3;


-- PROJETS

 INSERT INTO projet (creation_date, projet_description, projet_name, projet_status, owner_id)
 VALUES
   (NOW(6), 'Projet Jira - Backlog initial', 'Jira API', 'EN_COURS', 1),
   (NOW(6), 'Projet de test pour tickets', 'Demo Project', 'TODO', 1);

-- TICKETS
INSERT INTO ticket (creation_date, deadline, ticket_description, ticket_priority, ticket_status, title, assigne_id, creator_id, projet_id)
VALUES
  (NOW(6), '2026-02-15', 'Créer les endpoints CRUD utilisateurs', 'HIGH', 'TODO', 'CRUD utilisateurs', NULL, 1, 1),
  (NOW(6), '2026-03-01', 'Mettre en place Docker Compose', 'MEDIUM', 'EN_COURS', 'Docker compose', 1, 1, 1),
  (NOW(6), NULL, 'Ajouter gestion tags', 'LOW', 'TODO', 'Tags', NULL, 1, 2);


-- COMMENTAIRES
INSERT INTO commentaire (contenu, creation_date, ticket_id, auteur_id)
SELECT
  'J''ai reproduit le bug, il vient de l''extension AdBlock',
  NOW(6),
  (SELECT id FROM ticket WHERE title = 'Bug Login Page' LIMIT 1),
  (SELECT id FROM utilisateur WHERE username = 'dev_user' LIMIT 1)
WHERE EXISTS (SELECT 1 FROM ticket WHERE title = 'Bug Login Page');


-- MESSAGE DE CONFIRMATION
SELECT
    CONCAT(
        '✅ Base initialisée: ',
        (SELECT COUNT(*) FROM utilisateur), ' utilisateurs, ',
        (SELECT COUNT(*) FROM projet), ' projets, ',
        (SELECT COUNT(*) FROM tickets), ' tickets'
    ) AS message;