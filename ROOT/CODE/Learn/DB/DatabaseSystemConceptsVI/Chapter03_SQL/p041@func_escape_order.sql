---- SQL函数，转义和多属性排序 ----

--在SQL中调用函数
--转大写
select upper(name) from instructor;
--转小写
select lower(name) from instructor;
--去字符串后面的空格
select trim(name) from instructor;

--转义
--查询名字中包含%的记录
select * from instructor where name like '%\%%' escape '\';
--查询名字中包含\的记录
select * from instructor where name like '%\\%' escape '\';

--多个属性的排序
--先按salary排序，如果salary相同，再按name排序
select * from instructor order by salary desc, name desc;