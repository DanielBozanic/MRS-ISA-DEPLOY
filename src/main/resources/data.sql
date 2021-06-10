insert into pharmacy (name, address, city, country, description, rating, appointment_price, num_of_rating) values ('Dinuteka', 'Promenada1', 'NS', 'RS', 'Apoteka koja razbija glavu', 5, 1000.0, 1);
insert into pharmacy (name, address, city, country, description, rating, appointment_price, num_of_rating) values ('Drusoteka', 'Promenada3', 'BG', 'DE', 'Apoteka koja leci glavu', 2, 800.0, 1);


insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, first_login, rating, category_name, collected_points, enabled, penals) values ('PATIENT', 'mrs_isa_2021_t15_3@hotmail.com', 'Marko', 'Marković', 'Negde69', 'NS', 'Srbija', '0606023113', 'laki', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', null, null, 0, 0, true, 0);
insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, first_login, rating, category_name, collected_points, enabled, penals) values ('PATIENT', 'mrs_isa_2021_t15_2@hotmail.com', 'Petar', 'Markuza', 'Negde420', 'NS', 'Srbija', '0606023132', 'kek', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', null, null, 0, 0, true, 0);
insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, first_login, rating, category_name, collected_points, enabled, penals) values ('PATIENT', 'mrs_isa_2021_t15_4@hotmail.com', 'Dinu', 'Dinu', 'Negde1', 'NS', 'Srbija', '0606223112', 'kul', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', null, null, 1, 2500, true, 0);
insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, first_login, rating, category_name, collected_points, enabled, penals) values ('PATIENT', 'keter@gmail.com', 'Ikea', 'Velika', 'Negde42', 'BG', 'Srbija', '0616023113', 'Keter', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', null, null, 0, 0, true, 0);



insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, first_login, rating, enabled, num_of_rating) values ('PHARMACIST', 'farmaceut@gmail.com', 'Micko', 'Mica', 'Telep69', 'NS', 'Srbija', '0620602311', 'farmaceut', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', false, 1, true, 1);
insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, first_login, rating, enabled, num_of_rating) values ('DERMATOLOGIST', 'dermatolog@gmail.com', 'Petar', 'Markuza', 'Telep420', 'NS', 'Srbija', '0606023133', 'dermatolog', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', false, 5, true, 1);

insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, pharmacy_id, enabled, first_login) values ('PHARMACY_ADMIN', 'first@gmail.com', 'Pera', 'Peric', 'asdf', 'NS', 'Srbija', '0601234567', 'pharmacyadmin1', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', 1, true, false);
insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, pharmacy_id, enabled, first_login) values ('PHARMACY_ADMIN', 'second@gmail.com', 'Mika', 'Mikic', 'asdf', 'NS', 'Srbija', '0601234568', 'pharmacyadmin2', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', 1, true, false);
insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, pharmacy_id, enabled, first_login) values ('PHARMACY_ADMIN', 'third@gmail.com', 'Sara', 'Saric', 'asdf', 'NS', 'Srbija', '0601234569', 'pharmacyadmin3', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', 2, true, false);

insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, enabled, first_login) values ('SUPPLIER', 'supp1@gmail.com', 'Marko', 'Jovanovic', 'asdf', 'NS', 'Srbija', '0601234569', 'mare', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', true, false);
insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, enabled, first_login) values ('SUPPLIER', 'supp2@gmail.com', 'Jovan', 'Petrovic', 'asdf', 'NS', 'Srbija', '0601234569', 'jova', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', true, false);
insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, enabled, first_login) values ('SUPPLIER', 'supp3@gmail.com', 'Stefan', 'Teodorovic', 'asdf', 'NS', 'Srbija', '0601234569', 'stefan', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', true, false);

insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, first_login, rating, enabled, num_of_rating) values ('PHARMACIST', 'workaholic@gmail.com', 'Mika', 'Markuka', 'Telep', 'NS', 'Srbija', '0620602311', 'workaholic', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', false, 1, true, 1);
insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, enabled, first_login) values ('SYSTEM_ADMIN', 'admin@gmail.com', 'Ranko', 'Urosevic', 'asdf', 'NS', 'Srbija', '0601234569', 'rane', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', true, false);
insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, first_login, rating, enabled, num_of_rating) values ('PHARMACIST', 'firster@gmail.com', 'Moka', 'Markuka', 'Telep', 'NS', 'Srbija', '0623602311', 'firstlog', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', true, 1, true, 1);
insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, first_login, rating, enabled, num_of_rating) values ('DERMATOLOGIST', 'firster2@gmail.com', 'Mokadon', 'Markukadon', 'Telep', 'NS', 'Srbija', '0623602511', 'firstlogder', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', true, 1, true, 1);




insert into role (name) values ('ROLE_PATIENT');
insert into role (name) values ('ROLE_SYSTEM_ADMIN');
insert into role (name) values ('ROLE_PHARMACY_ADMIN');
insert into role (name) values ('ROLE_SUPPLIER');
insert into role (name) values ('ROLE_DERMATOLOGIST');
insert into role (name) values ('ROLE_PHARMACIST');

insert into user_role (user_id, role_id) values (1, 1);
insert into user_role (user_id, role_id) values (2, 1);
insert into user_role (user_id, role_id) values (3, 1);
insert into user_role (user_id, role_id) values (4, 1);
insert into user_role (user_id, role_id) values (5, 6);
insert into user_role (user_id, role_id) values (6, 5);
insert into user_role (user_id, role_id) values (7, 3);
insert into user_role (user_id, role_id) values (8, 3);
insert into user_role (user_id, role_id) values (9, 3);
insert into user_role (user_id, role_id) values (10, 4);
insert into user_role (user_id, role_id) values (11, 4);
insert into user_role (user_id, role_id) values (12, 4);
insert into user_role (user_id, role_id) values (13, 6);
insert into user_role (user_id, role_id) values (14, 2);
insert into user_role (user_id, role_id) values (15, 6);
insert into user_role (user_id, role_id) values (16, 5);


insert into employment (EMPLOYMENT_TYPE, end, start, pharmacy_id, pharmacist_id) values ('EMPLOYMENT_PHARMACIST', 20, 9, 1, 5);
insert into employment (EMPLOYMENT_TYPE, end, start, pharmacy_id, dermatologist_id) values ('EMPLOYMENT_DERMATOLOGIST', 20, 8, 1, 6);
insert into employment (EMPLOYMENT_TYPE, end, start, pharmacy_id, dermatologist_id) values ('EMPLOYMENT_DERMATOLOGIST', 19, 9, 2, 6);
insert into employment (EMPLOYMENT_TYPE, end, start, pharmacy_id, pharmacist_id) values ('EMPLOYMENT_PHARMACIST', 24, 0, 1, 13);
insert into employment (EMPLOYMENT_TYPE, end, start, pharmacy_id, pharmacist_id) values ('EMPLOYMENT_PHARMACIST', 20, 9, 1, 15);
insert into employment (EMPLOYMENT_TYPE, end, start, pharmacy_id, dermatologist_id) values ('EMPLOYMENT_DERMATOLOGIST', 20, 8, 1, 16);

insert into appointment (APPOINTMENT_TYPE, start, end, price, discount, patient_id, pharmacist_id, dermatologist_id, pharmacy_id, done) values ('APPOINTMENT_PHARMACIST', '2021-01-22 18:30:00', '2021-01-22 19:30:00', 1000, 0, 1, 5, null, 1, true);
insert into appointment (APPOINTMENT_TYPE, start, end, price, discount, patient_id, pharmacist_id, dermatologist_id, pharmacy_id, done) values ('APPOINTMENT_PHARMACIST', '2021-01-22 19:45:00', '2021-01-22 20:30:00', 1000, 0, 1, 5, null, 1, true);
insert into appointment (APPOINTMENT_TYPE, start, end, price, discount, patient_id, pharmacist_id, dermatologist_id, pharmacy_id, done) values ('APPOINTMENT_PHARMACIST', '2021-01-23 12:30:00', '2021-01-23 17:30:00', 1000, 0, 2, 5, null, 1, true);
insert into appointment (APPOINTMENT_TYPE, start, end, price, discount, patient_id, pharmacist_id, dermatologist_id, pharmacy_id, done) values ('APPOINTMENT_PHARMACIST', '2021-02-22 18:30:00', '2021-02-22 19:30:00', 1000, 0, 3, 5, null, 1, true);

insert into appointment (APPOINTMENT_TYPE, start, end, price, discount, patient_id, pharmacist_id, dermatologist_id, pharmacy_id, done) values ('APPOINTMENT_PHARMACIST', '2021-06-01 12:30:00', '2021-06-01 14:00:00', 1000, 0, 3, 5, null, 1, false);
insert into appointment (APPOINTMENT_TYPE, start, end, price, discount, patient_id, pharmacist_id, dermatologist_id, pharmacy_id, done) values ('APPOINTMENT_DERMATOLOGIST', '2021-06-01 17:30:00', '2021-06-01 18:45:00', 1000, 0, 2, null, 6, 2, false);

insert into appointment (APPOINTMENT_TYPE, start, end, price, discount, patient_id, pharmacist_id, dermatologist_id, pharmacy_id, done) values ('APPOINTMENT_DERMATOLOGIST', '2021-01-22 16:30:00', '2021-01-22 18:30:00', 1000, 0, 1, null, 6, 1, true);
insert into appointment (APPOINTMENT_TYPE, start, end, price, discount, patient_id, pharmacist_id, dermatologist_id, pharmacy_id, done) values ('APPOINTMENT_DERMATOLOGIST', '2021-02-22 9:30:00', '2021-02-22 10:00', 1000, 0, 4, null, 6, 2, true);

insert into appointment (APPOINTMENT_TYPE, start, end, price, discount, patient_id, pharmacist_id, dermatologist_id, pharmacy_id, done) values ('APPOINTMENT_DERMATOLOGIST', '2021-05-22 16:30:00', '2021-05-22 18:30:00', 1000, 0, null, null, 6, 1, false);
insert into appointment (APPOINTMENT_TYPE, start, end, price, discount, patient_id, pharmacist_id, dermatologist_id, pharmacy_id, done) values ('APPOINTMENT_DERMATOLOGIST', '2021-06-22 9:30:00', '2021-06-22 10:00', 1000, 0, null, null, 6, 2, false);

insert into absence (description, start, end, approved, doctor_id) values ("Ocu kuci!", '2021-06-10 16:30:00', '2021-06-10 19:30:00', true, 5);
insert into absence (description, start, end, approved, doctor_id) values ("Ocu kuci!", '2021-06-15 9:30:00', '2021-06-20 19:30:00', false, 5);
insert into absence (description, start, end, approved, doctor_id) values ("Ocu kuci!", '2021-06-15 9:30:00', '2021-06-19 19:30:00', true, 5);
insert into absence (description, start, end, approved, doctor_id) values ("Ocu kuci!", '2021-06-15 9:30:00', '2021-06-20 19:30:00', true, 6);

insert into medicine (medicine_code, name, medicine_type, form, composition, manufacturer, prescription, additional_comments, average_rating, points, num_of_rating)
	values ('s123', 'medicine1', 0, 1, 'Paracetamol 500 mg, Maize starch, potassium sorbate (E 202), purified talc, stearic acid, povidone\nstarch pregelatinised, hypromellose, triacetin', 'GE Healthcare', true, 'Not for every day usage.', 4, 42, 1);
insert into medicine (medicine_code, name, medicine_type, form, composition, manufacturer, prescription, additional_comments, average_rating, points, num_of_rating)
	values ('s124', 'medicine2', 0, 1, 'random stuff', 'mrsisa', false, 'comment', 2, 50, 1);
insert into medicine (medicine_code, name, medicine_type, form, composition, manufacturer, prescription, additional_comments, average_rating, points, num_of_rating)
	values ('s125', 'medicine3', 1, 2, 'random stuff', 'mrsisa', true, null, 2, 40, 1);
insert into medicine (medicine_code, name, medicine_type, form, composition, manufacturer, prescription, additional_comments, average_rating, points, num_of_rating)
	values ('s126', 'bensedin', 3, 2, 'drugs', 'mrsisa', true, 'commentary channel', 3, 70, 1);
insert into medicine (medicine_code, name, medicine_type, form, composition, manufacturer, prescription, additional_comments, average_rating, points, num_of_rating)
	values ('s127', 'bromazepam', 3, 2, 'kinda cool', 'actavis', true, null, 5, 20, 1);
insert into medicine (medicine_code, name, medicine_type, form, composition, manufacturer, prescription, additional_comments, average_rating, points, num_of_rating)
	values ('s128', 'actasulid', 1, 2, 'noice', 'actavis', false, 'commentary channel', 1, 63, 1);
insert into medicine (medicine_code, name, medicine_type, form, composition, manufacturer, prescription, additional_comments, average_rating, points)
	values ('s129', 'Povidone-iodine', 4, 7, 'Contains from 9.0% to 12.0% available iodine', 'Pfizer', true, 'Side effects include skin irritation and sometimes swelling', 4, 35);
insert into medicine (medicine_code, name, medicine_type, form, composition, manufacturer, prescription, additional_comments, average_rating, points)
	values ('s130', 'Chlorhexidine', 4, 7, 'Contains 0.12% of the medicinal ingredient, \nchlorhexidine gluconate and the following non-medicinal ingredients: alcohol, FD&C Blue # 1, flavour, glycerin, PEG 40-sorbitan diisostearate,
					\nsaccharin sodium and USP Purified water.', 'Basic Pharma Life Science Pvt. Ltd.', true, 'Used for cleaning wounds, preventing dental plaque, treating yeast infections of the mouth, and to keep urinary catheters from blocking', 3, 20);
insert into medicine (medicine_code, name, medicine_type, form, composition, manufacturer, prescription, additional_comments, average_rating, points)
	values ('s131', 'Imitrex', 5, 2, 'Contains the inactive ingredients croscarmellose sodium, dibasic calcium phosphate, \nmagnesium stearate, microcrystalline cellulose, and sodium bicarbonate.', 'GlaxoSmithKline', true, 
			'It used to treat migraine headaches in adults. Imitrex will only treat a headache', 5, 10);
	

insert into substitute_medicine (medicine_id, substitute_medicine_id) values (1, 2);
insert into substitute_medicine (medicine_id, substitute_medicine_id) values (1, 3);
insert into substitute_medicine (medicine_id, substitute_medicine_id) values (2, 1);
insert into substitute_medicine (medicine_id, substitute_medicine_id) values (4, 1);
insert into substitute_medicine (medicine_id, substitute_medicine_id) values (4, 2);
insert into substitute_medicine (medicine_id, substitute_medicine_id) values (4, 3);

insert into allergy (medicine_id, patient_id) values (1, 3);
insert into allergy (medicine_id, patient_id) values (2, 2);
insert into allergy (medicine_id, patient_id) values (2, 1);
insert into allergy (medicine_id, patient_id) values (4, 1);

insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (500, 0, 1, 1);
insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (600, 400, 1, 2);
insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (1000, 50, 1, 3);
insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (10, 5000, 1, 4);
insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (1000, 50, 1, 5);
insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (100, 10, 1, 6);
insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (200, 25, 1, 7);
insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (150, 15, 1, 8);
insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (300, 30, 1, 9);
insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (500, 500, 2, 1);
insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (800, 800, 2, 2);
insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (400, 1000, 2, 3);
insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (200, 3000, 2, 6);
insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (100, 10, 2, 7);
insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (250, 75, 2, 8);
insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (400, 50, 2, 9);

insert into medicine_supply (supplier_id, medicine_id, quantity) values (10, 1, 200);
insert into medicine_supply (supplier_id, medicine_id, quantity) values (10, 2, 100); 
insert into medicine_supply (supplier_id, medicine_id, quantity) values (10, 3, 500);
insert into medicine_supply (supplier_id, medicine_id, quantity) values (10, 4, 300);
insert into medicine_supply (supplier_id, medicine_id, quantity) values (10, 5, 100);
insert into medicine_supply (supplier_id, medicine_id, quantity) values (11, 1, 50);
insert into medicine_supply (supplier_id, medicine_id, quantity) values (11, 2, 0); 
insert into medicine_supply (supplier_id, medicine_id, quantity) values (11, 3, 15);
insert into medicine_supply (supplier_id, medicine_id, quantity) values (12, 1, 150);
insert into medicine_supply (supplier_id, medicine_id, quantity) values (12, 2, 600); 
insert into medicine_supply (supplier_id, medicine_id, quantity) values (12, 3, 100);

insert into purchase_order (purchase_order_name, due_date_offer) values ('order1', '2021-07-05');
insert into purchase_order (purchase_order_name, due_date_offer) values ('order2', '2021-07-01');
insert into purchase_order (purchase_order_name, due_date_offer) values ('order3','2021-07-10');

insert into purchase_order_medicine (purchase_order_id,  medicine_id, quantity) values (1, 1, 20);
insert into purchase_order_medicine (purchase_order_id,  medicine_id, quantity) values (1, 2, 30);
insert into purchase_order_medicine (purchase_order_id,  medicine_id, quantity) values (1, 3, 15);

insert into purchase_order_medicine (purchase_order_id, medicine_id, quantity) values (2, 1, 5);
insert into purchase_order_medicine (purchase_order_id, medicine_id, quantity) values (2, 3, 10);

insert into purchase_order_medicine (purchase_order_id,  medicine_id, quantity) values (3, 3, 20);

insert into purchase_order_supplier (purchase_order_id, supplier_id, delivery_date, price, offer_status) values (1, 10, '2021-08-10', 1000, 0);
insert into purchase_order_supplier (purchase_order_id, supplier_id, delivery_date, price, offer_status) values (2, 10, '2021-08-10', 900, 1);
insert into purchase_order_supplier (purchase_order_id, supplier_id, delivery_date, price, offer_status) values (3, 10, '2021-08-26', 700, 2);

insert into complaints (complaint_type, text, patient_id, dermatologist_id, pharmacist_id, pharmacy_id) values ('COMPLAINT_DERMATOLOGIST', 'Dermatologist has given me the wrong medicine!', 1, 6, null, null);
insert into complaints (complaint_type, text, patient_id, dermatologist_id, pharmacist_id, pharmacy_id) values ('COMPLAINT_DERMATOLOGIST', 'Can you please fire dermatologist Peter? He is very unprofessional!', 1, 6, null, null);

insert into category (category_name, required_number_of_points, discount) values (0, 0, 0);
insert into category (category_name, required_number_of_points, discount) values (1, 2000, 5);
insert into category (category_name, required_number_of_points, discount) values (2, 5000, 10);

insert into appointment_consultation_points (type, points) values ('APPOINTMENT', 50);
insert into appointment_consultation_points (type, points) values ('CONSULTATION', 30);

insert into promotion (starting_date, ending_date, description, pharmacy_id) values ('2021-05-10', '2021-07-10', 'PROMOTION ON ANTIBIOTICS 30% off', 1);
insert into promotion (starting_date, ending_date, description, pharmacy_id) values ('2021-05-20', '2021-07-20', 'PROMOTION ON ANALGESICS 10% off', 1);
insert into promotion (starting_date, ending_date, description, pharmacy_id) values ('2021-04-10', '2021-05-10', 'PROMOTION ON ALL MEDICINE 20% off', 2);
insert into promotion (starting_date, ending_date, description, pharmacy_id) values ('2021-05-10', '2021-07-10', 'PROMOTION ON ANTIHISTAMINE 5% off', 2);

insert into medicine_needed (requested, pharmacy_id, medicine_id) values ('2021-05-10', 1, 2);
insert into medicine_needed (requested, pharmacy_id, medicine_id) values ('2021-05-10', 1, 3);

--insert into patient_sub_pharmacy (patient_id, pharmacy_id, subscribed) values (1, 1, true);
--insert into patient_sub_pharmacy (patient_id, pharmacy_id, subscribed) values (1, 2, true);