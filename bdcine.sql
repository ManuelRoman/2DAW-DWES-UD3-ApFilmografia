DROP DATABASE IF EXISTS cine;
CREATE DATABASE cine;
USE cine;
CREATE TABLE peliculas (
  director VARCHAR(128) NOT NULL,
  titulo VARCHAR(128) NOT NULL,
  fecha DATETIME NOT NULL
);

INSERT INTO peliculas (director,titulo,fecha) VALUES
('ridley scott','Alien: el octavo pasajero','1979/01/01'),
('ridley scott','Blade Runner','1982/01/01'),
('stanley kubrick','2001: Una odisea del espacio','1968/01/01');
