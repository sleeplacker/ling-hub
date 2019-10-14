#测试三：不指定classpath运行class文件，会默认从当前目录开始查找class文件，相当于执行了：set classpath=.
#先将当前目录下建文件夹：.\com\ling\learn0408\，再将searchclassSearchClassTest.class放到该目录下，再运行脚本
java com.ling.learn0408.searchclass.SearchClassTest
