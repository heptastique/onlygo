INSERT INTO users (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE, OBJECTIFHEBDO) VALUES (100, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, '2003/01/22', -1);
INSERT INTO users (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE, OBJECTIFHEBDO) VALUES (200, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', 1, '2003/01/22', -1);
INSERT INTO users (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE, OBJECTIFHEBDO) VALUES (300, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', 0, '2003/01/22', -1);

INSERT INTO authority (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO authority (ID, NAME) VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (100, 1);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (100, 2);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (200, 1);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (300, 1);

INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (1, 0, 3, 'LUNDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (2, 3, 6, 'LUNDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (3, 6, 9, 'LUNDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (4, 9, 12, 'LUNDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (5, 12, 15, 'LUNDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (6, 15, 18, 'LUNDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (7, 18, 21, 'LUNDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (8, 21, 24, 'LUNDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (9, 0, 3, 'MARDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (10, 3, 6, 'MARDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (11, 6, 9, 'MARDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (12, 9, 12, 'MARDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (13, 12, 15, 'MARDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (14, 15, 18, 'MARDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (15, 18, 21, 'MARDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (16, 21, 24, 'MARDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (17, 0, 3, 'MERCREDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (18, 3, 6, 'MERCREDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (19, 6, 9, 'MERCREDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (20, 9, 12, 'MERCREDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (21, 12, 15, 'MERCREDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (22, 15, 18, 'MERCREDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (23, 18, 21, 'MERCREDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (24, 21, 24, 'MERCREDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (25, 0, 3, 'JEUDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (26, 3, 6, 'JEUDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (27, 6, 9, 'JEUDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (28, 9, 12, 'JEUDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (29, 12, 15, 'JEUDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (30, 15, 18, 'JEUDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (31, 18, 21, 'JEUDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (32, 21, 24, 'JEUDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (33, 0, 3, 'VENDREDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (34, 3, 6, 'VENDREDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (35, 6, 9, 'VENDREDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (36, 9, 12, 'VENDREDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (37, 12, 15, 'VENDREDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (38, 15, 18, 'VENDREDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (39, 18, 21, 'VENDREDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (40, 21, 24, 'VENDREDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (41, 0, 3, 'SAMEDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (42, 3, 6, 'SAMEDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (43, 6, 9, 'SAMEDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (44, 9, 12, 'SAMEDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (45, 12, 15, 'SAMEDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (46, 15, 18, 'SAMEDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (47, 18, 21, 'SAMEDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (48, 21, 24, 'SAMEDI', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (49, 0, 3, 'DIMANCHE', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (50, 3, 6, 'DIMANCHE', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (51, 6, 9, 'DIMANCHE', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (52, 9, 12, 'DIMANCHE', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (53, 12, 15, 'DIMANCHE', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (54, 15, 18, 'DIMANCHE', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (55, 18, 21, 'DIMANCHE', -1);
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR, EVALUATION) VALUES (56, 21, 24, 'DIMANCHE', -1);

INSERT INTO sport(SPORT_ID, NOM, KMH, KCALH) VALUES (1, 'Course', 12, 880);
INSERT INTO sport(SPORT_ID, NOM, KMH, KCALH) VALUES (2, 'Marche', 4, 245);
INSERT INTO sport(SPORT_ID, NOM, KMH, KCALH) VALUES (3, 'Cyclisme', 20, 690);

###### DONNEES POUR LES TESTS
INSERT INTO programme(programme_id, date_debut, user_id) VALUES (100, '2018-04-16', 100);
INSERT INTO programme(programme_id, date_debut, user_id) VALUES (200, '2018-04-23', 100);
INSERT INTO programme(programme_id, date_debut, user_id) VALUES (300, '2018-04-09', 100);
INSERT INTO programme(programme_id, date_debut, user_id) VALUES (400, '2018-04-02', 100);
INSERT INTO programme(programme_id, date_debut, user_id) VALUES (500, '2018-04-16', 200);
INSERT INTO programme(programme_id, date_debut, user_id) VALUES (600, '2018-04-23', 200);

INSERT INTO activity(activity_id, date, distance, estrealisee, programme_id, sport_id) VALUES (100, '2018-04-23', 300, 0, 200, 1);
INSERT INTO activity(activity_id, date, distance, estrealisee, programme_id, sport_id) VALUES (200, '2018-04-24', 500, 1, 300, 2);
INSERT INTO activity(activity_id, date, distance, estrealisee, programme_id, sport_id) VALUES (300, '2018-04-25', 400, 1, 300, 3);
INSERT INTO activity(activity_id, date, distance, estrealisee, programme_id, sport_id) VALUES (400, '2018-04-26', 200, 1, 200, 1);
INSERT INTO activity(activity_id, date, distance, estrealisee, programme_id, sport_id) VALUES (500, '2018-04-23', 300, 0, 300, 1);
INSERT INTO activity(activity_id, date, distance, estrealisee, programme_id, sport_id) VALUES (600, '2018-04-24', 500, 0, 300, 2);
INSERT INTO activity(activity_id, date, distance, estrealisee, programme_id, sport_id) VALUES (700, '2018-04-25', 400, 0, 300, 3);
INSERT INTO activity(activity_id, date, distance, estrealisee, programme_id, sport_id) VALUES (800, '2018-04-26', 200, 0, 300, 1);

INSERT INTO realisation(realisation_id, date, distance, activity_id, programme_id) VALUES (100, '2018-04-23', 300, 400, 200);
INSERT INTO realisation(realisation_id, date, distance, activity_id, programme_id) VALUES (300, '2018-04-25', 540, 200, 300);
INSERT INTO realisation(realisation_id, date, distance, activity_id, programme_id) VALUES (400, '2018-04-26', 380, 300, 300);
###### DONNEES POUR LES TESTS

INSERT INTO zone ( id, name) values ( 1, 'Lyon');

INSERT INTO centreinteret ( id, name) values ( 1, 'Lyon')
