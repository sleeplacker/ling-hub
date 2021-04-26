## 1 下载

### 1.1下载地址：https://manjaro.org/download/

### 1.2 桌面版本选择

可以在下载地址后面加上桌面版本

- gnome：https://manjaro.org/download/#gnome

- kde：https://manjaro.org/download/#kde-plasma

- xfce：https://manjaro.org/download/#

  本次选择的是kde版本

## 2 系统安装

### 2.1 将Manjaro写入U盘

使用Rufus工具将Manjaro系统的iso文件进行写入U盘，选择DD模式写入，如果没有这个选项就不用选，软件会进行智能选择。



### 2.2 重启系统选择U盘启动

进入Manjaro界面，选择一下地区和语言，然后选择驱动选择free/no free都行，看机器硬件，通常选free就行。然后就会进入Manjaro系统。

### 2.3 开始安装系统

经过步骤2.2进入Manjaro系统后，其实已经可以使用系统了，只不过每次要插U盘用，所以要安装到硬盘上。这时选运行安装器进入安装流程。

#### 2.3.1 选择地区语言等



#### 2.3.2 重点，一定要选择手动分区

- 安装前需要在硬盘上留出空闲空间。

- 只要分出下面分区就行

  - swap：8G
  - /boot：2G
  - /：占空闲空间的30%左右
  - /home：剩下的空闲空间

- 挂载引导分区

  选择原来安装win10时创建的引导分区(格式为FAT16或者FAT32的分区)，然后点"编辑"，按下图方式进行挂载，必须选择 **keep** ，否则原来的win10系统无法引导，挂载点选择 **/boot/efi** 然后一直下一步安装就完成了。

  ![ManjaroBoot](../static/image/ManjaroBoot.png)

## 3 软件安装与配置

### 3.1 源配置

#### 3.1.1 添加国内源(配置/etc/pacman.d/mirrorlist文件)

- 先备份：`cp mirrorlist mirrorlist.bak`

- 在 mirrorlist 文件最前面添加如下内容(如果文件后面有重复内容就删除)：

  ```properties
  #### manjaro稳定源
  ## 中科大
  Server = https://mirrors.ustc.edu.cn/manjaro/stable/$repo/$arch
  
  ##  清华大学
  Server = https://mirrors.tuna.tsinghua.edu.cn/manjaro/stable/$repo/$arch
  
  ## 上海交通大学
  Server = https://mirrors.sjtug.sjtu.edu.cn/manjaro/stable/$repo/$arch
  
  ## 浙江大学
  Server = https://mirrors.zju.edu.cn/manjaro/stable/$repo/$arch
  
  #### archlinux源
  # 清华大学
  Server = https://mirrors.tuna.tsinghua.edu.cn/archlinux/$repo/os/$arch
  ## 163
  Server = http://mirrors.163.com/archlinux/$repo/os/$arch
  ## aliyun
  Server = http://mirrors.aliyun.com/archlinux/$repo/os/$arch
  
  
  ```

#### 3.1.2 添加中文社区仓库(配置/etc/pacman.conf 文件)

- 先备份：`cp pacman.conf pacman.conf.bak`

- 在 pacman.conf 文件末尾添加如下内容：

  ```properties
  [archlinuxcn]
  # The Chinese Arch Linux communities packages.
  # SigLevel = Optional TrustedOnly
  SigLevel = Optional TrustAll
  # 官方源
  #Server   = http://repo.archlinuxcn.org/$arch
  # 163源
  #Server = http://mirrors.163.com/archlinux-cn/$arch
  # 清华大学
  Server = https://mirrors.tuna.tsinghua.edu.cn/archlinuxcn/$arch
  
  ```

#### 3.1.3 手动更改源排名

`sudo pacman-mirrors -i -c China -m rank`

#### 3.1.4 更多参考下面链接

https://blog.csdn.net/weixin_43968923/article/details/86349914



### 3.2 安装 yay

`sudo pacman -S yay`







