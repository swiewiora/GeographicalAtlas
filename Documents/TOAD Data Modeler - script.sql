/*
Created		2016-05-30
Modified	2016-05-30
Project		Atlas geograficzny
Tool			TOAD Data Modeler
Database	mySQL 5 
*/


drop table IF EXISTS Jezyki_Panstwa;
drop table IF EXISTS Oblewanie;
drop table IF EXISTS Panstwo_Na_Kontynencie;
drop table IF EXISTS Kontynent;
drop table IF EXISTS Morze;
drop table IF EXISTS Jezyk;
drop table IF EXISTS Panstwo;
drop table IF EXISTS Miasto;


Create table Miasto (
	Id_Miasta Int NOT NULL,
	Nazwa_Miasta Char(50),
	Liczba_Mieszkancow Int,
	Czy_Stolica Bool,
	Id_Panstwa Int NOT NULL,
 Primary Key (Id_Miasta)) ENGINE = MyISAM;

Create table Panstwo (
	Id_Panstwa Int NOT NULL,
	Nazwa_Panstwa Char(50),
	Powierzchnia_Panstwa Double,
	Liczba_Ludnosci Int,
	Jednostka_Monetarna Char(50),
	Ustroj Char(50),
	Informacje_Dodatkowe Char(255),
 Primary Key (Id_Panstwa)) ENGINE = MyISAM;

Create table Jezyk (
	Id_Jezyka Int NOT NULL,
	Jezyk Char(50),
 Primary Key (Id_Jezyka)) ENGINE = MyISAM;

Create table Morze (
	Id_Morza Int NOT NULL,
	Nazwa_Morza Char(50),
	Powierzchnia_Morza Double,
 Primary Key (Id_Morza)) ENGINE = MyISAM;

Create table Kontynent (
	Id_Kontynentu Int NOT NULL,
	Nazwa_Kontynentu Char(50),
	Powierzchnia_Bez_Wysp Double,
	Powierzchnia_Z_Wyspami Double,
 Primary Key (Id_Kontynentu)) ENGINE = MyISAM;

Create table Panstwo_Na_Kontynencie (
	Id_Panstwa Int NOT NULL,
	Id_Kontynentu Int NOT NULL,
 Primary Key (Id_Panstwa,Id_Kontynentu)) ENGINE = MyISAM;

Create table Oblewanie (
	Id_Panstwa Int NOT NULL,
	Id_Morza Int NOT NULL,
	Dlugosc_Granicy Double,
 Primary Key (Id_Panstwa,Id_Morza)) ENGINE = MyISAM;

Create table Jezyki_Panstwa (
	Id_Panstwa Int NOT NULL,
	Id_Jezyka Int NOT NULL,
 Primary Key (Id_Panstwa,Id_Jezyka)) ENGINE = MyISAM;


Alter table Miasto add Foreign Key (Id_Panstwa) references Panstwo (Id_Panstwa) on delete  restrict on update  restrict;
Alter table Panstwo_Na_Kontynencie add Foreign Key (Id_Panstwa) references Panstwo (Id_Panstwa) on delete  restrict on update  restrict;
Alter table Oblewanie add Foreign Key (Id_Panstwa) references Panstwo (Id_Panstwa) on delete  restrict on update  restrict;
Alter table Jezyki_Panstwa add Foreign Key (Id_Panstwa) references Panstwo (Id_Panstwa) on delete  restrict on update  restrict;
Alter table Jezyki_Panstwa add Foreign Key (Id_Jezyka) references Jezyk (Id_Jezyka) on delete  restrict on update  restrict;
Alter table Oblewanie add Foreign Key (Id_Morza) references Morze (Id_Morza) on delete  restrict on update  restrict;
Alter table Panstwo_Na_Kontynencie add Foreign Key (Id_Kontynentu) references Kontynent (Id_Kontynentu) on delete  restrict on update  restrict;


/* Users permissions */


