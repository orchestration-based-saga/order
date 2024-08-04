alter table merchant_product drop column if exists ean;
alter table merchant_product drop column if exists product;

alter table orders drop column if exists cancellation_date;
alter table orders drop column if exists created_at;