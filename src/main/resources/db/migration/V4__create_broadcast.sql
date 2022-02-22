create table broadcast
(
    id                int8 not null,
    twitch_username   varchar(255),
    broadcast_ordinal varchar(255),
    primary key (id)
);
