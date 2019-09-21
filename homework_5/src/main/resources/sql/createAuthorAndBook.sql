CREATE TABLE authors_and_books_table (
    id INT AUTO_INCREMENT,
    id_author INT NOT NULL,
    id_book INT NOT NULL
);
ALTER TABLE authors_and_books_table ADD PRIMARY KEY (id, id_author, id_book)
