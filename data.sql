-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               10.6.7-MariaDB - Arch Linux
-- Server OS:                    Linux
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table wetube.users
CREATE TABLE IF NOT EXISTS `users` (
    `id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `created` datetime DEFAULT NULL,
    `edited` datetime DEFAULT NULL,
    `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table wetube.users: ~1 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `created`, `edited`, `email`, `password`, `username`) VALUES
    ('d1edcf54-5a2c-4db0-b05f-3f15d37a78dd', '2022-01-23 00:00:00', '2022-01-23 00:00:00', 'admin@admin.fr', '$2a$10$c51LUFAfZ0UuXHt9hkm3f.l/rCSAgASJSQs2La/qTHXCZuMDMBaLG', 'administrateur');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Dumping structure for table wetube.videos
CREATE TABLE IF NOT EXISTS `videos` (
    `id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `created` datetime(6) DEFAULT NULL,
    `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `edited` datetime(6) DEFAULT NULL,
    `path` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `thumbnail` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `author_id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FKjhdkpdd15l36g7yess2kyiyxe` (`author_id`),
    CONSTRAINT `FKjhdkpdd15l36g7yess2kyiyxe` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table wetube.videos: ~9 rows (approximately)
/*!40000 ALTER TABLE `videos` DISABLE KEYS */;
INSERT INTO `videos` (`id`, `created`, `description`, `edited`, `path`, `thumbnail`, `title`, `type`, `author_id`) VALUES
   ('1c43aa17-90d7-467f-b21f-d55413967654', '2022-01-23 00:00:00.000000', NULL, '2022-01-23 00:00:00.000000', 'ScNNfyq3d_w', 'https://i.ytimg.com/vi/RRGSHvlu9Ss/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAHvnQdAOeMWdIph9gep9jhwii5-A', 'Linkin Park - Castle of Glass', 'YOUTUBE', 'd1edcf54-5a2c-4db0-b05f-3f15d37a78dd'),
   ('206fe68b-f7c6-4e46-bdc7-89229be51f6a', '2022-01-23 00:00:01.000000', NULL, '2022-01-23 00:00:00.000000', 'dQw4w9WgXcQ', 'https://i.ytimg.com/vi/EE4OCYOLjBc/hq720.jpg?sqp=-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAMvRl5VJaihf3aK2P150YtfRLAJQ', 'Why is EVERYONE Buying this $35 Gaming Keyboard?', 'YOUTUBE', 'd1edcf54-5a2c-4db0-b05f-3f15d37a78dd'),
   ('4cef8706-7bd5-4102-b1fe-03922a9c15fe', '2022-01-23 00:00:02.000000', NULL, '2022-01-23 00:00:00.000000', '4T0RZ6ustKQ', 'https://i.ytimg.com/vi/4T0RZ6ustKQ/hqdefault.jpg?sqp=-oaymwEcCPYBEIoBSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLA-xYrl9Nfm9f4GdENifDPDBE3daw', 'Steam Deck Teardown: Everything Valve Said Not to Do!', 'YOUTUBE', 'd1edcf54-5a2c-4db0-b05f-3f15d37a78dd'),
   ('a4e69622-e135-46a2-abe2-3e86eb814166', '2022-01-23 00:00:03.000000', NULL, '2022-01-23 00:00:00.000000', 'HuyioN2YTrM', 'https://i.ytimg.com/vi/HuyioN2YTrM/hq720.jpg?sqp=-oaymwEcCOgCEMoBSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLDzbtr6e_H2LlQm2sDmjmm8AOt4EA', 'DANGER - 4h30', 'YOUTUBE', 'd1edcf54-5a2c-4db0-b05f-3f15d37a78dd'),
   ('d6019eb3-103a-41eb-a8c2-e4b913983f5d', '2022-01-23 00:00:04.000000', NULL, '2022-01-23 00:00:00.000000', 'HjZ4POvk14c', 'https://i.ytimg.com/vi/HjZ4POvk14c/hq720.jpg?sqp=-oaymwEcCOgCEMoBSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLCZITL1cS_yWlmBbRthiua_4fKd7w', 'Valve Left Me Unsupervised: Steam Deck Hardware Review', 'YOUTUBE', 'd1edcf54-5a2c-4db0-b05f-3f15d37a78dd'),
   ('d9c1cb0b-407b-41c6-b98f-595a0dedf7c6', '2022-01-23 00:00:05.000000', NULL, '2022-01-23 00:00:00.000000', 'cznpPMomJXw', 'https://i.ytimg.com/vi/cznpPMomJXw/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLA57PGJOUjAPjaXSDtEpnLQuB5LVg', 'DANGER - 22h39', 'YOUTUBE', 'd1edcf54-5a2c-4db0-b05f-3f15d37a78dd'),
   ('e5cd43c4-2743-471e-ae95-b1b24cc59e48', '2022-01-23 00:00:06.000000', NULL, '2022-01-23 00:00:00.000000', 'g_NK7B2xfsc', 'https://i.ytimg.com/vi/g_NK7B2xfsc/hqdefault.jpg?sqp=-oaymwEcCPYBEIoBSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLBsfTjkJuiJlbGu1nk-UZv7MCum-w', 'Implementing Shops into my Farming Game!', 'YOUTUBE', 'd1edcf54-5a2c-4db0-b05f-3f15d37a78dd'),
   ('fe3c79b2-8d7c-4177-9dde-edfbd92f6bb0', '2022-01-23 00:00:07.000000', NULL, '2022-01-23 00:00:00.000000', '5R6ug5NMC1M', 'https://i.ytimg.com/vi/5R6ug5NMC1M/hq720.jpg?sqp=-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAPPuqgRtx_dQNZbqV-lzbE22Kzxw', 'How to OPTIMIZE YOUR CODE!', 'YOUTUBE', 'd1edcf54-5a2c-4db0-b05f-3f15d37a78dd'),
   ('fff4bfb4-2cae-475f-b0e6-b5151d5c184a', '2022-01-23 00:00:08.000000', NULL, '2022-01-23 00:00:00.000000', 'SzClkMxdsgI', 'https://i.ytimg.com/vi/SzClkMxdsgI/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLBF77aI7Wr9FbWeEW9b4d1pFGQM9Q', 'She - Coloris', 'YOUTUBE', 'd1edcf54-5a2c-4db0-b05f-3f15d37a78dd');
/*!40000 ALTER TABLE `videos` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
