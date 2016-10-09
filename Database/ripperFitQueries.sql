CREATE DATABASE  IF NOT EXISTS `ripper_fit` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ripper_fit`;


DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
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
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`designation_id`) REFERENCES `designation` (`designation_id`)
)
DROP TABLE IF EXISTS `employee_team_approval`;
CREATE TABLE `employee_team_approval` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `approvee_id` int(11) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `employee_team_approval_ibfk_2` (`approvee_id`),
  CONSTRAINT `employee_team_approval_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`),
  CONSTRAINT `employee_team_approval_ibfk_2` FOREIGN KEY (`approvee_id`) REFERENCES `employee` (`employee_id`)
)
DROP TABLE IF EXISTS `resource_request`;
CREATE TABLE `resource_request` (
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_id` int(11) NOT NULL,
  `requestor_id` int(11) NOT NULL,
  `current_approval_designation_id` int(11) NOT NULL,
  `priority` varchar(30) NOT NULL,
  `status` varchar(30) NOT NULL,
  `comments` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`request_id`),
  KEY `resource_id` (`resource_id`),
  KEY `requestor_id` (`requestor_id`),
  KEY `current_approval_designation_id` (`current_approval_designation_id`),
  CONSTRAINT `designation_level_ibfk_3` FOREIGN KEY (`current_approval_designation_id`) REFERENCES `designation` (`designation_id`),
  CONSTRAINT `resource_request_ibfk_1` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`resource_id`),
  CONSTRAINT `resource_request_ibfk_2` FOREIGN KEY (`requestor_id`) REFERENCES `employee` (`employee_id`)
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
  `final_approval_designation_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`resource_id`),
  UNIQUE KEY `resource_name_UNIQUE` (`resource_name`),
  KEY `designation_ibfk_1` (`final_approval_designation_id`),
  CONSTRAINT `designation_ibfk_1` FOREIGN KEY (`final_approval_designation_id`) REFERENCES `designation` (`designation_id`)
)
DROP TABLE IF EXISTS `designation`;
CREATE TABLE `designation` (
  `designation_id` int(11) NOT NULL AUTO_INCREMENT,
  `designation_name` varchar(30) NOT NULL,
  `parent_designation_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`designation_id`),
  UNIQUE KEY `designation_name_UNIQUE` (`designation_name`)
)