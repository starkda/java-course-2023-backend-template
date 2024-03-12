create table if not exists chat_track(
    chat_id int,
    track_id int,
    foreign key(chat_id) references chat(id),
    foreign key(track_id) references track(id)
)
