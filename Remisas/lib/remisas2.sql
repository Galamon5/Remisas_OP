-- MySQL dump 10.13  Distrib 5.7.20, for Win64 (x86_64)
--
-- Host: localhost    Database: remisa
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `clientefisico`
--

DROP TABLE IF EXISTS `clientefisico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientefisico` (
  `noCliente` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `direccionLocal` varchar(45) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `direccionPersonal` varchar(45) DEFAULT NULL,
  `tipoCliente` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`noCliente`),
  UNIQUE KEY `rfc_UNIQUE` (`noCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientefisico`
--

LOCK TABLES `clientefisico` WRITE;
/*!40000 ALTER TABLE `clientefisico` DISABLE KEYS */;
INSERT INTO `clientefisico` VALUES (1,'Mario Gutierrez','Calle Tabasco #13 Col.Granjas Edo Mex','mario@hotmail.com','Calle Tabasco #13 Col.Granjas Edo Mex','Fisico'),(2,'Maria Fernanda Del Rio','Av. 15 de Septiembre #34 Del. Miguel Hidalgo','maFercha@outlook.com','Calle Elota #04 Col.Tutucule Edo. Mex','Fisico');
/*!40000 ALTER TABLE `clientefisico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientemoral`
--

DROP TABLE IF EXISTS `clientemoral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientemoral` (
  `noCliente` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `direccionLocal` varchar(45) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `tipoCliente` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`noCliente`),
  UNIQUE KEY `noHacienda_UNIQUE` (`noCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientemoral`
--

LOCK TABLES `clientemoral` WRITE;
/*!40000 ALTER TABLE `clientemoral` DISABLE KEYS */;
INSERT INTO `clientemoral` VALUES (15,'El estudiante S.A. de C.V.','Av. del trabajo #23 Del. Cuauhtemoc','elStudent@hotmail.com','2'),(48,'Papeleria doña Maria','Av. Reforma #3434 Col. Tultepec Edo. de Mexic','doñapape@yahoo.com','2');
/*!40000 ALTER TABLE `clientemoral` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido` (
  `idPedido` int(11) NOT NULL AUTO_INCREMENT,
  `fechaCreacion` date DEFAULT NULL,
  `fk_cliente` int(11) NOT NULL,
  `puesto` varchar(45) DEFAULT NULL,
  `nombreComprador` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idPedido`),
  UNIQUE KEY `idPedido_UNIQUE` (`idPedido`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (1,'2017-11-28',1,NULL,NULL),(2,'2017-11-29',2,NULL,NULL);
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidoproducto`
--

DROP TABLE IF EXISTS `pedidoproducto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedidoproducto` (
  `idPedido` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `idProducto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidoproducto`
--

LOCK TABLES `pedidoproducto` WRITE;
/*!40000 ALTER TABLE `pedidoproducto` DISABLE KEYS */;
INSERT INTO `pedidoproducto` VALUES (1,25,6),(1,10,1),(1,8,4),(2,2,2),(2,14,7),(2,15,9),(3,2,4),(3,5,9),(4,2,1),(4,2,2),(4,2,9);
/*!40000 ALTER TABLE `pedidoproducto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto` (
  `idProducto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `marca` varchar(45) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `cantidadPorCaja` int(11) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  PRIMARY KEY (`idProducto`),
  UNIQUE KEY `idProducto_UNIQUE` (`idProducto`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'Tijeras','Barrilito',50,50,50),(2,'Cinta adhesiva','Scetch',100,20,30),(3,'Goma','Disney',10,50,45.5),(4,'Goma','Scetch',13,45,20.84),(5,'Lapiz','Big',100,50,20.5),(6,'Pluma-Negra','Big',80,20,20.5),(7,'Pluma-Azul','Big',80,20,20.5),(8,'Pluma-Roja','Big',80,20,20.5),(9,'Regla','Barrilito',80,10,20);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registro`
--

DROP TABLE IF EXISTS `registro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registro` (
  `idRegistro` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `monto` double DEFAULT NULL,
  `motivo` varchar(45) DEFAULT NULL,
  `fk_pedido` int(11) NOT NULL,
  PRIMARY KEY (`idRegistro`),
  UNIQUE KEY `idRegistro_UNIQUE` (`idRegistro`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registro`
--

LOCK TABLES `registro` WRITE;
/*!40000 ALTER TABLE `registro` DISABLE KEYS */;
INSERT INTO `registro` VALUES (1,'2017-11-22',50,'pago',3),(2,'2017-11-27',50,'pago',3),(3,'2017-10-30',60,'pago',4),(4,'2017-11-05',40,'pago',4),(5,'2017-11-15',100,'liquidacion de remisa',4);
/*!40000 ALTER TABLE `registro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `remisa`
--

DROP TABLE IF EXISTS `remisa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `remisa` (
  `idPedido` int(11) NOT NULL,
  `fechaCreacion` date DEFAULT NULL,
  `fechaRegistro` date DEFAULT NULL,
  `montoTotal` double DEFAULT NULL,
  `monto` double DEFAULT NULL,
  `fechaLimite` date DEFAULT NULL,
  `fk_cliente` int(11) NOT NULL,
  `puesto` varchar(45) DEFAULT NULL,
  `nombreComprador` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idPedido`),
  UNIQUE KEY `idPedido_UNIQUE` (`idPedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remisa`
--

LOCK TABLES `remisa` WRITE;
/*!40000 ALTER TABLE `remisa` DISABLE KEYS */;
INSERT INTO `remisa` VALUES (3,'2017-11-15','2017-11-20',141.65,41.65,'2017-12-05',15,NULL,NULL);
/*!40000 ALTER TABLE `remisa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `remisapagada`
--

DROP TABLE IF EXISTS `remisapagada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `remisapagada` (
  `idPedido` int(11) NOT NULL,
  `fechaCreacion` date DEFAULT NULL,
  `fechaRemisaPagada` date DEFAULT NULL,
  `montoPagado` double DEFAULT NULL,
  `fk_cliente` int(11) NOT NULL,
  `puesto` varchar(45) DEFAULT NULL,
  `nombreComprador` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idPedido`),
  UNIQUE KEY `idPedido_UNIQUE` (`idPedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remisapagada`
--

LOCK TABLES `remisapagada` WRITE;
/*!40000 ALTER TABLE `remisapagada` DISABLE KEYS */;
INSERT INTO `remisapagada` VALUES (4,'2017-10-25','2017-11-15',200,48,NULL,NULL);
/*!40000 ALTER TABLE `remisapagada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefonos`
--

DROP TABLE IF EXISTS `telefonos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefonos` (
  `idTelefonos` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `fk_cliente` int(11) NOT NULL,
  PRIMARY KEY (`idTelefonos`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefonos`
--

LOCK TABLES `telefonos` WRITE;
/*!40000 ALTER TABLE `telefonos` DISABLE KEYS */;
INSERT INTO `telefonos` VALUES (1,'Local','95845641',1),(2,'Movil','5584965574',1),(3,'Local','4895621405',15),(4,'Movil','5554178595',2),(5,'Local','957126345',2),(6,'Local','4815456486',48),(7,'Movil','555478015',48);
/*!40000 ALTER TABLE `telefonos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `usuario` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Pedro','123','emoxxo');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-02  0:50:55
