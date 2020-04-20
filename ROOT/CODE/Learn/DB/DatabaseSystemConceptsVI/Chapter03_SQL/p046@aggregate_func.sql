---- 聚集函数 ----

--5个聚集函数
--1.平均值：avg
--2.最大值：max
--3.最小值：min
--4.总和：sum
--5.计数：count
--其中sum和svg只能作用于数字集

--计算平均工资
select avg (salary) as avg_salary from instructor where dept_name='Comp.Sci.';

--去重后计数
select count (distinct ID) from teaches where semester = 'Spring' and year = 2010;

--分组聚集
select dept_name, avg(salary) as avg_salary from instructor group by dept_name;
select dept_name, count(distinct ID) from instructor natural join teaches where semester = 'Spring' and year = 2010 group by instructor.dept_name;

--having子句
select dept_name, avg(salary) from instructor group by dept_name having avg(salary)>70000;

--空值的聚集运算，空值被当做0，
--如果A是null，则count(A)为0，但是count(*)为1
select sum(salary) from instructor;
select count(salary) from instructor where ID='10101';
update instructor set salary=null where ID='10101';
select sum(salary) from instructor;
select count(*) from instructor where ID='10101';
update instructor set salary=65000 where ID='10101';

--where，group by和having出现顺序
select xxx from xxx where xxx group by xxx having xxx
