/*START*/
SET foreign_key_checks = 0;
/*PROCESS*/

DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS order_address;
DROP TABLE IF EXISTS order_items;


CREATE TABLE `orders` (
                          `id` varchar(255) NOT NULL,
                          `status` int DEFAULT NULL,
                          `created_date` DATETIME DEFAULT NULL,
                          `processed_date` DATETIME DEFAULT NULL,
                          `cancelled_date` DATETIME DEFAULT NULL,
                          `client_id` varchar(255) DEFAULT NULL,
                          `tracking_id` varchar(255) DEFAULT NULL,
                          `price` decimal(19,2) DEFAULT NULL,
                          `phoneno` int DEFAULT NULL,
                          `remark` varchar(300) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `order_address` (
                                 `id` varchar(255) NOT NULL,
                                 `order_id` varchar(255) NOT NULL,
                                 `street` varchar(255) DEFAULT NULL,
                                 `postalCode` varchar(255) DEFAULT NULL,
                                 `region` int DEFAULT NULL,
                                 `township` int DEFAULT NULL,
                                 `name` VARCHAR(200) DEFAULT NULL,
                                 `phoneno` VARCHAR(100) DEFAULT NULL,
                                 `email` VARCHAR(100) DEFAULT NULL,
                                 `houseno` VARCHAR(50) DEFAULT NULL,
                                 `suburb` VARCHAR(100) DEFAULT  NULL,
                                 `floor` VARCHAR(50) DEFAULT NULL,
                                 `deliveryinstruction` VARCHAR(150) DEFAULT  NULL,
                                 `label` VARCHAR(50) DEFAULT NULL,
                                 `addresstype` int DEFAULT  NULL,
                                 PRIMARY KEY (`id`),
                                 KEY `order_address_pkey_idx` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `order_items` (
                               `id` varchar(255) NOT NULL,
                               `order_id` varchar(255) NOT NULL,
                               `name` varchar(255) DEFAULT NULL,
                               `price` varchar(255) DEFAULT NULL,
                               `quantity` int DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               KEY `order_items_pkey_idx` (`order_id`),
                               CONSTRAINT `order_items_pkey` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




/*END*/
SET foreign_key_checks = 1;
/*PROCESS*/