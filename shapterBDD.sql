-- MySQL dump 10.13  Distrib 5.5.24, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: shapter
-- ------------------------------------------------------
-- Server version	5.5.24-3

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
-- Table structure for table `app_course`
--

DROP TABLE IF EXISTS `app_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `school_id` int(11) NOT NULL,
  `code` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_course`
--

LOCK TABLES `app_course` WRITE;
/*!40000 ALTER TABLE `app_course` DISABLE KEYS */;
INSERT INTO `app_course` VALUES (1,'Informatique graphique 3D et réalité virtuelle',1,'INFSI350'),(2,'Interfaces hommes-machines',1,'INFSI351'),(3,'Ingénierie du Web',1,'INFSI347'),(4,'Text Searching Algorithms',11,''),(17,'Smart and accessible home',1,''),(18,'Test',12,''),(19,'Test 2',12,''),(20,'Test 3',12,''),(22,'Ingénierie Nucléaire',15,''),(23,'Neutronique',15,''),(24,'Géologie',17,''),(25,'Energie renouvelables',15,'EN1021'),(26,'Management de projet et sociologie de l\'innovation',1,'SES242'),(27,'Traitement et analyse des images',1,'SI241'),(28,'Paradigmes de programmation',1,'IN224');
/*!40000 ALTER TABLE `app_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_course_teachers`
--

DROP TABLE IF EXISTS `app_course_teachers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_course_teachers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `course_id` (`course_id`,`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_course_teachers`
--

LOCK TABLES `app_course_teachers` WRITE;
/*!40000 ALTER TABLE `app_course_teachers` DISABLE KEYS */;
INSERT INTO `app_course_teachers` VALUES (1,1,1),(15,1,3),(18,2,3),(6,3,8),(2,17,4),(3,18,5),(4,18,6),(9,18,12),(5,19,7),(7,20,9),(10,22,13),(11,23,14),(12,24,15),(13,25,16),(14,26,3),(16,27,3),(17,28,3);
/*!40000 ALTER TABLE `app_course_teachers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_coursecomment`
--

DROP TABLE IF EXISTS `app_coursecomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_coursecomment` (
  `usertext_ptr_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `study_id` int(11) NOT NULL,
  PRIMARY KEY (`usertext_ptr_id`),
  KEY `app_coursecomment_ff48d8e5` (`course_id`),
  KEY `app_coursecomment_da5fc7d6` (`study_id`),
  CONSTRAINT `course_id_refs_id_4dd9ab0913bcb149` FOREIGN KEY (`course_id`) REFERENCES `app_course` (`id`),
  CONSTRAINT `study_id_refs_id_37bd760b49bced52` FOREIGN KEY (`study_id`) REFERENCES `app_study` (`id`),
  CONSTRAINT `usertext_ptr_id_refs_id_659e65b008697748` FOREIGN KEY (`usertext_ptr_id`) REFERENCES `app_usertext` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_coursecomment`
--

LOCK TABLES `app_coursecomment` WRITE;
/*!40000 ALTER TABLE `app_coursecomment` DISABLE KEYS */;
INSERT INTO `app_coursecomment` VALUES (12,2,5),(13,4,6),(14,1,5),(15,3,9),(16,18,11),(17,22,12),(18,23,13),(19,24,14),(20,25,15),(21,26,9),(22,1,9),(23,27,9),(24,28,9),(25,2,5);
/*!40000 ALTER TABLE `app_coursecomment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_news`
--

DROP TABLE IF EXISTS `app_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(250) NOT NULL,
  `content` varchar(4000) NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_news`
--

LOCK TABLES `app_news` WRITE;
/*!40000 ALTER TABLE `app_news` DISABLE KEYS */;
INSERT INTO `app_news` VALUES (3,'Le site est enfin ouvert !','Bienvenue sur notre nouveau site !\nDécouvrez, imaginez et partagez votre parcours scolaire, et ce sans mauvaise surprise !\n\nA très bientôt !','2013-04-30 12:13:38'),(4,'Nouveau : recherchez vos cours favoris !','Toute nouvelle fonctionnalité sur le site ! Vous pouvez désormais rechercher en détail les cours et/ou les élèves qui vous correspondent le mieux, grâce à notre algorithme HR5B8 ultra développé.\n\nPour ce faire, rien de plus simple ! Cliquez sur l\'onglet \"Rechercher\", et... enjoy !\n\nNDLR : Toute ressemblance avec la fonction rand() serait purement fortuite.','2013-04-30 12:13:48');
/*!40000 ALTER TABLE `app_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_questiongenius`
--

DROP TABLE IF EXISTS `app_questiongenius`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_questiongenius` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `question` (`question`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_questiongenius`
--

LOCK TABLES `app_questiongenius` WRITE;
/*!40000 ALTER TABLE `app_questiongenius` DISABLE KEYS */;
/*!40000 ALTER TABLE `app_questiongenius` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_school`
--

DROP TABLE IF EXISTS `app_school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `country` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_school`
--

LOCK TABLES `app_school` WRITE;
/*!40000 ALTER TABLE `app_school` DISABLE KEYS */;
INSERT INTO `app_school` VALUES (1,'Telecom ParisTech','France'),(2,'Czech Technical University in Prague','République tchèque'),(5,'Ecole des Mines de Nancy/Technische Universität München','Allemagne'),(7,'Imperial College London','United Kingdom'),(9,'Kungliga Tekniska högskolan','Suède'),(10,'KTH','Suède'),(11,'Czech Technical University in Prague','France'),(12,'École Centrale de Lyon','France'),(13,'Mines de Nancy','France'),(14,'Telecom','France'),(15,'Centrale Paris','France'),(17,'Centrale Pékin','Chine');
/*!40000 ALTER TABLE `app_school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_schooldesc`
--

DROP TABLE IF EXISTS `app_schooldesc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_schooldesc` (
  `usertext_ptr_id` int(11) NOT NULL,
  `school_id` int(11) NOT NULL,
  PRIMARY KEY (`usertext_ptr_id`),
  KEY `app_schooldesc_1ebdc00a` (`school_id`),
  CONSTRAINT `school_id_refs_id_68b1f509f98119b4` FOREIGN KEY (`school_id`) REFERENCES `app_school` (`id`),
  CONSTRAINT `usertext_ptr_id_refs_id_36c2d7142d234a8e` FOREIGN KEY (`usertext_ptr_id`) REFERENCES `app_usertext` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_schooldesc`
--

LOCK TABLES `app_schooldesc` WRITE;
/*!40000 ALTER TABLE `app_schooldesc` DISABLE KEYS */;
INSERT INTO `app_schooldesc` VALUES (1,1),(26,1);
/*!40000 ALTER TABLE `app_schooldesc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_student`
--

DROP TABLE IF EXISTS `app_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_student` (
  `user_ptr_id` int(11) NOT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `student_year` int(11) DEFAULT NULL,
  `last_connection` datetime NOT NULL,
  `personal_desc` varchar(5000) NOT NULL,
  `desc_moderated` tinyint(1) NOT NULL,
  `terms_accepted` tinyint(1) NOT NULL,
  PRIMARY KEY (`user_ptr_id`),
  CONSTRAINT `user_ptr_id_refs_id_4c183c42` FOREIGN KEY (`user_ptr_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_student`
--

LOCK TABLES `app_student` WRITE;
/*!40000 ALTER TABLE `app_student` DISABLE KEYS */;
INSERT INTO `app_student` VALUES (2,'students/adrien_8.png',NULL,'2013-04-02 11:51:58','',0,0),(3,'students/IMG_0205_1.JPG',NULL,'2013-04-02 12:05:39','',0,0),(4,NULL,NULL,'2013-04-02 12:05:39','',0,0),(5,NULL,NULL,'2013-04-02 12:05:39','',0,0),(6,'students/PhotoID2012.jpg',NULL,'2013-04-02 12:05:39','',0,0),(7,'students/IMG_0021.JPG',NULL,'2013-04-02 12:05:39','',0,0),(8,NULL,NULL,'2013-04-14 06:37:44','',0,0);
/*!40000 ALTER TABLE `app_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_student_courses`
--

DROP TABLE IF EXISTS `app_student_courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_student_courses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `student_id` (`student_id`,`course_id`),
  KEY `course_id_refs_id_d1dd9497` (`course_id`),
  CONSTRAINT `course_id_refs_id_d1dd9497` FOREIGN KEY (`course_id`) REFERENCES `app_course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_student_courses`
--

LOCK TABLES `app_student_courses` WRITE;
/*!40000 ALTER TABLE `app_student_courses` DISABLE KEYS */;
INSERT INTO `app_student_courses` VALUES (3,2,1),(1,2,2),(7,2,3),(2,2,4),(5,3,18),(6,3,19),(8,3,20),(12,3,24),(15,6,1),(18,6,2),(4,6,17),(14,6,26),(16,6,27),(17,6,28),(10,7,22),(11,7,23),(13,7,25);
/*!40000 ALTER TABLE `app_student_courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_student_schools`
--

DROP TABLE IF EXISTS `app_student_schools`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_student_schools` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `school_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `student_id` (`student_id`,`school_id`),
  KEY `app_student_schools_42ff452e` (`student_id`),
  KEY `app_student_schools_1ebdc00a` (`school_id`),
  CONSTRAINT `student_id_refs_user_ptr_id_f7fb786b` FOREIGN KEY (`student_id`) REFERENCES `app_student` (`user_ptr_id`),
  CONSTRAINT `school_id_refs_id_81637361` FOREIGN KEY (`school_id`) REFERENCES `app_school` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_student_schools`
--

LOCK TABLES `app_student_schools` WRITE;
/*!40000 ALTER TABLE `app_student_schools` DISABLE KEYS */;
INSERT INTO `app_student_schools` VALUES (1,2,1),(2,2,11),(3,3,12),(10,3,17),(4,4,13),(5,5,1),(6,6,1),(7,7,15),(8,8,12);
/*!40000 ALTER TABLE `app_student_schools` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_student_study`
--

DROP TABLE IF EXISTS `app_student_study`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_student_study` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `study_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `student_id` (`student_id`,`study_id`),
  KEY `app_student_study_42ff452e` (`student_id`),
  KEY `app_student_study_da5fc7d6` (`study_id`),
  CONSTRAINT `student_id_refs_user_ptr_id_e4e80d57` FOREIGN KEY (`student_id`) REFERENCES `app_student` (`user_ptr_id`),
  CONSTRAINT `study_id_refs_id_83e9c05b` FOREIGN KEY (`study_id`) REFERENCES `app_study` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_student_study`
--

LOCK TABLES `app_student_study` WRITE;
/*!40000 ALTER TABLE `app_student_study` DISABLE KEYS */;
INSERT INTO `app_student_study` VALUES (1,2,5),(2,2,6),(6,2,9),(4,3,7),(5,3,8),(8,3,11),(11,3,14),(3,6,5),(13,6,9),(9,7,12),(10,7,13),(12,7,15);
/*!40000 ALTER TABLE `app_student_study` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_study`
--

DROP TABLE IF EXISTS `app_study`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_study` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) NOT NULL,
  `school_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `app_study_1ebdc00a` (`school_id`),
  CONSTRAINT `school_id_refs_id_9dd8549` FOREIGN KEY (`school_id`) REFERENCES `app_school` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_study`
--

LOCK TABLES `app_study` WRITE;
/*!40000 ALTER TABLE `app_study` DISABLE KEYS */;
INSERT INTO `app_study` VALUES (3,'Master\'s programme in Machine Learning',9),(4,'Master\'s program in Machine Learning',10),(5,'IWG',1),(6,'IWG',11),(7,'Bio-ingénierie nanotechnologies',12),(8,'Ingénieur Business Development',12),(9,'',1),(11,'',12),(12,'Rien je suis qu\'en 2A',15),(13,'Toujours rien',15),(14,'Master Business et Géologie',17),(15,'Energie',15);
/*!40000 ALTER TABLE `app_study` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_teacher`
--

DROP TABLE IF EXISTS `app_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_teacher`
--

LOCK TABLES `app_teacher` WRITE;
/*!40000 ALTER TABLE `app_teacher` DISABLE KEYS */;
INSERT INTO `app_teacher` VALUES (1,'Tamy','Boubekeur'),(2,'Eric','Lecolinet'),(3,'',''),(4,'','Rodriguez'),(5,'Test','Test'),(6,'Test','Test 2'),(7,'test','test 3'),(8,'Pierre','Senellart'),(9,'test test test','test test'),(11,'azef','azef'),(12,'Test','test test'),(13,'John','Smith'),(14,'Mike','Johnson'),(15,'Chang','Lihn'),(16,'David','Mertil');
/*!40000 ALTER TABLE `app_teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_usertext`
--

DROP TABLE IF EXISTS `app_usertext`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_usertext` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment` longtext NOT NULL,
  `student_id` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `app_usertext_42ff452e` (`student_id`),
  CONSTRAINT `student_id_refs_user_ptr_id_72fcfd3f3c937715` FOREIGN KEY (`student_id`) REFERENCES `app_student` (`user_ptr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_usertext`
--

LOCK TABLES `app_usertext` WRITE;
/*!40000 ALTER TABLE `app_usertext` DISABLE KEYS */;
INSERT INTO `app_usertext` VALUES (1,'Mes 3 ans à Télécom ParisTech ont été les plus bénéfiques, professionnellement parlant.\r\n\r\nDe plus, l\'activité associative constante qui y règne m\'a à la fois amusé et servi ; amusé par les activités proposées et par l\'organisation même d\'activités, servi car il est toujours utile de savoir gérer une équipe de 15 personnes, organiser un évènement pour 300 personnes, améliorer ses relations entreprises...\r\n\r\nEnfin, les activités extra-scolaires (permises entre autres par une administration conciliante) m\'ont permis de m\'épanouir dans ce que j\'aime faire.\r\n\r\nBref, je recommande chaudement mon école !',2,'2013-05-02 10:08:15'),(4,'Ce cours n\'était pas exactement ce à quoi je m\'attendais. Je m\'attendais à un cours sur la théorie des interfaces entre l\'homme et la machine, à savoir quels sont les points importants à respecter dans l\'ergonomie d\'une application, quelles sont les réactions d\'un humain face à telle ou telle situation, comment les deviner, comment y faire face... mais ce n\'était qu\'en fait une infime partie du cours. En effet, le cours se décompose en trois parties : une partie cours de Qt, langage de programmation pour créer des interfaces, à la bibliothèque aussi longue qu\'ennuyante, une partie sur les concepts utilisateurs, très bon cours, très intéressant, ce à quoi je m\'attendais (avec, qui plus est, d\'excellents profs, un cours très interactif, sans arrêt remis en question par les enseignants qui appliquent dans leur cours même ce qu\'ils enseignent), et une \"partie\" très confuse ou on range tout le reste (ergonomie, 3D,...).\r\n\r\nLa validation se fait via plusieurs critères : un projet Qt, une des pires choses que j\'ai eu à faire en 2A (heureusement que l\'on peut choisir son binome avec qui travailler, ce qui m\'a sauvé de l\'ennui et du désespoir), un projet sur les concepts utilisateurs, plutôt amusant (il a fallu faire des tests utilisateurs sur notre application avec des petites maquettes papier faites à la main), le partiel (pas forcément trivial si on ne va pas en cours) et apparemment une partie sur les TP.\r\n\r\nEn bref, une UE que je ne conseillerais pas, car malgré une partie du cours intéressante et utile, cela ne compense pas le temps passé sur les docs de Qt pour arriver à afficher un bouton, qui constitue la majeure partie du cours...\r\n\r\nEDIT : Le cours a été modifié cette année, le projet n\'est plus obligatoirement en Qt.',2,'2013-05-03 09:54:10'),(5,'Ce cours est très facile. Les notions abordées sont très élémentaires. On y parle de théorie des graphes et d\'algorithmes de reconnaissance de texte.\r\nLes notions qu\'on nous transmet ne sont pas désagréables, mais on perçoit mal leur intérêt. Et même après avoir fait des études et un stage en algorithmie, je n\'ai toujours pas vu de champs dans lesquels je pourraient les réutiliser.\r\nSinon les cours ne sont pas très prenant, et laisse bien le temps de profiter de la ville.\r\nLes activités organisés sont très cools, et il y a 3 cours à Prague, ce qui permet de retrouver pas mal de Telecomiens.',2,'2013-05-03 09:54:10'),(6,'Le rayon de soleil de ma 2ème année ! Un cours que j\'avais pris plus par curiosité que par passion, et je fus comblé.\r\nTout d\'abord, d\'excellents profs, la crème de la crème dans le domaine : Tamy Boubekeur et Elmar Eisemann, d\'excellents pédagogues et qui savent rendre le sujet intéressant.\r\nEnsuite, une présentation d\'article scientifique, qui fait partie de la note, qui peut permettre d\'en apprendre un peu plus sur le sujet si on choisit bien son thème ; mais ce n\'est pas la meilleure partie selon moi.\r\nEnfin, pour finir en beauté, et c\'est la majeure partie de l\'UE, le projet ; sur environ 3/4 semaines, il vous faut développer un moteur de rendu 3D, en groupe de 3 (choisi par vous, si c\'est pas beau la vie). Qu\'est-ce que c\'est un moteur de rendu 3D ? En gros, vous avez une scène 3D (elle est donnée à la base du projet, vous n\'avez pas à tout coder), et vous devez en faire quelque chose de joli. Des ombres, des réflexions, des effets de flou, de mouvement... de quoi vraiment s\'amuser, surtout qu\'on a directement un résultat visuel (et vous pourrez vous amuser à y mettre plein d\'animaux et d\'objets bizarres de toutes les couleurs). Pour \"les petits et les grands\" du codage, car réussir le projet est assez simple, mais l\'approfondir (jusqu\'à faire un moteur de rendu en temps réel) et l\'optimiser (pour que ça ne prenne pas 30mins par image) reste réservé aux geeks qui y trouveront leur plaisir.\r\n\r\nEn bref : Mon coup de coeur, si vous aimez le traitement d\'image et/ou le codage, cette UE est faite pour vous !',2,'2013-05-03 09:54:10'),(7,'Excellent prof, très ouvert aux questions, réponds vite et bien au mail, prêt à aider dans vos divers projets (ex: POUF).\r\nCe cours donne un très grand aperçu des technologies du Web. Il peut être utile même si vous vous y connaissez déjà en Web ; certaines technologies, comme le xml, sont très utiles et pas forcément très communes lorsqu\'on code par soit-même. Un petit moins pour le Java EE, TP absolument horrible qui ne m\'a rien appris de par sa complexité et son manque d\'intérêt. Le TP Javascript, en revanche, est très bien fait, bien que noté sévèrement. Les autres TP sont dans l\'ensemble assez classiques (HTML, CSS, PHP...), ou assez originaux (moteurs de recherches, map-reduce...). On regrettera peut-être l\'absence du lien avec un langage comme Python et des technologies comme Django, utilisées constamment en Web, ça pourrait remplacer le TP Java EE.\r\nSinon, la notation se fait sur les TP. Pas forcément faciles, mais au final, l\'intégralité de la promo a validé cette année, donc ça se fait bien !\r\nEn bref, si vous voulez apprendre à créer un site, vous aurez plus vite fait d\'apprendre par vous-même, ce cours vous ne le permettra pas, et c\'est pourquoi je le déconseille aux débutants. Il vous donnera par contre un bon aperçu de ce qu\'il est possible de faire, y compris si vous vous y connaissez. Pour la validation, elle est quasi immédiate, à condition d\'aller aux TP et de les faire assez sérieusement !',2,'2013-05-03 09:54:10'),(8,'Coulos !',3,'2013-05-03 09:54:10'),(9,'Très bon cours d\'introduction aux problématiques du nucléaire. Cours très complet avec des intervenant de qualité (faire attention à la propagande pro nucléaire quand même)',7,'2013-05-03 09:54:10'),(10,'Très bon cours, plus théorique qu\'ingénierie nucléaire. Très bon complément à ce cours',7,'2013-05-03 09:54:10'),(11,'Très bon cours.',3,'2013-05-03 09:54:10'),(12,'Ce cours n\'était pas exactement ce à quoi je m\'attendais. Je m\'attendais à un cours sur la théorie des interfaces entre l\'homme et la machine, à savoir quels sont les points importants à respecter dans l\'ergonomie d\'une application, quelles sont les réactions d\'un humain face à telle ou telle situation, comment les deviner, comment y faire face... mais ce n\'était qu\'en fait une infime partie du cours. En effet, le cours se décompose en trois parties : une partie cours de Qt, langage de programmation pour créer des interfaces, à la bibliothèque aussi longue qu\'ennuyante, une partie sur les concepts utilisateurs, très bon cours, très intéressant, ce à quoi je m\'attendais (avec, qui plus est, d\'excellents profs, un cours très interactif, sans arrêt remis en question par les enseignants qui appliquent dans leur cours même ce qu\'ils enseignent), et une \"partie\" très confuse ou on range tout le reste (ergonomie, 3D,...).\r\n\r\nLa validation se fait via plusieurs critères : un projet Qt, une des pires choses que j\'ai eu à faire en 2A (heureusement que l\'on peut choisir son binome avec qui travailler, ce qui m\'a sauvé de l\'ennui et du désespoir), un projet sur les concepts utilisateurs, plutôt amusant (il a fallu faire des tests utilisateurs sur notre application avec des petites maquettes papier faites à la main), le partiel (pas forcément trivial si on ne va pas en cours) et apparemment une partie sur les TP.\r\n\r\nEn bref, une UE que je ne conseillerais pas, car malgré une partie du cours intéressante et utile, cela ne compense pas le temps passé sur les docs de Qt pour arriver à afficher un bouton, qui constitue la majeure partie du cours...\r\n\r\nEDIT : Le cours a été modifié cette année, le projet n\'est plus obligatoirement en Qt.',2,'2013-05-03 15:20:33'),(13,'Ce cours est très facile. Les notions abordées sont très élémentaires. On y parle de théorie des graphes et d\'algorithmes de reconnaissance de texte.\r\nLes notions qu\'on nous transmet ne sont pas désagréables, mais on perçoit mal leur intérêt. Et même après avoir fait des études et un stage en algorithmie, je n\'ai toujours pas vu de champs dans lesquels je pourraient les réutiliser.\r\nSinon les cours ne sont pas très prenant, et laisse bien le temps de profiter de la ville.\r\nLes activités organisés sont très cools, et il y a 3 cours à Prague, ce qui permet de retrouver pas mal de Telecomiens.',2,'2013-05-03 15:20:33'),(14,'Le rayon de soleil de ma 2ème année ! Un cours que j\'avais pris plus par curiosité que par passion, et je fus comblé.\r\nTout d\'abord, d\'excellents profs, la crème de la crème dans le domaine : Tamy Boubekeur et Elmar Eisemann, d\'excellents pédagogues et qui savent rendre le sujet intéressant.\r\nEnsuite, une présentation d\'article scientifique, qui fait partie de la note, qui peut permettre d\'en apprendre un peu plus sur le sujet si on choisit bien son thème ; mais ce n\'est pas la meilleure partie selon moi.\r\nEnfin, pour finir en beauté, et c\'est la majeure partie de l\'UE, le projet ; sur environ 3/4 semaines, il vous faut développer un moteur de rendu 3D, en groupe de 3 (choisi par vous, si c\'est pas beau la vie). Qu\'est-ce que c\'est un moteur de rendu 3D ? En gros, vous avez une scène 3D (elle est donnée à la base du projet, vous n\'avez pas à tout coder), et vous devez en faire quelque chose de joli. Des ombres, des réflexions, des effets de flou, de mouvement... de quoi vraiment s\'amuser, surtout qu\'on a directement un résultat visuel (et vous pourrez vous amuser à y mettre plein d\'animaux et d\'objets bizarres de toutes les couleurs). Pour \"les petits et les grands\" du codage, car réussir le projet est assez simple, mais l\'approfondir (jusqu\'à faire un moteur de rendu en temps réel) et l\'optimiser (pour que ça ne prenne pas 30mins par image) reste réservé aux geeks qui y trouveront leur plaisir.\r\n\r\nEn bref : Mon coup de coeur, si vous aimez le traitement d\'image et/ou le codage, cette UE est faite pour vous !',2,'2013-05-03 15:20:33'),(15,'Excellent prof, très ouvert aux questions, réponds vite et bien au mail, prêt à aider dans vos divers projets (ex: POUF).\r\nCe cours donne un très grand aperçu des technologies du Web. Il peut être utile même si vous vous y connaissez déjà en Web ; certaines technologies, comme le xml, sont très utiles et pas forcément très communes lorsqu\'on code par soit-même. Un petit moins pour le Java EE, TP absolument horrible qui ne m\'a rien appris de par sa complexité et son manque d\'intérêt. Le TP Javascript, en revanche, est très bien fait, bien que noté sévèrement. Les autres TP sont dans l\'ensemble assez classiques (HTML, CSS, PHP...), ou assez originaux (moteurs de recherches, map-reduce...). On regrettera peut-être l\'absence du lien avec un langage comme Python et des technologies comme Django, utilisées constamment en Web, ça pourrait remplacer le TP Java EE.\r\nSinon, la notation se fait sur les TP. Pas forcément faciles, mais au final, l\'intégralité de la promo a validé cette année, donc ça se fait bien !\r\nEn bref, si vous voulez apprendre à créer un site, vous aurez plus vite fait d\'apprendre par vous-même, ce cours vous ne le permettra pas, et c\'est pourquoi je le déconseille aux débutants. Il vous donnera par contre un bon aperçu de ce qu\'il est possible de faire, y compris si vous vous y connaissez. Pour la validation, elle est quasi immédiate, à condition d\'aller aux TP et de les faire assez sérieusement !',2,'2013-05-03 15:20:33'),(16,'Coulos !',3,'2013-05-03 15:20:33'),(17,'Très bon cours d\'introduction aux problématiques du nucléaire. Cours très complet avec des intervenant de qualité (faire attention à la propagande pro nucléaire quand même)',7,'2013-05-03 15:20:33'),(18,'Très bon cours, plus théorique qu\'ingénierie nucléaire. Très bon complément à ce cours',7,'2013-05-03 15:20:33'),(19,'Très bon cours.',3,'2013-05-03 15:20:33'),(20,'Dans l\'ensemble, un bon cours d\'introduction aux energies renouvellables ou on aborde de nombreuses technologies, de l\'eolien a la biomasse.\r\n Le point negatif pour moi est que l\'on rentre pas assez dans les details de chaque technologies. Le cours est aussi pour une assez grande partie oriente sur les reseaux, a savoir parce que j\'ai trouve ca relou.',7,'2013-05-09 23:53:24'),(21,'Première partie du cours très intéressante, l\'intervenant a été chef de projet sur des projets absolument faramineux (refonte du système de contrôle des contrôleurs de la navigation aérienne de toute la zone australienne, et mise en place des boîtiers enregistreurs de TV chez Canal+). On apprendra à gérer un projet, l\'évaluation se faisant sur excel sur un cas pratique. Il faut vraiment essayer de faire vite et bien pour se démêler les pattes dans de la logique pas compliquée mais dense (bidule commencera à travailler sur machin 3 mois après que trucmuche aura fini les 2/3 de sa tâche, dont la fin devra correspondre avec l\'installation du matériel de truc qui durera deux fois plus longtemps que la tâche de bidule...)\r\nLa deuxième partie : de la psychologie des relations. On apprend à mettre des mots sur les différents types de relations entre personnes, certains pourraient penser qu\'on enfonce beaucoup de portes ouvertes mais en vrai, c\'est intéressant. Ce n\'est pas parce qu\'on apprend pas de théorèmes que c\'est inutile ! C\'est agréable d\'apprendre autre chose que des sciences et du management, et avoir des explications d\'une coach-psy sur les comportements des gens au travail et quelques autres théories.\r\nTroisième partie : plusieurs intervenants viennent nous parler de diverses théories et études de cas de sociologie : qu\'et-ce que le pouvoir ? Comment être légitime quand on donne des ordres ? Qu\'est-ce que l\'autorité ? Pourquoi les gens obéissent-ils ? etc.\r\nEn conclusion, un bon cours pour qui veut s\'aérer un peu la tête et se faire une bonne culture de socio sur des thèmes très intéressants.',6,'2013-05-10 17:41:57'),(22,'Une UE à prendre si on st intéressé (même un tout petit peu) par le domaine de l\'image. Les cours sont la plupart du temps bien ficelés avec plusieurs intervenants et de nombreux thèmes abordés (rendu, modélisation, traitement d\'image 3D, applications). Le module de 3D demande pas mal d\'investissement, puisqu\'il se valide en 3 étapes : présentation d\'article scientifique, projet et partiel. L\'analyse d\'article et le projet se fait en groupe. Le projet consiste (en général) à réaliser un moteur de rendu 3D en codant de nombreux algorithmes vus en cours, pour ajouter par exemple des ombres et des couleurs à une scène 3D. C\'est très gratifiant et amusant à la fois, de l\'algorithmique pure. \r\nC\'est une UE assez prenante surtout dans les 3 dernières semaines de la période, mais le résultat en vaut la peine !',6,'2013-05-10 17:43:42'),(23,'SI241 est une UE où l\'on en apprend sur beaucoup de domaines mais pas forcément en profondeur. Bref, c\'est le but d\'une introduction. Ce qui en fait une très bonne UE de base pour ceux qui, comme moi, ne prenne pas pour parcours principal le parcours image : cela permet de se faire une idée générale de ce qu\'est le traitement d\'images, les différents aspects et applications dans lesquels on peut travailler... Le projet en binôme est très gratifiant puisqu\'on met en application les algorithmes et modèles vus en cours, ce qui permet de bien s\'en imprégner et de véritablement comprendre comment manipuler les images. En plus, les images, c\'est joli ! Sans rire, faire des TP / projets qui consistent à obtenir un rendu visuel, c\'est motivant. Cf le module de 3D, INFSI350. Le poly est très complet, parfois même plus qu\'il ne le faut. Le partiel se fait avec documents et n\'est pas trop dur si l\'on est allé en cours (ou qu\'on est très fort) et qu\'on a bien fait quelques TP (notamment le TP sur les champs de markov).',6,'2013-05-10 17:45:07'),(24,'Ce cours permet d\'aborder le C++... par la manière douce ! Les cours ne sont pas très utiles sauf si vous voulez poser des questions, le poly suffit pour la première partie où l\'on apprend des généralités sur les paradigmes de programmation, le C++ et le Java. 5 points du partiels sont consacrés à cette partie, à ne pas négliger tout de même (lisez bien cette partie du poly !), ce sont souvent des points cadeau. Pour le reste, les TP forment la partie vraiment intéressante du cours : à faire avec le poly à côté ou avec le site du zéro. Le poly explique assez bien les différences entre C++ et java, ou permet au moins de commencer quelques recherches pour en savoir plus sur les caractéristiques du langage. Globalement, c\'est une UE pas trop dure, pas si preneuse en terme de temps (la majeure partie TP se fait assez bien pendant les heures consacrées), manquerait peut-être un mini-projet pour mettre véritablement en application la connaissance du langage ? Pas sûr, je pense qu\'arriver à terminer les TP montre déjà qu\'on s\'est bien investi !',6,'2013-05-10 17:46:54'),(25,'Ce cours n\'était pas exactement ce à quoi je m\'attendais. Je m\'attendais à un cours sur la théorie des interfaces entre l\'homme et la machine, à savoir quels sont les points importants à respecter dans l\'ergonomie d\'une application, quelles sont les réactions d\'un humain face à telle ou telle situation, comment les deviner, comment y faire face... mais ce n\'était qu\'en fait une infime partie du cours. En effet, le cours se décompose en trois parties : une partie cours de Qt, langage de programmation pour créer des interfaces, à la bibliothèque aussi longue qu\'ennuyante, une partie sur les concepts utilisateurs, très bon cours, très intéressant, ce à quoi je m\'attendais (avec, qui plus est, d\'excellents profs, un cours très interactif, sans arrêt remis en question par les enseignants qui appliquent dans leur cours même ce qu\'ils enseignent), et une \"partie\" très confuse ou on range tout le reste (ergonomie, 3D,...). La validation se fait via plusieurs critères : un projet Qt, une des pires choses que j\'ai eu à faire en 2A (heureusement que l\'on peut choisir son binome avec qui travailler, ce qui m\'a sauvé de l\'ennui et du désespoir), un projet sur les concepts utilisateurs, plutôt amusant (il a fallu faire des tests utilisateurs sur notre application avec des petites maquettes papier faites à la main), le partiel (pas forcément trivial si on ne va pas en cours) et apparemment une partie sur les TP. En bref, une UE que je ne conseillerais pas, car malgré une partie du cours intéressante et utile, cela ne compense pas le temps passé sur les docs de Qt pour arriver à afficher un bouton, qui constitue la majeure partie du cours...',6,'2013-05-10 17:48:09'),(26,'Télécom c\'est d\'la gomme :-D',6,'2013-05-10 17:48:59');
/*!40000 ALTER TABLE `app_usertext` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_group`
--

DROP TABLE IF EXISTS `auth_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_group`
--

LOCK TABLES `auth_group` WRITE;
/*!40000 ALTER TABLE `auth_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_group_permissions`
--

DROP TABLE IF EXISTS `auth_group_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_group_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `group_id` (`group_id`,`permission_id`),
  KEY `auth_group_permissions_bda51c3c` (`group_id`),
  KEY `auth_group_permissions_1e014c8f` (`permission_id`),
  CONSTRAINT `group_id_refs_id_3cea63fe` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`),
  CONSTRAINT `permission_id_refs_id_a7792de1` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_group_permissions`
--

LOCK TABLES `auth_group_permissions` WRITE;
/*!40000 ALTER TABLE `auth_group_permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_group_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_permission`
--

DROP TABLE IF EXISTS `auth_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `content_type_id` int(11) NOT NULL,
  `codename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `content_type_id` (`content_type_id`,`codename`),
  KEY `auth_permission_e4470c6e` (`content_type_id`),
  CONSTRAINT `content_type_id_refs_id_728de91f` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_permission`
--

LOCK TABLES `auth_permission` WRITE;
/*!40000 ALTER TABLE `auth_permission` DISABLE KEYS */;
INSERT INTO `auth_permission` VALUES (1,'Can add permission',1,'add_permission'),(2,'Can change permission',1,'change_permission'),(3,'Can delete permission',1,'delete_permission'),(4,'Can add group',2,'add_group'),(5,'Can change group',2,'change_group'),(6,'Can delete group',2,'delete_group'),(7,'Can add user',3,'add_user'),(8,'Can change user',3,'change_user'),(9,'Can delete user',3,'delete_user'),(10,'Can add content type',4,'add_contenttype'),(11,'Can change content type',4,'change_contenttype'),(12,'Can delete content type',4,'delete_contenttype'),(13,'Can add session',5,'add_session'),(14,'Can change session',5,'change_session'),(15,'Can delete session',5,'delete_session'),(16,'Can add site',6,'add_site'),(17,'Can change site',6,'change_site'),(18,'Can delete site',6,'delete_site'),(19,'Can add log entry',7,'add_logentry'),(20,'Can change log entry',7,'change_logentry'),(21,'Can delete log entry',7,'delete_logentry'),(25,'Can add school',9,'add_school'),(26,'Can change school',9,'change_school'),(27,'Can delete school',9,'delete_school'),(28,'Can add student',10,'add_student'),(29,'Can change student',10,'change_student'),(30,'Can delete student',10,'delete_student'),(31,'Can add course',11,'add_course'),(32,'Can change course',11,'change_course'),(33,'Can delete course',11,'delete_course'),(34,'Can add teacher',12,'add_teacher'),(35,'Can change teacher',12,'change_teacher'),(36,'Can delete teacher',12,'delete_teacher'),(37,'Can add comment',13,'add_comment'),(38,'Can change comment',13,'change_comment'),(39,'Can delete comment',13,'delete_comment'),(40,'Can add question genius',14,'add_questiongenius'),(41,'Can change question genius',14,'change_questiongenius'),(42,'Can delete question genius',14,'delete_questiongenius'),(43,'Can add study',15,'add_study'),(44,'Can change study',15,'change_study'),(45,'Can delete study',15,'delete_study'),(46,'Can add migration history',16,'add_migrationhistory'),(47,'Can change migration history',16,'change_migrationhistory'),(48,'Can delete migration history',16,'delete_migrationhistory'),(49,'Can add news',17,'add_news'),(50,'Can change news',17,'change_news'),(51,'Can delete news',17,'delete_news'),(52,'Can add user text',18,'add_usertext'),(53,'Can change user text',18,'change_usertext'),(54,'Can delete user text',18,'delete_usertext'),(55,'Can add school desc',19,'add_schooldesc'),(56,'Can change school desc',19,'change_schooldesc'),(57,'Can delete school desc',19,'delete_schooldesc'),(58,'Can add course comment',20,'add_coursecomment'),(59,'Can change course comment',20,'change_coursecomment'),(60,'Can delete course comment',20,'delete_coursecomment');
/*!40000 ALTER TABLE `auth_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user`
--

DROP TABLE IF EXISTS `auth_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `email` varchar(75) NOT NULL,
  `password` varchar(128) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `last_login` datetime NOT NULL,
  `date_joined` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user`
--

LOCK TABLES `auth_user` WRITE;
/*!40000 ALTER TABLE `auth_user` DISABLE KEYS */;
INSERT INTO `auth_user` VALUES (2,'Tibery','Adrien','Tibere-inglesse','adrien.tibereinglesse@gmail.com','pbkdf2_sha256$10000$zwZk5TMjukdb$5wwEaqEbyq7vYjSqIOms9A4D34o3xx6pTs8+vdo/Nn0=',0,1,0,'2013-05-02 14:37:08','2013-04-02 11:53:23'),(3,'Dem','Alexandre','Hervé','alexandre.herve9@gmail.com','pbkdf2_sha256$10000$gZwzWFhcZBfC$HsytReq5gDseBnZao9OOdZcuIilgmOR2KaQf8U/3kFw=',0,1,0,'2013-05-08 16:54:32','2013-04-02 12:08:28'),(4,'Mamu','Muller','Marie','mariemyu@gmail.com','pbkdf2_sha256$10000$XCrKveFIpcHT$tZ10s93mDkotlqjod61MWC9GaUrzby9BN5vICWlYcg8=',0,1,0,'2013-04-02 13:11:12','2013-04-02 13:11:09'),(5,'ulysse','Ulysse','Klatzmann','ulysseklatzmann@gmail.com','pbkdf2_sha256$10000$6HnfOqyg88GF$TfHXz7qckjN3gjcY13uTSWNnmsVRuRn4LYMHYzTKNjw=',0,1,0,'2013-04-30 20:58:51','2013-04-02 17:22:50'),(6,'tatiana','tatiana','peraldi','peraldi.tatiana@gmail.com','pbkdf2_sha256$10000$IsqGiZY8HYMq$+79Se6/2F1r2zCChEsWnqURk2FiUVWcVejrXQHVjSg0=',0,1,0,'2013-05-14 17:25:47','2013-04-03 12:37:03'),(7,'KiwiPowaa','Augustin','Tibere','gugus000@hotmail.com','pbkdf2_sha256$10000$YyIeHpCrDC7G$X7ANV0wehGshPnBVJqTJUPgtmtQEI7T6LrFAibZRnKc=',0,1,0,'2013-05-10 12:15:37','2013-04-04 01:22:51'),(8,'Émile','Rodrigue','Émile','alexandre@antoineherve.com','pbkdf2_sha256$10000$eFwaue08wTgq$Ck9nMki+6aF7pZWpDol6mDaBEBvNuPVXeQmOrBCmARY=',0,1,0,'2013-04-15 12:03:42','2013-04-15 12:03:40');
/*!40000 ALTER TABLE `auth_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user_groups`
--

DROP TABLE IF EXISTS `auth_user_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user_groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`group_id`),
  KEY `auth_user_groups_fbfc09f1` (`user_id`),
  KEY `auth_user_groups_bda51c3c` (`group_id`),
  CONSTRAINT `user_id_refs_id_831107f1` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`),
  CONSTRAINT `group_id_refs_id_f0ee9890` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user_groups`
--

LOCK TABLES `auth_user_groups` WRITE;
/*!40000 ALTER TABLE `auth_user_groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_user_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user_user_permissions`
--

DROP TABLE IF EXISTS `auth_user_user_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user_user_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`permission_id`),
  KEY `auth_user_user_permissions_fbfc09f1` (`user_id`),
  KEY `auth_user_user_permissions_1e014c8f` (`permission_id`),
  CONSTRAINT `user_id_refs_id_f2045483` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`),
  CONSTRAINT `permission_id_refs_id_67e79cb` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user_user_permissions`
--

LOCK TABLES `auth_user_user_permissions` WRITE;
/*!40000 ALTER TABLE `auth_user_user_permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_user_user_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_admin_log`
--

DROP TABLE IF EXISTS `django_admin_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `django_admin_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action_time` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  `content_type_id` int(11) DEFAULT NULL,
  `object_id` longtext,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint(5) unsigned NOT NULL,
  `change_message` longtext NOT NULL,
  PRIMARY KEY (`id`),
  KEY `django_admin_log_fbfc09f1` (`user_id`),
  KEY `django_admin_log_e4470c6e` (`content_type_id`),
  CONSTRAINT `user_id_refs_id_c8665aa` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`),
  CONSTRAINT `content_type_id_refs_id_288599e6` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_admin_log`
--

LOCK TABLES `django_admin_log` WRITE;
/*!40000 ALTER TABLE `django_admin_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `django_admin_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_content_type`
--

DROP TABLE IF EXISTS `django_content_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `django_content_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `app_label` (`app_label`,`model`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_content_type`
--

LOCK TABLES `django_content_type` WRITE;
/*!40000 ALTER TABLE `django_content_type` DISABLE KEYS */;
INSERT INTO `django_content_type` VALUES (1,'permission','auth','permission'),(2,'group','auth','group'),(3,'user','auth','user'),(4,'content type','contenttypes','contenttype'),(5,'session','sessions','session'),(6,'site','sites','site'),(7,'log entry','admin','logentry'),(9,'school','app','school'),(10,'student','app','student'),(11,'course','app','course'),(12,'teacher','app','teacher'),(13,'comment','app','comment'),(14,'question genius','app','questiongenius'),(15,'study','app','study'),(16,'migration history','south','migrationhistory'),(17,'news','app','news'),(18,'user text','app','usertext'),(19,'school desc','app','schooldesc'),(20,'course comment','app','coursecomment');
/*!40000 ALTER TABLE `django_content_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_session`
--

DROP TABLE IF EXISTS `django_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime NOT NULL,
  PRIMARY KEY (`session_key`),
  KEY `django_session_c25c2c28` (`expire_date`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_session`
--

LOCK TABLES `django_session` WRITE;
/*!40000 ALTER TABLE `django_session` DISABLE KEYS */;
INSERT INTO `django_session` VALUES ('05d3d31b62aef4e01cf5320e346a390a','YjEwNjdmNmRmYzExYTM4NTM4NzEyMjYzMjY1NDg2YzBiMDRlNjI3NzqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQJ1Lg==\n','2013-05-16 14:37:08'),('0adcbd26ac73d681a643c99c34c6a460','YzgwMGE3YmQwMjFkYjA0MjI0OTAzOTJjYjVhOGVkMWQyYWM1MTVmZTqAAn1xAS4=\n','2013-05-27 15:18:31'),('2319ba65168684a2f9d2a6f4d44fe4d5','YjEwNjdmNmRmYzExYTM4NTM4NzEyMjYzMjY1NDg2YzBiMDRlNjI3NzqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQJ1Lg==\n','2013-05-14 11:01:45'),('231a50c5f593a6a29df0b5332b84b555','YzcxMThkYTQ4NjQ2YzU3Y2FkMzE1Yjk3MGFlNjY3YTIwYzdkNDRlMjqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQdVD19zZXNzaW9uX2V4cGlyeXEFSwB1Lg==\n','2013-05-24 12:15:37'),('279f2de41b1d7df669c575d523227547','MTFjNDU5ZDdlYWExYjg2M2Q0NzBhZGEzMTllM2JiZDcyYjY1OGFiZjqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQR1Lg==\n','2013-04-16 13:11:12'),('31e1218f7f69fcfb8454bf312e31c5c2','NzA1ZDBiY2I4ZjdhMTViZjk5YTFhMTRhYzVmYTlkMjdiMmIwYjNjYjqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQNVD19zZXNzaW9uX2V4cGlyeXEFSwB1Lg==\n','2013-05-16 07:55:41'),('3864dc5149ccf3488a9143db5f489536','ZTM3NzNkYTIwYTk2YWRlOWMzYWRkOTNjYzdlZDk3NzA4YjlkYmY0NzqAAn1xAVUPX3Nlc3Npb25f\nZXhwaXJ5cQJLAHMu\n','2013-05-14 15:05:58'),('3f1a4a302235ccfef04c6e85de339fc0','NzA1ZDBiY2I4ZjdhMTViZjk5YTFhMTRhYzVmYTlkMjdiMmIwYjNjYjqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQNVD19zZXNzaW9uX2V4cGlyeXEFSwB1Lg==\n','2013-05-14 10:14:09'),('49b232d552d752a4a7305764a65bf0f4','YWNmZTA2MWQ0NTExYjBmZmNjN2MyZTgwY2I1YTk1ZDc5Mjg2MWIxZDqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQJVD19zZXNzaW9uX2V4cGlyeXEFSwB1Lg==\n','2013-05-14 19:39:15'),('49ee2527b496f97124b9b16a0fa7ee10','ZDI4NTRjNmZjMDU1MzM0NzA2NTE1YjdmNzhmZmRmYTUwNjgzNzI4MjqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQZVD19zZXNzaW9uX2V4cGlyeXEFSwB1Lg==\n','2013-05-28 17:25:47'),('5401d056033b563d7845cb3642b819a8','NzA1ZDBiY2I4ZjdhMTViZjk5YTFhMTRhYzVmYTlkMjdiMmIwYjNjYjqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQNVD19zZXNzaW9uX2V4cGlyeXEFSwB1Lg==\n','2013-05-06 08:34:41'),('5b28057d51591a43e657759547a1ca35','ZDlkODUwNWI5Y2I5MDFhZjNhZTI1ODdjNmMyM2Y2Mjc1NWYzMTlhOTqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQZVD19zZXNzaW9uX2V4cGlyeUsAdS4=\n','2013-05-06 12:07:56'),('6143bea9cdcc36060f0731cdc6e8994c','NTM2ODNiZTBjMTA5MjkwNmQ1OGE2ZWU4MTZkZWM3Mzc2M2U1YWFhMTqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQJVD19zZXNzaW9uX2V4cGlyeUsAdS4=\n','2013-05-07 17:18:52'),('71080d9198c48f8b108f6166f0698cfe','MjIzMmM4MTc1ZGI1ZWZkZWUxMTExZTdjNGRkMjY2NjFiMThkY2FjMjqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQd1Lg==\n','2013-04-18 01:22:54'),('73d53c9ba8b0b1f8e0f3a22b34ddc8cd','NzA1ZDBiY2I4ZjdhMTViZjk5YTFhMTRhYzVmYTlkMjdiMmIwYjNjYjqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQNVD19zZXNzaW9uX2V4cGlyeXEFSwB1Lg==\n','2013-05-05 15:59:20'),('8745b22201f9430b7026c9c18e05fbdf','MjIzMmM4MTc1ZGI1ZWZkZWUxMTExZTdjNGRkMjY2NjFiMThkY2FjMjqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQd1Lg==\n','2013-04-18 01:23:38'),('8f5eb902f1f5be50b5b152e249cdcd54','YjYzNjIyNWRmNWE1ZDVmMDA2N2M3OGZiNzE0MGE0MTYyNWRkNWM1NjqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQV1Lg==\n','2013-04-16 17:22:54'),('9832a0f687074dad6fd4feac15e11d24','YzgwMGE3YmQwMjFkYjA0MjI0OTAzOTJjYjVhOGVkMWQyYWM1MTVmZTqAAn1xAS4=\n','2013-04-27 10:08:07'),('9a8b2a6622f65ee51be7da10038401da','NzA1ZDBiY2I4ZjdhMTViZjk5YTFhMTRhYzVmYTlkMjdiMmIwYjNjYjqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQNVD19zZXNzaW9uX2V4cGlyeXEFSwB1Lg==\n','2013-05-07 10:35:36'),('9b8d44aa0f1129b28f01402d1ca43b87','NTU4NmZlMWNhOTg2Y2VjYWEyMzM5ZmI5MDY5ZjQ4MWYxYjYzMGI3MDqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQVVD19zZXNzaW9uX2V4cGlyeXEFSwB1Lg==\n','2013-05-14 20:58:51'),('acb51e1ec56b62bf896e7ba0586a2819','NTU4NmZlMWNhOTg2Y2VjYWEyMzM5ZmI5MDY5ZjQ4MWYxYjYzMGI3MDqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQVVD19zZXNzaW9uX2V4cGlyeXEFSwB1Lg==\n','2013-05-14 15:06:46'),('afd7b8c02eaa4a7e935ee5518ad0a545','NzA1ZDBiY2I4ZjdhMTViZjk5YTFhMTRhYzVmYTlkMjdiMmIwYjNjYjqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQNVD19zZXNzaW9uX2V4cGlyeXEFSwB1Lg==\n','2013-05-17 15:59:29'),('b05b2a65ea21479fb13d4df0033bdd21','YzcxMThkYTQ4NjQ2YzU3Y2FkMzE1Yjk3MGFlNjY3YTIwYzdkNDRlMjqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQdVD19zZXNzaW9uX2V4cGlyeXEFSwB1Lg==\n','2013-05-23 23:24:03'),('bebdfc12b598261eab95bf66624f96b1','NGJlNzkzZjVhZjc1M2NiYTYwZDFjMzYyZDk1OGMwMzhkOWM5NzdlODqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQN1Lg==\n','2013-04-27 08:42:25'),('bf058a040196c837dcfd0f16c23011a5','NzA1ZDBiY2I4ZjdhMTViZjk5YTFhMTRhYzVmYTlkMjdiMmIwYjNjYjqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQNVD19zZXNzaW9uX2V4cGlyeXEFSwB1Lg==\n','2013-05-08 15:59:23'),('c0c63cd2dcd459a2579f1b623ea3a942','NzA1ZDBiY2I4ZjdhMTViZjk5YTFhMTRhYzVmYTlkMjdiMmIwYjNjYjqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQNVD19zZXNzaW9uX2V4cGlyeXEFSwB1Lg==\n','2013-05-17 11:51:48'),('da74506df66964358768caad8fb45a99','YjYzNjIyNWRmNWE1ZDVmMDA2N2M3OGZiNzE0MGE0MTYyNWRkNWM1NjqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQV1Lg==\n','2013-04-29 12:05:11'),('eed2b15244dedc38ec0b50f21a1763cf','YzcxMThkYTQ4NjQ2YzU3Y2FkMzE1Yjk3MGFlNjY3YTIwYzdkNDRlMjqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQdVD19zZXNzaW9uX2V4cGlyeXEFSwB1Lg==\n','2013-05-14 20:09:44'),('ef638589e0c3fd321c26462316a19307','MzExYzExOGUwNDBmNjczNTdjYWE0M2Q4NjEyZmVjOGJlNjc2MjlkZTqAAn1xAVUKdGVzdGNvb2tp\nZXECVQZ3b3JrZWRxA3Mu\n','2013-05-13 14:22:04'),('f0e699ca8910504031f1c217fb3c7480','YjEwNjdmNmRmYzExYTM4NTM4NzEyMjYzMjY1NDg2YzBiMDRlNjI3NzqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQJ1Lg==\n','2013-05-03 22:50:41'),('f9fa25146304de6b3c8e4d69f76b84a7','N2I4MGIwYmY0YjUwMDg2YzhiZWI2YjRiM2U4MWRhZWU3NGE2NDVhNjqAAn1xAShVEl9hdXRoX3Vz\nZXJfYmFja2VuZHECVSlkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZHED\nVQ1fYXV0aF91c2VyX2lkcQSKAQZ1Lg==\n','2013-04-17 12:37:08');
/*!40000 ALTER TABLE `django_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_site`
--

DROP TABLE IF EXISTS `django_site`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `django_site` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `domain` varchar(100) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_site`
--

LOCK TABLES `django_site` WRITE;
/*!40000 ALTER TABLE `django_site` DISABLE KEYS */;
INSERT INTO `django_site` VALUES (1,'example.com','example.com'),(2,'www.shapter.com','Shapter');
/*!40000 ALTER TABLE `django_site` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `south_migrationhistory`
--

DROP TABLE IF EXISTS `south_migrationhistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `south_migrationhistory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(255) NOT NULL,
  `migration` varchar(255) NOT NULL,
  `applied` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `south_migrationhistory`
--

LOCK TABLES `south_migrationhistory` WRITE;
/*!40000 ALTER TABLE `south_migrationhistory` DISABLE KEYS */;
INSERT INTO `south_migrationhistory` VALUES (4,'app','0001_initial','2013-04-30 09:50:33'),(5,'app','0002_auto__add_field_comment_test','2013-04-30 12:10:02'),(6,'app','0003_auto__del_field_comment_test','2013-04-30 12:10:16'),(7,'app','0004_auto__add_schooldesc__add_usertext__add_field_course_code','2013-05-02 09:59:54'),(8,'app','0005_auto__add_field_comment_temp_id_comment','2013-05-03 09:51:06'),(9,'app','0009_move_comment_to_usertext','2013-05-03 09:54:10'),(10,'app','0010_auto__del_field_comment_comment__del_field_comment_student__del_field_','2013-05-03 09:59:19'),(11,'app','0011_auto__add_coursecomment','2013-05-03 10:25:41'),(12,'app','0015_comment_migr','2013-05-03 15:20:34'),(13,'app','0016_auto__del_comment__del_field_coursecomment_temp_id_comment','2013-05-03 15:22:59');
/*!40000 ALTER TABLE `south_migrationhistory` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-05-15 15:55:23
