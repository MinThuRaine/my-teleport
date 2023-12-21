/*START*/
SET foreign_key_checks = 0;
/*PROCESS*/


DROP TABLE IF EXISTS clients;

--
-- CREATE TABLE `clients` (
--                            `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
--                            `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
--                            `age` int DEFAULT NULL,
--                            `clientStatus` int DEFAULT NULL,
--                            `identityNumber` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
--                            `phoneNo` int DEFAULT NULL,
--                            `email` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
--                            PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

create table clients
(
    id             varchar(255) not null,
    name           varchar(200) null,
    age            int          null,
    clientStatus   int          null,
    identityNumber varchar(150) null,
    phoneNo        int          null,
    email          varchar(150) null,
    constraint clients_pk
        primary key (id)
);

DROP TABLE IF EXISTS client_address;

create table client_address
(
    id                   varchar(255) not null,
    client_id            varchar(255) null,
    street               varchar(100) null,
    postalCode           varchar(100) null,
    region               varchar(100) null,
    township             varchar(100) null,
    houseNo              varchar(100) null,
    suburb               varchar(100) null,
    floor                varchar(100) null,
    deliveryInstructions varchar(150) not null,
    constraint client_address_pk
        primary key (id),
    constraint client_address_clients_id_fk
        foreign key (client_id) references clients (id)
);




/*END*/
SET foreign_key_checks = 1;
/*PROCESS*/
