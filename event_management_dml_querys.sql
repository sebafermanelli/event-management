# 10 statements for insertion
INSERT INTO event
VALUES (1, 'Salon Moto 2023', 'Motorcycles Exposition', 3500, '2023-12-19 09:00:00', '2023-12-22 22:00:00', 'Av. Sarmiento 2704, C1425 CABA',
        'Enjoy live music, delicious food, and connect with like-minded individuals who share your passion for the open road.');
INSERT INTO employee
VALUES (1, 20428378825, 'Juan', 'Rodriguez', 'San Juan 5203, C1425 CABA', '+5493412834729', 'juan.rodriguez@gmail.com', 340000);
INSERT INTO client (id, cuit, business_name, address, phone, email)
VALUES (1, 20224448235, 'Ducati', 'La Madrid 105, C1425 CABA', '+5492384927738', 'ducati@gmail.com'),
       (2, 20239928835, 'Yamaha', 'San Martin 1238, C1425 CABA', '+5494728382976', 'yamaha@gmail.com'),
       (3, 20282734335, 'Honda', 'San Fernando 182, C1425 CABA', '+5494728989281', 'honda@gmail.com'),
       (4, 20299283335, 'Suzuki', 'Lavalle 8912, C1425 CABA', '+5498912989281', 'suzuki@gmail.com'),
       (5, 20222800135, 'Beta', 'Av. Libertador 1723, C1425 CABA', '+5494702937181', 'beta@gmail.com'),
       (6, 20239282335, 'Kawasaki', 'Av. Santa Fe 8913, C1425 CABA', '+5494728002881', 'kawasaki@gmail.com');
INSERT INTO event_employee
VALUES (1, 'Manager', 1, 1);
INSERT INTO room
VALUES (1, 'Main hall', 1500, 1200, 'Available', 1);
INSERT INTO room
VALUES (2, 'North room', 200, 180, 'Available', 1);
INSERT INTO room
VALUES (3, 'South room', 150, 120, 'Available', 1);
INSERT INTO stand
VALUES (1, 480000, 1, 1);
INSERT INTO stand
VALUES (2, 320000, 1, 4);
INSERT INTO presenter
VALUES (1, 20328729935, 'Gerardo', 'Perez', 'Av. Santa Fe 3823, C1425 CABA', '+5494728972982', 'gerardo.perez@gmail.com', 'Mechanical Engineer');
INSERT INTO presentation
VALUES (1, 'Motorcycles on the road',
        'Join us for an exhilarating exploration at the Motorcycles on the Road conference, where enthusiasts, industry experts, and visionaries come together to delve into the dynamic world of two-wheel adventures',
        '2023-12-20 12:00:00', '2023-12-20 13:00:00', 1500, 2, 1);
# 10 statements for updating
UPDATE client
SET phone = '+5492384928192'
WHERE cuit = 20224448235;
UPDATE client
SET phone = '+5494728380012'
WHERE cuit = 20239928835;
UPDATE client
SET phone = '+5494728982003'
WHERE cuit = 20282734335;
UPDATE client
SET phone = '+5498912989823'
WHERE cuit = 20299283335;
UPDATE client
SET phone = '+5494702937819'
WHERE cuit = 20222800135;
UPDATE client
SET phone = '+5494728002887'
WHERE cuit = 20239282335;
UPDATE employee
SET address = 'Av. San Lorenzo 2033, C1425 CABA'
WHERE cuil = 20428378825;
UPDATE presentation
SET ticket_price = 1750
WHERE room_id = 2
  AND start_datetime = '2023-12-20 12:00:00';
UPDATE room
SET capacity = 190
WHERE id = 2;
UPDATE room
SET capacity = 130
WHERE id = 3;
# 10 statements for deletions
DELETE
FROM presentation
WHERE id = 1;
DELETE
FROM event_employee
WHERE event_id = 1;
DELETE
FROM stand
WHERE room_id = 2;
DELETE
FROM employee
WHERE cuil = 20428378825;
DELETE
FROM client
WHERE cuit = 20224448235;
DELETE
FROM client
WHERE cuit = 20239928835;
DELETE
FROM client
WHERE cuit = 20282734335;
DELETE
FROM client
WHERE cuit = 20299283335;
DELETE
FROM client
WHERE cuit = 20222800135;
DELETE
FROM client
WHERE cuit = 20239282335;
DELETE
FROM room
WHERE id = 3;
DELETE
FROM event
WHERE id = 1;
# 5 alter table
ALTER TABLE event
    ADD COLUMN description TEXT;
ALTER TABLE employee
    ADD COLUMN salary DECIMAL;
ALTER TABLE room
    ADD COLUMN capacity INT;
ALTER TABLE room
    ADD COLUMN surface DECIMAL;
ALTER TABLE presenter
    ADD COLUMN specialization VARCHAR(255);
# 1 big statement to join all tables in the database
SELECT *
FROM event
         LEFT JOIN
     event_employee ON event.id = event_employee.event_id
         LEFT JOIN
     employee ON event_employee.employee_id = employee.id
         LEFT JOIN
     room ON event.id = room.event_id
         LEFT JOIN
     stand ON room.id = stand.room_id
         LEFT JOIN
     presentation ON room.id = presentation.room_id
         LEFT JOIN
     client ON stand.client_id = client.id
         LEFT JOIN
     ticket ON event.id = ticket.event_id
         LEFT JOIN
     presenter ON presentation.presenter_id = presenter.id
         LEFT JOIN
     presentation_ticket ON presentation.id = presentation_ticket.presentation_id AND ticket.id = presentation_ticket.ticket_id
         LEFT JOIN
     visitor ON ticket.attendee_id = visitor.id;
# 5 statements with left, right, inner, outer joins
SELECT *
FROM event
         LEFT JOIN
     event_employee ON event.id = event_employee.event_id;
SELECT *
FROM event
         RIGHT JOIN
     room ON event.id = room.event_id;
SELECT *
FROM room
         INNER JOIN
     stand ON room.id = stand.room_id;
SELECT *
FROM event
         LEFT OUTER JOIN
     room ON event.id = room.event_id;
SELECT *
FROM presentation
         LEFT JOIN
     presenter ON presentation.presenter_id = presenter.id;
# 7 statements with aggregate functions and group by and without having
SELECT event.id,
       COUNT(event_employee.employee_id) AS employee_count
FROM event
         LEFT JOIN
     event_employee ON event.id = event_employee.event_id
GROUP BY event.id;
SELECT event.id,
       AVG(presentation.ticket_price) AS average_ticket_price
FROM event
         LEFT JOIN
     room ON event.id = room.event_id
         LEFT JOIN
     presentation ON room.id = presentation.room_id
GROUP BY event.id;
SELECT event.id,
       SUM(ticket.cost) AS total_ticket_cost
FROM event
         LEFT JOIN
     ticket ON event.id = ticket.event_id
GROUP BY event.id;
SELECT event.id,
       MAX(stand.price) AS max_stand_value
FROM event
         LEFT JOIN
     room ON event.id = room.event_id
         LEFT JOIN
     stand ON room.id = stand.room_id
GROUP BY event.id;
SELECT room.event_id,
       AVG(room.capacity) AS average_room_capacity
FROM room
GROUP BY room.event_id;
SELECT event.id,
       COUNT(presentation.id) AS presentation_count
FROM event
         LEFT JOIN
     room ON event.id = room.event_id
         LEFT JOIN
     presentation ON room.id = presentation.room_id
GROUP BY event.id, presentation.id;
SELECT event.id,
       MIN(employee.salary) AS min_salary
FROM event
         LEFT JOIN
     event_employee ON event.id = event_employee.event_id
         LEFT JOIN
     employee ON event_employee.employee_id = employee.id
GROUP BY event.id;
# 7 statements with aggregate functions and group by and with having
SELECT event.id,
       COUNT(presentation.id) AS presentation_count
FROM event
         LEFT JOIN
     room ON event.id = room.event_id
         LEFT JOIN
     presentation ON room.id = presentation.room_id
GROUP BY event.id
HAVING presentation_count > 3;
SELECT event.id,
       AVG(ticket.cost) AS average_ticket_price
FROM event
         LEFT JOIN
     ticket ON event.id = ticket.event_id
GROUP BY event.id
HAVING COUNT(ticket.event_id) > 500;
SELECT event.id,
       SUM(ticket.cost) AS total_ticket_cost
FROM event
         LEFT JOIN
     ticket ON event.id = ticket.event_id
WHERE event.name = 'Salon Moto 2023'
GROUP BY event.id;
SELECT event.id,
       MAX(stand.price) AS max_stand_value
FROM event
         LEFT JOIN
     room ON event.id = room.event_id
         LEFT JOIN
     stand ON room.id = stand.room_id
         LEFT JOIN
     ticket ON event.id = ticket.event_id
GROUP BY event.id
HAVING SUM(ticket.cost) > 5000;
SELECT event.id,
       AVG(room.capacity) AS average_room_capacity
FROM event
         LEFT JOIN
     room ON event.id = room.event_id
GROUP BY event.id
HAVING AVG(room.surface) > 250;
SELECT event.id,
       room.id                     AS room_id,
       COUNT(presentation.room_id) AS presentation_count
FROM event
         LEFT JOIN
     room ON event.id = room.event_id
         LEFT JOIN
     presentation ON room.id = presentation.room_id
         LEFT JOIN
     presentation_ticket ON presentation.id = presentation_ticket.presentation_id
         LEFT JOIN
     ticket ON presentation_ticket.ticket_id = ticket.id
GROUP BY event.id, room.id
HAVING COUNT(ticket.attendee_id) > 100;
SELECT event.id,
       MIN(employee.salary) AS min_salary
FROM event
         LEFT JOIN
     event_employee ON event.id = event_employee.event_id
         LEFT JOIN
     employee ON event_employee.employee_id = employee.id
         LEFT JOIN
     ticket ON event.id = ticket.event_id
GROUP BY event.id
HAVING AVG(ticket.cost) > 200;