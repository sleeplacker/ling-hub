---- 嵌套查询 ----

--1.多个字段关联的嵌套查询
select * 
from takes 
where (course_id, sec_id, semester, year) in 
		(select course_id, sec_id, semester, year
		from teaches
		where teaches.ID = 10101);
	
--2.集合的比较
--查询工资至少比Biology系某一个教师的工资要高的老师的姓名
select * from instructor 
where salary > some (select salary from instructor where dept_name='Biology');
--等价于查询工资比Biology系工资最低的教师的工资要高的老师的姓名
select * from instructor 
where salary > (select min(salary) from instructor where dept_name='Biology');
--查询工资比Comp.Sci.系每个教师的工资要高的老师的姓名
select * from instructor 
where salary > all (select salary from instructor where dept_name='Comp.Sci.');
--等价于查询工资比Comp.Sci.系工资最高的教师的工资要高的老师的姓名
select * from instructor 
where salary > (select max(salary) from instructor where dept_name='Comp.Sci.');

--3.用exists和not exists关键字进行空关系测试
--exists用于检查子查询中是否存在元组
--使用exists关键字方式查询2009年秋季和2010年春季同事开课的课程
select course_id from section as S
where semester='Fall' and year=2009 and
	exists (select * from section as T where
	semester='Spring' and year=2010 and S.course_id=T.course_id
	);

--查询选修了Biology系开设的所有课程的学生，结果为空，因为Biology系有一门课并未开设
--not exists后面子查询说明：Biology系开设的所有课程除去该学生所有选修的课程后剩下的课程，
--not exists表示不存在这样的课程，说明该学生把Biology系开设的所有课程都选修了
select S.ID, S.name
from student as S
where not exists (
	(select course_id from course where dept_name='Biology')
	except
	(select T.course_id from takes as T where S.ID=T.ID)
	);
	
--查询选修了Elec.Eng.系开设的所有课程的学生，结果有一个学生
select S.ID, S.name
from student as S
where not exists (
	(select course_id from course where dept_name='Elec.Eng.')
	except
	(select T.course_id from takes as T where S.ID=T.ID)
	);
	
--4.with子句，先产生一个或多个关系，然后立即在随后的查询中使用这些关系
--查询所有这样的系，它的工资总额大于所有系的平均工资总额
with 
	dept_total(dept_name, value) 
		as (select dept_name, sum(salary) from instructor group by dept_name),
	dept_total_avg(value)
		as (select avg(salary) from instructor)
select dept_total.dept_name from dept_total, dept_total_avg where dept_total.value > dept_total_avg.value;