INSERT INTO users (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (100, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, '2003/01/22');
INSERT INTO users (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (200, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', 1, '2003/01/22');
INSERT INTO users (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (300, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', 0, '2003/01/22');

INSERT INTO authority (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO authority (ID, NAME) VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (100, 1);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (100, 2);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (200, 1);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (300, 1);

INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (1, 0, 3, 'LUNDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (2, 3, 6, 'LUNDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (3, 6, 9, 'LUNDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (4, 9, 12, 'LUNDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (5, 12, 15, 'LUNDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (6, 15, 18, 'LUNDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (7, 18, 21, 'LUNDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (8, 21, 24, 'LUNDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (9, 0, 3, 'MARDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (10, 3, 6, 'MARDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (11, 6, 9, 'MARDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (12, 9, 12, 'MARDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (13, 12, 15, 'MARDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (14, 15, 18, 'MARDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (15, 18, 21, 'MARDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (16, 21, 24, 'MARDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (17, 0, 3, 'MERCREDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (18, 3, 6, 'MERCREDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (19, 6, 9, 'MERCREDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (20, 9, 12, 'MERCREDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (21, 12, 15, 'MERCREDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (22, 15, 18, 'MERCREDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (23, 18, 21, 'MERCREDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (24, 21, 24, 'MERCREDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (25, 0, 3, 'JEUDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (26, 3, 6, 'JEUDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (27, 6, 9, 'JEUDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (28, 9, 12, 'JEUDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (29, 12, 15, 'JEUDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (30, 15, 18, 'JEUDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (31, 18, 21, 'JEUDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (32, 21, 24, 'JEUDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (33, 0, 3, 'VENDREDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (34, 3, 6, 'VENDREDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (35, 6, 9, 'VENDREDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (36, 9, 12, 'VENDREDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (37, 12, 15, 'VENDREDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (38, 15, 18, 'VENDREDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (39, 18, 21, 'VENDREDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (40, 21, 24, 'VENDREDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (41, 0, 3, 'SAMEDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (42, 3, 6, 'SAMEDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (43, 6, 9, 'SAMEDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (44, 9, 12, 'SAMEDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (45, 12, 15, 'SAMEDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (46, 15, 18, 'SAMEDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (47, 18, 21, 'SAMEDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (48, 21, 24, 'SAMEDI');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (49, 0, 3, 'DIMANCHE');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (50, 3, 6, 'DIMANCHE');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (51, 6, 9, 'DIMANCHE');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (52, 9, 12, 'DIMANCHE');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (53, 12, 15, 'DIMANCHE');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (54, 15, 18, 'DIMANCHE');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (55, 18, 21, 'DIMANCHE');
INSERT INTO timeframe (ID, HEUREDEBUT, HEUREFIN, NOMJOUR) VALUES (56, 21, 24, 'DIMANCHE');

INSERT INTO sport(SPORT_ID, NOM, KMH, KCALH) VALUES (1, 'Course', 12, 880);
INSERT INTO sport(SPORT_ID, NOM, KMH, KCALH) VALUES (2, 'Marche', 4, 245);
INSERT INTO sport(SPORT_ID, NOM, KMH, KCALH) VALUES (3, 'Cyclisme', 20, 690);

###### DONNEES POUR LES TESTS
INSERT INTO programme(programme_id, date_debut, user_id) VALUES (1, '2018-04-16', 100);
INSERT INTO programme(programme_id, date_debut, user_id) VALUES (2, '2018-04-23', 100);
INSERT INTO programme(programme_id, date_debut, user_id) VALUES (3, '2018-04-09', 100);
INSERT INTO programme(programme_id, date_debut, user_id) VALUES (4, '2018-04-02', 100);
INSERT INTO programme(programme_id, date_debut, user_id) VALUES (5, '2018-04-16', 200);
INSERT INTO programme(programme_id, date_debut, user_id) VALUES (6, '2018-04-23', 200);

INSERT INTO activity(activity_id, date, distance, estrealisee, programme_id, sport_id) VALUES (1, '2018-04-23', 300, 1, 2, 1);
INSERT INTO activity(activity_id, date, distance, estrealisee, programme_id, sport_id) VALUES (2, '2018-04-24', 500, 1, 2, 2);
INSERT INTO activity(activity_id, date, distance, estrealisee, programme_id, sport_id) VALUES (3, '2018-04-25', 400, 1, 2, 3);
INSERT INTO activity(activity_id, date, distance, estrealisee, programme_id, sport_id) VALUES (4, '2018-04-26', 200, 0, 2, 1);
INSERT INTO activity(activity_id, date, distance, estrealisee, programme_id, sport_id) VALUES (5, '2018-04-23', 300, 0, 3, 1);
INSERT INTO activity(activity_id, date, distance, estrealisee, programme_id, sport_id) VALUES (6, '2018-04-24', 500, 0, 3, 2);
INSERT INTO activity(activity_id, date, distance, estrealisee, programme_id, sport_id) VALUES (7, '2018-04-25', 400, 0, 3, 3);
INSERT INTO activity(activity_id, date, distance, estrealisee, programme_id, sport_id) VALUES (8, '2018-04-26', 200, 0, 3, 1);
INSERT INTO activity(activity_id, date, distance, estrealisee, programme_id, sport_id) VALUES (9, '2018-04-23', 1000, 1, 2, 3);
INSERT INTO activity(activity_id, date, distance, estrealisee, programme_id, sport_id) VALUES (10, '2018-04-24', 600, 1, 2, 2);
INSERT INTO activity(activity_id, date, distance, estrealisee, programme_id, sport_id) VALUES (11, '2018-04-25', 100, 1, 2, 2);

INSERT INTO realisation(realisation_id, date, distance, activity_id, programme_id) VALUES (1, '2018-04-23', 300, 9, 2);
INSERT INTO realisation(realisation_id, date, distance, activity_id, programme_id) VALUES (2, '2018-04-24', 1200, 10, 2);
INSERT INTO realisation(realisation_id, date, distance, activity_id, programme_id) VALUES (3, '2018-04-25', 300, 11, 2);
INSERT INTO realisation(realisation_id, date, distance, activity_id, programme_id) VALUES (4, '2018-04-23', 350, 1, 2);
INSERT INTO realisation(realisation_id, date, distance, activity_id, programme_id) VALUES (5, '2018-04-25', 540, 2, 2);
INSERT INTO realisation(realisation_id, date, distance, activity_id, programme_id) VALUES (6, '2018-04-26', 380, 3, 2);
###### DONNEES POUR LES TESTS

INSERT INTO zone ( id, name) values ( 1, 'Lyon')

