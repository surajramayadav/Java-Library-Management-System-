CREATE DATABASE  IF NOT EXISTS `library_management` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `library_management`;
-- MySQL dump 10.13  Distrib 8.0.27, for Linux (x86_64)
--
-- Host: localhost    Database: library_management
-- ------------------------------------------------------
-- Server version	8.0.27-0ubuntu0.20.04.1

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
-- Table structure for table `issued_book`
--

DROP TABLE IF EXISTS `issued_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `issued_book` (
  `issuedbook_id` int NOT NULL AUTO_INCREMENT,
  `issued_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `return_date` timestamp NOT NULL,
  `return_status` varchar(50) NOT NULL DEFAULT 'pending',
  `book_id` int DEFAULT NULL,
  `admin_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`issuedbook_id`),
  KEY `FK_BookIssuedBook` (`book_id`),
  KEY `FK_AdminIssuedBook` (`admin_id`),
  KEY `FK_UserIssuedBook` (`user_id`),
  CONSTRAINT `FK_AdminIssuedBook` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`),
  CONSTRAINT `FK_BookIssuedBook` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`),
  CONSTRAINT `FK_UserIssuedBook` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issued_book`
--

LOCK TABLES `issued_book` WRITE;
/*!40000 ALTER TABLE `issued_book` DISABLE KEYS */;
INSERT INTO `issued_book` VALUES (3,'2022-01-27 13:00:34','2022-02-01 13:00:34','done',1,1,3),(4,'2022-01-28 04:45:47','2022-02-02 04:45:47','done',1,1,3);
/*!40000 ALTER TABLE `issued_book` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-29  1:59:18
