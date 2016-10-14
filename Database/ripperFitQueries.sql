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
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `organization_id` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(30) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `gender` varchar(6) NOT NULL,
  `contact_number` varchar(10) NOT NULL,
  `address` varchar(200) NOT NULL,
  `designation_id` int(11) DEFAULT NULL,
  `profile_picture` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `designation_id` (`designation_id`),
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`designation_id`) REFERENCES `designation` (`designation_id`),
  CONSTRAINT `employee_ibfk_3` FOREIGN KEY (`organization_id`) REFERENCES `organization` (`organization_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organization` (
  `organization_id` int(11) NOT NULL AUTO_INCREMENT,
  `organization_name` varchar(100) NOT NULL,
  PRIMARY KEY (`organization_id`),
  UNIQUE KEY `organization_name_UNIQUE` (`organization_name`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `employee_team_approval`
--

DROP TABLE IF EXISTS `employee_team_approval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_team_approval` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `approvee_id` int(11) DEFAULT NULL,
  `organization_id` int(11) NOT NULL,
  `status` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `employee_team_approval_ibfk_2` (`approvee_id`),
  CONSTRAINT `employee_team_approval_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`),
  CONSTRAINT `employee_team_approval_ibfk_2` FOREIGN KEY (`approvee_id`) REFERENCES `employee` (`employee_id`),
  CONSTRAINT `employee_team_approval_ibfk_3` FOREIGN KEY (`organization_id`) REFERENCES `organization` (`organization_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resource_request`
--

DROP TABLE IF EXISTS `resource_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_request` (
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
  `organization_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  `requestor_id` int(11) NOT NULL,
  `current_approval_tier_level` int(11) NOT NULL,
  `priority` varchar(30) NOT NULL,
  `status` varchar(30) NOT NULL,
  `comments` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`request_id`),
  KEY `resource_id` (`resource_id`),
  KEY `requestor_id` (`requestor_id`),
  CONSTRAINT `designation_level_ibfk_3` FOREIGN KEY (`current_approval_tier_level`) REFERENCES `designation` (`designation_tier_level`),
  CONSTRAINT `resource_request_ibfk_1` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`resource_id`),
  CONSTRAINT `resource_request_ibfk_2` FOREIGN KEY (`requestor_id`) REFERENCES `employee` (`employee_id`),
  CONSTRAINT `resource_request_ibfk_4` FOREIGN KEY (`organization_id`) REFERENCES `organization` (`organization_id`)
  
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource` (
  `resource_id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(30) NOT NULL,
  `organization_id` int(11) NOT NULL,
  `final_approval_tier_level` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`resource_id`),
  CONSTRAINT `resource_ibfk_1` FOREIGN KEY (`final_approval_tier_level`) REFERENCES `designation` (`designation_tier_level`),
  CONSTRAINT `resource_ibfk_2` FOREIGN KEY (`organization_id`) REFERENCES `organization` (`organization_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `designation`
--

DROP TABLE IF EXISTS `designation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `designation` (
  `designation_id` int(11) NOT NULL AUTO_INCREMENT,
  `designation_name` varchar(30) NOT NULL,
  `organization_id` int(11) NOT NULL,
  `designation_tier_level` int(11) NOT NULL,
  `parent_designation_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`designation_id`),
  CONSTRAINT `designation_ibfk_1` FOREIGN KEY (`organization_id`) REFERENCES `organization` (`organization_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-07 16:23:48
