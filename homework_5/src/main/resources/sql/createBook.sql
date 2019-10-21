CREATE TABLE books_table (
    id INT8 PRIMARY KEY AUTO_INCREMENT,
    id_genre INT8,
    caption VARCHAR (1024) NOT NULL,
    CONSTRAINT fk_genre_id
        FOREIGN KEY (id_genre)
        REFERENCES genres_table (id)
        ON DELETE SET NULL
);
