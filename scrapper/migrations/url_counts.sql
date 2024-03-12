create table if not exists url_counts(
    id int primary key generated always as identity,
    url varchar(256),
    count int
)
