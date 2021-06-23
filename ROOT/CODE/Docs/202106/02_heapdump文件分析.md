### 1 在 Linux/Unix 上生成 heapdump 文件

#### 1.1 HotSpot JVM

基于 HotSpot 的 Java 运行时只能够生成 HPROF 格式的转储文件。您可以选择以下几种交互方法和一种基于事件的方法来生成这个转储文件：

- 交互式方法：

  - **使用 Ctrl+Break**：如果运行的应用程序设置了 `-XX:+HeapDumpOnCtrlBreak` 命令行选项，那么在通过控制台发出 Ctrl+Break 事件或 `SIGQUIT`（通常通过 `kill -3` 生成），那么会生成一个 HPROF 格式的转储文件和一个线程 Dump。有一些版本不支持这个选项，那么在遇到这些情况时可以尝试使用：

    `-Xrunhprof:format=b,file=heapdump.hprof`

  - **使用 `jmap` 工具**：`jmap` 实用工具（见 [参考资料](https://www.oschina.net/action/GoToLink?url=http%3A%2F%2Fwww.ibm.com%2Fdeveloperworks%2Fcn%2Fjava%2Fj-memoryanalyzer%2Findex.html%3Fca%3Ddrs-%23resources)）位于 JDK 的 bin 目录，它提供了一种从运行中的进程请求一个 HPROF 转储文件的选项。在 Java 5 中，要使用：

    `jmap -dump:format=b *pid* 							`

    而在 Java 6 中，要使用以下命令，其中 `live` 是可选的，表示只返回正在写到转储文件进程 ID (PID) 的 “live” 对象：

    `jmap -dump[live,]format=b,file=*filename pid* 							`

  - **使用操作系统**：使用“无害”的 `gcore` 命令或破坏性的 `kill -6` 或 `kill -11` 命令来生成一个内核文件。然后，使用 `jmap` 从内核文件中提取一个堆转储文件：

    `jmap -dump:format=b,file=heap.hprof *path to java executable core* 							`

  - **使用 JConsole 工具：** `dumpHeap` 操作是基于 JConsole 的 `HotSpotDiagnostic` MBean 提供的。这个操作要求必须生成一个 HPROF Dump。

- 基于事件的方法：

  - **遇到 `OutOfMemoryError` 时：** 如果运行中应用程序设置了 `-XX:+HeapDumpOnOutOfMemoryError` 命令行选项，那么当出现 `OutOfMemoryError` 错误时就会有一个 HPROF 格式的转储文件生成。在生产系统中使用这种方法非常好，因为它几乎一直需要分析内存问题，并且它不会引起额外的性能开销。在旧版本的基于 HotSpot 的 Java 运行时中，每次 JVM 执行时这个事件所产生的堆转储文件数量并没有限制；而在新的版本中，每次 JVM 执行的事件所生成的堆转储文件具有一个最大值(-XX:HeapDumpPath=D:/dump/ 参数可以设置转存文件路径)。



#### 1.2 IBM JVM

IBM 运行时环境提供了转储文件和跟踪引擎，它们可以在众多交互式和基于事件的场景中生成 PHD 格式或系统转储文件。您也可以使用 Health Center 工具或使用 Java API 编程生成交互式转储文件。

- 交互式方法

  - **使用 `SIGQUIT` 或 Ctrl+Break：** 当向 IBM 运行时环境发送 Ctrl+Break 或 `SIGQUIT`（通常是使用 `kill -3` 生成）时，IBM 转储文件引擎中会生成一个用户事件。默认情况下，这个事件只会生成一个线程转储文件（javacore.txt）。您可以使用 `-Xdump:heap:events=user` 选项来生成 PHD 格式的转储文件，或者使用 `-Xdump:system:events=user` 选项生成一个 Java 应用程序的系统转储文件。
  - **通过操作系统生成一个系统转储文件：**
    - AIX：`gencore`（或者破坏性 `kill -6` 或 `kill -11`）
    - Linux/Solaris：`gcore`（或者破坏性 `kill -6` 或 `kill -11`）
    - Windows：`userdump.exe`
    - z/OS：`SVCDUMP` 或者控制台转储文件
  - **使用 IBM Monitoring and Diagnostics Tools for Java - Health Center：** Health Center 工具提供了一个菜单选项，可用来请求从一个运行的 Java 进程生成一个 PHD 或系统转储文件（见 [参考资料](https://www.oschina.net/action/GoToLink?url=http%3A%2F%2Fwww.ibm.com%2Fdeveloperworks%2Fcn%2Fjava%2Fj-memoryanalyzer%2Findex.html%3Fca%3Ddrs-%23resources)）。

- 基于事件的方法。IBM 转储文件和跟踪引擎提供了灵活的功能集，它们可以根据由所执行方法抛出的大量事件来生成 PHD 和系统 Dump。通过使用这些功能，您就能够为您希望分析的大部分问题场景生成转储文件。

  - **使用 IBM 转储文件引擎：** 转储文件引擎提供了大量您可用来生成一个 PHD 或系统转储文件的事件。而且，您可以用它来过滤这些事件类型，以便在生成转储文件时执行精细的控制。

    您可以通过 `-Xdump:what` 选项查看默认事件。例如，您会注意到，JVM 的前 4 个 `OutOfMemoryError` 异常会产生 heapdump.phd 和 javacore.txt 文件。

    为了收集更多的数据，您可以在 `OutOfMemoryError` 异常上生成一个系统转储文件，而非堆转储文件。

    `-Xdump:heap:none -Xdump:java+system:events=systhrow, filter=java/lang/OutOfMemoryError,range=1..4,request=exclusive+compact+prepwalk`

    有一些异常，如 `NullPointerException`，在大多数应用程序中可能会在许多处代码中发生，这使得它很难针对特定的 `NullPointerException` 生成转储文件。为了帮助您更明确转储文件所对应的异常，它在 “throw” 和 “catch” 事件上提供了额外的过滤机制，允许您分别指定抛出和捕捉方法。例如，下面这个选项会在 `bad()` 方法抛出一个 `NullPointerException` 异常时生成一个系统转储文件：

    `-Xdump:system:events=throw,       filter=java/lang/NullPointerException#com/ibm/example/Example.bad`

    当 `catch()` 方法捕捉到一个 `NullPointerException` 异常时，这个选项会产生一个系统转储文件：

    `-Xdump:system:events=catch,       filter=java/lang/NullPointerException#com/ibm/example/Example.catch`

    除了事件过滤之外，您还可以指定希望生成转储文件的事件范围。例如，下面这个选项只会在第五个 `NullPointerException` 异常上产生转储文件：

    `-Xdump:system:events=throw, filter=java/lang/NullPointerException,range=5`

    下面这个选项只会在第二、第三和第四个 `NullPointerException` 异常上产生转储文件：

    `-Xdump:system:events=throw, filter=java/lang/NullPointerException,range=2..4`

    表 2 列出了最有用的事件和过滤器：

    
    
    **表 2. 可用的转储文件事件**

| <span style="display:inline-block; width:40px">**事件**</span>         | <span style="display:inline-block; width:40px">**描述**</span>                                                         | <span style="display:inline-block; width:80px">**可用过滤**</span>         | <span style="display:inline-block; width:100px">**示例**</span>                                                         |
| ------------ | ------------------------------------------------------------ | ---------------- | ------------------------------------------------------------ |
| `gpf`        | 一般保护故障（崩溃）                                         |                  | `-Xdump:system:events=gpf`                                   |
| `user`       | 用户触发信息（`SIGQUIT` 或 Ctrl+Break）                      |                  | `-Xdump:system:events=user`                                  |
| `vmstop`     | VM 关闭，包括调用 `System.exit()`                            | 退出代码         | `-Xdump:system:events=vmstop,filter=#0..#10` 在 VM 关闭时生成带有 至 `10` 退出代码的系统转储文件。 |
| `load`       | 类加载                                                       | 类名称           | `-Xdump:system:events=load,filter=com/ibm/example/Example` 当 `com.ibm.example.Example` 类加载时生成一个系统转储文件。 |
| `unload`     | 类卸载                                                       | 类名称           | `-Xdump:system:events=unload,filter=com/ibm/example/Example` 当 `com.ibm.example.Example` 类卸载时生成一个系统转储文件。 |
| `throw`      | 抛出一个异常                                                 | 异常类名         | `-Xdump:system:events=throw,filter=java/net/ConnectException` 当 `ConnectException` 产生时生成一个系统转储文件。 |
| `catch`      | 捕捉到一个异常                                               | 异常类名         | `-Xdump:system:events=catch,filter=java/net/ConnectException` 当 `ConnectException` 被捕捉时生成一个系统转储文件。 |
| `systhrow`   | JVM 将抛出一个 Java 异常。（这与 `throw` 事件不同，因为它只能由 JVM 内部检测到的错误条件触发。） | 异常类名         | `-Xdump:system:events=systhrow,filter=java/lang/OutOfMemoryError` 当 `OutOfMemoryError` 产生时生成一个系统转储文件。 |
| `allocation` | 分配一个 Java 对象                                           | 所分配对象的大小 | `-Xdump:system:events=allocate,filter=#5m` 当分配大于 5MB 的对象时生成一个系统转储文件。 |


  - 使用 IBM 跟踪引擎：

    这个跟踪引擎允许 PHD 和系统转储文件由应用程序中任何 Java 方法进入或退出事件触发。您可以在控制 IBM 跟踪引擎的

     

    ```
    -Xtrace
    ```

     

    命令行选项中使用

     

    ```
    trigger
    ```

     

    关键字来实现这个配置。这个触发器选项的语法是：

    `method{*methods*[,*entryAction*[,*exitAction*[,*delayCount*[,*matchcount*]]]]}`

    将下面的命令行选项添加到应用程序上会在 `Example.trigger()` 方法被调用时产生一个系统转储文件：

    `-Xtrace:maximal=mt,trigger=method{com/ibm/example/Example.trigger,sysdump}`

    这个命令行选项会在 `Example.trigger()` 方法被调用时产生一个 PHD 转储文件：

    `-Xtrace:maximal=mt,trigger=method{com/ibm/example/Example.trigger,heapdump}`

    但是，推荐您设置一个范围，这样您就不会在每次这个方法被调用时都产生转储文件。下面这个例子会忽略 `Example.trigger()` 的前五次调用，然后触发产生一个转储文件：

    `-Xtrace:maximal=mt,trigger=method{com/ibm/example/Example.trigger,sysdump,,5,1}`

    注意这个例子中的

     

    ```
    exitAction
    ```

     

    使用了一个空项，因为我们只触发该方法的进入事件。

- **通过编程方法实现：**IBM 运行时环境还提供了一个 `com.ibm.jvm.Dump` 类及其 `javaDump(`)、`heapDump()` 和 `systemDump()` 方法。它们分别产生线程转储文件、PHD 转储文件和系统转储文件。

#### 1.3 格式

IBM JDK：.phd 或 .phd.hax

SUN JDK: .hprof 或 .hprof.hax

### 2 分析工具

#### 2.1 IBM 分析工具(TMDA)

下载地址：https://www.ibm.com/support/pages/ibm-heapanalyzer

下载内容：一个jar文件，ha*.jar，例如 ha457.jar

##### 2.1.1 运行jar包

在包含该jar包的目录运行下面命令

```shell
java -Xmx2g -jar ./ha457.jar
```



##### 2.1.2 打开文件和查看信息

打开文件方式类似上一篇的 JCA 工具，查看信息慢慢摸索。



#### 2.2 eclipse分析工具 Memory Analyzer

##### 2.2.1 下载

在 IBM TMDA工具的官网上，写了下面提示信息，也就是建议使用 Memory Analyzer 工具，确实 TMDA 工具不太好用

> **Note**: IBM HeapAnalyzer has no new development and therefore, in general, we recommend using the [Eclipse Memory Analyzer Tool (MAT)](https://www.eclipse.org/mat/) with [IBM DTFJ Extension](https://publib.boulder.ibm.com/httpserv/cookbook/Major_Tools-IBM_Memory_Analyzer_Tool.html#Major_Tools-IBM_Memory_Analyzer_Tool_MAT-Standalone_Installation) instead which is open source and has active development, a similar feature set (finding large dominators, leak suspects, etc.), and the IBM Extensions for Memory Analyzer with product-specific analysis engines.

下载地址：https://www.eclipse.org/mat/downloads.php

版本信息，包括下面3种版本：

- Update Site：在eclipse中输入URL进行下载
- Archived Update Site：eclipse离线插件包
- Stand-alone Eclipse RCP Applications：单独的安装包，不需要依赖eclipse

> ### Memory Analyzer 1.12.0 Release
>
> - Version
>
>   : 1.12.0.20210602 |
>
>    
>
>   Date
>
>   : 16 June 2021 |
>
>    
>
>   Type
>
>   : Released
>
>   - **Update Site**: http://download.eclipse.org/mat/1.12.0/update-site/
>   - **Archived Update Site**: [MemoryAnalyzer-1.12.0.202106020830.zip](http://www.eclipse.org/downloads/download.php?file=/mat/1.12.0/MemoryAnalyzer-1.12.0.202106020830.zip)
>   - **Stand-alone Eclipse RCP Applications**
>     [![img](https://www.eclipse.org/mat/home/icon-save.gif)](http://www.eclipse.org/downloads/download.php?file=/mat/1.12.0/rcp/MemoryAnalyzer-1.12.0.20210602-win32.win32.x86_64.zip)  [Windows (x86_64)](http://www.eclipse.org/downloads/download.php?file=/mat/1.12.0/rcp/MemoryAnalyzer-1.12.0.20210602-win32.win32.x86_64.zip)
>     [![img](https://www.eclipse.org/mat/home/icon-save.gif)](http://www.eclipse.org/downloads/download.php?file=/mat/1.12.0/rcp/MemoryAnalyzer-1.12.0.20210602-macosx.cocoa.x86_64.dmg)  [Mac OSX (Mac/Cocoa/x86_64)](http://www.eclipse.org/downloads/download.php?file=/mat/1.12.0/rcp/MemoryAnalyzer-1.12.0.20210602-macosx.cocoa.x86_64.dmg)
>     [![img](https://www.eclipse.org/mat/home/icon-save.gif)](http://www.eclipse.org/downloads/download.php?file=/mat/1.12.0/rcp/MemoryAnalyzer-1.12.0.20210602-linux.gtk.x86_64.zip)  [Linux (x86_64/GTK+)](http://www.eclipse.org/downloads/download.php?file=/mat/1.12.0/rcp/MemoryAnalyzer-1.12.0.20210602-linux.gtk.x86_64.zip)
>     [![img](https://www.eclipse.org/mat/home/icon-save.gif)](http://www.eclipse.org/downloads/download.php?file=/mat/1.12.0/rcp/MemoryAnalyzer-1.12.0.20210602-linux.gtk.ppc64le.zip)  [Linux (PPC64le/GTK+)](http://www.eclipse.org/downloads/download.php?file=/mat/1.12.0/rcp/MemoryAnalyzer-1.12.0.20210602-linux.gtk.ppc64le.zip)

**注意：各个版本的 Memory Analyzer 需要依赖的jdk版本不一样，如果jdk版本不匹配，启动 Memory Analyzer 时会报错**。



##### 2.2.2 IBM 插件(DTFJ)安装

这里以安装版本(Stand-alone Eclipse RCP Applications)为例，首先解压 MemoryAnalyzer-1.10.0.202002252112.zip，双击 MemoryAnalyzer.exe，然后点击右上角的 help->Install new Softwar，然后输入下面这个URL进行插件安装。

http://public.dhe.ibm.com/ibmdl/export/pub/software/websphere/runtimes/tools/dtfj/



