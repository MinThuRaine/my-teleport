/*START*/
SET foreign_key_checks = 0;
/*PROCESS*/



DROP TABLE IF EXISTS agents;

CREATE TABLE `agents` (
                          `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                          `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                          `age` int DEFAULT NULL,
                          `status` int DEFAULT NULL,
                          `identityNumber` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                          `phoneNo` int DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;








/*END*/
SET foreign_key_checks = 1;
/*PROCESS*/
