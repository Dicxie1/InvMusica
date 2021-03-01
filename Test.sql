USE Inventario;
TRUNCATE Artista;
TRUNCATE Discos;
TRUNCATE Musica;
INSERT Artista(nombreCompleto, nombreArtistico, sexo, nacionalidad) values('Hilsong', 'Hilsong Worship', '?', 'Sídney');
INSERT Artista(nombreCompleto, nombreArtistico, sexo, nacionalidad) values('Marcos Barrientos', null, 'M', 'Mexico');
INSERT Artista(nombreCompleto, nombreArtistico, sexo, nacionalidad) values('Marcos Burnet', null, 'M', 'Brasil');
INSERT Artista(nombreCompleto, nombreArtistico, sexo, nacionalidad) values('Michael W Smith', null, 'M', 'EE.UU');
INSERT Artista(nombreCompleto, nombreArtistico, sexo, nacionalidad) values('Kari Jobe', null, 'F', 'EE.UU');
INSERT Artista(nombreCompleto, nombreArtistico, sexo, nacionalidad) values('Katheryn Elizabet Houdson', 'Katy Perr','F', 'EE.UU');
INSERT Artista(nombreCompleto, nombreArtistico, sexo, nacionalidad) values('Robyn Rihanna Fenty', 'Rihanna', 'F', 'EE.UU');


USE Inventario;
INSERT Discos(titulo_disco, lanzamiento, id_artista) value('Music Of The Sun', 2005, 7);
INSERT Discos(titulo_disco, lanzamiento, id_artista) value('A Girl Like Me', 2006, 7);
INSERT Discos(titulo_disco, lanzamiento, id_artista) value('Good Girl Gone Bad', 2007, 7);
INSERT Discos(titulo_disco, lanzamiento, id_artista) value('Rated R', 2009, 7);
INSERT Discos(titulo_disco, lanzamiento, id_artista) value('Loud', 2010, 7);
INSERT Discos(titulo_disco, lanzamiento, id_artista) value('Talk That Talk', 2011,7);
INSERT Discos(titulo_disco, lanzamiento, id_artista) value('Unapologetic', 2012,7);
INSERT Discos(titulo_disco, lanzamiento, id_artista) value('Anti',2016, 7);
INSERT Discos(titulo_disco, lanzamiento, id_artista) value('One of the Boy',2008,6);
INSERT Discos(titulo_disco, lanzamiento, id_artista) value('Teenage Dream',2010,6);
INSERT Discos(titulo_disco, lanzamiento, id_artista) value('Prism', 2013, 6);
INSERT Discos(titulo_disco, lanzamiento, id_artista) value('Witness', 2017, 6);
INSERT Discos(titulo_disco, lanzamiento, id_artista) value('Smile', 2020,6);
INSERT Discos(titulo_disco, lanzamiento, id_artista) value('I\'M Singing', 2008, 5);
INSERT Discos(titulo_disco, lanzamiento, id_artista) value('Pure', 2009, 5);
INSERT Discos(titulo_disco, lanzamiento, id_artista) value('You Are for Me', 2010, 5);
INSERT Discos(titulo_disco, lanzamiento, id_artista) value('What Love is This', 2012, 5);
INSERT Discos(titulo_disco, lanzamiento, id_artista) value('Worshio Again', 2004, 4);
INSERT Discos(titulo_disco, lanzamiento, id_artista) value('Dialogo Íntimo', 2011, 3);
INSERT Discos(titulo_disco, lanzamiento, id_artista) value('Aguas profundas', 2007, 3);
INSERT Discos(titulo_disco, lanzamiento, id_artista) value('Toma tu lugar', 2009,3);
INSERT Discos(titulo_disco, lanzamiento, id_artista) value('Uniendo Cielos y Tierra', 2010,3);
INSERT Discos(titulo_disco, lanzamiento, id_artista) value('Spirit and Truth', 1988, 1);
INSERT Discos(titulo_disco, lanzamiento, id_artista) value('Mighty to Save', 2006, 1);
#Insertar Registro de la Tabla Musica
USE Inventario;
INSERT Musica(num_pist, titulo_musica, id_disco) value(1, 'Pon de Repley', 1);
INSERT Musica(num_pist, titulo_musica, id_disco) value(2, 'Here I Go Again', 1);
INSERT Musica(num_pist, titulo_musica, id_disco) value(3, 'If it\'s Lovin That You Want', 1);
INSERT Musica(num_pist, titulo_musica, id_disco) value(1, 'SOS', 2);
INSERT Musica(num_pist, titulo_musica, id_disco) value(2, 'Kisses Don\'t lie', 2);
INSERT Musica(num_pist, titulo_musica, id_disco) value(3, 'Unfaithful', 2);
INSERT Musica(num_pist, titulo_musica, id_disco) value(1, 'Take a Bow',3);
INSERT Musica(num_pist, titulo_musica, id_disco) value(2, 'Take A Bow - Main',3);
INSERT Musica(num_pist, titulo_musica, id_disco) value(1, 'S&M', 5);
INSERT Musica(num_pist, titulo_musica, id_disco) value(2, 'What\'s My Name', 5);
INSERT Musica(num_pist, titulo_musica, id_disco) value(3, 'Cheers(Drik to That)', 5);
INSERT Musica(num_pist, titulo_musica, id_disco) value(1, 'One of the boys', 9);
INSERT Musica(num_pist, titulo_musica, id_disco) value(2, 'I Kissed a Girl',9);
INSERT Musica(num_pist, titulo_musica, id_disco) value(3, 'Waking Un In Vegas',9 );

#Registro de la tabla Genero
use inventario;
INSERT INTO Genero(titulo) value('Bachata');
INSERT INTO Genero(titulo) value('Baladas');
INSERT INTO Genero(titulo) value('Blues');
INSERT INTO Genero(titulo) value('Bolero');
INSERT INTO Genero(titulo) value('Bosa Nova');
INSERT INTO Genero(titulo) value('Celta');
INSERT INTO Genero(titulo) value('Clasica');
INSERT INTO Genero(titulo) value('Corrido');
INSERT INTO Genero(titulo) value('Country');
INSERT INTO Genero(titulo) value('Criollo');
INSERT INTO Genero(titulo) value('Dubstep'); 
INSERT INTO Genero(titulo) value('Elentrónica');
INSERT INTO Genero(titulo) value('Gospel');
INSERT INTO Genero(titulo) value('Hip Hop');
INSERT INTO Genero(titulo) value('Pop');
INSERT INTO Genero(titulo) value('Reggae');

#registro de GeneroArtista
USE Inventario;
INSERT INTO GeneroArtista VALUE(1, 13);
INSERT INTO GeneroArtista VALUE(1, 12);
INSERT INTO GeneroArtista VALUE(2, 13 );
INSERT INTO GeneroArtista VALUE(3,13);
INSERT INTO GeneroArtista VALUE(4,13);
INSERT INTO GeneroArtista VALUE(5, 13);
INSERT INTO GeneroArtista VALUE(6,15);
INSERT INTO GeneroArtista VALUE(7,15);
INSERT INTO GeneroArtista VALUE(7,14);
USE Inventario;
INSERT INTO GeneroMusica()


INSERT INTO GeneroMusica VALUE(1, 14);
INSERT INTO GeneroMusica VALUE (2, 14);
INSERT INTO GeneroMusica VALUE(3, 14);
INSERT INTO GeneroMusica VALUE(4, 15);
INSERT INTO GeneroMusica VALUE(5, 15);
INSERT INTO GeneroMusica VALUE(6, 15);
INSERT INTO GeneroMusica VALUE(8, 14);
INSERT INTO GeneroMusica VALUE(9, 14);
INSERT INTO GeneroMusica VALUE(10, 14);
INSERT INTO GeneroMusica VALUE(11, 13);
INSERT INTO GeneroMusica VALUE(12, 13);
INSERT INTO GeneroMusica VALUE(13, 14);
INSERT INTO GeneroMusica VALUE(14, 15);
SELECT * FROM Artista;


#SELECT 'DS' + char_length(Discos) AS lengh, Discos FROM (
#	SELECT COUNT(id_disco) AS Discos FROM Discos
#) AS Dis;

SELECT * FROM Discos;
SELECT * FROM Musica;
SELECT * FROM Genero;
SELECT * FROM GeneroMusica;
