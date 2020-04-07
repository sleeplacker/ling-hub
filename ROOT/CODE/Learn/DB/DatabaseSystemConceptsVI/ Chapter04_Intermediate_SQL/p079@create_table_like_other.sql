---- 创建表的扩展 ----

--创建和其他表相同结构的表
--DB2支持，ORACLE不支持
create table temp_student like student;

--创建一个表，列明和数据类型从查询结果推导出
--ORACLE支持，DB2不支持
create table temp_student as (select * from student where tot_cred>50);