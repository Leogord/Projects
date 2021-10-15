drop table if exists realm CASCADE;

create table realm
	(
	id integer generated by default as identity,
    name varchar(255),
    region varchar(255),
    primary key (id)
    );
   
   
drop table if exists user CASCADE;
   
create table user
    (
     id integer generated by default as identity,
     name varchar(255),
     primary key (id)
    );
   
   
drop table if exists toon CASCADE;
create table toon 
	(
	 id integer generated by default as identity,
	 clazz varchar(255),
	 gold integer,
	 level integer,
     name varchar(255),
     race varchar(255),
	 realm_id integer,
	 user_id integer, 
	 primary key (id));
	       
alter table toon

	add constraint FKsd3d30cg7gc19db2lncyayc3n foreign key (realm_id) references realm;

alter table toon
	add constraint FKj6af1ugro32cmldinepw1bgep foreign key (user_id) references user;