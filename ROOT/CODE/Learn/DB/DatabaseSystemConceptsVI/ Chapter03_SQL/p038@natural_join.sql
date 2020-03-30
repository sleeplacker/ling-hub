---- 自然连接查询 ----

--查詢表table1和表table2中相同字段名值相同的记录，
--如果两个表中有多个字段名相同，那么这些字段值都要相同的记录才会被返回
select * from table1 natural join table2;

--自然连接的结果可以和另外的表做笛卡尔积
select * from table1 natural join table2, table3;

--使用join ... using指定只需column1, column2两个同名属性值相同就返回该记录
select * from table1 join table2 using(column1, column2);