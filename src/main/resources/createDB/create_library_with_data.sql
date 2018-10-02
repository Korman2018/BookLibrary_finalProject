CREATE DATABASE  IF NOT EXISTS `library` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `library`;
-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
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
 SET character_set_client = utf8mb4 ;
CREATE TABLE `author` (
  `idAuthor` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `patronymic` varchar(45) DEFAULT NULL,
  `surname` varchar(45) NOT NULL,
  PRIMARY KEY (`idAuthor`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'Александр','Сергеевич','Пушкин'),(2,'Лев','Николаевич','Толстой'),(3,'Федор','Михайлович','Достоевский'),(4,'Михаил','Афанасьевич','Булгаков'),(5,'Николай','Васильевич','Гоголь'),(6,'Жюль',NULL,'Верн'),(7,'Артур',NULL,'Конан Дойл'),(8,'Марк',NULL,'Твен'),(9,'Джон',NULL,'Толкин'),(10,'Гебрерт',NULL,'Шилдт'),(11,'Агата',NULL,'Кристи'),(12,'Фрэнк','','Герберт'),(13,'Джордж','','Оруэлл'),(14,'Михаил','Афанасьевич','Булгаков'),(15,'Александр','','Дюма'),(16,'Джоан','','Роулинг');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `book` (
  `idBook` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `idAuthor` int(11) DEFAULT NULL,
  `idGenre` int(11) DEFAULT NULL,
  PRIMARY KEY (`idBook`),
  KEY `fk_Books_Authors1_idx` (`idAuthor`),
  KEY `fk_Book_Genre1_idx` (`idGenre`),
  CONSTRAINT `fk_Book_Genre1` FOREIGN KEY (`idGenre`) REFERENCES `genre` (`idgenre`),
  CONSTRAINT `fk_Books_Authors1` FOREIGN KEY (`idAuthor`) REFERENCES `author` (`idauthor`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'Хоббит',9,6),(2,'Властелин колец',9,6),(3,'Война и мир том.1',2,7),(4,'Война и мир том.2',2,7),(5,'Дюна',12,5),(6,'20 тысяч лье под водой',6,5),(7,'Собака Баскервилей',7,2),(8,'Принц и нищий',8,7),(9,'Приключения Тома Сойера',8,16),(10,'Приключения Гекльберри Финна',8,16),(11,'Анна Каренина',2,7),(12,'Три мушкетера',15,7),(13,'20 лет спустя',15,7),(14,'Идиот',3,1),(15,'Бесы',3,1),(16,'Гарри Поттер и философский камень',16,6),(17,'Гарри Поттер и дары смерти часть 1',16,6),(18,'Вечера на хуторе близ Диканьки',5,1),(19,'Тарас Бульба',5,7),(20,'Вий',5,4),(21,'Хоббит',9,6),(22,'Дюна',12,5),(23,'10 лет спустя',15,7);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookrequest`
--

DROP TABLE IF EXISTS `bookrequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bookrequest` (
  `idBookRequest` int(11) NOT NULL AUTO_INCREMENT,
  `idReader` int(11) NOT NULL,
  `idBook` int(11) NOT NULL,
  `idStatus` int(11) NOT NULL DEFAULT '1',
  `idLibrarian` int(11) DEFAULT NULL,
  PRIMARY KEY (`idBookRequest`),
  KEY `fk_BookRequest_Books1_idx` (`idBook`),
  KEY `fk_BookRequest_Status1_idx` (`idStatus`),
  KEY `fk_BookRequest_User1_idx` (`idReader`),
  KEY `fk_BookRequest_User2_idx` (`idLibrarian`),
  CONSTRAINT `fk_BookRequest_Books1` FOREIGN KEY (`idBook`) REFERENCES `book` (`idbook`),
  CONSTRAINT `fk_BookRequest_Status1` FOREIGN KEY (`idStatus`) REFERENCES `status` (`idstatus`),
  CONSTRAINT `fk_BookRequest_User1` FOREIGN KEY (`idReader`) REFERENCES `user` (`iduser`),
  CONSTRAINT `fk_BookRequest_User2` FOREIGN KEY (`idLibrarian`) REFERENCES `user` (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookrequest`
--

LOCK TABLES `bookrequest` WRITE;
/*!40000 ALTER TABLE `bookrequest` DISABLE KEYS */;
INSERT INTO `bookrequest` VALUES (1,3,1,3,1),(2,3,3,2,1),(3,3,14,1,3),(4,2,5,1,2),(5,2,16,2,1),(6,2,9,1,2);
/*!40000 ALTER TABLE `bookrequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `genre` (
  `idGenre` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`idGenre`),
  UNIQUE KEY `description_UNIQUE` (`description`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (2,'детектив'),(13,'детская литература'),(7,'исторический роман'),(1,'классика'),(16,'приключения'),(15,'программирование'),(8,'роман'),(12,'словарь'),(3,'стихи'),(10,'техническая литература'),(4,'ужасы'),(5,'фантастика'),(6,'фэнтези'),(9,'энциклопедия'),(11,'юмор');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `status` (
  `idStatus` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`idStatus`),
  UNIQUE KEY `description_UNIQUE` (`description`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (4,'возврат'),(3,'выдано'),(1,'запрос'),(2,'обработано');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `patronymic` varchar(45) DEFAULT NULL,
  `surname` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `isLibrarian` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Константин','','Коростелев','admin','admin',1),(2,'Василий',NULL,'Пупкин','user','user',0),(3,'Иван','Иванович','Иванов','ivanov','ivanov',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-24 10:57:03
