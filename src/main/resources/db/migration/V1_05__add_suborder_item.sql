create table if not exists suborder_item
(
    id                    serial  not null primary key,
    merchant_inventory_id integer not null,
    suborder_id           integer not null
)