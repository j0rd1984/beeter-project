-- MySQL dump 10.13  Distrib 5.6.26, for Win64 (x86_64)
--
-- Host: localhost    Database: beeterdb
-- ------------------------------------------------------
-- Server version	5.6.27-log

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
-- Table structure for table `auth_tokens`
--

DROP TABLE IF EXISTS `auth_tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_tokens` (
  `userid` binary(16) NOT NULL,
  `token` binary(16) NOT NULL,
  PRIMARY KEY (`token`),
  KEY `userid` (`userid`),
  CONSTRAINT `auth_tokens_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_tokens`
--

LOCK TABLES `auth_tokens` WRITE;
/*!40000 ALTER TABLE `auth_tokens` DISABLE KEYS */;
INSERT INTO `auth_tokens` VALUES ('*[ëmr‹å‰Ò\0#¥lŒ','_5¿ÑŒvå½T\0#¥lŒ'),('àž(´rŠå‰Ò\0#¥lŒ','ºyÍ•Žå¸˜\0#¥lŒ'),('íª¨ƒrŠå‰Ò\0#¥lŒ','í¬ÂÚrŠå‰Ò\0#¥lŒ');
/*!40000 ALTER TABLE `auth_tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stings`
--

DROP TABLE IF EXISTS `stings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stings` (
  `id` binary(16) NOT NULL,
  `userid` binary(16) NOT NULL,
  `subject` varchar(100) NOT NULL,
  `content` varchar(500) NOT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creation_timestamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  CONSTRAINT `stings_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stings`
--

LOCK TABLES `stings` WRITE;
/*!40000 ALTER TABLE `stings` DISABLE KEYS */;
INSERT INTO `stings` VALUES ('Ãe‰r‹å‰Ò\0#¥lŒ','àž(´rŠå‰Ò\0#¥lŒ','Mi primer Sting','Este es el primer Sting del proyecto Restful','2015-10-14 15:48:47','2015-10-14 17:48:47'),('·–r‹å‰Ò\0#¥lŒ','àž(´rŠå‰Ò\0#¥lŒ','Mi primer Sting','Este es el primer Sting del proyecto Restful','2015-10-14 15:48:52','2015-10-14 17:48:52'),('8A7r‹å‰Ò\0#¥lŒ','àž(´rŠå‰Ò\0#¥lŒ','Mi primer Sting','Este es el primer Sting del proyecto Restful','2015-10-14 15:48:53','2015-10-14 17:48:53'),('­†Ur‹å‰Ò\0#¥lŒ','àž(´rŠå‰Ò\0#¥lŒ','Mi primer Sting','Este es el primer Sting del proyecto Restful','2015-10-14 15:48:54','2015-10-14 17:48:54'),('Dûér‹å‰Ò\0#¥lŒ','íª¨ƒrŠå‰Ò\0#¥lŒ','Mi Update del Segundo sting','Este Sting ha sido actualizado mediante el mÃƒÂ©todo PUT','2015-10-14 15:53:28','2015-10-14 17:50:18'),('eÇé¸tå½Ï\0#¥lŒ','àž(´rŠå‰Ò\0#¥lŒ','Mi primer Sting','Este es el primer Sting del proyecto Restful','2015-10-16 13:18:32','2015-10-16 15:18:32'),('gËStå½Ï\0#¥lŒ','àž(´rŠå‰Ò\0#¥lŒ','Mi primer Sting','Este es el primer Sting del proyecto Restful','2015-10-16 13:18:35','2015-10-16 15:18:35'),('jaB`tå½Ï\0#¥lŒ','àž(´rŠå‰Ò\0#¥lŒ','Mi primer Sting','Este es el primer Sting del proyecto Restful','2015-10-16 13:18:39','2015-10-16 15:18:39'),('pý‚tå½Ï\0#¥lŒ','àž(´rŠå‰Ò\0#¥lŒ','Mi primer Sting','Este es el primer Sting del proyecto Restful','2015-10-16 13:18:49','2015-10-16 15:18:49'),('w·iWtå½Ï\0#¥lŒ','àž(´rŠå‰Ò\0#¥lŒ','Mi primer Sting','Este es el primer Sting del proyecto Restful','2015-10-16 13:19:02','2015-10-16 15:19:02'),('x~¸2tå½Ï\0#¥lŒ','àž(´rŠå‰Ò\0#¥lŒ','Mi primer Sting','Este es el primer Sting del proyecto Restful','2015-10-16 13:19:03','2015-10-16 15:19:03'),('yúøtå½Ï\0#¥lŒ','àž(´rŠå‰Ò\0#¥lŒ','Mi primer Sting','Este es el primer Sting del proyecto Restful','2015-10-16 13:19:04','2015-10-16 15:19:04'),('y“›btå½Ï\0#¥lŒ','àž(´rŠå‰Ò\0#¥lŒ','Mi primer Sting','Este es el primer Sting del proyecto Restful','2015-10-16 13:19:05','2015-10-16 15:19:05'),('ëÿ\ntå½Ï\0#¥lŒ','àž(´rŠå‰Ò\0#¥lŒ','Mi primer Sting','Este es el primer Sting del proyecto Restful','2015-10-16 13:22:17','2015-10-16 15:22:17'),('ìÜ5>tå½Ï\0#¥lŒ','àž(´rŠå‰Ò\0#¥lŒ','Mi primer Sting','Este es el primer Sting del proyecto Restful','2015-10-16 13:22:18','2015-10-16 15:22:18'),('í@õtå½Ï\0#¥lŒ','àž(´rŠå‰Ò\0#¥lŒ','Mi primer Sting','Este es el primer Sting del proyecto Restful','2015-10-16 13:22:19','2015-10-16 15:22:19'),('í¨þtå½Ï\0#¥lŒ','àž(´rŠå‰Ò\0#¥lŒ','Mi primer Sting','Este es el primer Sting del proyecto Restful','2015-10-16 13:22:20','2015-10-16 15:22:20'),('î£rtå½Ï\0#¥lŒ','àž(´rŠå‰Ò\0#¥lŒ','Mi primer Sting','Este es el primer Sting del proyecto Restful','2015-10-16 13:22:20','2015-10-16 15:22:20'),('îZdÌtå½Ï\0#¥lŒ','àž(´rŠå‰Ò\0#¥lŒ','Mi primer Sting','Este es el primer Sting del proyecto Restful','2015-10-16 13:22:21','2015-10-16 15:22:21'),('îµ,tå½Ï\0#¥lŒ','àž(´rŠå‰Ò\0#¥lŒ','Mi primer Sting','Este es el primer Sting del proyecto Restful','2015-10-16 13:22:21','2015-10-16 15:22:21'),('ï/…tå½Ï\0#¥lŒ','àž(´rŠå‰Ò\0#¥lŒ','Mi primer Sting','Este es el primer Sting del proyecto Restful','2015-10-16 13:22:22','2015-10-16 15:22:22'),('ï•Ý tå½Ï\0#¥lŒ','àž(´rŠå‰Ò\0#¥lŒ','Mi primer Sting','Este es el primer Sting del proyecto Restful','2015-10-16 13:22:23','2015-10-16 15:22:23'),('ð/•Ktå½Ï\0#¥lŒ','àž(´rŠå‰Ò\0#¥lŒ','Sting tuneado y actualizado','Este Sting ha sido actualizado mediante el metodo PUT','2015-10-16 14:34:25','2015-10-16 15:22:24');
/*!40000 ALTER TABLE `stings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `userid` binary(16) NOT NULL,
  `role` enum('registered') NOT NULL DEFAULT 'registered',
  PRIMARY KEY (`userid`,`role`),
  CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES ('*[ëmr‹å‰Ò\0#¥lŒ','registered'),('àž(´rŠå‰Ò\0#¥lŒ','registered'),('íª¨ƒrŠå‰Ò\0#¥lŒ','registered');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` binary(16) NOT NULL,
  `loginid` varchar(15) NOT NULL,
  `password` binary(16) NOT NULL,
  `email` varchar(255) NOT NULL,
  `fullname` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginid` (`loginid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('*[ëmr‹å‰Ò\0#¥lŒ','calamaro','Ü›ÛRÐMÂ\06ÛØ1>ÐU','calamaro@bikinibottom.com','Calamaro Calamarous'),('àž(´rŠå‰Ò\0#¥lŒ','spongebob','Ü›ÛRÐMÂ\06ÛØ1>ÐU','spongebob@bikinibottom.com','Spongebob Squarepants'),('íª¨ƒrŠå‰Ò\0#¥lŒ','patrick','Ü›ÛRÐMÂ\06ÛØ1>ÐU','patrick@bikinibottom.com','Patrick Star');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-23 16:47:56
