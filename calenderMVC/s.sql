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
-- Table structure for table `tbl_board`
--

DROP TABLE IF EXISTS `tbl_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_board` (
  `bno` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `content` varchar(2000) NOT NULL,
  `writer` varchar(50) NOT NULL,
  `regdate` datetime DEFAULT CURRENT_TIMESTAMP,
  `updatedate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`bno`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_board`
--

LOCK TABLES `tbl_board` WRITE;
/*!40000 ALTER TABLE `tbl_board` DISABLE KEYS */;
INSERT INTO `tbl_board` VALUES (1,'제목 수정합니다','테스트 내용','user00','2020-01-25 23:48:10','2020-01-27 21:02:34'),(4,'텍스트 제목','테스트 내용','user00','2020-01-25 23:48:56','2020-01-25 23:48:56'),(5,'텍스트 제목','테스트 내용','user00','2020-01-25 23:48:58','2020-01-25 23:48:58'),(6,'새로작성하는 글','새로 작성하는 내용asdsd','newbie','2020-01-26 03:43:13','2020-02-10 01:33:22'),(7,'새로작성하는 글','새로 작성하는 내용','newbie','2020-01-26 03:44:05','2020-01-26 03:44:05'),(8,'새로작성하는 글','새로 작성하는 내용','newbie','2020-01-27 20:01:36','2020-01-27 20:01:36'),(9,'새로작성하는 글','새로 작성하는 내용','newbie','2020-01-27 20:01:45','2020-01-27 20:01:45'),(10,'새로 작성하는 글','새로 작성하는 내용','newbie','2020-01-27 20:44:04','2020-01-27 20:44:04'),(11,'새로 작성하는 글','새로 작성하는 내용','newbie','2020-01-27 20:46:01','2020-01-27 20:46:01'),(12,'새로 작성하는 글','새로 작성하는 내용','newbie','2020-01-27 20:47:53','2020-01-27 20:47:53'),(13,'새로 작성하는 글','새로 작성하는 내용','newbie','2020-01-27 20:49:34','2020-01-27 20:49:34'),(14,'새로 작성하는 글','새로 작성하는 내용','newbie','2020-01-27 21:01:47','2020-01-27 21:01:47'),(15,'새로 작성하는 글','새로 작성하는 내용','newbie','2020-01-27 21:02:34','2020-01-27 21:02:34'),(16,'테스트 새글 제목','테스트 새글 내용','user00','2020-01-27 21:42:06','2020-01-27 21:42:06'),(17,'테스트 새글 제목','테스트 새글 내용','user00','2020-01-27 21:43:01','2020-01-27 21:43:01'),(18,'테스트 새글 제목','테스트 새글 내용','user00','2020-01-27 21:43:59','2020-01-27 21:43:59'),(19,'íì¤í¸','íì¤í¸','user00','2020-01-28 01:55:11','2020-01-28 01:55:11');
/*!40000 ALTER TABLE `tbl_board` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `tbl_member` VALUES ('user01','1');
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_schedule`
--

LOCK TABLES `tbl_schedule` WRITE;
/*!40000 ALTER TABLE `tbl_schedule` DISABLE KEYS */;
INSERT INTO `tbl_schedule` VALUES (1,NULL,'2020-01-07','2020-01-07','ㅁㄴㅇㄴㅇ',NULL),(2,'user01','2020-01-24','2020-01-24','안녕?',NULL),(3,'user01','2020-01-28','2020-01-28','123123',NULL),(4,'user01','2020-01-18','2020-01-18','`213213421','1232134123'),(5,'user01','2020-01-07','2020-01-07','adsfadsf','sdafad');
/*!40000 ALTER TABLE `tbl_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertbl`
--

DROP TABLE IF EXISTS `usertbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usertbl` (
  `userID` char(8) NOT NULL,
  `name` varchar(10) NOT NULL,
  `birthYear` int(11) NOT NULL,
  `addr` char(2) NOT NULL,
  `mobile1` char(3) DEFAULT NULL,
  `mobile2` char(8) DEFAULT NULL,
  `height` smallint(6) DEFAULT NULL,
  `mDate` date DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertbl`
--

LOCK TABLES `usertbl` WRITE;
/*!40000 ALTER TABLE `usertbl` DISABLE KEYS */;
INSERT INTO `usertbl` VALUES ('BBK','바비킴',1973,'서울','010','0000000',176,'2013-05-05'),('EJW','은지원',1972,'경북','011','8888888',174,'2014-03-03'),('JKW','조관우',1965,'경기','018','9999999',172,'2010-10-10'),('JYP','조용필',1950,'경기','011','4444444',166,'2009-04-04'),('KBS','김범수',1979,'경남','011','2222222',173,'2012-04-04'),('KKH','김경호',1971,'전남','019','3333333',177,'2007-07-07'),('LJB','임재범',1963,'서울','016','6666666',182,'2009-09-09'),('LSG','이승기',1987,'서울','011','1111111',182,'2008-08-08'),('SSK','성시경',1979,'서울',NULL,NULL,186,'2013-12-12'),('YJS','윤종신',1969,'경남',NULL,NULL,170,'2005-05-05');
/*!40000 ALTER TABLE `usertbl` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-10  7:16:35
