drop table if exists taco_ingredients;
drop table if exists taco_order_tacos;
drop table if exists taco;
drop table if exists ingredient;
drop table if exists taco_order;
drop table if exists user;
create table if not exists Ingredient (
  id varchar(4),
  name varchar(25) not null,
  type varchar(10) not null,
  primary key (id)
);
create table if not exists Taco (
  id varchar(4),
  name varchar(50) not null,
  created_at timestamp not null,
  primary key (id)
);
create table if not exists Taco_Ingredients (
  taco_id varchar(4) not null,
  ingredients_id varchar(4) not null
);
alter table Taco_Ingredients
  add foreign key (taco_id) references Taco(id);
alter table Taco_Ingredients
  add foreign key (ingredients_id) references Ingredient(id);
create table if not exists Taco_Order (
  id varchar(4),
  name varchar(50) not null,
  street varchar(50) not null,
  city varchar(50) not null,
  state varchar(2) not null,
  zip varchar(10) not null,
  cc_number varchar(16) not null,
  cc_expiration varchar(5) not null,
  cc_cvv varchar(3) not null,
  placed_at timestamp not null,
  placed_by varchar(4) not null,
  primary key (id)
);
create table if not exists Taco_Order_Tacos (
  order_id varchar(4) not null,
  tacos_id varchar(4) not null
);
alter table Taco_Order_Tacos
  add foreign key (order_id) references Taco_Order(id);
alter table Taco_Order_Tacos
  add foreign key (tacos_id) references Taco(id);
create table if not exists User (
  id varchar(4),
  fullname varchar(10) not null,
  username varchar(20) not null,
  password varchar(255) not null,
  street varchar(50) not null,
  city varchar(50) not null,
  state varchar(2) not null,
  zip varchar(10) not null,
  phone_number varchar(20) not null,
  primary key (id)
);