create database vehiculo;
use vehiculo;
CREATE TABLE `anioD` (
  `ANIO` char(4) NOT NULL,
  PRIMARY KEY (`ANIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
LOCK TABLES `anioD` WRITE;
INSERT INTO `anioD` VALUES ('2005'),('2006'),('2017'),('2010'),('2014'),('2022'),('2019'),('2020'),('2021');
UNLOCK TABLES;
CREATE TABLE `datosVehiculo` (matricula varchar(10) not null ,
 tipo varchar(10) not null,
 propietario varchar(10) not null,
 modelo varchar(10) not null,
 anio char(4) not null,
 color varchar(100) not null,
 PRIMARY KEY (`matricula`)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `datosVehiculo` WRITE;
INSERT INTO `datosVehiculo` VALUES ('1234567','Mecanico','Maria', 'Tesla','2005','Verde');
UNLOCK TABLES;


CREATE TABLE `colores` (
  `Collor` varchar(10) NOT NULL,
  PRIMARY KEY (`Collor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `colores` WRITE;
INSERT INTO `colores` VALUES ('Azul'),('Amarrillo'),('Negro'),('Rosa'),('Gris'),('Blanco');
UNLOCK TABLES;

select * from colores;
select * from datosVehiculo;
