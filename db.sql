--DROP TABLE heroes
CREATE TABLE heroes (
	name varchar(200),
	power varchar(200),
	universe varchar(200),
	villain_enemy varchar(200)
);
--DROP TABLE villain;
CREATE TABLE villains (
	name varchar(200),
	power varchar(200),
	universe varchar(200),
	hero_enemy varchar(200)
);