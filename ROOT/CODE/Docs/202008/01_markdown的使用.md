### 目录
[TOC]


# 1 基础语法

## 1.1 字体

1. 标题：#+空格+标题内容，最多6级标题
示例：
> # 我是一级标题
> ## 我是二级标题

2. 粗体：\*\*+内容+\*\*
示例：
> **我是粗体**

3. 斜体：\*+内容+\*
示例：
> *我是斜体*

## 1.2 列表

1. 无序列表：-+空格+列表内容
示例：
> - 我是无序列表
> - 我也是无序列表

2. 有序列表：数字+.+列表内容
示例：
> 1. 我是有序列表
> 2. 我是有序列表

3. 列表层级：TAB键+下一层列表
示例：
> - 我是第一层无序列表
> 	- 我是第二层无序列表
> 	1. 我是第二层有序列表
> 	2. 我也是第二层有序列表
> 		1. 我是第三层有序列表
> 			- 我是第四层无序列表
> 		- 我是第三层无序列表

## 1.3 分割线
3个以上的\* / \- / \_，使用减号时，中间必须有空格
示例：

> 星号分割线
> 上面内容
> ***
> 下面内容
>
> 下划线分割线
> 上面内容
> ___
> 下面内容
>
> 减号分割线
> 上面内容
>
> - - -
> 下面内容

## 1.4 图片

1. 引用方式：\!\[找不到图片时的描述](图片地址)，图片路径可以是相对路径，绝对路径，以及URL
示例：
> 相对路径引用图片
> ![图片挂了](../static/image/markdown.png)
> 
> 绝对路径引用图片
> ![图片挂了](I:\MyGitLib\repository\ling-hub\ROOT\CODE\Docs\static\image\markdown.png)
> 
> URL方式引用图片
> ![图片挂了](https://www.runoob.com/wp-content/uploads/2019/03/iconfinder_markdown_298823.png)


2. Base64方式
![图片挂了][imgBase64Str]

## 1.5 链接

1. 文字链接：\[\]中是j要显示的文字，\(\)中是实际包含的文字
示例：
> [GitHub](https://github.com)

2. 引用链接：第一个\[\]中是要显示的文字，第二个\[\]中是引用的变量
> [谷歌][Google]

3. 网址链接：包含在\<\>中的内容被视为超链接
> <https://www.baidu.com>
> 
> 注意：typora预览时要按住ctrl点击才会跳转URL

## 1.6 代码

1. 行内代码：将代码放在``之间
示例：
> java代码 `System.out.println("Hello md!")` 会打印信息到控制台


2. 多行代码：将代码放在两个```之间(GFM扩展语法)，且可以选择语言，这里选择的java
示例：
> ```java
> package com.lg.test;
> 
> public class HelloMD {
> 	public static void main(String[] args) {
> 		System.out.println("Hello md !");
> 	}
> }
> ```
>

## 1.7 引用

语法：>+空格+引用内容，可以多层引用
示例：

> 第一层引用
>
> > 第二层引用

## 1.8 转译
语法：\\+被转译的字符
示例：

> 想打"\*\* 加油 \*\*"这句话
> 直接打的效果：**加油** (\*\*被识别成了加粗操作)
> 对\*进行转译后的效果：\*\* 加油 \*\*



# 2 扩展语法GFM

## 2.1 删除线

语法：\~\~被删除的文字\~\~
示例：

> ~~我被删除了~~

## 2.2 表格
使用竖线分割表格
示例：

> **默认向左对齐(:-)**
>
> |姓名|手机号|身份证号|
> |-|-|-|
> |爱丽丝|13923472222|440801199110262222|
> |鲍勃|13923473333|440801199110263333|
>
> **居中：第二行使用:-:**
> |姓名|手机号|身份证号|
> |:-:|:-:|:-:|
> |爱丽丝|13923472222|440801199110262222|
> |鲍勃|13923473333|440801199110263333|
>
> **向右对齐：第二行使用-:**
>
> |姓名|手机号|身份证号|
> |-:|-:|-:|
> |爱丽丝|13923472222|440801199110262222|
> |鲍勃|13923473333|440801199110263333|
>



控制表格列宽

> 默认情况下是自适应
>
> | **序号** | **字段名** |
> | ------------------------------------------------------------ | ------------------------------------------------------------ |
> | 1                                                            | Param1                                                 |

>
> 使用span标签设置列宽，这里设置第一列的宽度为500px
> | <span style="display:inline-block; width:500px">**序号**</span> |**字段名** |
> | ------------------------------------------------------------ | ------------------------------------------------------------ |
> | 1                                                            | Param1                                                       |





## 2.3 任务列表

语法：-+空格+[ ]表示未勾选，将括号中的空格改为x表示已勾选
示例

> 今日计划
> 
> - [x] 市场买菜
> - [ ] 买自行车
> - [ ] 上岛

## 2.4 锚点
语法：\[显示字符\]\(\#要跳转位置标题\)
示例：

> 要了解代码围栏示例请看 [代码围栏示例](#1.6 代码)
> 注意：typora预览时要按ctrl+点击才能跳转到指定位置

## 2.5 表情符号
语法：:单词:
示例：

> :smile:
> :cry:
> :baby:

# 3 typora的使用以及扩展语法
## 3.1 导入和导出
不安装插件只能导出pdf和html，安装Pandoc后可以导入和导出更多格式

## 3.2 扩展语法

1. 下划线
> <u>我有下划线</u>


2. 内联数学公式：\$公式\$
>	- 分数：$\frac{x^2}{y^2}$
>	- 开根号：$\sqrt[n]{{x^2}{y^3}}$
>	- 省略号：1, 2, 3, $\cdots$ , n


3. 上标和下标

> 上标：n^2^, n^c^
> 下标：a~0~, a~n~


4. 高亮

> 我没高亮
> ==我高亮了==


5. 注释

> <!-- 我是注释 -->

6. 目录
> typora可以自动生成目录，且会随着文档的编写自动更新，请看[目录](#目录)


7. 脚注
> 我有个脚注[^1]

***
到底了
***

[^1]:我是被引用的脚注内容
[Google]:https://www.google.com	"gg"
[imgBase64Str]:data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACACAYAAADDPmHLAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEwAACxMBAJqcGAAABPpJREFUeJzt3UuIHFUUxvF/9wzKaBaGMDOgiGIWoiKYB74QzMLHRl3owoUKAz4WBrNS3LhTF4roRmYnmclOjLpSdKEJuBExJgGThZIgOBEzJL6gGY2YcnG7tajpnunqOlWnqu73gwuZyfS9p29/dN3qejSIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiEhTdUb8fgfwOHAPcDWwtbKKxNIacBo4DOwHjmz2gFngXSBRa2X7AJhjhBuAH2pQpFq5bQW4kb7BJmAW+Aq4FonBGWAXcLbb/8Xb6MWPyVXAIoR3gB2ExcGoBaG0164p4AXgTu9KxMWFLmFXT+K0pwP8gvbzY9XrEHYNJFLdzf9E2kwBiJwCEDkFIHIKQOSmvQtwkN3r2eznqRJrcWfxDtAp2JYNahhlech43UybyrTpTCuq6PyUrujhxaIuA04Y1JFtJ/p9F+U9P2UfHnZ/ghCOT/cMn1SP1DHvgrznJ4oAACwYPqkFw7q85yeaAAAsGdSzZFyT9/xEFYCi6wGr7X6a9/xEFQCYfD1gud1P856f6AIAk60HFkqqxXt+ogwA5FsPLJVYh/f8RBuAcdcDZWz307znJ9oAwObrgbK2+2ne8xN1AGDj9cBCBeN7z0/0AYDh64Glisb2nh8FgPXrgbK3+2ne86MA9A3WA1Vs99O856fUADTpfICTwN7Uv8WAxWnhbb+kzHt+Sn2X1SlhkVMAIqcARE4BiJwCEDkFIHIKQOTqEIAnS+z7qRL6fIxqz+vPM9YTkwzg/VHnGuGOVdZ2A38a9JN9vr8B1xj0a+064A8aeCwgIdyfcJtBXwPb+P+eh0UNe85fUK9LxqaBL2nowaBBP59gs0nqAp9iX1+2vWTQt5VXKfD61SUACfCyQX+vUF596fY3cKtB/0XdDfxDSwJwEXigQF8P9vuoIgAJ8D2wxWCMSW0FfhxSV2MDkAC/Atsn6Gd7/7Fl15dt7xiMMamDG9TV2AAkwDFgJkcfM8DxCuvLtkcMxsnr6TFra2QAEuBAjj4OONSXbucJ99+tyvXYXU1d2wAkwLNjPH6vY33p9hnVnBxzCfBNjroaHYC/gNs2eOzt/b+pQwAS4HmD8TbzZs6aGh2AhLDKnR3yuDnClx9415cN7C0GY45yP+v3clofgITw9pr+5G0K+LxG9aXbSfItYMc1B/w8QT2tCEACvJZ6zOs1rC/dFg3GzfpowlpaE4AEeLjf6lpfuhX5QCtrX4E6WhWA38l/xKvK+tJtFZg3GP9mwlFNBaCB9X1ccOwZ4NuCNSgAzvU9V2DsRYPxFQDn+taAmyYY9yGDsRWAmtR3HLg0x5hXAueMxlYAalLfW2OO1yV87lHFi68AVFjfReDeMcZ70XBMBaBm9f3Exuc+7gYuGI+pAFDslvHW9Xw4Yh62AN9VMB/mAVDL355hvf1OtbhPRoytRzipY+BRr1r0xZF+jgB3EHb5jgFXeBShAPh6g3BSy11eBSgAkavDxaHiSAGIXJdwsELi1OsCp72rEDenusBh7yrEzaEO4eYMX3tXIi52dgkfSIz6fFra6yBwdHAp0zwhCFVe3yZ+Vgjv/KuD3cCzwH3AGbeSpCorhNd6ddh/zgPv43+wRK2c9h7hCqP/jLqadSfh+3j2EO4+dfmIv5N66wGngEPAMnDUtxwREREREREREREREREREREREREREREREbH0L2CGRcREr3u5AAAAAElFTkSuQmCC



# 4 附录

## A 符号代码

![Symbols1](../static/image/Symbols1.png)

![Symbols2](../static/image/Symbols2.png)

![Symbols3](../static/image/Symbols3.png)

![Symbols4](../static/image/Symbols4.png)