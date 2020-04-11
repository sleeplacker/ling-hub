---- 递归查询 ----

--方法一，定义函数递归查询
--参考第106页

--方法二，在SQL中递归查询
--造数据：先将prereq表清空，再插入新数据
DELETE FROM PREREQ;
INSERT INTO PREREQ (COURSE_ID, PREREQ_ID) VALUES ('BIO-301', 'BIO-101');
INSERT INTO PREREQ (COURSE_ID, PREREQ_ID) VALUES ('BIO-399', 'BIO-301');
INSERT INTO PREREQ (COURSE_ID, PREREQ_ID) VALUES ('CS-190', 'CS-101');
INSERT INTO PREREQ (COURSE_ID, PREREQ_ID) VALUES ('CS-315', 'CS-190');
INSERT INTO PREREQ (COURSE_ID, PREREQ_ID) VALUES ('CS-319', 'CS-315');
INSERT INTO PREREQ (COURSE_ID, PREREQ_ID) VALUES ('CS-347', 'CS-319');
INSERT INTO PREREQ (COURSE_ID, PREREQ_ID) VALUES ('EE-181', 'PHY-101');
--开始递归查询，SQL标准中使用with recursive来定义递归视图，
--而在ORACLA和DB2中都要省略with后面的recursive关键字，而且要使用union all
--查询所有课程的所有先修课程
with rec_prereq(course_id, prereq_id) as (
		select course_id, prereq_id
		from prereq
	union all
		select rec_prereq.course_id, prereq.prereq_id
		from prereq, rec_prereq
		where prereq.course_id = rec_prereq.prereq_id
	)
select * from rec_prereq;
--查询某一门课程的所有先修课程
with rec_prereq(course_id, prereq_id) as (
		select course_id, prereq_id
		from prereq where course_id='CS-319'
	union all
		select rec_prereq.course_id, prereq.prereq_id
		from prereq, rec_prereq
		where prereq.course_id = rec_prereq.prereq_id
	)
select * from rec_prereq;