CREATE DATABASE  IF NOT EXISTS `ripper_fit` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ripper_fit`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: ripper_fit
-- ------------------------------------------------------
-- Server version	5.5.23

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
-- Table structure for table `approve_request`
--

DROP TABLE IF EXISTS `approve_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `approve_request` (
  `approvee_id` int(11) NOT NULL,
  `request_id` int(11) NOT NULL,
  `forwardTo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`request_id`),
  KEY `ApproveRequest_ibfk_2` (`request_id`),
  KEY `ApproveRequest_ibfk_3` (`forwardTo_id`),
  KEY `ApproveRequest_ibfk_1` (`approvee_id`),
  CONSTRAINT `ApproveRequest_ibfk_1` FOREIGN KEY (`approvee_id`) REFERENCES `employee` (`employee_id`),
  CONSTRAINT `ApproveRequest_ibfk_2` FOREIGN KEY (`request_id`) REFERENCES `resource_request` (`request_id`),
  CONSTRAINT `ApproveRequest_ibfk_3` FOREIGN KEY (`forwardTo_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `approve_request`
--

LOCK TABLES `approve_request` WRITE;
/*!40000 ALTER TABLE `approve_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `approve_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource` (
  `resource_id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(30) NOT NULL,
  `final_approval_level` int(11) NOT NULL,
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource`
--

LOCK TABLES `resource` WRITE;
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `designation`
--

DROP TABLE IF EXISTS `designation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `designation` (
  `designation_id` int(11) NOT NULL AUTO_INCREMENT,
  `designation_name` varchar(30) NOT NULL,
  `designation_level` int(11) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`designation_id`),
  KEY `department_ibfk_2` (`department_id`),
  CONSTRAINT `department_ibfk_2` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `designation`
--

LOCK TABLES `designation` WRITE;
/*!40000 ALTER TABLE `designation` DISABLE KEYS */;
/*!40000 ALTER TABLE `designation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_request`
--

DROP TABLE IF EXISTS `resource_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_request` (
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_id` int(11) NOT NULL,
  `requestor_id` int(11) NOT NULL,
  `current_approval_level` int(11) DEFAULT NULL,
  `priority` varchar(30) DEFAULT 'low',
  `status` varchar(30) NOT NULL,
  `message` varchar(1000) DEFAULT NULL,
  `request_date` date DEFAULT NULL,
  PRIMARY KEY (`request_id`),
  KEY `resource_id` (`resource_id`),
  KEY `requestor_id` (`requestor_id`),
  CONSTRAINT `resource_request_ibfk_1` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`resource_id`),
  CONSTRAINT `resource_request_ibfk_2` FOREIGN KEY (`requestor_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_request`
--

LOCK TABLES `resource_request` WRITE;
/*!40000 ALTER TABLE `resource_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `resource_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `commentor_id` int(11) NOT NULL,
  `comments` varchar(500) NOT NULL,
  `request_id` int(11) NOT NULL,
  `comment_date` date NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `comments_ibfk_1` (`commentor_id`),
  KEY `comments_ibfk_2` (`request_id`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`commentor_id`) REFERENCES `employee` (`employee_id`),
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`request_id`) REFERENCES `resource_request` (`request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `department_id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(200) NOT NULL,
  `organization_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`department_id`),
  KEY `department_ibfk_1` (`organization_id`),
  CONSTRAINT `department_ibfk_1` FOREIGN KEY (`organization_id`) REFERENCES `organization` (`organization_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `organization_id` int(11) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(30) DEFAULT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `gender` varchar(6) DEFAULT NULL,
  `contact_number` varchar(10) DEFAULT NULL,
  `designation_id` int(11) DEFAULT NULL,
  `reportTo_id` int(11) DEFAULT NULL,
  `profile_picture` varchar(500) DEFAULT NULL,
  `approval_status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `employee_ibfk_3` (`organization_id`),
  KEY `employee_ibfk_4` (`designation_id`),
  KEY `employee_ibfk_6` (`reportTo_id`),
  CONSTRAINT `employee_ibfk_3` FOREIGN KEY (`organization_id`) REFERENCES `organization` (`organization_id`),
  CONSTRAINT `employee_ibfk_4` FOREIGN KEY (`designation_id`) REFERENCES `designation` (`designation_id`),
  CONSTRAINT `employee_ibfk_6` FOREIGN KEY (`reportTo_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization`
--

DROP TABLE IF EXISTS `organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organization` (
  `organization_id` int(11) NOT NULL AUTO_INCREMENT,
  `organization_name` varchar(100) NOT NULL,
  `domain_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`organization_id`),
  UNIQUE KEY `organization_name_UNIQUE` (`organization_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization`
--

LOCK TABLES `organization` WRITE;
/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-19 19:09:48
