insert into genres (name) values ('Роман');
insert into genres (name) values ('Приключение');
insert into genres (name) values ('Наука');
insert into genres (name) values ('Фольклор');
insert into genres (name) values ('Проза');
insert into genres (name) values ('Фантастика');
insert into genres (name) values ('Детектив');
insert into genres (name) values ('Справочник');
insert into genres (name) values ('Юмор');
insert into genres (name) values ('Религия');
insert into genres (name) values ('Поэзия');

insert into books (isbn, title, publisher, pagecount, totalamount, status, publishdate)
values (973180983, 'Dune', 'Books Magazine', 100, 10, 'AVAILABLE', '2000-08-01');
insert into books (isbn, title, publisher, pagecount, totalamount, status, publishdate)
values (973678365, 'LOTR', 'Books Shop', 200, 15, 'AVAILABLE', '2005-12-21');
insert into books (isbn, title, publisher, pagecount, totalamount, status, publishdate)
values (973483298, 'Harry Potter', 'British Books', 340, 5, 'AVAILABLE', '2015-09-11');
insert into books (isbn, title, publisher, pagecount, totalamount, status, publishdate)
values (973123456, 'Java EE', 'Programming magazine', 600, 3, 'AVAILABLE', '2017-12-25');

insert into authors (firstname, lastname) values ('Frank', 'Herbert');
insert into authors (firstname, lastname) values ('John', 'Tolkien');
insert into authors (firstname, lastname) values ('Joan', 'Rowling');
insert into authors (firstname, lastname) values ('Kate', 'Sierra');
insert into authors (firstname, lastname) values ('Bert', 'Bates');

insert into book_authors values ('973180983', 1);
insert into book_authors values ('973678365', 2);
insert into book_authors values ('973483298', 3);
insert into book_authors values ('973123456', 4);
insert into book_authors values ('973123456', 5);