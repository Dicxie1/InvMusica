CREATE DATABASE IF NOT EXISTS  Inventario;

USE Inventario;

CREATE TABLE Artista(
id_artista			INT  AUTO_INCREMENT	PRIMARY KEY,
nombreCompleto 		varchar(100) null,
nombreArtistico		varchar(100) null,
sexo				char(1)  not null ,
nacionalidad 		varchar(100)
)engine=innodb;

ALTER TABLE Artista
	CHANGE sexo sexo enum('M', 'F', '?') DEFAULT '?';

CREATE TABLE Discos(
id_disco 		INT AUTO_INCREMENT PRIMARY KEY,
titulo_disco	VARCHAR(225) NOT NULL,
lanzamiento		INT NOT NULL,
id_artista 		INT NOT NULL
)engine=innodb;

ALTER TABLE Discos
	ADD CONSTRAINT FK_Disco_Artista
	FOREIGN KEY(id_artista) REFERENCES Artista(id_artista);

CREATE TABLE Musica(
id_musica  int AUTO_INCREMENT PRIMARY KEY,
num_pist   tinyint not null,
titulo_musica  VARCHAR(225) NOT NULL
)engine=Innodb;

ALTER TABLE Musica
	ADD COLUMN id_disco INT NOT NULL;

ALTER TABLE Musica
	ADD CONSTRAINT FK_Musica_Disco
    FOREIGN KEY(id_disco) REFERENCES Discos(id_disco);
    
CREATE TABLE TipoMusica(
id_tipoMusica	int AUTO_INCREMENT PRIMARY KEY,
titulo 			varchar(225) not null
)engine=innodb;



CREATE TABLE Genero(
id_genero	int AUTO_INCREMENT PRIMARY KEY,
titulo 			varchar(225) not null
)engine=innodb;

DROP TABLE TipoMusica;

CREATE TABLE GeneroMusica(
id_musica    int not null,
id_genero    int not null,
CONSTRAINT FK_GeneroMusica_Musica
FOREIGN KEY(id_musica) REFERENCES Musica(id_musica),
CONSTRAINT FK_GeneroMusica_Genero
FOREIGN KEY(id_genero) REFERENCES Genero(id_genero)
);


ALTER TABLE GeneroMusica
	ADD PRIMARY KEY(id_musica, id_genero);

CREATE TABLE GeneroArtista(
id_artista int not null,
id_genero int not null
);

ALTER TABLE GeneroArtista
	ADD PRIMARY KEY(id_artista, id_genero);

ALTER TABLE GeneroArtista
	ADD CONSTRAINT FK_GeneroArtista_Artista
    FOREIGN KEY(id_artista) REFERENCES Artista(id_artista);
    
ALTER TABLE GeneroArtista
	ADD CONSTRAINT FK_GeneroArtista_Genero
    FOREIGN KEY(id_genero) REFERENCES Genero(id_genero);

CREATE TABLE ArtistaMusica(
id_Artista int not null,
id_Musica  int not null,
CONSTRAINT FK_ArtistaMusica_Artista
FOREIGN KEY(id_artista) REFERENCES Artista(id_artista),
CONSTRAINT FK_ArtistaMusica
FOREIGN KEY(id_musica) REFERENCES Musica(id_musica)
);

ALTER TABLE ArtistaMusica
	ADD PRIMARY KEY(id_artista, id_Musica);
