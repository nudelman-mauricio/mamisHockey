-- MySQL dump 10.13  Distrib 5.6.21, for Win32 (x86)
--
-- Host: localhost    Database: mamishockeydb
-- ------------------------------------------------------
-- Server version	5.6.21-log

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
-- Table structure for table `cancha`
--

DROP TABLE IF EXISTS `cancha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cancha` (
  `IDCANCHA` bigint(20) NOT NULL,
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `NOMBRE` varchar(255) DEFAULT NULL,
  `UNTIPOCANCHA_IDTIPOCANCHA` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`IDCANCHA`),
  KEY `FK_CANCHA_UNTIPOCANCHA_IDTIPOCANCHA` (`UNTIPOCANCHA_IDTIPOCANCHA`),
  CONSTRAINT `FK_CANCHA_UNTIPOCANCHA_IDTIPOCANCHA` FOREIGN KEY (`UNTIPOCANCHA_IDTIPOCANCHA`) REFERENCES `tipocancha` (`IDTIPOCANCHA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cancha`
--

LOCK TABLES `cancha` WRITE;
/*!40000 ALTER TABLE `cancha` DISABLE KEYS */;
INSERT INTO `cancha` VALUES (1,0,'NombreCancha',2),(2,0,'NombreCancha',2),(3,0,'NombreCancha',3),(4,0,'NombreCancha',1),(5,0,'NombreCancha',2),(6,0,'NombreCancha',3),(7,0,'NombreCancha',1),(8,0,'NombreCancha',3),(9,0,'NombreCancha',3),(10,0,'NombreCancha',2),(11,0,'NombreCancha',1),(12,0,'NombreCancha',3),(13,0,'NombreCancha',3),(14,0,'NombreCancha',1),(15,0,'NombreCancha',2),(16,0,'NombreCancha',3),(17,0,'NombreCancha',1),(18,0,'NombreCancha',2),(19,0,'NombreCancha',3),(20,0,'NombreCancha',3),(21,0,'NombreCancha',2),(22,0,'NombreCancha',3),(23,0,'NombreCancha',3),(24,0,'NombreCancha',3),(25,0,'NombreCancha',1),(26,0,'NombreCancha',1),(27,0,'NombreCancha',1),(28,0,'NombreCancha',2),(29,0,'NombreCancha',2),(30,0,'NombreCancha',2);
/*!40000 ALTER TABLE `cancha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `IDCATEGORIA` bigint(20) NOT NULL,
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `CANTIDADMAXIMA` int(11) DEFAULT NULL,
  `CANTIDADMINIMA` int(11) DEFAULT NULL,
  `EDADPARAMETRO` int(11) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDCATEGORIA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,0,10,0,40,'Menores'),(2,0,5,0,45,'Intermedio'),(3,0,0,0,50,'Mayores');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club`
--

DROP TABLE IF EXISTS `club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `club` (
  `IDCLUB` bigint(20) NOT NULL,
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `LOGO` longblob,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `NOMBREPRESIDENTE` varchar(255) DEFAULT NULL,
  `UNALOCALIDAD_IDLOCALIDAD` bigint(20) DEFAULT NULL,
  `UNARESPONSABLESEDE_DNI` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`IDCLUB`),
  KEY `FK_CLUB_UNALOCALIDAD_IDLOCALIDAD` (`UNALOCALIDAD_IDLOCALIDAD`),
  KEY `FK_CLUB_UNARESPONSABLESEDE_DNI` (`UNARESPONSABLESEDE_DNI`),
  CONSTRAINT `FK_CLUB_UNALOCALIDAD_IDLOCALIDAD` FOREIGN KEY (`UNALOCALIDAD_IDLOCALIDAD`) REFERENCES `localidad` (`IDLOCALIDAD`),
  CONSTRAINT `FK_CLUB_UNARESPONSABLESEDE_DNI` FOREIGN KEY (`UNARESPONSABLESEDE_DNI`) REFERENCES `socia` (`DNI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club`
--

LOCK TABLES `club` WRITE;
/*!40000 ALTER TABLE `club` DISABLE KEYS */;
INSERT INTO `club` VALUES (1,0,'','Camionero','DeiCastelli',1,NULL),(2,0,'','Capri','Gregas',1,NULL),(3,0,'','Centro Cazadores','Okulovich',3,NULL),(4,0,'','Crucero del Norte','Kruse',1,NULL),(5,0,'','Educación','Pescador',1,NULL),(6,0,'','Guarani','Franco',1,NULL),(7,0,'','La Picada','Legislador',1,NULL),(8,0,'','Camara legislativo','Franco',1,NULL),(9,0,'','Lux y Fuerza','Franco',1,NULL),(10,0,'','Mitre','Franco',1,NULL),(11,0,'','Pita Pyta Pora','Franco',1,NULL),(12,0,'','Tacuru','Franco',1,NULL),(13,0,'','Ucraniano','Franco',1,NULL),(14,0,'','Union','Franco',1,NULL),(15,0,'','Yta','Franco',1,NULL);
/*!40000 ALTER TABLE `club` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club_cancha`
--

DROP TABLE IF EXISTS `club_cancha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `club_cancha` (
  `Club_IDCLUB` bigint(20) NOT NULL,
  `canchas_IDCANCHA` bigint(20) NOT NULL,
  PRIMARY KEY (`Club_IDCLUB`,`canchas_IDCANCHA`),
  KEY `FK_CLUB_CANCHA_canchas_IDCANCHA` (`canchas_IDCANCHA`),
  CONSTRAINT `FK_CLUB_CANCHA_Club_IDCLUB` FOREIGN KEY (`Club_IDCLUB`) REFERENCES `club` (`IDCLUB`),
  CONSTRAINT `FK_CLUB_CANCHA_canchas_IDCANCHA` FOREIGN KEY (`canchas_IDCANCHA`) REFERENCES `cancha` (`IDCANCHA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club_cancha`
--

LOCK TABLES `club_cancha` WRITE;
/*!40000 ALTER TABLE `club_cancha` DISABLE KEYS */;
INSERT INTO `club_cancha` VALUES (15,1),(5,2),(13,3),(14,4),(2,5),(1,6),(2,7),(8,8),(3,9),(9,10),(8,11),(8,12),(1,13),(11,14),(13,15),(3,16),(3,17),(10,18),(9,19),(8,20),(8,21),(6,22),(11,23),(8,24),(14,25),(1,26),(10,27),(1,28),(13,29),(3,30);
/*!40000 ALTER TABLE `club_cancha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club_equipo`
--

DROP TABLE IF EXISTS `club_equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `club_equipo` (
  `Club_IDCLUB` bigint(20) NOT NULL,
  `equipos_IDEQUIPO` bigint(20) NOT NULL,
  PRIMARY KEY (`Club_IDCLUB`,`equipos_IDEQUIPO`),
  KEY `FK_CLUB_EQUIPO_equipos_IDEQUIPO` (`equipos_IDEQUIPO`),
  CONSTRAINT `FK_CLUB_EQUIPO_Club_IDCLUB` FOREIGN KEY (`Club_IDCLUB`) REFERENCES `club` (`IDCLUB`),
  CONSTRAINT `FK_CLUB_EQUIPO_equipos_IDEQUIPO` FOREIGN KEY (`equipos_IDEQUIPO`) REFERENCES `equipo` (`IDEQUIPO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club_equipo`
--

LOCK TABLES `club_equipo` WRITE;
/*!40000 ALTER TABLE `club_equipo` DISABLE KEYS */;
INSERT INTO `club_equipo` VALUES (1,1),(2,2),(2,3),(2,4),(3,5),(3,6),(3,7),(4,8),(4,9),(5,10),(6,11),(7,12),(8,13),(8,14),(8,15),(8,16),(8,17),(9,18),(10,19),(11,20),(12,21),(13,22),(14,23),(14,24),(15,25);
/*!40000 ALTER TABLE `club_equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conceptodeportivo`
--

DROP TABLE IF EXISTS `conceptodeportivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conceptodeportivo` (
  `IDCONCEPTODEPORTIVO` bigint(20) NOT NULL,
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `CONCEPTO` varchar(255) DEFAULT NULL,
  `MONTO` double DEFAULT NULL,
  `UNTIPOCANCHA_IDTIPOCANCHA` bigint(20) DEFAULT NULL,
  `UNTIPOESTADO_IDTIPOESTADO` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`IDCONCEPTODEPORTIVO`),
  KEY `FK_CONCEPTODEPORTIVO_UNTIPOESTADO_IDTIPOESTADO` (`UNTIPOESTADO_IDTIPOESTADO`),
  KEY `FK_CONCEPTODEPORTIVO_UNTIPOCANCHA_IDTIPOCANCHA` (`UNTIPOCANCHA_IDTIPOCANCHA`),
  CONSTRAINT `FK_CONCEPTODEPORTIVO_UNTIPOCANCHA_IDTIPOCANCHA` FOREIGN KEY (`UNTIPOCANCHA_IDTIPOCANCHA`) REFERENCES `tipocancha` (`IDTIPOCANCHA`),
  CONSTRAINT `FK_CONCEPTODEPORTIVO_UNTIPOESTADO_IDTIPOESTADO` FOREIGN KEY (`UNTIPOESTADO_IDTIPOESTADO`) REFERENCES `tipoestado` (`IDTIPOESTADO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conceptodeportivo`
--

LOCK TABLES `conceptodeportivo` WRITE;
/*!40000 ALTER TABLE `conceptodeportivo` DISABLE KEYS */;
INSERT INTO `conceptodeportivo` VALUES (63,0,'Cuota Socia',70,NULL,1),(64,0,'Cuota Jugadora',130,NULL,2),(65,0,'Cuota Licencia',70,NULL,3),(66,0,'Cuota Baja por Mora',20,NULL,5),(67,0,'Seguro Técnicos',70,NULL,NULL),(68,0,'Cancha',0,NULL,NULL),(69,0,'Fichaje',120,NULL,NULL),(70,0,'Re-Fichaje',75,NULL,NULL),(71,0,'Inscripción',135,NULL,NULL),(72,0,'Re-Inscripción',200,NULL,NULL),(73,0,'Pase',200,NULL,NULL),(74,0,'Otro',0,NULL,NULL);
/*!40000 ALTER TABLE `conceptodeportivo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conceptodeportivo_mes`
--

DROP TABLE IF EXISTS `conceptodeportivo_mes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conceptodeportivo_mes` (
  `ConceptoDeportivo_IDCONCEPTODEPORTIVO` bigint(20) NOT NULL,
  `meses_NOMBRE` varchar(255) NOT NULL,
  PRIMARY KEY (`ConceptoDeportivo_IDCONCEPTODEPORTIVO`,`meses_NOMBRE`),
  KEY `FK_CONCEPTODEPORTIVO_MES_meses_NOMBRE` (`meses_NOMBRE`),
  CONSTRAINT `CNCPTDEPORTIVOMESCncptDeportivoIDCONCEPTODEPORTIVO` FOREIGN KEY (`ConceptoDeportivo_IDCONCEPTODEPORTIVO`) REFERENCES `conceptodeportivo` (`IDCONCEPTODEPORTIVO`),
  CONSTRAINT `FK_CONCEPTODEPORTIVO_MES_meses_NOMBRE` FOREIGN KEY (`meses_NOMBRE`) REFERENCES `mes` (`NOMBRE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conceptodeportivo_mes`
--

LOCK TABLES `conceptodeportivo_mes` WRITE;
/*!40000 ALTER TABLE `conceptodeportivo_mes` DISABLE KEYS */;
INSERT INTO `conceptodeportivo_mes` VALUES (63,'Abril'),(64,'Abril'),(65,'Abril'),(66,'Abril'),(63,'Agosto'),(64,'Agosto'),(65,'Agosto'),(66,'Agosto'),(63,'Diciembre'),(64,'Diciembre'),(65,'Diciembre'),(66,'Diciembre'),(63,'Julio'),(64,'Julio'),(65,'Julio'),(66,'Julio'),(63,'Junio'),(64,'Junio'),(65,'Junio'),(66,'Junio'),(63,'Marzo'),(64,'Marzo'),(65,'Marzo'),(66,'Marzo'),(63,'Mayo'),(64,'Mayo'),(65,'Mayo'),(66,'Mayo'),(63,'Noviembre'),(64,'Noviembre'),(65,'Noviembre'),(66,'Noviembre'),(63,'Octubre'),(64,'Octubre'),(65,'Octubre'),(66,'Octubre'),(63,'Septiembre'),(64,'Septiembre'),(65,'Septiembre'),(66,'Septiembre');
/*!40000 ALTER TABLE `conceptodeportivo_mes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conceptoegreso`
--

DROP TABLE IF EXISTS `conceptoegreso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conceptoegreso` (
  `IDCONCEPTOEGRESO` bigint(20) NOT NULL,
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `DETALLE` varchar(255) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDCONCEPTOEGRESO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conceptoegreso`
--

LOCK TABLES `conceptoegreso` WRITE;
/*!40000 ALTER TABLE `conceptoegreso` DISABLE KEYS */;
INSERT INTO `conceptoegreso` VALUES (75,0,'Corresponde a los pagos DE la asociación A los Clubes que posean canchas y sean utilizadas.','Cancha');
/*!40000 ALTER TABLE `conceptoegreso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conceptoingreso`
--

DROP TABLE IF EXISTS `conceptoingreso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conceptoingreso` (
  `IDCONCEPTOINGRESO` bigint(20) NOT NULL,
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `DETALLE` varchar(255) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDCONCEPTOINGRESO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conceptoingreso`
--

LOCK TABLES `conceptoingreso` WRITE;
/*!40000 ALTER TABLE `conceptoingreso` DISABLE KEYS */;
/*!40000 ALTER TABLE `conceptoingreso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuracion`
--

DROP TABLE IF EXISTS `configuracion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuracion` (
  `ID` bigint(20) NOT NULL,
  `CONCEPTO` varchar(255) DEFAULT NULL,
  `VALOR` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuracion`
--

LOCK TABLES `configuracion` WRITE;
/*!40000 ALTER TABLE `configuracion` DISABLE KEYS */;
INSERT INTO `configuracion` VALUES (76,'diaVencimientoEstandar','15');
/*!40000 ALTER TABLE `configuracion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuota`
--

DROP TABLE IF EXISTS `cuota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuota` (
  `IDCUOTA` bigint(20) NOT NULL,
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `FECHAVENCIMIENTO` date DEFAULT NULL,
  `MONTO` double DEFAULT NULL,
  `NUMERO` varchar(255) DEFAULT NULL,
  `UNPAGOCUOTA_IDPAGOCUOTA` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`IDCUOTA`),
  KEY `FK_CUOTA_UNPAGOCUOTA_IDPAGOCUOTA` (`UNPAGOCUOTA_IDPAGOCUOTA`),
  CONSTRAINT `FK_CUOTA_UNPAGOCUOTA_IDPAGOCUOTA` FOREIGN KEY (`UNPAGOCUOTA_IDPAGOCUOTA`) REFERENCES `pagocuota` (`IDPAGOCUOTA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuota`
--

LOCK TABLES `cuota` WRITE;
/*!40000 ALTER TABLE `cuota` DISABLE KEYS */;
INSERT INTO `cuota` VALUES (476,0,'2010-02-02',0,'1/1',477),(480,0,'2002-03-01',0,'1/1',481),(484,0,'2010-03-17',0,'1/1',485),(488,0,'2004-05-31',0,'1/1',489),(492,0,'2008-09-03',0,'1/1',493),(496,0,'2009-07-30',0,'1/1',497),(500,0,'2005-10-05',0,'1/1',501),(504,0,'2005-08-27',0,'1/1',505),(508,0,'1999-01-27',0,'1/1',509),(512,0,'2006-06-19',0,'1/1',513),(516,0,'1999-04-09',0,'1/1',517),(520,0,'2012-06-14',0,'1/1',521),(524,0,'2004-06-29',0,'1/1',525),(528,0,'2013-03-13',0,'1/1',529),(532,0,'2012-02-07',0,'1/1',533),(536,0,'2014-01-25',0,'1/1',537),(540,0,'2000-12-29',0,'1/1',541),(544,0,'2013-05-16',0,'1/1',545),(548,0,'2006-09-09',0,'1/1',549),(552,0,'2002-02-20',0,'1/1',553),(556,0,'2001-11-21',0,'1/1',557),(560,0,'2007-10-20',0,'1/1',561),(564,0,'2008-05-17',0,'1/1',565),(568,0,'2012-11-01',0,'1/1',569),(572,0,'1999-06-25',0,'1/1',573),(576,0,'2000-10-20',0,'1/1',577),(580,0,'2011-12-07',0,'1/1',581),(584,0,'2003-06-01',0,'1/1',585),(588,0,'2002-02-05',0,'1/1',589),(592,0,'2011-05-26',0,'1/1',593),(596,0,'2014-02-24',0,'1/1',597),(600,0,'2004-10-13',0,'1/1',601),(604,0,'2005-07-09',0,'1/1',605),(608,0,'2014-06-21',0,'1/1',609),(612,0,'2008-11-29',0,'1/1',613),(616,0,'2010-01-13',0,'1/1',617),(620,0,'2013-03-25',0,'1/1',621),(624,0,'2001-11-25',0,'1/1',625),(628,0,'1999-07-09',0,'1/1',629),(632,0,'2013-04-10',0,'1/1',633),(636,0,'2013-01-04',0,'1/1',637),(640,0,'2006-11-08',0,'1/1',641),(644,0,'2001-10-12',0,'1/1',645),(648,0,'2012-08-27',0,'1/1',649),(652,0,'2002-09-20',0,'1/1',653),(656,0,'2007-01-26',0,'1/1',657),(660,0,'2014-04-07',0,'1/1',661),(664,0,'2008-02-12',0,'1/1',665),(668,0,'2010-05-29',0,'1/1',669),(672,0,'1999-04-23',0,'1/1',673),(676,0,'2009-05-27',0,'1/1',677),(680,0,'2011-02-13',0,'1/1',681),(684,0,'2008-12-01',0,'1/1',685),(688,0,'2002-09-20',0,'1/1',689),(692,0,'2000-08-29',0,'1/1',693),(696,0,'2008-03-20',0,'1/1',697),(700,0,'2011-10-10',0,'1/1',701),(704,0,'2010-12-17',0,'1/1',705),(708,0,'2010-04-18',0,'1/1',709),(712,0,'1999-03-25',0,'1/1',713),(716,0,'2014-02-03',0,'1/1',717),(720,0,'2005-03-26',0,'1/1',721),(724,0,'2007-08-18',0,'1/1',725),(728,0,'2004-09-25',0,'1/1',729),(732,0,'2010-05-17',0,'1/1',733),(736,0,'2003-02-10',0,'1/1',737),(740,0,'2000-04-11',0,'1/1',741),(744,0,'2010-06-06',0,'1/1',745),(748,0,'2008-09-18',0,'1/1',749),(752,0,'2005-03-13',0,'1/1',753),(756,0,'2008-07-07',0,'1/1',757),(760,0,'2003-09-07',0,'1/1',761),(764,0,'2000-12-13',0,'1/1',765),(768,0,'2005-07-22',0,'1/1',769),(772,0,'2001-03-02',0,'1/1',773),(776,0,'2004-12-23',0,'1/1',777),(780,0,'2012-05-30',0,'1/1',781),(784,0,'2010-04-01',0,'1/1',785),(788,0,'2010-02-05',0,'1/1',789),(792,0,'2001-10-12',0,'1/1',793),(796,0,'2012-04-11',0,'1/1',797),(800,0,'2010-09-16',0,'1/1',801),(804,0,'2004-07-20',0,'1/1',805),(808,0,'2006-09-22',0,'1/1',809),(812,0,'2006-12-03',0,'1/1',813),(816,0,'2003-05-02',0,'1/1',817),(820,0,'2001-04-16',0,'1/1',821),(824,0,'2004-03-04',0,'1/1',825),(828,0,'2000-12-08',0,'1/1',829),(832,0,'1999-03-14',0,'1/1',833),(836,0,'2013-02-17',0,'1/1',837),(840,0,'2000-03-28',0,'1/1',841),(844,0,'2000-04-23',0,'1/1',845),(848,0,'2003-07-26',0,'1/1',849),(852,0,'2007-05-12',0,'1/1',853),(856,0,'2000-09-10',0,'1/1',857),(860,0,'2011-01-30',0,'1/1',861),(864,0,'2012-07-03',0,'1/1',865),(868,0,'1999-09-12',0,'1/1',869),(872,0,'2001-05-05',0,'1/1',873),(876,0,'2010-12-08',0,'1/1',877),(880,0,'2003-12-17',0,'1/1',881),(884,0,'2003-04-12',0,'1/1',885),(888,0,'2002-01-02',0,'1/1',889),(892,0,'2008-12-12',0,'1/1',893),(896,0,'2010-07-14',0,'1/1',897),(900,0,'2003-06-10',0,'1/1',901),(904,0,'2011-07-31',0,'1/1',905),(908,0,'2013-05-10',0,'1/1',909),(912,0,'2000-08-18',0,'1/1',913),(916,0,'2010-12-19',0,'1/1',917),(920,0,'2009-12-01',0,'1/1',921),(924,0,'2001-03-26',0,'1/1',925),(928,0,'2004-06-02',0,'1/1',929),(932,0,'2002-12-13',0,'1/1',933),(936,0,'2002-11-23',0,'1/1',937),(940,0,'2010-11-30',0,'1/1',941),(944,0,'2006-08-09',0,'1/1',945),(948,0,'2009-07-17',0,'1/1',949),(952,0,'2010-04-13',0,'1/1',953),(956,0,'2010-09-18',0,'1/1',957),(960,0,'2010-02-12',0,'1/1',961),(964,0,'2006-09-27',0,'1/1',965),(968,0,'2002-10-31',0,'1/1',969),(972,0,'2013-04-30',0,'1/1',973),(976,0,'2002-08-27',0,'1/1',977),(980,0,'2009-07-26',0,'1/1',981),(984,0,'2006-01-28',0,'1/1',985),(988,0,'2010-04-19',0,'1/1',989),(992,0,'2010-10-30',0,'1/1',993),(996,0,'2003-06-27',0,'1/1',997),(1000,0,'2009-11-21',0,'1/1',1001),(1004,0,'2009-11-12',0,'1/1',1005),(1008,0,'2002-10-24',0,'1/1',1009),(1012,0,'2002-12-14',0,'1/1',1013),(1016,0,'2006-04-13',0,'1/1',1017),(1020,0,'2012-12-20',0,'1/1',1021),(1024,0,'2005-08-21',0,'1/1',1025),(1028,0,'2005-08-12',0,'1/1',1029),(1032,0,'2009-11-07',0,'1/1',1033),(1036,0,'2002-04-14',0,'1/1',1037),(1040,0,'1999-01-26',0,'1/1',1041),(1044,0,'2005-11-23',0,'1/1',1045),(1048,0,'2001-05-17',0,'1/1',1049),(1052,0,'2006-12-03',0,'1/1',1053),(1056,0,'2007-09-25',0,'1/1',1057),(1060,0,'1999-06-18',0,'1/1',1061),(1064,0,'2013-08-23',0,'1/1',1065),(1068,0,'1999-10-14',0,'1/1',1069),(1072,0,'1999-07-19',0,'1/1',1073),(1076,0,'2012-05-19',0,'1/1',1077),(1080,0,'2000-04-26',0,'1/1',1081),(1084,0,'2008-05-06',0,'1/1',1085),(1088,0,'2006-12-21',0,'1/1',1089),(1092,0,'1999-07-28',0,'1/1',1093),(1096,0,'2014-02-15',0,'1/1',1097),(1100,0,'2003-10-31',0,'1/1',1101),(1104,0,'2006-07-08',0,'1/1',1105),(1108,0,'2005-01-05',0,'1/1',1109),(1112,0,'2011-01-22',0,'1/1',1113),(1116,0,'2009-03-09',0,'1/1',1117),(1120,0,'2001-09-25',0,'1/1',1121),(1124,0,'2009-02-12',0,'1/1',1125),(1128,0,'2004-07-20',0,'1/1',1129),(1132,0,'2002-05-21',0,'1/1',1133),(1136,0,'2007-07-18',0,'1/1',1137),(1140,0,'2013-03-13',0,'1/1',1141),(1144,0,'2006-12-23',0,'1/1',1145),(1148,0,'1999-03-03',0,'1/1',1149),(1152,0,'1999-05-14',0,'1/1',1153),(1156,0,'2006-12-06',0,'1/1',1157),(1160,0,'2011-06-12',0,'1/1',1161),(1164,0,'2004-02-04',0,'1/1',1165),(1168,0,'2012-06-15',0,'1/1',1169),(1172,0,'2005-08-27',0,'1/1',1173),(1176,0,'2003-05-02',0,'1/1',1177),(1180,0,'2007-02-13',0,'1/1',1181),(1184,0,'2004-09-13',0,'1/1',1185),(1188,0,'2003-04-26',0,'1/1',1189),(1192,0,'2007-12-18',0,'1/1',1193),(1196,0,'2012-01-15',0,'1/1',1197),(1200,0,'2004-12-05',0,'1/1',1201),(1204,0,'2007-05-28',0,'1/1',1205),(1208,0,'2003-10-13',0,'1/1',1209),(1212,0,'2012-09-05',0,'1/1',1213),(1216,0,'2001-02-21',0,'1/1',1217),(1220,0,'2002-09-11',0,'1/1',1221),(1224,0,'2004-08-12',0,'1/1',1225),(1228,0,'2001-08-27',0,'1/1',1229),(1232,0,'2011-06-09',0,'1/1',1233),(1236,0,'2004-03-23',0,'1/1',1237),(1240,0,'2001-04-08',0,'1/1',1241),(1244,0,'2013-01-17',0,'1/1',1245),(1248,0,'2004-10-10',0,'1/1',1249),(1252,0,'2010-09-06',0,'1/1',1253),(1256,0,'2013-12-07',0,'1/1',1257),(1260,0,'2001-01-17',0,'1/1',1261),(1264,0,'2007-03-01',0,'1/1',1265),(1268,0,'2003-03-11',0,'1/1',1269),(1272,0,'2013-04-02',0,'1/1',1273),(1276,0,'2005-10-20',0,'1/1',1277),(1280,0,'2009-12-22',0,'1/1',1281),(1284,0,'2001-07-20',0,'1/1',1285),(1288,0,'2002-05-03',0,'1/1',1289),(1292,0,'1999-09-02',0,'1/1',1293),(1296,0,'2009-07-22',0,'1/1',1297),(1300,0,'2012-06-19',0,'1/1',1301),(1304,0,'2004-05-07',0,'1/1',1305),(1308,0,'1999-11-07',0,'1/1',1309),(1312,0,'2007-09-26',0,'1/1',1313),(1316,0,'2014-05-14',0,'1/1',1317),(1320,0,'2006-02-04',0,'1/1',1321),(1324,0,'2006-06-17',0,'1/1',1325),(1328,0,'2014-07-26',0,'1/1',1329),(1332,0,'2009-01-13',0,'1/1',1333),(1336,0,'2006-07-22',0,'1/1',1337),(1340,0,'2000-05-07',0,'1/1',1341),(1344,0,'2012-05-06',0,'1/1',1345),(1348,0,'2000-04-03',0,'1/1',1349),(1352,0,'2005-04-06',0,'1/1',1353),(1356,0,'2010-01-22',0,'1/1',1357),(1360,0,'1999-12-28',0,'1/1',1361),(1364,0,'2006-10-12',0,'1/1',1365),(1368,0,'2009-08-08',0,'1/1',1369),(1372,0,'2012-01-14',0,'1/1',1373),(1376,0,'2010-01-11',0,'1/1',1377),(1380,0,'2014-01-11',0,'1/1',1381),(1384,0,'2006-04-03',0,'1/1',1385),(1388,0,'2003-10-20',0,'1/1',1389),(1392,0,'2008-03-12',0,'1/1',1393),(1396,0,'2012-04-23',0,'1/1',1397),(1400,0,'2011-10-09',0,'1/1',1401),(1404,0,'1999-06-20',0,'1/1',1405),(1408,0,'2000-04-24',0,'1/1',1409),(1412,0,'2014-05-15',0,'1/1',1413),(1416,0,'2011-12-12',0,'1/1',1417),(1420,0,'1999-05-22',0,'1/1',1421),(1424,0,'2005-03-22',0,'1/1',1425),(1428,0,'2006-07-19',0,'1/1',1429),(1432,0,'2003-03-15',0,'1/1',1433),(1436,0,'2010-04-17',0,'1/1',1437),(1440,0,'2000-12-20',0,'1/1',1441),(1444,0,'2014-03-23',0,'1/1',1445),(1448,0,'2004-09-03',0,'1/1',1449),(1452,0,'2000-06-11',0,'1/1',1453),(1456,0,'2004-02-15',0,'1/1',1457),(1460,0,'2002-05-24',0,'1/1',1461),(1464,0,'2008-02-22',0,'1/1',1465),(1468,0,'2001-06-14',0,'1/1',1469),(1472,0,'2011-11-12',0,'1/1',1473),(1476,0,'2005-06-03',0,'1/1',1477),(1480,0,'2004-10-12',0,'1/1',1481),(1484,0,'2010-07-01',0,'1/1',1485),(1488,0,'2003-06-26',0,'1/1',1489),(1492,0,'2010-04-04',0,'1/1',1493),(1496,0,'2009-02-04',0,'1/1',1497),(1500,0,'2001-03-29',0,'1/1',1501),(1504,0,'2007-02-21',0,'1/1',1505),(1508,0,'2001-07-28',0,'1/1',1509),(1512,0,'2007-11-21',0,'1/1',1513),(1516,0,'2002-10-30',0,'1/1',1517),(1520,0,'2005-12-08',0,'1/1',1521),(1524,0,'2014-03-05',0,'1/1',1525),(1528,0,'2010-11-08',0,'1/1',1529),(1532,0,'2007-03-10',0,'1/1',1533),(1536,0,'2013-04-20',0,'1/1',1537),(1540,0,'2001-07-17',0,'1/1',1541),(1544,0,'2001-10-27',0,'1/1',1545),(1548,0,'2008-07-30',0,'1/1',1549),(1552,0,'2005-02-28',0,'1/1',1553),(1556,0,'2006-05-27',0,'1/1',1557),(1560,0,'2009-06-18',0,'1/1',1561),(1564,0,'2009-09-12',0,'1/1',1565),(1568,0,'2000-03-31',0,'1/1',1569),(1572,0,'2002-01-15',0,'1/1',1573),(1576,0,'2004-05-18',0,'1/1',1577),(1580,0,'2003-11-01',0,'1/1',1581),(1584,0,'2011-05-20',0,'1/1',1585),(1588,0,'2008-07-07',0,'1/1',1589),(1592,0,'2009-05-24',0,'1/1',1593),(1596,0,'1999-10-30',0,'1/1',1597),(1600,0,'2001-05-29',0,'1/1',1601),(1604,0,'2012-10-14',0,'1/1',1605),(1608,0,'2000-12-06',0,'1/1',1609),(1612,0,'2000-02-16',0,'1/1',1613),(1616,0,'2009-03-13',0,'1/1',1617),(1620,0,'2014-05-04',0,'1/1',1621),(1624,0,'2010-04-12',0,'1/1',1625),(1628,0,'2005-04-24',0,'1/1',1629),(1632,0,'2005-04-18',0,'1/1',1633),(1636,0,'2000-12-01',0,'1/1',1637),(1640,0,'2001-01-01',0,'1/1',1641),(1644,0,'2005-09-24',0,'1/1',1645),(1648,0,'2013-09-13',0,'1/1',1649),(1652,0,'2003-04-15',0,'1/1',1653),(1656,0,'2013-07-30',0,'1/1',1657),(1660,0,'1999-05-30',0,'1/1',1661),(1664,0,'2005-08-20',0,'1/1',1665),(1668,0,'2010-11-20',0,'1/1',1669),(1672,0,'2004-02-11',0,'1/1',1673),(1676,0,'2010-06-10',0,'1/1',1677),(1680,0,'2013-12-10',0,'1/1',1681),(1684,0,'2002-08-10',0,'1/1',1685),(1688,0,'2014-03-24',0,'1/1',1689),(1692,0,'1999-01-27',0,'1/1',1693),(1696,0,'1999-04-09',0,'1/1',1697),(1700,0,'2000-07-22',0,'1/1',1701),(1704,0,'2010-04-30',0,'1/1',1705),(1708,0,'2002-04-23',0,'1/1',1709),(1712,0,'2004-12-14',0,'1/1',1713),(1716,0,'2008-06-14',0,'1/1',1717),(1720,0,'2010-08-08',0,'1/1',1721),(1724,0,'2003-11-13',0,'1/1',1725),(1728,0,'2010-02-16',0,'1/1',1729),(1732,0,'1999-03-20',0,'1/1',1733),(1736,0,'2006-04-21',0,'1/1',1737),(1740,0,'2006-06-14',0,'1/1',1741),(1744,0,'2009-05-26',0,'1/1',1745),(1748,0,'2006-02-18',0,'1/1',1749),(1752,0,'2004-08-02',0,'1/1',1753),(1756,0,'2010-02-21',0,'1/1',1757),(1760,0,'2008-07-24',0,'1/1',1761),(1764,0,'2002-07-10',0,'1/1',1765),(1768,0,'2010-09-07',0,'1/1',1769),(1772,0,'2001-04-13',0,'1/1',1773),(1776,0,'2011-11-22',0,'1/1',1777),(1780,0,'2014-04-04',0,'1/1',1781),(1784,0,'2012-02-26',0,'1/1',1785),(1788,0,'2011-05-27',0,'1/1',1789),(1792,0,'2011-07-19',0,'1/1',1793),(1796,0,'2000-10-05',0,'1/1',1797),(1800,0,'2000-08-16',0,'1/1',1801),(1804,0,'2013-06-26',0,'1/1',1805),(1808,0,'2004-01-22',0,'1/1',1809),(1812,0,'2003-06-13',0,'1/1',1813),(1816,0,'1999-08-26',0,'1/1',1817),(1820,0,'2007-06-15',0,'1/1',1821),(1824,0,'2004-09-03',0,'1/1',1825),(1828,0,'2010-08-07',0,'1/1',1829),(1832,0,'2007-05-26',0,'1/1',1833),(1836,0,'2011-07-15',0,'1/1',1837),(1840,0,'2008-02-11',0,'1/1',1841),(1844,0,'2010-05-26',0,'1/1',1845),(1848,0,'2010-05-04',0,'1/1',1849),(1852,0,'2003-09-27',0,'1/1',1853),(1856,0,'1999-11-10',0,'1/1',1857),(1860,0,'2006-09-22',0,'1/1',1861),(1864,0,'2007-02-02',0,'1/1',1865),(1868,0,'2008-12-14',0,'1/1',1869),(1872,0,'2012-10-25',0,'1/1',1873),(1876,0,'2000-05-16',0,'1/1',1877),(1880,0,'2008-10-11',0,'1/1',1881),(1884,0,'2002-05-01',0,'1/1',1885),(1888,0,'2010-12-22',0,'1/1',1889),(1892,0,'2012-10-05',0,'1/1',1893),(1896,0,'2001-03-05',0,'1/1',1897),(1900,0,'2010-10-24',0,'1/1',1901),(1904,0,'2003-10-12',0,'1/1',1905),(1908,0,'2009-08-01',0,'1/1',1909),(1912,0,'1999-08-24',0,'1/1',1913),(1916,0,'2011-07-18',0,'1/1',1917),(1920,0,'2009-03-31',0,'1/1',1921),(1924,0,'2006-02-11',0,'1/1',1925),(1928,0,'2004-03-25',0,'1/1',1929),(1932,0,'2008-01-16',0,'1/1',1933),(1936,0,'2002-09-21',0,'1/1',1937),(1940,0,'2003-03-16',0,'1/1',1941),(1944,0,'2002-08-08',0,'1/1',1945),(1948,0,'2012-11-09',0,'1/1',1949),(1952,0,'2012-03-30',0,'1/1',1953),(1956,0,'2007-07-19',0,'1/1',1957),(1960,0,'1999-04-07',0,'1/1',1961),(1964,0,'2007-12-03',0,'1/1',1965),(1968,0,'2014-05-14',0,'1/1',1969),(1972,0,'2006-02-13',0,'1/1',1973);
/*!40000 ALTER TABLE `cuota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deuda`
--

DROP TABLE IF EXISTS `deuda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deuda` (
  `IDDEUDA` bigint(20) NOT NULL,
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `FECHAGENERACION` date DEFAULT NULL,
  `OBSERVACION` varchar(1000) DEFAULT NULL,
  `UNCONCEPTODEPORTIVO_IDCONCEPTODEPORTIVO` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`IDDEUDA`),
  KEY `FK_DEUDA_UNCONCEPTODEPORTIVO_IDCONCEPTODEPORTIVO` (`UNCONCEPTODEPORTIVO_IDCONCEPTODEPORTIVO`),
  CONSTRAINT `FK_DEUDA_UNCONCEPTODEPORTIVO_IDCONCEPTODEPORTIVO` FOREIGN KEY (`UNCONCEPTODEPORTIVO_IDCONCEPTODEPORTIVO`) REFERENCES `conceptodeportivo` (`IDCONCEPTODEPORTIVO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deuda`
--

LOCK TABLES `deuda` WRITE;
/*!40000 ALTER TABLE `deuda` DISABLE KEYS */;
INSERT INTO `deuda` VALUES (478,0,'2010-02-02','Pase de Equipo: ---- a Equipo : Guaraní',73),(482,0,'2002-03-01','Pase de Equipo: ---- a Equipo : Legislativo',73),(486,0,'2010-03-17','Pase de Equipo: ---- a Equipo : Legislativo B',73),(490,0,'2004-05-31','Pase de Equipo: ---- a Equipo : CAPRI A',73),(494,0,'2008-09-03','Pase de Equipo: ---- a Equipo : Camionero',73),(498,0,'2009-07-30','Pase de Equipo: ---- a Equipo : CAPRI A',73),(502,0,'2005-10-05','Pase de Equipo: ---- a Equipo : Ucraniano',73),(506,0,'2005-08-27','Pase de Equipo: ---- a Equipo : Pira Pyta Pota',73),(510,0,'1999-01-27','Pase de Equipo: ---- a Equipo : Nuevo Legislativo',73),(514,0,'2006-06-19','Pase de Equipo: ---- a Equipo : Camara Legislativa',73),(518,0,'1999-04-09','Pase de Equipo: ---- a Equipo : Legislativo B',73),(522,0,'2012-06-14','Pase de Equipo: ---- a Equipo : Unión B',73),(526,0,'2004-06-29','Pase de Equipo: ---- a Equipo : CAPRI C',73),(530,0,'2013-03-13','Pase de Equipo: ---- a Equipo : Crucero NERANJA',73),(534,0,'2012-02-07','Pase de Equipo: ---- a Equipo : Luz y Fuerza',73),(538,0,'2014-01-25','Pase de Equipo: ---- a Equipo : Camionero',73),(542,0,'2000-12-29','Pase de Equipo: ---- a Equipo : Ucraniano',73),(546,0,'2013-05-16','Pase de Equipo: ---- a Equipo : Guaraní',73),(550,0,'2006-09-09','Pase de Equipo: ---- a Equipo : Mitre',73),(554,0,'2002-02-20','Pase de Equipo: ---- a Equipo : Unión B',73),(558,0,'2001-11-21','Pase de Equipo: ---- a Equipo : Unión B',73),(562,0,'2007-10-20','Pase de Equipo: ---- a Equipo : Ytá',73),(566,0,'2008-05-17','Pase de Equipo: ---- a Equipo : Guaraní',73),(570,0,'2012-11-01','Pase de Equipo: ---- a Equipo : Nuevo Legislativo',73),(574,0,'1999-06-25','Pase de Equipo: ---- a Equipo : Educación',73),(578,0,'2000-10-20','Pase de Equipo: ---- a Equipo : Unión B',73),(582,0,'2011-12-07','Pase de Equipo: ---- a Equipo : Camionero',73),(586,0,'2003-06-01','Pase de Equipo: ---- a Equipo : Educación',73),(590,0,'2002-02-05','Pase de Equipo: ---- a Equipo : La Picada',73),(594,0,'2011-05-26','Pase de Equipo: ---- a Equipo : Ytá',73),(598,0,'2014-02-24','Pase de Equipo: ---- a Equipo : Nuevo Legislativo',73),(602,0,'2004-10-13','Pase de Equipo: ---- a Equipo : CAPRI B',73),(606,0,'2005-07-09','Pase de Equipo: ---- a Equipo : Mitre',73),(610,0,'2014-06-21','Pase de Equipo: ---- a Equipo : Educación',73),(614,0,'2008-11-29','Pase de Equipo: ---- a Equipo : Luz y Fuerza',73),(618,0,'2010-01-13','Pase de Equipo: ---- a Equipo : Legislativo B',73),(622,0,'2013-03-25','Pase de Equipo: ---- a Equipo : Crucero AMARILLO',73),(626,0,'2001-11-25','Pase de Equipo: ---- a Equipo : Pira Pyta Pota',73),(630,0,'1999-07-09','Pase de Equipo: ---- a Equipo : Legislativo',73),(634,0,'2013-04-10','Pase de Equipo: ---- a Equipo : Cazadores AZUL',73),(638,0,'2013-01-04','Pase de Equipo: ---- a Equipo : Unión A',73),(642,0,'2006-11-08','Pase de Equipo: ---- a Equipo : Educación',73),(646,0,'2001-10-12','Pase de Equipo: ---- a Equipo : Mitre',73),(650,0,'2012-08-27','Pase de Equipo: ---- a Equipo : Tacurú',73),(654,0,'2002-09-20','Pase de Equipo: ---- a Equipo : Crucero AMARILLO',73),(658,0,'2007-01-26','Pase de Equipo: ---- a Equipo : Crucero NERANJA',73),(662,0,'2014-04-07','Pase de Equipo: ---- a Equipo : CAPRI C',73),(666,0,'2008-02-12','Pase de Equipo: ---- a Equipo : Camionero',73),(670,0,'2010-05-29','Pase de Equipo: ---- a Equipo : Legislativo',73),(674,0,'1999-04-23','Pase de Equipo: ---- a Equipo : Educación',73),(678,0,'2009-05-27','Pase de Equipo: ---- a Equipo : Camara Legislativa',73),(682,0,'2011-02-13','Pase de Equipo: ---- a Equipo : Pira Pyta Pota',73),(686,0,'2008-12-01','Pase de Equipo: ---- a Equipo : Guaraní',73),(690,0,'2002-09-20','Pase de Equipo: ---- a Equipo : Unión B',73),(694,0,'2000-08-29','Pase de Equipo: ---- a Equipo : Cazadores ROJO',73),(698,0,'2008-03-20','Pase de Equipo: ---- a Equipo : CAPRI C',73),(702,0,'2011-10-10','Pase de Equipo: ---- a Equipo : Luz y Fuerza',73),(706,0,'2010-12-17','Pase de Equipo: ---- a Equipo : Mitre',73),(710,0,'2010-04-18','Pase de Equipo: ---- a Equipo : Tacurú',73),(714,0,'1999-03-25','Pase de Equipo: ---- a Equipo : La Picada',73),(718,0,'2014-02-03','Pase de Equipo: ---- a Equipo : CAPRI C',73),(722,0,'2005-03-26','Pase de Equipo: ---- a Equipo : Educación',73),(726,0,'2007-08-18','Pase de Equipo: ---- a Equipo : Unión B',73),(730,0,'2004-09-25','Pase de Equipo: ---- a Equipo : Nuevo Legislativo',73),(734,0,'2010-05-17','Pase de Equipo: ---- a Equipo : La Picada',73),(738,0,'2003-02-10','Pase de Equipo: ---- a Equipo : Legislativo',73),(742,0,'2000-04-11','Pase de Equipo: ---- a Equipo : Nuevo Legislativo',73),(746,0,'2010-06-06','Pase de Equipo: ---- a Equipo : Tacurú',73),(750,0,'2008-09-18','Pase de Equipo: ---- a Equipo : Legislativo B',73),(754,0,'2005-03-13','Pase de Equipo: ---- a Equipo : Ucraniano',73),(758,0,'2008-07-07','Pase de Equipo: ---- a Equipo : Legislativo B',73),(762,0,'2003-09-07','Pase de Equipo: ---- a Equipo : Nuevo Legislativo',73),(766,0,'2000-12-13','Pase de Equipo: ---- a Equipo : Luz y Fuerza',73),(770,0,'2005-07-22','Pase de Equipo: ---- a Equipo : Ucraniano',73),(774,0,'2001-03-02','Pase de Equipo: ---- a Equipo : Camara Legislativa',73),(778,0,'2004-12-23','Pase de Equipo: ---- a Equipo : Cazadores ROJO',73),(782,0,'2012-05-30','Pase de Equipo: ---- a Equipo : CAPRI A',73),(786,0,'2010-04-01','Pase de Equipo: ---- a Equipo : Ucraniano',73),(790,0,'2010-02-05','Pase de Equipo: ---- a Equipo : CAPRI A',73),(794,0,'2001-10-12','Pase de Equipo: ---- a Equipo : Unión A',73),(798,0,'2012-04-11','Pase de Equipo: ---- a Equipo : Sol Legislativo',73),(802,0,'2010-09-16','Pase de Equipo: ---- a Equipo : Cazadores AZUL',73),(806,0,'2004-07-20','Pase de Equipo: ---- a Equipo : Ytá',73),(810,0,'2006-09-22','Pase de Equipo: ---- a Equipo : Cazadores AZUL',73),(814,0,'2006-12-03','Pase de Equipo: ---- a Equipo : Cazadores A',73),(818,0,'2003-05-02','Pase de Equipo: ---- a Equipo : Educación',73),(822,0,'2001-04-16','Pase de Equipo: ---- a Equipo : Guaraní',73),(826,0,'2004-03-04','Pase de Equipo: ---- a Equipo : Sol Legislativo',73),(830,0,'2000-12-08','Pase de Equipo: ---- a Equipo : Camionero',73),(834,0,'1999-03-14','Pase de Equipo: ---- a Equipo : Unión A',73),(838,0,'2013-02-17','Pase de Equipo: ---- a Equipo : Unión A',73),(842,0,'2000-03-28','Pase de Equipo: ---- a Equipo : CAPRI B',73),(846,0,'2000-04-23','Pase de Equipo: ---- a Equipo : Educación',73),(850,0,'2003-07-26','Pase de Equipo: ---- a Equipo : Cazadores AZUL',73),(854,0,'2007-05-12','Pase de Equipo: ---- a Equipo : Cazadores AZUL',73),(858,0,'2000-09-10','Pase de Equipo: ---- a Equipo : Educación',73),(862,0,'2011-01-30','Pase de Equipo: ---- a Equipo : Unión A',73),(866,0,'2012-07-03','Pase de Equipo: ---- a Equipo : Mitre',73),(870,0,'1999-09-12','Pase de Equipo: ---- a Equipo : Unión B',73),(874,0,'2001-05-05','Pase de Equipo: ---- a Equipo : Legislativo',73),(878,0,'2010-12-08','Pase de Equipo: ---- a Equipo : Ytá',73),(882,0,'2003-12-17','Pase de Equipo: ---- a Equipo : CAPRI A',73),(886,0,'2003-04-12','Pase de Equipo: ---- a Equipo : Pira Pyta Pota',73),(890,0,'2002-01-02','Pase de Equipo: ---- a Equipo : Unión A',73),(894,0,'2008-12-12','Pase de Equipo: ---- a Equipo : Pira Pyta Pota',73),(898,0,'2010-07-14','Pase de Equipo: ---- a Equipo : Ytá',73),(902,0,'2003-06-10','Pase de Equipo: ---- a Equipo : Legislativo',73),(906,0,'2011-07-31','Pase de Equipo: ---- a Equipo : Educación',73),(910,0,'2013-05-10','Pase de Equipo: ---- a Equipo : CAPRI C',73),(914,0,'2000-08-18','Pase de Equipo: ---- a Equipo : Sol Legislativo',73),(918,0,'2010-12-19','Pase de Equipo: ---- a Equipo : Pira Pyta Pota',73),(922,0,'2009-12-01','Pase de Equipo: ---- a Equipo : La Picada',73),(926,0,'2001-03-26','Pase de Equipo: ---- a Equipo : Tacurú',73),(930,0,'2004-06-02','Pase de Equipo: ---- a Equipo : Crucero AMARILLO',73),(934,0,'2002-12-13','Pase de Equipo: ---- a Equipo : CAPRI C',73),(938,0,'2002-11-23','Pase de Equipo: ---- a Equipo : Mitre',73),(942,0,'2010-11-30','Pase de Equipo: ---- a Equipo : Camara Legislativa',73),(946,0,'2006-08-09','Pase de Equipo: ---- a Equipo : Cazadores AZUL',73),(950,0,'2009-07-17','Pase de Equipo: ---- a Equipo : CAPRI A',73),(954,0,'2010-04-13','Pase de Equipo: ---- a Equipo : Guaraní',73),(958,0,'2010-09-18','Pase de Equipo: ---- a Equipo : Ucraniano',73),(962,0,'2010-02-12','Pase de Equipo: ---- a Equipo : Cazadores AZUL',73),(966,0,'2006-09-27','Pase de Equipo: ---- a Equipo : Ytá',73),(970,0,'2002-10-31','Pase de Equipo: ---- a Equipo : Cazadores AZUL',73),(974,0,'2013-04-30','Pase de Equipo: ---- a Equipo : Ucraniano',73),(978,0,'2002-08-27','Pase de Equipo: ---- a Equipo : La Picada',73),(982,0,'2009-07-26','Pase de Equipo: ---- a Equipo : Nuevo Legislativo',73),(986,0,'2006-01-28','Pase de Equipo: ---- a Equipo : Ucraniano',73),(990,0,'2010-04-19','Pase de Equipo: ---- a Equipo : Tacurú',73),(994,0,'2010-10-30','Pase de Equipo: ---- a Equipo : Legislativo',73),(998,0,'2003-06-27','Pase de Equipo: ---- a Equipo : Crucero NERANJA',73),(1002,0,'2009-11-21','Pase de Equipo: ---- a Equipo : La Picada',73),(1006,0,'2009-11-12','Pase de Equipo: ---- a Equipo : Nuevo Legislativo',73),(1010,0,'2002-10-24','Pase de Equipo: ---- a Equipo : Educación',73),(1014,0,'2002-12-14','Pase de Equipo: ---- a Equipo : Pira Pyta Pota',73),(1018,0,'2006-04-13','Pase de Equipo: ---- a Equipo : Unión A',73),(1022,0,'2012-12-20','Pase de Equipo: ---- a Equipo : CAPRI C',73),(1026,0,'2005-08-21','Pase de Equipo: ---- a Equipo : Mitre',73),(1030,0,'2005-08-12','Pase de Equipo: ---- a Equipo : Ytá',73),(1034,0,'2009-11-07','Pase de Equipo: ---- a Equipo : La Picada',73),(1038,0,'2002-04-14','Pase de Equipo: ---- a Equipo : Educación',73),(1042,0,'1999-01-26','Pase de Equipo: ---- a Equipo : Sol Legislativo',73),(1046,0,'2005-11-23','Pase de Equipo: ---- a Equipo : Legislativo',73),(1050,0,'2001-05-17','Pase de Equipo: ---- a Equipo : Cazadores ROJO',73),(1054,0,'2006-12-03','Pase de Equipo: ---- a Equipo : Educación',73),(1058,0,'2007-09-25','Pase de Equipo: ---- a Equipo : Pira Pyta Pota',73),(1062,0,'1999-06-18','Pase de Equipo: ---- a Equipo : Luz y Fuerza',73),(1066,0,'2013-08-23','Pase de Equipo: ---- a Equipo : Mitre',73),(1070,0,'1999-10-14','Pase de Equipo: ---- a Equipo : Pira Pyta Pota',73),(1074,0,'1999-07-19','Pase de Equipo: ---- a Equipo : Sol Legislativo',73),(1078,0,'2012-05-19','Pase de Equipo: ---- a Equipo : Cazadores AZUL',73),(1082,0,'2000-04-26','Pase de Equipo: ---- a Equipo : La Picada',73),(1086,0,'2008-05-06','Pase de Equipo: ---- a Equipo : Unión A',73),(1090,0,'2006-12-21','Pase de Equipo: ---- a Equipo : Cazadores ROJO',73),(1094,0,'1999-07-28','Pase de Equipo: ---- a Equipo : Crucero NERANJA',73),(1098,0,'2014-02-15','Pase de Equipo: ---- a Equipo : Ucraniano',73),(1102,0,'2003-10-31','Pase de Equipo: ---- a Equipo : Luz y Fuerza',73),(1106,0,'2006-07-08','Pase de Equipo: ---- a Equipo : Crucero AMARILLO',73),(1110,0,'2005-01-05','Pase de Equipo: ---- a Equipo : Nuevo Legislativo',73),(1114,0,'2011-01-22','Pase de Equipo: ---- a Equipo : Sol Legislativo',73),(1118,0,'2009-03-09','Pase de Equipo: ---- a Equipo : CAPRI C',73),(1122,0,'2001-09-25','Pase de Equipo: ---- a Equipo : Luz y Fuerza',73),(1126,0,'2009-02-12','Pase de Equipo: ---- a Equipo : CAPRI B',73),(1130,0,'2004-07-20','Pase de Equipo: ---- a Equipo : Unión A',73),(1134,0,'2002-05-21','Pase de Equipo: ---- a Equipo : CAPRI A',73),(1138,0,'2007-07-18','Pase de Equipo: ---- a Equipo : Camionero',73),(1142,0,'2013-03-13','Pase de Equipo: ---- a Equipo : Crucero NERANJA',73),(1146,0,'2006-12-23','Pase de Equipo: ---- a Equipo : Crucero NERANJA',73),(1150,0,'1999-03-03','Pase de Equipo: ---- a Equipo : Unión B',73),(1154,0,'1999-05-14','Pase de Equipo: ---- a Equipo : Mitre',73),(1158,0,'2006-12-06','Pase de Equipo: ---- a Equipo : Cazadores A',73),(1162,0,'2011-06-12','Pase de Equipo: ---- a Equipo : Cazadores AZUL',73),(1166,0,'2004-02-04','Pase de Equipo: ---- a Equipo : Unión B',73),(1170,0,'2012-06-15','Pase de Equipo: ---- a Equipo : Educación',73),(1174,0,'2005-08-27','Pase de Equipo: ---- a Equipo : Cazadores AZUL',73),(1178,0,'2003-05-02','Pase de Equipo: ---- a Equipo : CAPRI B',73),(1182,0,'2007-02-13','Pase de Equipo: ---- a Equipo : Cazadores A',73),(1186,0,'2004-09-13','Pase de Equipo: ---- a Equipo : La Picada',73),(1190,0,'2003-04-26','Pase de Equipo: ---- a Equipo : Luz y Fuerza',73),(1194,0,'2007-12-18','Pase de Equipo: ---- a Equipo : CAPRI B',73),(1198,0,'2012-01-15','Pase de Equipo: ---- a Equipo : Camionero',73),(1202,0,'2004-12-05','Pase de Equipo: ---- a Equipo : Unión A',73),(1206,0,'2007-05-28','Pase de Equipo: ---- a Equipo : La Picada',73),(1210,0,'2003-10-13','Pase de Equipo: ---- a Equipo : Cazadores AZUL',73),(1214,0,'2012-09-05','Pase de Equipo: ---- a Equipo : Crucero AMARILLO',73),(1218,0,'2001-02-21','Pase de Equipo: ---- a Equipo : Crucero AMARILLO',73),(1222,0,'2002-09-11','Pase de Equipo: ---- a Equipo : Nuevo Legislativo',73),(1226,0,'2004-08-12','Pase de Equipo: ---- a Equipo : Unión A',73),(1230,0,'2001-08-27','Pase de Equipo: ---- a Equipo : Pira Pyta Pota',73),(1234,0,'2011-06-09','Pase de Equipo: ---- a Equipo : Camionero',73),(1238,0,'2004-03-23','Pase de Equipo: ---- a Equipo : Ytá',73),(1242,0,'2001-04-08','Pase de Equipo: ---- a Equipo : Crucero AMARILLO',73),(1246,0,'2013-01-17','Pase de Equipo: ---- a Equipo : Ucraniano',73),(1250,0,'2004-10-10','Pase de Equipo: ---- a Equipo : Cazadores ROJO',73),(1254,0,'2010-09-06','Pase de Equipo: ---- a Equipo : Cazadores A',73),(1258,0,'2013-12-07','Pase de Equipo: ---- a Equipo : Pira Pyta Pota',73),(1262,0,'2001-01-17','Pase de Equipo: ---- a Equipo : Cazadores A',73),(1266,0,'2007-03-01','Pase de Equipo: ---- a Equipo : Cazadores ROJO',73),(1270,0,'2003-03-11','Pase de Equipo: ---- a Equipo : La Picada',73),(1274,0,'2013-04-02','Pase de Equipo: ---- a Equipo : Luz y Fuerza',73),(1278,0,'2005-10-20','Pase de Equipo: ---- a Equipo : CAPRI A',73),(1282,0,'2009-12-22','Pase de Equipo: ---- a Equipo : Luz y Fuerza',73),(1286,0,'2001-07-20','Pase de Equipo: ---- a Equipo : Cazadores A',73),(1290,0,'2002-05-03','Pase de Equipo: ---- a Equipo : Unión B',73),(1294,0,'1999-09-02','Pase de Equipo: ---- a Equipo : Crucero AMARILLO',73),(1298,0,'2009-07-22','Pase de Equipo: ---- a Equipo : Guaraní',73),(1302,0,'2012-06-19','Pase de Equipo: ---- a Equipo : CAPRI B',73),(1306,0,'2004-05-07','Pase de Equipo: ---- a Equipo : Educación',73),(1310,0,'1999-11-07','Pase de Equipo: ---- a Equipo : La Picada',73),(1314,0,'2007-09-26','Pase de Equipo: ---- a Equipo : Mitre',73),(1318,0,'2014-05-14','Pase de Equipo: ---- a Equipo : Sol Legislativo',73),(1322,0,'2006-02-04','Pase de Equipo: ---- a Equipo : Tacurú',73),(1326,0,'2006-06-17','Pase de Equipo: ---- a Equipo : Unión A',73),(1330,0,'2014-07-26','Pase de Equipo: ---- a Equipo : Ucraniano',73),(1334,0,'2009-01-13','Pase de Equipo: ---- a Equipo : Camionero',73),(1338,0,'2006-07-22','Pase de Equipo: ---- a Equipo : Ucraniano',73),(1342,0,'2000-05-07','Pase de Equipo: ---- a Equipo : CAPRI C',73),(1346,0,'2012-05-06','Pase de Equipo: ---- a Equipo : Camara Legislativa',73),(1350,0,'2000-04-03','Pase de Equipo: ---- a Equipo : Camara Legislativa',73),(1354,0,'2005-04-06','Pase de Equipo: ---- a Equipo : Camionero',73),(1358,0,'2010-01-22','Pase de Equipo: ---- a Equipo : Mitre',73),(1362,0,'1999-12-28','Pase de Equipo: ---- a Equipo : Pira Pyta Pota',73),(1366,0,'2006-10-12','Pase de Equipo: ---- a Equipo : Nuevo Legislativo',73),(1370,0,'2009-08-08','Pase de Equipo: ---- a Equipo : Crucero NERANJA',73),(1374,0,'2012-01-14','Pase de Equipo: ---- a Equipo : La Picada',73),(1378,0,'2010-01-11','Pase de Equipo: ---- a Equipo : Nuevo Legislativo',73),(1382,0,'2014-01-11','Pase de Equipo: ---- a Equipo : Mitre',73),(1386,0,'2006-04-03','Pase de Equipo: ---- a Equipo : Ucraniano',73),(1390,0,'2003-10-20','Pase de Equipo: ---- a Equipo : CAPRI B',73),(1394,0,'2008-03-12','Pase de Equipo: ---- a Equipo : Mitre',73),(1398,0,'2012-04-23','Pase de Equipo: ---- a Equipo : CAPRI A',73),(1402,0,'2011-10-09','Pase de Equipo: ---- a Equipo : Guaraní',73),(1406,0,'1999-06-20','Pase de Equipo: ---- a Equipo : Cazadores A',73),(1410,0,'2000-04-24','Pase de Equipo: ---- a Equipo : Camara Legislativa',73),(1414,0,'2014-05-15','Pase de Equipo: ---- a Equipo : Sol Legislativo',73),(1418,0,'2011-12-12','Pase de Equipo: ---- a Equipo : Educación',73),(1422,0,'1999-05-22','Pase de Equipo: ---- a Equipo : Luz y Fuerza',73),(1426,0,'2005-03-22','Pase de Equipo: ---- a Equipo : Nuevo Legislativo',73),(1430,0,'2006-07-19','Pase de Equipo: ---- a Equipo : Unión A',73),(1434,0,'2003-03-15','Pase de Equipo: ---- a Equipo : CAPRI A',73),(1438,0,'2010-04-17','Pase de Equipo: ---- a Equipo : Mitre',73),(1442,0,'2000-12-20','Pase de Equipo: ---- a Equipo : Pira Pyta Pota',73),(1446,0,'2014-03-23','Pase de Equipo: ---- a Equipo : Unión A',73),(1450,0,'2004-09-03','Pase de Equipo: ---- a Equipo : Unión A',73),(1454,0,'2000-06-11','Pase de Equipo: ---- a Equipo : Mitre',73),(1458,0,'2004-02-15','Pase de Equipo: ---- a Equipo : CAPRI B',73),(1462,0,'2002-05-24','Pase de Equipo: ---- a Equipo : Cazadores ROJO',73),(1466,0,'2008-02-22','Pase de Equipo: ---- a Equipo : Crucero AMARILLO',73),(1470,0,'2001-06-14','Pase de Equipo: ---- a Equipo : CAPRI B',73),(1474,0,'2011-11-12','Pase de Equipo: ---- a Equipo : CAPRI B',73),(1478,0,'2005-06-03','Pase de Equipo: ---- a Equipo : Unión B',73),(1482,0,'2004-10-12','Pase de Equipo: ---- a Equipo : CAPRI B',73),(1486,0,'2010-07-01','Pase de Equipo: ---- a Equipo : Ytá',73),(1490,0,'2003-06-26','Pase de Equipo: ---- a Equipo : Camionero',73),(1494,0,'2010-04-04','Pase de Equipo: ---- a Equipo : CAPRI A',73),(1498,0,'2009-02-04','Pase de Equipo: ---- a Equipo : Pira Pyta Pota',73),(1502,0,'2001-03-29','Pase de Equipo: ---- a Equipo : Cazadores ROJO',73),(1506,0,'2007-02-21','Pase de Equipo: ---- a Equipo : Guaraní',73),(1510,0,'2001-07-28','Pase de Equipo: ---- a Equipo : La Picada',73),(1514,0,'2007-11-21','Pase de Equipo: ---- a Equipo : La Picada',73),(1518,0,'2002-10-30','Pase de Equipo: ---- a Equipo : La Picada',73),(1522,0,'2005-12-08','Pase de Equipo: ---- a Equipo : CAPRI B',73),(1526,0,'2014-03-05','Pase de Equipo: ---- a Equipo : Camionero',73),(1530,0,'2010-11-08','Pase de Equipo: ---- a Equipo : Pira Pyta Pota',73),(1534,0,'2007-03-10','Pase de Equipo: ---- a Equipo : CAPRI C',73),(1538,0,'2013-04-20','Pase de Equipo: ---- a Equipo : Mitre',73),(1542,0,'2001-07-17','Pase de Equipo: ---- a Equipo : La Picada',73),(1546,0,'2001-10-27','Pase de Equipo: ---- a Equipo : Educación',73),(1550,0,'2008-07-30','Pase de Equipo: ---- a Equipo : CAPRI A',73),(1554,0,'2005-02-28','Pase de Equipo: ---- a Equipo : Pira Pyta Pota',73),(1558,0,'2006-05-27','Pase de Equipo: ---- a Equipo : Unión A',73),(1562,0,'2009-06-18','Pase de Equipo: ---- a Equipo : Nuevo Legislativo',73),(1566,0,'2009-09-12','Pase de Equipo: ---- a Equipo : Tacurú',73),(1570,0,'2000-03-31','Pase de Equipo: ---- a Equipo : CAPRI C',73),(1574,0,'2002-01-15','Pase de Equipo: ---- a Equipo : Mitre',73),(1578,0,'2004-05-18','Pase de Equipo: ---- a Equipo : Mitre',73),(1582,0,'2003-11-01','Pase de Equipo: ---- a Equipo : Tacurú',73),(1586,0,'2011-05-20','Pase de Equipo: ---- a Equipo : Educación',73),(1590,0,'2008-07-07','Pase de Equipo: ---- a Equipo : Cazadores AZUL',73),(1594,0,'2009-05-24','Pase de Equipo: ---- a Equipo : Legislativo B',73),(1598,0,'1999-10-30','Pase de Equipo: ---- a Equipo : Crucero NERANJA',73),(1602,0,'2001-05-29','Pase de Equipo: ---- a Equipo : Camionero',73),(1606,0,'2012-10-14','Pase de Equipo: ---- a Equipo : Cazadores AZUL',73),(1610,0,'2000-12-06','Pase de Equipo: ---- a Equipo : Legislativo B',73),(1614,0,'2000-02-16','Pase de Equipo: ---- a Equipo : CAPRI C',73),(1618,0,'2009-03-13','Pase de Equipo: ---- a Equipo : Pira Pyta Pota',73),(1622,0,'2014-05-04','Pase de Equipo: ---- a Equipo : Ytá',73),(1626,0,'2010-04-12','Pase de Equipo: ---- a Equipo : Cazadores A',73),(1630,0,'2005-04-24','Pase de Equipo: ---- a Equipo : Cazadores A',73),(1634,0,'2005-04-18','Pase de Equipo: ---- a Equipo : Educación',73),(1638,0,'2000-12-01','Pase de Equipo: ---- a Equipo : Luz y Fuerza',73),(1642,0,'2001-01-01','Pase de Equipo: ---- a Equipo : Crucero AMARILLO',73),(1646,0,'2005-09-24','Pase de Equipo: ---- a Equipo : La Picada',73),(1650,0,'2013-09-13','Pase de Equipo: ---- a Equipo : Mitre',73),(1654,0,'2003-04-15','Pase de Equipo: ---- a Equipo : La Picada',73),(1658,0,'2013-07-30','Pase de Equipo: ---- a Equipo : Luz y Fuerza',73),(1662,0,'1999-05-30','Pase de Equipo: ---- a Equipo : La Picada',73),(1666,0,'2005-08-20','Pase de Equipo: ---- a Equipo : CAPRI B',73),(1670,0,'2010-11-20','Pase de Equipo: ---- a Equipo : Crucero NERANJA',73),(1674,0,'2004-02-11','Pase de Equipo: ---- a Equipo : Cazadores ROJO',73),(1678,0,'2010-06-10','Pase de Equipo: ---- a Equipo : Pira Pyta Pota',73),(1682,0,'2013-12-10','Pase de Equipo: ---- a Equipo : Camara Legislativa',73),(1686,0,'2002-08-10','Pase de Equipo: ---- a Equipo : Unión A',73),(1690,0,'2014-03-24','Pase de Equipo: ---- a Equipo : CAPRI C',73),(1694,0,'1999-01-27','Pase de Equipo: ---- a Equipo : Cazadores AZUL',73),(1698,0,'1999-04-09','Pase de Equipo: ---- a Equipo : Guaraní',73),(1702,0,'2000-07-22','Pase de Equipo: ---- a Equipo : Ucraniano',73),(1706,0,'2010-04-30','Pase de Equipo: ---- a Equipo : Crucero AMARILLO',73),(1710,0,'2002-04-23','Pase de Equipo: ---- a Equipo : Crucero NERANJA',73),(1714,0,'2004-12-14','Pase de Equipo: ---- a Equipo : CAPRI B',73),(1718,0,'2008-06-14','Pase de Equipo: ---- a Equipo : Educación',73),(1722,0,'2010-08-08','Pase de Equipo: ---- a Equipo : Legislativo B',73),(1726,0,'2003-11-13','Pase de Equipo: ---- a Equipo : Camara Legislativa',73),(1730,0,'2010-02-16','Pase de Equipo: ---- a Equipo : CAPRI B',73),(1734,0,'1999-03-20','Pase de Equipo: ---- a Equipo : Tacurú',73),(1738,0,'2006-04-21','Pase de Equipo: ---- a Equipo : Camara Legislativa',73),(1742,0,'2006-06-14','Pase de Equipo: ---- a Equipo : Educación',73),(1746,0,'2009-05-26','Pase de Equipo: ---- a Equipo : Legislativo',73),(1750,0,'2006-02-18','Pase de Equipo: ---- a Equipo : Unión B',73),(1754,0,'2004-08-02','Pase de Equipo: ---- a Equipo : Legislativo B',73),(1758,0,'2010-02-21','Pase de Equipo: ---- a Equipo : Luz y Fuerza',73),(1762,0,'2008-07-24','Pase de Equipo: ---- a Equipo : Legislativo B',73),(1766,0,'2002-07-10','Pase de Equipo: ---- a Equipo : CAPRI B',73),(1770,0,'2010-09-07','Pase de Equipo: ---- a Equipo : Legislativo',73),(1774,0,'2001-04-13','Pase de Equipo: ---- a Equipo : Guaraní',73),(1778,0,'2011-11-22','Pase de Equipo: ---- a Equipo : Legislativo B',73),(1782,0,'2014-04-04','Pase de Equipo: ---- a Equipo : CAPRI B',73),(1786,0,'2012-02-26','Pase de Equipo: ---- a Equipo : Cazadores ROJO',73),(1790,0,'2011-05-27','Pase de Equipo: ---- a Equipo : CAPRI B',73),(1794,0,'2011-07-19','Pase de Equipo: ---- a Equipo : Crucero AMARILLO',73),(1798,0,'2000-10-05','Pase de Equipo: ---- a Equipo : Pira Pyta Pota',73),(1802,0,'2000-08-16','Pase de Equipo: ---- a Equipo : CAPRI A',73),(1806,0,'2013-06-26','Pase de Equipo: ---- a Equipo : Unión A',73),(1810,0,'2004-01-22','Pase de Equipo: ---- a Equipo : Cazadores AZUL',73),(1814,0,'2003-06-13','Pase de Equipo: ---- a Equipo : Educación',73),(1818,0,'1999-08-26','Pase de Equipo: ---- a Equipo : Educación',73),(1822,0,'2007-06-15','Pase de Equipo: ---- a Equipo : Pira Pyta Pota',73),(1826,0,'2004-09-03','Pase de Equipo: ---- a Equipo : Educación',73),(1830,0,'2010-08-07','Pase de Equipo: ---- a Equipo : Mitre',73),(1834,0,'2007-05-26','Pase de Equipo: ---- a Equipo : CAPRI C',73),(1838,0,'2011-07-15','Pase de Equipo: ---- a Equipo : Legislativo',73),(1842,0,'2008-02-11','Pase de Equipo: ---- a Equipo : Unión A',73),(1846,0,'2010-05-26','Pase de Equipo: ---- a Equipo : Unión A',73),(1850,0,'2010-05-04','Pase de Equipo: ---- a Equipo : Sol Legislativo',73),(1854,0,'2003-09-27','Pase de Equipo: ---- a Equipo : Unión A',73),(1858,0,'1999-11-10','Pase de Equipo: ---- a Equipo : Unión B',73),(1862,0,'2006-09-22','Pase de Equipo: ---- a Equipo : CAPRI A',73),(1866,0,'2007-02-02','Pase de Equipo: ---- a Equipo : Crucero AMARILLO',73),(1870,0,'2008-12-14','Pase de Equipo: ---- a Equipo : Legislativo',73),(1874,0,'2012-10-25','Pase de Equipo: ---- a Equipo : Unión B',73),(1878,0,'2000-05-16','Pase de Equipo: ---- a Equipo : Pira Pyta Pota',73),(1882,0,'2008-10-11','Pase de Equipo: ---- a Equipo : Tacurú',73),(1886,0,'2002-05-01','Pase de Equipo: ---- a Equipo : Cazadores ROJO',73),(1890,0,'2010-12-22','Pase de Equipo: ---- a Equipo : Cazadores A',73),(1894,0,'2012-10-05','Pase de Equipo: ---- a Equipo : Educación',73),(1898,0,'2001-03-05','Pase de Equipo: ---- a Equipo : Tacurú',73),(1902,0,'2010-10-24','Pase de Equipo: ---- a Equipo : CAPRI A',73),(1906,0,'2003-10-12','Pase de Equipo: ---- a Equipo : CAPRI A',73),(1910,0,'2009-08-01','Pase de Equipo: ---- a Equipo : Camionero',73),(1914,0,'1999-08-24','Pase de Equipo: ---- a Equipo : Pira Pyta Pota',73),(1918,0,'2011-07-18','Pase de Equipo: ---- a Equipo : Camara Legislativa',73),(1922,0,'2009-03-31','Pase de Equipo: ---- a Equipo : Cazadores A',73),(1926,0,'2006-02-11','Pase de Equipo: ---- a Equipo : Unión A',73),(1930,0,'2004-03-25','Pase de Equipo: ---- a Equipo : Cazadores ROJO',73),(1934,0,'2008-01-16','Pase de Equipo: ---- a Equipo : Luz y Fuerza',73),(1938,0,'2002-09-21','Pase de Equipo: ---- a Equipo : Unión B',73),(1942,0,'2003-03-16','Pase de Equipo: ---- a Equipo : Cazadores A',73),(1946,0,'2002-08-08','Pase de Equipo: ---- a Equipo : CAPRI C',73),(1950,0,'2012-11-09','Pase de Equipo: ---- a Equipo : Nuevo Legislativo',73),(1954,0,'2012-03-30','Pase de Equipo: ---- a Equipo : Ytá',73),(1958,0,'2007-07-19','Pase de Equipo: ---- a Equipo : CAPRI C',73),(1962,0,'1999-04-07','Pase de Equipo: ---- a Equipo : Camionero',73),(1966,0,'2007-12-03','Pase de Equipo: ---- a Equipo : Nuevo Legislativo',73),(1970,0,'2014-05-14','Pase de Equipo: ---- a Equipo : Legislativo',73),(1974,0,'2006-02-13','Pase de Equipo: ---- a Equipo : Pira Pyta Pota',73);
/*!40000 ALTER TABLE `deuda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deuda_cuota`
--

DROP TABLE IF EXISTS `deuda_cuota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deuda_cuota` (
  `Deuda_IDDEUDA` bigint(20) NOT NULL,
  `cuotas_IDCUOTA` bigint(20) NOT NULL,
  PRIMARY KEY (`Deuda_IDDEUDA`,`cuotas_IDCUOTA`),
  KEY `FK_DEUDA_CUOTA_cuotas_IDCUOTA` (`cuotas_IDCUOTA`),
  CONSTRAINT `FK_DEUDA_CUOTA_Deuda_IDDEUDA` FOREIGN KEY (`Deuda_IDDEUDA`) REFERENCES `deuda` (`IDDEUDA`),
  CONSTRAINT `FK_DEUDA_CUOTA_cuotas_IDCUOTA` FOREIGN KEY (`cuotas_IDCUOTA`) REFERENCES `cuota` (`IDCUOTA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deuda_cuota`
--

LOCK TABLES `deuda_cuota` WRITE;
/*!40000 ALTER TABLE `deuda_cuota` DISABLE KEYS */;
INSERT INTO `deuda_cuota` VALUES (478,476),(482,480),(486,484),(490,488),(494,492),(498,496),(502,500),(506,504),(510,508),(514,512),(518,516),(522,520),(526,524),(530,528),(534,532),(538,536),(542,540),(546,544),(550,548),(554,552),(558,556),(562,560),(566,564),(570,568),(574,572),(578,576),(582,580),(586,584),(590,588),(594,592),(598,596),(602,600),(606,604),(610,608),(614,612),(618,616),(622,620),(626,624),(630,628),(634,632),(638,636),(642,640),(646,644),(650,648),(654,652),(658,656),(662,660),(666,664),(670,668),(674,672),(678,676),(682,680),(686,684),(690,688),(694,692),(698,696),(702,700),(706,704),(710,708),(714,712),(718,716),(722,720),(726,724),(730,728),(734,732),(738,736),(742,740),(746,744),(750,748),(754,752),(758,756),(762,760),(766,764),(770,768),(774,772),(778,776),(782,780),(786,784),(790,788),(794,792),(798,796),(802,800),(806,804),(810,808),(814,812),(818,816),(822,820),(826,824),(830,828),(834,832),(838,836),(842,840),(846,844),(850,848),(854,852),(858,856),(862,860),(866,864),(870,868),(874,872),(878,876),(882,880),(886,884),(890,888),(894,892),(898,896),(902,900),(906,904),(910,908),(914,912),(918,916),(922,920),(926,924),(930,928),(934,932),(938,936),(942,940),(946,944),(950,948),(954,952),(958,956),(962,960),(966,964),(970,968),(974,972),(978,976),(982,980),(986,984),(990,988),(994,992),(998,996),(1002,1000),(1006,1004),(1010,1008),(1014,1012),(1018,1016),(1022,1020),(1026,1024),(1030,1028),(1034,1032),(1038,1036),(1042,1040),(1046,1044),(1050,1048),(1054,1052),(1058,1056),(1062,1060),(1066,1064),(1070,1068),(1074,1072),(1078,1076),(1082,1080),(1086,1084),(1090,1088),(1094,1092),(1098,1096),(1102,1100),(1106,1104),(1110,1108),(1114,1112),(1118,1116),(1122,1120),(1126,1124),(1130,1128),(1134,1132),(1138,1136),(1142,1140),(1146,1144),(1150,1148),(1154,1152),(1158,1156),(1162,1160),(1166,1164),(1170,1168),(1174,1172),(1178,1176),(1182,1180),(1186,1184),(1190,1188),(1194,1192),(1198,1196),(1202,1200),(1206,1204),(1210,1208),(1214,1212),(1218,1216),(1222,1220),(1226,1224),(1230,1228),(1234,1232),(1238,1236),(1242,1240),(1246,1244),(1250,1248),(1254,1252),(1258,1256),(1262,1260),(1266,1264),(1270,1268),(1274,1272),(1278,1276),(1282,1280),(1286,1284),(1290,1288),(1294,1292),(1298,1296),(1302,1300),(1306,1304),(1310,1308),(1314,1312),(1318,1316),(1322,1320),(1326,1324),(1330,1328),(1334,1332),(1338,1336),(1342,1340),(1346,1344),(1350,1348),(1354,1352),(1358,1356),(1362,1360),(1366,1364),(1370,1368),(1374,1372),(1378,1376),(1382,1380),(1386,1384),(1390,1388),(1394,1392),(1398,1396),(1402,1400),(1406,1404),(1410,1408),(1414,1412),(1418,1416),(1422,1420),(1426,1424),(1430,1428),(1434,1432),(1438,1436),(1442,1440),(1446,1444),(1450,1448),(1454,1452),(1458,1456),(1462,1460),(1466,1464),(1470,1468),(1474,1472),(1478,1476),(1482,1480),(1486,1484),(1490,1488),(1494,1492),(1498,1496),(1502,1500),(1506,1504),(1510,1508),(1514,1512),(1518,1516),(1522,1520),(1526,1524),(1530,1528),(1534,1532),(1538,1536),(1542,1540),(1546,1544),(1550,1548),(1554,1552),(1558,1556),(1562,1560),(1566,1564),(1570,1568),(1574,1572),(1578,1576),(1582,1580),(1586,1584),(1590,1588),(1594,1592),(1598,1596),(1602,1600),(1606,1604),(1610,1608),(1614,1612),(1618,1616),(1622,1620),(1626,1624),(1630,1628),(1634,1632),(1638,1636),(1642,1640),(1646,1644),(1650,1648),(1654,1652),(1658,1656),(1662,1660),(1666,1664),(1670,1668),(1674,1672),(1678,1676),(1682,1680),(1686,1684),(1690,1688),(1694,1692),(1698,1696),(1702,1700),(1706,1704),(1710,1708),(1714,1712),(1718,1716),(1722,1720),(1726,1724),(1730,1728),(1734,1732),(1738,1736),(1742,1740),(1746,1744),(1750,1748),(1754,1752),(1758,1756),(1762,1760),(1766,1764),(1770,1768),(1774,1772),(1778,1776),(1782,1780),(1786,1784),(1790,1788),(1794,1792),(1798,1796),(1802,1800),(1806,1804),(1810,1808),(1814,1812),(1818,1816),(1822,1820),(1826,1824),(1830,1828),(1834,1832),(1838,1836),(1842,1840),(1846,1844),(1850,1848),(1854,1852),(1858,1856),(1862,1860),(1866,1864),(1870,1868),(1874,1872),(1878,1876),(1882,1880),(1886,1884),(1890,1888),(1894,1892),(1898,1896),(1902,1900),(1906,1904),(1910,1908),(1914,1912),(1918,1916),(1922,1920),(1926,1924),(1930,1928),(1934,1932),(1938,1936),(1942,1940),(1946,1944),(1950,1948),(1954,1952),(1958,1956),(1962,1960),(1966,1964),(1970,1968),(1974,1972);
/*!40000 ALTER TABLE `deuda_cuota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `egreso`
--

DROP TABLE IF EXISTS `egreso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `egreso` (
  `IDEGRESO` bigint(20) NOT NULL,
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `FECHA` date DEFAULT NULL,
  `MONTO` double DEFAULT NULL,
  `OBSERVACION` varchar(1000) DEFAULT NULL,
  `UNCONCEPTOEGRESO_IDCONCEPTOEGRESO` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`IDEGRESO`),
  KEY `FK_EGRESO_UNCONCEPTOEGRESO_IDCONCEPTOEGRESO` (`UNCONCEPTOEGRESO_IDCONCEPTOEGRESO`),
  CONSTRAINT `FK_EGRESO_UNCONCEPTOEGRESO_IDCONCEPTOEGRESO` FOREIGN KEY (`UNCONCEPTOEGRESO_IDCONCEPTOEGRESO`) REFERENCES `conceptoegreso` (`IDCONCEPTOEGRESO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `egreso`
--

LOCK TABLES `egreso` WRITE;
/*!40000 ALTER TABLE `egreso` DISABLE KEYS */;
/*!40000 ALTER TABLE `egreso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo`
--

DROP TABLE IF EXISTS `equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipo` (
  `IDEQUIPO` bigint(20) NOT NULL,
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `NOMBRE` varchar(255) DEFAULT NULL,
  `UNAYUDANTECAMPO_DNI` bigint(20) DEFAULT NULL,
  `UNDT_DNI` bigint(20) DEFAULT NULL,
  `UNPREPARADORFISICO_DNI` bigint(20) DEFAULT NULL,
  `UNACAPITANA_DNI` bigint(20) DEFAULT NULL,
  `UNACAPITANASUPLENTE_DNI` bigint(20) DEFAULT NULL,
  `UNADELEGADA_DNI` bigint(20) DEFAULT NULL,
  `UNADELEGADASUPLENTE_DNI` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`IDEQUIPO`),
  KEY `FK_EQUIPO_UNADELEGADASUPLENTE_DNI` (`UNADELEGADASUPLENTE_DNI`),
  KEY `FK_EQUIPO_UNADELEGADA_DNI` (`UNADELEGADA_DNI`),
  KEY `FK_EQUIPO_UNDT_DNI` (`UNDT_DNI`),
  KEY `FK_EQUIPO_UNPREPARADORFISICO_DNI` (`UNPREPARADORFISICO_DNI`),
  KEY `FK_EQUIPO_UNACAPITANA_DNI` (`UNACAPITANA_DNI`),
  KEY `FK_EQUIPO_UNAYUDANTECAMPO_DNI` (`UNAYUDANTECAMPO_DNI`),
  KEY `FK_EQUIPO_UNACAPITANASUPLENTE_DNI` (`UNACAPITANASUPLENTE_DNI`),
  CONSTRAINT `FK_EQUIPO_UNACAPITANASUPLENTE_DNI` FOREIGN KEY (`UNACAPITANASUPLENTE_DNI`) REFERENCES `socia` (`DNI`),
  CONSTRAINT `FK_EQUIPO_UNACAPITANA_DNI` FOREIGN KEY (`UNACAPITANA_DNI`) REFERENCES `socia` (`DNI`),
  CONSTRAINT `FK_EQUIPO_UNADELEGADASUPLENTE_DNI` FOREIGN KEY (`UNADELEGADASUPLENTE_DNI`) REFERENCES `socia` (`DNI`),
  CONSTRAINT `FK_EQUIPO_UNADELEGADA_DNI` FOREIGN KEY (`UNADELEGADA_DNI`) REFERENCES `socia` (`DNI`),
  CONSTRAINT `FK_EQUIPO_UNAYUDANTECAMPO_DNI` FOREIGN KEY (`UNAYUDANTECAMPO_DNI`) REFERENCES `personaauxiliar` (`DNI`),
  CONSTRAINT `FK_EQUIPO_UNDT_DNI` FOREIGN KEY (`UNDT_DNI`) REFERENCES `personaauxiliar` (`DNI`),
  CONSTRAINT `FK_EQUIPO_UNPREPARADORFISICO_DNI` FOREIGN KEY (`UNPREPARADORFISICO_DNI`) REFERENCES `personaauxiliar` (`DNI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo`
--

LOCK TABLES `equipo` WRITE;
/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
INSERT INTO `equipo` VALUES (1,0,'Camionero',NULL,29665741,NULL,NULL,NULL,NULL,NULL),(2,0,'CAPRI A',NULL,30251132,NULL,NULL,NULL,NULL,NULL),(3,0,'CAPRI B',NULL,28745663,NULL,NULL,NULL,NULL,NULL),(4,0,'CAPRI C',NULL,24558997,NULL,NULL,NULL,NULL,NULL),(5,0,'Cazadores A',NULL,27633987,NULL,NULL,NULL,NULL,NULL),(6,0,'Cazadores AZUL',NULL,28996332,NULL,NULL,NULL,NULL,NULL),(7,0,'Cazadores ROJO',NULL,28996333,NULL,NULL,NULL,NULL,NULL),(8,0,'Crucero AMARILLO',NULL,28996334,NULL,NULL,NULL,NULL,NULL),(9,0,'Crucero NERANJA',NULL,28996335,NULL,NULL,NULL,NULL,NULL),(10,0,'Educación',NULL,28996336,NULL,NULL,NULL,NULL,NULL),(11,0,'Guaraní',NULL,28996337,NULL,NULL,NULL,NULL,NULL),(12,0,'La Picada',NULL,28996338,NULL,NULL,NULL,NULL,NULL),(13,0,'Legislativo',NULL,28996339,NULL,NULL,NULL,NULL,NULL),(14,0,'Nuevo Legislativo',NULL,28996340,NULL,NULL,NULL,NULL,NULL),(15,0,'Sol Legislativo',NULL,28996341,NULL,NULL,NULL,NULL,NULL),(16,0,'Camara Legislativa',NULL,28996342,NULL,NULL,NULL,NULL,NULL),(17,0,'Legislativo B',NULL,28996343,NULL,NULL,NULL,NULL,NULL),(18,0,'Luz y Fuerza',NULL,28996344,NULL,NULL,NULL,NULL,NULL),(19,0,'Mitre',NULL,28996345,NULL,NULL,NULL,NULL,NULL),(20,0,'Pira Pyta Pota',NULL,28996346,NULL,NULL,NULL,NULL,NULL),(21,0,'Tacurú',NULL,28996347,NULL,NULL,NULL,NULL,NULL),(22,0,'Ucraniano',NULL,28996348,NULL,NULL,NULL,NULL,NULL),(23,0,'Unión A',NULL,28996349,NULL,NULL,NULL,NULL,NULL),(24,0,'Unión B',NULL,28996350,NULL,NULL,NULL,NULL,NULL),(25,0,'Ytá',NULL,28996351,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo_deuda`
--

DROP TABLE IF EXISTS `equipo_deuda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipo_deuda` (
  `Equipo_IDEQUIPO` bigint(20) NOT NULL,
  `deudas_IDDEUDA` bigint(20) NOT NULL,
  PRIMARY KEY (`Equipo_IDEQUIPO`,`deudas_IDDEUDA`),
  KEY `FK_EQUIPO_DEUDA_deudas_IDDEUDA` (`deudas_IDDEUDA`),
  CONSTRAINT `FK_EQUIPO_DEUDA_Equipo_IDEQUIPO` FOREIGN KEY (`Equipo_IDEQUIPO`) REFERENCES `equipo` (`IDEQUIPO`),
  CONSTRAINT `FK_EQUIPO_DEUDA_deudas_IDDEUDA` FOREIGN KEY (`deudas_IDDEUDA`) REFERENCES `deuda` (`IDDEUDA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo_deuda`
--

LOCK TABLES `equipo_deuda` WRITE;
/*!40000 ALTER TABLE `equipo_deuda` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipo_deuda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo_indumentaria`
--

DROP TABLE IF EXISTS `equipo_indumentaria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipo_indumentaria` (
  `Equipo_IDEQUIPO` bigint(20) NOT NULL,
  `indumentarias_IDINDUMENTARIA` bigint(20) NOT NULL,
  PRIMARY KEY (`Equipo_IDEQUIPO`,`indumentarias_IDINDUMENTARIA`),
  KEY `EQUIPO_INDUMENTARIA_indumentarias_IDINDUMENTARIA` (`indumentarias_IDINDUMENTARIA`),
  CONSTRAINT `EQUIPO_INDUMENTARIA_indumentarias_IDINDUMENTARIA` FOREIGN KEY (`indumentarias_IDINDUMENTARIA`) REFERENCES `indumentaria` (`IDINDUMENTARIA`),
  CONSTRAINT `FK_EQUIPO_INDUMENTARIA_Equipo_IDEQUIPO` FOREIGN KEY (`Equipo_IDEQUIPO`) REFERENCES `equipo` (`IDEQUIPO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo_indumentaria`
--

LOCK TABLES `equipo_indumentaria` WRITE;
/*!40000 ALTER TABLE `equipo_indumentaria` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipo_indumentaria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo_planillapago`
--

DROP TABLE IF EXISTS `equipo_planillapago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipo_planillapago` (
  `Equipo_IDEQUIPO` bigint(20) NOT NULL,
  `planillasPagos_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`Equipo_IDEQUIPO`,`planillasPagos_ID`),
  KEY `FK_EQUIPO_PLANILLAPAGO_planillasPagos_ID` (`planillasPagos_ID`),
  CONSTRAINT `FK_EQUIPO_PLANILLAPAGO_Equipo_IDEQUIPO` FOREIGN KEY (`Equipo_IDEQUIPO`) REFERENCES `equipo` (`IDEQUIPO`),
  CONSTRAINT `FK_EQUIPO_PLANILLAPAGO_planillasPagos_ID` FOREIGN KEY (`planillasPagos_ID`) REFERENCES `planillapago` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo_planillapago`
--

LOCK TABLES `equipo_planillapago` WRITE;
/*!40000 ALTER TABLE `equipo_planillapago` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipo_planillapago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo_sanciontribunal`
--

DROP TABLE IF EXISTS `equipo_sanciontribunal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipo_sanciontribunal` (
  `Equipo_IDEQUIPO` bigint(20) NOT NULL,
  `sancionesTribunal_IDSANCIONTRIBUNAL` bigint(20) NOT NULL,
  PRIMARY KEY (`Equipo_IDEQUIPO`,`sancionesTribunal_IDSANCIONTRIBUNAL`),
  KEY `QPOSANCIONTRIBUNALsnconesTribunalIDSANCIONTRIBUNAL` (`sancionesTribunal_IDSANCIONTRIBUNAL`),
  CONSTRAINT `FK_EQUIPO_SANCIONTRIBUNAL_Equipo_IDEQUIPO` FOREIGN KEY (`Equipo_IDEQUIPO`) REFERENCES `equipo` (`IDEQUIPO`),
  CONSTRAINT `QPOSANCIONTRIBUNALsnconesTribunalIDSANCIONTRIBUNAL` FOREIGN KEY (`sancionesTribunal_IDSANCIONTRIBUNAL`) REFERENCES `sanciontribunal` (`IDSANCIONTRIBUNAL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo_sanciontribunal`
--

LOCK TABLES `equipo_sanciontribunal` WRITE;
/*!40000 ALTER TABLE `equipo_sanciontribunal` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipo_sanciontribunal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo_socia`
--

DROP TABLE IF EXISTS `equipo_socia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipo_socia` (
  `Equipo_IDEQUIPO` bigint(20) NOT NULL,
  `plantel_DNI` bigint(20) NOT NULL,
  PRIMARY KEY (`Equipo_IDEQUIPO`,`plantel_DNI`),
  KEY `FK_EQUIPO_SOCIA_plantel_DNI` (`plantel_DNI`),
  CONSTRAINT `FK_EQUIPO_SOCIA_Equipo_IDEQUIPO` FOREIGN KEY (`Equipo_IDEQUIPO`) REFERENCES `equipo` (`IDEQUIPO`),
  CONSTRAINT `FK_EQUIPO_SOCIA_plantel_DNI` FOREIGN KEY (`plantel_DNI`) REFERENCES `socia` (`DNI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo_socia`
--

LOCK TABLES `equipo_socia` WRITE;
/*!40000 ALTER TABLE `equipo_socia` DISABLE KEYS */;
INSERT INTO `equipo_socia` VALUES (11,5917598),(13,6074300),(17,10534228),(2,10639418),(1,10955361),(2,11392148),(22,11399982),(20,11479299),(14,11479449),(16,11635309),(17,11661464),(24,11694515),(4,12296490),(9,12637259),(18,12637417),(1,12852501),(22,12898395),(11,12956647),(19,13004758),(24,13005252),(24,13005781),(25,13005891),(11,13005993),(14,13013763),(10,13248833),(24,13350813),(1,13421303),(10,13558172),(12,13658522),(25,13867641),(14,13897862),(3,13957943),(19,14181471),(10,14194183),(18,14209322),(17,14258039),(8,14258752),(20,14301412),(13,14335266),(6,14335484),(23,14469013),(10,14551343),(19,14713298),(21,14745833),(8,14745996),(9,14826658),(4,14826696),(1,14911492),(13,14926497),(10,14946666),(16,14946893),(20,14957513),(11,14960101),(24,14986595),(7,16163272),(4,16205157),(18,16356400),(19,16365101),(21,16365171),(12,16365251),(4,16367471),(10,16563703),(24,16650419),(14,16695422),(12,16695464),(13,16695503),(14,16695780),(21,16696948),(17,16829403),(22,16853984),(17,16871076),(14,16944749),(18,16986200),(22,16993353),(16,17039784),(7,17064155),(2,17093025),(22,17135365),(2,17135398),(23,17164587),(15,17170689),(6,17170744),(25,17216632),(6,17299811),(5,17312131),(10,17369473),(11,17378032),(15,17525644),(1,17525767),(23,17562547),(23,17562559),(3,17604599),(10,17630446),(6,17652488),(6,17675809),(10,17751922),(23,17760877),(19,17760927),(24,17773508),(13,17814471),(25,17831378),(2,17905182),(20,17952248),(23,17980404),(20,17980484),(25,18063802),(13,18095622),(10,18095845),(4,18180848),(15,18265145),(20,18290431),(12,18308518),(21,18438573),(8,18464360),(4,18465156),(19,18546184),(16,18683582),(6,18694386),(2,18701304),(11,18767528),(22,18776248),(6,18793716),(25,18801405),(6,20117775),(22,20117813),(12,20175929),(14,20186811),(22,20290582),(21,20338247),(13,20338614),(9,20338642),(12,20476201),(14,20484881),(10,20495953),(20,20500992),(23,20518423),(4,20545757),(19,20578722),(25,20629246),(12,20737427),(10,20815489),(15,20899640),(13,20907159),(7,20939295),(10,21180225),(20,21182875),(18,21300349),(19,21300428),(20,21301810),(15,21302821),(6,21303869),(12,21304070),(23,21305751),(7,21365232),(9,21546890),(22,21562507),(18,21598339),(8,21634884),(14,21660785),(15,21684508),(4,21723344),(18,21723721),(3,21775640),(23,21781027),(2,21781619),(1,21781681),(9,21781725),(9,21793705),(24,21974458),(19,21976884),(5,22040318),(6,22090129),(24,22090330),(10,22090333),(6,22090400),(3,22141132),(5,22141155),(12,22351819),(18,22351900),(3,22352199),(1,22485728),(23,22488411),(12,22488625),(6,22488745),(8,22508922),(8,22539277),(14,22662681),(23,22692609),(20,22737993),(1,22738113),(25,22814059),(8,22835570),(22,22835678),(7,22835931),(5,22836855),(20,22870686),(5,22944957),(7,23035827),(12,23066520),(18,23096336),(2,23096644),(18,23170837),(5,23207943),(24,23338376),(8,23349910),(11,23383304),(3,23383428),(10,23430610),(12,23468003),(19,23482663),(15,23546861),(21,23675117),(23,23675136),(22,23675148),(1,23675260),(22,23675431),(4,23690040),(16,23690119),(16,23737897),(1,23900628),(19,23901844),(20,23910719),(14,23951159),(9,23951555),(12,23951570),(14,24076719),(19,24090590),(22,24118105),(3,24130561),(19,24130661),(2,24131387),(11,24143117),(5,24143256),(16,24294655),(15,24294858),(10,24389202),(18,24485566),(14,24509951),(23,24516692),(2,24572255),(19,24572310),(20,24573249),(23,24573403),(23,24573604),(19,24600324),(3,24601104),(7,24601250),(8,24601563),(3,24601655),(3,24601704),(24,24644783),(3,24679829),(25,24679887),(1,24709560),(2,24769433),(20,24835945),(7,24865425),(11,24903025),(12,24932200),(12,24937904),(12,24985441),(3,24985506),(1,24989460),(20,25019526),(4,25066453),(19,25159104),(12,25192099),(10,25201554),(2,25231594),(20,25313007),(23,25316907),(14,25322125),(21,25340444),(4,25428447),(19,25450204),(19,25450265),(21,25489363),(10,25489484),(6,25535836),(17,25758210),(9,25767251),(1,25774560),(6,25860834),(17,25894270),(4,25895655),(20,25985040),(25,26082506),(5,26286013),(5,26286171),(10,26292240),(18,26292622),(8,26425752),(12,26447307),(19,26546441),(12,26578164),(18,26606894),(12,26623490),(3,26754871),(9,26776185),(7,26776882),(20,26849016),(16,26853222),(23,27054164),(4,27116817),(6,27200223),(11,27205305),(22,27221986),(8,27233372),(9,27233664),(3,27257937),(10,27366789),(17,27433794),(16,27433797),(3,27456218),(21,27456856),(16,27470356),(10,27504424),(13,27554105),(24,27574610),(17,27650131),(18,27685910),(17,27800047),(3,27800068),(13,27979166),(11,27979343),(17,27999936),(3,28017815),(7,28065205),(3,28065478),(8,28084616),(20,28118896),(2,28246117),(23,28466304),(6,28513597),(10,28552811),(10,28589963),(20,28610740),(10,28818364),(19,28832531),(4,28903354),(13,29008677),(23,29054393),(23,29241507),(15,29325180),(23,29411528),(24,29431996),(2,29441092),(8,29455785),(13,29596937),(24,29671618),(20,29671701),(21,29671992),(7,29698224),(5,29806907),(10,29806908),(21,30003106),(2,30084028),(2,30165274),(1,30255272),(20,30297834),(16,30362346),(5,30396924),(23,30398079),(7,30619550),(18,30687895),(24,30790108),(5,30790455),(4,30846541),(14,30935057),(25,33012397),(4,36408906),(1,92255489),(14,92452168),(13,93512269),(20,93997742);
/*!40000 ALTER TABLE `equipo_socia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ergometria`
--

DROP TABLE IF EXISTS `ergometria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ergometria` (
  `IDERGOMETRIA` bigint(20) NOT NULL,
  `APROBADO` tinyint(1) DEFAULT '0',
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `COMENTARIOS` varchar(1000) DEFAULT NULL,
  `FECHACADUCIDAD` date DEFAULT NULL,
  `FECHAREALIZACION` date DEFAULT NULL,
  PRIMARY KEY (`IDERGOMETRIA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ergometria`
--

LOCK TABLES `ergometria` WRITE;
/*!40000 ALTER TABLE `ergometria` DISABLE KEYS */;
INSERT INTO `ergometria` VALUES (1976,1,0,'','2019-05-20','2010-02-02'),(1977,1,0,'','2019-05-20','2002-03-01'),(1978,1,0,'','2019-05-20','2010-03-17'),(1979,1,0,'','2019-05-20','2004-05-31'),(1980,1,0,'','2019-05-20','2008-09-03'),(1981,1,0,'','2019-05-20','2009-07-30'),(1982,1,0,'','2019-05-20','2005-10-05'),(1983,1,0,'','2019-05-20','2005-08-27'),(1984,1,0,'','2019-05-20','1999-01-27'),(1985,1,0,'','2019-05-20','2006-06-19'),(1986,1,0,'','2019-05-20','1999-04-09'),(1987,1,0,'','2019-05-20','2012-06-14'),(1988,1,0,'','2019-05-20','2004-06-29'),(1989,1,0,'','2019-05-20','2013-03-13'),(1990,1,0,'','2019-05-20','2012-02-07'),(1991,1,0,'','2019-05-20','2014-01-25'),(1992,0,0,'','2019-05-20','2000-12-29'),(1993,1,0,'','2019-05-20','2013-05-16'),(1994,1,0,'','2019-05-20','2006-09-09'),(1995,1,0,'','2019-05-20','2002-02-20'),(1996,1,0,'','2019-05-20','2001-11-21'),(1997,1,0,'','2019-05-20','2007-10-20'),(1998,1,0,'','2019-05-20','2008-05-17'),(1999,1,0,'','2019-05-20','2012-11-01'),(2000,1,0,'','2019-05-20','1999-06-25'),(2001,1,0,'','2019-05-20','2000-10-20'),(2002,0,0,'','2019-05-20','2011-12-07'),(2003,1,0,'','2019-05-20','2003-06-01'),(2004,1,0,'','2019-05-20','2002-02-05'),(2005,1,0,'','2019-05-20','2011-05-26'),(2006,1,0,'','2019-05-20','2014-02-24'),(2007,1,0,'','2019-05-20','2004-10-13'),(2008,1,0,'','2019-05-20','2005-07-09'),(2009,0,0,'','2019-05-20','2014-06-21'),(2010,1,0,'','2019-05-20','2008-11-29'),(2011,1,0,'','2019-05-20','2010-01-13'),(2012,1,0,'','2019-05-20','2013-03-25'),(2013,1,0,'','2019-05-20','2001-11-25'),(2014,1,0,'','2019-05-20','1999-07-09'),(2015,1,0,'','2019-05-20','2013-04-10'),(2016,1,0,'','2019-05-20','2013-01-04'),(2017,1,0,'','2019-05-20','2006-11-08'),(2018,1,0,'','2019-05-20','2001-10-12'),(2019,1,0,'','2019-05-20','2012-08-27'),(2020,1,0,'','2019-05-20','2002-09-20'),(2021,1,0,'','2019-05-20','2007-01-26'),(2022,1,0,'','2019-05-20','2014-04-07'),(2023,0,0,'','2019-05-20','2008-02-12'),(2024,1,0,'','2019-05-20','2010-05-29'),(2025,0,0,'','2019-05-20','1999-04-23'),(2026,0,0,'','2019-05-20','2009-05-27'),(2027,1,0,'','2019-05-20','2011-02-13'),(2028,0,0,'','2019-05-20','2008-12-01'),(2029,1,0,'','2019-05-20','2002-09-20'),(2030,1,0,'','2019-05-20','2000-08-29'),(2031,1,0,'','2019-05-20','2008-03-20'),(2032,1,0,'','2019-05-20','2011-10-10'),(2033,1,0,'','2019-05-20','2010-12-17'),(2034,1,0,'','2019-05-20','2010-04-18'),(2035,0,0,'','2019-05-20','1999-03-25'),(2036,1,0,'','2019-05-20','2014-02-03'),(2037,1,0,'','2019-05-20','2005-03-26'),(2038,1,0,'','2019-05-20','2007-08-18'),(2039,1,0,'','2019-05-20','2004-09-25'),(2040,1,0,'','2019-05-20','2010-05-17'),(2041,1,0,'','2019-05-20','2003-02-10'),(2042,1,0,'','2019-05-20','2000-04-11'),(2043,0,0,'','2019-05-20','2010-06-06'),(2044,1,0,'','2019-05-20','2008-09-18'),(2045,1,0,'','2019-05-20','2005-03-13'),(2046,1,0,'','2019-05-20','2008-07-07'),(2047,1,0,'','2019-05-20','2003-09-07'),(2048,1,0,'','2019-05-20','2000-12-13'),(2049,1,0,'','2019-05-20','2005-07-22'),(2050,1,0,'','2019-05-20','2001-03-02'),(2051,1,0,'','2019-05-20','2004-12-23'),(2052,1,0,'','2019-05-20','2012-05-30'),(2053,1,0,'','2019-05-20','2010-04-01'),(2054,1,0,'','2019-05-20','2010-02-05'),(2055,1,0,'','2019-05-20','2001-10-12'),(2056,0,0,'','2019-05-20','2012-04-11'),(2057,1,0,'','2019-05-20','2010-09-16'),(2058,1,0,'','2019-05-20','2004-07-20'),(2059,1,0,'','2019-05-20','2006-09-22'),(2060,1,0,'','2019-05-20','2006-12-03'),(2061,1,0,'','2019-05-20','2003-05-02'),(2062,1,0,'','2019-05-20','2001-04-16'),(2063,1,0,'','2019-05-20','2004-03-04'),(2064,0,0,'','2019-05-20','2000-12-08'),(2065,1,0,'','2019-05-20','1999-03-14'),(2066,0,0,'','2019-05-20','2013-02-17'),(2067,1,0,'','2019-05-20','2000-03-28'),(2068,1,0,'','2019-05-20','2000-04-23'),(2069,1,0,'','2019-05-20','2003-07-26'),(2070,1,0,'','2019-05-20','2007-05-12'),(2071,1,0,'','2019-05-20','2000-09-10'),(2072,1,0,'','2019-05-20','2011-01-30'),(2073,0,0,'','2019-05-20','2012-07-03'),(2074,1,0,'','2019-05-20','1999-09-12'),(2075,1,0,'','2019-05-20','2001-05-05'),(2076,1,0,'','2019-05-20','2010-12-08'),(2077,1,0,'','2019-05-20','2003-12-17'),(2078,1,0,'','2019-05-20','2003-04-12'),(2079,0,0,'','2019-05-20','2002-01-02'),(2080,1,0,'','2019-05-20','2008-12-12'),(2081,1,0,'','2019-05-20','2010-07-14'),(2082,0,0,'','2019-05-20','2003-06-10'),(2083,1,0,'','2019-05-20','2011-07-31'),(2084,1,0,'','2019-05-20','2013-05-10'),(2085,1,0,'','2019-05-20','2000-08-18'),(2086,1,0,'','2019-05-20','2010-12-19'),(2087,0,0,'','2019-05-20','2009-12-01'),(2088,1,0,'','2019-05-20','2001-03-26'),(2089,1,0,'','2019-05-20','2004-06-02'),(2090,1,0,'','2019-05-20','2002-12-13'),(2091,1,0,'','2019-05-20','2002-11-23'),(2092,1,0,'','2019-05-20','2010-11-30'),(2093,0,0,'','2019-05-20','2006-08-09'),(2094,1,0,'','2019-05-20','2009-07-17'),(2095,1,0,'','2019-05-20','2010-04-13'),(2096,1,0,'','2019-05-20','2010-09-18'),(2097,1,0,'','2019-05-20','2010-02-12'),(2098,1,0,'','2019-05-20','2006-09-27'),(2099,1,0,'','2019-05-20','2002-10-31'),(2100,1,0,'','2019-05-20','2013-04-30'),(2101,1,0,'','2019-05-20','2002-08-27'),(2102,0,0,'','2019-05-20','2009-07-26'),(2103,1,0,'','2019-05-20','2006-01-28'),(2104,1,0,'','2019-05-20','2010-04-19'),(2105,1,0,'','2019-05-20','2010-10-30'),(2106,1,0,'','2019-05-20','2003-06-27'),(2107,1,0,'','2019-05-20','2009-11-21'),(2108,1,0,'','2019-05-20','2009-11-12'),(2109,0,0,'','2019-05-20','2002-10-24'),(2110,1,0,'','2019-05-20','2002-12-14'),(2111,1,0,'','2019-05-20','2006-04-13'),(2112,1,0,'','2019-05-20','2012-12-20'),(2113,1,0,'','2019-05-20','2005-08-21'),(2114,0,0,'','2019-05-20','2005-08-12'),(2115,1,0,'','2019-05-20','2009-11-07'),(2116,1,0,'','2019-05-20','2002-04-14'),(2117,1,0,'','2019-05-20','1999-01-26'),(2118,1,0,'','2019-05-20','2005-11-23'),(2119,0,0,'','2019-05-20','2001-05-17'),(2120,1,0,'','2019-05-20','2006-12-03'),(2121,0,0,'','2019-05-20','2007-09-25'),(2122,1,0,'','2019-05-20','1999-06-18'),(2123,1,0,'','2019-05-20','2013-08-23'),(2124,1,0,'','2019-05-20','1999-10-14'),(2125,0,0,'','2019-05-20','1999-07-19'),(2126,1,0,'','2019-05-20','2012-05-19'),(2127,1,0,'','2019-05-20','2000-04-26'),(2128,1,0,'','2019-05-20','2008-05-06'),(2129,1,0,'','2019-05-20','2006-12-21'),(2130,1,0,'','2019-05-20','1999-07-28'),(2131,1,0,'','2019-05-20','2014-02-15'),(2132,1,0,'','2019-05-20','2003-10-31'),(2133,1,0,'','2019-05-20','2006-07-08'),(2134,1,0,'','2019-05-20','2005-01-05'),(2135,1,0,'','2019-05-20','2011-01-22'),(2136,1,0,'','2019-05-20','2009-03-09'),(2137,1,0,'','2019-05-20','2001-09-25'),(2138,1,0,'','2019-05-20','2009-02-12'),(2139,1,0,'','2019-05-20','2004-07-20'),(2140,0,0,'','2019-05-20','2002-05-21'),(2141,1,0,'','2019-05-20','2007-07-18'),(2142,1,0,'','2019-05-20','2013-03-13'),(2143,1,0,'','2019-05-20','2006-12-23'),(2144,1,0,'','2019-05-20','1999-03-03'),(2145,1,0,'','2019-05-20','1999-05-14'),(2146,1,0,'','2019-05-20','2006-12-06'),(2147,1,0,'','2019-05-20','2011-06-12'),(2148,1,0,'','2019-05-20','2004-02-04'),(2149,1,0,'','2019-05-20','2012-06-15'),(2150,1,0,'','2019-05-20','2005-08-27'),(2151,1,0,'','2019-05-20','2003-05-02'),(2152,1,0,'','2019-05-20','2007-02-13'),(2153,1,0,'','2019-05-20','2004-09-13'),(2154,1,0,'','2019-05-20','2003-04-26'),(2155,1,0,'','2019-05-20','2007-12-18'),(2156,1,0,'','2019-05-20','2012-01-15'),(2157,1,0,'','2019-05-20','2004-12-05'),(2158,0,0,'','2019-05-20','2007-05-28'),(2159,0,0,'','2019-05-20','2003-10-13'),(2160,1,0,'','2019-05-20','2012-09-05'),(2161,1,0,'','2019-05-20','2001-02-21'),(2162,1,0,'','2019-05-20','2002-09-11'),(2163,1,0,'','2019-05-20','2004-08-12'),(2164,1,0,'','2019-05-20','2001-08-27'),(2165,1,0,'','2019-05-20','2011-06-09'),(2166,1,0,'','2019-05-20','2004-03-23'),(2167,0,0,'','2019-05-20','2001-04-08'),(2168,1,0,'','2019-05-20','2013-01-17'),(2169,1,0,'','2019-05-20','2004-10-10'),(2170,1,0,'','2019-05-20','2010-09-06'),(2171,1,0,'','2019-05-20','2013-12-07'),(2172,1,0,'','2019-05-20','2001-01-17'),(2173,1,0,'','2019-05-20','2007-03-01'),(2174,1,0,'','2019-05-20','2003-03-11'),(2175,1,0,'','2019-05-20','2013-04-02'),(2176,1,0,'','2019-05-20','2005-10-20'),(2177,1,0,'','2019-05-20','2009-12-22'),(2178,1,0,'','2019-05-20','2001-07-20'),(2179,1,0,'','2019-05-20','2002-05-03'),(2180,1,0,'','2019-05-20','1999-09-02'),(2181,1,0,'','2019-05-20','2009-07-22'),(2182,1,0,'','2019-05-20','2012-06-19'),(2183,1,0,'','2019-05-20','2004-05-07'),(2184,1,0,'','2019-05-20','1999-11-07'),(2185,1,0,'','2019-05-20','2007-09-26'),(2186,1,0,'','2019-05-20','2014-05-14'),(2187,1,0,'','2019-05-20','2006-02-04'),(2188,1,0,'','2019-05-20','2006-06-17'),(2189,1,0,'','2019-05-20','2014-07-26'),(2190,1,0,'','2019-05-20','2009-01-13'),(2191,0,0,'','2019-05-20','2006-07-22'),(2192,1,0,'','2019-05-20','2000-05-07'),(2193,1,0,'','2019-05-20','2012-05-06'),(2194,1,0,'','2019-05-20','2000-04-03'),(2195,1,0,'','2019-05-20','2005-04-06'),(2196,1,0,'','2019-05-20','2010-01-22'),(2197,1,0,'','2019-05-20','1999-12-28'),(2198,1,0,'','2019-05-20','2006-10-12'),(2199,1,0,'','2019-05-20','2009-08-08'),(2200,1,0,'','2019-05-20','2012-01-14'),(2201,1,0,'','2019-05-20','2010-01-11'),(2202,1,0,'','2019-05-20','2014-01-11'),(2203,0,0,'','2019-05-20','2006-04-03'),(2204,1,0,'','2019-05-20','2003-10-20'),(2205,1,0,'','2019-05-20','2008-03-12'),(2206,1,0,'','2019-05-20','2012-04-23'),(2207,1,0,'','2019-05-20','2011-10-09'),(2208,1,0,'','2019-05-20','1999-06-20'),(2209,1,0,'','2019-05-20','2000-04-24'),(2210,1,0,'','2019-05-20','2014-05-15'),(2211,0,0,'','2019-05-20','2011-12-12'),(2212,1,0,'','2019-05-20','1999-05-22'),(2213,1,0,'','2019-05-20','2005-03-22'),(2214,1,0,'','2019-05-20','2006-07-19'),(2215,1,0,'','2019-05-20','2003-03-15'),(2216,1,0,'','2019-05-20','2010-04-17'),(2217,1,0,'','2019-05-20','2000-12-20'),(2218,1,0,'','2019-05-20','2014-03-23'),(2219,0,0,'','2019-05-20','2004-09-03'),(2220,1,0,'','2019-05-20','2000-06-11'),(2221,1,0,'','2019-05-20','2004-02-15'),(2222,1,0,'','2019-05-20','2002-05-24'),(2223,1,0,'','2019-05-20','2008-02-22'),(2224,1,0,'','2019-05-20','2001-06-14'),(2225,0,0,'','2019-05-20','2011-11-12'),(2226,1,0,'','2019-05-20','2005-06-03'),(2227,0,0,'','2019-05-20','2004-10-12'),(2228,1,0,'','2019-05-20','2010-07-01'),(2229,0,0,'','2019-05-20','2003-06-26'),(2230,1,0,'','2019-05-20','2010-04-04'),(2231,1,0,'','2019-05-20','2009-02-04'),(2232,1,0,'','2019-05-20','2001-03-29'),(2233,1,0,'','2019-05-20','2007-02-21'),(2234,1,0,'','2019-05-20','2001-07-28'),(2235,1,0,'','2019-05-20','2007-11-21'),(2236,0,0,'','2019-05-20','2002-10-30'),(2237,1,0,'','2019-05-20','2005-12-08'),(2238,1,0,'','2019-05-20','2014-03-05'),(2239,1,0,'','2019-05-20','2010-11-08'),(2240,1,0,'','2019-05-20','2007-03-10'),(2241,1,0,'','2019-05-20','2013-04-20'),(2242,1,0,'','2019-05-20','2001-07-17'),(2243,1,0,'','2019-05-20','2001-10-27'),(2244,0,0,'','2019-05-20','2008-07-30'),(2245,1,0,'','2019-05-20','2005-02-28'),(2246,1,0,'','2019-05-20','2006-05-27'),(2247,1,0,'','2019-05-20','2009-06-18'),(2248,1,0,'','2019-05-20','2009-09-12'),(2249,1,0,'','2019-05-20','2000-03-31'),(2250,1,0,'','2019-05-20','2002-01-15'),(2251,1,0,'','2019-05-20','2004-05-18'),(2252,1,0,'','2019-05-20','2003-11-01'),(2253,1,0,'','2019-05-20','2011-05-20'),(2254,1,0,'','2019-05-20','2008-07-07'),(2255,1,0,'','2019-05-20','2009-05-24'),(2256,1,0,'','2019-05-20','1999-10-30'),(2257,1,0,'','2019-05-20','2001-05-29'),(2258,1,0,'','2019-05-20','2012-10-14'),(2259,1,0,'','2019-05-20','2000-12-06'),(2260,1,0,'','2019-05-20','2000-02-16'),(2261,1,0,'','2019-05-20','2009-03-13'),(2262,1,0,'','2019-05-20','2014-05-04'),(2263,0,0,'','2019-05-20','2010-04-12'),(2264,1,0,'','2019-05-20','2005-04-24'),(2265,1,0,'','2019-05-20','2005-04-18'),(2266,1,0,'','2019-05-20','2000-12-01'),(2267,1,0,'','2019-05-20','2001-01-01'),(2268,1,0,'','2019-05-20','2005-09-24'),(2269,1,0,'','2019-05-20','2013-09-13'),(2270,1,0,'','2019-05-20','2003-04-15'),(2271,1,0,'','2019-05-20','2013-07-30'),(2272,0,0,'','2019-05-20','1999-05-30'),(2273,1,0,'','2019-05-20','2005-08-20'),(2274,1,0,'','2019-05-20','2010-11-20'),(2275,1,0,'','2019-05-20','2004-02-11'),(2276,1,0,'','2019-05-20','2010-06-10'),(2277,1,0,'','2019-05-20','2013-12-10'),(2278,1,0,'','2019-05-20','2002-08-10'),(2279,1,0,'','2019-05-20','2014-03-24'),(2280,1,0,'','2019-05-20','1999-01-27'),(2281,1,0,'','2019-05-20','1999-04-09'),(2282,1,0,'','2019-05-20','2000-07-22'),(2283,1,0,'','2019-05-20','2010-04-30'),(2284,1,0,'','2019-05-20','2002-04-23'),(2285,1,0,'','2019-05-20','2004-12-14'),(2286,1,0,'','2019-05-20','2008-06-14'),(2287,1,0,'','2019-05-20','2010-08-08'),(2288,0,0,'','2019-05-20','2003-11-13'),(2289,1,0,'','2019-05-20','2010-02-16'),(2290,1,0,'','2019-05-20','1999-03-20'),(2291,1,0,'','2019-05-20','2006-04-21'),(2292,1,0,'','2019-05-20','2006-06-14'),(2293,1,0,'','2019-05-20','2009-05-26'),(2294,1,0,'','2019-05-20','2006-02-18'),(2295,1,0,'','2019-05-20','2004-08-02'),(2296,1,0,'','2019-05-20','2010-02-21'),(2297,1,0,'','2019-05-20','2008-07-24'),(2298,1,0,'','2019-05-20','2002-07-10'),(2299,1,0,'','2019-05-20','2010-09-07'),(2300,1,0,'','2019-05-20','2001-04-13'),(2301,0,0,'','2019-05-20','2011-11-22'),(2302,1,0,'','2019-05-20','2014-04-04'),(2303,1,0,'','2019-05-20','2012-02-26'),(2304,1,0,'','2019-05-20','2011-05-27'),(2305,0,0,'','2019-05-20','2011-07-19'),(2306,1,0,'','2019-05-20','2000-10-05'),(2307,0,0,'','2019-05-20','2000-08-16'),(2308,1,0,'','2019-05-20','2013-06-26'),(2309,1,0,'','2019-05-20','2004-01-22'),(2310,1,0,'','2019-05-20','2003-06-13'),(2311,1,0,'','2019-05-20','1999-08-26'),(2312,1,0,'','2019-05-20','2007-06-15'),(2313,1,0,'','2019-05-20','2004-09-03'),(2314,1,0,'','2019-05-20','2010-08-07'),(2315,1,0,'','2019-05-20','2007-05-26'),(2316,1,0,'','2019-05-20','2011-07-15'),(2317,0,0,'','2019-05-20','2008-02-11'),(2318,0,0,'','2019-05-20','2010-05-26'),(2319,1,0,'','2019-05-20','2010-05-04'),(2320,1,0,'','2019-05-20','2003-09-27'),(2321,1,0,'','2019-05-20','1999-11-10'),(2322,1,0,'','2019-05-20','2006-09-22'),(2323,1,0,'','2019-05-20','2007-02-02'),(2324,0,0,'','2019-05-20','2008-12-14'),(2325,1,0,'','2019-05-20','2012-10-25'),(2326,1,0,'','2019-05-20','2000-05-16'),(2327,1,0,'','2019-05-20','2008-10-11'),(2328,1,0,'','2019-05-20','2002-05-01'),(2329,1,0,'','2019-05-20','2010-12-22'),(2330,0,0,'','2019-05-20','2012-10-05'),(2331,1,0,'','2019-05-20','2001-03-05'),(2332,1,0,'','2019-05-20','2010-10-24'),(2333,1,0,'','2019-05-20','2003-10-12'),(2334,1,0,'','2019-05-20','2009-08-01'),(2335,0,0,'','2019-05-20','1999-08-24'),(2336,1,0,'','2019-05-20','2011-07-18'),(2337,0,0,'','2019-05-20','2009-03-31'),(2338,1,0,'','2019-05-20','2006-02-11'),(2339,1,0,'','2019-05-20','2004-03-25'),(2340,1,0,'','2019-05-20','2008-01-16'),(2341,1,0,'','2019-05-20','2002-09-21'),(2342,1,0,'','2019-05-20','2003-03-16'),(2343,1,0,'','2019-05-20','2002-08-08'),(2344,1,0,'','2019-05-20','2012-11-09'),(2345,1,0,'','2019-05-20','2012-03-30'),(2346,1,0,'','2019-05-20','2007-07-19'),(2347,1,0,'','2019-05-20','1999-04-07'),(2348,1,0,'','2019-05-20','2007-12-03'),(2349,1,0,'','2019-05-20','2014-05-14'),(2350,1,0,'','2019-05-20','2006-02-13');
/*!40000 ALTER TABLE `ergometria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado` (
  `IDESTADO` bigint(20) NOT NULL,
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `FECHA` date DEFAULT NULL,
  `UNTIPOESTADO_IDTIPOESTADO` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`IDESTADO`),
  KEY `FK_ESTADO_UNTIPOESTADO_IDTIPOESTADO` (`UNTIPOESTADO_IDTIPOESTADO`),
  CONSTRAINT `FK_ESTADO_UNTIPOESTADO_IDTIPOESTADO` FOREIGN KEY (`UNTIPOESTADO_IDTIPOESTADO`) REFERENCES `tipoestado` (`IDTIPOESTADO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado`
--

LOCK TABLES `estado` WRITE;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
INSERT INTO `estado` VALUES (101,0,'2010-02-02',2),(102,0,'2002-03-01',2),(103,0,'2010-03-17',2),(104,0,'2004-05-31',2),(105,0,'2008-09-03',2),(106,0,'2009-07-30',2),(107,0,'2005-10-05',2),(108,0,'2005-08-27',2),(109,0,'1999-01-27',2),(110,0,'2006-06-19',2),(111,0,'1999-04-09',2),(112,0,'2012-06-14',2),(113,0,'2004-06-29',2),(114,0,'2013-03-13',2),(115,0,'2012-02-07',2),(116,0,'2014-01-25',2),(117,0,'2000-12-29',2),(118,0,'2013-05-16',2),(119,0,'2006-09-09',2),(120,0,'2002-02-20',2),(121,0,'2001-11-21',2),(122,0,'2007-10-20',2),(123,0,'2008-05-17',2),(124,0,'2012-11-01',2),(125,0,'1999-06-25',2),(126,0,'2000-10-20',2),(127,0,'2011-12-07',2),(128,0,'2003-06-01',2),(129,0,'2002-02-05',2),(130,0,'2011-05-26',2),(131,0,'2014-02-24',2),(132,0,'2004-10-13',2),(133,0,'2005-07-09',2),(134,0,'2014-06-21',2),(135,0,'2008-11-29',2),(136,0,'2010-01-13',2),(137,0,'2013-03-25',2),(138,0,'2001-11-25',2),(139,0,'1999-07-09',2),(140,0,'2013-04-10',2),(141,0,'2013-01-04',2),(142,0,'2006-11-08',2),(143,0,'2001-10-12',2),(144,0,'2012-08-27',2),(145,0,'2002-09-20',2),(146,0,'2007-01-26',2),(147,0,'2014-04-07',2),(148,0,'2008-02-12',2),(149,0,'2010-05-29',2),(150,0,'1999-04-23',2),(151,0,'2009-05-27',2),(152,0,'2011-02-13',2),(153,0,'2008-12-01',2),(154,0,'2002-09-20',2),(155,0,'2000-08-29',2),(156,0,'2008-03-20',2),(157,0,'2011-10-10',2),(158,0,'2010-12-17',2),(159,0,'2010-04-18',2),(160,0,'1999-03-25',2),(161,0,'2014-02-03',2),(162,0,'2005-03-26',2),(163,0,'2007-08-18',2),(164,0,'2004-09-25',2),(165,0,'2010-05-17',2),(166,0,'2003-02-10',2),(167,0,'2000-04-11',2),(168,0,'2010-06-06',2),(169,0,'2008-09-18',2),(170,0,'2005-03-13',2),(171,0,'2008-07-07',2),(172,0,'2003-09-07',2),(173,0,'2000-12-13',2),(174,0,'2005-07-22',2),(175,0,'2001-03-02',2),(176,0,'2004-12-23',2),(177,0,'2012-05-30',2),(178,0,'2010-04-01',2),(179,0,'2010-02-05',2),(180,0,'2001-10-12',2),(181,0,'2012-04-11',2),(182,0,'2010-09-16',2),(183,0,'2004-07-20',2),(184,0,'2006-09-22',2),(185,0,'2006-12-03',2),(186,0,'2003-05-02',2),(187,0,'2001-04-16',2),(188,0,'2004-03-04',2),(189,0,'2000-12-08',2),(190,0,'1999-03-14',2),(191,0,'2013-02-17',2),(192,0,'2000-03-28',2),(193,0,'2000-04-23',2),(194,0,'2003-07-26',2),(195,0,'2007-05-12',2),(196,0,'2000-09-10',2),(197,0,'2011-01-30',2),(198,0,'2012-07-03',2),(199,0,'1999-09-12',2),(200,0,'2001-05-05',2),(201,0,'2010-12-08',2),(202,0,'2003-12-17',2),(203,0,'2003-04-12',2),(204,0,'2002-01-02',2),(205,0,'2008-12-12',2),(206,0,'2010-07-14',2),(207,0,'2003-06-10',2),(208,0,'2011-07-31',2),(209,0,'2013-05-10',2),(210,0,'2000-08-18',2),(211,0,'2010-12-19',2),(212,0,'2009-12-01',2),(213,0,'2001-03-26',2),(214,0,'2004-06-02',2),(215,0,'2002-12-13',2),(216,0,'2002-11-23',2),(217,0,'2010-11-30',2),(218,0,'2006-08-09',2),(219,0,'2009-07-17',2),(220,0,'2010-04-13',2),(221,0,'2010-09-18',2),(222,0,'2010-02-12',2),(223,0,'2006-09-27',2),(224,0,'2002-10-31',2),(225,0,'2013-04-30',2),(226,0,'2002-08-27',2),(227,0,'2009-07-26',2),(228,0,'2006-01-28',2),(229,0,'2010-04-19',2),(230,0,'2010-10-30',2),(231,0,'2003-06-27',2),(232,0,'2009-11-21',2),(233,0,'2009-11-12',2),(234,0,'2002-10-24',2),(235,0,'2002-12-14',2),(236,0,'2006-04-13',2),(237,0,'2012-12-20',2),(238,0,'2005-08-21',2),(239,0,'2005-08-12',2),(240,0,'2009-11-07',2),(241,0,'2002-04-14',2),(242,0,'1999-01-26',2),(243,0,'2005-11-23',2),(244,0,'2001-05-17',2),(245,0,'2006-12-03',2),(246,0,'2007-09-25',2),(247,0,'1999-06-18',2),(248,0,'2013-08-23',2),(249,0,'1999-10-14',2),(250,0,'1999-07-19',2),(251,0,'2012-05-19',2),(252,0,'2000-04-26',2),(253,0,'2008-05-06',2),(254,0,'2006-12-21',2),(255,0,'1999-07-28',2),(256,0,'2014-02-15',2),(257,0,'2003-10-31',2),(258,0,'2006-07-08',2),(259,0,'2005-01-05',2),(260,0,'2011-01-22',2),(261,0,'2009-03-09',2),(262,0,'2001-09-25',2),(263,0,'2009-02-12',2),(264,0,'2004-07-20',2),(265,0,'2002-05-21',2),(266,0,'2007-07-18',2),(267,0,'2013-03-13',2),(268,0,'2006-12-23',2),(269,0,'1999-03-03',2),(270,0,'1999-05-14',2),(271,0,'2006-12-06',2),(272,0,'2011-06-12',2),(273,0,'2004-02-04',2),(274,0,'2012-06-15',2),(275,0,'2005-08-27',2),(276,0,'2003-05-02',2),(277,0,'2007-02-13',2),(278,0,'2004-09-13',2),(279,0,'2003-04-26',2),(280,0,'2007-12-18',2),(281,0,'2012-01-15',2),(282,0,'2004-12-05',2),(283,0,'2007-05-28',2),(284,0,'2003-10-13',2),(285,0,'2012-09-05',2),(286,0,'2001-02-21',2),(287,0,'2002-09-11',2),(288,0,'2004-08-12',2),(289,0,'2001-08-27',2),(290,0,'2011-06-09',2),(291,0,'2004-03-23',2),(292,0,'2001-04-08',2),(293,0,'2013-01-17',2),(294,0,'2004-10-10',2),(295,0,'2010-09-06',2),(296,0,'2013-12-07',2),(297,0,'2001-01-17',2),(298,0,'2007-03-01',2),(299,0,'2003-03-11',2),(300,0,'2013-04-02',2),(301,0,'2005-10-20',2),(302,0,'2009-12-22',2),(303,0,'2001-07-20',2),(304,0,'2002-05-03',2),(305,0,'1999-09-02',2),(306,0,'2009-07-22',2),(307,0,'2012-06-19',2),(308,0,'2004-05-07',2),(309,0,'1999-11-07',2),(310,0,'2007-09-26',2),(311,0,'2014-05-14',2),(312,0,'2006-02-04',2),(313,0,'2006-06-17',2),(314,0,'2014-07-26',2),(315,0,'2009-01-13',2),(316,0,'2006-07-22',2),(317,0,'2000-05-07',2),(318,0,'2012-05-06',2),(319,0,'2000-04-03',2),(320,0,'2005-04-06',2),(321,0,'2010-01-22',2),(322,0,'1999-12-28',2),(323,0,'2006-10-12',2),(324,0,'2009-08-08',2),(325,0,'2012-01-14',2),(326,0,'2010-01-11',2),(327,0,'2014-01-11',2),(328,0,'2006-04-03',2),(329,0,'2003-10-20',2),(330,0,'2008-03-12',2),(331,0,'2012-04-23',2),(332,0,'2011-10-09',2),(333,0,'1999-06-20',2),(334,0,'2000-04-24',2),(335,0,'2014-05-15',2),(336,0,'2011-12-12',2),(337,0,'1999-05-22',2),(338,0,'2005-03-22',2),(339,0,'2006-07-19',2),(340,0,'2003-03-15',2),(341,0,'2010-04-17',2),(342,0,'2000-12-20',2),(343,0,'2014-03-23',2),(344,0,'2004-09-03',2),(345,0,'2000-06-11',2),(346,0,'2004-02-15',2),(347,0,'2002-05-24',2),(348,0,'2008-02-22',2),(349,0,'2001-06-14',2),(350,0,'2011-11-12',2),(351,0,'2005-06-03',2),(352,0,'2004-10-12',2),(353,0,'2010-07-01',2),(354,0,'2003-06-26',2),(355,0,'2010-04-04',2),(356,0,'2009-02-04',2),(357,0,'2001-03-29',2),(358,0,'2007-02-21',2),(359,0,'2001-07-28',2),(360,0,'2007-11-21',2),(361,0,'2002-10-30',2),(362,0,'2005-12-08',2),(363,0,'2014-03-05',2),(364,0,'2010-11-08',2),(365,0,'2007-03-10',2),(366,0,'2013-04-20',2),(367,0,'2001-07-17',2),(368,0,'2001-10-27',2),(369,0,'2008-07-30',2),(370,0,'2005-02-28',2),(371,0,'2006-05-27',2),(372,0,'2009-06-18',2),(373,0,'2009-09-12',2),(374,0,'2000-03-31',2),(375,0,'2002-01-15',2),(376,0,'2004-05-18',2),(377,0,'2003-11-01',2),(378,0,'2011-05-20',2),(379,0,'2008-07-07',2),(380,0,'2009-05-24',2),(381,0,'1999-10-30',2),(382,0,'2001-05-29',2),(383,0,'2012-10-14',2),(384,0,'2000-12-06',2),(385,0,'2000-02-16',2),(386,0,'2009-03-13',2),(387,0,'2014-05-04',2),(388,0,'2010-04-12',2),(389,0,'2005-04-24',2),(390,0,'2005-04-18',2),(391,0,'2000-12-01',2),(392,0,'2001-01-01',2),(393,0,'2005-09-24',2),(394,0,'2013-09-13',2),(395,0,'2003-04-15',2),(396,0,'2013-07-30',2),(397,0,'1999-05-30',2),(398,0,'2005-08-20',2),(399,0,'2010-11-20',2),(400,0,'2004-02-11',2),(401,0,'2010-06-10',2),(402,0,'2013-12-10',2),(403,0,'2002-08-10',2),(404,0,'2014-03-24',2),(405,0,'1999-01-27',2),(406,0,'1999-04-09',2),(407,0,'2000-07-22',2),(408,0,'2010-04-30',2),(409,0,'2002-04-23',2),(410,0,'2004-12-14',2),(411,0,'2008-06-14',2),(412,0,'2010-08-08',2),(413,0,'2003-11-13',2),(414,0,'2010-02-16',2),(415,0,'1999-03-20',2),(416,0,'2006-04-21',2),(417,0,'2006-06-14',2),(418,0,'2009-05-26',2),(419,0,'2006-02-18',2),(420,0,'2004-08-02',2),(421,0,'2010-02-21',2),(422,0,'2008-07-24',2),(423,0,'2002-07-10',2),(424,0,'2010-09-07',2),(425,0,'2001-04-13',2),(426,0,'2011-11-22',2),(427,0,'2014-04-04',2),(428,0,'2012-02-26',2),(429,0,'2011-05-27',2),(430,0,'2011-07-19',2),(431,0,'2000-10-05',2),(432,0,'2000-08-16',2),(433,0,'2013-06-26',2),(434,0,'2004-01-22',2),(435,0,'2003-06-13',2),(436,0,'1999-08-26',2),(437,0,'2007-06-15',2),(438,0,'2004-09-03',2),(439,0,'2010-08-07',2),(440,0,'2007-05-26',2),(441,0,'2011-07-15',2),(442,0,'2008-02-11',2),(443,0,'2010-05-26',2),(444,0,'2010-05-04',2),(445,0,'2003-09-27',2),(446,0,'1999-11-10',2),(447,0,'2006-09-22',2),(448,0,'2007-02-02',2),(449,0,'2008-12-14',2),(450,0,'2012-10-25',2),(451,0,'2000-05-16',2),(452,0,'2008-10-11',2),(453,0,'2002-05-01',2),(454,0,'2010-12-22',2),(455,0,'2012-10-05',2),(456,0,'2001-03-05',2),(457,0,'2010-10-24',2),(458,0,'2003-10-12',2),(459,0,'2009-08-01',2),(460,0,'1999-08-24',2),(461,0,'2011-07-18',2),(462,0,'2009-03-31',2),(463,0,'2006-02-11',2),(464,0,'2004-03-25',2),(465,0,'2008-01-16',2),(466,0,'2002-09-21',2),(467,0,'2003-03-16',2),(468,0,'2002-08-08',2),(469,0,'2012-11-09',2),(470,0,'2012-03-30',2),(471,0,'2007-07-19',2),(472,0,'1999-04-07',2),(473,0,'2007-12-03',2),(474,0,'2014-05-14',2),(475,0,'2006-02-13',2);
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fechatorneo`
--

DROP TABLE IF EXISTS `fechatorneo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fechatorneo` (
  `IDFECHA` bigint(20) NOT NULL,
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `NUMEROFECHA` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDFECHA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fechatorneo`
--

LOCK TABLES `fechatorneo` WRITE;
/*!40000 ALTER TABLE `fechatorneo` DISABLE KEYS */;
/*!40000 ALTER TABLE `fechatorneo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fechatorneo_partido`
--

DROP TABLE IF EXISTS `fechatorneo_partido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fechatorneo_partido` (
  `FechaTorneo_IDFECHA` bigint(20) NOT NULL,
  `partidos_IDPARTIDO` bigint(20) NOT NULL,
  PRIMARY KEY (`FechaTorneo_IDFECHA`,`partidos_IDPARTIDO`),
  KEY `FK_FECHATORNEO_PARTIDO_partidos_IDPARTIDO` (`partidos_IDPARTIDO`),
  CONSTRAINT `FK_FECHATORNEO_PARTIDO_FechaTorneo_IDFECHA` FOREIGN KEY (`FechaTorneo_IDFECHA`) REFERENCES `fechatorneo` (`IDFECHA`),
  CONSTRAINT `FK_FECHATORNEO_PARTIDO_partidos_IDPARTIDO` FOREIGN KEY (`partidos_IDPARTIDO`) REFERENCES `partido` (`IDPARTIDO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fechatorneo_partido`
--

LOCK TABLES `fechatorneo_partido` WRITE;
/*!40000 ALTER TABLE `fechatorneo_partido` DISABLE KEYS */;
/*!40000 ALTER TABLE `fechatorneo_partido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gol`
--

DROP TABLE IF EXISTS `gol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gol` (
  `IDGOL` bigint(20) NOT NULL,
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `MINUTO` varchar(255) DEFAULT NULL,
  `TIEMPO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDGOL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gol`
--

LOCK TABLES `gol` WRITE;
/*!40000 ALTER TABLE `gol` DISABLE KEYS */;
/*!40000 ALTER TABLE `gol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `indumentaria`
--

DROP TABLE IF EXISTS `indumentaria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `indumentaria` (
  `IDINDUMENTARIA` bigint(20) NOT NULL,
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `CAMISETA` varchar(255) DEFAULT NULL,
  `MEDIA` varchar(255) DEFAULT NULL,
  `POLLERA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDINDUMENTARIA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indumentaria`
--

LOCK TABLES `indumentaria` WRITE;
/*!40000 ALTER TABLE `indumentaria` DISABLE KEYS */;
/*!40000 ALTER TABLE `indumentaria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingresootro`
--

DROP TABLE IF EXISTS `ingresootro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingresootro` (
  `IDINGRESOOTRO` bigint(20) NOT NULL,
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `FECHA` date DEFAULT NULL,
  `MONTO` double DEFAULT NULL,
  `OBSERVACION` varchar(1000) DEFAULT NULL,
  `UNCONCEPTOINGRESO_IDCONCEPTOINGRESO` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`IDINGRESOOTRO`),
  KEY `FK_INGRESOOTRO_UNCONCEPTOINGRESO_IDCONCEPTOINGRESO` (`UNCONCEPTOINGRESO_IDCONCEPTOINGRESO`),
  CONSTRAINT `FK_INGRESOOTRO_UNCONCEPTOINGRESO_IDCONCEPTOINGRESO` FOREIGN KEY (`UNCONCEPTOINGRESO_IDCONCEPTOINGRESO`) REFERENCES `conceptoingreso` (`IDCONCEPTOINGRESO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingresootro`
--

LOCK TABLES `ingresootro` WRITE;
/*!40000 ALTER TABLE `ingresootro` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingresootro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localidad`
--

DROP TABLE IF EXISTS `localidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localidad` (
  `IDLOCALIDAD` bigint(20) NOT NULL,
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `CODPOSTAL` varchar(255) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDLOCALIDAD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localidad`
--

LOCK TABLES `localidad` WRITE;
/*!40000 ALTER TABLE `localidad` DISABLE KEYS */;
INSERT INTO `localidad` VALUES (1,0,'3300','Posadas'),(2,0,'3334','Puerto Rico'),(3,0,'3320','Obera'),(6,0,'3364','2 de Mayo'),(7,0,'3363','25 de Mayo'),(8,0,'3363','9 de Julio'),(9,0,'3363','Alba Posse'),(10,0,'3350','Apóstoles'),(11,0,'3364','Aristóbulo del Valle'),(12,0,'3366','Bernardo de Irigoyen'),(13,0,'3317','Bonpland'),(14,0,'3362','Campo Grande'),(15,0,'3362','Campo Viera'),(16,0,'3308','Candelaria'),(17,0,'3332','Capioví'),(18,0,'3313','Cerro Azul'),(19,0,'3309','Cerro Corá'),(20,0,'3311','Colonia Alberdi'),(21,0,'3363','Colonia Aurora'),(22,0,'3386','Caraguatay'),(23,0,'3382','Colonia Delicia'),(24,0,'3326','Colonia Polana'),(25,0,'3355','Concepción de la Sierra'),(26,0,'3327','Corpus'),(27,0,'3364','Comandante Andresito'),(28,0,'3315','Dos Arroyos'),(29,0,'3384','El Alcázar'),(30,0,'3364','El Soberbio'),(31,0,'3382','El Dorado'),(32,0,'3334','Garuhapé'),(33,0,'3304','Garupá'),(34,0,'3324','Gobernador Roca'),(35,0,'3361','Guaraní'),(36,0,'3353','Itacaruaré'),(37,0,'3328','Jardín América'),(38,0,'3315','Leandro N Alem'),(39,0,'3316','Loreto'),(40,0,'3304','Miguel Lanús'),(41,0,'3315','Mojón Grande'),(42,0,'3384','Montecarlo'),(43,0,'3360','Oberá'),(44,0,'3361','Panambí'),(45,0,'3300','Posadas'),(46,0,'3378','Puerto Esperanza'),(47,0,'3370','Puerto Iguazú'),(48,0,'3370','Puerto Libertad'),(49,0,'3381','Puerto Piray'),(50,0,'3334','Puerto Rico'),(51,0,'3334','Ruiz de Montoya'),(52,0,'3366','San Antonio'),(53,0,'3322','San Ignacio'),(54,0,'3357','San Javier'),(55,0,'3306','San José'),(56,0,'3364','San Pedro'),(57,0,'3364','San Vicente'),(58,0,'3316','Santa Ana'),(59,0,'3363','Santa Rita'),(60,0,'3326','Santo Pipó'),(61,0,'3364','Salto Encantado'),(62,0,'3376','Wanda');
/*!40000 ALTER TABLE `localidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mes`
--

DROP TABLE IF EXISTS `mes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mes` (
  `NOMBRE` varchar(255) NOT NULL,
  PRIMARY KEY (`NOMBRE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mes`
--

LOCK TABLES `mes` WRITE;
/*!40000 ALTER TABLE `mes` DISABLE KEYS */;
INSERT INTO `mes` VALUES ('Abril'),('Agosto'),('Diciembre'),('Enero'),('Febrero'),('Julio'),('Junio'),('Marzo'),('Mayo'),('Noviembre'),('Octubre'),('Septiembre');
/*!40000 ALTER TABLE `mes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagocuota`
--

DROP TABLE IF EXISTS `pagocuota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagocuota` (
  `IDPAGOCUOTA` bigint(20) NOT NULL,
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `FECHAPAGO` date DEFAULT NULL,
  `MONTO` double DEFAULT NULL,
  `OBSERVACION` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`IDPAGOCUOTA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagocuota`
--

LOCK TABLES `pagocuota` WRITE;
/*!40000 ALTER TABLE `pagocuota` DISABLE KEYS */;
INSERT INTO `pagocuota` VALUES (477,0,'2010-02-02',0,'Pago deuda de monto 0'),(481,0,'2002-03-01',0,'Pago deuda de monto 0'),(485,0,'2010-03-17',0,'Pago deuda de monto 0'),(489,0,'2004-05-31',0,'Pago deuda de monto 0'),(493,0,'2008-09-03',0,'Pago deuda de monto 0'),(497,0,'2009-07-30',0,'Pago deuda de monto 0'),(501,0,'2005-10-05',0,'Pago deuda de monto 0'),(505,0,'2005-08-27',0,'Pago deuda de monto 0'),(509,0,'1999-01-27',0,'Pago deuda de monto 0'),(513,0,'2006-06-19',0,'Pago deuda de monto 0'),(517,0,'1999-04-09',0,'Pago deuda de monto 0'),(521,0,'2012-06-14',0,'Pago deuda de monto 0'),(525,0,'2004-06-29',0,'Pago deuda de monto 0'),(529,0,'2013-03-13',0,'Pago deuda de monto 0'),(533,0,'2012-02-07',0,'Pago deuda de monto 0'),(537,0,'2014-01-25',0,'Pago deuda de monto 0'),(541,0,'2000-12-29',0,'Pago deuda de monto 0'),(545,0,'2013-05-16',0,'Pago deuda de monto 0'),(549,0,'2006-09-09',0,'Pago deuda de monto 0'),(553,0,'2002-02-20',0,'Pago deuda de monto 0'),(557,0,'2001-11-21',0,'Pago deuda de monto 0'),(561,0,'2007-10-20',0,'Pago deuda de monto 0'),(565,0,'2008-05-17',0,'Pago deuda de monto 0'),(569,0,'2012-11-01',0,'Pago deuda de monto 0'),(573,0,'1999-06-25',0,'Pago deuda de monto 0'),(577,0,'2000-10-20',0,'Pago deuda de monto 0'),(581,0,'2011-12-07',0,'Pago deuda de monto 0'),(585,0,'2003-06-01',0,'Pago deuda de monto 0'),(589,0,'2002-02-05',0,'Pago deuda de monto 0'),(593,0,'2011-05-26',0,'Pago deuda de monto 0'),(597,0,'2014-02-24',0,'Pago deuda de monto 0'),(601,0,'2004-10-13',0,'Pago deuda de monto 0'),(605,0,'2005-07-09',0,'Pago deuda de monto 0'),(609,0,'2014-06-21',0,'Pago deuda de monto 0'),(613,0,'2008-11-29',0,'Pago deuda de monto 0'),(617,0,'2010-01-13',0,'Pago deuda de monto 0'),(621,0,'2013-03-25',0,'Pago deuda de monto 0'),(625,0,'2001-11-25',0,'Pago deuda de monto 0'),(629,0,'1999-07-09',0,'Pago deuda de monto 0'),(633,0,'2013-04-10',0,'Pago deuda de monto 0'),(637,0,'2013-01-04',0,'Pago deuda de monto 0'),(641,0,'2006-11-08',0,'Pago deuda de monto 0'),(645,0,'2001-10-12',0,'Pago deuda de monto 0'),(649,0,'2012-08-27',0,'Pago deuda de monto 0'),(653,0,'2002-09-20',0,'Pago deuda de monto 0'),(657,0,'2007-01-26',0,'Pago deuda de monto 0'),(661,0,'2014-04-07',0,'Pago deuda de monto 0'),(665,0,'2008-02-12',0,'Pago deuda de monto 0'),(669,0,'2010-05-29',0,'Pago deuda de monto 0'),(673,0,'1999-04-23',0,'Pago deuda de monto 0'),(677,0,'2009-05-27',0,'Pago deuda de monto 0'),(681,0,'2011-02-13',0,'Pago deuda de monto 0'),(685,0,'2008-12-01',0,'Pago deuda de monto 0'),(689,0,'2002-09-20',0,'Pago deuda de monto 0'),(693,0,'2000-08-29',0,'Pago deuda de monto 0'),(697,0,'2008-03-20',0,'Pago deuda de monto 0'),(701,0,'2011-10-10',0,'Pago deuda de monto 0'),(705,0,'2010-12-17',0,'Pago deuda de monto 0'),(709,0,'2010-04-18',0,'Pago deuda de monto 0'),(713,0,'1999-03-25',0,'Pago deuda de monto 0'),(717,0,'2014-02-03',0,'Pago deuda de monto 0'),(721,0,'2005-03-26',0,'Pago deuda de monto 0'),(725,0,'2007-08-18',0,'Pago deuda de monto 0'),(729,0,'2004-09-25',0,'Pago deuda de monto 0'),(733,0,'2010-05-17',0,'Pago deuda de monto 0'),(737,0,'2003-02-10',0,'Pago deuda de monto 0'),(741,0,'2000-04-11',0,'Pago deuda de monto 0'),(745,0,'2010-06-06',0,'Pago deuda de monto 0'),(749,0,'2008-09-18',0,'Pago deuda de monto 0'),(753,0,'2005-03-13',0,'Pago deuda de monto 0'),(757,0,'2008-07-07',0,'Pago deuda de monto 0'),(761,0,'2003-09-07',0,'Pago deuda de monto 0'),(765,0,'2000-12-13',0,'Pago deuda de monto 0'),(769,0,'2005-07-22',0,'Pago deuda de monto 0'),(773,0,'2001-03-02',0,'Pago deuda de monto 0'),(777,0,'2004-12-23',0,'Pago deuda de monto 0'),(781,0,'2012-05-30',0,'Pago deuda de monto 0'),(785,0,'2010-04-01',0,'Pago deuda de monto 0'),(789,0,'2010-02-05',0,'Pago deuda de monto 0'),(793,0,'2001-10-12',0,'Pago deuda de monto 0'),(797,0,'2012-04-11',0,'Pago deuda de monto 0'),(801,0,'2010-09-16',0,'Pago deuda de monto 0'),(805,0,'2004-07-20',0,'Pago deuda de monto 0'),(809,0,'2006-09-22',0,'Pago deuda de monto 0'),(813,0,'2006-12-03',0,'Pago deuda de monto 0'),(817,0,'2003-05-02',0,'Pago deuda de monto 0'),(821,0,'2001-04-16',0,'Pago deuda de monto 0'),(825,0,'2004-03-04',0,'Pago deuda de monto 0'),(829,0,'2000-12-08',0,'Pago deuda de monto 0'),(833,0,'1999-03-14',0,'Pago deuda de monto 0'),(837,0,'2013-02-17',0,'Pago deuda de monto 0'),(841,0,'2000-03-28',0,'Pago deuda de monto 0'),(845,0,'2000-04-23',0,'Pago deuda de monto 0'),(849,0,'2003-07-26',0,'Pago deuda de monto 0'),(853,0,'2007-05-12',0,'Pago deuda de monto 0'),(857,0,'2000-09-10',0,'Pago deuda de monto 0'),(861,0,'2011-01-30',0,'Pago deuda de monto 0'),(865,0,'2012-07-03',0,'Pago deuda de monto 0'),(869,0,'1999-09-12',0,'Pago deuda de monto 0'),(873,0,'2001-05-05',0,'Pago deuda de monto 0'),(877,0,'2010-12-08',0,'Pago deuda de monto 0'),(881,0,'2003-12-17',0,'Pago deuda de monto 0'),(885,0,'2003-04-12',0,'Pago deuda de monto 0'),(889,0,'2002-01-02',0,'Pago deuda de monto 0'),(893,0,'2008-12-12',0,'Pago deuda de monto 0'),(897,0,'2010-07-14',0,'Pago deuda de monto 0'),(901,0,'2003-06-10',0,'Pago deuda de monto 0'),(905,0,'2011-07-31',0,'Pago deuda de monto 0'),(909,0,'2013-05-10',0,'Pago deuda de monto 0'),(913,0,'2000-08-18',0,'Pago deuda de monto 0'),(917,0,'2010-12-19',0,'Pago deuda de monto 0'),(921,0,'2009-12-01',0,'Pago deuda de monto 0'),(925,0,'2001-03-26',0,'Pago deuda de monto 0'),(929,0,'2004-06-02',0,'Pago deuda de monto 0'),(933,0,'2002-12-13',0,'Pago deuda de monto 0'),(937,0,'2002-11-23',0,'Pago deuda de monto 0'),(941,0,'2010-11-30',0,'Pago deuda de monto 0'),(945,0,'2006-08-09',0,'Pago deuda de monto 0'),(949,0,'2009-07-17',0,'Pago deuda de monto 0'),(953,0,'2010-04-13',0,'Pago deuda de monto 0'),(957,0,'2010-09-18',0,'Pago deuda de monto 0'),(961,0,'2010-02-12',0,'Pago deuda de monto 0'),(965,0,'2006-09-27',0,'Pago deuda de monto 0'),(969,0,'2002-10-31',0,'Pago deuda de monto 0'),(973,0,'2013-04-30',0,'Pago deuda de monto 0'),(977,0,'2002-08-27',0,'Pago deuda de monto 0'),(981,0,'2009-07-26',0,'Pago deuda de monto 0'),(985,0,'2006-01-28',0,'Pago deuda de monto 0'),(989,0,'2010-04-19',0,'Pago deuda de monto 0'),(993,0,'2010-10-30',0,'Pago deuda de monto 0'),(997,0,'2003-06-27',0,'Pago deuda de monto 0'),(1001,0,'2009-11-21',0,'Pago deuda de monto 0'),(1005,0,'2009-11-12',0,'Pago deuda de monto 0'),(1009,0,'2002-10-24',0,'Pago deuda de monto 0'),(1013,0,'2002-12-14',0,'Pago deuda de monto 0'),(1017,0,'2006-04-13',0,'Pago deuda de monto 0'),(1021,0,'2012-12-20',0,'Pago deuda de monto 0'),(1025,0,'2005-08-21',0,'Pago deuda de monto 0'),(1029,0,'2005-08-12',0,'Pago deuda de monto 0'),(1033,0,'2009-11-07',0,'Pago deuda de monto 0'),(1037,0,'2002-04-14',0,'Pago deuda de monto 0'),(1041,0,'1999-01-26',0,'Pago deuda de monto 0'),(1045,0,'2005-11-23',0,'Pago deuda de monto 0'),(1049,0,'2001-05-17',0,'Pago deuda de monto 0'),(1053,0,'2006-12-03',0,'Pago deuda de monto 0'),(1057,0,'2007-09-25',0,'Pago deuda de monto 0'),(1061,0,'1999-06-18',0,'Pago deuda de monto 0'),(1065,0,'2013-08-23',0,'Pago deuda de monto 0'),(1069,0,'1999-10-14',0,'Pago deuda de monto 0'),(1073,0,'1999-07-19',0,'Pago deuda de monto 0'),(1077,0,'2012-05-19',0,'Pago deuda de monto 0'),(1081,0,'2000-04-26',0,'Pago deuda de monto 0'),(1085,0,'2008-05-06',0,'Pago deuda de monto 0'),(1089,0,'2006-12-21',0,'Pago deuda de monto 0'),(1093,0,'1999-07-28',0,'Pago deuda de monto 0'),(1097,0,'2014-02-15',0,'Pago deuda de monto 0'),(1101,0,'2003-10-31',0,'Pago deuda de monto 0'),(1105,0,'2006-07-08',0,'Pago deuda de monto 0'),(1109,0,'2005-01-05',0,'Pago deuda de monto 0'),(1113,0,'2011-01-22',0,'Pago deuda de monto 0'),(1117,0,'2009-03-09',0,'Pago deuda de monto 0'),(1121,0,'2001-09-25',0,'Pago deuda de monto 0'),(1125,0,'2009-02-12',0,'Pago deuda de monto 0'),(1129,0,'2004-07-20',0,'Pago deuda de monto 0'),(1133,0,'2002-05-21',0,'Pago deuda de monto 0'),(1137,0,'2007-07-18',0,'Pago deuda de monto 0'),(1141,0,'2013-03-13',0,'Pago deuda de monto 0'),(1145,0,'2006-12-23',0,'Pago deuda de monto 0'),(1149,0,'1999-03-03',0,'Pago deuda de monto 0'),(1153,0,'1999-05-14',0,'Pago deuda de monto 0'),(1157,0,'2006-12-06',0,'Pago deuda de monto 0'),(1161,0,'2011-06-12',0,'Pago deuda de monto 0'),(1165,0,'2004-02-04',0,'Pago deuda de monto 0'),(1169,0,'2012-06-15',0,'Pago deuda de monto 0'),(1173,0,'2005-08-27',0,'Pago deuda de monto 0'),(1177,0,'2003-05-02',0,'Pago deuda de monto 0'),(1181,0,'2007-02-13',0,'Pago deuda de monto 0'),(1185,0,'2004-09-13',0,'Pago deuda de monto 0'),(1189,0,'2003-04-26',0,'Pago deuda de monto 0'),(1193,0,'2007-12-18',0,'Pago deuda de monto 0'),(1197,0,'2012-01-15',0,'Pago deuda de monto 0'),(1201,0,'2004-12-05',0,'Pago deuda de monto 0'),(1205,0,'2007-05-28',0,'Pago deuda de monto 0'),(1209,0,'2003-10-13',0,'Pago deuda de monto 0'),(1213,0,'2012-09-05',0,'Pago deuda de monto 0'),(1217,0,'2001-02-21',0,'Pago deuda de monto 0'),(1221,0,'2002-09-11',0,'Pago deuda de monto 0'),(1225,0,'2004-08-12',0,'Pago deuda de monto 0'),(1229,0,'2001-08-27',0,'Pago deuda de monto 0'),(1233,0,'2011-06-09',0,'Pago deuda de monto 0'),(1237,0,'2004-03-23',0,'Pago deuda de monto 0'),(1241,0,'2001-04-08',0,'Pago deuda de monto 0'),(1245,0,'2013-01-17',0,'Pago deuda de monto 0'),(1249,0,'2004-10-10',0,'Pago deuda de monto 0'),(1253,0,'2010-09-06',0,'Pago deuda de monto 0'),(1257,0,'2013-12-07',0,'Pago deuda de monto 0'),(1261,0,'2001-01-17',0,'Pago deuda de monto 0'),(1265,0,'2007-03-01',0,'Pago deuda de monto 0'),(1269,0,'2003-03-11',0,'Pago deuda de monto 0'),(1273,0,'2013-04-02',0,'Pago deuda de monto 0'),(1277,0,'2005-10-20',0,'Pago deuda de monto 0'),(1281,0,'2009-12-22',0,'Pago deuda de monto 0'),(1285,0,'2001-07-20',0,'Pago deuda de monto 0'),(1289,0,'2002-05-03',0,'Pago deuda de monto 0'),(1293,0,'1999-09-02',0,'Pago deuda de monto 0'),(1297,0,'2009-07-22',0,'Pago deuda de monto 0'),(1301,0,'2012-06-19',0,'Pago deuda de monto 0'),(1305,0,'2004-05-07',0,'Pago deuda de monto 0'),(1309,0,'1999-11-07',0,'Pago deuda de monto 0'),(1313,0,'2007-09-26',0,'Pago deuda de monto 0'),(1317,0,'2014-05-14',0,'Pago deuda de monto 0'),(1321,0,'2006-02-04',0,'Pago deuda de monto 0'),(1325,0,'2006-06-17',0,'Pago deuda de monto 0'),(1329,0,'2014-07-26',0,'Pago deuda de monto 0'),(1333,0,'2009-01-13',0,'Pago deuda de monto 0'),(1337,0,'2006-07-22',0,'Pago deuda de monto 0'),(1341,0,'2000-05-07',0,'Pago deuda de monto 0'),(1345,0,'2012-05-06',0,'Pago deuda de monto 0'),(1349,0,'2000-04-03',0,'Pago deuda de monto 0'),(1353,0,'2005-04-06',0,'Pago deuda de monto 0'),(1357,0,'2010-01-22',0,'Pago deuda de monto 0'),(1361,0,'1999-12-28',0,'Pago deuda de monto 0'),(1365,0,'2006-10-12',0,'Pago deuda de monto 0'),(1369,0,'2009-08-08',0,'Pago deuda de monto 0'),(1373,0,'2012-01-14',0,'Pago deuda de monto 0'),(1377,0,'2010-01-11',0,'Pago deuda de monto 0'),(1381,0,'2014-01-11',0,'Pago deuda de monto 0'),(1385,0,'2006-04-03',0,'Pago deuda de monto 0'),(1389,0,'2003-10-20',0,'Pago deuda de monto 0'),(1393,0,'2008-03-12',0,'Pago deuda de monto 0'),(1397,0,'2012-04-23',0,'Pago deuda de monto 0'),(1401,0,'2011-10-09',0,'Pago deuda de monto 0'),(1405,0,'1999-06-20',0,'Pago deuda de monto 0'),(1409,0,'2000-04-24',0,'Pago deuda de monto 0'),(1413,0,'2014-05-15',0,'Pago deuda de monto 0'),(1417,0,'2011-12-12',0,'Pago deuda de monto 0'),(1421,0,'1999-05-22',0,'Pago deuda de monto 0'),(1425,0,'2005-03-22',0,'Pago deuda de monto 0'),(1429,0,'2006-07-19',0,'Pago deuda de monto 0'),(1433,0,'2003-03-15',0,'Pago deuda de monto 0'),(1437,0,'2010-04-17',0,'Pago deuda de monto 0'),(1441,0,'2000-12-20',0,'Pago deuda de monto 0'),(1445,0,'2014-03-23',0,'Pago deuda de monto 0'),(1449,0,'2004-09-03',0,'Pago deuda de monto 0'),(1453,0,'2000-06-11',0,'Pago deuda de monto 0'),(1457,0,'2004-02-15',0,'Pago deuda de monto 0'),(1461,0,'2002-05-24',0,'Pago deuda de monto 0'),(1465,0,'2008-02-22',0,'Pago deuda de monto 0'),(1469,0,'2001-06-14',0,'Pago deuda de monto 0'),(1473,0,'2011-11-12',0,'Pago deuda de monto 0'),(1477,0,'2005-06-03',0,'Pago deuda de monto 0'),(1481,0,'2004-10-12',0,'Pago deuda de monto 0'),(1485,0,'2010-07-01',0,'Pago deuda de monto 0'),(1489,0,'2003-06-26',0,'Pago deuda de monto 0'),(1493,0,'2010-04-04',0,'Pago deuda de monto 0'),(1497,0,'2009-02-04',0,'Pago deuda de monto 0'),(1501,0,'2001-03-29',0,'Pago deuda de monto 0'),(1505,0,'2007-02-21',0,'Pago deuda de monto 0'),(1509,0,'2001-07-28',0,'Pago deuda de monto 0'),(1513,0,'2007-11-21',0,'Pago deuda de monto 0'),(1517,0,'2002-10-30',0,'Pago deuda de monto 0'),(1521,0,'2005-12-08',0,'Pago deuda de monto 0'),(1525,0,'2014-03-05',0,'Pago deuda de monto 0'),(1529,0,'2010-11-08',0,'Pago deuda de monto 0'),(1533,0,'2007-03-10',0,'Pago deuda de monto 0'),(1537,0,'2013-04-20',0,'Pago deuda de monto 0'),(1541,0,'2001-07-17',0,'Pago deuda de monto 0'),(1545,0,'2001-10-27',0,'Pago deuda de monto 0'),(1549,0,'2008-07-30',0,'Pago deuda de monto 0'),(1553,0,'2005-02-28',0,'Pago deuda de monto 0'),(1557,0,'2006-05-27',0,'Pago deuda de monto 0'),(1561,0,'2009-06-18',0,'Pago deuda de monto 0'),(1565,0,'2009-09-12',0,'Pago deuda de monto 0'),(1569,0,'2000-03-31',0,'Pago deuda de monto 0'),(1573,0,'2002-01-15',0,'Pago deuda de monto 0'),(1577,0,'2004-05-18',0,'Pago deuda de monto 0'),(1581,0,'2003-11-01',0,'Pago deuda de monto 0'),(1585,0,'2011-05-20',0,'Pago deuda de monto 0'),(1589,0,'2008-07-07',0,'Pago deuda de monto 0'),(1593,0,'2009-05-24',0,'Pago deuda de monto 0'),(1597,0,'1999-10-30',0,'Pago deuda de monto 0'),(1601,0,'2001-05-29',0,'Pago deuda de monto 0'),(1605,0,'2012-10-14',0,'Pago deuda de monto 0'),(1609,0,'2000-12-06',0,'Pago deuda de monto 0'),(1613,0,'2000-02-16',0,'Pago deuda de monto 0'),(1617,0,'2009-03-13',0,'Pago deuda de monto 0'),(1621,0,'2014-05-04',0,'Pago deuda de monto 0'),(1625,0,'2010-04-12',0,'Pago deuda de monto 0'),(1629,0,'2005-04-24',0,'Pago deuda de monto 0'),(1633,0,'2005-04-18',0,'Pago deuda de monto 0'),(1637,0,'2000-12-01',0,'Pago deuda de monto 0'),(1641,0,'2001-01-01',0,'Pago deuda de monto 0'),(1645,0,'2005-09-24',0,'Pago deuda de monto 0'),(1649,0,'2013-09-13',0,'Pago deuda de monto 0'),(1653,0,'2003-04-15',0,'Pago deuda de monto 0'),(1657,0,'2013-07-30',0,'Pago deuda de monto 0'),(1661,0,'1999-05-30',0,'Pago deuda de monto 0'),(1665,0,'2005-08-20',0,'Pago deuda de monto 0'),(1669,0,'2010-11-20',0,'Pago deuda de monto 0'),(1673,0,'2004-02-11',0,'Pago deuda de monto 0'),(1677,0,'2010-06-10',0,'Pago deuda de monto 0'),(1681,0,'2013-12-10',0,'Pago deuda de monto 0'),(1685,0,'2002-08-10',0,'Pago deuda de monto 0'),(1689,0,'2014-03-24',0,'Pago deuda de monto 0'),(1693,0,'1999-01-27',0,'Pago deuda de monto 0'),(1697,0,'1999-04-09',0,'Pago deuda de monto 0'),(1701,0,'2000-07-22',0,'Pago deuda de monto 0'),(1705,0,'2010-04-30',0,'Pago deuda de monto 0'),(1709,0,'2002-04-23',0,'Pago deuda de monto 0'),(1713,0,'2004-12-14',0,'Pago deuda de monto 0'),(1717,0,'2008-06-14',0,'Pago deuda de monto 0'),(1721,0,'2010-08-08',0,'Pago deuda de monto 0'),(1725,0,'2003-11-13',0,'Pago deuda de monto 0'),(1729,0,'2010-02-16',0,'Pago deuda de monto 0'),(1733,0,'1999-03-20',0,'Pago deuda de monto 0'),(1737,0,'2006-04-21',0,'Pago deuda de monto 0'),(1741,0,'2006-06-14',0,'Pago deuda de monto 0'),(1745,0,'2009-05-26',0,'Pago deuda de monto 0'),(1749,0,'2006-02-18',0,'Pago deuda de monto 0'),(1753,0,'2004-08-02',0,'Pago deuda de monto 0'),(1757,0,'2010-02-21',0,'Pago deuda de monto 0'),(1761,0,'2008-07-24',0,'Pago deuda de monto 0'),(1765,0,'2002-07-10',0,'Pago deuda de monto 0'),(1769,0,'2010-09-07',0,'Pago deuda de monto 0'),(1773,0,'2001-04-13',0,'Pago deuda de monto 0'),(1777,0,'2011-11-22',0,'Pago deuda de monto 0'),(1781,0,'2014-04-04',0,'Pago deuda de monto 0'),(1785,0,'2012-02-26',0,'Pago deuda de monto 0'),(1789,0,'2011-05-27',0,'Pago deuda de monto 0'),(1793,0,'2011-07-19',0,'Pago deuda de monto 0'),(1797,0,'2000-10-05',0,'Pago deuda de monto 0'),(1801,0,'2000-08-16',0,'Pago deuda de monto 0'),(1805,0,'2013-06-26',0,'Pago deuda de monto 0'),(1809,0,'2004-01-22',0,'Pago deuda de monto 0'),(1813,0,'2003-06-13',0,'Pago deuda de monto 0'),(1817,0,'1999-08-26',0,'Pago deuda de monto 0'),(1821,0,'2007-06-15',0,'Pago deuda de monto 0'),(1825,0,'2004-09-03',0,'Pago deuda de monto 0'),(1829,0,'2010-08-07',0,'Pago deuda de monto 0'),(1833,0,'2007-05-26',0,'Pago deuda de monto 0'),(1837,0,'2011-07-15',0,'Pago deuda de monto 0'),(1841,0,'2008-02-11',0,'Pago deuda de monto 0'),(1845,0,'2010-05-26',0,'Pago deuda de monto 0'),(1849,0,'2010-05-04',0,'Pago deuda de monto 0'),(1853,0,'2003-09-27',0,'Pago deuda de monto 0'),(1857,0,'1999-11-10',0,'Pago deuda de monto 0'),(1861,0,'2006-09-22',0,'Pago deuda de monto 0'),(1865,0,'2007-02-02',0,'Pago deuda de monto 0'),(1869,0,'2008-12-14',0,'Pago deuda de monto 0'),(1873,0,'2012-10-25',0,'Pago deuda de monto 0'),(1877,0,'2000-05-16',0,'Pago deuda de monto 0'),(1881,0,'2008-10-11',0,'Pago deuda de monto 0'),(1885,0,'2002-05-01',0,'Pago deuda de monto 0'),(1889,0,'2010-12-22',0,'Pago deuda de monto 0'),(1893,0,'2012-10-05',0,'Pago deuda de monto 0'),(1897,0,'2001-03-05',0,'Pago deuda de monto 0'),(1901,0,'2010-10-24',0,'Pago deuda de monto 0'),(1905,0,'2003-10-12',0,'Pago deuda de monto 0'),(1909,0,'2009-08-01',0,'Pago deuda de monto 0'),(1913,0,'1999-08-24',0,'Pago deuda de monto 0'),(1917,0,'2011-07-18',0,'Pago deuda de monto 0'),(1921,0,'2009-03-31',0,'Pago deuda de monto 0'),(1925,0,'2006-02-11',0,'Pago deuda de monto 0'),(1929,0,'2004-03-25',0,'Pago deuda de monto 0'),(1933,0,'2008-01-16',0,'Pago deuda de monto 0'),(1937,0,'2002-09-21',0,'Pago deuda de monto 0'),(1941,0,'2003-03-16',0,'Pago deuda de monto 0'),(1945,0,'2002-08-08',0,'Pago deuda de monto 0'),(1949,0,'2012-11-09',0,'Pago deuda de monto 0'),(1953,0,'2012-03-30',0,'Pago deuda de monto 0'),(1957,0,'2007-07-19',0,'Pago deuda de monto 0'),(1961,0,'1999-04-07',0,'Pago deuda de monto 0'),(1965,0,'2007-12-03',0,'Pago deuda de monto 0'),(1969,0,'2014-05-14',0,'Pago deuda de monto 0'),(1973,0,'2006-02-13',0,'Pago deuda de monto 0');
/*!40000 ALTER TABLE `pagocuota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partido`
--

DROP TABLE IF EXISTS `partido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partido` (
  `IDPARTIDO` bigint(20) NOT NULL,
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `FECHA` date DEFAULT NULL,
  `JUGADO` tinyint(1) DEFAULT '0',
  `NOMBREAYUDANTEMESALOCAL` varchar(255) DEFAULT NULL,
  `NOMBREAYUDANTEMESAVISITANTE` varchar(255) DEFAULT NULL,
  `NOMBREVEEDOR` varchar(255) DEFAULT NULL,
  `OBSERVACIONES` varchar(1000) DEFAULT NULL,
  `UNAYUDANTECAMPOLOCAL` longblob,
  `UNAYUDANTECAMPOVISITANTE` longblob,
  `UNDTLOCAL` longblob,
  `UNDTVISITANTE` longblob,
  `UNPREPARADORFISICOLOCAL` longblob,
  `UNPREPARADORFISICOVISITANTE` longblob,
  `UNARBITRO1_DNI` bigint(20) DEFAULT NULL,
  `UNARBITRO2_DNI` bigint(20) DEFAULT NULL,
  `UNARBITRO3_DNI` bigint(20) DEFAULT NULL,
  `UNEQUIPOLOCAL_IDEQUIPO` bigint(20) DEFAULT NULL,
  `UNEQUIPOVISITANTE_IDEQUIPO` bigint(20) DEFAULT NULL,
  `UNACANCHA_IDCANCHA` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`IDPARTIDO`),
  KEY `FK_PARTIDO_UNEQUIPOVISITANTE_IDEQUIPO` (`UNEQUIPOVISITANTE_IDEQUIPO`),
  KEY `FK_PARTIDO_UNARBITRO3_DNI` (`UNARBITRO3_DNI`),
  KEY `FK_PARTIDO_UNEQUIPOLOCAL_IDEQUIPO` (`UNEQUIPOLOCAL_IDEQUIPO`),
  KEY `FK_PARTIDO_UNACANCHA_IDCANCHA` (`UNACANCHA_IDCANCHA`),
  KEY `FK_PARTIDO_UNARBITRO1_DNI` (`UNARBITRO1_DNI`),
  KEY `FK_PARTIDO_UNARBITRO2_DNI` (`UNARBITRO2_DNI`),
  CONSTRAINT `FK_PARTIDO_UNACANCHA_IDCANCHA` FOREIGN KEY (`UNACANCHA_IDCANCHA`) REFERENCES `cancha` (`IDCANCHA`),
  CONSTRAINT `FK_PARTIDO_UNARBITRO1_DNI` FOREIGN KEY (`UNARBITRO1_DNI`) REFERENCES `personaauxiliar` (`DNI`),
  CONSTRAINT `FK_PARTIDO_UNARBITRO2_DNI` FOREIGN KEY (`UNARBITRO2_DNI`) REFERENCES `personaauxiliar` (`DNI`),
  CONSTRAINT `FK_PARTIDO_UNARBITRO3_DNI` FOREIGN KEY (`UNARBITRO3_DNI`) REFERENCES `personaauxiliar` (`DNI`),
  CONSTRAINT `FK_PARTIDO_UNEQUIPOLOCAL_IDEQUIPO` FOREIGN KEY (`UNEQUIPOLOCAL_IDEQUIPO`) REFERENCES `equipo` (`IDEQUIPO`),
  CONSTRAINT `FK_PARTIDO_UNEQUIPOVISITANTE_IDEQUIPO` FOREIGN KEY (`UNEQUIPOVISITANTE_IDEQUIPO`) REFERENCES `equipo` (`IDEQUIPO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partido`
--

LOCK TABLES `partido` WRITE;
/*!40000 ALTER TABLE `partido` DISABLE KEYS */;
/*!40000 ALTER TABLE `partido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partido_gol`
--

DROP TABLE IF EXISTS `partido_gol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partido_gol` (
  `Partido_IDPARTIDO` bigint(20) NOT NULL,
  `goles_IDGOL` bigint(20) NOT NULL,
  PRIMARY KEY (`Partido_IDPARTIDO`,`goles_IDGOL`),
  KEY `FK_PARTIDO_GOL_goles_IDGOL` (`goles_IDGOL`),
  CONSTRAINT `FK_PARTIDO_GOL_Partido_IDPARTIDO` FOREIGN KEY (`Partido_IDPARTIDO`) REFERENCES `partido` (`IDPARTIDO`),
  CONSTRAINT `FK_PARTIDO_GOL_goles_IDGOL` FOREIGN KEY (`goles_IDGOL`) REFERENCES `gol` (`IDGOL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partido_gol`
--

LOCK TABLES `partido_gol` WRITE;
/*!40000 ALTER TABLE `partido_gol` DISABLE KEYS */;
/*!40000 ALTER TABLE `partido_gol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partido_jugadoras`
--

DROP TABLE IF EXISTS `partido_jugadoras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partido_jugadoras` (
  `CAMISETA` varchar(255) DEFAULT NULL,
  `LOCAL` tinyint(1) DEFAULT '0',
  `UNASOCIA_DNI` bigint(20) DEFAULT NULL,
  `Partido_IDPARTIDO` bigint(20) DEFAULT NULL,
  KEY `FK_Partido_JUGADORAS_Partido_IDPARTIDO` (`Partido_IDPARTIDO`),
  CONSTRAINT `FK_Partido_JUGADORAS_Partido_IDPARTIDO` FOREIGN KEY (`Partido_IDPARTIDO`) REFERENCES `partido` (`IDPARTIDO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partido_jugadoras`
--

LOCK TABLES `partido_jugadoras` WRITE;
/*!40000 ALTER TABLE `partido_jugadoras` DISABLE KEYS */;
/*!40000 ALTER TABLE `partido_jugadoras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partido_tarjeta`
--

DROP TABLE IF EXISTS `partido_tarjeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partido_tarjeta` (
  `Partido_IDPARTIDO` bigint(20) NOT NULL,
  `tarjetas_IDTARJETA` bigint(20) NOT NULL,
  PRIMARY KEY (`Partido_IDPARTIDO`,`tarjetas_IDTARJETA`),
  KEY `FK_PARTIDO_TARJETA_tarjetas_IDTARJETA` (`tarjetas_IDTARJETA`),
  CONSTRAINT `FK_PARTIDO_TARJETA_Partido_IDPARTIDO` FOREIGN KEY (`Partido_IDPARTIDO`) REFERENCES `partido` (`IDPARTIDO`),
  CONSTRAINT `FK_PARTIDO_TARJETA_tarjetas_IDTARJETA` FOREIGN KEY (`tarjetas_IDTARJETA`) REFERENCES `tarjeta` (`IDTARJETA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partido_tarjeta`
--

LOCK TABLES `partido_tarjeta` WRITE;
/*!40000 ALTER TABLE `partido_tarjeta` DISABLE KEYS */;
/*!40000 ALTER TABLE `partido_tarjeta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pase`
--

DROP TABLE IF EXISTS `pase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pase` (
  `IDPASE` bigint(20) NOT NULL,
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `FECHA` date DEFAULT NULL,
  `LIBREDEUDACLUB` tinyint(1) DEFAULT '0',
  `OBSERVACION` varchar(1000) DEFAULT NULL,
  `SOLICITUDPASE` tinyint(1) DEFAULT '0',
  `UNEQUIPO_IDEQUIPO` bigint(20) DEFAULT NULL,
  `UNADEUDA_IDDEUDA` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`IDPASE`),
  KEY `FK_PASE_UNEQUIPO_IDEQUIPO` (`UNEQUIPO_IDEQUIPO`),
  KEY `FK_PASE_UNADEUDA_IDDEUDA` (`UNADEUDA_IDDEUDA`),
  CONSTRAINT `FK_PASE_UNADEUDA_IDDEUDA` FOREIGN KEY (`UNADEUDA_IDDEUDA`) REFERENCES `deuda` (`IDDEUDA`),
  CONSTRAINT `FK_PASE_UNEQUIPO_IDEQUIPO` FOREIGN KEY (`UNEQUIPO_IDEQUIPO`) REFERENCES `equipo` (`IDEQUIPO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pase`
--

LOCK TABLES `pase` WRITE;
/*!40000 ALTER TABLE `pase` DISABLE KEYS */;
INSERT INTO `pase` VALUES (479,0,'2010-02-02',1,'',0,11,478),(483,0,'2002-03-01',1,'',0,13,482),(487,0,'2010-03-17',1,'',0,17,486),(491,0,'2004-05-31',1,'',0,2,490),(495,0,'2008-09-03',1,'',0,1,494),(499,0,'2009-07-30',1,'',0,2,498),(503,0,'2005-10-05',1,'',0,22,502),(507,0,'2005-08-27',1,'',0,20,506),(511,0,'1999-01-27',1,'',0,14,510),(515,0,'2006-06-19',1,'',0,16,514),(519,0,'1999-04-09',1,'',0,17,518),(523,0,'2012-06-14',1,'',0,24,522),(527,0,'2004-06-29',1,'',0,4,526),(531,0,'2013-03-13',1,'',0,9,530),(535,0,'2012-02-07',1,'',0,18,534),(539,0,'2014-01-25',1,'',0,1,538),(543,0,'2000-12-29',1,'',0,22,542),(547,0,'2013-05-16',1,'',0,11,546),(551,0,'2006-09-09',1,'',0,19,550),(555,0,'2002-02-20',1,'',0,24,554),(559,0,'2001-11-21',1,'',0,24,558),(563,0,'2007-10-20',1,'',0,25,562),(567,0,'2008-05-17',1,'',0,11,566),(571,0,'2012-11-01',1,'',0,14,570),(575,0,'1999-06-25',1,'',0,10,574),(579,0,'2000-10-20',1,'',0,24,578),(583,0,'2011-12-07',1,'',0,1,582),(587,0,'2003-06-01',1,'',0,10,586),(591,0,'2002-02-05',1,'',0,12,590),(595,0,'2011-05-26',1,'',0,25,594),(599,0,'2014-02-24',1,'',0,14,598),(603,0,'2004-10-13',1,'',0,3,602),(607,0,'2005-07-09',1,'',0,19,606),(611,0,'2014-06-21',1,'',0,10,610),(615,0,'2008-11-29',1,'',0,18,614),(619,0,'2010-01-13',1,'',0,17,618),(623,0,'2013-03-25',1,'',0,8,622),(627,0,'2001-11-25',1,'',0,20,626),(631,0,'1999-07-09',1,'',0,13,630),(635,0,'2013-04-10',1,'',0,6,634),(639,0,'2013-01-04',1,'',0,23,638),(643,0,'2006-11-08',1,'',0,10,642),(647,0,'2001-10-12',1,'',0,19,646),(651,0,'2012-08-27',1,'',0,21,650),(655,0,'2002-09-20',1,'',0,8,654),(659,0,'2007-01-26',1,'',0,9,658),(663,0,'2014-04-07',1,'',0,4,662),(667,0,'2008-02-12',1,'',0,1,666),(671,0,'2010-05-29',1,'',0,13,670),(675,0,'1999-04-23',1,'',0,10,674),(679,0,'2009-05-27',1,'',0,16,678),(683,0,'2011-02-13',1,'',0,20,682),(687,0,'2008-12-01',1,'',0,11,686),(691,0,'2002-09-20',1,'',0,24,690),(695,0,'2000-08-29',1,'',0,7,694),(699,0,'2008-03-20',1,'',0,4,698),(703,0,'2011-10-10',1,'',0,18,702),(707,0,'2010-12-17',1,'',0,19,706),(711,0,'2010-04-18',1,'',0,21,710),(715,0,'1999-03-25',1,'',0,12,714),(719,0,'2014-02-03',1,'',0,4,718),(723,0,'2005-03-26',1,'',0,10,722),(727,0,'2007-08-18',1,'',0,24,726),(731,0,'2004-09-25',1,'',0,14,730),(735,0,'2010-05-17',1,'',0,12,734),(739,0,'2003-02-10',1,'',0,13,738),(743,0,'2000-04-11',1,'',0,14,742),(747,0,'2010-06-06',1,'',0,21,746),(751,0,'2008-09-18',1,'',0,17,750),(755,0,'2005-03-13',1,'',0,22,754),(759,0,'2008-07-07',1,'',0,17,758),(763,0,'2003-09-07',1,'',0,14,762),(767,0,'2000-12-13',1,'',0,18,766),(771,0,'2005-07-22',1,'',0,22,770),(775,0,'2001-03-02',1,'',0,16,774),(779,0,'2004-12-23',1,'',0,7,778),(783,0,'2012-05-30',1,'',0,2,782),(787,0,'2010-04-01',1,'',0,22,786),(791,0,'2010-02-05',1,'',0,2,790),(795,0,'2001-10-12',1,'',0,23,794),(799,0,'2012-04-11',1,'',0,15,798),(803,0,'2010-09-16',1,'',0,6,802),(807,0,'2004-07-20',1,'',0,25,806),(811,0,'2006-09-22',1,'',0,6,810),(815,0,'2006-12-03',1,'',0,5,814),(819,0,'2003-05-02',1,'',0,10,818),(823,0,'2001-04-16',1,'',0,11,822),(827,0,'2004-03-04',1,'',0,15,826),(831,0,'2000-12-08',1,'',0,1,830),(835,0,'1999-03-14',1,'',0,23,834),(839,0,'2013-02-17',1,'',0,23,838),(843,0,'2000-03-28',1,'',0,3,842),(847,0,'2000-04-23',1,'',0,10,846),(851,0,'2003-07-26',1,'',0,6,850),(855,0,'2007-05-12',1,'',0,6,854),(859,0,'2000-09-10',1,'',0,10,858),(863,0,'2011-01-30',1,'',0,23,862),(867,0,'2012-07-03',1,'',0,19,866),(871,0,'1999-09-12',1,'',0,24,870),(875,0,'2001-05-05',1,'',0,13,874),(879,0,'2010-12-08',1,'',0,25,878),(883,0,'2003-12-17',1,'',0,2,882),(887,0,'2003-04-12',1,'',0,20,886),(891,0,'2002-01-02',1,'',0,23,890),(895,0,'2008-12-12',1,'',0,20,894),(899,0,'2010-07-14',1,'',0,25,898),(903,0,'2003-06-10',1,'',0,13,902),(907,0,'2011-07-31',1,'',0,10,906),(911,0,'2013-05-10',1,'',0,4,910),(915,0,'2000-08-18',1,'',0,15,914),(919,0,'2010-12-19',1,'',0,20,918),(923,0,'2009-12-01',1,'',0,12,922),(927,0,'2001-03-26',1,'',0,21,926),(931,0,'2004-06-02',1,'',0,8,930),(935,0,'2002-12-13',1,'',0,4,934),(939,0,'2002-11-23',1,'',0,19,938),(943,0,'2010-11-30',1,'',0,16,942),(947,0,'2006-08-09',1,'',0,6,946),(951,0,'2009-07-17',1,'',0,2,950),(955,0,'2010-04-13',1,'',0,11,954),(959,0,'2010-09-18',1,'',0,22,958),(963,0,'2010-02-12',1,'',0,6,962),(967,0,'2006-09-27',1,'',0,25,966),(971,0,'2002-10-31',1,'',0,6,970),(975,0,'2013-04-30',1,'',0,22,974),(979,0,'2002-08-27',1,'',0,12,978),(983,0,'2009-07-26',1,'',0,14,982),(987,0,'2006-01-28',1,'',0,22,986),(991,0,'2010-04-19',1,'',0,21,990),(995,0,'2010-10-30',1,'',0,13,994),(999,0,'2003-06-27',1,'',0,9,998),(1003,0,'2009-11-21',1,'',0,12,1002),(1007,0,'2009-11-12',1,'',0,14,1006),(1011,0,'2002-10-24',1,'',0,10,1010),(1015,0,'2002-12-14',1,'',0,20,1014),(1019,0,'2006-04-13',1,'',0,23,1018),(1023,0,'2012-12-20',1,'',0,4,1022),(1027,0,'2005-08-21',1,'',0,19,1026),(1031,0,'2005-08-12',1,'',0,25,1030),(1035,0,'2009-11-07',1,'',0,12,1034),(1039,0,'2002-04-14',1,'',0,10,1038),(1043,0,'1999-01-26',1,'',0,15,1042),(1047,0,'2005-11-23',1,'',0,13,1046),(1051,0,'2001-05-17',1,'',0,7,1050),(1055,0,'2006-12-03',1,'',0,10,1054),(1059,0,'2007-09-25',1,'',0,20,1058),(1063,0,'1999-06-18',1,'',0,18,1062),(1067,0,'2013-08-23',1,'',0,19,1066),(1071,0,'1999-10-14',1,'',0,20,1070),(1075,0,'1999-07-19',1,'',0,15,1074),(1079,0,'2012-05-19',1,'',0,6,1078),(1083,0,'2000-04-26',1,'',0,12,1082),(1087,0,'2008-05-06',1,'',0,23,1086),(1091,0,'2006-12-21',1,'',0,7,1090),(1095,0,'1999-07-28',1,'',0,9,1094),(1099,0,'2014-02-15',1,'',0,22,1098),(1103,0,'2003-10-31',1,'',0,18,1102),(1107,0,'2006-07-08',1,'',0,8,1106),(1111,0,'2005-01-05',1,'',0,14,1110),(1115,0,'2011-01-22',1,'',0,15,1114),(1119,0,'2009-03-09',1,'',0,4,1118),(1123,0,'2001-09-25',1,'',0,18,1122),(1127,0,'2009-02-12',1,'',0,3,1126),(1131,0,'2004-07-20',1,'',0,23,1130),(1135,0,'2002-05-21',1,'',0,2,1134),(1139,0,'2007-07-18',1,'',0,1,1138),(1143,0,'2013-03-13',1,'',0,9,1142),(1147,0,'2006-12-23',1,'',0,9,1146),(1151,0,'1999-03-03',1,'',0,24,1150),(1155,0,'1999-05-14',1,'',0,19,1154),(1159,0,'2006-12-06',1,'',0,5,1158),(1163,0,'2011-06-12',1,'',0,6,1162),(1167,0,'2004-02-04',1,'',0,24,1166),(1171,0,'2012-06-15',1,'',0,10,1170),(1175,0,'2005-08-27',1,'',0,6,1174),(1179,0,'2003-05-02',1,'',0,3,1178),(1183,0,'2007-02-13',1,'',0,5,1182),(1187,0,'2004-09-13',1,'',0,12,1186),(1191,0,'2003-04-26',1,'',0,18,1190),(1195,0,'2007-12-18',1,'',0,3,1194),(1199,0,'2012-01-15',1,'',0,1,1198),(1203,0,'2004-12-05',1,'',0,23,1202),(1207,0,'2007-05-28',1,'',0,12,1206),(1211,0,'2003-10-13',1,'',0,6,1210),(1215,0,'2012-09-05',1,'',0,8,1214),(1219,0,'2001-02-21',1,'',0,8,1218),(1223,0,'2002-09-11',1,'',0,14,1222),(1227,0,'2004-08-12',1,'',0,23,1226),(1231,0,'2001-08-27',1,'',0,20,1230),(1235,0,'2011-06-09',1,'',0,1,1234),(1239,0,'2004-03-23',1,'',0,25,1238),(1243,0,'2001-04-08',1,'',0,8,1242),(1247,0,'2013-01-17',1,'',0,22,1246),(1251,0,'2004-10-10',1,'',0,7,1250),(1255,0,'2010-09-06',1,'',0,5,1254),(1259,0,'2013-12-07',1,'',0,20,1258),(1263,0,'2001-01-17',1,'',0,5,1262),(1267,0,'2007-03-01',1,'',0,7,1266),(1271,0,'2003-03-11',1,'',0,12,1270),(1275,0,'2013-04-02',1,'',0,18,1274),(1279,0,'2005-10-20',1,'',0,2,1278),(1283,0,'2009-12-22',1,'',0,18,1282),(1287,0,'2001-07-20',1,'',0,5,1286),(1291,0,'2002-05-03',1,'',0,24,1290),(1295,0,'1999-09-02',1,'',0,8,1294),(1299,0,'2009-07-22',1,'',0,11,1298),(1303,0,'2012-06-19',1,'',0,3,1302),(1307,0,'2004-05-07',1,'',0,10,1306),(1311,0,'1999-11-07',1,'',0,12,1310),(1315,0,'2007-09-26',1,'',0,19,1314),(1319,0,'2014-05-14',1,'',0,15,1318),(1323,0,'2006-02-04',1,'',0,21,1322),(1327,0,'2006-06-17',1,'',0,23,1326),(1331,0,'2014-07-26',1,'',0,22,1330),(1335,0,'2009-01-13',1,'',0,1,1334),(1339,0,'2006-07-22',1,'',0,22,1338),(1343,0,'2000-05-07',1,'',0,4,1342),(1347,0,'2012-05-06',1,'',0,16,1346),(1351,0,'2000-04-03',1,'',0,16,1350),(1355,0,'2005-04-06',1,'',0,1,1354),(1359,0,'2010-01-22',1,'',0,19,1358),(1363,0,'1999-12-28',1,'',0,20,1362),(1367,0,'2006-10-12',1,'',0,14,1366),(1371,0,'2009-08-08',1,'',0,9,1370),(1375,0,'2012-01-14',1,'',0,12,1374),(1379,0,'2010-01-11',1,'',0,14,1378),(1383,0,'2014-01-11',1,'',0,19,1382),(1387,0,'2006-04-03',1,'',0,22,1386),(1391,0,'2003-10-20',1,'',0,3,1390),(1395,0,'2008-03-12',1,'',0,19,1394),(1399,0,'2012-04-23',1,'',0,2,1398),(1403,0,'2011-10-09',1,'',0,11,1402),(1407,0,'1999-06-20',1,'',0,5,1406),(1411,0,'2000-04-24',1,'',0,16,1410),(1415,0,'2014-05-15',1,'',0,15,1414),(1419,0,'2011-12-12',1,'',0,10,1418),(1423,0,'1999-05-22',1,'',0,18,1422),(1427,0,'2005-03-22',1,'',0,14,1426),(1431,0,'2006-07-19',1,'',0,23,1430),(1435,0,'2003-03-15',1,'',0,2,1434),(1439,0,'2010-04-17',1,'',0,19,1438),(1443,0,'2000-12-20',1,'',0,20,1442),(1447,0,'2014-03-23',1,'',0,23,1446),(1451,0,'2004-09-03',1,'',0,23,1450),(1455,0,'2000-06-11',1,'',0,19,1454),(1459,0,'2004-02-15',1,'',0,3,1458),(1463,0,'2002-05-24',1,'',0,7,1462),(1467,0,'2008-02-22',1,'',0,8,1466),(1471,0,'2001-06-14',1,'',0,3,1470),(1475,0,'2011-11-12',1,'',0,3,1474),(1479,0,'2005-06-03',1,'',0,24,1478),(1483,0,'2004-10-12',1,'',0,3,1482),(1487,0,'2010-07-01',1,'',0,25,1486),(1491,0,'2003-06-26',1,'',0,1,1490),(1495,0,'2010-04-04',1,'',0,2,1494),(1499,0,'2009-02-04',1,'',0,20,1498),(1503,0,'2001-03-29',1,'',0,7,1502),(1507,0,'2007-02-21',1,'',0,11,1506),(1511,0,'2001-07-28',1,'',0,12,1510),(1515,0,'2007-11-21',1,'',0,12,1514),(1519,0,'2002-10-30',1,'',0,12,1518),(1523,0,'2005-12-08',1,'',0,3,1522),(1527,0,'2014-03-05',1,'',0,1,1526),(1531,0,'2010-11-08',1,'',0,20,1530),(1535,0,'2007-03-10',1,'',0,4,1534),(1539,0,'2013-04-20',1,'',0,19,1538),(1543,0,'2001-07-17',1,'',0,12,1542),(1547,0,'2001-10-27',1,'',0,10,1546),(1551,0,'2008-07-30',1,'',0,2,1550),(1555,0,'2005-02-28',1,'',0,20,1554),(1559,0,'2006-05-27',1,'',0,23,1558),(1563,0,'2009-06-18',1,'',0,14,1562),(1567,0,'2009-09-12',1,'',0,21,1566),(1571,0,'2000-03-31',1,'',0,4,1570),(1575,0,'2002-01-15',1,'',0,19,1574),(1579,0,'2004-05-18',1,'',0,19,1578),(1583,0,'2003-11-01',1,'',0,21,1582),(1587,0,'2011-05-20',1,'',0,10,1586),(1591,0,'2008-07-07',1,'',0,6,1590),(1595,0,'2009-05-24',1,'',0,17,1594),(1599,0,'1999-10-30',1,'',0,9,1598),(1603,0,'2001-05-29',1,'',0,1,1602),(1607,0,'2012-10-14',1,'',0,6,1606),(1611,0,'2000-12-06',1,'',0,17,1610),(1615,0,'2000-02-16',1,'',0,4,1614),(1619,0,'2009-03-13',1,'',0,20,1618),(1623,0,'2014-05-04',1,'',0,25,1622),(1627,0,'2010-04-12',1,'',0,5,1626),(1631,0,'2005-04-24',1,'',0,5,1630),(1635,0,'2005-04-18',1,'',0,10,1634),(1639,0,'2000-12-01',1,'',0,18,1638),(1643,0,'2001-01-01',1,'',0,8,1642),(1647,0,'2005-09-24',1,'',0,12,1646),(1651,0,'2013-09-13',1,'',0,19,1650),(1655,0,'2003-04-15',1,'',0,12,1654),(1659,0,'2013-07-30',1,'',0,18,1658),(1663,0,'1999-05-30',1,'',0,12,1662),(1667,0,'2005-08-20',1,'',0,3,1666),(1671,0,'2010-11-20',1,'',0,9,1670),(1675,0,'2004-02-11',1,'',0,7,1674),(1679,0,'2010-06-10',1,'',0,20,1678),(1683,0,'2013-12-10',1,'',0,16,1682),(1687,0,'2002-08-10',1,'',0,23,1686),(1691,0,'2014-03-24',1,'',0,4,1690),(1695,0,'1999-01-27',1,'',0,6,1694),(1699,0,'1999-04-09',1,'',0,11,1698),(1703,0,'2000-07-22',1,'',0,22,1702),(1707,0,'2010-04-30',1,'',0,8,1706),(1711,0,'2002-04-23',1,'',0,9,1710),(1715,0,'2004-12-14',1,'',0,3,1714),(1719,0,'2008-06-14',1,'',0,10,1718),(1723,0,'2010-08-08',1,'',0,17,1722),(1727,0,'2003-11-13',1,'',0,16,1726),(1731,0,'2010-02-16',1,'',0,3,1730),(1735,0,'1999-03-20',1,'',0,21,1734),(1739,0,'2006-04-21',1,'',0,16,1738),(1743,0,'2006-06-14',1,'',0,10,1742),(1747,0,'2009-05-26',1,'',0,13,1746),(1751,0,'2006-02-18',1,'',0,24,1750),(1755,0,'2004-08-02',1,'',0,17,1754),(1759,0,'2010-02-21',1,'',0,18,1758),(1763,0,'2008-07-24',1,'',0,17,1762),(1767,0,'2002-07-10',1,'',0,3,1766),(1771,0,'2010-09-07',1,'',0,13,1770),(1775,0,'2001-04-13',1,'',0,11,1774),(1779,0,'2011-11-22',1,'',0,17,1778),(1783,0,'2014-04-04',1,'',0,3,1782),(1787,0,'2012-02-26',1,'',0,7,1786),(1791,0,'2011-05-27',1,'',0,3,1790),(1795,0,'2011-07-19',1,'',0,8,1794),(1799,0,'2000-10-05',1,'',0,20,1798),(1803,0,'2000-08-16',1,'',0,2,1802),(1807,0,'2013-06-26',1,'',0,23,1806),(1811,0,'2004-01-22',1,'',0,6,1810),(1815,0,'2003-06-13',1,'',0,10,1814),(1819,0,'1999-08-26',1,'',0,10,1818),(1823,0,'2007-06-15',1,'',0,20,1822),(1827,0,'2004-09-03',1,'',0,10,1826),(1831,0,'2010-08-07',1,'',0,19,1830),(1835,0,'2007-05-26',1,'',0,4,1834),(1839,0,'2011-07-15',1,'',0,13,1838),(1843,0,'2008-02-11',1,'',0,23,1842),(1847,0,'2010-05-26',1,'',0,23,1846),(1851,0,'2010-05-04',1,'',0,15,1850),(1855,0,'2003-09-27',1,'',0,23,1854),(1859,0,'1999-11-10',1,'',0,24,1858),(1863,0,'2006-09-22',1,'',0,2,1862),(1867,0,'2007-02-02',1,'',0,8,1866),(1871,0,'2008-12-14',1,'',0,13,1870),(1875,0,'2012-10-25',1,'',0,24,1874),(1879,0,'2000-05-16',1,'',0,20,1878),(1883,0,'2008-10-11',1,'',0,21,1882),(1887,0,'2002-05-01',1,'',0,7,1886),(1891,0,'2010-12-22',1,'',0,5,1890),(1895,0,'2012-10-05',1,'',0,10,1894),(1899,0,'2001-03-05',1,'',0,21,1898),(1903,0,'2010-10-24',1,'',0,2,1902),(1907,0,'2003-10-12',1,'',0,2,1906),(1911,0,'2009-08-01',1,'',0,1,1910),(1915,0,'1999-08-24',1,'',0,20,1914),(1919,0,'2011-07-18',1,'',0,16,1918),(1923,0,'2009-03-31',1,'',0,5,1922),(1927,0,'2006-02-11',1,'',0,23,1926),(1931,0,'2004-03-25',1,'',0,7,1930),(1935,0,'2008-01-16',1,'',0,18,1934),(1939,0,'2002-09-21',1,'',0,24,1938),(1943,0,'2003-03-16',1,'',0,5,1942),(1947,0,'2002-08-08',1,'',0,4,1946),(1951,0,'2012-11-09',1,'',0,14,1950),(1955,0,'2012-03-30',1,'',0,25,1954),(1959,0,'2007-07-19',1,'',0,4,1958),(1963,0,'1999-04-07',1,'',0,1,1962),(1967,0,'2007-12-03',1,'',0,14,1966),(1971,0,'2014-05-14',1,'',0,13,1970),(1975,0,'2006-02-13',1,'',0,20,1974);
/*!40000 ALTER TABLE `pase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personaauxiliar`
--

DROP TABLE IF EXISTS `personaauxiliar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personaauxiliar` (
  `DNI` bigint(20) NOT NULL,
  `APELLIDO` varchar(100) DEFAULT NULL,
  `ARBITRO` tinyint(1) DEFAULT '0',
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `CUERPOTECNICO` tinyint(1) DEFAULT '0',
  `CUERPOTECNICOACTIVO` tinyint(1) DEFAULT '0',
  `DOMICILIO` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(100) DEFAULT NULL,
  `FECHAINGRESO` date DEFAULT NULL,
  `FECHANACIMIENTO` date DEFAULT NULL,
  `FOTOCOPIADNI` longblob,
  `NOMBRE` varchar(100) DEFAULT NULL,
  `PLANTAPERMANENTE` tinyint(1) DEFAULT '0',
  `TELCELULAR` varchar(255) DEFAULT NULL,
  `TELFIJO` varchar(255) DEFAULT NULL,
  `UNALOCALIDAD_IDLOCALIDAD` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DNI`),
  KEY `FK_PERSONAAUXILIAR_UNALOCALIDAD_IDLOCALIDAD` (`UNALOCALIDAD_IDLOCALIDAD`),
  CONSTRAINT `FK_PERSONAAUXILIAR_UNALOCALIDAD_IDLOCALIDAD` FOREIGN KEY (`UNALOCALIDAD_IDLOCALIDAD`) REFERENCES `localidad` (`IDLOCALIDAD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personaauxiliar`
--

LOCK TABLES `personaauxiliar` WRITE;
/*!40000 ALTER TABLE `personaauxiliar` DISABLE KEYS */;
INSERT INTO `personaauxiliar` VALUES (24558997,'Genessini',1,0,1,0,'Sarmiento412','Genessini@gmail.com','2003-02-14','1972-11-03','','Fernando',0,'37646699745','',1),(27633987,'Traverso',1,0,1,0,'Israel411','Traverso@gmail.com','2002-05-13','1974-12-23','','Francisca',0,'3764159763','',1),(28745663,'Elizondo',1,0,0,0,'LaRioja','Elizondo@gmail.com','2008-01-23','1958-10-03','','Sarsenio',0,'37646654123','',1),(28996332,'Hojaldre',1,0,1,0,'Saltalafea147','Hojaldre@gmail.com','2008-04-05','1970-07-13','','Claudia',0,'3764348712','',1),(28996333,'Rojas',1,0,1,0,'Saltalafea147','Hojaldre@gmail.com','2008-04-05','1970-07-13','','Rocio',0,'3764348712','',1),(28996334,'Andrusisin',1,0,1,0,'Saltalafea147','Hojaldre@gmail.com','2008-04-05','1970-07-13','','Angelica',0,'3764348712','',1),(28996335,'Gomes',1,0,1,0,'Saltalafea147','Hojaldre@gmail.com','2008-04-05','1970-07-13','','Belen',0,'3764348712','',1),(28996336,'Gilbert',1,0,1,0,'Saltalafea147','Hojaldre@gmail.com','2008-04-05','1970-07-13','','Andrea',0,'3764348712','',1),(28996337,'Vegas',1,0,1,0,'Saltalafea147','Hojaldre@gmail.com','2008-04-05','1970-07-13','','Romina',0,'3764348712','',1),(28996338,'Rambo',1,0,1,0,'Saltalafea147','Hojaldre@gmail.com','2008-04-05','1970-07-13','','Soledad',0,'3764348712','',1),(28996339,'Olivera',1,0,1,0,'Saltalafea147','Hojaldre@gmail.com','2008-04-05','1970-07-13','','Isabel',0,'3764348712','',1),(28996340,'Camissasa',1,0,1,0,'Saltalafea147','Hojaldre@gmail.com','2008-04-05','1970-07-13','','Mariela',0,'3764348712','',1),(28996341,'Oberstar',1,0,1,0,'Saltalafea147','Hojaldre@gmail.com','2008-04-05','1970-07-13','','Marisa',0,'3764348712','',1),(28996342,'Montoto',1,0,1,0,'Saltalafea147','Hojaldre@gmail.com','2008-04-05','1970-07-13','','Silvina',0,'3764348712','',1),(28996343,'Fortunato',1,0,1,0,'Saltalafea147','Hojaldre@gmail.com','2008-04-05','1970-07-13','','Ruth',0,'3764348712','',1),(28996344,'Sanadria',1,0,1,0,'Saltalafea147','Hojaldre@gmail.com','2008-04-05','1970-07-13','','Mariana',0,'3764348712','',1),(28996345,'Bianchi',1,0,1,0,'Saltalafea147','Hojaldre@gmail.com','2008-04-05','1970-07-13','','Maria',0,'3764348712','',1),(28996346,'Sorje',1,0,1,0,'Saltalafea147','Hojaldre@gmail.com','2008-04-05','1970-07-13','','Mabel',0,'3764348712','',1),(28996347,'Justiniano',1,0,1,0,'Saltalafea147','Hojaldre@gmail.com','2008-04-05','1970-07-13','','Liliana',0,'3764348712','',1),(28996348,'Lichsten',1,0,1,0,'Saltalafea147','Hojaldre@gmail.com','2008-04-05','1970-07-13','','Alejandra',0,'3764348712','',1),(28996349,'Toledo',1,0,1,0,'Saltalafea147','Hojaldre@gmail.com','2008-04-05','1970-07-13','','Gladis',0,'3764348712','',1),(28996350,'Kilimilk',1,0,1,0,'Saltalafea147','Hojaldre@gmail.com','2008-04-05','1970-07-13','','Sandra',0,'3764348712','',1),(28996351,'Allende',1,0,1,0,'Saltalafea147','Hojaldre@gmail.com','2008-04-05','1970-07-13','','Nilda',0,'3764348712','',1),(28996352,'Sapuaitano',1,0,1,0,'Saltalafea147','Hojaldre@gmail.com','2008-04-05','1970-07-13','','Graciela',0,'3764348712','',1),(28996353,'Fantino',1,0,1,0,'Saltalafea147','Hojaldre@gmail.com','2008-04-05','1970-07-13','','Florencia',0,'3764348712','',1),(29665741,'Lopez',1,0,1,0,'EntreRios213','lopezito@gmail.com','2010-03-14','1971-12-20','','Horacio',0,'3764887554','',1),(30251132,'Rojitas',0,0,1,0,'TeentraAgua141','Rojitas@gmail.com','2006-01-23','1969-10-03','','Facundo',0,'3764887554','',1);
/*!40000 ALTER TABLE `personaauxiliar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personaauxiliar_actascompromiso`
--

DROP TABLE IF EXISTS `personaauxiliar_actascompromiso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personaauxiliar_actascompromiso` (
  `PersonaAuxiliar_DNI` bigint(20) DEFAULT NULL,
  `ACTASCOMPROMISO` date DEFAULT NULL,
  KEY `PersonaAuxiliar_ACTASCOMPROMISOPersonaAuxiliar_DNI` (`PersonaAuxiliar_DNI`),
  CONSTRAINT `PersonaAuxiliar_ACTASCOMPROMISOPersonaAuxiliar_DNI` FOREIGN KEY (`PersonaAuxiliar_DNI`) REFERENCES `personaauxiliar` (`DNI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personaauxiliar_actascompromiso`
--

LOCK TABLES `personaauxiliar_actascompromiso` WRITE;
/*!40000 ALTER TABLE `personaauxiliar_actascompromiso` DISABLE KEYS */;
/*!40000 ALTER TABLE `personaauxiliar_actascompromiso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personaauxiliar_sanciontribunal`
--

DROP TABLE IF EXISTS `personaauxiliar_sanciontribunal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personaauxiliar_sanciontribunal` (
  `PersonaAuxiliar_DNI` bigint(20) NOT NULL,
  `sancionesTribunal_IDSANCIONTRIBUNAL` bigint(20) NOT NULL,
  PRIMARY KEY (`PersonaAuxiliar_DNI`,`sancionesTribunal_IDSANCIONTRIBUNAL`),
  KEY `PRSNXLRSANCIONTRIBUNALsncnsTrbnalIDSANCIONTRIBUNAL` (`sancionesTribunal_IDSANCIONTRIBUNAL`),
  CONSTRAINT `PERSONAAUXILIAR_SANCIONTRIBUNALPersonaAuxiliar_DNI` FOREIGN KEY (`PersonaAuxiliar_DNI`) REFERENCES `personaauxiliar` (`DNI`),
  CONSTRAINT `PRSNXLRSANCIONTRIBUNALsncnsTrbnalIDSANCIONTRIBUNAL` FOREIGN KEY (`sancionesTribunal_IDSANCIONTRIBUNAL`) REFERENCES `sanciontribunal` (`IDSANCIONTRIBUNAL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personaauxiliar_sanciontribunal`
--

LOCK TABLES `personaauxiliar_sanciontribunal` WRITE;
/*!40000 ALTER TABLE `personaauxiliar_sanciontribunal` DISABLE KEYS */;
/*!40000 ALTER TABLE `personaauxiliar_sanciontribunal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `planillapago`
--

DROP TABLE IF EXISTS `planillapago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `planillapago` (
  `ID` bigint(20) NOT NULL,
  `FECHAPAGO` date DEFAULT NULL,
  `MONTO` double DEFAULT NULL,
  `NRORECIBO` varchar(255) DEFAULT NULL,
  `RUTAPDF` varchar(500) DEFAULT NULL,
  `RESPONSABLEPAGO_DNI` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PLANILLAPAGO_RESPONSABLEPAGO_DNI` (`RESPONSABLEPAGO_DNI`),
  CONSTRAINT `FK_PLANILLAPAGO_RESPONSABLEPAGO_DNI` FOREIGN KEY (`RESPONSABLEPAGO_DNI`) REFERENCES `socia` (`DNI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `planillapago`
--

LOCK TABLES `planillapago` WRITE;
/*!40000 ALTER TABLE `planillapago` DISABLE KEYS */;
/*!40000 ALTER TABLE `planillapago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanciontribunal`
--

DROP TABLE IF EXISTS `sanciontribunal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sanciontribunal` (
  `IDSANCIONTRIBUNAL` bigint(20) NOT NULL,
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `CANTFECHAS` int(11) DEFAULT NULL,
  `CANTFECHASCUMPLIDAS` int(11) DEFAULT NULL,
  `DETALLES` varchar(1000) DEFAULT NULL,
  `FECHA` date DEFAULT NULL,
  `MOTIVO` varchar(1000) DEFAULT NULL,
  `NUMERORESOLUCION` varchar(255) DEFAULT NULL,
  `VENCIMIENTO` date DEFAULT NULL,
  `UNPARTIDO_IDPARTIDO` bigint(20) DEFAULT NULL,
  `UNATARJETA_IDTARJETA` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`IDSANCIONTRIBUNAL`),
  KEY `FK_SANCIONTRIBUNAL_UNPARTIDO_IDPARTIDO` (`UNPARTIDO_IDPARTIDO`),
  KEY `FK_SANCIONTRIBUNAL_UNATARJETA_IDTARJETA` (`UNATARJETA_IDTARJETA`),
  CONSTRAINT `FK_SANCIONTRIBUNAL_UNATARJETA_IDTARJETA` FOREIGN KEY (`UNATARJETA_IDTARJETA`) REFERENCES `tarjeta` (`IDTARJETA`),
  CONSTRAINT `FK_SANCIONTRIBUNAL_UNPARTIDO_IDPARTIDO` FOREIGN KEY (`UNPARTIDO_IDPARTIDO`) REFERENCES `partido` (`IDPARTIDO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanciontribunal`
--

LOCK TABLES `sanciontribunal` WRITE;
/*!40000 ALTER TABLE `sanciontribunal` DISABLE KEYS */;
/*!40000 ALTER TABLE `sanciontribunal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequence`
--

DROP TABLE IF EXISTS `sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence`
--

LOCK TABLES `sequence` WRITE;
/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
INSERT INTO `sequence` VALUES ('SEQ_GEN',2350);
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socia`
--

DROP TABLE IF EXISTS `socia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socia` (
  `DNI` bigint(20) NOT NULL,
  `APELLIDO` varchar(100) DEFAULT NULL,
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `DOMICILIO` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(100) DEFAULT NULL,
  `EXJUGADORA` tinyint(1) DEFAULT '0',
  `FECHAINGRESO` date DEFAULT NULL,
  `FECHANACIMIENTO` date DEFAULT NULL,
  `FOTOCARNET` longblob,
  `NOMBRE` varchar(100) DEFAULT NULL,
  `NUMEROCAMISETA` varchar(255) DEFAULT NULL,
  `TELCELULAR` varchar(255) DEFAULT NULL,
  `TELFIJO` varchar(255) DEFAULT NULL,
  `UNALOCALIDAD_IDLOCALIDAD` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DNI`),
  KEY `FK_SOCIA_UNALOCALIDAD_IDLOCALIDAD` (`UNALOCALIDAD_IDLOCALIDAD`),
  CONSTRAINT `FK_SOCIA_UNALOCALIDAD_IDLOCALIDAD` FOREIGN KEY (`UNALOCALIDAD_IDLOCALIDAD`) REFERENCES `localidad` (`IDLOCALIDAD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socia`
--

LOCK TABLES `socia` WRITE;
/*!40000 ALTER TABLE `socia` DISABLE KEYS */;
INSERT INTO `socia` VALUES (5917598,'QUEVEDO',0,'Domicilio 8117','email@email.com',0,'2010-02-02','1976-01-13','','ANDREA ALVARINA','60','154022092','4147668',2),(6074300,'Espindola',0,'Domicilio 6487','email@email.com',1,'2002-03-01','1971-07-26','','Dolores Concepcion','77','154640128','4894278',2),(10534228,'Cossiani',0,'Domicilio 1775','email@email.com',0,'2010-03-17','1955-01-25','','Gabriela Isabel','90','154014241','4823748',1),(10639418,'Soriano',0,'Domicilio 6905','email@email.com',0,'2004-05-31','1963-02-25','','Elida Susana','12','154124957','4848526',3),(10955361,'BAEZ',0,'Domicilio 8265','email@email.com',0,'2008-09-03','1979-02-06','','MIRTA TERESA','78','154219951','4727236',3),(11392148,'CAROGLIO',0,'Domicilio 4372','email@email.com',0,'2009-07-30','1956-12-15','','GLADYS BEATRIZ','5','154227638','4405302',3),(11399982,'ALMEIDA',0,'Domicilio 9435','email@email.com',0,'2005-10-05','1954-08-21','','Alba Epifania','27','154972979','4937459',1),(11479299,'BAREYRO',0,'Domicilio 3906','email@email.com',0,'2005-08-27','1953-10-07','','NORMA ESTELA','92','154343717','4259555',2),(11479449,'ALARCON',0,'Domicilio 3064','email@email.com',0,'1999-01-27','1960-02-03','','Gerónima Gladis','15','154653443','4777221',2),(11635309,'Ricatti',0,'Domicilio 6913','email@email.com',1,'2006-06-19','1978-11-29','','Beatriz Graciela','47','154185504','4424473',1),(11661464,'Benitez ',0,'Domicilio 4341','email@email.com',0,'1999-04-09','1971-02-22','','Carmen','18','154923744','4562322',3),(11694515,'SILVERO',0,'Domicilio 5001','email@email.com',1,'2012-06-14','1970-12-22','','MARIA ELENA','32','154376449','4204962',3),(12296490,'Sussini',0,'Domicilio 1546','email@email.com',0,'2004-06-29','1967-02-09','','Maria Griselda','49','154838119','4242574',3),(12637259,'VILLAR',0,'Domicilio 4596','email@email.com',0,'2013-03-13','1974-12-30','','LILIANA GRACIELA','90','154490952','4171556',3),(12637417,'González',0,'Domicilio 2604','email@email.com',0,'2012-02-07','1956-09-21','','Selva Nora','8','154625203','4732681',2),(12852501,'D imperio',0,'Domicilio 7401','email@email.com',0,'2014-01-25','1970-12-14','','Angela Haydée','28','154647488','4125963',3),(12898395,'POWCH',0,'Domicilio 9472','email@email.com',0,'2000-12-29','1965-07-27','','Elsa Ana','44','154471030','4526447',3),(12956647,'Alvez',0,'Domicilio 3074','email@email.com',0,'2013-05-16','1953-09-15','','Cristina','95','154770666','4102257',3),(13004758,'DUTRA',0,'Domicilio 2101','email@email.com',0,'2006-09-09','1973-02-18','','MARTA LETICIA','34','154404833','4957294',1),(13005252,'MAIDANA',0,'Domicilio 1743','email@email.com',0,'2002-02-20','1962-06-20','','Maria Elisa','74','154531902','4338701',1),(13005781,'PASIECZNIK',0,'Domicilio 7187','email@email.com',0,'2001-11-21','1954-11-27','','María Irma','11','154886201','4797715',1),(13005891,'ZALDÍAVAR',0,'Domicilio 6003','email@email.com',0,'2007-10-20','1973-02-10','','Marta Beatríz','52','154665376','4277970',1),(13005993,'AVALOS ULLON',0,'Domicilio 7645','email@email.com',0,'2008-05-17','1954-03-02','','ESTELA MARÍA','3','154067925','4719488',3),(13013763,'Franzoy',0,'Domicilio 4205','email@email.com',0,'2012-11-01','1962-04-02','','Lucrecia M.','22','154191916','4005612',2),(13248833,'Matteo',0,'Domicilio 5461','email@email.com',0,'1999-06-25','1967-04-25','','María Magdalena','98','154786186','4919336',3),(13350813,'Turineto',0,'Domicilio 3309','email@email.com',0,'2000-10-20','1968-10-05','','Teresita Gladys','67','154856055','4318736',3),(13421303,'LOPEZ',0,'Domicilio 4348','email@email.com',0,'2011-12-07','1970-12-03','','ROSA MASSI','41','154671285','4672139',3),(13558172,'DEL VALLE',0,'Domicilio 9088','email@email.com',0,'2003-06-01','1982-10-20','','Maria Carmen','35','154222600','4765278',3),(13658522,'San Martin',0,'Domicilio 5786','email@email.com',0,'2002-02-05','1965-08-07','','Mabel','11','154905530','4419306',2),(13867641,'Gorostegui',0,'Domicilio 6731','email@email.com',0,'2011-05-26','1950-07-23','','Haydee Beatriz','31','154864919','4686793',1),(13897862,'BOGADO',0,'Domicilio 5436','email@email.com',0,'2014-02-24','1969-07-28','','Carmen Gladys','80','154131859','4475634',3),(13957943,'Alonso',0,'Domicilio 4877','email@email.com',0,'2004-10-13','1964-03-18','','Graciela Patricia','59','154657247','4406377',3),(14181471,'PIANOVI',0,'Domicilio 5304','email@email.com',0,'2005-07-09','1975-01-04','','Ana María','91','154169612','4007188',2),(14194183,'Quintana',0,'Domicilio 9370','email@email.com',0,'2014-06-21','1951-08-22','','Maria Beatriz','22','154142027','4146236',3),(14209322,'Ortiz Pereyra',0,'Domicilio 5203','email@email.com',0,'2008-11-29','1961-01-08','','Angela Maria','46','154365399','4513427',1),(14258039,'ROTELA',0,'Domicilio 9641','email@email.com',0,'2010-01-13','1956-04-21','','GLADIS LUZ BELLA ARGENTINA','91','154676984','4664350',1),(14258752,'González',0,'Domicilio 5186','email@email.com',0,'2013-03-25','1966-01-01','','Irma  ','2','154188048','4583724',3),(14301412,'Beck',0,'Domicilio 4429','email@email.com',0,'2001-11-25','1981-11-05','','Silvia Rosana','47','154186681','4361838',1),(14335266,'MEZA',0,'Domicilio 5973','email@email.com',0,'1999-07-09','1963-03-27','','Analia Ines','83','154482661','4031265',2),(14335484,'VILLALBA',0,'Domicilio 4623','email@email.com',0,'2013-04-10','1982-04-19','','BLANCA MIRIAM','34','154659887','4105060',1),(14469013,'Castro',0,'Domicilio 1446','email@email.com',0,'2013-01-04','1980-08-25','','Elisabet ','13','154028403','4013253',2),(14551343,'Friedich',0,'Domicilio 5289','email@email.com',0,'2006-11-08','1973-07-23','','Angelica','36','154576667','4726873',3),(14713298,'San Miguel',0,'Domicilio 8716','email@email.com',0,'2001-10-12','1951-12-25','','Patricia Noemí','44','154452793','4784030',2),(14745833,'BARREYRO',0,'Domicilio 3080','email@email.com',0,'2012-08-27','1952-10-27','','Nelly Margot','98','154947721','4826536',1),(14745996,'LLAMOSA',0,'Domicilio 5135','email@email.com',0,'2002-09-20','1953-12-30','','Marta Teresita','32','154383399','4829232',3),(14826658,'LEÓN',0,'Domicilio 5787','email@email.com',1,'2007-01-26','1980-04-21','','Alicia Dora','75','154517247','4995869',2),(14826696,'ACEVEDO',0,'Domicilio 4103','email@email.com',0,'2014-04-07','1969-10-29','','Nilda  Irene','55','154162601','4519599',1),(14911492,'PROCOPIO',0,'Domicilio 1995','email@email.com',0,'2008-02-12','1976-01-14','','Lidia Luján','61','154069181','4972420',1),(14926497,'SAWICZ',0,'Domicilio 5832','email@email.com',0,'2010-05-29','1951-01-08','','Norma Raquel','43','154741473','4764007',3),(14946666,'ALARCON',0,'Domicilio 8540','email@email.com',0,'1999-04-23','1970-05-02','','NORMA ELISABETH','11','154800190','4341662',2),(14946893,'MONFERRAN',0,'Domicilio 4885','email@email.com',0,'2009-05-27','1982-02-25','','Emilia Inocencia','10','154884816','4762576',3),(14957513,'Baconé',0,'Domicilio 1907','email@email.com',0,'2011-02-13','1953-11-13','','Claudia','37','154196299','4858530',2),(14960101,'Ruppel',0,'Domicilio 8391','email@email.com',0,'2008-12-01','1956-11-09','','Clara Nilda','84','154782301','4160970',1),(14986595,'Acosta',0,'Domicilio 6911','email@email.com',0,'2002-09-20','1972-11-03','','Elba Aidee','79','154225711','4989528',1),(16163272,'Dalmasso',0,'Domicilio 4829','email@email.com',0,'2000-08-29','1952-10-16','','Rosana Inés','85','154796601','4187924',1),(16205157,'Cohen',0,'Domicilio 1014','email@email.com',0,'2008-03-20','1976-11-13','','Myriam Ruth','82','154032736','4903021',1),(16356400,'Haselteiner',0,'Domicilio 4332','email@email.com',0,'2011-10-10','1975-10-13','','Nelly Catalina','29','154551157','4207244',1),(16365101,'Abuin ',0,'Domicilio 1181','email@email.com',1,'2010-12-17','1952-11-23','','Graciela Luisa','72','154509448','4640303',2),(16365171,'Figueredo',0,'Domicilio 9727','email@email.com',0,'2010-04-18','1980-11-29','','Graciela Yolanda','69','154224079','4852127',1),(16365251,'Correa',0,'Domicilio 7404','email@email.com',0,'1999-03-25','1950-02-22','','María Isabel','84','154390311','4471873',1),(16367471,'Sipituca',0,'Domicilio 1024','email@email.com',0,'2014-02-03','1959-12-28','','Patricia Edith','30','154527097','4898785',2),(16563703,'MARUNIAK',0,'Domicilio 7756','email@email.com',0,'2005-03-26','1950-08-23','','Carmen Isabel','17','154861540','4595776',3),(16650419,'MERCADO',0,'Domicilio 7346','email@email.com',0,'2007-08-18','1977-03-05','','NIEVES BEATRIZ','79','154392231','4169497',1),(16695422,'GAUNA',0,'Domicilio 7756','email@email.com',0,'2004-09-25','1955-10-13','','Liliana Rosa','63','154378146','4284012',3),(16695464,'GONZALEZ',0,'Domicilio 3086','email@email.com',1,'2010-05-17','1969-06-06','','Amalia Estela','33','154841332','4771421',3),(16695503,'LINDO POISSON',0,'Domicilio 8444','email@email.com',0,'2003-02-10','1969-02-26','','ROSANA ESTELA','93','154166648','4118077',3),(16695780,'San Miguel',0,'Domicilio 6938','email@email.com',0,'2000-04-11','1968-12-03','','Monica Susana','32','154450224','4057655',1),(16696948,'Flecha',0,'Domicilio 2037','email@email.com',0,'2010-06-06','1966-03-15','','Leonida','17','154471205','4982679',1),(16829403,'Núñez ',0,'Domicilio 7804','email@email.com',0,'2008-09-18','1952-09-24','','Nidia Herminia','37','154378767','4381243',2),(16853984,'Bar ',0,'Domicilio 8603','email@email.com',0,'2005-03-13','1959-01-08','','María Isabel','20','154077773','4507053',2),(16871076,'Mendoza',0,'Domicilio 5356','email@email.com',0,'2008-07-07','1953-12-14','','Noemí Isabel','8','154511976','4666698',3),(16944749,'BENITEZ',0,'Domicilio 1177','email@email.com',0,'2003-09-07','1957-10-16','','Sonia Bibiana','57','154005008','4351786',3),(16986200,'GALLARDO ',0,'Domicilio 3062','email@email.com',0,'2000-12-13','1968-06-27','','Ana Maria','86','154847508','4130086',1),(16993353,'ESCALANTE',0,'Domicilio 5662','email@email.com',0,'2005-07-22','1951-08-23','','Angélica  Narcisa','49','154520923','4235144',3),(17039784,'KRISKOVICH',0,'Domicilio 9970','email@email.com',0,'2001-03-02','1968-06-01','','Maria Eduarda','2','154449348','4761351',3),(17064155,'PLANAS',0,'Domicilio 3015','email@email.com',0,'2004-12-23','1952-11-10','','MARISA HAIDEE','36','154587816','4231395',1),(17093025,'Gonzalez',0,'Domicilio 7663','email@email.com',0,'2012-05-30','1983-07-10','','Estela Natividad','81','154948025','4543137',2),(17135365,'SILVA',0,'Domicilio 8825','email@email.com',0,'2010-04-01','1961-01-23','','ORILDE','26','154701331','4198628',1),(17135398,'Arriola',0,'Domicilio 7317','email@email.com',0,'2010-02-05','1951-07-01','','Teresa Ines','14','154418936','4612243',3),(17164587,'MANSO',0,'Domicilio 1827','email@email.com',0,'2001-10-12','1967-12-17','','LILIANA TERESA','21','154048412','4884965',3),(17170689,'FERRARI',0,'Domicilio 3756','email@email.com',0,'2012-04-11','1981-06-10','','FABIANA JACQUELINE','19','154466430','4201578',3),(17170744,'RUNKE',0,'Domicilio 3440','email@email.com',0,'2010-09-16','1968-10-11','','LEONARDA','78','154841608','4327489',2),(17216632,'LABANDERA',0,'Domicilio 9882','email@email.com',0,'2004-07-20','1977-08-10','','Nadia Rosa','4','154008220','4595017',2),(17299811,'AVELLANEDA',0,'Domicilio 2440','email@email.com',0,'2006-09-22','1982-10-21','','Elida Rita','77','154991901','4214179',1),(17312131,'Carraffa Flores',0,'Domicilio 7831','email@email.com',0,'2006-12-03','1973-10-20','','Lucrecia Liliam','15','154331773','4105523',3),(17369473,'MARTINEZ',0,'Domicilio 9896','email@email.com',0,'2003-05-02','1964-09-21','','MABEL ZUNILDA','3','154870174','4402657',3),(17378032,'ARANDA',0,'Domicilio 5762','email@email.com',0,'2001-04-16','1977-08-27','','Graciela  Beatriz','83','154767602','4773809',1),(17525644,'Zorrilla',0,'Domicilio 9717','email@email.com',0,'2004-03-04','1960-11-16','','Maria Valentina','59','154332413','4702947',3),(17525767,'Escobar',0,'Domicilio 2384','email@email.com',0,'2000-12-08','1969-10-24','','Laura Trinidad','18','154685217','4441742',3),(17562547,'RUGGIERO',0,'Domicilio 8924','email@email.com',0,'1999-03-14','1979-01-27','','MARGARITA BEATRIZ','53','154436041','4803286',3),(17562559,'PEREYRA',0,'Domicilio 2799','email@email.com',0,'2013-02-17','1983-11-15','','Julia Isabel','4','154628477','4087272',2),(17604599,'BERNAL',0,'Domicilio 3665','email@email.com',0,'2000-03-28','1958-05-19','','Liliana Elizabeth','73','154725348','4437079',1),(17630446,'Gutiérrez',0,'Domicilio 6088','email@email.com',0,'2000-04-23','1955-04-25','','Blanca Isabel','96','154187409','4337315',3),(17652488,'GONZALEZ',0,'Domicilio 8759','email@email.com',0,'2003-07-26','1962-08-20','','AIDA','89','154512879','4058208',3),(17675809,'HABERL',0,'Domicilio 6408','email@email.com',0,'2007-05-12','1970-05-24','','Lilian Esther','98','154987473','4581166',3),(17751922,'LUFT',0,'Domicilio 5785','email@email.com',0,'2000-09-10','1971-12-20','','Orfila','63','154922007','4660066',3),(17760877,'PINTO ',0,'Domicilio 2445','email@email.com',0,'2011-01-30','1977-07-27','','Silvia Liliana','67','154273350','4641557',3),(17760927,'LLAMOSAS',0,'Domicilio 3979','email@email.com',0,'2012-07-03','1959-12-08','','Lucía Esther','34','154115219','4336418',2),(17773508,'Basili',0,'Domicilio 9948','email@email.com',0,'1999-09-12','1977-10-26','','Sonia Elizabeth','91','154249485','4732129',2),(17814471,'Cardozo',0,'Domicilio 5192','email@email.com',0,'2001-05-05','1955-05-21','','Emilia Clarisse ','41','154018741','4088262',1),(17831378,'FERREYRA',0,'Domicilio 6091','email@email.com',0,'2010-12-08','1961-12-24','','Maria Susana','21','154396181','4541961',1),(17905182,'Espada',0,'Domicilio 4706','email@email.com',0,'2003-12-17','1954-07-24','','Maria Silvia','68','154545701','4090619',2),(17952248,'ENGLER',0,'Domicilio 6921','email@email.com',0,'2003-04-12','1980-12-27','','Yolanda Beatriz','32','154697410','4123944',1),(17980404,'Blanco',0,'Domicilio 8559','email@email.com',0,'2002-01-02','1981-07-29','','Claudia Rozaura','73','154274196','4974803',3),(17980484,'SANCHEZ',0,'Domicilio 3775','email@email.com',0,'2008-12-12','1976-08-28','','VISENCIA FABIANA','58','154512438','4795320',1),(18063802,'BOGADO',0,'Domicilio 3654','email@email.com',0,'2010-07-14','1955-08-07','','Dora Elizabeth','73','154218889','4077850',2),(18095622,'GALLO',0,'Domicilio 3316','email@email.com',0,'2003-06-10','1965-08-17','','ELDA NELIDA','49','154545306','4467766',1),(18095845,'Espindola ',0,'Domicilio 8479','email@email.com',0,'2011-07-31','1960-03-31','','Norma','39','154266623','4058939',2),(18180848,'Ciejovicz',0,'Domicilio 2389','email@email.com',0,'2013-05-10','1966-05-29','','Mónica Beatriz','47','154283950','4625323',3),(18265145,'LEÓN',0,'Domicilio 5703','email@email.com',0,'2000-08-18','1982-12-26','','María Elena','19','154639795','4903131',3),(18290431,'Zamora',0,'Domicilio 2957','email@email.com',0,'2010-12-19','1977-09-04','','Myriam Soraya','55','154618135','4539099',1),(18308518,'Sanabria ',0,'Domicilio 4893','email@email.com',0,'2009-12-01','1977-02-14','','Marcela Noemi','53','154846099','4316570',3),(18438573,'Viana',0,'Domicilio 3029','email@email.com',0,'2001-03-26','1967-11-27','','Alicia Beatriz','60','154017463','4951722',2),(18464360,'Chaves',0,'Domicilio 6080','email@email.com',0,'2004-06-02','1959-06-08','','Laura Mónica','28','154762783','4488934',1),(18465156,'Orué ',0,'Domicilio 9480','email@email.com',0,'2002-12-13','1960-12-30','','Julia Elena','51','154308609','4829321',2),(18546184,'URZAGASTI',0,'Domicilio 5907','email@email.com',0,'2002-11-23','1968-05-17','','DORA ELSA','18','154176168','4016854',2),(18683582,'HENN',0,'Domicilio 8392','email@email.com',0,'2010-11-30','1960-10-17','','MARIA SILVINA','59','154248967','4370334',2),(18694386,'Lugo',0,'Domicilio 1602','email@email.com',0,'2006-08-09','1970-07-17','','Herminia','15','154865160','4500876',3),(18701304,'FERNANDEZ',0,'Domicilio 7870','email@email.com',0,'2009-07-17','1979-11-26','','ESTHER','70','154479388','4160921',2),(18767528,'HILDEBRAND',0,'Domicilio 2888','email@email.com',0,'2010-04-13','1973-07-14','',' Clair','81','154784538','4054281',2),(18776248,'GALEANO VELÁZQUEZ',0,'Domicilio 2489','email@email.com',0,'2010-09-18','1978-03-04','','Zulema','14','154956954','4133495',2),(18793716,'PERALTA',0,'Domicilio 8025','email@email.com',0,'2010-02-12','1959-02-17','','Amalia','46','154326277','4290517',2),(18801405,'Sanchez Herrera',0,'Domicilio 5591','email@email.com',0,'2006-09-27','1959-05-27','','Susana Ofelia','55','154521949','4326623',3),(20117775,'Alfonzo Calvo',0,'Domicilio 5888','email@email.com',0,'2002-10-31','1981-07-13','','Laura Beatriz','38','154732504','4949549',3),(20117813,'PALACIOS',0,'Domicilio 4715','email@email.com',0,'2013-04-30','1962-07-09','','María Inés','96','154202683','4357140',1),(20175929,'CODARO',0,'Domicilio 5264','email@email.com',0,'2002-08-27','1973-08-17','','Claudia Gabriela','96','154318873','4017616',3),(20186811,'Noguera',0,'Domicilio 2325','email@email.com',0,'2009-07-26','1969-10-20','','Elida Raquel','75','154078698','4042878',1),(20290582,'Voulquin',0,'Domicilio 2604','email@email.com',1,'2006-01-28','1955-10-10','','Maria Teresa','2','154740225','4568044',1),(20338247,'MEZA',0,'Domicilio 8284','email@email.com',0,'2010-04-19','1963-09-11','','SILVIA DEL CARMEN','70','154969205','4194117',1),(20338614,'VILLANUEVA',0,'Domicilio 4750','email@email.com',0,'2010-10-30','1954-08-08','','FERNANDA LETICIA','64','154451509','4466745',3),(20338642,'silvero',0,'Domicilio 7570','email@email.com',0,'2003-06-27','1972-06-06','','Mirta Lilian','67','154652630','4403080',2),(20476201,'Vera',0,'Domicilio 1720','email@email.com',0,'2009-11-21','1964-01-29','','Maria Liliana','46','154414761','4902997',3),(20484881,'FALCONE',0,'Domicilio 8537','email@email.com',0,'2009-11-12','1955-03-26','','NATALIA BEATRIZ','14','154537975','4427535',1),(20495953,'RODRIGUEZ',0,'Domicilio 9451','email@email.com',0,'2002-10-24','1950-06-02','','MIRTA GLADIS','98','154076118','4652008',1),(20500992,'Villegas',0,'Domicilio 6677','email@email.com',0,'2002-12-14','1967-07-29','','Raquel Teresa','42','154758526','4690460',3),(20518423,'OJEDA',0,'Domicilio 4575','email@email.com',0,'2006-04-13','1965-03-06','','RAMONA ISABEL GRISELDA','53','154546611','4179255',3),(20545757,'Michitte',0,'Domicilio 8171','email@email.com',0,'2012-12-20','1954-01-17','','Sandra Patricia','3','154850882','4256740',1),(20578722,'Mogdans',0,'Domicilio 7394','email@email.com',0,'2005-08-21','1973-01-06','','Erica Daniela','36','154572717','4502009',2),(20629246,'Barrios ',0,'Domicilio 4902','email@email.com',0,'2005-08-12','1967-07-12','','Maria del Carmen ','9','154411255','4199044',2),(20737427,'Fontana',0,'Domicilio 9951','email@email.com',0,'2009-11-07','1970-10-02','','Maria Rosa','36','154835040','4448052',2),(20815489,'RUNKE',0,'Domicilio 3971','email@email.com',0,'2002-04-14','1959-11-29','','NORMA BEATRIZ','93','154483670','4417870',3),(20899640,'Gomez',0,'Domicilio 8795','email@email.com',0,'1999-01-26','1983-04-23','','Nora Gabriela','17','154907120','4858059',1),(20907159,'ARAUJO',0,'Domicilio 9847','email@email.com',0,'2005-11-23','1981-09-23','','Nelida Lucia','48','154896795','4843982',1),(20939295,'GONZALEZ',0,'Domicilio 7930','email@email.com',0,'2001-05-17','1952-12-13','','ANA MARIA','11','154269333','4297208',3),(21180225,'Correa',0,'Domicilio 8383','email@email.com',0,'2006-12-03','1962-01-20','','Fabiana Raquel','47','154085554','4848787',1),(21182875,'FARIÑA',0,'Domicilio 6464','email@email.com',0,'2007-09-25','1967-05-11','','MIRTHA GRACIELA','56','154001418','4669381',1),(21300349,'VILLANUEVA',0,'Domicilio 2797','email@email.com',0,'1999-06-18','1976-10-31','','ESTELA MARIA INES','8','154291967','4434761',1),(21300428,'DEI CASTELLI',0,'Domicilio 5592','email@email.com',0,'2013-08-23','1959-12-17','','Laura Marisa','14','154195967','4496750',3),(21301810,'Atamañuk',0,'Domicilio 6601','email@email.com',0,'1999-10-14','1967-04-12','','Cristina innes','4','154514267','4525116',2),(21302821,'CASTILLO',0,'Domicilio 2988','email@email.com',0,'1999-07-19','1959-02-03','','Graciela Ester','62','154194601','4732090',3),(21303869,'Mazo',0,'Domicilio 6414','email@email.com',0,'2012-05-19','1982-05-26','','Mirian Mabel','5','154133727','4170124',1),(21304070,'Zmiak',0,'Domicilio 3709','email@email.com',0,'2000-04-26','1971-09-23','','Marta Alicia','86','154423950','4962314',1),(21305751,'Vogel ',0,'Domicilio 1687','email@email.com',0,'2008-05-06','1974-06-19','','Susi Raquel','56','154000596','4499287',3),(21365232,'DOMINGUEZ',0,'Domicilio 8107','email@email.com',0,'2006-12-21','1975-05-18','','Anselma Beatriz','75','154438362','4074812',2),(21546890,'RIVEROS',0,'Domicilio 5727','email@email.com',0,'1999-07-28','1954-02-26','','Irma Alicia','3','154275643','4332481',3),(21562507,'COSTA',0,'Domicilio 5854','email@email.com',0,'2014-02-15','1955-12-02','','Emma Fabiana','63','154031699','4838422',2),(21598339,'Corti',0,'Domicilio 6550','email@email.com',0,'2003-10-31','1975-06-22','','Flavia Selene','63','154019990','4688145',1),(21634884,'Iturbe',0,'Domicilio 9996','email@email.com',0,'2006-07-08','1958-02-14','','Norma Beatriz ','36','154212764','4961606',2),(21660785,'Echeverria',0,'Domicilio 2554','email@email.com',0,'2005-01-05','1970-01-22','','Claudia Alejandra14','68','154137397','4665943',1),(21684508,'ESCURRA CANIZA',0,'Domicilio 8354','email@email.com',0,'2011-01-22','1979-03-27','','CLAUDIA GISELA','72','154798457','4348994',2),(21723344,'De Olivera ',0,'Domicilio 8208','email@email.com',0,'2009-03-09','1978-06-10','','Claudia ','82','154496345','4179810',1),(21723721,'LOMBARDI',0,'Domicilio 7075','email@email.com',0,'2001-09-25','1951-05-05','','CLAUDIA VIVIANA','2','154771630','4124745',1),(21775640,'Villegas',0,'Domicilio 2084','email@email.com',0,'2009-02-12','1972-06-24','','Claudia Patrici','17','154645532','4755010',2),(21781027,'Ibarra ',0,'Domicilio 8908','email@email.com',0,'2004-07-20','1975-08-11','','Beatriz R.','91','154764043','4146530',3),(21781619,'PERALTA',0,'Domicilio 2583','email@email.com',0,'2002-05-21','1973-11-17','','Mabel Alejandra','78','154478753','4581369',3),(21781681,'Maidana',0,'Domicilio 8976','email@email.com',0,'2007-07-18','1964-02-13','','Claudia Isabel','94','154300827','4335550',1),(21781725,'MENDOZA ',0,'Domicilio 5147','email@email.com',0,'2013-03-13','1959-04-19','','MARIA DEL CARMEN','25','154823584','4816919',3),(21793705,'Escobar',0,'Domicilio 3948','email@email.com',0,'2006-12-23','1962-04-10','','Elsa Elena','38','154545725','4875713',2),(21974458,'KORNUTA',0,'Domicilio 1200','email@email.com',0,'1999-03-03','1971-05-18','','Estela Carmen','6','154802729','4060846',2),(21976884,'CARCACHA',0,'Domicilio 9786','email@email.com',0,'1999-05-14','1956-04-26','','Maria Teresa Del Rosario','35','154846567','4607829',1),(22040318,'Piñeiro',0,'Domicilio 4256','email@email.com',0,'2006-12-06','1976-05-01','','Marta Irene','53','154700902','4055332',2),(22090129,'VICENTE',0,'Domicilio 7684','email@email.com',0,'2011-06-12','1957-12-25','','Alejandra Del Carmen','61','154011245','4541729',1),(22090330,'Zorrilla',0,'Domicilio 4084','email@email.com',0,'2004-02-04','1979-03-02','','Isabel Isaura','56','154966102','4555523',3),(22090333,'LEZCANO',0,'Domicilio 5514','email@email.com',0,'2012-06-15','1974-11-01','','LILIANA ELIZABETH','27','154246417','4402213',2),(22090400,'ZARZA',0,'Domicilio 6101','email@email.com',0,'2005-08-27','1974-12-02','','CELIA ELISA','87','154563184','4046374',3),(22141132,'Alonso',0,'Domicilio 4846','email@email.com',0,'2003-05-02','1957-11-20','','Maria Alejandra','87','154996359','4223086',3),(22141155,'FERNANDEZ',0,'Domicilio 9878','email@email.com',0,'2007-02-13','1976-12-30','','María Alejendra','84','154858871','4685558',3),(22351819,'López',0,'Domicilio 8498','email@email.com',1,'2004-09-13','1979-10-29','','María del Carmen','2','154341671','4629438',2),(22351900,'ELIAS COUTTO',0,'Domicilio 5952','email@email.com',0,'2003-04-26','1969-05-28','','Roxana Guillermina','15','154615996','4458072',1),(22352199,'RODRIGUEZ',0,'Domicilio 9035','email@email.com',0,'2007-12-18','1957-03-23','','Mirtha Santa','69','154228790','4807063',2),(22485728,'Labycz',0,'Domicilio 3532','email@email.com',0,'2012-01-15','1955-04-01','','Ivana María','87','154891351','4092648',1),(22488411,'RIBERO',0,'Domicilio 5711','email@email.com',0,'2004-12-05','1971-10-31','','MIRIAM BEATRIZ','28','154565441','4790879',3),(22488625,'Fernandez',0,'Domicilio 4330','email@email.com',0,'2007-05-28','1951-09-30','','Maria Ester','44','154493497','4024952',2),(22488745,'CORREA',0,'Domicilio 8866','email@email.com',1,'2003-10-13','1961-05-03','','ROSANA TRINIDAD','84','154150719','4850923',1),(22508922,'Encina',0,'Domicilio 4233','email@email.com',0,'2012-09-05','1969-04-10','','Susana Andrea','4','154875654','4167353',3),(22539277,'Dobler ',0,'Domicilio 5909','email@email.com',0,'2001-02-21','1978-07-22','','María Esther','54','154706027','4094805',1),(22662681,'Pollina',0,'Domicilio 3959','email@email.com',0,'2002-09-11','1972-04-18','','Claudia Jazmin','42','154831277','4011572',2),(22692609,'Espinola ',0,'Domicilio 7539','email@email.com',0,'2004-08-12','1960-01-12','','Marta Isabel','37','154245704','4496972',3),(22737993,'SUÁREZ',0,'Domicilio 8610','email@email.com',0,'2001-08-27','1956-05-09','','Mónica Beatríz','1','154562860','4209433',1),(22738113,'Jara',0,'Domicilio 9536','email@email.com',0,'2011-06-09','1966-12-03','','Susana','61','154177997','4175249',2),(22814059,'RODRIGUEZ',0,'Domicilio 7280','email@email.com',0,'2004-03-23','1978-06-13','','GRACIELA BEATRIZ','50','154186955','4106519',3),(22835570,'Jara ',0,'Domicilio 3319','email@email.com',0,'2001-04-08','1975-04-30','','Ana Graciela','43','154036638','4381939',3),(22835678,'FLORENTIN',0,'Domicilio 5717','email@email.com',0,'2013-01-17','1981-02-11','','MIRIAN ROSANA','20','154858767','4776682',1),(22835931,'ACOSTA',0,'Domicilio 6893','email@email.com',0,'2004-10-10','1953-04-30','','MONICA ALICIA','76','154994326','4281051',1),(22836855,'Soto',0,'Domicilio 5858','email@email.com',0,'2010-09-06','1952-12-22','','Rosana del Rosario','42','154897320','4237776',1),(22870686,'Rodriguez',0,'Domicilio 7642','email@email.com',0,'2013-12-07','1982-11-05','','Susana','89','154193019','4758519',2),(22944957,'Da Silva',0,'Domicilio 1632','email@email.com',0,'2001-01-17','1973-01-08','','Lidia ines','76','154097213','4694474',2),(23035827,'Dos Santos',0,'Domicilio 6937','email@email.com',0,'2007-03-01','1980-04-09','','Ramona Liliana','62','154661380','4696653',1),(23066520,'MONGE',0,'Domicilio 6123','email@email.com',0,'2003-03-11','1964-10-20','','CINTIA NOEMI','46','154830753','4040642',1),(23096336,'ESQUIVEL',0,'Domicilio 5178','email@email.com',0,'2013-04-02','1961-06-30','','Ruth Elisabeth','83','154090919','4597846',1),(23096644,'MACRON',0,'Domicilio 8950','email@email.com',0,'2005-10-20','1957-06-24','','MYRIAM CRISTINA','53','154582747','4835635',2),(23170837,'ALTAMIRANO',0,'Domicilio 2679','email@email.com',0,'2009-12-22','1973-09-23','','Blasia Isabel','4','154395307','4106151',1),(23207943,'Alonso',0,'Domicilio 5178','email@email.com',0,'2001-07-20','1957-08-03','','Viviana Karina','27','154382404','4580044',1),(23338376,'Zarza',0,'Domicilio 3560','email@email.com',0,'2002-05-03','1969-10-15','','Laura Mabel','67','154341206','4576908',3),(23349910,'Ortiz',0,'Domicilio 4152','email@email.com',0,'1999-09-02','1979-02-18','','Norma Natividad','13','154931756','4920403',1),(23383304,'Rodriguez',0,'Domicilio 5088','email@email.com',1,'2009-07-22','1964-02-16','','Rossanna Andrea','69','154878856','4217707',2),(23383428,'VERON',0,'Domicilio 5285','email@email.com',0,'2012-06-19','1955-05-13','','CLAUDIA ISABEL','58','154515931','4631060',3),(23430610,'Farías',0,'Domicilio 1734','email@email.com',0,'2004-05-07','1960-06-24','','María Leandra','4','154100382','4688557',3),(23468003,'Gonzalez',0,'Domicilio 6824','email@email.com',0,'1999-11-07','1956-01-15','','Liliana Ines','75','154836338','4326339',2),(23482663,'Cristaldo',0,'Domicilio 2901','email@email.com',0,'2007-09-26','1956-02-06','','Sandra Noemi','31','154309737','4969738',3),(23546861,'RODRIGUEZ',0,'Domicilio 6794','email@email.com',0,'2014-05-14','1979-07-07','','María del Rosario','4','154983778','4178173',3),(23675117,'Romero',0,'Domicilio 9763','email@email.com',0,'2006-02-04','1961-05-21','','Mirian Marcela','80','154974335','4917080',1),(23675136,'Denike ',0,'Domicilio 8615','email@email.com',0,'2006-06-17','1957-08-04','','Mariela','68','154089997','4983151',3),(23675148,'RUNKE ',0,'Domicilio 7118','email@email.com',0,'2014-07-26','1953-02-25','','MIRTA GLADYS','32','154363374','4436640',2),(23675260,'ALARCON',0,'Domicilio 1842','email@email.com',0,'2009-01-13','1950-07-24','','Norma Elizabeth','89','154935443','4157869',3),(23675431,'BARCHETTA',0,'Domicilio 9811','email@email.com',0,'2006-07-22','1963-02-17','','Gabriela  María','68','154203435','4532453',1),(23690040,'FERNANDEZ',0,'Domicilio 8500','email@email.com',0,'2000-05-07','1963-04-10','','Carmen Fabiana','70','154796577','4444396',1),(23690119,'SENA',0,'Domicilio 4931','email@email.com',1,'2012-05-06','1977-12-04','','PATRICIA HAYDEE','8','154857514','4969419',3),(23737897,'Vogel ',0,'Domicilio 3298','email@email.com',0,'2000-04-03','1975-05-08','','Mabel','77','154164060','4217320',3),(23900628,'SOSA',0,'Domicilio 7063','email@email.com',0,'2005-04-06','1975-09-08','','Silvia Andrea','9','154299478','4186033',1),(23901844,'Da Silva',0,'Domicilio 6162','email@email.com',0,'2010-01-22','1974-11-03','','Virginia','12','154615577','4302642',2),(23910719,'Toledo ',0,'Domicilio 3539','email@email.com',1,'1999-12-28','1980-07-21','','Sulma','35','154693448','4588062',3),(23951159,'GALEANO',0,'Domicilio 8638','email@email.com',0,'2006-10-12','1973-09-19','','LILIANA ESTELA','34','154691350','4572827',2),(23951555,'Saucedo',0,'Domicilio 1138','email@email.com',0,'2009-08-08','1977-07-14','','Claudia Beatriz ','77','154132694','4150956',1),(23951570,'Recalde',0,'Domicilio 6893','email@email.com',0,'2012-01-14','1962-01-03','','Maria Graciela','92','154775891','4007012',3),(24076719,'Antúnez Martínez',0,'Domicilio 9787','email@email.com',0,'2010-01-11','1981-09-28','','Irma  ','1','154112889','4971763',3),(24090590,'Mazo',0,'Domicilio 5799','email@email.com',0,'2014-01-11','1963-04-19','','Sandra Noemí','59','154204043','4858542',2),(24118105,'Soto',0,'Domicilio 7206','email@email.com',0,'2006-04-03','1983-10-27','','Noemi Liliana','5','154361677','4627225',1),(24130561,'MASACOSKI',0,'Domicilio 8024','email@email.com',1,'2003-10-20','1969-08-26','','Liliana del Carmen','76','154004485','4638443',2),(24130661,'Novak',0,'Domicilio 5017','email@email.com',0,'2008-03-12','1962-06-16','','Paula Mariela','98','154931059','4675449',3),(24131387,'DELGADO',0,'Domicilio 8538','email@email.com',0,'2012-04-23','1970-09-28','','SILVIA KARINA','54','154753433','4426696',3),(24143117,'Morinigo',0,'Domicilio 4439','email@email.com',0,'2011-10-09','1977-11-30','','Rosa Ma Gabriela','29','154444114','4360986',3),(24143256,'Rojas ',0,'Domicilio 6657','email@email.com',0,'1999-06-20','1963-05-21','','Viviana Mabel','39','154257715','4723439',2),(24294655,'Segui',0,'Domicilio 1944','email@email.com',1,'2000-04-24','1958-04-16','','Adriana Lucia','93','154607651','4063889',2),(24294858,'RODRIGUEZ',0,'Domicilio 6909','email@email.com',1,'2014-05-15','1977-08-16','','GRACIELA PETRONA','88','154768861','4065319',2),(24389202,'Mazo ',0,'Domicilio 5923','email@email.com',0,'2011-12-12','1959-05-28','','Karina Mariela','50','154563822','4076009',1),(24485566,'Rodriguez',0,'Domicilio 6244','email@email.com',1,'1999-05-22','1956-06-14','','Matilde Ester','84','154272922','4630525',2),(24509951,'Chamorro',0,'Domicilio 6318','email@email.com',0,'2005-03-22','1974-04-11','','Alejandra Cecilia','81','154655027','4246437',3),(24516692,'Cardozo',0,'Domicilio 8547','email@email.com',0,'2006-07-19','1978-11-15','','Mría Silvina','12','154301837','4347111',1),(24572255,'Patiño',0,'Domicilio 7153','email@email.com',0,'2003-03-15','1951-04-11','','Marina Sandra','85','154610129','4461737',1),(24572310,'Gonzalez',0,'Domicilio 1176','email@email.com',0,'2010-04-17','1965-09-03','','Nilda ','9','154361165','4608320',1),(24573249,'ESCUDERO',0,'Domicilio 9434','email@email.com',0,'2000-12-20','1971-04-20','','VERONICA FABIANA','63','154338144','4976816',1),(24573403,'Martinez',0,'Domicilio 9728','email@email.com',0,'2014-03-23','1972-02-17','','Hilda Ester','19','154431729','4967381',2),(24573604,'VARGAS',0,'Domicilio 4716','email@email.com',0,'2004-09-03','1983-11-14','','ELIDA RAQUEL','85','154361852','4732400',2),(24600324,'COTT',0,'Domicilio 3691','email@email.com',0,'2000-06-11','1977-03-30','','SUSANA MARIELA','92','154438889','4967610',3),(24601104,'LÓPEZ',0,'Domicilio 3178','email@email.com',0,'2004-02-15','1970-04-01','','Gladys Eliana Elizabeth','75','154429869','4535000',3),(24601250,'ACEVEDO',0,'Domicilio 9679','email@email.com',0,'2002-05-24','1981-05-12','','Patricia Fabiana','43','154034373','4080524',3),(24601563,'Fleitas',0,'Domicilio 2580','email@email.com',0,'2008-02-22','1957-11-04','','Lorena Elizabeth','40','154463693','4928754',3),(24601655,'Arriola',0,'Domicilio 2878','email@email.com',0,'2001-06-14','1983-05-16','','Gladis Beatriz','11','154879565','4408005',2),(24601704,'Garcia',0,'Domicilio 8579','email@email.com',0,'2011-11-12','1952-02-25','','Betiana Isabel','18','154385508','4298197',1),(24644783,'Denis ',0,'Domicilio 4627','email@email.com',0,'2005-06-03','1957-10-20','','Olga Ermelinda','16','154667531','4726393',1),(24679829,'Suárez ',0,'Domicilio 7650','email@email.com',0,'2004-10-12','1957-11-03','','Silvia Lorena','84','154660112','4821577',2),(24679887,'Oviedo',0,'Domicilio 8019','email@email.com',0,'2010-07-01','1982-06-27','','Daniela Itati','67','154104902','4975163',1),(24709560,'DRI DANIEL',0,'Domicilio 2614','email@email.com',1,'2003-06-26','1966-06-11','','CARLA LAURA','78','154607971','4097083',2),(24769433,'VERITE',0,'Domicilio 9633','email@email.com',1,'2010-04-04','1967-03-30','','MARIA  FLORENCIA','16','154774818','4875775',3),(24835945,'Vercelli',0,'Domicilio 4932','email@email.com',0,'2009-02-04','1954-09-02','',' Carla Andrea','3','154269085','4420928',3),(24865425,'Vargas ',0,'Domicilio 1305','email@email.com',0,'2001-03-29','1980-08-23','','Liliana Concecpcion ','69','154198073','4088035',2),(24903025,'Oliveira',0,'Domicilio 4304','email@email.com',0,'2007-02-21','1962-09-20','','Rosalina','33','154976506','4257552',2),(24932200,'Garcia',0,'Domicilio 5518','email@email.com',0,'2001-07-28','1966-12-06','','Fabiana Andrea','78','154617501','4831396',2),(24937904,'Ortiz',0,'Domicilio 8563','email@email.com',1,'2007-11-21','1959-09-03','','Marcela','73','154081980','4389410',1),(24985441,'Oliveira',0,'Domicilio 8618','email@email.com',0,'2002-10-30','1972-01-17','','Claudia Beatriz','10','154603526','4729905',1),(24985506,'Maidana',0,'Domicilio 6129','email@email.com',0,'2005-12-08','1956-03-20','','liliana elizabeth','91','154114444','4731535',1),(24989460,'Zarate',0,'Domicilio 6573','email@email.com',0,'2014-03-05','1950-03-17','','Silvia Beatriz','4','154411892','4052125',3),(25019526,'ORTIGOZA',0,'Domicilio 9750','email@email.com',0,'2010-11-08','1964-03-22','','Cristina Alejandra','77','154381195','4425480',3),(25066453,'FRAGOSO DE LIMA',0,'Domicilio 7627','email@email.com',0,'2007-03-10','1983-06-27','','NELIDA','97','154024034','4418049',1),(25159104,'Aguirre',0,'Domicilio 2646','email@email.com',0,'2013-04-20','1965-09-18','','Rosalia Mariza','90','154319742','4042465',2),(25192099,'Zarza ',0,'Domicilio 3614','email@email.com',0,'2001-07-17','1956-03-07','','Julia Beatriz','78','154855955','4380644',1),(25201554,'Gonzaga',0,'Domicilio 1804','email@email.com',0,'2001-10-27','1950-07-08','','Rosana Mabel','12','154216011','4396645',2),(25231594,'Vera',0,'Domicilio 2773','email@email.com',0,'2008-07-30','1972-03-11','','Barbara Anahi','66','154609374','4766351',2),(25313007,'Gauto',0,'Domicilio 1666','email@email.com',0,'2005-02-28','1967-03-15','','Mónica Beatriz','45','154788733','4880570',3),(25316907,'Herrera',0,'Domicilio 6760','email@email.com',0,'2006-05-27','1968-04-28','','Liliana Beatriz','78','154036505','4727132',2),(25322125,'ANDINO',0,'Domicilio 1856','email@email.com',0,'2009-06-18','1969-08-22','','Yolanda Elisabeth','5','154835361','4821526',2),(25340444,'TAGLIABUE',0,'Domicilio 5062','email@email.com',0,'2009-09-12','1968-07-14','','MARIELA VERONICA','70','154984249','4558979',3),(25428447,'Ojeda',0,'Domicilio 6748','email@email.com',0,'2000-03-31','1964-06-18','','Alejandra M','5','154035032','4046583',3),(25450204,'Sdanovichi',0,'Domicilio 4420','email@email.com',0,'2002-01-15','1972-03-17','','Susana Fabiola','65','154831896','4650671',3),(25450265,'Escobar',0,'Domicilio 2811','email@email.com',0,'2004-05-18','1954-02-13','','Silvia Elizabeth','63','154757434','4262706',2),(25489363,'Portillo',0,'Domicilio 4665','email@email.com',0,'2003-11-01','1976-08-19','','Ramona Alejandra Ermelinda','15','154293338','4113965',1),(25489484,'ORTIGOZA ',0,'Domicilio 4449','email@email.com',0,'2011-05-20','1971-06-14','','MELBA RAQUEL','85','154405525','4183627',1),(25535836,'SUAREZ',0,'Domicilio 9895','email@email.com',0,'2008-07-07','1975-07-14','','Claudia Griselda','2','154039445','4162514',3),(25758210,'VERITE',0,'Domicilio 9551','email@email.com',0,'2009-05-24','1969-12-31','','VERONICA','64','154951104','4964996',2),(25767251,'Gonzalez',0,'Domicilio 4984','email@email.com',0,'1999-10-30','1970-11-17','','Griselda Beatriz','9','154783259','4739695',1),(25774560,'Montero',0,'Domicilio 8848','email@email.com',0,'2001-05-29','1983-09-29','','Maria d l nieves','3','154142609','4126458',2),(25860834,'QUIROZ',0,'Domicilio 6966','email@email.com',0,'2012-10-14','1979-07-07','','ANDREA SOLEDAD','2','154057064','4248034',2),(25894270,'ALAMADA',0,'Domicilio 2930','email@email.com',0,'2000-12-06','1952-09-04','','MIRIAM ALICIA','54','154565858','4316465',3),(25895655,'KRIVONOSOW',0,'Domicilio 1012','email@email.com',1,'2000-02-16','1957-06-17','','Roxana Beatriz','95','154505206','4960706',2),(25985040,'Rodriguez',0,'Domicilio 2957','email@email.com',0,'2009-03-13','1974-11-17','','Georgina Marisol','70','154102617','4878802',1),(26082506,'Garrido',0,'Domicilio 7720','email@email.com',0,'2014-05-04','1952-03-26','',' Maria de los Angeles','44','154284373','4859127',3),(26286013,'Enriquez',0,'Domicilio 6178','email@email.com',1,'2010-04-12','1983-05-05','','Isabel Beatriz','89','154484270','4501635',2),(26286171,'IBAÑEZ',0,'Domicilio 5412','email@email.com',1,'2005-04-24','1974-04-09','','Natalia Soledad','48','154704347','4779915',3),(26292240,'GALEANO',0,'Domicilio 2332','email@email.com',1,'2005-04-18','1977-06-21','','MIRIAM SUSANA','92','154855229','4653359',3),(26292622,'AQUERI',0,'Domicilio 6499','email@email.com',0,'2000-12-01','1967-01-11','','Patricia Itati ','20','154535240','4113487',3),(26425752,'Garcia',0,'Domicilio 7043','email@email.com',0,'2001-01-01','1951-06-20','','Claudia Liliana','65','154937662','4413487',1),(26447307,'Avila',0,'Domicilio 9194','email@email.com',0,'2005-09-24','1974-12-18','','Marisa Alejandra','31','154855361','4071876',3),(26546441,'SANCHEZ',0,'Domicilio 9454','email@email.com',0,'2013-09-13','1968-09-19','','Mirian Soledad','35','154073012','4052871',1),(26578164,'CHAVEZ',0,'Domicilio 5808','email@email.com',0,'2003-04-15','1967-01-17','','CLAUDIA RAMONA','11','154294190','4220587',2),(26606894,'Vallaro',0,'Domicilio 9005','email@email.com',0,'2013-07-30','1972-09-13','','Isabel Romina','22','154284972','4103616',2),(26623490,'Gimenez',0,'Domicilio 6948','email@email.com',1,'1999-05-30','1981-02-27','','Alicia Elizabeth','70','154114010','4802060',2),(26754871,'Hnatiuk',0,'Domicilio 4470','email@email.com',1,'2005-08-20','1951-09-28','','María Juana','42','154130064','4060065',3),(26776185,'Gaona',0,'Domicilio 2228','email@email.com',0,'2010-11-20','1972-05-07','','Rita Marina ','11','154329579','4441175',3),(26776882,'Arguello ',0,'Domicilio 3480','email@email.com',0,'2004-02-11','1951-06-23','','Carla Analia','32','154830515','4145746',1),(26849016,'Quintana',0,'Domicilio 3719','email@email.com',0,'2010-06-10','1964-03-16','','Verónica Itatí','68','154465049','4482221',3),(26853222,'DE LA CRUZ',0,'Domicilio 8952','email@email.com',0,'2013-12-10','1968-03-02','','MIRIAM LILIANA','37','154249911','4590693',3),(27054164,'Galeano',0,'Domicilio 8617','email@email.com',0,'2002-08-10','1982-09-02','','Maria Carolina','4','154155976','4545641',3),(27116817,'MONTAÑO AQUINO',0,'Domicilio 9404','email@email.com',0,'2014-03-24','1979-05-12','','ROSANA ARACELI','10','154435496','4420152',2),(27200223,'Marecos',0,'Domicilio 5913','email@email.com',0,'1999-01-27','1954-01-01','','Victoria Beatriz','97','154384791','4795780',1),(27205305,'Rodriguez Souto',0,'Domicilio 3322','email@email.com',0,'1999-04-09','1977-03-28','','Claudia Andrea','19','154963902','4580293',2),(27221986,'NOLBERTO',0,'Domicilio 3660','email@email.com',0,'2000-07-22','1977-11-11','','MARIA MIRTA','84','154851555','4517861',2),(27233372,'SILVA',0,'Domicilio 1468','email@email.com',0,'2010-04-30','1973-02-10','','MARCELA CELESTE','38','154712369','4902495',1),(27233664,'Franco',0,'Domicilio 2563','email@email.com',0,'2002-04-23','1966-05-08','','Gilda Graciela','20','154277861','4437785',2),(27257937,'Pedraza',0,'Domicilio 4624','email@email.com',0,'2004-12-14','1962-04-08','','Maria Valeria','9','154316484','4336663',1),(27366789,'GANDUGLIA',0,'Domicilio 5049','email@email.com',0,'2008-06-14','1968-12-30','','María Lorena ','67','154054607','4311433',1),(27433794,'Cabrera',0,'Domicilio 7394','email@email.com',0,'2010-08-08','1976-11-28','','Liliana Esther','44','154728237','4804078',3),(27433797,'Nielsen',0,'Domicilio 9566','email@email.com',0,'2003-11-13','1952-12-08','','Mery Lizabeth','3','154447184','4084808',2),(27456218,'IBARRA',0,'Domicilio 3366','email@email.com',0,'2010-02-16','1960-10-04','','KARINA ALEJANDRA','8','154527999','4664694',3),(27456856,'Gutierrez',0,'Domicilio 9626','email@email.com',0,'1999-03-20','1958-07-07','','Karina Elena','71','154812957','4999232',3),(27470356,'Geneyro',0,'Domicilio 4961','email@email.com',0,'2006-04-21','1967-10-22','','Nora Eliana.','22','154764495','4574978',2),(27504424,'Prieto',0,'Domicilio 7319','email@email.com',0,'2006-06-14','1975-10-29','','Marisa Dorotea','88','154275801','4216586',2),(27554105,'Cunha',0,'Domicilio 6089','email@email.com',0,'2009-05-26','1970-03-17','','Carmen Marina','55','154257623','4961285',1),(27574610,'CAMBAS',0,'Domicilio 2505','email@email.com',0,'2006-02-18','1975-03-13','','PAULA ANDREA','22','154443991','4530530',2),(27650131,'MIÑO',0,'Domicilio 5380','email@email.com',0,'2004-08-02','1981-10-05','','Alicia','62','154278115','4119783',2),(27685910,'GOMEZ',0,'Domicilio 4670','email@email.com',0,'2010-02-21','1971-03-19','','Karina Vanesa','63','154234091','4339993',1),(27800047,'Mauri',0,'Domicilio 8815','email@email.com',0,'2008-07-24','1962-01-14','','Laura Mariela','87','154593463','4855769',3),(27800068,'De Miranda',0,'Domicilio 8696','email@email.com',0,'2002-07-10','1980-04-10','','Lorena Evelyn','45','154441994','4332144',2),(27979166,'Ferreira',0,'Domicilio 4171','email@email.com',0,'2010-09-07','1958-04-17','','Carolina Vanessa','48','154722640','4335590',3),(27979343,'Pintos',0,'Domicilio 5160','email@email.com',0,'2001-04-13','1975-08-12','','Paola Paticia','22','154915725','4379434',3),(27999936,'Bonato',0,'Domicilio 5695','email@email.com',0,'2011-11-22','1964-02-28','','Virginia Isabel','74','154699909','4432247',1),(28017815,'Ramirez',0,'Domicilio 5024','email@email.com',0,'2014-04-04','1965-03-28','','claudia Andrea','29','154298821','4326465',2),(28065205,'Paredes',0,'Domicilio 2764','email@email.com',0,'2012-02-26','1964-06-12','','Maria Josefa ','77','154172992','4321571',2),(28065478,'Ramirez',0,'Domicilio 3667','email@email.com',0,'2011-05-27','1961-05-11','','Andrea Liliana','79','154455905','4492865',3),(28084616,'Baez',0,'Domicilio 4415','email@email.com',0,'2011-07-19','1983-01-01','','Mercedes Lina','24','154778492','4679521',3),(28118896,'Reinaldo',0,'Domicilio 9890','email@email.com',0,'2000-10-05','1952-06-29','','Elsa Esther','89','154314255','4431764',2),(28246117,'ESCOBAR',0,'Domicilio 8277','email@email.com',1,'2000-08-16','1959-12-04','','Fabiana Eugenia','83','154490843','4649562',1),(28466304,'Azarmendia',0,'Domicilio 4257','email@email.com',0,'2013-06-26','1978-06-13','','Raquel Silvia','2','154921052','4552363',2),(28513597,'FARIÑA',0,'Domicilio 8887','email@email.com',0,'2004-01-22','1956-08-30','','María Cecilia','4','154094956','4432305',3),(28552811,'Da Silva',0,'Domicilio 4526','email@email.com',0,'2003-06-13','1981-05-22','','Mabel','97','154636819','4516162',2),(28589963,'Breard',0,'Domicilio 4764','email@email.com',0,'1999-08-26','1972-08-31','','Lidia Beatriz','8','154316241','4249341',2),(28610740,'FERRARI',0,'Domicilio 8759','email@email.com',0,'2007-06-15','1965-07-25','','VANESA FABIOLA','33','154165315','4405804',2),(28818364,'CABRERA',0,'Domicilio 7292','email@email.com',0,'2004-09-03','1955-06-01','','ANALIA LORENA','20','154180773','4433813',3),(28832531,'Avalos',0,'Domicilio 6354','email@email.com',0,'2010-08-07','1979-11-12','','Rossana Soledad','53','154660032','4834716',3),(28903354,'DAVALOS',0,'Domicilio 2608','email@email.com',0,'2007-05-26','1972-10-01','','LORENA TAMARA','18','154685650','4825550',2),(29008677,'Quiñones',0,'Domicilio 8361','email@email.com',0,'2011-07-15','1970-04-16','','Laura Edith','82','154345659','4377322',3),(29054393,'Silva',0,'Domicilio 6790','email@email.com',0,'2008-02-11','1952-02-25','','Maria Soleda','11','154034001','4524702',3),(29241507,'Benito',0,'Domicilio 5774','email@email.com',0,'2010-05-26','1971-05-03','','Natalia Rosa','95','154946972','4183301',2),(29325180,'SADANIOVSKI',0,'Domicilio 4327','email@email.com',0,'2010-05-04','1950-12-28','','Claudia Alejandra','25','154628191','4264424',2),(29411528,'Dominguez',0,'Domicilio 1024','email@email.com',0,'2003-09-27','1961-09-02','','Flavia Alejandra','33','154387661','4067282',3),(29431996,'SANCHEZ',0,'Domicilio 1712','email@email.com',0,'1999-11-10','1969-05-29','','Marcelina Mabel','35','154718356','4405609',1),(29441092,'Franco',0,'Domicilio 9156','email@email.com',0,'2006-09-22','1958-08-30','','Laura Micaela','37','154132922','4541808',3),(29455785,'HASEITEL',0,'Domicilio 2619','email@email.com',0,'2007-02-02','1958-12-22','','Isabel','82','154937121','4149922',2),(29596937,'SOSA',0,'Domicilio 3963','email@email.com',0,'2008-12-14','1974-05-31','','Paola Andrea','46','154097607','4267404',3),(29671618,'ALFONSO',0,'Domicilio 3247','email@email.com',0,'2012-10-25','1980-05-07','','María Alicia del Carmen','82','154559695','4073662',1),(29671701,'Bado',0,'Domicilio 3093','email@email.com',0,'2000-05-16','1976-09-18','','Natalia  Vanesa','18','154265467','4590144',1),(29671992,'RODRIGUEZ',0,'Domicilio 3215','email@email.com',0,'2008-10-11','1973-07-23','','María Laura','34','154322328','4665369',3),(29698224,'LOPEZ',0,'Domicilio 5604','email@email.com',0,'2002-05-01','1978-11-18','','Estela Soledad','14','154758095','4074175',3),(29806907,'Rios',0,'Domicilio 9246','email@email.com',0,'2010-12-22','1970-01-09','','Shirley','65','154462424','4642400',2),(29806908,'Dominguez',0,'Domicilio 6485','email@email.com',0,'2012-10-05','1964-12-21','','Ma Cecilia','24','154729996','4357048',1),(30003106,'Santa Cruz',0,'Domicilio 8924','email@email.com',0,'2001-03-05','1975-06-09','','Marta Beatriz','87','154257736','4825927',1),(30084028,'Brizuela',0,'Domicilio 5484','email@email.com',0,'2010-10-24','1966-02-08','','Claudia Soledad','98','154824644','4194287',1),(30165274,'Pintos',0,'Domicilio 9125','email@email.com',0,'2003-10-12','1950-06-17','','Natalia Alejandra','80','154494859','4995702',2),(30255272,'Correa',0,'Domicilio 5222','email@email.com',0,'2009-08-01','1971-10-12','','Maria Agustina','51','154863958','4684577',1),(30297834,'ARROYO',0,'Domicilio 7578','email@email.com',0,'1999-08-24','1952-08-09','','Dalma Ramona','52','154954031','4126543',2),(30362346,'Fernandez',0,'Domicilio 9101','email@email.com',0,'2011-07-18','1976-12-05','','Laura Itati','49','154102645','4077212',3),(30396924,'ARBO de FLEITA',0,'Domicilio 4191','email@email.com',0,'2009-03-31','1978-05-27','','Andrea Gabriela','23','154547190','4183563',3),(30398079,'DIAZ',0,'Domicilio 1469','email@email.com',0,'2006-02-11','1968-02-02','','MELISA IVANA','56','154504769','4154951',3),(30619550,'FERNANDEZ',0,'Domicilio 4669','email@email.com',0,'2004-03-25','1965-06-23','','MARINA ISABEL','61','154341315','4616090',3),(30687895,'Cardozo',0,'Domicilio 3588','email@email.com',0,'2008-01-16','1966-04-06','','Noemi Paola','68','154014960','4112711',1),(30790108,'Montero',0,'Domicilio 7138','email@email.com',0,'2002-09-21','1960-01-19','','Romina','65','154509050','4446107',3),(30790455,'Pintos',0,'Domicilio 8328','email@email.com',0,'2003-03-16','1967-11-03','','Romina','29','154122010','4853121',2),(30846541,'ORTIZ',0,'Domicilio 7804','email@email.com',0,'2002-08-08','1971-11-08','','Romina Valeria','56','154354297','4356117',1),(30935057,'ABINAGALDE',0,'Domicilio 8262','email@email.com',0,'2012-11-09','1967-05-29','','VALERIA ALEJANDRA','17','154487797','4191378',3),(33012397,'ALONSO',0,'Domicilio 3571','email@email.com',0,'2012-03-30','1976-02-13','','Blanca Zunilda','83','154600238','4451626',3),(36408906,'Machado',0,'Domicilio 9479','email@email.com',0,'2007-07-19','1972-10-02','','Mónica Beatríz','5','154269400','4062974',2),(92255489,'Nuñez Palacios',0,'Domicilio 5313','email@email.com',1,'1999-04-07','1976-03-09','','Alejandra Maria','62','154181509','4736512',2),(92452168,'Garcia',0,'Domicilio 1458','email@email.com',0,'2007-12-03','1982-04-16','','María Roque','53','154263284','4956825',1),(93512269,'Barraza Campos',0,'Domicilio 1832','email@email.com',1,'2014-05-14','1980-04-13','','Agustina Elizabeth','14','154643796','4360287',1),(93997742,'Avalos Oribe',0,'Domicilio 1823','email@email.com',0,'2006-02-13','1954-12-19','','Dionisia','51','154209996','4423500',3);
/*!40000 ALTER TABLE `socia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socia_deuda`
--

DROP TABLE IF EXISTS `socia_deuda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socia_deuda` (
  `Socia_DNI` bigint(20) NOT NULL,
  `deudas_IDDEUDA` bigint(20) NOT NULL,
  PRIMARY KEY (`Socia_DNI`,`deudas_IDDEUDA`),
  KEY `FK_SOCIA_DEUDA_deudas_IDDEUDA` (`deudas_IDDEUDA`),
  CONSTRAINT `FK_SOCIA_DEUDA_Socia_DNI` FOREIGN KEY (`Socia_DNI`) REFERENCES `socia` (`DNI`),
  CONSTRAINT `FK_SOCIA_DEUDA_deudas_IDDEUDA` FOREIGN KEY (`deudas_IDDEUDA`) REFERENCES `deuda` (`IDDEUDA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socia_deuda`
--

LOCK TABLES `socia_deuda` WRITE;
/*!40000 ALTER TABLE `socia_deuda` DISABLE KEYS */;
INSERT INTO `socia_deuda` VALUES (5917598,478),(6074300,482),(10534228,486),(10639418,490),(10955361,494),(11392148,498),(11399982,502),(11479299,506),(11479449,510),(11635309,514),(11661464,518),(11694515,522),(12296490,526),(12637259,530),(12637417,534),(12852501,538),(12898395,542),(12956647,546),(13004758,550),(13005252,554),(13005781,558),(13005891,562),(13005993,566),(13013763,570),(13248833,574),(13350813,578),(13421303,582),(13558172,586),(13658522,590),(13867641,594),(13897862,598),(13957943,602),(14181471,606),(14194183,610),(14209322,614),(14258039,618),(14258752,622),(14301412,626),(14335266,630),(14335484,634),(14469013,638),(14551343,642),(14713298,646),(14745833,650),(14745996,654),(14826658,658),(14826696,662),(14911492,666),(14926497,670),(14946666,674),(14946893,678),(14957513,682),(14960101,686),(14986595,690),(16163272,694),(16205157,698),(16356400,702),(16365101,706),(16365171,710),(16365251,714),(16367471,718),(16563703,722),(16650419,726),(16695422,730),(16695464,734),(16695503,738),(16695780,742),(16696948,746),(16829403,750),(16853984,754),(16871076,758),(16944749,762),(16986200,766),(16993353,770),(17039784,774),(17064155,778),(17093025,782),(17135365,786),(17135398,790),(17164587,794),(17170689,798),(17170744,802),(17216632,806),(17299811,810),(17312131,814),(17369473,818),(17378032,822),(17525644,826),(17525767,830),(17562547,834),(17562559,838),(17604599,842),(17630446,846),(17652488,850),(17675809,854),(17751922,858),(17760877,862),(17760927,866),(17773508,870),(17814471,874),(17831378,878),(17905182,882),(17952248,886),(17980404,890),(17980484,894),(18063802,898),(18095622,902),(18095845,906),(18180848,910),(18265145,914),(18290431,918),(18308518,922),(18438573,926),(18464360,930),(18465156,934),(18546184,938),(18683582,942),(18694386,946),(18701304,950),(18767528,954),(18776248,958),(18793716,962),(18801405,966),(20117775,970),(20117813,974),(20175929,978),(20186811,982),(20290582,986),(20338247,990),(20338614,994),(20338642,998),(20476201,1002),(20484881,1006),(20495953,1010),(20500992,1014),(20518423,1018),(20545757,1022),(20578722,1026),(20629246,1030),(20737427,1034),(20815489,1038),(20899640,1042),(20907159,1046),(20939295,1050),(21180225,1054),(21182875,1058),(21300349,1062),(21300428,1066),(21301810,1070),(21302821,1074),(21303869,1078),(21304070,1082),(21305751,1086),(21365232,1090),(21546890,1094),(21562507,1098),(21598339,1102),(21634884,1106),(21660785,1110),(21684508,1114),(21723344,1118),(21723721,1122),(21775640,1126),(21781027,1130),(21781619,1134),(21781681,1138),(21781725,1142),(21793705,1146),(21974458,1150),(21976884,1154),(22040318,1158),(22090129,1162),(22090330,1166),(22090333,1170),(22090400,1174),(22141132,1178),(22141155,1182),(22351819,1186),(22351900,1190),(22352199,1194),(22485728,1198),(22488411,1202),(22488625,1206),(22488745,1210),(22508922,1214),(22539277,1218),(22662681,1222),(22692609,1226),(22737993,1230),(22738113,1234),(22814059,1238),(22835570,1242),(22835678,1246),(22835931,1250),(22836855,1254),(22870686,1258),(22944957,1262),(23035827,1266),(23066520,1270),(23096336,1274),(23096644,1278),(23170837,1282),(23207943,1286),(23338376,1290),(23349910,1294),(23383304,1298),(23383428,1302),(23430610,1306),(23468003,1310),(23482663,1314),(23546861,1318),(23675117,1322),(23675136,1326),(23675148,1330),(23675260,1334),(23675431,1338),(23690040,1342),(23690119,1346),(23737897,1350),(23900628,1354),(23901844,1358),(23910719,1362),(23951159,1366),(23951555,1370),(23951570,1374),(24076719,1378),(24090590,1382),(24118105,1386),(24130561,1390),(24130661,1394),(24131387,1398),(24143117,1402),(24143256,1406),(24294655,1410),(24294858,1414),(24389202,1418),(24485566,1422),(24509951,1426),(24516692,1430),(24572255,1434),(24572310,1438),(24573249,1442),(24573403,1446),(24573604,1450),(24600324,1454),(24601104,1458),(24601250,1462),(24601563,1466),(24601655,1470),(24601704,1474),(24644783,1478),(24679829,1482),(24679887,1486),(24709560,1490),(24769433,1494),(24835945,1498),(24865425,1502),(24903025,1506),(24932200,1510),(24937904,1514),(24985441,1518),(24985506,1522),(24989460,1526),(25019526,1530),(25066453,1534),(25159104,1538),(25192099,1542),(25201554,1546),(25231594,1550),(25313007,1554),(25316907,1558),(25322125,1562),(25340444,1566),(25428447,1570),(25450204,1574),(25450265,1578),(25489363,1582),(25489484,1586),(25535836,1590),(25758210,1594),(25767251,1598),(25774560,1602),(25860834,1606),(25894270,1610),(25895655,1614),(25985040,1618),(26082506,1622),(26286013,1626),(26286171,1630),(26292240,1634),(26292622,1638),(26425752,1642),(26447307,1646),(26546441,1650),(26578164,1654),(26606894,1658),(26623490,1662),(26754871,1666),(26776185,1670),(26776882,1674),(26849016,1678),(26853222,1682),(27054164,1686),(27116817,1690),(27200223,1694),(27205305,1698),(27221986,1702),(27233372,1706),(27233664,1710),(27257937,1714),(27366789,1718),(27433794,1722),(27433797,1726),(27456218,1730),(27456856,1734),(27470356,1738),(27504424,1742),(27554105,1746),(27574610,1750),(27650131,1754),(27685910,1758),(27800047,1762),(27800068,1766),(27979166,1770),(27979343,1774),(27999936,1778),(28017815,1782),(28065205,1786),(28065478,1790),(28084616,1794),(28118896,1798),(28246117,1802),(28466304,1806),(28513597,1810),(28552811,1814),(28589963,1818),(28610740,1822),(28818364,1826),(28832531,1830),(28903354,1834),(29008677,1838),(29054393,1842),(29241507,1846),(29325180,1850),(29411528,1854),(29431996,1858),(29441092,1862),(29455785,1866),(29596937,1870),(29671618,1874),(29671701,1878),(29671992,1882),(29698224,1886),(29806907,1890),(29806908,1894),(30003106,1898),(30084028,1902),(30165274,1906),(30255272,1910),(30297834,1914),(30362346,1918),(30396924,1922),(30398079,1926),(30619550,1930),(30687895,1934),(30790108,1938),(30790455,1942),(30846541,1946),(30935057,1950),(33012397,1954),(36408906,1958),(92255489,1962),(92452168,1966),(93512269,1970),(93997742,1974);
/*!40000 ALTER TABLE `socia_deuda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socia_ergometria`
--

DROP TABLE IF EXISTS `socia_ergometria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socia_ergometria` (
  `Socia_DNI` bigint(20) NOT NULL,
  `ergometrias_IDERGOMETRIA` bigint(20) NOT NULL,
  PRIMARY KEY (`Socia_DNI`,`ergometrias_IDERGOMETRIA`),
  KEY `FK_SOCIA_ERGOMETRIA_ergometrias_IDERGOMETRIA` (`ergometrias_IDERGOMETRIA`),
  CONSTRAINT `FK_SOCIA_ERGOMETRIA_Socia_DNI` FOREIGN KEY (`Socia_DNI`) REFERENCES `socia` (`DNI`),
  CONSTRAINT `FK_SOCIA_ERGOMETRIA_ergometrias_IDERGOMETRIA` FOREIGN KEY (`ergometrias_IDERGOMETRIA`) REFERENCES `ergometria` (`IDERGOMETRIA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socia_ergometria`
--

LOCK TABLES `socia_ergometria` WRITE;
/*!40000 ALTER TABLE `socia_ergometria` DISABLE KEYS */;
INSERT INTO `socia_ergometria` VALUES (5917598,1976),(6074300,1977),(10534228,1978),(10639418,1979),(10955361,1980),(11392148,1981),(11399982,1982),(11479299,1983),(11479449,1984),(11635309,1985),(11661464,1986),(11694515,1987),(12296490,1988),(12637259,1989),(12637417,1990),(12852501,1991),(12898395,1992),(12956647,1993),(13004758,1994),(13005252,1995),(13005781,1996),(13005891,1997),(13005993,1998),(13013763,1999),(13248833,2000),(13350813,2001),(13421303,2002),(13558172,2003),(13658522,2004),(13867641,2005),(13897862,2006),(13957943,2007),(14181471,2008),(14194183,2009),(14209322,2010),(14258039,2011),(14258752,2012),(14301412,2013),(14335266,2014),(14335484,2015),(14469013,2016),(14551343,2017),(14713298,2018),(14745833,2019),(14745996,2020),(14826658,2021),(14826696,2022),(14911492,2023),(14926497,2024),(14946666,2025),(14946893,2026),(14957513,2027),(14960101,2028),(14986595,2029),(16163272,2030),(16205157,2031),(16356400,2032),(16365101,2033),(16365171,2034),(16365251,2035),(16367471,2036),(16563703,2037),(16650419,2038),(16695422,2039),(16695464,2040),(16695503,2041),(16695780,2042),(16696948,2043),(16829403,2044),(16853984,2045),(16871076,2046),(16944749,2047),(16986200,2048),(16993353,2049),(17039784,2050),(17064155,2051),(17093025,2052),(17135365,2053),(17135398,2054),(17164587,2055),(17170689,2056),(17170744,2057),(17216632,2058),(17299811,2059),(17312131,2060),(17369473,2061),(17378032,2062),(17525644,2063),(17525767,2064),(17562547,2065),(17562559,2066),(17604599,2067),(17630446,2068),(17652488,2069),(17675809,2070),(17751922,2071),(17760877,2072),(17760927,2073),(17773508,2074),(17814471,2075),(17831378,2076),(17905182,2077),(17952248,2078),(17980404,2079),(17980484,2080),(18063802,2081),(18095622,2082),(18095845,2083),(18180848,2084),(18265145,2085),(18290431,2086),(18308518,2087),(18438573,2088),(18464360,2089),(18465156,2090),(18546184,2091),(18683582,2092),(18694386,2093),(18701304,2094),(18767528,2095),(18776248,2096),(18793716,2097),(18801405,2098),(20117775,2099),(20117813,2100),(20175929,2101),(20186811,2102),(20290582,2103),(20338247,2104),(20338614,2105),(20338642,2106),(20476201,2107),(20484881,2108),(20495953,2109),(20500992,2110),(20518423,2111),(20545757,2112),(20578722,2113),(20629246,2114),(20737427,2115),(20815489,2116),(20899640,2117),(20907159,2118),(20939295,2119),(21180225,2120),(21182875,2121),(21300349,2122),(21300428,2123),(21301810,2124),(21302821,2125),(21303869,2126),(21304070,2127),(21305751,2128),(21365232,2129),(21546890,2130),(21562507,2131),(21598339,2132),(21634884,2133),(21660785,2134),(21684508,2135),(21723344,2136),(21723721,2137),(21775640,2138),(21781027,2139),(21781619,2140),(21781681,2141),(21781725,2142),(21793705,2143),(21974458,2144),(21976884,2145),(22040318,2146),(22090129,2147),(22090330,2148),(22090333,2149),(22090400,2150),(22141132,2151),(22141155,2152),(22351819,2153),(22351900,2154),(22352199,2155),(22485728,2156),(22488411,2157),(22488625,2158),(22488745,2159),(22508922,2160),(22539277,2161),(22662681,2162),(22692609,2163),(22737993,2164),(22738113,2165),(22814059,2166),(22835570,2167),(22835678,2168),(22835931,2169),(22836855,2170),(22870686,2171),(22944957,2172),(23035827,2173),(23066520,2174),(23096336,2175),(23096644,2176),(23170837,2177),(23207943,2178),(23338376,2179),(23349910,2180),(23383304,2181),(23383428,2182),(23430610,2183),(23468003,2184),(23482663,2185),(23546861,2186),(23675117,2187),(23675136,2188),(23675148,2189),(23675260,2190),(23675431,2191),(23690040,2192),(23690119,2193),(23737897,2194),(23900628,2195),(23901844,2196),(23910719,2197),(23951159,2198),(23951555,2199),(23951570,2200),(24076719,2201),(24090590,2202),(24118105,2203),(24130561,2204),(24130661,2205),(24131387,2206),(24143117,2207),(24143256,2208),(24294655,2209),(24294858,2210),(24389202,2211),(24485566,2212),(24509951,2213),(24516692,2214),(24572255,2215),(24572310,2216),(24573249,2217),(24573403,2218),(24573604,2219),(24600324,2220),(24601104,2221),(24601250,2222),(24601563,2223),(24601655,2224),(24601704,2225),(24644783,2226),(24679829,2227),(24679887,2228),(24709560,2229),(24769433,2230),(24835945,2231),(24865425,2232),(24903025,2233),(24932200,2234),(24937904,2235),(24985441,2236),(24985506,2237),(24989460,2238),(25019526,2239),(25066453,2240),(25159104,2241),(25192099,2242),(25201554,2243),(25231594,2244),(25313007,2245),(25316907,2246),(25322125,2247),(25340444,2248),(25428447,2249),(25450204,2250),(25450265,2251),(25489363,2252),(25489484,2253),(25535836,2254),(25758210,2255),(25767251,2256),(25774560,2257),(25860834,2258),(25894270,2259),(25895655,2260),(25985040,2261),(26082506,2262),(26286013,2263),(26286171,2264),(26292240,2265),(26292622,2266),(26425752,2267),(26447307,2268),(26546441,2269),(26578164,2270),(26606894,2271),(26623490,2272),(26754871,2273),(26776185,2274),(26776882,2275),(26849016,2276),(26853222,2277),(27054164,2278),(27116817,2279),(27200223,2280),(27205305,2281),(27221986,2282),(27233372,2283),(27233664,2284),(27257937,2285),(27366789,2286),(27433794,2287),(27433797,2288),(27456218,2289),(27456856,2290),(27470356,2291),(27504424,2292),(27554105,2293),(27574610,2294),(27650131,2295),(27685910,2296),(27800047,2297),(27800068,2298),(27979166,2299),(27979343,2300),(27999936,2301),(28017815,2302),(28065205,2303),(28065478,2304),(28084616,2305),(28118896,2306),(28246117,2307),(28466304,2308),(28513597,2309),(28552811,2310),(28589963,2311),(28610740,2312),(28818364,2313),(28832531,2314),(28903354,2315),(29008677,2316),(29054393,2317),(29241507,2318),(29325180,2319),(29411528,2320),(29431996,2321),(29441092,2322),(29455785,2323),(29596937,2324),(29671618,2325),(29671701,2326),(29671992,2327),(29698224,2328),(29806907,2329),(29806908,2330),(30003106,2331),(30084028,2332),(30165274,2333),(30255272,2334),(30297834,2335),(30362346,2336),(30396924,2337),(30398079,2338),(30619550,2339),(30687895,2340),(30790108,2341),(30790455,2342),(30846541,2343),(30935057,2344),(33012397,2345),(36408906,2346),(92255489,2347),(92452168,2348),(93512269,2349),(93997742,2350);
/*!40000 ALTER TABLE `socia_ergometria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socia_estado`
--

DROP TABLE IF EXISTS `socia_estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socia_estado` (
  `Socia_DNI` bigint(20) NOT NULL,
  `estados_IDESTADO` bigint(20) NOT NULL,
  PRIMARY KEY (`Socia_DNI`,`estados_IDESTADO`),
  KEY `FK_SOCIA_ESTADO_estados_IDESTADO` (`estados_IDESTADO`),
  CONSTRAINT `FK_SOCIA_ESTADO_Socia_DNI` FOREIGN KEY (`Socia_DNI`) REFERENCES `socia` (`DNI`),
  CONSTRAINT `FK_SOCIA_ESTADO_estados_IDESTADO` FOREIGN KEY (`estados_IDESTADO`) REFERENCES `estado` (`IDESTADO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socia_estado`
--

LOCK TABLES `socia_estado` WRITE;
/*!40000 ALTER TABLE `socia_estado` DISABLE KEYS */;
INSERT INTO `socia_estado` VALUES (5917598,101),(6074300,102),(10534228,103),(10639418,104),(10955361,105),(11392148,106),(11399982,107),(11479299,108),(11479449,109),(11635309,110),(11661464,111),(11694515,112),(12296490,113),(12637259,114),(12637417,115),(12852501,116),(12898395,117),(12956647,118),(13004758,119),(13005252,120),(13005781,121),(13005891,122),(13005993,123),(13013763,124),(13248833,125),(13350813,126),(13421303,127),(13558172,128),(13658522,129),(13867641,130),(13897862,131),(13957943,132),(14181471,133),(14194183,134),(14209322,135),(14258039,136),(14258752,137),(14301412,138),(14335266,139),(14335484,140),(14469013,141),(14551343,142),(14713298,143),(14745833,144),(14745996,145),(14826658,146),(14826696,147),(14911492,148),(14926497,149),(14946666,150),(14946893,151),(14957513,152),(14960101,153),(14986595,154),(16163272,155),(16205157,156),(16356400,157),(16365101,158),(16365171,159),(16365251,160),(16367471,161),(16563703,162),(16650419,163),(16695422,164),(16695464,165),(16695503,166),(16695780,167),(16696948,168),(16829403,169),(16853984,170),(16871076,171),(16944749,172),(16986200,173),(16993353,174),(17039784,175),(17064155,176),(17093025,177),(17135365,178),(17135398,179),(17164587,180),(17170689,181),(17170744,182),(17216632,183),(17299811,184),(17312131,185),(17369473,186),(17378032,187),(17525644,188),(17525767,189),(17562547,190),(17562559,191),(17604599,192),(17630446,193),(17652488,194),(17675809,195),(17751922,196),(17760877,197),(17760927,198),(17773508,199),(17814471,200),(17831378,201),(17905182,202),(17952248,203),(17980404,204),(17980484,205),(18063802,206),(18095622,207),(18095845,208),(18180848,209),(18265145,210),(18290431,211),(18308518,212),(18438573,213),(18464360,214),(18465156,215),(18546184,216),(18683582,217),(18694386,218),(18701304,219),(18767528,220),(18776248,221),(18793716,222),(18801405,223),(20117775,224),(20117813,225),(20175929,226),(20186811,227),(20290582,228),(20338247,229),(20338614,230),(20338642,231),(20476201,232),(20484881,233),(20495953,234),(20500992,235),(20518423,236),(20545757,237),(20578722,238),(20629246,239),(20737427,240),(20815489,241),(20899640,242),(20907159,243),(20939295,244),(21180225,245),(21182875,246),(21300349,247),(21300428,248),(21301810,249),(21302821,250),(21303869,251),(21304070,252),(21305751,253),(21365232,254),(21546890,255),(21562507,256),(21598339,257),(21634884,258),(21660785,259),(21684508,260),(21723344,261),(21723721,262),(21775640,263),(21781027,264),(21781619,265),(21781681,266),(21781725,267),(21793705,268),(21974458,269),(21976884,270),(22040318,271),(22090129,272),(22090330,273),(22090333,274),(22090400,275),(22141132,276),(22141155,277),(22351819,278),(22351900,279),(22352199,280),(22485728,281),(22488411,282),(22488625,283),(22488745,284),(22508922,285),(22539277,286),(22662681,287),(22692609,288),(22737993,289),(22738113,290),(22814059,291),(22835570,292),(22835678,293),(22835931,294),(22836855,295),(22870686,296),(22944957,297),(23035827,298),(23066520,299),(23096336,300),(23096644,301),(23170837,302),(23207943,303),(23338376,304),(23349910,305),(23383304,306),(23383428,307),(23430610,308),(23468003,309),(23482663,310),(23546861,311),(23675117,312),(23675136,313),(23675148,314),(23675260,315),(23675431,316),(23690040,317),(23690119,318),(23737897,319),(23900628,320),(23901844,321),(23910719,322),(23951159,323),(23951555,324),(23951570,325),(24076719,326),(24090590,327),(24118105,328),(24130561,329),(24130661,330),(24131387,331),(24143117,332),(24143256,333),(24294655,334),(24294858,335),(24389202,336),(24485566,337),(24509951,338),(24516692,339),(24572255,340),(24572310,341),(24573249,342),(24573403,343),(24573604,344),(24600324,345),(24601104,346),(24601250,347),(24601563,348),(24601655,349),(24601704,350),(24644783,351),(24679829,352),(24679887,353),(24709560,354),(24769433,355),(24835945,356),(24865425,357),(24903025,358),(24932200,359),(24937904,360),(24985441,361),(24985506,362),(24989460,363),(25019526,364),(25066453,365),(25159104,366),(25192099,367),(25201554,368),(25231594,369),(25313007,370),(25316907,371),(25322125,372),(25340444,373),(25428447,374),(25450204,375),(25450265,376),(25489363,377),(25489484,378),(25535836,379),(25758210,380),(25767251,381),(25774560,382),(25860834,383),(25894270,384),(25895655,385),(25985040,386),(26082506,387),(26286013,388),(26286171,389),(26292240,390),(26292622,391),(26425752,392),(26447307,393),(26546441,394),(26578164,395),(26606894,396),(26623490,397),(26754871,398),(26776185,399),(26776882,400),(26849016,401),(26853222,402),(27054164,403),(27116817,404),(27200223,405),(27205305,406),(27221986,407),(27233372,408),(27233664,409),(27257937,410),(27366789,411),(27433794,412),(27433797,413),(27456218,414),(27456856,415),(27470356,416),(27504424,417),(27554105,418),(27574610,419),(27650131,420),(27685910,421),(27800047,422),(27800068,423),(27979166,424),(27979343,425),(27999936,426),(28017815,427),(28065205,428),(28065478,429),(28084616,430),(28118896,431),(28246117,432),(28466304,433),(28513597,434),(28552811,435),(28589963,436),(28610740,437),(28818364,438),(28832531,439),(28903354,440),(29008677,441),(29054393,442),(29241507,443),(29325180,444),(29411528,445),(29431996,446),(29441092,447),(29455785,448),(29596937,449),(29671618,450),(29671701,451),(29671992,452),(29698224,453),(29806907,454),(29806908,455),(30003106,456),(30084028,457),(30165274,458),(30255272,459),(30297834,460),(30362346,461),(30396924,462),(30398079,463),(30619550,464),(30687895,465),(30790108,466),(30790455,467),(30846541,468),(30935057,469),(33012397,470),(36408906,471),(92255489,472),(92452168,473),(93512269,474),(93997742,475);
/*!40000 ALTER TABLE `socia_estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socia_gol`
--

DROP TABLE IF EXISTS `socia_gol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socia_gol` (
  `Socia_DNI` bigint(20) NOT NULL,
  `goles_IDGOL` bigint(20) NOT NULL,
  PRIMARY KEY (`Socia_DNI`,`goles_IDGOL`),
  KEY `FK_SOCIA_GOL_goles_IDGOL` (`goles_IDGOL`),
  CONSTRAINT `FK_SOCIA_GOL_Socia_DNI` FOREIGN KEY (`Socia_DNI`) REFERENCES `socia` (`DNI`),
  CONSTRAINT `FK_SOCIA_GOL_goles_IDGOL` FOREIGN KEY (`goles_IDGOL`) REFERENCES `gol` (`IDGOL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socia_gol`
--

LOCK TABLES `socia_gol` WRITE;
/*!40000 ALTER TABLE `socia_gol` DISABLE KEYS */;
/*!40000 ALTER TABLE `socia_gol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socia_pase`
--

DROP TABLE IF EXISTS `socia_pase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socia_pase` (
  `Socia_DNI` bigint(20) NOT NULL,
  `pases_IDPASE` bigint(20) NOT NULL,
  PRIMARY KEY (`Socia_DNI`,`pases_IDPASE`),
  KEY `FK_SOCIA_PASE_pases_IDPASE` (`pases_IDPASE`),
  CONSTRAINT `FK_SOCIA_PASE_Socia_DNI` FOREIGN KEY (`Socia_DNI`) REFERENCES `socia` (`DNI`),
  CONSTRAINT `FK_SOCIA_PASE_pases_IDPASE` FOREIGN KEY (`pases_IDPASE`) REFERENCES `pase` (`IDPASE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socia_pase`
--

LOCK TABLES `socia_pase` WRITE;
/*!40000 ALTER TABLE `socia_pase` DISABLE KEYS */;
INSERT INTO `socia_pase` VALUES (5917598,479),(6074300,483),(10534228,487),(10639418,491),(10955361,495),(11392148,499),(11399982,503),(11479299,507),(11479449,511),(11635309,515),(11661464,519),(11694515,523),(12296490,527),(12637259,531),(12637417,535),(12852501,539),(12898395,543),(12956647,547),(13004758,551),(13005252,555),(13005781,559),(13005891,563),(13005993,567),(13013763,571),(13248833,575),(13350813,579),(13421303,583),(13558172,587),(13658522,591),(13867641,595),(13897862,599),(13957943,603),(14181471,607),(14194183,611),(14209322,615),(14258039,619),(14258752,623),(14301412,627),(14335266,631),(14335484,635),(14469013,639),(14551343,643),(14713298,647),(14745833,651),(14745996,655),(14826658,659),(14826696,663),(14911492,667),(14926497,671),(14946666,675),(14946893,679),(14957513,683),(14960101,687),(14986595,691),(16163272,695),(16205157,699),(16356400,703),(16365101,707),(16365171,711),(16365251,715),(16367471,719),(16563703,723),(16650419,727),(16695422,731),(16695464,735),(16695503,739),(16695780,743),(16696948,747),(16829403,751),(16853984,755),(16871076,759),(16944749,763),(16986200,767),(16993353,771),(17039784,775),(17064155,779),(17093025,783),(17135365,787),(17135398,791),(17164587,795),(17170689,799),(17170744,803),(17216632,807),(17299811,811),(17312131,815),(17369473,819),(17378032,823),(17525644,827),(17525767,831),(17562547,835),(17562559,839),(17604599,843),(17630446,847),(17652488,851),(17675809,855),(17751922,859),(17760877,863),(17760927,867),(17773508,871),(17814471,875),(17831378,879),(17905182,883),(17952248,887),(17980404,891),(17980484,895),(18063802,899),(18095622,903),(18095845,907),(18180848,911),(18265145,915),(18290431,919),(18308518,923),(18438573,927),(18464360,931),(18465156,935),(18546184,939),(18683582,943),(18694386,947),(18701304,951),(18767528,955),(18776248,959),(18793716,963),(18801405,967),(20117775,971),(20117813,975),(20175929,979),(20186811,983),(20290582,987),(20338247,991),(20338614,995),(20338642,999),(20476201,1003),(20484881,1007),(20495953,1011),(20500992,1015),(20518423,1019),(20545757,1023),(20578722,1027),(20629246,1031),(20737427,1035),(20815489,1039),(20899640,1043),(20907159,1047),(20939295,1051),(21180225,1055),(21182875,1059),(21300349,1063),(21300428,1067),(21301810,1071),(21302821,1075),(21303869,1079),(21304070,1083),(21305751,1087),(21365232,1091),(21546890,1095),(21562507,1099),(21598339,1103),(21634884,1107),(21660785,1111),(21684508,1115),(21723344,1119),(21723721,1123),(21775640,1127),(21781027,1131),(21781619,1135),(21781681,1139),(21781725,1143),(21793705,1147),(21974458,1151),(21976884,1155),(22040318,1159),(22090129,1163),(22090330,1167),(22090333,1171),(22090400,1175),(22141132,1179),(22141155,1183),(22351819,1187),(22351900,1191),(22352199,1195),(22485728,1199),(22488411,1203),(22488625,1207),(22488745,1211),(22508922,1215),(22539277,1219),(22662681,1223),(22692609,1227),(22737993,1231),(22738113,1235),(22814059,1239),(22835570,1243),(22835678,1247),(22835931,1251),(22836855,1255),(22870686,1259),(22944957,1263),(23035827,1267),(23066520,1271),(23096336,1275),(23096644,1279),(23170837,1283),(23207943,1287),(23338376,1291),(23349910,1295),(23383304,1299),(23383428,1303),(23430610,1307),(23468003,1311),(23482663,1315),(23546861,1319),(23675117,1323),(23675136,1327),(23675148,1331),(23675260,1335),(23675431,1339),(23690040,1343),(23690119,1347),(23737897,1351),(23900628,1355),(23901844,1359),(23910719,1363),(23951159,1367),(23951555,1371),(23951570,1375),(24076719,1379),(24090590,1383),(24118105,1387),(24130561,1391),(24130661,1395),(24131387,1399),(24143117,1403),(24143256,1407),(24294655,1411),(24294858,1415),(24389202,1419),(24485566,1423),(24509951,1427),(24516692,1431),(24572255,1435),(24572310,1439),(24573249,1443),(24573403,1447),(24573604,1451),(24600324,1455),(24601104,1459),(24601250,1463),(24601563,1467),(24601655,1471),(24601704,1475),(24644783,1479),(24679829,1483),(24679887,1487),(24709560,1491),(24769433,1495),(24835945,1499),(24865425,1503),(24903025,1507),(24932200,1511),(24937904,1515),(24985441,1519),(24985506,1523),(24989460,1527),(25019526,1531),(25066453,1535),(25159104,1539),(25192099,1543),(25201554,1547),(25231594,1551),(25313007,1555),(25316907,1559),(25322125,1563),(25340444,1567),(25428447,1571),(25450204,1575),(25450265,1579),(25489363,1583),(25489484,1587),(25535836,1591),(25758210,1595),(25767251,1599),(25774560,1603),(25860834,1607),(25894270,1611),(25895655,1615),(25985040,1619),(26082506,1623),(26286013,1627),(26286171,1631),(26292240,1635),(26292622,1639),(26425752,1643),(26447307,1647),(26546441,1651),(26578164,1655),(26606894,1659),(26623490,1663),(26754871,1667),(26776185,1671),(26776882,1675),(26849016,1679),(26853222,1683),(27054164,1687),(27116817,1691),(27200223,1695),(27205305,1699),(27221986,1703),(27233372,1707),(27233664,1711),(27257937,1715),(27366789,1719),(27433794,1723),(27433797,1727),(27456218,1731),(27456856,1735),(27470356,1739),(27504424,1743),(27554105,1747),(27574610,1751),(27650131,1755),(27685910,1759),(27800047,1763),(27800068,1767),(27979166,1771),(27979343,1775),(27999936,1779),(28017815,1783),(28065205,1787),(28065478,1791),(28084616,1795),(28118896,1799),(28246117,1803),(28466304,1807),(28513597,1811),(28552811,1815),(28589963,1819),(28610740,1823),(28818364,1827),(28832531,1831),(28903354,1835),(29008677,1839),(29054393,1843),(29241507,1847),(29325180,1851),(29411528,1855),(29431996,1859),(29441092,1863),(29455785,1867),(29596937,1871),(29671618,1875),(29671701,1879),(29671992,1883),(29698224,1887),(29806907,1891),(29806908,1895),(30003106,1899),(30084028,1903),(30165274,1907),(30255272,1911),(30297834,1915),(30362346,1919),(30396924,1923),(30398079,1927),(30619550,1931),(30687895,1935),(30790108,1939),(30790455,1943),(30846541,1947),(30935057,1951),(33012397,1955),(36408906,1959),(92255489,1963),(92452168,1967),(93512269,1971),(93997742,1975);
/*!40000 ALTER TABLE `socia_pase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socia_sanciontribunal`
--

DROP TABLE IF EXISTS `socia_sanciontribunal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socia_sanciontribunal` (
  `Socia_DNI` bigint(20) NOT NULL,
  `sancionesTribunal_IDSANCIONTRIBUNAL` bigint(20) NOT NULL,
  PRIMARY KEY (`Socia_DNI`,`sancionesTribunal_IDSANCIONTRIBUNAL`),
  KEY `SCSANCIONTRIBUNALsncionesTribunalIDSANCIONTRIBUNAL` (`sancionesTribunal_IDSANCIONTRIBUNAL`),
  CONSTRAINT `FK_SOCIA_SANCIONTRIBUNAL_Socia_DNI` FOREIGN KEY (`Socia_DNI`) REFERENCES `socia` (`DNI`),
  CONSTRAINT `SCSANCIONTRIBUNALsncionesTribunalIDSANCIONTRIBUNAL` FOREIGN KEY (`sancionesTribunal_IDSANCIONTRIBUNAL`) REFERENCES `sanciontribunal` (`IDSANCIONTRIBUNAL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socia_sanciontribunal`
--

LOCK TABLES `socia_sanciontribunal` WRITE;
/*!40000 ALTER TABLE `socia_sanciontribunal` DISABLE KEYS */;
/*!40000 ALTER TABLE `socia_sanciontribunal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socia_tarjeta`
--

DROP TABLE IF EXISTS `socia_tarjeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socia_tarjeta` (
  `Socia_DNI` bigint(20) NOT NULL,
  `tarjetas_IDTARJETA` bigint(20) NOT NULL,
  PRIMARY KEY (`Socia_DNI`,`tarjetas_IDTARJETA`),
  KEY `FK_SOCIA_TARJETA_tarjetas_IDTARJETA` (`tarjetas_IDTARJETA`),
  CONSTRAINT `FK_SOCIA_TARJETA_Socia_DNI` FOREIGN KEY (`Socia_DNI`) REFERENCES `socia` (`DNI`),
  CONSTRAINT `FK_SOCIA_TARJETA_tarjetas_IDTARJETA` FOREIGN KEY (`tarjetas_IDTARJETA`) REFERENCES `tarjeta` (`IDTARJETA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socia_tarjeta`
--

LOCK TABLES `socia_tarjeta` WRITE;
/*!40000 ALTER TABLE `socia_tarjeta` DISABLE KEYS */;
/*!40000 ALTER TABLE `socia_tarjeta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarjeta`
--

DROP TABLE IF EXISTS `tarjeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tarjeta` (
  `IDTARJETA` bigint(20) NOT NULL,
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `COMPUTADO` tinyint(1) DEFAULT '0',
  `FECHA` date DEFAULT NULL,
  `MINUTO` varchar(255) DEFAULT NULL,
  `MOTIVO` varchar(1000) DEFAULT NULL,
  `TIEMPO` varchar(255) DEFAULT NULL,
  `TIPO` varchar(255) DEFAULT NULL,
  `UNTORNEO_IDTORNEO` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`IDTARJETA`),
  KEY `FK_TARJETA_UNTORNEO_IDTORNEO` (`UNTORNEO_IDTORNEO`),
  CONSTRAINT `FK_TARJETA_UNTORNEO_IDTORNEO` FOREIGN KEY (`UNTORNEO_IDTORNEO`) REFERENCES `torneo` (`IDTORNEO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarjeta`
--

LOCK TABLES `tarjeta` WRITE;
/*!40000 ALTER TABLE `tarjeta` DISABLE KEYS */;
/*!40000 ALTER TABLE `tarjeta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipocancha`
--

DROP TABLE IF EXISTS `tipocancha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipocancha` (
  `IDTIPOCANCHA` bigint(20) NOT NULL,
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `NOMBRE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDTIPOCANCHA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipocancha`
--

LOCK TABLES `tipocancha` WRITE;
/*!40000 ALTER TABLE `tipocancha` DISABLE KEYS */;
INSERT INTO `tipocancha` VALUES (1,0,'Sintetica c/Luz'),(2,0,'Tierra Batida c/Luz'),(3,0,'Pasto c/Luz'),(4,0,'Polvo de Ladrillo c/Luz'),(5,0,'Sintetica s/Luz'),(6,0,'Tierra Batidas/Luz'),(7,0,'Pastos/Luz'),(8,0,'Polvo de Ladrillos/Luz');
/*!40000 ALTER TABLE `tipocancha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoestado`
--

DROP TABLE IF EXISTS `tipoestado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipoestado` (
  `IDTIPOESTADO` bigint(20) NOT NULL,
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `NOMBRE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDTIPOESTADO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoestado`
--

LOCK TABLES `tipoestado` WRITE;
/*!40000 ALTER TABLE `tipoestado` DISABLE KEYS */;
INSERT INTO `tipoestado` VALUES (1,0,'Socia'),(2,0,'Jugadora'),(3,0,'Licencia'),(4,0,'Baja'),(5,0,'Baja por Mora');
/*!40000 ALTER TABLE `tipoestado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `torneo`
--

DROP TABLE IF EXISTS `torneo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `torneo` (
  `IDTORNEO` bigint(20) NOT NULL,
  `BORRADOLOGICO` tinyint(1) DEFAULT '0',
  `FECHAINICIO` date DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `UNTORNEOPADRE_IDTORNEO` bigint(20) DEFAULT NULL,
  `UNACATEGORIA_IDCATEGORIA` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`IDTORNEO`),
  KEY `FK_TORNEO_UNTORNEOPADRE_IDTORNEO` (`UNTORNEOPADRE_IDTORNEO`),
  KEY `FK_TORNEO_UNACATEGORIA_IDCATEGORIA` (`UNACATEGORIA_IDCATEGORIA`),
  CONSTRAINT `FK_TORNEO_UNACATEGORIA_IDCATEGORIA` FOREIGN KEY (`UNACATEGORIA_IDCATEGORIA`) REFERENCES `categoria` (`IDCATEGORIA`),
  CONSTRAINT `FK_TORNEO_UNTORNEOPADRE_IDTORNEO` FOREIGN KEY (`UNTORNEOPADRE_IDTORNEO`) REFERENCES `torneo` (`IDTORNEO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `torneo`
--

LOCK TABLES `torneo` WRITE;
/*!40000 ALTER TABLE `torneo` DISABLE KEYS */;
/*!40000 ALTER TABLE `torneo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `torneo_equipo`
--

DROP TABLE IF EXISTS `torneo_equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `torneo_equipo` (
  `Torneo_IDTORNEO` bigint(20) NOT NULL,
  `equiposInscriptos_IDEQUIPO` bigint(20) NOT NULL,
  PRIMARY KEY (`Torneo_IDTORNEO`,`equiposInscriptos_IDEQUIPO`),
  KEY `FK_TORNEO_EQUIPO_equiposInscriptos_IDEQUIPO` (`equiposInscriptos_IDEQUIPO`),
  CONSTRAINT `FK_TORNEO_EQUIPO_Torneo_IDTORNEO` FOREIGN KEY (`Torneo_IDTORNEO`) REFERENCES `torneo` (`IDTORNEO`),
  CONSTRAINT `FK_TORNEO_EQUIPO_equiposInscriptos_IDEQUIPO` FOREIGN KEY (`equiposInscriptos_IDEQUIPO`) REFERENCES `equipo` (`IDEQUIPO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `torneo_equipo`
--

LOCK TABLES `torneo_equipo` WRITE;
/*!40000 ALTER TABLE `torneo_equipo` DISABLE KEYS */;
/*!40000 ALTER TABLE `torneo_equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `torneo_fechatorneo`
--

DROP TABLE IF EXISTS `torneo_fechatorneo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `torneo_fechatorneo` (
  `Torneo_IDTORNEO` bigint(20) NOT NULL,
  `fechasTorneo_IDFECHA` bigint(20) NOT NULL,
  PRIMARY KEY (`Torneo_IDTORNEO`,`fechasTorneo_IDFECHA`),
  KEY `FK_TORNEO_FECHATORNEO_fechasTorneo_IDFECHA` (`fechasTorneo_IDFECHA`),
  CONSTRAINT `FK_TORNEO_FECHATORNEO_Torneo_IDTORNEO` FOREIGN KEY (`Torneo_IDTORNEO`) REFERENCES `torneo` (`IDTORNEO`),
  CONSTRAINT `FK_TORNEO_FECHATORNEO_fechasTorneo_IDFECHA` FOREIGN KEY (`fechasTorneo_IDFECHA`) REFERENCES `fechatorneo` (`IDFECHA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `torneo_fechatorneo`
--

LOCK TABLES `torneo_fechatorneo` WRITE;
/*!40000 ALTER TABLE `torneo_fechatorneo` DISABLE KEYS */;
/*!40000 ALTER TABLE `torneo_fechatorneo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-19 17:40:14
