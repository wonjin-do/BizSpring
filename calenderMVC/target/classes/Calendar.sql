CREATE DATABASE  IF NOT EXISTS `bizspring` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bizspring`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: bizspring
-- ------------------------------------------------------
-- Server version	5.7.10-log

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
-- Table structure for table `tbl_member`
--

DROP TABLE IF EXISTS `tbl_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_member` (
  `id` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_member`
--

LOCK TABLES `tbl_member` WRITE;
/*!40000 ALTER TABLE `tbl_member` DISABLE KEYS */;
INSERT INTO `tbl_member` VALUES ('1','1'),('123','123'),('135','135'),('156','156'),('234','234'),('321','321'),('aaa123','bbb123'),('user01','1');
/*!40000 ALTER TABLE `tbl_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_schedule`
--

DROP TABLE IF EXISTS `tbl_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_schedule` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(45) DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `enddate` date DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `content` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `USERID` (`userid`),
  CONSTRAINT `USERID` FOREIGN KEY (`userid`) REFERENCES `tbl_member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_schedule`
--

LOCK TABLES `tbl_schedule` WRITE;
/*!40000 ALTER TABLE `tbl_schedule` DISABLE KEYS */;
INSERT INTO `tbl_schedule` VALUES (1,NULL,'2020-01-07','2020-01-07','ㅁㄴㅇㄴㅇ',NULL),(2,'user01','2020-01-24','2020-01-24','안녕?',NULL),(3,'user01','2020-01-28','2020-01-28','123123',NULL),(4,'user01','2020-01-18','2020-01-18','`213213421','1232134123'),(5,'user01','2020-01-07','2020-01-07','adsfadsf','sdafad'),(6,NULL,'2020-01-08','2020-01-08','','sad'),(21,'aaa123','2020-02-05','2020-02-06','abc','abc'),(22,'123','2020-01-08','2020-01-08','test1','test1'),(23,'123','2020-01-07','2020-01-07','test2','test2'),(24,'123','2020-01-06','2020-01-06','dsg','sdfg'),(25,'123','2020-02-04','2020-02-04','test','test'),(26,'123','2020-02-05','2020-02-05','test2','test2'),(27,'123','2020-02-05','2020-02-05','test3','test3'),(28,'123','2020-02-04','2020-02-04','test4','test4'),(29,'321','2020-02-11','2020-02-11','321','321'),(30,'321','2020-02-11','2020-02-11','4321','4321'),(31,'321','2020-02-11','2020-02-11','1','1'),(32,'321','2020-02-11','2020-02-11','2','2'),(33,'321','2020-02-11','2020-02-11','5','5'),(34,'321','2020-02-11','2020-02-11','6','6'),(35,'321','2020-02-13','2020-02-13','1','1'),(36,'321','2020-02-13','2020-02-13','2','2'),(37,'321','2020-02-14','2020-02-14','5','5'),(38,'321','2020-02-14','2020-02-14','6','6'),(39,'321','2020-02-12','2020-02-12','1','1'),(40,'321','2020-02-12','2020-02-12','4','4'),(41,'321','2020-02-06','2020-02-06','1','1'),(42,'321','2020-02-07','2020-02-07','2','2'),(43,'321','2020-02-08','2020-02-08','5','5'),(44,'321','2020-02-07','2020-02-07','4','4'),(45,'321','2020-02-15','2020-02-15','1','1'),(46,'321','2020-02-01','2020-02-01','1','1'),(47,'321','2020-02-01','2020-02-01','2','2'),(65,'1','2020-02-03','2020-02-03','3','3'),(66,'1','2020-02-05','2020-02-05','123','123'),(70,'1','2020-02-07','2020-02-07','6','6'),(71,'1','2020-02-07','2020-02-07','67','756'),(72,'1','2020-02-03','2020-02-03','123','3123'),(73,'1','2020-02-07','2020-02-07','1','1'),(74,'1','2020-02-07','2020-02-07','1','1'),(75,'1','2020-02-07','2020-02-07','1','1'),(76,'1','2020-02-05','2020-02-05','1','1'),(77,'1','2020-02-03','2020-02-03','1','1'),(78,'1','2020-02-04','2020-02-04','1','1'),(81,'1','2020-02-11','2020-02-11','1','1'),(90,'1','2020-02-13','2020-02-13','rewqrqwersdf','asdfasdf wqrtewq'),(91,'1','2020-02-10','2020-02-10','123','123'),(92,'1','2020-02-10','2020-02-10','123','123'),(93,'1','2020-02-10','2020-02-10','안녕하세요','안녕하세요'),(94,'1','2020-02-11','2020-02-11','414141','414141'),(95,'1','2020-02-14','2020-02-14','1','1'),(96,'1','2020-02-14','2020-02-14','1','1'),(97,'1','2020-02-15','2020-02-15','2','2'),(98,'1','2020-02-17','2020-02-17','5','5'),(99,'1','2020-02-18','2020-02-18','123','123'),(100,'1','2020-02-19','2020-02-19','123','asdfafsdfadsfadsfadadfs'),(104,'1','2020-02-26','2020-02-26','new','new'),(105,'1','2020-02-24','2020-02-24','','rrrrrrrrr'),(106,'1','2020-02-24','2020-02-24','rrrrr','rrrr');
/*!40000 ALTER TABLE `tbl_schedule` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-14 15:01:58
