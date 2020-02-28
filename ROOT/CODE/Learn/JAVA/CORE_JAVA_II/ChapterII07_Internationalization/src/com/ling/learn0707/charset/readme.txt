1. 日志文件：如果使用java.util.logging库中的日志消息，那要使用file.encoding系统属性来设置平台编码，java -Dfile.encdoing=UTF-8 MyProg

2. java编译和运行时涉及的编码：
	源文件：本地编码
	类文件：modified UTF-8(不是一般的UTF-8)
	虚拟机：UTF-16
	
3. 执行java命令时，可以指定编码，比如：编译UTF-8格式的源码时，应该这样执行：javac -encoding UTF-8 MyFile.java

4. native2ascii命令，可以将本地编码文件转换为ASCII码格式的文件，不在ASCII码范围的字符会被转换为\u加4位十六进制数字
	native2ascii Myfile.java Myfile.temp
	native2ascii -reverse Myfile.temp Myfile.java
	native2ascii -encoding UTF-8 Myfile.java Myfile.temp