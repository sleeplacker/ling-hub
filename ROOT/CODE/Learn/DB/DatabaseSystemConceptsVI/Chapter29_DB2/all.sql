---- DB2特性 ----

--创建基于基本数据类型的类型
create distinct type us_dollor as decimal(9,2);
--可以在建表时使用该类型
create table us_sales(
    id varchar(20) not null,
    price us_dollor
);
--作为查询条件时应该先将数值转为us_dollor类型
select * from us_sales where price > us_dollor(70000);
drop table us_sales;
drop type us_dollor;

--创建结构化类型
create type department_t as 
	(deptname varchar(32),
	depthead varchar(32),
	fqculty_count integer)
	mode db2sql
	ref using  integer;
--定义有类型的(typed)表
create table dept of department_t  (ref is oid user generated);
--插入数据
insert into dept(oid,deptname,depthead,fqculty_count) values(department_t(123123),'Biology','BIO',6);
--查询数据
select * from dept;