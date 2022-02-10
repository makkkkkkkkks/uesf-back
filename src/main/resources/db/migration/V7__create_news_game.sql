create table news_game
(
    news_id int8 not null,
    game_id int8 not null,
    primary key (news_id, game_id)
)
