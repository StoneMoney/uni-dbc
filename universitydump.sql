-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: university
-- ------------------------------------------------------
-- Server version	8.0.23

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

DROP DATABASE IF EXISTS university;

CREATE DATABASE university;
USE university;


--
-- Table structure for table `degree`
--

DROP TABLE IF EXISTS `degree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `degree` (
  `degreeID` int NOT NULL AUTO_INCREMENT,
  `degreeName` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`degreeID`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `degree`
--

LOCK TABLES `degree` WRITE;
/*!40000 ALTER TABLE `degree` DISABLE KEYS */;
INSERT INTO `degree` VALUES (1,'Artificial Intelligence in Computer Science Adv. Cert.'),(2,'Big Data Analytics Adv. Cert.'),(3,'Bioinformatics MS'),(4,'Computer Engineering MS'),(5,'Computational Finance MS'),(6,'Computing and Information Sciences Ph.D.'),(7,'Computer Science MS'),(8,'Computing Security MS'),(9,'Cybersecurity Adv. Cert.'),(10,'Data Science MS'),(11,'Electrical and Computer Engineering Ph.D.'),(12,'Game Design and Development MS'),(13,'Health Informatics MS'),(14,'Human-Computer Interaction MS'),(15,'Imaging Science MS'),(16,'Imaging Science Ph.D.'),(17,'Information Sciences and Technologies MS'),(18,'Media Arts and Technology MS'),(19,'Software Engineering MS'),(20,'Visual Communication Design MFA'),(21,'Web Development Adv. Cert.'),(22,'Bioinformatics and Computational Biologoy BS'),(23,'Computer Engineering BS'),(24,'Computer Engineering Technology BS'),(25,'Computing Exploration'),(26,'Computing and Information Technologies BS'),(27,'Computational Mathematics BS'),(28,'Computer Science BS'),(29,'Computing Security BS'),(30,'3D Digital Design BFA'),(31,'Digital Humanities and Social Sciences BS'),(32,'Game Design and Development BS'),(33,'Human-Centered Computing BS'),(34,'Imaging Science BS'),(35,'Management Information Systems (MIS) BS'),(36,'Media Arts and Technology BS'),(37,'New Media Design BFA'),(38,'New Media Interactive Development BS'),(39,'Software Engineering BS'),(40,'Web and Mobile Computing BS'),(41,'Underwater Basket Weaving Ph.D.');
/*!40000 ALTER TABLE `degree` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `departmentID` int NOT NULL AUTO_INCREMENT,
  `departmentName` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`departmentID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Department of Computing and Information Sciences Ph.D.'),(2,'Department of Computer Science'),(3,'Department of Computing Security'),(4,'Department of Software Engineering'),(5,'School of Information'),(6,'School of Interactive Games and Media');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty` (
  `facultyID` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `abstracts` mediumtext,
  `officeNumber` varchar(20) DEFAULT NULL,
  `phoneNumber` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`facultyID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (1,'Jim','Habermas','This book, Learn C and C++ by Samples written by James R. Habermas, is a companion to A First Book Ansi C++ by Gary Bronson.  It is the author’s firm belief that one can never have too many samples.  If a textbook is to be useful, it needs primary support through an instructor and/or more samples.  This textbook contains a wealth of useful C & C++ samples that are fashioned to further demonstrate the topics outlined in the text.','GOL-2443','(585) 746-1234','jim.habermas@rit.edu'),(2,'Richard','Smedley','This book presents ‘standard’ C, i.e., code that compiles cleanly with a compiler that meets the ANSI C standard.  This book has over 90 example programs that illustrate the topics of each chapters.  In addition complete working programs are developed fully, from design to program output.  This book is filled with Antibugging Notes (the stress traps to be avoided), and Quick Notes, that emphasize important points to be remembered.','GOL-2445','(585) 746-2345','rich.smedley@rit.edu'),(3,'Barbara','Ericson','The programming language used in this book is Python.  Python has been described as “executable pseudo-code.”  I have found that both computer science majors and non majors can learn Python.  Since Python is actually used for communications tasks (e.g., Web site Development), it’s relevant language for an in introductory computing course.  The specific dialect of Python used in this book is Jython.  Jython is Python.  The differences between Python (normally implemented in C) and Jython (which is implemented in Java) are akin to the differences between any two language implementations (e.g., Microsoft vs. GNU C++ implementations).','GOL-2447','(585) 746-3456','barb.ericson@rit.edu'),(4,'Larry','Hill','Mainframes have been around since the beginning of computing, many believe that they are antiquated and obsolete but 97 of the top 100 banks all rely on mainframes for their powerful transactional power.','GOL-2449','(585) 746-4567','larry.hill@rit.edu');
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty_department`
--

DROP TABLE IF EXISTS `faculty_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty_department` (
  `facultyID` int NOT NULL,
  `departmentID` int NOT NULL,
  PRIMARY KEY (`facultyID`,`departmentID`),
  KEY `faculty_department_department` (`departmentID`),
  CONSTRAINT `faculty_department_department` FOREIGN KEY (`departmentID`) REFERENCES `department` (`departmentID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `faculty_department_faculty` FOREIGN KEY (`facultyID`) REFERENCES `faculty` (`facultyID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty_department`
--

LOCK TABLES `faculty_department` WRITE;
/*!40000 ALTER TABLE `faculty_department` DISABLE KEYS */;
INSERT INTO `faculty_department` VALUES (1,1),(3,2),(4,3),(2,5);
/*!40000 ALTER TABLE `faculty_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty_interest`
--

DROP TABLE IF EXISTS `faculty_interest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty_interest` (
  `facultyID` int NOT NULL,
  `interestID` int NOT NULL,
  PRIMARY KEY (`facultyID`,`interestID`),
  KEY `faculty_interests_interests` (`interestID`),
  CONSTRAINT `faculty_interests_faculty` FOREIGN KEY (`facultyID`) REFERENCES `faculty` (`facultyID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `faculty_interests_interests` FOREIGN KEY (`interestID`) REFERENCES `interest` (`interestID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty_interest`
--

LOCK TABLES `faculty_interest` WRITE;
/*!40000 ALTER TABLE `faculty_interest` DISABLE KEYS */;
INSERT INTO `faculty_interest` VALUES (1,1),(2,1),(3,1),(1,2),(2,2),(3,2),(4,2),(1,3),(2,3),(1,4),(1,5),(2,5),(3,5),(1,6),(1,7),(1,8),(1,9),(1,10),(4,10),(1,11),(4,11),(1,12),(1,13),(4,14);
/*!40000 ALTER TABLE `faculty_interest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interest`
--

DROP TABLE IF EXISTS `interest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interest` (
  `interestID` int NOT NULL AUTO_INCREMENT,
  `interestDescription` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`interestID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interest`
--

LOCK TABLES `interest` WRITE;
/*!40000 ALTER TABLE `interest` DISABLE KEYS */;
INSERT INTO `interest` VALUES (1,'Java'),(2,'C'),(3,'C++'),(4,'C#'),(5,'Ruby on Rails'),(6,'PHP'),(7,'Go'),(8,'Rust'),(9,'Python'),(10,'BASIC'),(11,'Machine Learning'),(12,'AI'),(13,'BASH'),(14,'Scripting'),(15,'HTML'),(16,'JavaScript'),(17,'Game Design'),(18,'Photography'),(19,'Game Development'),(20,'Web Development'),(21,'CSS'),(22,'Mobile Development');
/*!40000 ALTER TABLE `interest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `studentID` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(20) DEFAULT NULL,
  `lastName` varchar(20) DEFAULT NULL,
  `phoneNumber` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`studentID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'Stephen','Farrell','(585) 123-4567','spf2962@rit.edu'),(2,'Nick','Giancursio','(585) 234-5678','npg3487@rit.edu'),(3,'Max','Gerber','(585) 345-6789','mlg4789@rit.edu'),(4,'Fei','Gao','(585) 456-7890','fxg8365@rit.edu'),(5,'John','Doe','(585) 567-8901','jpd1234@rit.edu'),(6,'Danielle','Vero','(585) 678-9012','drv2345@rit.edu');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_degree`
--

DROP TABLE IF EXISTS `student_degree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_degree` (
  `studentID` int NOT NULL,
  `degreeID` int NOT NULL,
  PRIMARY KEY (`studentID`,`degreeID`),
  KEY `student_degree_degree` (`degreeID`),
  CONSTRAINT `student_degree_degree` FOREIGN KEY (`degreeID`) REFERENCES `degree` (`degreeID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_degree_student` FOREIGN KEY (`studentID`) REFERENCES `student` (`studentID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_degree`
--

LOCK TABLES `student_degree` WRITE;
/*!40000 ALTER TABLE `student_degree` DISABLE KEYS */;
INSERT INTO `student_degree` VALUES (5,16),(1,26),(2,40),(3,40),(4,40),(6,41);
/*!40000 ALTER TABLE `student_degree` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_interest`
--

DROP TABLE IF EXISTS `student_interest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_interest` (
  `studentID` int NOT NULL,
  `interestID` int NOT NULL,
  PRIMARY KEY (`studentID`,`interestID`),
  KEY `student_interests_interests` (`interestID`),
  CONSTRAINT `student_interests_interests` FOREIGN KEY (`interestID`) REFERENCES `interest` (`interestID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_interests_student` FOREIGN KEY (`studentID`) REFERENCES `student` (`studentID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_interest`
--

LOCK TABLES `student_interest` WRITE;
/*!40000 ALTER TABLE `student_interest` DISABLE KEYS */;
INSERT INTO `student_interest` VALUES (1,1),(2,1),(3,1),(4,1),(2,2),(1,4),(2,4),(1,9),(1,11),(1,14),(1,18);
/*!40000 ALTER TABLE `student_interest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'university'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-28 15:57:21
