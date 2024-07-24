create table if not exists "order"
(
    id                serial                            not null primary key,
    merchant_id       integer
        constraint fk_merchant references merchant (id) not null,
    customer_id       uuid                              not null,
    order_id          text                              not null,
    status            text                              not null,
    payment_method    text                              not null,
    grand_total       numeric(15, 2)                    not null,
    currency          text                              not null,
    created_at        timestamp                         not null,
    modified_at       timestamp,
    confirmed_at      timestamp,
    packed_at         timestamp,
    cancellation_date timestamp
);
