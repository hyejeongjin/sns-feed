
use sns_feed;




DROP DATABASE sns_feed;
CREATE DATABASE sns_feed CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

select * from users;

select * from boards;


insert into boards values (now(),1,now(),1,'게시글내용','제목내용');
insert into boards values (now(),3,now(),1,'게시글내용','제목내용검색');
insert into boards values (now(),2,now(),1,'게시글내용','제목내용');
insert into boards values (now(),4,now(),1,'게시글내용','제목내용검색');
insert into boards values (now(),5,now(),1,'게시글내용','제목내용');
insert into boards values (now(),6,now(),1,'게시글내용','제목내용');
insert into boards values (now(),7,now(),1,'게시글내용','제목내용검색');
insert into boards values (now(),8,now(),1,'게시글내용','제목내용');
insert into boards values (now(),9,now(),2,'게시글내용','제목내용');
insert into boards values (now(),10,now(),2,'게시글내용','제목내용');
insert into boards values (now(),11,now(),2,'게시글내용','제목내용검색');
insert into boards values (now(),12,now(),2,'게시글내용','제목내용');
insert into boards values (now(),13,now(),2,'게시글내용','제목내용');
insert into boards values (now(),14,now(),2,'게시글내용','제목내용검색');
insert into boards values (now(),15,now(),2,'게시글내용','제목내용');
insert into boards values (now(),16,now(),3,'게시글내용','제목내용');
insert into boards values (now(),17,now(),3,'게시글내용','제목내용');
insert into boards values (now(),18,now(),3,'게시글내용','제목내용검색');
insert into boards values (now(),19,now(),3,'게시글내용','제목내용');
insert into boards values (now(),20,now(),3,'게시글내용','제목내용');



insert into boards values (now(),21,now(),2,'게시글내용','제목내용');
insert into boards values (now(),22,now(),2,'게시글내용','제목내용검색');
insert into boards values (now(),23,now(),2,'게시글내용','제목내용');
insert into boards values (now(),24,now(),2,'게시글내용','제목내용');
insert into boards values (now(),25,now(),2,'게시글내용','제목내용검색');
insert into boards values (now(),26,now(),2,'게시글내용','제목내용');
insert into boards values (now(),27,now(),3,'게시글내용','제목내용');
insert into boards values (now(),28,now(),3,'게시글내용','제목내용');
insert into boards values (now(),29,now(),3,'게시글내용','제목내용검색');
insert into boards values (now(),30,now(),3,'게시글내용','제목내용');
insert into boards values (now(),31,now(),3,'게시글내용','제목내용');


insert into boards values (now(),32,now(),2,'게시글내용','제목내용');
insert into boards values (now(),33,now(),2,'게시글내용','제목내용검색');
insert into boards values (now(),34,now(),2,'게시글내용','제목내용');
insert into boards values (now(),35,now(),2,'게시글내용','제목내용');
insert into boards values (now(),36,now(),2,'게시글내용','제목내용검색');
insert into boards values (now(),37,now(),2,'게시글내용','제목내용');
insert into boards values (now(),38,now(),3,'게시글내용','제목내용');
insert into boards values (now(),39,now(),3,'게시글내용','제목내용');
insert into boards values (now(),40,now(),3,'게시글내용','제목내용검색');
insert into boards values (now(),41,now(),3,'게시글내용','제목내용');
insert into boards values (now(),42,now(),3,'게시글내용','제목내용');



select * from users;

select * from comments;

select * from follows;


insert into follows values (now(),1,2,1,now(),'ACCEPTED');
insert into follows values (now(),2,3,1,now(),'ACCEPTED');
insert into follows values (now(),3,3,2,now(),'ACCEPTED');


insert into comments values (1, now(),now(),'댓글내용',1,1);
insert into comments values (2, now(),now(),'댓글내용',1,1);
insert into comments values (3, now(),now(),'댓글내용',1,1);

insert into comments values (1,1,now(),now(),1,'댓글내용');
insert into comments values (1,2,now(),now(),2,'댓글내용');
insert into comments values (1,3,now(),now(),3,'댓글내용');
insert into comments values (2,4,now(),now(),1,'댓글내용');
insert into comments values (2,5,now(),now(),2,'댓글내용');
insert into comments values (2,6,now(),now(),3,'댓글내용');


update users set deleted_at = null where id between 1 and 3;

select * from users;



select
    b.id, u.user_name,b.title,IFNULL((select count(board_id) as commentCounts from comments where board_id = b.id group by board_id), 0) as commentCount,b.updated_at
from boards b
     inner join users u
    on b.user_id = u.id
                inner join follows f
                    on f.receiver = u.id
        group by b.id;


# 게시글, 댓글 카운트까지
select
    b.id,
    u.user_name,
    b.title,
    (select
         COUNT(c.user_id)
     from boards bb
              inner join users u on b.user_id = u.id
              left join comments c on c.board_id = b.id
     where bb.id  = b.id
     group by b.id) as commentCount,
    b.updated_at
from boards b
    inner join users u on b.user_id = u.id;

#게시글, title검색
select
    b.id,
    u.user_name,
    b.title,
    (select
         COUNT(c.user_id)
     from boards bb
              inner join users u on b.user_id = u.id
              left join comments c on c.board_id = b.id
     where bb.id  = b.id
     group by b.id) as commentCount,
    b.updated_at
from boards b
    inner join users u on b.user_id = u.id
        where title like '%검색%';

# 게시글, 댓글 카운트까지, 팔로윙, sender뒤가 sessionId
select
    b.id,
    u.user_name,
    b.title,
    (select
         COUNT(c.user_id)
     from boards bb
              inner join users u on b.user_id = u.id
              left join comments c on c.board_id = b.id
     where bb.id  = b.id
     group by b.id) as commentCount,
    b.updated_at
from boards b
    inner join users u on b.user_id = u.id
        inner join follows f on b.user_id = f.receiver where f.follow_status = 'ACCEPTED' and  f.sender = 1;

# 게시글, 댓글 카운트, 팔로윙, 검색
select
    b.id,
    u.user_name,
    b.title,
    (select
         COUNT(c.user_id)
     from boards bb
              inner join users u on b.user_id = u.id
              left join comments c on c.board_id = b.id
     where bb.id  = b.id
     group by b.id) as comment_count,
    b.updated_at,
    COUNT(*) OVER() AS total_count
from boards b
         inner join users u on b.user_id = u.id
         left join follows f on b.user_id = f.receiver
where title like '%검색%' and f.follow_status = 'ACCEPTED' and  f.sender = 1
order by deleted_at desc;


#페이지만 검색
select
    b.id,
    u.user_name,
    b.title,
    (select
         COUNT(c.user_id)
     from boards bb
              inner join users u on b.user_id = u.id
              left join comments c on c.board_id = b.id
     where bb.id  = b.id
     group by b.id) as comment_count,
    b.updated_at,
    COUNT(*) OVER() AS total_count
from boards b
         inner join users u on b.user_id = u.id
         left join follows f on b.user_id = f.receiver
order by updated_at desc;








select
    COUNT(c.user_id)
from boards b
         inner join users u on b.user_id = u.id
         left join comments c on c.board_id = b.id
group by b.id;



            left join follows f on f.receiver = b.user_id;




select * from boards;

#Count 댓글수
select count(board_id) as commentCounts from comments where board_id = 1 group by board_id ;

select * from comments;

select version();