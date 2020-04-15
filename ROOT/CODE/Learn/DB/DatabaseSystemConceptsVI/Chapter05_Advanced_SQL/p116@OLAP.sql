---- OLAP(联机分析处理) ----

--创建交叉表，ORACLE支持，DB2不支持
select * from sales
pivot (
	sum(quantity)
	for color in ('dark', 'pastel', 'white')
)
order by item_name;
--结果：
--ITEM_NAME CLOTHES_SIZE 'dark' 'pastel' 'white' 
----------- ------------ ------ -------- ------- 
--dress     large        12     3        0       
--dress     medium       6      3        3       
--dress     small        2      4        2       
--pants     large        0      1        2       
--pants     medium       6      0        0       
--pants     small        14     1        3       
--shirt     large        6      2        10      
--shirt     medium       6      1        1       
--shirt     small        2      4        17      
--skirt     large        1      15       3       
--skirt     medium       5      9        5       
--skirt     small        2      11       2  

--按多个字段分组
select item_name, color, sum(quantity)
from sales
group by item_name, color;
--结果：
--ITEM_NAME COLOR  SUM(QUANTITY) 
----------- ------ ------------- 
--dress     dark   20            
--skirt     pastel 35            
--dress     white  5             
--shirt     white  28            
--skirt     dark   8             
--skirt     white  10            
--dress     pastel 10            
--shirt     dark   14            
--pants     pastel 2             
--pants     dark   20            
--shirt     pastel 7             
--pants     white  5             

--cube结构，产生数据立方体
--3维，产生8个分组
select item_name, color, clothes_size, sum(quantity)
from sales
group by cube(item_name, color, clothes_size);
--2维
select item_name, color, sum(quantity)
from sales
group by cube(item_name, color);
--使用decode和grouping函数将null替换为all
select 
	decode(grouping(item_name), 1, 'all', item_name) as item_name,
	decode(grouping(color), 1, 'all', color) as color,
	sum(quantity) as quantity
from sales
group by cube(item_name, color);

--rollup结构，属性有顺序之分，适合描述层次结构
--3维，只产生4个分组
select item_name, color, clothes_size, sum(quantity)
from sales
group by rollup(item_name, color, clothes_size);
--使用多个rollup或cube
--下面可以产生6个分组，详细参考第118页
select item_name, color, clothes_size, sum(quantity)
from sales
group by rollup(item_name), rollup(color, clothes_size);