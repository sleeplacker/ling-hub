---- 完整性约束 ----

--单个关系上的约束，包括3种：
--	1) not null
--	2) unique
--	3) check(<谓词>)

--not null不用说
--unique可以指定多个属性来形成候选码
--check可以指定属性必须满足的条件
--当在department表增加下面的约束，然后视图将某个部门
--的预算更新成0 就会出现完整性约束的报错
alter table department add constraint budget_active check(budget > 0);

--在默认情况下，当违反完整性约束的操作发生时，会让该操作失败
--但是foreign key的外码约束可以指定在依赖的外码被更新或删除时
--级联更新或删除参照关系中的关联属性，详细内容参见第74页

--空值null的参照约束：当参照关系中的外码列为null时，被认为是满足参照关系的。
--例如，instructor表中的dept_name是依赖department表的dept_name属性的外码，
--那么当向instructor表中插入dept_name列是null值时，被认为是满足参照完整性的

--断言不推荐使用，相关内容参考第75页