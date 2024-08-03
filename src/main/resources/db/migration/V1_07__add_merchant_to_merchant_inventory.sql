alter table merchant_product
    add column if not exists merchant_id integer
        references merchant (id) not null;