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
INSERT INTO `localidad` VALUES (6,0,'3364','2 de Mayo'),(7,0,'3363','25 de Mayo'),(8,0,'3363','9 de Julio'),(9,0,'3363','Alba Posse'),(10,0,'3350','Apóstoles'),(11,0,'3364','Aristóbulo del Valle'),(12,0,'3366','Bernardo de Irigoyen'),(13,0,'3317','Bonpland'),(14,0,'3362','Campo Grande'),(15,0,'3362','Campo Viera'),(16,0,'3308','Candelaria'),(17,0,'3332','Capioví'),(18,0,'3313','Cerro Azul'),(19,0,'3309','Cerro Corá'),(20,0,'3311','Colonia Alberdi'),(21,0,'3363','Colonia Aurora'),(22,0,'3386','Caraguatay'),(23,0,'3382','Colonia Delicia'),(24,0,'3326','Colonia Polana'),(25,0,'3355','Concepción de la Sierra'),(26,0,'3327','Corpus'),(27,0,'3364','Comandante Andresito'),(28,0,'3315','Dos Arroyos'),(29,0,'3384','El Alcázar'),(30,0,'3364','El Soberbio'),(31,0,'3382','El Dorado'),(32,0,'3334','Garuhapé'),(33,0,'3304','Garupá'),(34,0,'3324','Gobernador Roca'),(35,0,'3361','Guaraní'),(36,0,'3353','Itacaruaré'),(37,0,'3328','Jardín América'),(38,0,'3315','Leandro N Alem'),(39,0,'3316','Loreto'),(40,0,'3304','Miguel Lanús'),(41,0,'3315','Mojón Grande'),(42,0,'3384','Montecarlo'),(43,0,'3360','Oberá'),(44,0,'3361','Panambí'),(45,0,'3300','Posadas'),(46,0,'3378','Puerto Esperanza'),(47,0,'3370','Puerto Iguazú'),(48,0,'3370','Puerto Libertad'),(49,0,'3381','Puerto Piray'),(50,0,'3334','Puerto Rico'),(51,0,'3334','Ruiz de Montoya'),(52,0,'3366','San Antonio'),(53,0,'3322','San Ignacio'),(54,0,'3357','San Javier'),(55,0,'3306','San José'),(56,0,'3364','San Pedro'),(57,0,'3364','San Vicente'),(58,0,'3316','Santa Ana'),(59,0,'3363','Santa Rita'),(60,0,'3326','Santo Pipó'),(61,0,'3364','Salto Encantado'),(62,0,'3376','Wanda');
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
INSERT INTO `sequence` VALUES ('SEQ_GEN',100);
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

-- Dump completed on 2015-05-19 16:55:16
