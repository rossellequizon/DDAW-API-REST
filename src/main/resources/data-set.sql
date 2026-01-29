-- UTILISATEUR SYSTEME
INSERT IGNORE INTO utilisateur (username, email, pwd, actif)
VALUES ('deleted_user', 'deleted@system.local', 'deleted', b'0');


-- UTILISATEURS DEMO
INSERT IGNORE INTO utilisateur (username, email, pwd, actif)
VALUES ('admin', 'admin@jira.local', 'admin1234', b'1');

INSERT IGNORE INTO utilisateur (username, email, pwd, actif)
VALUES ('dev_user', 'dev@jira.local', 'dev1234', b'1');


-- PROJETS (owner_id = id de admin)
INSERT INTO projet (creation_date, projet_description, projet_name, projet_status, owner_id)
SELECT
  NOW(6),
  'Projet Jira - Backlog initial',
  'Jira API',
  'EN_COURS',
  (SELECT id FROM utilisateur WHERE username='admin' LIMIT 1)
WHERE NOT EXISTS (SELECT 1 FROM projet WHERE projet_name='Jira API');

INSERT INTO projet (creation_date, projet_description, projet_name, projet_status, owner_id)
SELECT
  NOW(6),
  'Projet de test pour tickets',
  'Demo Project',
  'TODO',
  (SELECT id FROM utilisateur WHERE username='admin' LIMIT 1)
WHERE NOT EXISTS (SELECT 1 FROM projet WHERE projet_name='Demo Project');


-- TICKETS (creator_id = admin, assigne_id = dev_user optionnel)
INSERT INTO ticket (creation_date, deadline, ticket_description, ticket_priority, ticket_status, title, assigne_id, creator_id, projet_id)
SELECT
  NOW(6),
  '2026-02-15',
  'Créer les endpoints CRUD utilisateurs',
  'HIGH',
  'TODO',
  'CRUD utilisateurs',
  NULL,
  (SELECT id FROM utilisateur WHERE username='admin' LIMIT 1),
  (SELECT id FROM projet WHERE projet_name='Jira API' LIMIT 1)
WHERE NOT EXISTS (SELECT 1 FROM ticket WHERE title='CRUD utilisateurs');

INSERT INTO ticket (creation_date, deadline, ticket_description, ticket_priority, ticket_status, title, assigne_id, creator_id, projet_id)
SELECT
  NOW(6),
  '2026-03-01',
  'Mettre en place Docker Compose',
  'MEDIUM',
  'EN_COURS',
  'Docker compose',
  (SELECT id FROM utilisateur WHERE username='dev_user' LIMIT 1),
  (SELECT id FROM utilisateur WHERE username='admin' LIMIT 1),
  (SELECT id FROM projet WHERE projet_name='Jira API' LIMIT 1)
WHERE NOT EXISTS (SELECT 1 FROM ticket WHERE title='Docker compose');

INSERT INTO ticket (creation_date, deadline, ticket_description, ticket_priority, ticket_status, title, assigne_id, creator_id, projet_id)
SELECT
  NOW(6),
  NULL,
  'Ajouter gestion tags',
  'LOW',
  'TODO',
  'Tags',
  NULL,
  (SELECT id FROM utilisateur WHERE username='admin' LIMIT 1),
  (SELECT id FROM projet WHERE projet_name='Demo Project' LIMIT 1)
WHERE NOT EXISTS (SELECT 1 FROM ticket WHERE title='Tags');

-- COMMENTAIRES (on commente un ticket existant)
INSERT INTO commentaire (contenu, creation_date, ticket_id, auteur_id)
SELECT
  'J''ai reproduit le bug, il vient de l''extension AdBlock',
  NOW(6),
  (SELECT id FROM ticket WHERE title = 'Docker compose' LIMIT 1),
  (SELECT id FROM utilisateur WHERE username = 'dev_user' LIMIT 1)
WHERE EXISTS (SELECT 1 FROM ticket WHERE title = 'Docker compose');
