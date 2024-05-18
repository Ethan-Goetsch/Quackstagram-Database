-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: quackstagram
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `comment_id` int NOT NULL,
  `post_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `message` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `publication_date` datetime DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `comment_user_FK` (`user_id`),
  KEY `comment_post_FK` (`post_id`),
  CONSTRAINT `comment_post_FK` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`),
  CONSTRAINT `comment_user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,1,1,'Behold the arcane secrets unveiled!','2024-05-01 00:00:00'),(2,2,2,'A true masterpiece of eldritch lore.','2024-05-02 00:00:00'),(3,3,3,'Such a mystical journey through the enchanted woods.','2024-05-03 00:00:00'),(4,4,4,'This potion recipe has transformed my brew!','2024-05-04 00:00:00'),(5,5,5,'The dragon’s flight was depicted with fiery passion!','2024-05-05 00:00:00'),(6,6,6,'Your tale of the orc warlord was truly gripping.','2024-05-06 00:00:00'),(7,7,7,'The song of the siren echoed deep within my soul.','2024-05-07 00:00:00'),(8,8,8,'A treasure map that leads to ancient mysteries!','2024-05-08 00:00:00'),(9,9,9,'The shadows of the vampire castle were chilling.','2024-05-09 00:00:00'),(10,10,10,'The lore of the ancient scrolls is enlightening.','2024-05-10 00:00:00'),(11,11,11,'A giants tale that shakes the very earth!','2024-05-11 00:00:00'),(12,12,12,'Bewitched by the witch’s latest concoction.','2024-05-12 00:00:00'),(13,13,13,'Your chronicle of the phoenix rebirth is inspiring.','2024-05-01 00:00:00'),(14,14,14,'The griffin’s majesty was captured splendidly.','2024-05-02 00:00:00'),(15,15,15,'The leprechauns tricks added a spark of mischief!','2024-05-03 00:00:00'),(16,16,16,'Your exploration of the deep sea kraken was thrilling.','2024-05-04 00:00:00'),(17,17,17,'The centaur’s charge was a rush of adrenaline.','2024-05-05 00:00:00'),(18,18,18,'Mermaid tales from the deep blue are captivating.','2024-05-06 00:00:00'),(19,19,19,'Your depiction of the trolls bridge was spot-on.','2024-05-07 00:00:00'),(20,20,20,'Goblins and their mischief never cease to amuse!','2024-05-08 00:00:00');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `content_popularity`
--

DROP TABLE IF EXISTS `content_popularity`;
/*!50001 DROP VIEW IF EXISTS `content_popularity`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `content_popularity` AS SELECT 
 1 AS `post_id`,
 1 AS `like_count`,
 1 AS `comment_count`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `notification_id` int NOT NULL,
  `target_id` int DEFAULT NULL,
  `message` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`notification_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,1,'You have a new follower from the enchanted forest'),(2,2,'Your magical post just got a new like'),(3,3,'New comment on your craft'),(4,4,'Your story has enchanted another reader'),(5,5,'Dragon sightings reported near your last post'),(6,6,'Battle cry appreciated by fellow orcs'),(7,7,'Centaur race event invitation'),(8,8,'Sirens have replied to your song'),(9,9,'Your trick has been uncovered'),(10,10,'New flowers bloom in your forest'),(11,11,'Invitation to the vampire gathering'),(12,12,'Your potion recipe has been featured'),(13,13,'A goat tried to cross the bridge'),(14,14,'Your phoenix tale has inspired many'),(15,15,'Ancient scroll discoveries need your expertise'),(16,16,'New depths have been explored'),(17,17,'Griffin spotted near your location'),(18,18,'Pearl diving contest awaits'),(19,19,'Feast preparations are complete'),(20,20,'Gold found at the end of the rainbow'),(21,2,'elvenarcher Liked Your Post!'),(22,3,'elvenarcher Liked Your Post!'),(23,3,'elvenarcher Liked Your Post!'),(24,17,'elvenarcher Liked Your Post!'),(25,18,'elvenarcher Followed You!');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `picture`
--

DROP TABLE IF EXISTS `picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `picture` (
  `picture_id` int NOT NULL,
  `path_file` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`picture_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `picture`
--

LOCK TABLES `picture` WRITE;
/*!40000 ALTER TABLE `picture` DISABLE KEYS */;
INSERT INTO `picture` VALUES (1,'src/main/resources/img/storage/elvenarcher.jpg'),(2,'src/main/resources/img/storage/wizardoflore.jpg'),(3,'src/main/resources/img/storage/dwarvenfighter.jpg'),(4,'src/main/resources/img/storage/fairydust.jpg'),(5,'src/main/resources/img/storage/dragonrider.jpg'),(6,'src/main/resources/img/storage/orcchieftain.jpg'),(7,'src/main/resources/img/storage/centaurguard.jpg'),(8,'src/main/resources/img/storage/sirenssong.jpg'),(9,'src/main/resources/img/storage/goblintrickster.jpg'),(10,'src/main/resources/img/storage/nymphoftheforest.jpg'),(11,'src/main/resources/img/storage/vampirenight.jpg'),(12,'src/main/resources/img/storage/witchybrew.jpg'),(13,'src/main/resources/img/storage/trollbridge.jpg'),(14,'src/main/resources/img/storage/phoenixreborn.jpg'),(15,'src/main/resources/img/storage/elfscribe.jpg'),(16,'src/main/resources/img/storage/krakenbeast.jpg'),(17,'src/main/resources/img/storage/griffintamer.jpg'),(18,'src/main/resources/img/storage/mermaidpearl.jpg'),(19,'src/main/resources/img/storage/giantogre.jpg'),(20,'src/main/resources/img/storage/leprechaunluck.jpg');
/*!40000 ALTER TABLE `picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `post_id` int NOT NULL,
  `author_id` int NOT NULL,
  `picture_id` int DEFAULT NULL,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `publication_date` datetime NOT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,1,1,'The Hidden Glade','2024-05-02 00:00:00'),(2,2,2,'Secrets of the Arcane','2024-05-11 00:00:00'),(3,3,3,'An Ode to the Forge','2024-05-01 00:00:00'),(4,4,4,'Magic in the Moonlight','2024-05-06 00:00:00'),(5,5,5,'Flight of the Dragons','2024-05-03 00:00:00'),(6,6,6,'War Songs of the Orcs','2024-05-07 00:00:00'),(7,7,7,'Meadow Run at Dawn','2024-05-03 00:00:00'),(8,8,8,'Songs from the Depths','2024-05-06 00:00:00'),(9,9,9,'The Treasure Map','2024-05-06 00:00:00'),(10,10,10,'Whispers of the Old Trees','2024-05-03 00:00:00'),(11,11,11,'The Vampires Ball','2024-05-07 00:00:00'),(12,12,12,'Potions and Hexes','2024-05-12 00:00:00'),(13,13,13,'The Trolls Toll','2024-05-05 00:00:00'),(14,14,14,'Rebirth of the Phoenix','2024-05-10 00:00:00'),(15,15,15,'The Scrolls of Elders','2024-05-01 00:00:00'),(16,16,16,'Mysteries of the Deep','2024-05-08 00:00:00'),(17,17,17,'Griffin Flight Lessons','2024-05-01 00:00:00'),(18,18,18,'Beneath the Waves','2024-05-04 00:00:00'),(19,19,19,'The Ogres Feast','2024-05-04 00:00:00'),(20,20,20,'The Leprechauns Pot of Gold','2024-05-10 00:00:00'),(21,1,20,'The Leprechauns Pot of Gold','2024-05-10 00:00:00');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_relationship`
--

DROP TABLE IF EXISTS `post_relationship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_relationship` (
  `user_id` int NOT NULL,
  `post_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`post_id`),
  KEY `post_relationship_post_FK` (`post_id`),
  CONSTRAINT `post_relationship_post_FK` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`),
  CONSTRAINT `post_relationship_user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_relationship`
--

LOCK TABLES `post_relationship` WRITE;
/*!40000 ALTER TABLE `post_relationship` DISABLE KEYS */;
INSERT INTO `post_relationship` VALUES (5,1),(1,2),(4,2),(1,3),(7,3),(2,4),(1,5),(8,6),(3,7),(10,8),(9,9),(6,10),(11,11),(13,12),(12,13),(14,14),(15,15),(16,16),(1,17),(18,17),(17,18),(19,19),(20,20);
/*!40000 ALTER TABLE `post_relationship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `system_analytics`
--

DROP TABLE IF EXISTS `system_analytics`;
/*!50001 DROP VIEW IF EXISTS `system_analytics`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `system_analytics` AS SELECT 
 1 AS `post_date`,
 1 AS `posts_count`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `bio` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `picture_id` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `user_picture_FK` (`picture_id`),
  CONSTRAINT `user_picture_FK` FOREIGN KEY (`picture_id`) REFERENCES `picture` (`picture_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'elvenarcher','bowandarrow123','Protector of the enchanted woods',1),(2,'wizardoflore','magicwand456','Keeper of ancient spells',2),(3,'dwarvenfighter','axeandshield789','Guardian of the mountain mines',3),(4,'fairydust','pixie123','Spreading magic wherever I fly',4),(5,'dragonrider','firebreath456','Soaring the skies on my dragon',5),(6,'orcchieftain','battleaxe789','Ruler of the orc tribes',6),(7,'centaurguard','hoovesandarrows123','Patroller of the mystical meadows',7),(8,'sirenssong','oceanmelody456','Singer of the sea',8),(9,'goblintrickster','mischief789','Master of pranks and treasures',9),(10,'nymphoftheforest','treespirit123','Protector of the ancient grove',10),(11,'vampirenight','moonlight456','Wanderer under the moon',11),(12,'witchybrew','cauldron789','Concocter of mystical potions',12),(13,'trollbridge','billygoats123','Guardian of the forgotten bridge',13),(14,'phoenixreborn','flameoflife456','Eternal bird of fire and rebirth',14),(15,'elfscribe','quillandink789','Keeper of the enchanted scrolls',15),(16,'krakenbeast','depthsofsea123','Ruler of the ocean depths',16),(17,'griffintamer','windsoar456','Flyer among the clouds',17),(18,'mermaidpearl','deepblue789','Dweller of the coral palaces',18),(19,'giantogre','clubandsmash123','Giant of the northern hills',19),(20,'leprechaunluck','rainbowgold456','Bringer of fortune and gold',20);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `user_behaviour`
--

DROP TABLE IF EXISTS `user_behaviour`;
/*!50001 DROP VIEW IF EXISTS `user_behaviour`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `user_behaviour` AS SELECT 
 1 AS `user_id`,
 1 AS `followers_count`,
 1 AS `followings_count`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `user_relationship`
--

DROP TABLE IF EXISTS `user_relationship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_relationship` (
  `source_id` int NOT NULL,
  `destination_id` int NOT NULL,
  PRIMARY KEY (`source_id`,`destination_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_relationship`
--

LOCK TABLES `user_relationship` WRITE;
/*!40000 ALTER TABLE `user_relationship` DISABLE KEYS */;
INSERT INTO `user_relationship` VALUES (1,2),(1,3),(1,18),(2,4),(2,5),(3,6),(4,7),(5,8),(6,9),(7,10),(8,11),(9,12),(10,13),(11,14),(12,15),(13,16),(14,17),(15,18),(16,19),(17,20),(18,1);
/*!40000 ALTER TABLE `user_relationship` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `update_user_relationship_history_insert` AFTER INSERT ON `user_relationship` FOR EACH ROW call update_user_relationship_history(new.source_id, new.destination_id, 1, now()) */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `update_user_relationship_history_delete` AFTER DELETE ON `user_relationship` FOR EACH ROW call update_user_relationship_history(old.source_id, old.destination_id, 0, now()) */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `user_relationship_history`
--

DROP TABLE IF EXISTS `user_relationship_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_relationship_history` (
  `follow_history_id` int NOT NULL AUTO_INCREMENT,
  `source_id` int DEFAULT NULL,
  `destination_id` int DEFAULT NULL,
  `followed` tinyint(1) DEFAULT NULL,
  `relationship_date` datetime DEFAULT NULL,
  PRIMARY KEY (`follow_history_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_relationship_history`
--

LOCK TABLES `user_relationship_history` WRITE;
/*!40000 ALTER TABLE `user_relationship_history` DISABLE KEYS */;
INSERT INTO `user_relationship_history` VALUES (1,1,2,1,'2024-05-01 00:00:00'),(2,2,3,1,'2024-05-01 00:00:00'),(3,3,4,1,'2024-05-02 00:00:00'),(4,4,5,1,'2024-05-02 00:00:00'),(5,1,2,0,'2024-05-03 00:00:00'),(6,2,1,1,'2024-05-03 00:00:00'),(7,2,1,0,'2024-05-04 00:00:00'),(8,5,6,1,'2024-05-04 00:00:00'),(9,6,5,0,'2024-05-05 00:00:00'),(10,5,6,1,'2024-05-05 00:00:00'),(11,7,8,1,'2024-05-06 00:00:00'),(12,8,7,1,'2024-05-06 00:00:00'),(13,8,7,0,'2024-05-07 00:00:00'),(14,9,10,1,'2024-05-07 00:00:00'),(15,10,9,1,'2024-05-08 00:00:00'),(16,10,9,0,'2024-05-08 00:00:00'),(17,11,12,1,'2024-05-09 00:00:00'),(18,12,11,1,'2024-05-09 00:00:00'),(19,13,14,1,'2024-05-10 00:00:00'),(20,14,13,0,'2024-05-10 00:00:00');
/*!40000 ALTER TABLE `user_relationship_history` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `validate_user_relationship_history_insert` AFTER INSERT ON `user_relationship_history` FOR EACH ROW call delete_invalid_user_relationship_history() */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `validate_user_relationship_history_delete` AFTER DELETE ON `user_relationship_history` FOR EACH ROW call delete_invalid_user_relationship_history() */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping routines for database 'quackstagram'
--
/*!50003 DROP FUNCTION IF EXISTS `get_all_users` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `get_all_users`() RETURNS decimal(5,2)
    DETERMINISTIC
return (select Count(user_id) from user) ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_invalid_user_relationship_history` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_invalid_user_relationship_history`()
DELETE FROM user_relationship_history
WHERE followed = 0 AND follow_history_id IN (
    SELECT u1.follow_history_id
    FROM user_relationship_history u1
    WHERE u1.followed = 0 AND NOT EXISTS (
        SELECT 1
        FROM user_relationship_history u2
        WHERE u2.source_id = u1.source_id
        AND u2.destination_id = u1.destination_id
        AND u2.followed = 1
        AND u2.relationship_date < u1.relationship_date
    )
) ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_follow_history` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_follow_history`(user_id int)
select source_id as user, destination_id as user_followed, followed
from user_relationship_history
where source_id = user_id ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_user_relationship_history` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_user_relationship_history`(source_id int, destination_id int, followed bool, date_time datetime)
insert into user_relationship_history (source_id, destination_id, followed, relationship_date)
	values (source_id, destination_id, followed, date_time) ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `validate_user_relationship_history` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `validate_user_relationship_history`()
SELECT history.follow_history_id
FROM user_relationship_history history
WHERE history.followed = 0 AND NOT EXISTS (
    SELECT 1
    FROM user_relationship_history previous
    WHERE previous.source_id = history.source_id
    AND previous.destination_id = history.destination_id
    AND previous.followed = 1
    AND previous.relationship_date < history.relationship_date
) ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `content_popularity`
--

/*!50001 DROP VIEW IF EXISTS `content_popularity`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `content_popularity` AS select `p`.`post_id` AS `post_id`,coalesce(`l`.`like_count`,0) AS `like_count`,coalesce(`c`.`comment_count`,0) AS `comment_count` from ((`post` `p` left join (select `post_relationship`.`post_id` AS `post_id`,count(`post_relationship`.`user_id`) AS `like_count` from `post_relationship` group by `post_relationship`.`post_id`) `l` on((`p`.`post_id` = `l`.`post_id`))) left join (select `comment`.`post_id` AS `post_id`,count(`comment`.`comment_id`) AS `comment_count` from `comment` group by `comment`.`post_id`) `c` on((`p`.`post_id` = `c`.`post_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `system_analytics`
--

/*!50001 DROP VIEW IF EXISTS `system_analytics`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `system_analytics` AS select `post`.`publication_date` AS `post_date`,count(`post`.`post_id`) AS `posts_count` from `post` group by `post`.`publication_date` having (count(`post`.`post_id`) >= 2) order by `post_date` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `user_behaviour`
--

/*!50001 DROP VIEW IF EXISTS `user_behaviour`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `user_behaviour` AS select `u`.`user_id` AS `user_id`,coalesce(`r`.`followers_count`,0) AS `followers_count`,coalesce(`f`.`followings_count`,0) AS `followings_count` from (((select distinct `user`.`user_id` AS `user_id` from `user`) `u` left join (select `ur2`.`destination_id` AS `destination_id`,count(`ur2`.`destination_id`) AS `followers_count` from `user_relationship` `ur2` group by `ur2`.`destination_id`) `r` on((`u`.`user_id` = `r`.`destination_id`))) left join (select `ur`.`source_id` AS `source_id`,count(`ur`.`source_id`) AS `followings_count` from `user_relationship` `ur` group by `ur`.`source_id`) `f` on((`u`.`user_id` = `f`.`source_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-18 12:19:55
