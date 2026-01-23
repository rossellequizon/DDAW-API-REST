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

INSERT IGNORE INTO utilisateur (USERNAME, EMAIL, PWD, ACTIF)
SELECT 'test_user', 'test@jira.local',
'test123',
true
FROM dual
WHERE NOT EXISTS (
    SELECT 1 FROM utilisateur WHERE USERNAME = 'test_user'
) AND (SELECT COUNT(*) FROM utilisateur) < 3;

-- PROJETS (si les utilisateurs ont été créés)
-- ====================================================================
INSERT IGNORE INTO projet (NOM, DESCRIPTION, DATE_DEBUT, DATE_FIN_PREVUE, STATUS, OWNER_ID)
SELECT 'Site E-commerce', 'Développement boutique en ligne',
CURDATE(), DATE_ADD(CURDATE(), INTERVAL 3 MONTH), 'EN_COURS',
(SELECT ID FROM utilisateur WHERE USERNAME = 'admin')
FROM dual
WHERE EXISTS (SELECT 1 FROM utilisateur WHERE USERNAME = 'admin')
AND NOT EXISTS (SELECT 1 FROM projet WHERE NOM = 'Site E-commerce');

INSERT IGNORE INTO projet (NOM, DESCRIPTION, DATE_DEBUT, DATE_FIN_PREVUE, STATUS, OWNER_ID)
SELECT 'App Mobile', 'Application de gestion de tâches',
CURDATE(), DATE_ADD(CURDATE(), INTERVAL 2 MONTH), 'TODO',
(SELECT ID FROM utilisateur WHERE USERNAME = 'dev_user')
FROM dual
WHERE EXISTS (SELECT 1 FROM utilisateur WHERE USERNAME = 'dev_user')
AND NOT EXISTS (SELECT 1 FROM projet WHERE NOM = 'App Mobile');

-- TICKETS
INSERT IGNORE INTO tickets (TITRE, DESCRIPTION, DATE_CREATION, DATE_ECHEANCE, PRIORITE, STATUS, CREATEUR_ID, ASSIGNE_ID, PROJET_ID)
SELECT 'Bug Login Page', 'Le formulaire de connexion plante sur Firefox',
CURDATE(), DATE_ADD(CURDATE(), INTERVAL 7 DAY), 'HAUTE', 'EN_COURS',
(SELECT ID FROM utilisateur WHERE USERNAME = 'admin'),
(SELECT ID FROM utilisateur WHERE USERNAME = 'dev_user'),
(SELECT ID FROM projet WHERE NOM = 'Site E-commerce')
FROM dual
WHERE EXISTS (SELECT 1 FROM utilisateur WHERE USERNAME = 'admin')
AND EXISTS (SELECT 1 FROM utilisateur WHERE USERNAME = 'dev_user')
AND EXISTS (SELECT 1 FROM projet WHERE NOM = 'Site E-commerce');

INSERT IGNORE INTO tickets (TITRE, DESCRIPTION, DATE_CREATION, DATE_ECHEANCE, PRIORITE, STATUS, CREATEUR_ID, ASSIGNE_ID, PROJET_ID)
SELECT 'Design Homepage', 'Refonte de la page d''accueil',
CURDATE(), DATE_ADD(CURDATE(), INTERVAL 14 DAY), 'MEDIUM', 'TODO',
(SELECT ID FROM utilisateur WHERE USERNAME = 'dev_user'),
(SELECT ID FROM utilisateur WHERE USERNAME = 'test_user'),
(SELECT ID FROM projet WHERE NOM = 'Site E-commerce')
FROM dual
WHERE EXISTS (SELECT 1 FROM utilisateur WHERE USERNAME = 'dev_user')
AND EXISTS (SELECT 1 FROM utilisateur WHERE USERNAME = 'test_user')
AND EXISTS (SELECT 1 FROM projet WHERE NOM = 'Site E-commerce');

-- COMMENTAIRES
INSERT IGNORE INTO commentaire (CONTENU, DATE_COMMENTAIRE, TICKET_ID, AUTEUR_ID)
SELECT 'J''ai reproduit le bug, il vient de l''extension AdBlock',
NOW(),
(SELECT ID FROM tickets WHERE TITRE = 'Bug Login Page'),
(SELECT ID FROM utilisateur WHERE USERNAME = 'dev_user')
FROM dual
WHERE EXISTS (SELECT 1 FROM tickets WHERE TITRE = 'Bug Login Page');

-- MESSAGE DE CONFIRMATION
SELECT
    CONCAT(
        '✅ Base initialisée: ',
        (SELECT COUNT(*) FROM utilisateur), ' utilisateurs, ',
        (SELECT COUNT(*) FROM projet), ' projets, ',
        (SELECT COUNT(*) FROM tickets), ' tickets'
    ) AS message;