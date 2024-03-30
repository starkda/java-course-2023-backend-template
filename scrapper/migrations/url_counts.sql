create table if not exists scrapper.url_counts(
    id int primary key generated always as identity,
    url varchar(256),
    count int
)
