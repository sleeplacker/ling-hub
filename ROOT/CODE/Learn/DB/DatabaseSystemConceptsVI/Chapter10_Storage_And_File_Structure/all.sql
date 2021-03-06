---- 存储和文件结构 ----

--存储介质分类
1) 高速缓冲存储器
2) 主存储器-内存
3) 快闪存储器-U盘，固态硬盘
4) 磁盘存储器-硬盘
5) 光学存储器-光盘
6) 磁带存储器-磁带

--磁盘结构
磁道-盘面上一圈圈的
扇区-磁道中大小固定的一部分
柱面-多个磁盘片平行的磁道合在一起
读写头-悬浮在磁盘表面的小东西

--独立磁盘冗余阵列(Redundant Array of Independent Disk, RAID)
两点作用：
1) 通过冗余提高可靠性
2) 通过并行提高性能

RAID级别(详细参考第251页)：
0) RAID0级：无冗余拆分
1) RAID1级：镜像磁盘
2) RAID2级：内存风格的纠错码
3) RAID3级：位交叉奇偶校验
4) RAID4级：块交叉奇偶校验
5) RAID5级：块交叉的分布奇偶校验
6) RAID6级：P+Q冗余

使用最多的是RAID1级和RAID5级，许多实现并不支持RAID6级

--文件组织
块：每个文件可以分成定长的存储单元，成为块
定长记录：每个元组占固定字节数
变长记录：每个元组占不固定字节数

组织方式：
1) 堆文件组织：一条记录可以放在文件的任何地方，无序
2) 顺序文件组织：按某个搜索码顺序排序记录，搜索码可以是任何一个属性或属性集合，不必是主码或超码
3) 散列文件组织：在每条记录的某些属性上计算一个散列值
4) 多表聚簇文件组织：几个不同关系的记录存储在同一个文件中，对连接查询比较有用

数据字典：记录“关于数据的数据”，成为元数据。包括关系名，属性名，属性的域和长度，约束，授权信息等

--数据库缓冲区
将未来可能需要的数据从磁盘读到内存中，以便提高查询速度
缓冲区替换策略(各场景见第263页)：
1) 最近最少使用
2) 立即丢弃
3) 最近最常使用