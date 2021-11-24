
CREATE TABLE heroes (
	name varchar(200) UNIQUE,
	power varchar(200),
	universe varchar(200),
	villain_enemy varchar(200)
);

INSERT INTO public.heroes (name, power, universe, villain_enemy) VALUES('anakin skywalker','sable de luz', 'star-wars', 'conde dooku');
INSERT INTO public.heroes (name, power, universe, villain_enemy) VALUES('obi wan kenobi','sable de luz', 'star-wars', 'darth vader');
INSERT INTO public.heroes (name, power, universe, villain_enemy) VALUES('R2D2', 'arreglar cualquier wea', 'star-wars', 'electric shock');
INSERT INTO public.heroes (name, power, universe, villain_enemy) VALUES('pagasus seiya', 'meteroro pegaso', 'saint-seiya', 'casios');
INSERT INTO public.heroes (name, power, universe, villain_enemy) VALUES('dragon shiryu', 'dragon naciente', 'saint-seiya', 'cancer death mask');
INSERT INTO public.heroes (name, power, universe, villain_enemy) VALUES('Cisne hyoga', 'polvo de diamantes', 'saint-seiya', 'Acuario kamus');

CREATE TABLE villains (
	name varchar(200) UNIQUE,
	power varchar(200),
	universe varchar(200),
	hero_enemy varchar(200)
);

INSERT INTO public.villains (name, power, universe, hero_enemy) VALUES('Darth Vader', 'Lado oscuro de la fuerza', 'star-wars', 'Obi one kenobi');
INSERT INTO public.villains (name, power, universe, hero_enemy) VALUES('Magneto', 'magnetismo', 'x-men', 'Profesor X');
INSERT INTO public.villains (name, power, universe, hero_enemy) VALUES('lord farcuak', 'robar pantanos', 'shrek', 'shrek');
INSERT INTO public.villains (name, power, universe, hero_enemy) VALUES('rupelstinky', 'hacer tratos magicos', 'shrek', 'ogros');
