# 网络教学信息统计系统使用功能

## 开发原因

为了方便老师统计每个班学生的在线学习的教学进度和缺席情况。我们开发出了这样一个web项目，以解决这个痛点。

## 开发技术

本项目使用的是前后端分离的开发方式，既便于团队开发合作，又便于提高开发进度，修改bug

* **后端**：后端我们主要使用的是spring框架，将对数据、业务、前端链接的分层操作，数据层使用的是mysql连接。
* **前端**：前端采用bootstraps的栅格系统实现了网页的响应式布局，对移动端和电脑端都比较友友好，同时利用bootstraps框架，大大简化了代码，避免了重复的劳动，让代码更加的简介；同时我们的网页交互使用ajax，按需取数据，使交互更加的快捷，优化了用户体验。

## 实现功能

1. 教师通过自己的工号，进行登录和注册

   **登录**![image-20200502211930262](https://raw.githubusercontent.com/Bogdanxin/cloudImage/master/20200502212437.png)

   **注册**

   ![image-20200502212055838](https://raw.githubusercontent.com/Bogdanxin/cloudImage/master/20200502212057.png)

2. 教师注册后，可以进行班级的添加、学生的添加（学生的添加也可以直接使用excel表进行添加）

   ![image-20200502212419783](https://raw.githubusercontent.com/Bogdanxin/cloudImage/master/20200502212421.png)

3. 进入到教师所管理界面，可以查看本周所教课班级的课程表，也可以进行添加课程

   ![image-20200502212753984](https://raw.githubusercontent.com/Bogdanxin/cloudImage/master/20200502212755.png)

4. 也可以对学生未签到情况进行一个统计，并计算出学生的出勤率

   ![image-20200502212830271](https://raw.githubusercontent.com/Bogdanxin/cloudImage/master/20200503083758.png)

5. 个人中心，会展示该教师相关班级的信息、课程完成情况和学生相关信息

   ![image-20200502213128989](https://raw.githubusercontent.com/Bogdanxin/cloudImage/master/20200503083750.png)


