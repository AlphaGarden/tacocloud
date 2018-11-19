drop table if exists taco_ingredients;
drop table if exists taco_order_tacos;
drop table if exists taco;
drop table if exists Ingredient;
drop table if exists taco_order;
create table if not exists Ingredient (
  id varchar(4),
  name varchar(25) not null,
  type varchar(10) not null,
  primary key (id)
);
create table if not exists Taco (
  id varchar(4),
  name varchar(50) not null,
  createdAt timestamp not null,
  primary key (id)
);
create table if not exists Taco_Ingredients (
  taco_id varchar(4) not null,
  ingredient_id varchar(4) not null
);
alter table Taco_Ingredients
  add foreign key (taco_id) references Taco(id);
alter table Taco_Ingredients
  add foreign key (ingredient_id) references Ingredient(id);
create table if not exists Taco_Order (
  id varchar(4),
  deliveryName varchar(50) not null,
  deliveryStreet varchar(50) not null,
  deliveryCity varchar(50) not null,
  deliveryState varchar(2) not null,
  deliveryZip varchar(10) not null,
  ccNumber varchar(16) not null,
  ccExpiration varchar(5) not null,
  ccCVV varchar(3) not null,
  placedAt timestamp not null,
  primary key (id)
);
create table if not exists Taco_Order_Tacos (
  tacoOrder_id varchar(4) not null,
  taco_id varchar(4) not null
);
alter table Taco_Order_Tacos
  add foreign key (tacoOrder_id) references Taco_Order(id);
alter table Taco_Order_Tacos
  add foreign key (taco_id) references Taco(id);