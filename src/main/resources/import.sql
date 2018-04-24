INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (100, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, '2003/01/22');
INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (200, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', 1, '2003/01/22');
INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (300, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', 0, '2003/01/22');

INSERT INTO AUTHORITY (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO AUTHORITY (ID, NAME) VALUES (2, 'ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (100, 2);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (100, 2);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (200, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (600, 1);

INSERT INTO sport(sport_id, nom, kcalh, kcalkm) VALUES (1, 'Course', 500, 6000);
INSERT INTO sport(sport_id, nom, kcalh, kcalkm) VALUES (2, 'Vélo', 350, 6000);
INSERT INTO sport(sport_id, nom, kcalh, kcalkm) VALUES (3, 'Marche', 150, 6000);

INSERT INTO programme(programme_id, user_id) VALUES (1,200);
INSERT INTO programme(programme_id, user_id) VALUES (2,100);
INSERT INTO programme(programme_id, user_id) VALUES (3,300);
INSERT INTO programme(programme_id, user_id) VALUES (4,200);

INSERT INTO activity(activity_id, date, distance, programme_id, sport_id) VALUES (1, '2018-04-24', 300, 1, 1);
INSERT INTO activity(activity_id, date, distance, programme_id, sport_id) VALUES (2, '2018-03-22', 200, 1, 2);
INSERT INTO activity(activity_id, date, distance, programme_id, sport_id) VALUES (3, '2018-02-12', 100, 2, 3);
INSERT INTO activity(activity_id, date, distance, programme_id, sport_id) VALUES (4, '2018-02-12', 100, 2, 4);

INSERT INTO realisation(realisation_id, activity_id) VALUES (1, 1);
INSERT INTO realisation(realisation_id, activity_id) VALUES (2, 5);
INSERT INTO realisation(realisation_id, activity_id) VALUES (3, 3);
INSERT INTO realisation(realisation_id, activity_id) VALUES (4, 2);
