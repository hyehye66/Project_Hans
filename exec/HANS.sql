CREATE DATABASE  IF NOT EXISTS `hans_test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hans_test`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: i7d109.p.ssafy.io    Database: hans_test
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `dictations`
--

DROP TABLE IF EXISTS `dictations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dictations` (
  `dictation_seq` bigint NOT NULL AUTO_INCREMENT,
  `difficulty` bigint NOT NULL,
  `sentence` varchar(255) NOT NULL,
  PRIMARY KEY (`dictation_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dictations`
--

LOCK TABLES `dictations` WRITE;
/*!40000 ALTER TABLE `dictations` DISABLE KEYS */;
INSERT INTO `dictations` VALUES (1,3,'지금까지 잘 보존된 전통'),(2,4,'무릎을 부딪쳐 싸운다.'),(3,4,'평소에 품었던 궁금증'),(4,3,'날씨가 맑다.'),(5,4,'다친 곳이 다 나았다.'),(6,4,'비가 내릴 거라고 예상하지 못했어.'),(7,1,'안녕하세요.'),(8,1,'반갑습니다.'),(9,2,'이거 얼마예요?'),(10,2,'관광하러 왔어요.'),(11,5,'빙그레 웃었습니다.'),(12,5,'이게 웬일일까요?'),(13,4,'밭에서 즐겁게'),(14,2,'자리 있어요?'),(15,2,'메뉴판 주세요.'),(16,1,'주문할게요.'),(17,2,'계산서 주세요.'),(18,5,'택시는 어디에서 탈 수 있나요?'),(19,3,'안타까워하였습니다.'),(20,3,'술래잡기를 하였습니다.'),(21,5,'아침녘이면'),(22,5,'김치볶음밥을 먹고'),(23,3,'과일을 잘 깎아서'),(24,1,'감사합니다.'),(25,1,'미안합니다.'),(26,1,'배고파요.'),(27,1,'잠와요.');
/*!40000 ALTER TABLE `dictations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `member_seq` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `introduction` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) NOT NULL,
  `provider` varchar(255) NOT NULL,
  `refresh_token` varchar(255) NOT NULL,
  PRIMARY KEY (`member_seq`),
  UNIQUE KEY `UK_9d30a9u1qpg8eou0otgkwrp5d` (`email`),
  UNIQUE KEY `UK_e6u9u9ypoc7oldnpxdjwcdx3` (`nickname`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES (1,'ssafy1@google.com','ㅅㄷㄴㅅ','회원정보 수정 Test','Google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2Njg1MjcxMjF9.xBHvgyB4gWEEylVtCnwX-mg8NFFURm-5937isa67eUI'),(3,'ssafy3@google.com',NULL,'ssafy3','Google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2NTk5NjQxNjB9.kZJR4aWFWNKJIP2K-CPz7shitgy0B_KcSc_LeMzIp5Q'),(4,'ssafy4@google.com',NULL,'ssafy4','Google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2NTk5NjgwMzl9.f1DdDdz4c_FzbqD_lv7bc8ut6pZKhJ9Eizn94wvc2uk'),(8,'do0134@naver.com',NULL,'영택','google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2NjAxMDYwMTF9.LnpMEqK9OCDXFeBcXXUqU1LmeF5LqRbNYqbtaskP8uM'),(19,'tonywoo0070@gmail.com','HANS','한글쓰는사람들','google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2Njg2NTIxNDF9.PjHsi4RF5uSPz_qtSv1LjFDhGLBs7Udzabt38wpwvY0'),(30,'ssafy5@google.com',NULL,'ssafy5','Google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2NjAwMDgwNTJ9.TiBWQ3z_7j7S_sjNBh6QiHO5S1idbVRvIDyw_aATCAU'),(31,'ijij@gmail.com',NULL,'ji2','google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2NjAwMTIzMjB9.quavMr3ekFrOABmvWExjLg22xu5FEsmCJG-oIAAYjug'),(32,'ssafy7@google.com',NULL,'ssafy7','Google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2NjAwMTc3MTV9.hYYRstOmYeAwICTI3ltaK2k9CRHatgMiQl6zJOE7sXk'),(40,'inseok9876@gmail.com','쏠수있어~!!','inseok','google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2Njg2NTAwMTl9.qkw-xa7U3eir53o22KFTo2b2LDvzNWvveITUyLzghVU'),(41,'ssafy8@google.com',NULL,'ssafy8','Google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2NjAwMjg3OTF9.yc7ud-oiTknQIQUHVPMqpt8KnVAQREb-7dJQrhDyz4k'),(43,'jihyeon@ssafy.com',NULL,'지현','google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2Njc4NjY0NTl9.yPHT_le44sb4PpOlxowoF-9ER96F7fIrEbswU5qugu4'),(44,'kmc9529@gmail.com','자기소개123','닉네임12','google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2Njg1MTYzMDJ9.UH6bXPo4Pe97UW_zLa8Jiin7Gb5E6AELVtksEqOpHzU'),(50,'a@ssafy.com',NULL,'aa','google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2Njc4Njg4NDZ9.HB05sB2cGDwHfqrm8haFXknvhwS_dcarKUWdgUo-yic'),(51,'ab@ssafy.com',NULL,'aab','google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2Njc4Nzg5MDd9.57UnP0rAtsPb7x7Q59FuD-AxXjYhXwy1fKzZQqXFc9Q'),(65,'yein0511@gmail.com','ㅅㄷㄴㅅ','신예인','google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2Njg2MDc5NjR9.GUgL2SPZnzZuz73pg97ddvGp_ZdsgatCqvM-unwJCY4'),(69,'jolielaide11@gmail.com','Hello World','froggy','google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2Njg2NTE5NTJ9.T_QDvKPqcG_RonhGatkRLZfaHP9p_acHqX8b4NW-T6E'),(73,'kmc95290@gmail.com','124142','닉네임임니다','google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2Njg2NTI4NzR9.OERtIPfYniCBxqzk_bptZ-OfG9AxC3B2BIJbm2cbppA'),(74,'paradoxx1492@gmail.com','사막여우','사막여우','google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2NjgwNjYyNTB9.Ok1to2DeSh5Iec1zRKfG2rPTsDMWIlVRran2h5Hfijs'),(75,'hyehyeb66@gmail.com','a','ssaaaaaaa','google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2Njg2MjcwMDN9.vem4Lx9OxpKra_85B6WZ4Iy_Svzx5t0V1a_RKyOyelM'),(76,'gh4pjt@gmail.com','네임펜','모나al','google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2Njg2NTAzNjR9._KExcECwPfjogM_Vrxo-_XLGTe8fPItbEdVB1qkBJjQ'),(77,'jihyeon3560@gmail.com','하이','다시 가입함','google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2Njg2NTAzMDN9.90H3SCNkyGFpuxcSsgM97fTsaxGzu5rbpO9IrWUjnqQ'),(79,'su980323@gmail.com','예인님 팬이에요>_<','신예인님 팬이에요','google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2Njg0MDcwOTB9.-i5GTG_4Y11N_bhRtLMCxoP7VnVJ9uBEyZcF1NQ9zoY'),(80,'chaemj97@gmail.com','하이하이','하이하이','google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2Njg2MDA0NjV9.0B4XAgkUS6W_kQYasMFkvTN2XSuun_rzdDFwqzrVD24'),(91,'do0134@knu.ac.kr','한글천재','훈민정음','google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2Njg2MTU4MzV9.BXoKM53D_F5W0_9teGewuWwzr8aViLsdOx_TfqiDANI'),(93,'zmstjftk@gmail.com','반갑습니다','안녕하세요','google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2Njg2MTY3OTJ9.wrn6sNNuZeR09sLsunuLjYpl-Qa0PK1Rwcku6-wxdbI'),(96,'kmc95291@gmail.com','자기소개','한글조아요','google','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJIYW5zIiwic3ViIjoiSGFucyBSZWZyZXNoVG9rZW4iLCJleHAiOjE2Njg2NTIzMjJ9.f8GriC_T5KIgc_NdqUQIBVKNTXTy2_CPKvA0wYN2Plc');
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modes`
--

DROP TABLE IF EXISTS `modes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modes` (
  `mode_seq` int NOT NULL AUTO_INCREMENT,
  `mode_name` varchar(255) NOT NULL,
  PRIMARY KEY (`mode_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modes`
--

LOCK TABLES `modes` WRITE;
/*!40000 ALTER TABLE `modes` DISABLE KEYS */;
INSERT INTO `modes` VALUES (1,'conversation'),(2,'word'),(3,'body'),(4,'lyrics');
/*!40000 ALTER TABLE `modes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rankings`
--

DROP TABLE IF EXISTS `rankings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rankings` (
  `ranking_seq` bigint NOT NULL AUTO_INCREMENT,
  `score` bigint NOT NULL,
  `tier` varchar(255) NOT NULL,
  `member_seq` bigint NOT NULL,
  `mode_seq` int NOT NULL,
  PRIMARY KEY (`ranking_seq`),
  KEY `FKpbivj9wuq04rqinpjbk4vywsw` (`member_seq`),
  KEY `FKncfjbqmryhcnb822dn98vd8s2` (`mode_seq`),
  CONSTRAINT `FKncfjbqmryhcnb822dn98vd8s2` FOREIGN KEY (`mode_seq`) REFERENCES `modes` (`mode_seq`),
  CONSTRAINT `FKpbivj9wuq04rqinpjbk4vywsw` FOREIGN KEY (`member_seq`) REFERENCES `members` (`member_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=235 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rankings`
--

LOCK TABLES `rankings` WRITE;
/*!40000 ALTER TABLE `rankings` DISABLE KEYS */;
INSERT INTO `rankings` VALUES (4,0,'Bronze',3,2),(5,0,'Bronze',3,3),(6,0,'Bronze',3,4),(7,0,'Bronze',4,2),(8,0,'Bronze',4,3),(9,0,'Bronze',4,4),(19,0,'Bronze',8,2),(20,0,'Bronze',8,3),(21,0,'Bronze',8,4),(52,1167,'Dia',19,2),(53,4795,'Dia',19,3),(54,0,'Bronze',19,4),(85,0,'Bronze',30,2),(86,0,'Bronze',30,3),(87,0,'Bronze',30,4),(88,0,'Bronze',31,2),(89,0,'Bronze',31,3),(90,0,'Bronze',31,4),(91,0,'Bronze',32,2),(92,0,'Bronze',32,3),(93,0,'Bronze',32,4),(94,712,'Gold',40,2),(95,1413,'Dia',40,3),(96,0,'Bronze',40,4),(97,0,'Bronze',41,2),(98,0,'Bronze',41,3),(99,0,'Bronze',41,4),(103,0,'Bronze',43,2),(104,0,'Bronze',43,3),(105,0,'Bronze',43,4),(106,15,'Bronze',44,2),(107,0,'Bronze',44,3),(108,0,'Bronze',44,4),(112,0,'Bronze',50,2),(113,0,'Bronze',50,3),(114,0,'Bronze',50,4),(115,0,'Bronze',51,2),(116,0,'Bronze',51,3),(117,0,'Bronze',51,4),(139,476,'Silver',65,2),(140,2082,'Dia',65,3),(141,0,'Bronze',65,4),(151,127,'Silver',69,2),(152,262,'Silver',69,3),(153,0,'Bronze',69,4),(163,405,'Silver',73,2),(164,1677,'Dia',73,3),(165,0,'Bronze',73,4),(166,0,'Bronze',74,2),(167,0,'Bronze',74,3),(168,0,'Bronze',74,4),(169,181,'Silver',75,2),(170,153,'Silver',75,3),(171,0,'Bronze',75,4),(172,315,'Silver',76,2),(173,988,'Gold',76,3),(174,0,'Bronze',76,4),(175,740,'Gold',77,2),(176,890,'Gold',77,3),(177,0,'Bronze',77,4),(181,3,'Bronze',79,2),(182,0,'Bronze',79,3),(183,0,'Bronze',79,4),(184,0,'Bronze',80,2),(185,0,'Bronze',80,3),(186,0,'Bronze',80,4),(217,0,'Bronze',91,2),(218,0,'Bronze',91,3),(219,0,'Bronze',91,4),(223,0,'Bronze',93,2),(224,0,'Bronze',93,3),(225,0,'Bronze',93,4),(232,0,'Bronze',96,2),(233,0,'Bronze',96,3),(234,0,'Bronze',96,4);
/*!40000 ALTER TABLE `rankings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_members`
--

DROP TABLE IF EXISTS `room_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_members` (
  `room_members` bigint NOT NULL AUTO_INCREMENT,
  `enter_dttm` datetime NOT NULL,
  `token` varchar(255) NOT NULL,
  `member_seq` bigint DEFAULT NULL,
  `room_seq` bigint DEFAULT NULL,
  PRIMARY KEY (`room_members`),
  KEY `FK2lje0qwf52gyecv5mynu2x0yf` (`member_seq`),
  KEY `FK8wnrn3rfwxvjq2rrpptowo9ti` (`room_seq`),
  CONSTRAINT `FK2lje0qwf52gyecv5mynu2x0yf` FOREIGN KEY (`member_seq`) REFERENCES `members` (`member_seq`),
  CONSTRAINT `FK8wnrn3rfwxvjq2rrpptowo9ti` FOREIGN KEY (`room_seq`) REFERENCES `rooms` (`room_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=2598 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_members`
--

LOCK TABLES `room_members` WRITE;
/*!40000 ALTER TABLE `room_members` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms` (
  `room_seq` bigint NOT NULL AUTO_INCREMENT,
  `current_num` int NOT NULL,
  `restrict_num` int NOT NULL,
  `room_dttm` datetime NOT NULL,
  `room_status` bit(1) NOT NULL,
  `title` varchar(255) NOT NULL,
  `member_seq` bigint DEFAULT NULL,
  `mode_seq` int DEFAULT NULL,
  PRIMARY KEY (`room_seq`),
  KEY `FKb7xwcpftakq4t8acj3wadayq9` (`member_seq`),
  KEY `FK4ft9n3o36csnbtur33nblt69x` (`mode_seq`),
  CONSTRAINT `FK4ft9n3o36csnbtur33nblt69x` FOREIGN KEY (`mode_seq`) REFERENCES `modes` (`mode_seq`),
  CONSTRAINT `FKb7xwcpftakq4t8acj3wadayq9` FOREIGN KEY (`member_seq`) REFERENCES `members` (`member_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=2509 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (2465,1,4,'2022-08-19 01:18:09',_binary '','Talk with me!',79,1),(2466,1,6,'2022-08-19 01:18:09',_binary '','블랙핑크 팬들만',80,1),(2467,1,3,'2022-08-19 01:18:09',_binary '','맛집 추천 받아요~',43,1),(2468,1,2,'2022-08-19 01:18:09',_binary '','심심해',3,1),(2470,1,4,'2022-08-19 01:18:09',_binary '','hahaha lol~',4,1),(2471,1,4,'2022-08-19 01:18:09',_binary '','오늘 날씨 어때?',74,1),(2472,1,4,'2022-08-19 01:18:09',_binary '','싸피 화이팅!!',75,1),(2474,1,6,'2022-08-19 02:03:36',_binary '','낱말 게임하자!!',79,2),(2475,1,6,'2022-08-19 02:03:36',_binary '','한국어 너무 어려워ㅠ',80,2),(2476,1,6,'2022-08-19 02:03:36',_binary '','낱말 마스터한다!',43,2),(2477,1,2,'2022-08-19 02:03:36',_binary '','배고프다',3,2),(2478,1,2,'2022-08-19 02:03:36',_binary '','들어오면 바로 시작함',91,2),(2479,1,3,'2022-08-19 02:03:36',_binary '','한국어 마스터들만',4,2),(2480,1,4,'2022-08-19 02:03:36',_binary '','한글 초보자만',74,2),(2481,1,4,'2022-08-19 02:03:36',_binary '','come on!!!!',75,2),(2482,1,4,'2022-08-19 02:03:38',_binary '','몸치들은 오지마라',79,3),(2483,1,6,'2022-08-19 02:03:38',_binary '','게임하자!!',80,3),(2484,1,3,'2022-08-19 02:03:38',_binary '','너무너무 재밌어 몸말',43,3),(2485,1,2,'2022-08-19 02:03:38',_binary '','심심한데 몸말 한판하자',3,3),(2486,1,5,'2022-08-19 02:03:38',_binary '','Let\'s go!!',91,3),(2487,1,4,'2022-08-19 02:03:38',_binary '','오늘은 내가 최고',4,3),(2488,1,4,'2022-08-19 02:03:38',_binary '','싸피 사람들만 드루와',74,3),(2489,1,4,'2022-08-19 02:03:38',_binary '','재밌는 몸말 게임!',75,3);
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `situations`
--

DROP TABLE IF EXISTS `situations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `situations` (
  `situation_seq` bigint NOT NULL AUTO_INCREMENT,
  `category` varchar(255) NOT NULL,
  `english_sentence` varchar(255) NOT NULL,
  `sentence` varchar(255) NOT NULL,
  PRIMARY KEY (`situation_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `situations`
--

LOCK TABLES `situations` WRITE;
/*!40000 ALTER TABLE `situations` DISABLE KEYS */;
INSERT INTO `situations` VALUES (1,'shopping','Where do they sell cosmetics?','화장품 매장이 어디에요?'),(2,'shopping','What is the most popular?','어떤 것이 가장 인기있어요?'),(3,'shopping','Can I look around?','구경 좀 해도 될까요?'),(4,'shopping','Can I try this on?','이것 입어봐도 될까요?'),(5,'shopping','Could you show me some other items?','다른 것 좀 보여주실 수 있을까요?'),(6,'shopping','I\'ll think about it a little more.','조금 더 생각해 볼게요.'),(7,'shopping','How much is it?','얼마예요?'),(8,'shopping','Where can I pay for it?','어디서 계산하나요?'),(9,'shopping','Can I get a receipt?','영수증 주시겠어요?'),(10,'shopping','I want to exchange it for another product.','다른 상품으로 교환하고 싶어요.'),(11,'shopping','Can I get a refund?','환불받을 수 있나요?'),(12,'restaurant','Can we get a table?','자리 있어요?'),(13,'restaurant','I made a reservation at six.','6시에 예약했습니다.'),(14,'restaurant','Could we get a menu, please?','메뉴판 주세요.'),(15,'restaurant','I would like to order now.','주문할게요.'),(16,'restaurant','Could we get is refilled?','이거 리필해 주세요.'),(17,'restaurant','To go, please.','포장해 주세요.'),(18,'restaurant','Check, please.','계산서 주세요.'),(19,'restaurant','We\'re paying together.','같이 계산해 주세요.'),(20,'hospital','I\'m hoarse.','목이 쉬었어요.'),(21,'hospital','My legs are swollen.','다리가 부었어요.'),(22,'hospital','Will it leave a scar?','흉터가 생길까요?'),(23,'hospital','I have a cold.','감기에 걸렸어요.'),(24,'hospital','I have a runny nose.','계속 콧물이 나요.'),(25,'hospital','I itch all over my body.','몸이 가려워요.'),(26,'hospital','My eyes are dry.','눈이 건조해요.'),(27,'hospital','My back hurts so much.','허리가 너무 아파요.'),(28,'hospital','I\'m allergic to peaches.','복숭아 알레르기가 있어요.'),(29,'travel','Where can I find a taxi?','택시는 어디에서 탈 수 있나요?'),(30,'travel','I\'m here on Sightseeing.','관광하러 왔어요.'),(31,'travel','Do you offer student discounts?','학생 할인이 돼요?'),(32,'travel','Can I keep my baggage here?','여기에 짐을 맡길 수 있나요?'),(33,'travel','Let\'s take a picture together.','같이 사진 찍어요.'),(34,'travel','Hou much is it for each person?','한 사람당 얼마예요?'),(35,'travel','Where is the tourist information center?','관광 안내소가 어디예요?'),(36,'travel','I need tourist information.','관광 정보가 필요해요.'),(37,'travel','Where do I get a ticket?','표를 어디서 사나요?'),(38,'travel','What should I bring?','미리 준비해야 할 것이 있나요?'),(39,'travel','It\'s really magnificent.','정말 어마어마하군요.'),(40,'call','Please tell him to call me.','제게 전화하라고 전해주세요.'),(41,'call','I\'ll call back in five minutes.','5분 뒤에 다시 걸겠습니다.'),(42,'call','Would you call me back?','다시 걸어주실 수 있나요?'),(43,'call','I\'ll put him on.','그 분을 바꿔드릴게요.'),(44,'call','Please send me a text message.','문자 메시지 주세요.'),(45,'call','What are you calling about?','무슨 일로 전화하셨나요?'),(46,'call','I\'m sorry that I couldn\'t answer your call.','전화 못 받아서 죄송합니다.'),(47,'call','Please tell him it\'s urgent.','급한 일이라고 전해 주세요.'),(48,'call','Okay. I\'ll call you back.','네, 다시 전화드릴게요.'),(49,'call','I\'ll leave a message.','메시지를 남길게요.'),(50,'call','Please tell him that I called.','제가 전화했다고 전해주세요.');
/*!40000 ALTER TABLE `situations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `words`
--

DROP TABLE IF EXISTS `words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `words` (
  `word_seq` bigint NOT NULL AUTO_INCREMENT,
  `difficulty` int NOT NULL,
  `mean` varchar(255) NOT NULL,
  `word` varchar(255) NOT NULL,
  PRIMARY KEY (`word_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `words`
--

LOCK TABLES `words` WRITE;
/*!40000 ALTER TABLE `words` DISABLE KEYS */;
INSERT INTO `words` VALUES (1,1,'빨간 과일','사과'),(2,1,'노란색 긴 과일','바나나'),(3,1,'학생들이 공부하러 가는 곳','학교'),(4,1,'OO을 매다.','가방'),(5,1,'종이를 자를 때 사용하는 것','가위'),(6,1,'누워서 잘 수 있는 곳','침대'),(7,1,'추운 계절','겨울'),(8,1,'더운 계절','여름'),(9,1,'여름에 먹는 큰 과일','수박'),(10,1,'노래 부르는 직업을 가진 사람','가수'),(11,1,'물에서 하는 운동','수영'),(12,1,'O코입','눈'),(13,2,'저온에서 음식물을 보관하기 위한 기계','냉장고'),(14,2,'아플 때 가는 곳','병원'),(15,2,'노래를 부르러 가는 곳','노래방'),(16,2,'태어난 날','생일'),(17,2,'2개의 바퀴를 가진 탈 것','자전거'),(18,2,'비벼서 먹는 음식','비빔밥'),(19,2,'비 올 때 쓰는 것','우산'),(20,2,'얼큰한 면 요리','라면'),(21,2,'코가 긴 동물','코끼리'),(22,2,'한국의 유명한 축구 선수','손흥민'),(23,2,'아플 때 먹는 것','약'),(24,2,'너와 나, OO','우리'),(25,2,'시간을 확인할 때 보는 것','시계'),(26,3,'생일에 먹는 국','미역국'),(27,3,'한국의 전통 무술','태권도'),(28,3,'같이 먹었을 때 맛있는 음식. 야구장에서 주로 먹음','치맥'),(29,3,'월화수목금OO','주말'),(30,3,'한국에서 많이 마시는 술','소주'),(31,3,'닦거나 코 풀 때 사용하는 얇은 종이','휴지'),(32,3,'한국에서 가장 큰 섬','제주도'),(33,3,'한국의 수도','서울'),(34,3,'한국의 명절','추석'),(35,3,'돈을 넣을 수 있는 물건','지갑'),(36,3,'다른 사람과 정한 것. OO하다.','약속'),(37,3,'노랗고 검은 색 줄이 있는 무서운 동물','호랑이'),(38,3,'사람이 걸어서 오르내리기 위해 건물에 만든 것','계단'),(39,3,'김 안에 야채와 밥 등을 넣어 만든 음식','김밥'),(40,3,'비 올 때 입는 것','우비'),(41,4,'밖에서 음식을 사 먹는 것','외식'),(42,4,'비행기 타는 곳','공항'),(43,4,'새해에 먹는 음식','떡국'),(44,4,'몸에 뿌리는 액체 화장품','향수'),(45,4,'여름 벌레','모기'),(46,4,'규칙으로 어떤 것을 하지 못하도록 함','금지'),(47,4,'과일이나 쌀 등을 심어서 기르고 거두는 일','농사'),(48,4,'벌레, 한국에서 유명한 영화 제목','기생충'),(49,4,'돈을 거래할 수 있는 기관','은행'),(50,4,'자동차, 기차, 배, 비행기 등을 이용하여 사람이 오고 감. OO수단','교통'),(51,4,'뜻밖에 일어난 불행한 일','사고'),(52,4,'음식을 만드는 것','요리'),(53,5,'하루 동안의 제일 낮은 온도와 제일 높은 온도의 차이','일교차'),(54,5,'8월 15일','광복절'),(55,5,'활로 겨루는 경기','양궁'),(56,5,'한글을 만든 사람','세종대왕'),(57,5,'한국의 대표적인 민요','아리랑'),(58,5,'서울에 있는 강','한강'),(59,5,'물고기 잡는 일을 직업으로 하는 사람','어부'),(60,5,'안부, 소식을 적어 보내는 글','편지'),(61,5,'0도 이하의 온도','영하'),(62,5,'비가 엄청 많이 내리는 것','폭우'),(63,5,'좋은 운수, 복','행운'),(64,5,'마그마가 땅으로 분출하는 곳','화산'),(65,5,'바닷물과 땅이 서로 닿은 곳','해변');
/*!40000 ALTER TABLE `words` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-19 11:56:40
