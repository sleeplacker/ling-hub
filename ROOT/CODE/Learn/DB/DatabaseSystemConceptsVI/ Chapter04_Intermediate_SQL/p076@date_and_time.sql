---- 日期和时间类型 ----

--DB2中的假表
select * from sysibm.sysdummy1;
--ORACLE中的假表
select * from dual;

--current_date-当前日期(带时区)
--DB2返回日期：2020-04-07
select current_date from sysibm.sysdummy1;
--ORACLE返回日期和时间：2020-04-07 23:04:48
select current_date from dual;

--current_time-当前时间(带时区)
--DB2返回时间：23:10:20
select current_time from sysibm.sysdummy1;
--ORACLE报错：找不到标识符current_time
--select current_time from dual;

--current_timestamp-当前时间戳
--DB2返回时间戳：2020-04-07 23:11:38
select current_timestamp from sysibm.sysdummy1;
--ORACLE返回时间戳：2020-04-07 23:11:53
select current_timestamp from dual;

--localtime和localtimestamp
--DB2两个都不支持
--select localtime from sysibm.sysdummy1;
--select localtimestamp from sysibm.sysdummy1;
--ORACLE只支持localtimestamp
--select localtime from dual;
select localtimestamp from dual;

--使用extract(field from d)获取日期和时间中单独的域
--可以获取year、month、day、hour、minute、second；
--以及timezone_hour和timezone_minute可以获取时区信息
--DB2不支持时区信息：2020年 4月 7日 23时 34分 29.487000秒
select 
	extract(year from current_timestamp)||'年'||' '||
	extract(month from current_timestamp)||'月'||' '||
	extract(day from current_timestamp)||'日'||' '||
	extract(hour from current_timestamp)||'时'||' '||
	extract(minute from current_timestamp)||'分'||' '||
	extract(second from current_timestamp)||'秒'
from sysibm.sysdummy1;
--ORACLE：2020年 4月 7日 15时 32分 6.463秒 8时区小时 0时区分钟
select 
	extract(year from current_timestamp)||'年'||' '||
	extract(month from current_timestamp)||'月'||' '||
	extract(day from current_timestamp)||'日'||' '||
	extract(hour from current_timestamp)||'时'||' '||
	extract(minute from current_timestamp)||'分'||' '||
	extract(second from current_timestamp)||'秒'||' '||
	extract(timezone_hour from current_timestamp)||'时区小时'||' '||
	extract(timezone_minute from current_timestamp)||'时区分钟'
from dual;

--时间间隔interval类型
--DB2日期加减：11106，意思是相隔1年11个月6天
select (current_date - '2018-05-01') from sysibm.sysdummy1;
--DB2时间加减：11106135247.502000，意思是相隔1年11个月6天13小时52分47秒零0.50200秒
select (current_timestamp - '2018-05-01 10:00:00') from sysibm.sysdummy1;
--ORACLE日期加减：707.998553240740740740740740740740740741，单位是天
select (current_date - to_date('2018-05-01','YYYY-MM-DD')) from dual;
--ORACLE时间加减：707 13:58:57.271，单位分别是天/时/分/秒/毫秒
select (current_date - to_date('2020-01-01','YYYY-MM-DD')) from dual;