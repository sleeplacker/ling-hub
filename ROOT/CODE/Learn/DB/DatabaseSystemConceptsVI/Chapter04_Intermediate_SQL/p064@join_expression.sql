---- 连接表达式 ----

--1.使用连接表达式实现两个表连接查询
select student.ID as ID, name, dept_name, tot_cred,
	course_id, sec_id, semester, year, grade
from student join takes on student.ID = takes.ID;

--2. 外连接，包括left/rigth/full outer join3种，left/right其实一样，只是关系的左右调换一下位置
--join为内连接，左右关系都匹配的元组才会返回
--查询所有学生和他们所选修的课程(缺少未选修任何课程的学生)
select name, course_id from student join takes on student.ID = takes.ID;
--left outer join为左外连接，左边的所有元组都会保留，包括和右边关系不匹配的元组
select name, course_id from student left outer join takes on student.ID = takes.ID;
如果左右关系关联字段名相同，可以用using (ID) 替换 student.ID = takes.ID
select name, course_id from student left outer join takes using (ID);
--通过外连接找出没有选修任何课程的学生
select name from student left outer join takes on student.ID = takes.ID where takes.course_id is null;
--全外连接
--查询Comp.Sci.系的所有学生以及他们在2009年春季选修的所有课程，
--需要显示2009年春季所有课程，包括他们没选的课程
select * from 
	(select * from student where dept_name = 'Comp.Sci.') S
	full outer join 
	(select * from takes where semester = 'Spring' and year = 2009) T
	on S.ID = T.ID;
--on和where的区别：
--on在外连接中可以找出未左右关系未匹配的元组，比如左边为非null，右边为null的元组也能找出
--where只能找出左右关系完全匹配的元组，左边为非null，右边为null的元组不会找出
--例如将上面查询语句中的on改为where，因为on是必须的，所以使用1=1表示on true
--查询结果表明：已经失去了外连接特性，和内连接结果一样
select * from 
	(select * from student where dept_name = 'Comp.Sci.') S
	full outer join 
	(select * from takes where semester = 'Spring' and year = 2009) T
	on 1=1 where S.ID = T.ID;