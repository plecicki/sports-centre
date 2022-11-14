-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sports_centre
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint NOT NULL,
  `auto_extension` bit(1) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `goal` int DEFAULT NULL,
  `gym` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `student` bit(1) DEFAULT NULL,
  `sub_validity` date DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `swimming_pool` bit(1) DEFAULT NULL,
  `card_id` bigint DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_qvssa6h1me5k6ohvd67jexrh8` (`card_id`),
  CONSTRAINT `FKrxplbc3h7tax4h43tfg5qgc1l` FOREIGN KEY (`card_id`) REFERENCES `cards` (`card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (9,_binary '','1980-01-17','marekk@oppoczta.pl',2,_binary '','Marek','987654321',_binary '\0','2022-12-08','Kowalski',_binary '\0',8),(12,_binary '\0','1996-05-15','piotrmail@mail.pl',1,_binary '\0','Piotr','987654321',_binary '','2022-12-08','Nowak',_binary '\0',11),(15,_binary '\0','1996-12-24','adminMariusz@admin.pl',0,_binary '\0','Mariusz','121212121',_binary '','2022-12-08','Kowal',_binary '',14),(18,_binary '\0','2001-01-10','kasiakasia@kasia.com',2,_binary '\0','Katarzyna','333333333',_binary '','2022-12-08','Johnson',_binary '',17),(21,_binary '\0','1990-12-12','krz.now@krznow.tech',1,_binary '','Krzysztof','111111111',_binary '\0','2022-12-08','Nowaczkiewicz',_binary '',20),(24,_binary '\0','1984-11-14','kuznia@metal.com',2,_binary '\0','Magdalena','321654987',_binary '\0','2022-12-08','Kuźniarz',_binary '',23),(27,_binary '\0','2002-03-06','mysgl@majeskuel.pl',1,_binary '','Grzegorz','444444444',_binary '\0','2022-12-08','Bazodanowy',_binary '\0',26),(30,_binary '\0','2008-01-01','piotrek@pio.pl',1,_binary '','Piotr','777777777',_binary '','2022-12-08','Piotrowski',_binary '',29),(33,_binary '\0','1975-02-12','spring@boot.com',0,_binary '','Małgorzata','888888888',_binary '\0','2022-12-08','SpringBootowa',_binary '',32),(36,_binary '\0','1980-01-23','kamiladesk@desk.pl',1,_binary '','Kamila','098765432',_binary '','2022-12-08','Biurkowa',_binary '',35),(38,_binary '\0','2015-12-23','mariank@marian.net',1,_binary '','Marian','123212321',_binary '','2022-11-12','Kowalczyk',_binary '',1),(59,_binary '','1990-12-12','email1',2,_binary '','name1','phone1',_binary '','2022-12-09','surname1',_binary '',NULL),(67,_binary '','1990-12-12','email1',2,_binary '','name1','phone1',_binary '','2022-12-09','surname1',_binary '',NULL),(100,_binary '','1990-12-12','email1',2,_binary '','name1','phone1',_binary '','2022-12-09','surname1',_binary '',NULL),(108,_binary '','1990-12-12','email1',2,_binary '','name1','phone1',_binary '','2022-12-09','surname1',_binary '',NULL),(229,_binary '\0','2009-02-10','mail@mail.mail',0,_binary '','Piotr','111111111',_binary '','2022-12-09','Łęcicki',_binary '\0',228);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-14 16:14:46
