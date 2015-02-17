drop database if exists CabinetronDB;
create database CabinetronDB;
use CabinetronDB;

drop table if exists parts;
create table parts (
        partId       	int primary key auto_increment,
        partName		varchar(255) NOT NULL,
		partNumber		varchar(20) UNIQUE NOT NULL,
		externalNumber	varchar(50),
		vendorName		varchar(255),
		unit			ENUM('Linear Feet', 'Pieces') NOT NULL
);

create table inventoryItems (
        itemId       	int primary key auto_increment,
        partId			int,
		quantity		varchar(50),
		Location		ENUM('Facility 1 Warehouse 1', 'Facility 1 Warehouse 2', 'Facility 2') NOT NULL,
		FOREIGN KEY (partId) REFERENCES parts(partId)
);


/*drop table if exists operatingSystems;
create table operatingSystems (
        userId       	 int primary key,
        operatingSystem1 varchar(40),
		operatingSystem2 varchar(40),
		operatingSystem3 varchar(40),
		operatingSystem4 varchar(40)
);

drop table if exists comments;
create table comments (
        commentId      	 int primary key auto_increment,
        userName		 varchar(40) COLLATE utf8_unicode_ci NOT NULL,
		information		 varchar(9001) NOT NULL,
		problemId		 int NOT NULL,
		solution		 varchar(1) DEFAULT '0',
		dateCreated  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

drop table if exists problems;
create table problems (
        problemId      	 int primary key auto_increment,
        userName		 varchar(40) COLLATE utf8_unicode_ci NOT NULL,
        title			 varchar(80) NOT NULL,
		information		 varchar(9001) NOT NULL,
		category	     ENUM('Advice','Problem','General Discussion'),
		dateCreated  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

insert into users (userName, password, email)
        values ('Chuck','banana', 'chuck@nerdherd.com');
insert into users (userName, password, email)
        values ('Jeff', 'potato', 'jeff@nerdherd.com');
insert into users (userName, password, email)
        values ('Skip', 'butter', 'skip@nerdherd.com');
        
insert into operatingSystems (userId, operatingSystem1)
        values ('1','Windows');
insert into operatingSystems (userId, operatingSystem1, operatingSystem2)
        values ('2', 'Windows', 'Mac');
insert into operatingSystems (userId, operatingSystem1, operatingSystem2, operatingSystem3, operatingSystem4)
        values ('3', 'Windows', 'Mac', 'Linux', 'Other');
*/