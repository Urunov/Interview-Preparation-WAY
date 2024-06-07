select current_time;

select version();

select date_trunc('second', current_timestamp - pg_postmaster_start_time()) as uptime;

select current_timestamp - pg_postmaster_start_time();

select date_trunc('second', current_timestamp - pg_postmaster_start_time()) as uptime;

select pg_listening_channels();

select pg_database_size(current_database());

select sum(pg_database_size());

select pg_relation_size('cars');

select pg_total_relation_size('cars');

select pg_size_pretty(pg_relation_size('orders'));

set
work_mem = '16MB';

show
work_mem;
/**
  The PostgreSQL
  */
show
config_file;

select *
from cars;

/** Less Than or Equal To */
select *
from cars
where model > '1975';

/**Greater Than or Equal to*/
select name, model
from cars
where model > '1975';
/**
  Not Equal To
  **/
select *
from cars
where name != 'Volvo';
/*
    LIKE
*/
select *
from testproducts
where product_name like 'M%';
/*
    ILIKE
*/
select *
from testproducts;

update cars
set color = 'blue'
where name = 'Ford';

update testproducts
set product_name = 'mine Janes Favorite Cheese'
where category_id = '4';

select *
from testproducts
where product_name ILIKE  'm%';

/*
    AND
*/
select *
from cars;

select *
from cars
where brand = 'p1800'
  AND type = '4';
/*
    OR
*/
select *
from cars
where brand = 'p1800'
   OR name = 'Ford';

select *
from cars
where brand = 'M1'
   OR color = 'green';

select *
from cars;

/*
IN
The IN operator is used when a column's value matches any of the values in a list:
*/
select *
from cars
where name IN ('Volvo', 'Ford', 'BMW');

/**
  BETWEEN

The BETWEEN operator is used to check if a column's value is between a specified range of values:
  */

select *
from cars
where model Between '1978' AND '2012';

select *
from cars;

/**
  IS NULL
*/
select *
from cars
where type is NULL;

/**
  NOT
*/
select *
from cars
where type NOT LIKE '%n';

select *
from cars
where type NOT ilike  'm%';

select *
from customers;

select DISTINCT customers.country
from customers;

/** SELECT COUNT(DISTINCT) */
SELECT COUNT(DISTINCT customers.country)
from customers;

select *
from customers
where city = 'London';

select *
from customers
where customer_id > 80;

select current_time;

/**  CREATE:  categories*/
CREATE TABLE categories
(
    category_id   SERIAL NOT NULL PRIMARY KEY,
    category_name VARCHAR(255),
    description   VARCHAR(255)
);

/** SELECT: categories*/
select *
from categories
order by categories.category_id desc;


/** INSERT: categories*/
INSERT INTO categories (category_name, description)
VALUES ('Beverages', 'Soft drinks, coffees, teas, beers, and ales'),
       ('Condiments', 'Sweet and savory sauces, relishes, spreads, and seasonings'),
       ('Confections', 'Desserts, candies, and sweet breads'),
       ('Dairy Products', 'Cheeses'),
       ('Grains/Cereals', 'Breads, crackers, pasta, and cereal'),
       ('Meat/Poultry', 'Prepared meats'),
       ('Produce', 'Dried fruit and bean curd'),
       ('Seafood', 'Seaweed and fish');



update categories
set category_name = 'Seafood'
where description = 'Ocean: Seaweed and fish';

update categories
set description = 'Just water, Soft drinks, coffees, teas, beers, and ales'
where category_id = '1';

update categories
set description = 'COW: Prepared meats'
where category_id = '6';

select *
from categories
order by categories.category_id desc;

select *
from categories;
