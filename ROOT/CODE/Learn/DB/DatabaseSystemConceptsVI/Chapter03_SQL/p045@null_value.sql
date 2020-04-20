---- 空值处理 有关true，false和unknown的逻辑运算参见第45页 ----

--暂时将一条记录某个属性置null值
update course set credits = null where course_id='BIO-101';
select * from course where course_id='BIO-101';

--null值的加减乘除运算结果都是null
select credits+1 from course;

--distinct会保留一份null的拷贝
select distinct(credits) from course;

--null=null的结果不是true，而是unknown，
--所以下面的查询不会有任何返回结果
select * from course where null=null;

--unknown和false的区别
--false
select * from course where 1<>1;
--!false=true
select * from course where 1=1;
--unknown
select * from course where null=null;
--!unknown=unknown
select * from course where null<>null;

--恢复置null的记录
update course set credits = 4 where course_id='BIO-101';