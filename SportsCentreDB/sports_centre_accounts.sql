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
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `account_id` bigint NOT NULL,
  `password_hash` varchar(255) DEFAULT NULL,
  `password_salt` varchar(255) DEFAULT NULL,
  `role` int DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `UK_k8h1bgqoplx0rkngj01pm1rgp` (`username`),
  UNIQUE KEY `UK_e4w4av1wrhanry7t6mxt42nou` (`user_id`),
  CONSTRAINT `FKnjuop33mo69pd79ctplkck40n` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (10,'4096984942a1c1f56e9bc0bf15930f1fec41647cb409d5d356c945102d9d25291ea1ea51847e39631069ca00b8b2b7e7cb76293a80c60cd1b7791b5355d6591b','??????????⓿??ଡ଼䲨뒢??',0,'1',9),(13,'de5a12765d53ecdb45dace43cbe92f999eb5c73a0ac094e1fd71b18254565c23a66734ee2dd34bb7e7448aecfca3c0dc1738e4602d4a5852cd8cdd8a6b8c1641','?ے???????䁪?礁?멾?壢헸妪?骵',0,'piotrek',12),(16,'e47c72ca081a6cefdd26a031dd30bdad78ad29c10a6209bf1260dd33941cc8ae13e7d6022c8263a00e30ba416c4bf95bd3c927b50e291b4a674cc5b6a18b7f67','晗ʥ??劳籼???废???????돏ᶌꪦ',1,'2',15),(19,'ab85f9af956dccefa81bb788d9cbdaaee19564e5cb068861658fb7fbb17bb040ac414125db916c301bec44ac1d7c72bdf6978bdf2ddedfc69448594e68fa6a93','澗閾??????쳆???᮴?壈???胋',1,'kaska',18),(22,'0fa33673cfa7a96611e5817ad7d32377adb4a540d9f635bdb0f30138550c7682bc536ba1a7723d57c61fc691ebf1b3ec2e7eb137de1fe933580bb674ea23a3f1','ׁ?喨?㭕??㡐??輙䑯믂椈?㕰?⾭䣦?౪Ｍᗚ',1,'adminkrz',21),(25,'b1cc82da12a667b84dae70004e0e6cc87dad77adb4369cc5176911d303fdefc78b0ab715f2be2df2d268147b48b81e7a7ffc5f86b3655ef0de6a001fce892e1e','???????켯???【籊??裷眘?疅',1,'magddgam',24),(28,'2c2b6105c2d99984cd97457a94d1c02c8400638269c6342e7a5766c8a5b7ac67e9c28a04113a46b2f6841dd2dbd58ada2d5b10062711edcf95f4a395d5b9f59d','藗?볼걜먦㡋??갌ᨛ㉳礹쟺????????',0,'grzegorz',27),(31,'80d472b644d6979479c28ae350ec6b6ed4c7e047cc15a6eb7c84e3aba3340e82dc12b7ba4795de6d205c903496dc6e8c8dd412af5cfce39b2a228b7592a7ff46','??驓????첼医?ㇲ???ᶭ???뽨',0,'piopio',30),(34,'598eb23e0f0e2a3abf817173e343eaf9206e7af4b4c219fd357b0f7fb97e9bd391305c08209444df861d6b50b1a69e13e7c4809859470bb93cfc4e80d13b102b','敥쨾?䖍㪥䥭땭露?㹤퓃?㘙䜌놿瞎ꆅ雀?靷?ʵ??뭜',0,'gosia123',33),(37,'5596e9cb25fb956ca4fcc4c16d79798cf92f3bfdb6fe8a1c7dc1f01d32c955d1465db523d27acc71b120eab231749ec3a9ae2dec6781f7897729fb1df1b8d133','???㬣??☱⟂됔?죺ꆇ???駃늩???',0,'biurko',36),(230,'479c5a989b3f529f3d9dcd000c0ba752315b01abe13357b70d3479e77f4b42ece4bc1b654e2f8d2602656d902e66323274c75285fe10c221c59ab1b9da1ad4b2','?????ꃉ??朝?詏???Ꜩ???',0,'piotr',229);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-14 16:14:47
