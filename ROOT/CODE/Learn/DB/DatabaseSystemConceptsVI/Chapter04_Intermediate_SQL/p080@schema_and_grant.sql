---- 模式和授权 ----

--1. 模式、目录和环境
--数据库的层次结构：目录(catalog) 包含 模式(schema) 包含 表(table)等
--环境：包括默认目录、默认模式和用户标识

--2. 授权
--权限的授予
--授予查询权限
grant select on student to scott;
--授予更新权限，可以指定属性列表，只有授予权限的属性列表才能被更新
grant update(tot_cred) on student to scott;
--授予插入权限，同样可以指定属性列表
--ORACLE可指定属性类别
grant insert(ID, name, dept_name) on student to scott;
--而DB2不行
grant insert on student to scott;
--授予删除权限
grant delete on student to scott;

--权限的回收
--收回查询权限
revoke select on student from scott;
--收回更新权限，规范规定可以指定属性列表，但是ORACLE和DB2都不支持
revoke update on student from scott;
--收回插入权限，规范规定可以指定属性列表，但是ORACLE和DB2都不支持
revoke insert on student from scott;
--收回删除权限
revoke delete on student from scott;

--角色
--创建角色
create role instructor;
--将权限授予给角色
grant select on takes to instructor;
--角色可以授予给用户，也可以授予给其他角色
grant dean to Amit;
create role dean;
grant instructor to dean;
grant dean to Satoshi;

--视图的授权
--首先创建视图
create view geo_instructor as (select * from instructor where dept_name='Ceology');
--视图的授权和关系的授权语法一样
grant select on geo_instructor to scott;
--视图授权有限制，参考第83页

--模式的授权
--只有模式的拥有者才能够执行对模式的任何修改，
--诸如创建和删除关系，增加或删除关系的属性，以及增加或删除索引
--references授权，可以控制外码对其他关系操作的限制
grant references(dept_name) on department to scott;

--权限的转移
--with grant option表示运行该权限被转移
grant select on department to scott with grant option;
--授权链或者授权图参考第84页，
--如果一个用户获得了多个相同授权，
--那么当其中一个授权被收回后，该用户仍然具有该权限

--权限从权限链中收回
--默认情况下会级联收回，可以使用restrict来防止级联收回，
--然而DB2和ORACLE都不支持该关键字
--revoke select on department from scott restrict;

--收回权限转移权限
--然而DB2和ORACLE都不支持该关键字
--revoke grant option for select on department from scott;

-- 为了避免权限被级联收回，可以从角色授予权限， 这样当授予权限的用户的权限被回收，
--被授权的用户权限不会被回收，具体参考第84页