CREATE DATABASE shopping_orders;


USE shopping_orders;

CREATE TABLE users(
  id BIGINT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(64) NOT NULL,
  last_name VARCHAR(128) NOT NULL,
  identification_card CHAR(11) NOT NULL,
  email VARCHAR(64) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  create_date DATETIME NOT NULL,
  create_by VARCHAR(64),
  update_date DATETIME,
  update_by VARCHAR(64),
  state BIT NOT NULL,
  CONSTRAINT pk_clients_id PRIMARY KEY (id)
);

CREATE TABLE products_category(
  id BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  description VARCHAR(128) NOT NULL,
  create_date DATETIME NOT NULL,
  create_by VARCHAR(64),
  update_date DATETIME,
  update_by VARCHAR(64),
  state BIT NOT NULL,
  CONSTRAINT pk_products_category PRIMARY KEY (id)
);



CREATE TABLE products(
  id BIGINT NOT NULL AUTO_INCREMENT,
  category_id BIGINT NOT NULL,
  nombre VARCHAR(256) NOT NULL,
  price DECIMAL(12,2) NOT NULL,
  create_date DATETIME NOT NULL,
  create_by VARCHAR(64),
  update_date DATETIME,
  update_by VARCHAR(64),
  state BIT NOT NULL,
  CONSTRAINT pk_products_id PRIMARY KEY (id),
  CONSTRAINT fk_products_category_id FOREIGN KEY (category_id) REFERENCES products_category (id)
);

CREATE TABLE locations(
	id BIGINT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(120) NOT NULL,
    latitud DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL,
	CONSTRAINT pk_locations_id PRIMARY KEY (id),
    create_date DATETIME NOT NULL,
	create_by VARCHAR(64),
	update_date DATETIME,
	update_by VARCHAR(64),
	state BIT NOT NULL
);


CREATE TABLE orders(
  id BIGINT NOT NULL AUTO_INCREMENT,
  users_id BIGINT NOT NULL,
  product_id BIGINT NOT NULL,
  location_id BIGINT NOT NULL,
  price DECIMAL(12,2) NOT NULL,
  quantity INT NOT NULL,
  sub_total DECIMAL (12, 2),
  order_date DATETIME NOT NULL,
  create_date DATETIME NOT NULL,
  create_by VARCHAR(64),
  update_date DATETIME,
  update_by VARCHAR(64),
  state BIT NOT NULL,
  CONSTRAINT pk_orders_id PRIMARY KEY (id),
  CONSTRAINT fk_orders_clients_id FOREIGN KEY (users_id) REFERENCES users (id),
  CONSTRAINT fk_orders_products_id FOREIGN KEY (product_id) REFERENCES products(id),
  CONSTRAINT fk_orders_locations_id FOREIGN KEY (location_id) REFERENCES locations(id)
);

