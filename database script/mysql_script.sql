CREATE DATABASE shopping_orders;

USE shopping_orders;

CREATE TABLE users(
  id BIGINT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(64) NOT NULL,
  last_name VARCHAR(128) NOT NULL,
  identification_card CHAR(11) NOT NULL UNIQUE,
  email VARCHAR(64) NOT NULL UNIQUE,
  `password` VARCHAR(64) NOT NULL,
  created_date DATETIME NOT NULL,
  created_by VARCHAR(64),
  modified_date DATETIME,
  modified_by VARCHAR(64),
  state BIT NOT NULL,
  CONSTRAINT pk_clients_id PRIMARY KEY (id)
);

CREATE TABLE products_category(
  id BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  description VARCHAR(128) NOT NULL,
   created_date DATETIME NOT NULL,
  created_by VARCHAR(64),
 modified_date DATETIME,
  modified_by VARCHAR(64),
  state BIT NOT NULL,
  CONSTRAINT pk_products_category PRIMARY KEY (id)
);


CREATE TABLE products(
  id BIGINT NOT NULL AUTO_INCREMENT,
  category_id BIGINT NOT NULL,
  `name` VARCHAR(128) NOT NULL,
  price DOUBLE(12,2) NOT NULL,
  created_date DATETIME NOT NULL,
  created_by VARCHAR(64),
  modified_date DATETIME,
  modified_by VARCHAR(64),
  state BIT NOT NULL,
  CONSTRAINT pk_products_id PRIMARY KEY (id),
  CONSTRAINT fk_products_category_id FOREIGN KEY (category_id) REFERENCES products_category (id)
);

CREATE TABLE locations(
	id BIGINT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(120) NOT NULL UNIQUE,
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL,
	CONSTRAINT pk_locations_id PRIMARY KEY (id),
	created_date DATETIME NOT NULL,
	created_by VARCHAR(64),
    modified_date DATETIME,
	modified_by VARCHAR(64),
	updated_by VARCHAR(64),
	state BIT NOT NULL
);

CREATE TABLE orders(
  id BIGINT NOT NULL AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  product_id BIGINT NOT NULL,
  location_id BIGINT NOT NULL,
  price DOUBLE(12,2) NOT NULL,
  quantity INT NOT NULL,
  sub_total DOUBLE (12, 2),
  order_date DATETIME NOT NULL,
  created_date DATETIME NOT NULL,
  created_by VARCHAR(64),
  modified_date DATETIME,
  modified_by VARCHAR(64),
  state BIT NOT NULL,
  CONSTRAINT pk_orders_id PRIMARY KEY (id),
  CONSTRAINT fk_orders_clients_id FOREIGN KEY (user_id) REFERENCES users (id),
  CONSTRAINT fk_orders_products_id FOREIGN KEY (product_id) REFERENCES products(id),
  CONSTRAINT fk_orders_locations_id FOREIGN KEY (location_id) REFERENCES locations(id)
);

CREATE TABLE orders_headers
(
    id            BIGINT   NOT NULL AUTO_INCREMENT,
    user_id       BIGINT   NOT NULL,
    location_id   BIGINT   NOT NULL,
    total         DOUBLE(12, 2),
    order_date    DATETIME NOT NULL,
    created_date  DATETIME NOT NULL,
    created_by    VARCHAR(64),
    modified_date DATETIME,
    modified_by   VARCHAR(64),
    state         BIT      NOT NULL,
    CONSTRAINT pk_orders_id PRIMARY KEY (id),
    CONSTRAINT fk_orders_users_id FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_orders_header_locations_id FOREIGN KEY (location_id) REFERENCES locations (id)
);


CREATE TABLE orders_details
(
    id              BIGINT        NOT NULL AUTO_INCREMENT,
    order_header_id BIGINT        NOT NULL,
    product_id      BIGINT        NOT NULL,
    price           DOUBLE(12, 2) NOT NULL,
    quantity        INT           NOT NULL,
    sub_total       DOUBLE(12, 2),
    created_date    DATETIME      NOT NULL,
    created_by      VARCHAR(64),
    modified_date   DATETIME,
    modified_by     VARCHAR(64),
    state           BIT           NOT NULL,
    CONSTRAINT pk_orders_details_id PRIMARY KEY (id),
    CONSTRAINT fk_orders_details_products_id FOREIGN KEY (product_id) REFERENCES products (id),
    CONSTRAINT fk_orders_details_orders_headers_id FOREIGN KEY (order_header_id) REFERENCES orders_headers (id)
);

DELIMITER //
create procedure GetReportOrders()

begin
    select u.id,
           concat(u.first_name, u.last_name) fullName,
           sum(o.sub_total)                  totalOrder
    from orders o
             inner join users u on o.user_id = u.id
    group by u.id, first_name, last_name;

end //
DELIMITER ;

DELIMITER //
create procedure GetReportQuantityOrdersByDate()

begin
    select u.id,
           to_date(o.order_date) orderDate,
           count(o.id)           quantity
    from orders o
             inner join users u on o.user_id = u.id
    group by u.id, first_name, last_name;

end //
DELIMITER ;

DELIMITER //
create procedure GetReportQuantityOrdersByDate()

begin
    select Date(o.order_date) orderDate,
           count(*)           quantity
    from orders o
             inner join users u on o.user_id = u.id
    group by Date(o.order_date);

end //
DELIMITER ;

create table ReportOrders
(
    id         bigint,
    fullName   varchar(64),
    totalOrder double(12, 2)
);




