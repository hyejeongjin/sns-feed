use sns_feed;

insert into user (create_at, deleted_at, id, updated_at, brith_date, email, mobile_number, password, profile, user_name)
values (now(),null,1,now(),'19981124','test@naver.com','01000000000','test','image.jpg','테스트');

select * from user;

select * from comment;

select * from board;