drop database if exists CabinetronDB;
create database CabinetronDB;
use CabinetronDB;

drop table if exists parts;
create table users (
        partId       	 int primary key auto_increment,
        partName			 varchar(255) UNIQUE COLLATE utf8_unicode_ci NOT NULL,
		partNumber			 varchar(255) COLLATE utf8_unicode_ci NOT NULL,
		externalNumber	 varchar(255) NOT NULL,
		vendorName			 varchar(40) UNIQUE NOT NULL,
		quantity		 varchar(40),
		location		 ENUM('Male','Female'),
		unit			 ENUM('Unknown, Pieces')
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