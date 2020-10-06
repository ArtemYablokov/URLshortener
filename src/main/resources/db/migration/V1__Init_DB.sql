

create sequence hibernate_sequence start 1 increment 1;

create table url (
id int8 not null,
short_url_hash_id int8,
short_url varchar(255),
user_url varchar(2048),
primary key (id)
);