INSERT INTO user (id, name, email) VALUES ('temp1', 'Tetiana', 'tanyaagolovina@gmail.com');
INSERT INTO user (id, name, email) VALUES ('temp2', 'Valeriia', 'golovina.ler1@gmail.com');
INSERT INTO user (id, name, email) VALUES ('temp3', 'Sergey', 'sg@gmail.com');
INSERT INTO user (id, name, email) VALUES ('temp4', 'Diana', 'disukhin@gmail.com');
INSERT INTO user (id, name, email) VALUES ('102088415054003278976', 'Tetiana Holovina', 'tetiana.holovina@gmail.com');


INSERT INTO event (id, title, address, datetime, forecast) VALUES (1, 'Coffee break', 'Kudryashova 14', '2019-06-11 12:30:00', 'Temperature:+23, Clouds');
INSERT INTO event (id, title, address, datetime, forecast) VALUES (2, 'Car wash', 'Bogolubova 18', '2019-06-11 14:30:00', 'Temperature:+21, Rain');
INSERT INTO event (id, title, address, datetime, forecast) VALUES (3, 'Lesson', 'Kudryashova 18', '2019-06-11 12:30:00', 'Temperature:+30, Sun');
INSERT INTO event (id, title, address, datetime, forecast) VALUES (4, 'Manicure', 'Kudryashova 14', '2019-06-11 11:30:00', 'Temperature:+25, Clouds');
INSERT INTO event (id, title, address, datetime, forecast) VALUES (5, 'Sport', 'Kudryashova 14', '2019-06-11 15:30:00', 'Temperature:+27, Rain');
INSERT INTO event (id, title, address, datetime, forecast) VALUES (6, 'Study', 'Kudryashova 18A', '2019-06-11 18:30:00', 'Temperature:+28, Sun');
INSERT INTO event (id, title, address, datetime, forecast) VALUES (7, 'Reading', 'Prospect Heroiv Nebesnoi Sotni 18', '2019-06-11 20:30:00', 'Temperature:+33, Sun');
INSERT INTO event (id, title, address, datetime, forecast) VALUES (8, 'Writing', 'Vasilkovskaya', '2019-06-12 10:30:00', 'Temperature:+23, Clouds');
INSERT INTO event (id, title, address, datetime, forecast) VALUES (9, 'Cinema', 'Ocean Plaza', '2019-06-13 19:00:00', 'Temperature:+22, Clouds');

INSERT INTO plan (id, user_id, event_id, status) VALUES (1,'temp1', 1, 'PLANNED');
INSERT INTO plan (id, user_id, event_id, status) VALUES (2, 'temp1', 2, 'PLANNED');
INSERT INTO plan (id, user_id, event_id, status) VALUES (3, 'temp2', 1, 'PLANNED');
INSERT INTO plan (id, user_id, event_id, status) VALUES (4, 'temp3', 3, 'PLANNED');
INSERT INTO plan (id, user_id, event_id, status) VALUES (5, 'temp4', 3, 'PLANNED');
INSERT INTO plan (id, user_id, event_id, status) VALUES (6, 'temp4', 4, 'PLANNED');
INSERT INTO plan (id, user_id, event_id, status) VALUES (7, 'temp4', 5, 'PLANNED');
INSERT INTO plan (id, user_id, event_id, status) VALUES (8, 'temp3', 6, 'PLANNED');
INSERT INTO plan (id, user_id, event_id, status) VALUES (9, 'temp4', 6, 'PLANNED');
INSERT INTO plan (id, user_id, event_id, status) VALUES (10, 'temp1', 7, 'PLANNED');
INSERT INTO plan (id, user_id, event_id, status) VALUES (11, 'temp2', 8, 'PLANNED');
INSERT INTO plan (id, user_id, event_id, status) VALUES (12, 'temp3', 9, 'PLANNED');
