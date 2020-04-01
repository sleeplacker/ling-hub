---- 集合运算 ----

--3条记录
select course_id from section where semester = 'Fall' and year = 2009;
--7条记录，有重复项
select course_id from section where semester = 'Spring' and year = 2010;

--并运算，查到8条记录，且没有重复项
(select course_id from section where semester = 'Fall' and year = 2009)
union
(select course_id from section where semester = 'Spring' and year = 2010);

--使用nion all保留重复项
(select course_id from section where semester = 'Fall' and year = 2009)
union all
(select course_id from section where semester = 'Spring' and year = 2010);

--交运算，也会自动去重复项
(select course_id from section where semester = 'Fall' and year = 2009)
in`tersect
(select course_id from section where semester = 'Spring' and year = 2010);

--使用intersect all保留重复项，ORACLE不支持intersect all，DB2支持，
--保留重复项的数量等于两个表中重复项数量较少的一个
(select course_id from section where semester = 'Fall' and year = 2009)
intersect all
(select course_id from section where semester = 'Spring' and year = 2010);

--差运算，返回except之前关系中出现，之后关系中不出现的元组
--ORACLE不支持，DB2支持
(select course_id from section where semester = 'Fall' and year = 2009)
except
(select course_id from section where semester = 'Spring' and year = 2010);

--差运算有方向，调换except前后的关系，结果不同
(select course_id from section where semester = 'Spring' and year = 2010)
except
(select course_id from section where semester = 'Fall' and year = 2009);

--差运算会自动去重，使用except all可以保留重复项
(select course_id from section where semester = 'Spring' and year = 2010)
except all
(select course_id from section where semester = 'Fall' and year = 2009);