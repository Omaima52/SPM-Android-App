INSERT INTO `allergies` (`id`, `name`, `user_id`) VALUES
(1, 'Sulfonamides', NULL),
(2, 'Cephalosporins', NULL),
(3, 'Penicillin', NULL),
(4, 'Dilantin', NULL),
(5, 'Tetracycline', NULL),
(6, 'Rice', NULL),
(7, 'Poultry Meat', NULL),
(8, 'Milk', NULL);



INSERT INTO `medicines` (`id`, `name`, `user_id`) VALUES
(1, 'Hydrocodone', NULL),
(2, 'Metformin', NULL),
(3, 'losartan', NULL),
(4, 'Albuterol', NULL),
(5, 'Gabapentin', NULL),
(6, 'Antibiotics', NULL),
(7, 'Antihistamines', NULL),
(8, 'Omeprazole', NULL);



INSERT INTO `medicine_allergy` (`medicine_id`, `allergy_id`) VALUES
(1, 1),
(2, 1),
(2, 2),
(3, 2),
(1, 3),
(1, 4),
(3, 4),
(1, 5),
(2, 6),
(4, 6),
(4, 7),
(5, 8);



INSERT INTO `emergency_cases` (`id`, `name`, `symptomes`, `to_avoid`, `to_do`) VALUES
(1, 'AbdominalPain', NULL, NULL, NULL),
(2, 'AnimalBite', NULL, NULL, NULL),
(3, 'Asthma', NULL, NULL, NULL),
(4, 'BrokenTeeth', NULL, NULL, NULL),
(5, 'EyeInjury', NULL, NULL, NULL),
(6, 'Fever', NULL, NULL, NULL),
(7, 'FoodPoisoning', NULL, NULL, NULL),
(8, 'HeartAttack', NULL, NULL, NULL),
(9, 'ScorpionBite', NULL, NULL, NULL),
(10, 'SevereBleeding', NULL, NULL, NULL),
(11, 'SnakeBite', NULL, NULL, NULL);



INSERT INTO `emergency_case_medicine` (`medicine_id`, `emergency_case_id`) VALUES
(2, 1),
(8, 1),
(1, 2),
(2, 2),
(5, 2),
(1, 3),
(4, 3),
(1, 4),
(5, 4),
(7, 4),
(6, 5),
(7, 5),
(1, 6),
(6, 6),
(7, 6),
(3, 7),
(4, 7),
(6, 7),
(1, 8),
(5, 8),
(8, 8),
(1, 9),
(4, 9),
(5, 9),
(2, 10),
(3, 10),
(2, 11),
(3, 11),
(6, 11);