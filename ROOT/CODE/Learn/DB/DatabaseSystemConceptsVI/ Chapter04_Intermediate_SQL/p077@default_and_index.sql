---- 默认值和索引 ----

--1.默认值，在定义的字段后 default 默认值
create table student (
	ID varchar(5) not null,
	name varchar(20) not null,
	dept_name varchar(20),
	tot_cred numeric(3,0) default 0,
	primary key(ID),
	foreign key(dept_name) references department
);

--索引，下面的语法非标准定义，但是DB2和ORACLE都支持
--创建索引
create index studend_name on student(name);

--查询索引，注意这里的tabname必须是大写，因为单引号中的内容
--是字符串，是区分大小写的，而数据库中的表名都是大写的
--DB2
select * from syscat.indexes where tabname = 'STUDENT'
--ORACLE，注意ORACLE的索引结构和DB2不一样，
--虽然定义索引名studend_name，但是实际索引名不是定义的名字，
--而是系统定义的，这里是SYS_C0011133
select* from all_indexes where table_name='STUDENT';

--删除索引
drop index studend_name;