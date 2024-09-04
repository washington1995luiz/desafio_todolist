CREATE TABLE `tasks` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(80) NOT NULL,
    `description` varchar(180) NOT NULL,
    `status` boolean NOT NULL,
    `priority` int NOT NULL
 )
