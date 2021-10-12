create table books (
                       isbn varchar(20) unique not null,
                       cover bytea,
                       title varchar(50) not null,
                       publisher varchar(50) not null,
                       pageCount integer not null,
                       description text,
                       totalAmount integer not null,
                       status varchar(20) not null,
                       publishDate date not null,
                       primary key(isbn)

);
create table authors(
                        id serial unique not null,
                        firstName varchar(30) not null,
                        lastName varchar(30) not null,
                        primary key(id)
);
create table book_authors(
                             book_isbn varchar(20) not null,
                             author_id bigint not null,
                             primary key (book_isbn, author_id),
                             foreign key(book_isbn) references books(isbn) on delete cascade,
                             foreign key (author_id) references authors(id) on delete cascade
);
create table genres(
                       id serial unique not null,
                       name  varchar(30) not null unique,
                       primary key(id)
);
create table book_genres (
                             book_isbn varchar(20) not null,
                             genre_id bigint not null,
                             primary key(book_isbn, genre_id),
                             foreign key(book_isbn)
                                 references books(isbn) on delete cascade,
                             foreign key(genre_id)
                                 references genres(id) on delete set null
);
create table readers(
                        email varchar(50) unique not null,
                        firstName varchar(30) not null,
                        secondName varchar(30) not null,
                        registrationDate date not null,
                        phone varchar(20) unique,
                        gender varchar(10) not null,
                        primary key(email)
);
create table book_readers(
                             book_isbn varchar(20) not null,
                             reader_email varchar(50) not null,
                             borrow_date date not null,
                             due_date date not null,
                             return_date date,
                             comment text,
                             timePeriod integer not null,
                             status varchar(30) not null,
                             primary key(book_isbn, reader_email),
                             foreign key(book_isbn)
                                 references books(isbn) on delete cascade,
                             foreign key(reader_email)
                                 references readers(email) on delete cascade
);