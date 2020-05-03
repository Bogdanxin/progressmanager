# API

## Tips：

* url ： host + uri
  * exampl :
    + host : http://182.92.121.195:8080/
    + uri : /login
    + url : http://182.92.121.195:8080/login.html

## 1.用户User

user重要的请求就三个 login  register   exit

**tips：**

* userType :  
  + 0 = 注册用户，只能看到自己课程进展 
  + 1 = 管理用户，能看到所有老师的课程进展和所有学生信息

### 1.1 注册 

* POST  /register

* payload:

  ```json
  {
  	"teacherId":14,
  	"userName":"李四",
  	"password":"1234567890qwe",
  	"userAcademy":"信息学院(这个默认可以不填)",
  	"token":"",
  	"userType":0
  }
  ```

* return:

  ```json
  {
      "code": 0,
      "message": "添加成功！"
  }
  ```

* ps：注册都是将用户的userType设置为0，特殊情况再说

### 1.2 退出登录 

* POST  /exit/{token}

* request中带有token，就是既在路径上有token，也要在request的header中带有token

  例如：

  <img src="https://i.loli.net/2020/04/07/x36Lut4QXo2AZ7w.png" alt="image-20200316145116651"  />

* return 

  ```json
  {
      "code": 0,
      "message": "退出成功！"
  }
  ```




### 1.3 登录

* GET  /login

* payload：

  ```json
  {
      "teacherId":13434,
      "password":"1234567890"
  }
  ```

  

* return:

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": {
          "userType": 0,
          "userName": "张三",
          "userId": 2,
          "token": "8221ef60a5c84142ae58f8aaed6eca12
      }
  }
  ```


### 1.4 删除指定Id用户

* DELETE  /deleteUserById?id=2

* payload: request的header带有token

* return:

  ```json
  {
      "code": 0,
      "message": "删除成功！"
  }
  ```



### 1.5 获取指定id的用户信息

* GET  /getUserById?id=3

* request

* return:

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": {
          "teacherId": 14,
          "userType": 0,
          "userName": "李四",
          "userId": 3,
          "userAcademy": "信息学院",
          "token": "fsdf2343sdfsdflj434jl"
      }
  }
  ```

  

## 2.课程Course

课程类是你提到的班级的课表中的内容

###　2.1 添加一个课程

* POST  /addCourse

* payload:

  request

  这样的一个json就是课表中的一个课程。要添加多个就要多次请求

  ```json
  {
  	"courseName":"c语言",
  	"courseIntroduction":"课程简介",
  	"courseHours":43,
  	"courseFinishHours":0,
  	"classId": 2
  }
  ```

* return:

  ```json
  {
      "code": 0,
      "message": "添加成功！"
  }
  ```

  

### 2.2 删除指定id的课程 

* DELETE  /deleteCourseById?courseId=4

* payload：token

* return:

  ```json
  {
      "code": 0,
      "message": "删除成功！"
  }
  ```

  

### 2.3 修改指定id的课程信息

* POST  /updateCourseById?courseId=1 

* token 

* payload:

  ```json
  {
  	"courseName":"高数",
  	"courseHours":47,
  	"courseIntroduction":"简介"
  }
  ```

* return:

  ```json
  {
      "code": 0,
      "message": "修改成功!"
  }
  ```

  

### 2.4 获得指定courseId课程的课程信息

* GET  /getCourseById?courseId=1

* payload request

* return

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": {
          "courseName":"c语言",
          "courseIntroduction":"课程简介",
          "courseHours":43,
          "courseFinishHours":0,
          "userId":3,
          "classId": 2
      }
  }
  ```

### 2.5 获得指定classId班级的课程信息

和上面的有区别，上面的是这个班级具体到星期几的信息，这个是看这个班级的全部课表

* GET  /getCourseByClassId?classId=1

* payload request

* return

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": [
          {
              "courseName":"c语言",
              "courseIntroduction":"课程简介",
              "courseHours":43,
              "courseFinishHours":4,
              "userId":3,
              "courseId": 1,
              "classId": 3
      	},
          {
              "courseName":"java",
              "courseIntroduction":"课程简介",
              "courseHours":33,
              "courseFinishHours":0,
              "userId":3,
              "courseId": 23,
              "classId": 3
      	}
       ]
  }
  ```

  



### 2.6 获得指定id的课程课时进程

(这个不写了，你可以直接请求获取指定id的courseId，你在直接计算完成课时和总课时比)

* GET  /getCalculation/{id}

* return

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": "17.02%"
  }
  ```



### 2.7 更新课时进度

这个功能用来输入一个这次上课完成了几个课时，输入课时数，我进行修改

* POST  /updateHours/{id}?increaseHours=4

* token userType只能是0，只能本人更改

* return

  ```json
  {
      "code": 0,
      "message": "修改成功！"
  }
  ```




## 3. 签到Absence

### 3.1 添加一个某个时间的未签到事件

添加一个或者多个签到时候，参数传入的一定要是**班级id（classId）**

后端会处理：如果传入的学生id不存在，报错：“不存在该学生信息，请先添加学生信息”，如果课程信息不存在，报错：“不存在该课程信息，请先添加该课程信息”

* POST  /addAbsence?classId=1

* payload:  request

  ```json
  {
  	"studentId":2018214505,
  	"courseId":2,
  	"createTime":"2020-3-15"
  }
  ```

* return:

  ```json
  {
      "code": 1,
      "message": "不存在该课程信息！请先添加该课程信息！"
  }
  ```



### 3.2 批量添加签到记录 

批量添加，可以试试故意添加不存在的课程，以便查看报错，找一下我的错误

* POST  /addAbsences?classId=1

* payload: request 

  ```json
  [
      {
  		"courseId":14,
  		"studentId":2018214505,
  		"createTime":"2020-3-4"
  	},
      {
  		"courseId":1,
  		"studentId":2018234567,
  		"createTime":"2020-3-5"
  	}
  ]
  ```

* return:

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": [
          {
              "14": "该课程信息不存在，请先添加！"
          },
          {
              "1": "添加成功！",
              "2018234567": "添加成功!"
          }
      ]
  }
  ```


### 3.3 删除一个签到记录 

同时使该记录的学生的未签到记录减一

* DELETE  /deleteAbsence?courseId=1&studentId=2018214505&date=2020-3-4&classId=1

* request

* return：

  ```json
  {
      "code": 0,
      "message": "修改成功！"
  }
  ```



## 下面的获取签到都是差不多的，返回值都类似(下面的返回值有可能和参数不是很相同，就是参考)，只有参数和意义上的不同，写几个后，其他的就省略着写了

### 3.4 获取某个时间点某同学的课程签到情况

* GET  /getAbsence?courseId=1&date=2020-3-15&studentId=2018231323&classId=2

* request

* return:

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": {
          "createTime": "2020-03-15",
          "studentId": 2018214505,
     	 	 "courseId":2,
          "studentName":"张三"
      }
  }
  ```

### 3.5 获取指定某天的签到 

**指的是某一天所有的签到**

* GET  /getAbsenceByDate?date=2020-3-15

* request 

* return：

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": [
          {
              "createTime": "2020-03-15",
              "studentId": 2018214505,
              "courseId": 1
          },
          {
              "createTime": "2020-03-15",
              "studentId": 2018242434,
              "courseId": 1
          }
      ]
  }
  ```

### 3.6 获取指定课程id的（就是课程表上某一节）签到

* GET  /getAbsenceByCourseId?courseId=1

* request

* return:

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": [
          {
              "createTime": "2020-03-15",
              "studentId": 2018214505,
              "courseId": 1
          },
          {
              "createTime": "2020-03-15",
              "studentId": 2018242434,
              "courseId": 1
          }
      ]
  }
  ```

### 3.7 获取某日、某节课的签到情况

* GET /getAbsenceByDateAndCourseId?courseId=1&date=2020-3-3&classId=1
* request
* return 。。。



### 3.8 获取指定某节课某学生的签到情况

* GET  /getAbsenceByCourseIdAndStudentId?courseId=1&studentId=2018212344
* request

### **这里如果有你想要的签到情况，但是我没写，或者有的get请求的获取信息不够，马上告诉我，我再想办法**

### 3.9 添加坐标

* POST  /addCoordinate

* request

  ```json
  {
  	"courseId":3,
  	"x":3,
  	"y":4
  }
  ```

* return:

  ```json
  {
      "code":0,
      "message":"添加成功！"
  }
  ```

### 3.10  删除指定坐标

* DELETE  /deleteCoordinate?x=3&y=4&courseId=3

* request

* return

  ```json
  {
      "code":0,
      "message":"删除成功！"
  }
  ```



### 3.11 修改坐标

* POST  /updateCoordinate?x=2&y=3

* request

  **注意**：json里面穿的x、y是要修改的数据，param中传的是用来定位的x、y，而courseId是课程的，固定不变的。

  ```json
  {
      "x":3,
      "y":4,
      "courseId":3
  }
  ```

* return ：

  ```json
  {
      "code":0,
      "message":"修改成功!"
  }
  ```



### 3.12 获取课程的课程表坐标

* GET  /getCoordinate?courseId=3

* request

* return

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": [
          {
              "x": 3,
              "y": 4,
              "courseId": 3
          },
          {
              "x": 5,
              "y": 7,
              "courseId": 3
          }
      ]
  }
  ```

  

## 4.学生Student

### 4.1 添加一个学生

* POST  /addStudent?classId=2  这里的classId就是这个班级id

* payload  token

  ```json
  {
  	"studentId":2018216585,
   	"studentName":"张思",
      "studentAbsenceTimes":0,
      "classId":2
  }
  ```

* return:

  ```json
  {
      "code": 0,
      "message": "添加成功！"
  }
  ```

### 4.2 修改指定id的学生信息

* DELETE  /updateStudent?id=2018242434?classId=2

* token

* payload

  ```json
  {
  	"studentName":"张三",
      "absenceTimes":3
  }
  ```

* return:

  ```json
  {
      "code": 0,
      "message": "修改成功！"
  }
  ```

  

### 4.3 获取指定id的学生信息

* GET　/getStudentById?studentId=2018214505&classId=2

* token

* return:

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": {
          "studentId": 2018214505,
          "studentName": "bogdan",
          "classId":2,
          "absenceTimes": 1
      }
  }
  ```




### 4.4 批量添加学生信息

* POST  /addStudents

* token

* payload:

  ```json
  [
      {
  		"studentId":2018214505,
  		"studentName":"张三",
          "classId":1
  	},
  	{
  		"studentId":2018234567,
  		"studentName":"李四",
          "classId":1
  	},
  	{
  		"studentId":2019234678,
  		"studentName":"bogdan",
          "classId":1
  	}
  ]
  	
  ```

* return：

  这里我会传过来每个学号的相应的添加信息，根据学号对应返回信息好了，下面就是所有的三种情况

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": [
          {
              "2018214505": "该学生已经录入，无需再次记录。"
          },
          {
              "2018234567": "该学生已经录入"
          },
          {
              "2019234678": "录入失败"
          }
      ]
  }
  ```

  

### 4.5 获取指定班级下的所有学生

* GET  /getStudentByClassId?classId=1

* request

* return:

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": [
          {
              "studentId": 2018214343,
              "studentName": "李四",
              "absenceTimes": 0
          },
          {
              "studentId": 2018214505,
              "studentName": "张三",
              "absenceTimes": 1
          },
          {
              "studentId": 2018234232,
              "studentName": "赵武",
              "absenceTimes": 0
          }
      ]
  }
  ```



### 4.6 通过传递Excel文件添加学生信息

* POST  /addStudentsByExcel?classId=4

* token

* file（

  ![image-20200407162731512](https://i.loli.net/2020/04/07/Jf6V4y2uYMH3pOe.png)

* return

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": [
          {
              "2019234432": "录入成功！"
          },
          {
              "2043234542": "录入成功！"
          },
          {
              "2018323456": "录入成功！"
          }
      ]
  }
  ```

### 4.7 返回班级下的学生人数

* POST  /getStudentNum?classId=2

* token

* return:(这里的data就是学生人数)

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": 3
  }
  ```

  

## 5.班级Class

## 5.1 添加一个班级

* POST  /addClass

* request

  ```json
  {
  	"className":"计科二班",
  	"userId":2,
  	"classStudentNum":20 
  }
  ```

* return 

  ```json
  {
      "code": 0,
      "message": "添加成功！"
  }
  ```



### 5.2 修改指定班级

* POST  /updateClass?classId=2

* request

  ```json
  {
  	"className":"计科三班",
  	"classStudentNum":30
  }
  ```

* return:

  ```json
  {
      "code": 0,
      "message": "修改成功！"
  }
  ```

  

### 5.3 删除指定id的班级

* DELETE   /deleteById?classId=1&courseId=2

* request

* return:

  ```json
  {
      "code": 1,
      "message": "删除失败！"
  }
  ```



### 5.4 获取指定班级的信息

* GET  /getClassById?classId=2

* request

* return：

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": {
          "classId": 1,
          "classStudentName": 23,
          "className": "计科2班",
          "userId": 2
      }
  }
  ```

  

### 5.5 获取指定用户id（指定老师）的所有班级信息

* GET   /getClassesByUserId?userId=2

* request

* return:

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": [
          {	
              "classId": 1,
              "userId": 2,
              "classStudentName": 23,
              "className": "计科2班"
          },
          {	
              "classId": 1,
              "userId": 2,
              "classStudentName": 23,
              "className": "计科2班"
          }
      ]
  }
  ```

  
