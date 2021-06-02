-- scheme
desc user;

-- join(insert)
insert into user values(null, '관리자', 'admin@mysite.com', '1234', 'male');

-- user list(select)
select * from user;


delete from user where no = '7';

-- login(select)
select no, name 
from user 
where email='mani703@naver.com' and password='1234';