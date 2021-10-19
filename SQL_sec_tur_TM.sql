--Dropeamos todas las tablas en ese orden para que no les de ansiedad
DROP TABLE IF EXISTS "itinerarios";
DROP TABLE IF EXISTS "promocion_atracciones";
DROP TABLE IF EXISTS "promociones";
DROP TABLE IF EXISTS "tipo_promociones";
DROP TABLE IF EXISTS "atracciones";
DROP TABLE IF EXISTS "usuarios";
DROP TABLE IF EXISTS "tipos";

--Tabla tipos
CREATE TABLE "tipos" ( 
	"nombre" TEXT NOT NULL, 
	PRIMARY KEY("nombre") 
);

--Tabla usuarios
CREATE TABLE "usuarios" ( 
	"nombre" TEXT NOT NULL, 
	"monedas" NUMERIC DEFAULT 0, 
	"tiempo" NUMERIC DEFAULT 0, 
	"tipo_preferido" TEXT, 
	FOREIGN KEY("tipo_preferido") REFERENCES "tipos"("nombre"), 
	PRIMARY KEY("nombre") 
);

--Tabla atracciones
CREATE TABLE "atracciones" (
	"nombre"	TEXT,
	"tiempo"	NUMERIC NOT NULL,
	"costo"	NUMERIC,
	"cupo"	INTEGER NOT NULL,
	"tipo"	TEXT NOT NULL,
	FOREIGN KEY("tipo") REFERENCES "tipos"("nombre"),
	PRIMARY KEY("nombre")
);

-- Tabla tipo_promociones
CREATE TABLE "tipo_promociones" ( 
	"nombre" TEXT, 
	PRIMARY KEY("nombre") 
);

-- Tabla promociones
CREATE TABLE "promociones" (
	"nombre"	TEXT,
	"tipo_promocion"	TEXT,
	"tipo_atracciones"	TEXT,
	"wildcard"	TEXT NOT NULL,
	FOREIGN KEY("tipo_promocion") REFERENCES "tipo_promociones"("nombre"),
	FOREIGN KEY("tipo_atracciones") REFERENCES "tipos"("nombre"),
	PRIMARY KEY("nombre")
);

--Tabla promocion_atracciones
CREATE TABLE "promocion_atracciones" ( 
	"id_promocion" TEXT, 
	"id_atraccion" TEXT, 
	FOREIGN KEY("id_atraccion") REFERENCES "atracciones"("nombre"), 
	FOREIGN KEY("id_promocion") REFERENCES "promociones"("nombre"), 
	PRIMARY KEY("id_promocion","id_atraccion") 
);

--Tabla itinerarios
CREATE TABLE "itinerarios" (
	"id_usuario"	TEXT,
	"id_atraccion"	TEXT,
	"id_promocion"	TEXT,
	PRIMARY KEY("id_usuario","id_atraccion"),
	FOREIGN KEY("id_promocion") REFERENCES "promociones"("nombre"),
	FOREIGN KEY("id_atraccion") REFERENCES "atracciones"("nombre"),
	FOREIGN KEY("id_usuario") REFERENCES "usuarios"("nombre")
);

-- Insercion de datos
INSERT INTO tipos ("nombre")
VALUES ("AVENTURA"), 
("PAISAJE"),
("DEGUSTACION");

INSERT INTO usuarios ("nombre", "monedas", "tiempo", "tipo_preferido")
VALUES 
('Eowyn',10,8,'AVENTURA'),
('Gandalf',100,5,'PAISAJE'),
('Sam',36,8,'DEGUSTACION'),
('Galadriel',120,6,'PAISAJE'),
('Radagast',20,1,'AVENTURA'),
('Bilbo',200,10,'PAISAJE'),
('Smeagol',50,4,'AVENTURA');

INSERT INTO atracciones ("nombre", "costo", "tiempo", "cupo", "tipo")
VALUES 
('Moria',10,2,6,'AVENTURA'),
('Minas Tirith',5,2.5,25,'PAISAJE'),
('La Comarca',3,6.5,150,'DEGUSTACION'),
('Mordor',25,3,4,'AVENTURA'),
('Abismo de Helm',5,2,15,'PAISAJE'),
('Lothlórien',35,1,30,'DEGUSTACION'),
('Erebor',12,3,32,'PAISAJE'),
('Bosque Negro',3,4,12,'AVENTURA');

INSERT INTO tipo_promociones ("nombre")
VALUES ("PORCENTUAL"),
("ABSOLUTA"),
("AxB");

INSERT INTO promociones ("tipo_promocion", "nombre", "wildcard", "tipo_atracciones")
VALUES
('PORCENTUAL', 'Pack Aventura', '20', 'AVENTURA'),
('ABSOLUTA', 'Pack Sabores', '36', 'DEGUSTACION'),
('AxB', 'Pack Paisajes', 'Erebor', 'PAISAJE');

INSERT INTO promocion_atracciones ("id_promocion", "id_atraccion")
VALUES
("Pack Aventura","Moria"),
("Pack Aventura","Bosque Negro"),
("Pack Sabores","Lothlórien"),
("Pack Sabores","La Comarca"),
("Pack Paisajes","Minas Tirith"),
("Pack Paisajes","Abismo de Helm");

INSERT INTO "itinerarios" ("id_usuario","id_atraccion","id_promocion") VALUES ('Eowyn','Mordor',NULL),
 ('Smeagol','Moria','Pack Aventura'),
 ('Smeagol','Bosque Negro','Pack Aventura'),
 ('Radagast','La Comarca',NULL),
 ('Radagast','Moria',NULL),
 ('Radagast','Bosque Negro',NULL),
 ('Gandalf','Minas Tirith','Pack Paisajes'),
 ('Gandalf','Abismo de Helm','Pack Paisajes'),
 ('Gandalf','Erebor','Pack Paisajes');
