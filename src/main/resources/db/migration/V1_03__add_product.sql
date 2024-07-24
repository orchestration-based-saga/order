create table if not exists merchant_product
(
    merchant_inventory_id serial                                   not null primary key,
    name                  text                                     not null,
    description           text,
    ean                   text                                     not null,
    product               integer                                  not null,
    serviceable           boolean                                  not null default false,
    pickup_address        integer
        constraint fk_pickup_addr references merchant_address (id) not null,
    return_address        integer
        constraint fk_return_addr references merchant_address (id) not null,
    stock_level           integer                                  not null,
    reserved_level        integer                                  not null
);