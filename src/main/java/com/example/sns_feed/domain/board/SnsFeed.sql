use sns_feed;




DROP DATABASE sns_feed;
CREATE DATABASE sns_feed CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

select * from users;



insert into users (create_at, deleted_at, id, updated_at, birth_date, email, mobile_number, password, profile, user_name)
values (now(),null,1,now(),'19981124','test@naver.com','01000000000','test','image.jpg','테스트');

insert into users (create_at, deleted_at, id, updated_at, birth_date, email, mobile_number, password, profile, user_name)
values (now(),null,2,now(),'19981124','test2@naver.com','01000000001','test','image.jpg','테스트2');

insert into users (create_at, deleted_at, id, updated_at, birth_date, email, mobile_number, password, profile, user_name)
values (now(),null,3,now(),'19981124','test3@naver.com','01000000002','test','image.jpg','테스트3');

select * from users;

select * from comments;

select * from follows;

insert into follows values (1, 'ACCEPTED', 1,2);
insert into follows values (2, 'ACCEPTED', 1,3);


insert into comments values (1, now(),now(),'댓글내용',1,1);
insert into comments values (2, now(),now(),'댓글내용',1,1);
insert into comments values (3, now(),now(),'댓글내용',1,1);

select * from boards b
    inner join users u
        on b.user_id = u.id
            inner join follows f
                    on f.follow_user_id = f.following_user_id;