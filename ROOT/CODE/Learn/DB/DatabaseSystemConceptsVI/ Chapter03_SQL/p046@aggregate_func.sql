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