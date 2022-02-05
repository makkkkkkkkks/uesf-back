create table news
(
    id                  int8 not null,
    contenten           varchar(255),
    contentua           varchar(255),
    creation_date       timestamp,
    game                varchar(255),
    imgen               varchar(255),
    imgua               varchar(255),
    news_status         varchar(255),
    short_descriptionen varchar(255),
    short_descriptionua varchar(255),
    titleen             varchar(255),
    titleua             varchar(255),
    update_date         timestamp,
    primary key (id)
);

create table partner
(
    id    int8 not null,
    image varchar(255),
    link  varchar(255),
    order_number varchar(255),
    primary key (id)
);

