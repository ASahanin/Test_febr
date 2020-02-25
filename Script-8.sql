CREATE TABLE news(
id serial primary key,
title varchar not null,
text varchar not null,
published_at timestamp not null
);

delete from news where news.id = 1;
update news set title = 'вот вот', text = 'проверка' where news.id = 5;