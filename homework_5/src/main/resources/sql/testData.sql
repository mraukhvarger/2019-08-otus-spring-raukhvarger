/* Авторы */
insert into authors_table (id, first_name, last_name)
values (1, 'Федор', 'Достоевски');
insert into authors_table (id, first_name, last_name)
values (2, 'Иван', 'Шмелев');
insert into authors_table (id, first_name, last_name)
values (3, 'Гилберт', 'Честертон');
insert into authors_table (id, first_name, last_name)
values (4, 'Иван', 'Ильин');

/* Жанры */
insert into genres_table (id, genre)
values (1, 'Роман');
insert into genres_table (id, genre)
values (2, 'Философия');
insert into genres_table (id, genre)
values (3, 'Автобиография');

/* Книги */
insert into books_table (id, id_genre, caption)
values (1, 1, 'Преступление и наказание');
insert into books_table (id, id_genre, caption)
values (2, 3, 'Лето Господне');
insert into books_table (id, id_genre, caption)
values (3, 2, 'Ортодоксия');
insert into books_table (id, id_genre, caption)
values (4, 1, 'Человек который был Четвергом');

/* Связи авторов и книг */
insert into authors_and_books_table (id_author, id_book)
values (1, 1);
insert into authors_and_books_table (id_author, id_book)
values (2, 2);
insert into authors_and_books_table (id_author, id_book)
values (3, 3);
insert into authors_and_books_table (id_author, id_book)
values (3, 4);