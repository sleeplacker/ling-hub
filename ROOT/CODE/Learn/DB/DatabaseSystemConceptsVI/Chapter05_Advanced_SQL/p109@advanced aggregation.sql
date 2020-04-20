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
--分区排名，对每个部门员工的工资分别进行排序
select 
	ID, dept_name, 
	rank() over (partition by dept_name order by salary desc) as dept_rank,
	salary
from instructor
order by dept_name, dept_rank;
--获取前n个元组，获取工资最高的前8个教师
select ID, salary from instructor order by salary desc limit 8;
--按百分比方式显示排名，ORACLE支持，DB2不支持
select ID, percent_rank() over (order by salary desc) as s_rank, salary from instructor order by s_rank;
--按累计分布方式显示排名，ORACLE支持，DB2不支持
select ID, cume_dist() over (order by salary desc) as s_rank, salary from instructor order by s_rank;
--排序并设置行号，相同名次的行号不一样，且怎样分配不确定
select ID, row_number() over (order by salary desc) as s_rank, salary from instructor order by s_rank;
--将所有元组分成几个等级
select ID, salary, ntile(4) over (order by salary desc) as quantile
from instructor;
--排名中的空值处理，包括空值第一和空值最后两种
select ID, rank() over (order by salary desc nulls first) as s_rank, salary from instructor order by s_rank;
select ID, rank() over (order by salary desc nulls last) as s_rank, salary from instructor order by s_rank;

--2. 分窗
--查询自己排名前3个人和自己的平均工资
select ID, salary, avg(salary) over (order by salary desc rows 3 preceding) as avg_s from instructor;
--查询自己排名前所有人和自己的平均工资
select ID, salary, avg(salary) over (order by salary desc rows unbounded preceding) as avg_s from instructor;
--following替换preceding表示自己排名后面的元组
--查询自己排名前3人和后2个人和自己的平均工资
select ID, salary, 
avg(salary) over (order by salary desc rows between 3 preceding and 2 following) as avg_s 
from instructor;
--按照order by属性的取值范围了设置窗口
--查询比自己工资高或低5000以内平均工资
select ID, salary, 
avg(salary) over (order by salary desc range between 5000 preceding and 5000 following) as avg_s 
from instructor;
--分区后分窗
--查询每个系，工资排名前1人和自己的平均工资
select ID, salary, dept_name,
avg(salary) over (partition by dept_name order by salary desc rows between 1 preceding and current row) as avg_s 
from instructor;