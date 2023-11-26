DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS book2author;

CREATE TABLE  books(
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       author VARCHAR(250) NOT NULL,
                       title VARCHAR(250) NOT NULL,
                       priceOld  VARCHAR(250) DEFAULT NULL,
                       price VARCHAR(250) DEFAULT NULL
);
CREATE TABLE authors(
    id INT NOT NULL AUTO_INCREMENT,
    photo VARCHAR(255),
    name VARCHAR(255) NOT NULL,
    description TEXT
);
CREATE TABLE book2author(
    id INT NOT NULL AUTO_INCREMENT,
    book_id INT NOT NULL,
    author_id INT NOT NULL,
    sort_index INT NOT NULL  DEFAULT 0
);