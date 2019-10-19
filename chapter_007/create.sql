create database system_items;

create table user_ (
	id serial,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	primary key(id)
);

create table category (
	name_category varchar(50),
	primary key(name_category)
);

create table state (
	name_state varchar(50),
	check (name_state in ('new', 'processing', 'completed')),
	primary key (name_state)
);

create table roles (
	name_role varchar(50),
	user_id integer,
	primary key (name_role),
	foreign key (user_id)
	references user_ (id)
);

create table rules (
	rule_name varchar(50),
	primary key(rule_name)
);

create table roles_rules(
	name_role varchar(50),
	rule_name varchar(50),
	primary key(name_role, rule_name),
	foreign key(name_role) references roles(name_role),
	foreign key(rule_name) references rules(rule_name)
);

create table item (
	item_id serial,
	user_id integer,
	name_category varchar(50),
	name_state varchar(50),
	primary key(item_id),
	foreign key(user_id) references user_(id),
	foreign key(name_category) references category(name_category),
	foreign key(name_state) references state(name_state)
);

create table comments_item (
	name_comment text,
	item_id integer,
	primary key(name_comment),
	foreign key(item_id) references item(item_id)
);

create table attached (
	name_attach varchar(50),
	item_id integer,
	primary key(name_attach),
	foreign key(item_id) references item(item_id)
);

insert into state values('new'), ('processing'), ('completed');

insert into category values ('repairs'), ('service'), ('replacement');

insert into user_ (first_name, last_name) values ('Petr', 'Petrov' ), 
('Ivan', 'Ivanov'), ('Sergei', 'Sergeev');

insert into item (user_id, name_category, name_state) values (1, 'service', 'new');




