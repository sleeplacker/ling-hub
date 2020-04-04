--按书上建表

--1. 部门表
create table department (
	dept_name varchar(20) not null, --部门名称
	building varchar(15),		--部门大楼
	budget numeric(12,2),	--部门预算
	primary key (dept_name) --部门名称作为主键
);

--2. 课程表
create table course (
	course_id varchar(7) not null,	--课程编号
	title	varchar(50), --课程名
	dept_name varchar(20), -- 课程所属部门
	credits numeric(2,0), -- 课程学分
	primary key (course_id), --课程编号作为主键
	foreign key (dept_name) references department --外键
);

--3. 教师表
create table instructor (
	ID varchar(5) not null, --编号
	name varchar(20) not null, --教师姓名
	dept_name varchar(20), --所属部门
	salary numeric(8,2), --薪资
	primary key (ID), --主键
	foreign key (dept_name) references department --外键
);

--4. 课程被讲授的记录表
create table section (
	course_id varchar(8) not null, --授课的课程编号
	sec_id varchar(8) not null, --同一门课一年内被教授的序号
	semester varchar(6) not null, --授课季度
	year numeric(4,0) not null, --授课学年
	building varchar(15), --授课所在大楼
	room_number varchar(7), --授课教室编号
	time_slot_id varchar(4), --授课时段
	primary key (course_id, sec_id, semester, year), --联合主键
	foreign key (course_id) references course
);

--5. 教室讲授课程的记录表
create table teaches (
	ID varchar(5) not null, --授课教室编号
	course_id varchar(8) not null, --授课课程编号
	sec_id varchar(8) not null, --一年内授课序号
	semester varchar(6) not null, --授课季度
	year numeric(4,0) not null, --授课学年
	primary key (ID, course_id, sec_id, semester, year), --联合主键
	foreign key (course_id, sec_id, semester, year) references section, --外键1
	foreign key (ID) references instructor --外键2
);

--6.学生表
create table student (
	ID varchar(5) not null,
	name varchar(20) not null,
	dept_name varchar(20),
	tot_cred numeric(3,0),
	primary key(ID),
	foreign key(dept_name) references department
);

--7.学生上课表
create table takes ( 
	ID varchar(5) not null,
	course_id varchar(8) not null,
	sec_id varchar(8) not null,
	semester varchar(6) not null,
	year numeric(4,0) not null,
	grade varchar(2),
	primary key(ID, course_id, sec_id, semester, year),
	foreign key(course_id, sec_id, semester, year) references section,
	foreign key(ID) references student
);