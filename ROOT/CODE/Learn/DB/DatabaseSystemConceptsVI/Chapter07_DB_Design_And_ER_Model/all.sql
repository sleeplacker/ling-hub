---- 数据库设计和E-R模型 ----

实体-联系(Entity-Relationship， E-R)模型是一个用于数据库设计的数据模型，它提供了一个方便的图形化表示方法以查看数据、联系和约束。

实体：现实世界中存在且区别于其他对象的对象
实体集：相同类型的实体的集合

弱实体集：不具有足够属性构成主码的实体集
强实体集：具有主码的实体集

联系：多个实体间的关联
联系集：相同类型的联系的集合
角色：实体在联系中扮演的功能
联系集的度：参与联系集的实体集的数目
类型包括：
	二元联系集
	多元联系集

属性种类：
	简单属性，不能划分为更小的部分
	复合属性：可以划分为更小部分
	单值属性：只能有一个值
	多值属性：可以有零或多个值
	派生属性：可以从别的属性或实体派生出来

映射基数：通过联系集可以和另一实体相关联的实体的个数
类型包括：
	一对一关系
	一对多(多对一)关系
	多对多关系
	
参与：实体集参与联系集
类型包括：
	全部参与：实体集中的每个实体都参与到了关系中
	部分参与：实体集中部分实体参与到了关系中
	
E-R图画法：参考第155页

E-R图转换为关系模式：详细参考第159页
模式的冗余：一般情况下，连接弱实体集与其所依赖的强实体集的联系集的模式是冗余的，可删除
模式的合并：
	映射基数为一对一时，联系集的关系模式可以和参与联系集的任何一个实体集的模式合并
	映射基数为一对多或多对一时：联系集的关系模式可以和多那方的实体集的模式合并
	
特化：取出高层实体集的一个子集来形成一个底层的实体集，类似面向对象中从父类继承的子类
概化：用两个或多个不相交的底层实体集的并集形成一个高层实体集，类似面向对象中从子类抽象出父类
重叠特化：一个实体属于多个特化实体集
不相交特化：一个实体至多属于一个特化实体集
聚集：联系集和关联的所有实体集被看成一个整体的实体集，可以参与到其他联系中

UML：一种常用的建模语言

吞吐量(throughput)：每个单位时间里能够处理的查询或更新的平均数量
响应时间(response time)：每个事务从开始到结束所需的平均时间或者最长时间