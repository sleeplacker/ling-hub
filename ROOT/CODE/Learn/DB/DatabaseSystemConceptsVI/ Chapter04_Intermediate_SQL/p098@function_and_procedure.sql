---- 函数和过程 ----

--函数声明和定义，不同数据库语法差异比较大
--定义dept_count方法统计每个部门的老师数量
--ORACLE定义方式，编译失败
create or replace function dept_count(dname varchar(20))
	return integer
	as
	d_count integer;
	begin
		select count(*) into d_count from instructor where dept_name=dname;
		return d_count;
	end dept_count;
--DB2定义方式
CREATE OR REPLACE FUNCTION dept_count(dname VARCHAR(20))
     RETURNS INT
     LANGUAGE SQL
     READS SQL DATA
     NO EXTERNAL ACTION
     RETURN SELECT COUNT(*)  FROM INSTRUCTOR WHERE DEPT_NAME=dname;
--函数调用-查询教师数量大于2个的部门以及预算
select dept_name, budget
from department
where dept_count(dept_name) > 2;    