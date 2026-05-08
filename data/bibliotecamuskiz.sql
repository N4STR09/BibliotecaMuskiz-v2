-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: bibliotecamuskiz
-- ------------------------------------------------------
-- Server version	5.7.44-log

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
-- Table structure for table `autores`
--

DROP TABLE IF EXISTS `autores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autores` (
  `id_autor` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) NOT NULL,
  `nacionalidad` varchar(100) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `defuncion` tinyint(1) DEFAULT '0',
  `fecha_fallecimiento` date DEFAULT NULL,
  `biografia` text,
  `foto` varchar(255) DEFAULT NULL,
  `genero_literario` varchar(100) DEFAULT NULL,
  `premios` text,
  `obras_destacadas` text,
  PRIMARY KEY (`id_autor`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autores`
--

LOCK TABLES `autores` WRITE;
/*!40000 ALTER TABLE `autores` DISABLE KEYS */;
INSERT INTO `autores` VALUES (1,'Miguel de Cervantes','Española','1547-09-29',1,'1616-04-22','Escritor del Siglo de Oro','cervantes.jpg','Novela','Ninguno','Don Quijote'),(2,'Gabriel García Márquez','Colombiana','1927-03-06',1,'2014-04-17','Autor del realismo mágico','gabo.jpg','Novela','Nobel','Cien años de soledad'),(3,'J.K. Rowling','Británica','1965-07-31',0,NULL,'Autora de Harry Potter','rowling.jpg','Fantasía','Ninguno','Harry Potter'),(4,'Isabel Allende','Chilena','1942-08-02',0,NULL,'Autora de La casa de los espíritus','allende.jpg','Novela','Ninguno','La casa de los espíritus'),(5,'Mario Vargas Llosa','Peruana','1936-03-28',0,NULL,'Escritor y político','vargas.jpg','Novela','Nobel','La ciudad y los perros');
/*!40000 ALTER TABLE `autores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ejemplares`
--

DROP TABLE IF EXISTS `ejemplares`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ejemplares` (
  `cod_ejemplar` int(11) NOT NULL AUTO_INCREMENT,
  `estado` varchar(50) NOT NULL,
  `id_libro` int(11) NOT NULL,
  PRIMARY KEY (`cod_ejemplar`),
  KEY `fk_ejemplares_libros` (`id_libro`),
  CONSTRAINT `fk_ejemplares_libros` FOREIGN KEY (`id_libro`) REFERENCES `libros` (`id_libro`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ejemplares`
--

LOCK TABLES `ejemplares` WRITE;
/*!40000 ALTER TABLE `ejemplares` DISABLE KEYS */;
INSERT INTO `ejemplares` VALUES (21,'DISPONIBLE',1),(22,'DISPONIBLE',1),(23,'DISPONIBLE',2),(24,'DISPONIBLE',3),(25,'DISPONIBLE',3),(26,'DISPONIBLE',3),(27,'DISPONIBLE',3),(28,'DISPONIBLE',4),(29,'DISPONIBLE',4),(30,'DISPONIBLE',4),(31,'DISPONIBLE',5),(32,'DISPONIBLE',5),(33,'DISPONIBLE',6),(34,'DISPONIBLE',7),(35,'DISPONIBLE',7),(36,'DISPONIBLE',8),(37,'DISPONIBLE',8),(38,'DISPONIBLE',8),(39,'DISPONIBLE',9),(40,'DISPONIBLE',9),(41,'DISPONIBLE',9),(42,'DISPONIBLE',9),(43,'DISPONIBLE',9),(44,'DISPONIBLE',10),(45,'DISPONIBLE',10),(46,'DISPONIBLE',10),(47,'DISPONIBLE',10),(48,'DISPONIBLE',11),(49,'DISPONIBLE',11),(50,'DISPONIBLE',11),(51,'DISPONIBLE',12),(52,'DISPONIBLE',12),(53,'DISPONIBLE',13),(54,'DISPONIBLE',13),(55,'DISPONIBLE',14),(56,'DISPONIBLE',15),(57,'DISPONIBLE',15),(58,'DISPONIBLE',16),(59,'DISPONIBLE',16),(60,'DISPONIBLE',16),(61,'DISPONIBLE',17),(62,'DISPONIBLE',17),(63,'DISPONIBLE',18),(64,'DISPONIBLE',19),(65,'DISPONIBLE',19),(66,'DISPONIBLE',20);
/*!40000 ALTER TABLE `ejemplares` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libros`
--

DROP TABLE IF EXISTS `libros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libros` (
  `id_libro` int(11) NOT NULL AUTO_INCREMENT,
  `numero_paginas` int(11) DEFAULT NULL,
  `titulo` varchar(255) NOT NULL,
  `id_autor` int(11) NOT NULL,
  `genero` varchar(100) DEFAULT NULL,
  `anio_publicacion` int(11) DEFAULT NULL,
  `editorial` varchar(150) DEFAULT NULL,
  `isbn` varchar(50) DEFAULT NULL,
  `idioma` varchar(50) DEFAULT NULL,
  `formato` varchar(50) DEFAULT NULL,
  `portada` varchar(255) DEFAULT NULL,
  `descripcion` text,
  `categoria` varchar(100) DEFAULT NULL,
  `disponibilidad` tinyint(1) DEFAULT '1',
  `numeroPaginas` int(11) NOT NULL DEFAULT '0',
  `existencias` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_libro`),
  KEY `fk_libro_autor` (`id_autor`),
  CONSTRAINT `fk_libro_autor` FOREIGN KEY (`id_autor`) REFERENCES `autores` (`id_autor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libros`
--

LOCK TABLES `libros` WRITE;
/*!40000 ALTER TABLE `libros` DISABLE KEYS */;
INSERT INTO `libros` VALUES (1,863,'Don Quijote de la Mancha',1,'Novela',1605,'Francisco de Robles',NULL,'Español','Físico',NULL,NULL,'Literatura',1,863,2),(2,350,'Novelas ejemplares',1,'Novela',1613,'Juan de la Cuesta',NULL,'Español','Físico',NULL,NULL,'Literatura',1,350,1),(3,471,'La Galatea',1,'Novela',1585,'Blas de Robles',NULL,'Español','Físico',NULL,NULL,'Literatura',1,471,4),(4,417,'Cien años de soledad',2,'Novela',1967,'Sudamericana',NULL,'Español','Físico',NULL,NULL,'Literatura',1,417,3),(5,120,'Crónica de una muerte anunciada',2,'Novela',1981,'Oveja Negra',NULL,'Español','Físico',NULL,NULL,'Literatura',1,120,2),(6,368,'El amor en los tiempos del cólera',2,'Novela',1985,'Oveja Negra',NULL,'Español','Físico',NULL,NULL,'Literatura',1,368,1),(7,192,'El coronel no tiene quien le escriba',2,'Novela',1961,'Sudamericana',NULL,'Español','Físico',NULL,NULL,'Literatura',1,192,2),(8,450,'Harry Potter y el Cáliz de Fuego',3,'Fantasía',2002,'Bloomsbury',NULL,'Español','Físico',NULL,NULL,'Literatura',1,450,3),(9,223,'Harry Potter y la piedra filosofal',3,'Fantasía',1997,'Bloomsbury',NULL,'Español','Físico',NULL,NULL,'Literatura',1,223,5),(10,251,'Harry Potter y la cámara secreta',3,'Fantasía',1998,'Bloomsbury',NULL,'Español','Físico',NULL,NULL,'Literatura',1,251,4),(11,317,'Harry Potter y el prisionero de Azkaban',3,'Fantasía',1999,'Bloomsbury',NULL,'Español','Físico',NULL,NULL,'Literatura',1,317,3),(12,636,'Harry Potter y la Orden del Fénix',3,'Fantasía',2003,'Bloomsbury',NULL,'Español','Físico',NULL,NULL,'Literatura',1,636,2),(13,432,'La casa de los espíritus',4,'Novela',1982,'Plaza & Janés',NULL,'Español','Físico',NULL,NULL,'Literatura',1,432,2),(14,368,'Eva Luna',4,'Novela',1987,'Plaza & Janés',NULL,'Español','Físico',NULL,NULL,'Literatura',1,368,1),(15,416,'Paula',4,'Memorias',1994,'Plaza & Janés',NULL,'Español','Físico',NULL,NULL,'Literatura',1,416,2),(16,384,'Hija de la fortuna',4,'Novela',1999,'Plaza & Janés',NULL,'Español','Físico',NULL,NULL,'Literatura',1,384,3),(17,432,'La ciudad y los perros',5,'Novela',1963,'Seix Barral',NULL,'Español','Físico',NULL,NULL,'Literatura',1,432,2),(18,376,'Conversación en La Catedral',5,'Novela',1969,'Seix Barral',NULL,'Español','Físico',NULL,NULL,'Literatura',1,376,1),(19,304,'Pantaleón y las visitadoras',5,'Novela',1973,'Seix Barral',NULL,'Español','Físico',NULL,NULL,'Literatura',1,304,2),(20,288,'La fiesta del chivo',5,'Novela',2000,'Alfaguara',NULL,'Español','Físico',NULL,NULL,'Literatura',1,288,1);
/*!40000 ALTER TABLE `libros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `penalizaciones`
--

DROP TABLE IF EXISTS `penalizaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `penalizaciones` (
  `cod_penalizacion` int(11) NOT NULL AUTO_INCREMENT,
  `motivo` varchar(255) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date DEFAULT NULL,
  `activa` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`cod_penalizacion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `penalizaciones`
--

LOCK TABLES `penalizaciones` WRITE;
/*!40000 ALTER TABLE `penalizaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `penalizaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamos`
--

DROP TABLE IF EXISTS `prestamos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamos` (
  `cod_prestamo` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_prestamo` date NOT NULL,
  `fecha_devolucion` date DEFAULT NULL,
  `cod_ejemplar` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`cod_prestamo`),
  KEY `fk_prestamos_ejemplares` (`cod_ejemplar`),
  KEY `fk_prestamos_usuarios` (`id_usuario`),
  CONSTRAINT `fk_prestamos_ejemplares` FOREIGN KEY (`cod_ejemplar`) REFERENCES `ejemplares` (`cod_ejemplar`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_prestamos_usuarios` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamos`
--

LOCK TABLES `prestamos` WRITE;
/*!40000 ALTER TABLE `prestamos` DISABLE KEYS */;
INSERT INTO `prestamos` VALUES (1,'2026-05-08','2026-05-08',21,3),(2,'2026-05-08','2026-05-08',21,3);
/*!40000 ALTER TABLE `prestamos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `defuncion` tinyint(1) NOT NULL DEFAULT '0',
  `fecha_fallecimiento` date DEFAULT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  `dni` varchar(20) NOT NULL,
  `numero_seguridad_social` int(11) NOT NULL,
  `password` varchar(255) NOT NULL,
  `cod_penalizacion` int(11) DEFAULT NULL,
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `dni` (`dni`),
  KEY `fk_usuarios_penalizaciones` (`cod_penalizacion`),
  CONSTRAINT `fk_usuarios_penalizaciones` FOREIGN KEY (`cod_penalizacion`) REFERENCES `penalizaciones` (`cod_penalizacion`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Administrador','2000-01-01',0,NULL,1,'admin',123456789,'admin',NULL,1),(3,'paco','2000-01-01',0,NULL,1,'1234',0,'1234',NULL,0);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-08 23:40:31
