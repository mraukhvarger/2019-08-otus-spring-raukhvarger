CREATE TABLE authors_and_books_table (
    id_author INT8 NOT NULL,
    id_book INT8 NOT NULL,
    CONSTRAINT fk_author_id
        FOREIGN KEY (id_author)
            REFERENCES authors_table (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_book_id
        FOREIGN KEY (id_book)
            REFERENCES books_table (id)
            ON DELETE CASCADE,
);
