create table if not exists bot.chat_track(
    chat_id int,
    track_id int,
    foreign key(chat_id) references bot.chat(id),
    foreign key(track_id) references bot.track(id)
)
