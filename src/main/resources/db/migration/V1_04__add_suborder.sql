create table if not exists suborder
(
    id                    serial                                                           not null primary key,
    order_id              integer
        constraint fk_order references "order" (id)                                        not null,
    merchant_inventory_id integer
        constraint fk_merchant_product references merchant_product (merchant_inventory_id) not null,
    price                 numeric(15, 2)                                                   not null,
    amount                integer                                                          not null
);