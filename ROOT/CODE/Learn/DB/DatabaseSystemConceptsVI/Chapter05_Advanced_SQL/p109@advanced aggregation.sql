---- 高级聚集特性 ----

--1. 排名
--使用rank方法查询所有教师的工资排名，同名次处理：2名并列第5，后面是第7名
select ID, rank() over (order by salary desc) as s_rank, salary from instructor order by s_rank;
--使用基本的sql聚集函数来实现上面的排名，排名即比自己工资高的人数+1
--虽然基本sql聚集函数能实现排名，但是随着数据量增加，排名效率会指数降低，
--而使用rank方法不会有上面问题
select ID, 
	(1+(select count(*) from instructor B where B.salary > A.salary)) as s_rank,salary
from instructor A order by s_rank;
--使用dense_rank方法查询所有教师的工资排名，同名次处理：2名并列第5，后面是第6名
select ID, dense_rank() over (order by salary desc) as s_rank, salary from instructor order by s_rank;
