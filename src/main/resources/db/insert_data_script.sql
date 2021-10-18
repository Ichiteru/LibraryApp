insert into genres (name) values ('Novel');
insert into genres (name) values ('Adventure');
insert into genres (name) values ('Science');
insert into genres (name) values ('Folklore');
insert into genres (name) values ('Prose');
insert into genres (name) values ('Fantasy');
insert into genres (name) values ('Detective');
insert into genres (name) values ('Manual');
insert into genres (name) values ('Humor');
insert into genres (name) values ('Religion');
insert into genres (name) values ('Poetry');

insert into books (isbn, title, publisher, pagecount, totalamount, status, publishdate)
values ('973180983', 'Dune', 'Books Magazine', 100, 10, 'AVAILABLE', '2000-08-01');
insert into books (isbn, title, publisher, pagecount, totalamount, status, publishdate)
values ('973678365', 'LOTR', 'Books Shop', 200, 15, 'AVAILABLE', '2005-12-21');
insert into books (isbn, title, publisher, pagecount, totalamount, status, publishdate)
values ('973483298', 'Harry Potter', 'British Books', 340, 5, 'AVAILABLE', '2015-09-11');
insert into books (isbn, title, publisher, pagecount, totalamount, status, publishdate)
values ('973123456', 'Java EE', 'Programming magazine', 600, 3, 'AVAILABLE', '2017-12-25');

insert into authors (firstname, lastname) values ('Frank', 'Herbert');
insert into authors (firstname, lastname) values ('John', 'Tolkien');
insert into authors (firstname, lastname) values ('Joan', 'Rowling');
insert into authors (firstname, lastname) values ('Kate', 'Sierra');
insert into authors (firstname, lastname) values ('Bert', 'Bates');

insert into book_authors values (1, 1);
insert into book_authors values (2, 2);
insert into book_authors values (3, 3);
insert into book_authors values (3, 4);
insert into book_authors values (4, 5);

insert into book_genres (book_id, genre_id) values (1, 1);
insert into book_genres (book_id, genre_id) values (1, 2);
insert into book_genres (book_id, genre_id) values (2, 3);
insert into book_genres (book_id, genre_id) values (2, 4);
insert into book_genres (book_id, genre_id) values (3, 5);
insert into book_genres (book_id, genre_id) values (3, 6);
insert into book_genres (book_id, genre_id) values (4, 7);
insert into book_genres (book_id, genre_id) values (4, 8);