INSERT INTO client (id, name, loyalty_points) VALUES (1, 'John Doe', 5);

INSERT INTO availability (id, start_date, planned_return_date)
VALUES (1, '2023-11-10', '2022-11-15');
INSERT INTO availability (id, start_date, planned_return_date)
VALUES (2, '2023-11-15', '2022-11-20');
INSERT INTO availability (id, start_date, planned_return_date)
VALUES (3, '2023-11-20', '2023-11-25');

INSERT INTO car (id, model, type, availability_id)
VALUES (1, 'BMW_7', 'PREMIUM', 1);
INSERT INTO car (id, model, type, availability_id)
VALUES (2, 'KIA_SORENTO', 'SUV', 2);
INSERT INTO car (id, model, type, availability_id)
VALUES (3, 'SEAT_IBIZA', 'SMALL', 3);