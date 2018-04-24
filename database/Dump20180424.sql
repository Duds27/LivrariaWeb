CREATE DATABASE  IF NOT EXISTS `livraria` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `livraria`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: livraria
-- ------------------------------------------------------
-- Server version	5.7.16-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `author` (
  `author_id` bigint(20) NOT NULL,
  `author_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (3,'Eric Gama'),(4,'Fabio Caversan'),(6,'Eliane'),(9,'Edu'),(31,'dsfs'),(34,'fgd'),(37,'nxfh'),(40,'fdha'),(43,'sgvfzg'),(46,'nfn'),(49,'ghfdjk'),(51,'nfgj');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `book_id` bigint(20) NOT NULL,
  `book_price` double NOT NULL,
  `book_publisher` varchar(100) NOT NULL,
  `book_title` varchar(100) NOT NULL,
  `book_year` int(11) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`book_id`),
  KEY `FKam9riv8y6rjwkua1gapdfew4j` (`category_id`),
  CONSTRAINT `FKam9riv8y6rjwkua1gapdfew4j` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,157.54,'Elselvier','Padrões de Projeto',2010,4),(2,254.43,'Caversan','Data Structure',2015,4),(7,5024.76,'Escola','Paisagens Brasil',2018,6),(10,455.32,'Paranaue','Paisagens do meu Sertão',2012,7),(32,545,'fsdfs','aaaa',2010,17),(35,423,'fgd','rgret',2344,18),(38,6654,'jxgh','abzgb',5355,19),(41,5345,'djsdtj','sgfzgf',653,20),(44,5,'fdsFs','oi',45346,21),(47,63,'sdjsjy','ngsmk',3563,22),(50,56345,'hfghg','FHETJ',6356,23),(52,76,'fhkjd','jfhj',534,24);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_author_nn`
--

DROP TABLE IF EXISTS `book_author_nn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_author_nn` (
  `author_id` bigint(20) NOT NULL,
  `book_id` bigint(20) NOT NULL,
  KEY `FK8bo9pd454owdkymm6r7qggoo0` (`book_id`),
  KEY `FKdqpo1jclub1atij672bhxp68m` (`author_id`),
  CONSTRAINT `FK8bo9pd454owdkymm6r7qggoo0` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`),
  CONSTRAINT `FKdqpo1jclub1atij672bhxp68m` FOREIGN KEY (`author_id`) REFERENCES `author` (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_author_nn`
--

LOCK TABLES `book_author_nn` WRITE;
/*!40000 ALTER TABLE `book_author_nn` DISABLE KEYS */;
INSERT INTO `book_author_nn` VALUES (3,1),(3,2),(4,2),(6,7),(9,10),(31,32),(34,35),(37,38),(40,41),(43,44),(46,47),(49,50),(51,52);
/*!40000 ALTER TABLE `book_author_nn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookauthornn`
--

DROP TABLE IF EXISTS `bookauthornn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookauthornn` (
  `author_id` bigint(20) NOT NULL,
  `book_id` bigint(20) NOT NULL,
  PRIMARY KEY (`author_id`,`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookauthornn`
--

LOCK TABLES `bookauthornn` WRITE;
/*!40000 ALTER TABLE `bookauthornn` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookauthornn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) NOT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `UK_lroeo5fvfdeg4hpicn4lw7x9b` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (9,'Arquitetura e Hum'),(6,'Arquitetura e Urba'),(10,'Arquitetura e Urbanismo'),(19,'bhkj'),(4,'Computação'),(18,'dfsf'),(3,'Engenharia'),(2,'Exatas'),(20,'fdhthj'),(21,'fgdfg'),(17,'fsdf'),(23,'fsdfdsG'),(24,'gfgdh'),(5,'Humanas'),(22,'mhxf'),(7,'Paisagismo');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (54),(54),(54);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `library`
--

DROP TABLE IF EXISTS `library`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `library` (
  `library_id` bigint(20) NOT NULL,
  `library_book_count` int(11) DEFAULT NULL,
  `book_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`library_id`),
  KEY `FKqckt63gdptlcrhfx5skt1bblj` (`book_id`),
  CONSTRAINT `FKqckt63gdptlcrhfx5skt1bblj` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `library`
--

LOCK TABLES `library` WRITE;
/*!40000 ALTER TABLE `library` DISABLE KEYS */;
INSERT INTO `library` VALUES (5,3,1),(36,1,35),(39,1,38),(42,1,41),(45,1,44),(48,1,47),(53,1,52);
/*!40000 ALTER TABLE `library` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'livraria'
--

--
-- Dumping routines for database 'livraria'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-24 16:49:27
