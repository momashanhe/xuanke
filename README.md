# 选课管理系统

## 项目简介

基于SSM框架开发的选课管理系统，实现了学生选课管理、教师课程管理、管理员用户管理等功能。

## 技术选型

### 后端技术

后端技术：
- Java版本: JDK 1.8
- 构建工具: Maven 3.6.3
- Web框架: Spring 5.3.23 + MVC 5.3.23
- ORM框架: MyBatis 3.5.13
- 数据库: MySQL 5.7.40
- 连接池: Druid 1.2.8
- JSON处理: Jackson 2.9.1

### 前端技术

前端技术：
- 模板引擎: JSP + JSTL
- UI框架: Bootstrap 5.3.0
- JavaScript库: jQuery 3.6.0
- 交互方式: AJAX异步请求

## 项目结构

项目结构：
```code code
xuanke/
├── src/
│   ├── main/
│   │   ├── java/com/momashanhe/xuanke/
│   │   │   ├── initializer/                       # 初始化器
│   │   │   │   └── WebInitializer.java            # Web应用初始化器
│   │   │   ├── config/                            # 配置类
│   │   │   │   ├── RootConfig.java                # 根配置类
│   │   │   │   └── WebConfig.java                 # Web配置类
│   │   │   ├── controller/                        # 前端控制器层
│   │   │   │   ├── CommonController.java          # 公共控制器
│   │   │   │   ├── ManagerController.java         # 管理员控制器
│   │   │   │   ├── StudentController.java         # 学生控制器
│   │   │   │   └── TeacherController.java         # 教师控制器
│   │   │   ├── business/                          # 业务控制器层
│   │   │   │   ├── ManagerBusiness.java           # 管理员业务控制器
│   │   │   │   ├── StudentBusiness.java           # 学生业务控制器
│   │   │   │   └── TeacherBusiness.java           # 教师业务控制器
│   │   │   ├── service/                           # 服务层
│   │   │   │   ├── impl/                          # 服务实现
│   │   │   │   │   ├── CourseServiceImpl.java     # 课程服务实现
│   │   │   │   │   ├── ManagerServiceImpl.java    # 管理员服务实现
│   │   │   │   │   ├── SelectionServiceImpl.java  # 选课服务实现
│   │   │   │   │   ├── StudentServiceImpl.java    # 学生服务实现
│   │   │   │   │   └── TeacherServiceImpl.java    # 教师服务实现
│   │   │   │   ├── CourseService.java             # 课程服务接口
│   │   │   │   ├── ManagerService.java            # 管理员服务接口
│   │   │   │   ├── SelectionService.java          # 选课服务接口
│   │   │   │   ├── StudentService.java            # 学生服务接口
│   │   │   │   └── TeacherService.java            # 教师服务接口
│   │   │   ├── mapper/                            # MyBatis映射
│   │   │   │   ├── CourseMapper.java              # 课程Mapper接口
│   │   │   │   ├── CourseMapper.xml               # 课程Mapper映射文件
│   │   │   │   ├── ManagerMapper.java             # 管理员Mapper接口
│   │   │   │   ├── ManagerMapper.xml              # 管理员Mapper映射文件
│   │   │   │   ├── SelectionMapper.java           # 选课记录Mapper接口
│   │   │   │   ├── SelectionMapper.xml            # 选课记录Mapper映射文件
│   │   │   │   ├── StudentMapper.java             # 学生Mapper接口
│   │   │   │   ├── StudentMapper.xml              # 学生Mapper映射文件
│   │   │   │   ├── TeacherMapper.java             # 教师Mapper接口
│   │   │   │   └── TeacherMapper.xml              # 教师Mapper映射文件
│   │   │   ├── entity/                            # 实体类
│   │   │   │   ├── Course.java                    # 课程实体类
│   │   │   │   ├── Manager.java                   # 管理员实体类
│   │   │   │   ├── Selection.java                 # 选课记录实体类
│   │   │   │   ├── Student.java                   # 学生实体类
│   │   │   │   └── Teacher.java                   # 教师实体类
│   │   │   └── util/                              # 工具类
│   │   │       └── BusinessUtil.java              # 业务工具类
│   │   ├── resources/                             # 资源文件
│   │   │   ├── data.sql                           # 数据库脚本文件
│   │   │   ├── jdbc.properties                    # 数据库配置文件
│   │   │   └── mybatis.xml                        # MyBatis配置文件
│   │   └── webapp/                                # Web资源
│   │       ├── WEB-INF/
│   │       │   ├── common/                        # 公共组件
│   │       │   │   └── common.jsp                 # 公共页面
│   │       │   ├── views/                         # 业务页面
│   │       │   │   ├── manager/                   # 管理员页面
│   │       │   │   │   └── home.jsp               # 管理员首页
│   │       │   │   ├── student/                   # 学生页面
│   │       │   │   │   └── home.jsp               # 学生首页
│   │       │   │   └── teacher/                   # 教师页面
│   │       │   │       └── home.jsp               # 教师首页
│   │       ├── static/                            # 静态资源
│   │       │   ├── css/                           # CSS样式文件
│   │       │   │   ├── common/                    # 公共样式
│   │       │   │   │   └── common.css             # 公共样式文件
│   │       │   │   └── vendor/                    # 第三方样式
│   │       │   │       ├── bootstrap/             # Bootstrap样式
│   │       │   │       └── font-awesome/          # Font Awesome图标
│   │       │   ├── js/                            # JS文件
│   │       │   │   ├── common/                    # 公共脚本
│   │       │   │   │   └── common.js              # 公共脚本文件
│   │       │   │   ├── custom/                    # 自定义脚本
│   │       │   │   │   ├── manager.js             # 管理员页面脚本
│   │       │   │   │   ├── student.js             # 学生页面脚本
│   │       │   │   │   └── teacher.js             # 教师页面脚本
│   │       │   │   └── vendor/                    # 第三方脚本
│   │       │   │       ├── bootstrap/             # Bootstrap脚本
│   │       │   │       └── jquery/                # jQuery脚本
│   │       ├── favicon.ico                        # 网站图标
│   │       └── index.jsp                          # 首页
│   └── test/                                      # 测试代码
├── .gitignore                                     # Git忽略文件
├── LICENSE                                        # 许可证文件
├── pom.xml                                        # Maven配置文件
└── README.md                                      # 项目说明文档
```

## 功能模块

### 管理员模块

管理员模块：
- 个人信息修改
- 学生管理
- 教师管理

### 学生模块

学生模块：
- 个人信息修改
- 查看课程列表
- 查看已选课程列表
- 选课
- 退课

### 教师模块

教师模块：
- 个人信息修改
- 课程管理
- 查看课程列表

## 接口说明

### 页面路由

页面路由：
- `GET /view/manager/home` - 管理员首页
- `GET /view/student/home` - 学生首页
- `GET /view/teacher/home` - 教师首页
- `GET /view/common/logout` - 退出登录

### 管理员接口

管理员接口：
- `POST /api/manager/login` - 管理员登录
- `GET /api/manager/detail` - 获取管理员详情
- `POST /api/manager/update` - 更新管理员信息
- `GET /api/manager/listStudent` - 获取学生列表
- `POST /api/manager/addStudent` - 添加学生
- `POST /api/manager/deleteStudent` - 删除学生
- `GET /api/manager/listTeacher` - 获取教师列表
- `POST /api/manager/addTeacher` - 添加教师
- `POST /api/manager/deleteTeacher` - 删除教师

### 学生接口

学生接口：
- `POST /api/student/login` - 学生登录
- `GET /api/student/detail` - 获取学生详情
- `POST /api/student/update` - 更新学生信息
- `GET /api/student/listCourse` - 获取课程列表
- `GET /api/student/listSelectionByStudentId` - 获取已选课程列表
- `POST /api/student/addCourse` - 选课
- `POST /api/student/deleteCourse` - 退课

### 教师接口

教师接口：
- `POST /api/teacher/login` - 教师登录
- `GET /api/teacher/detail` - 获取教师详情
- `POST /api/teacher/update` - 更新教师信息
- `GET /api/teacher/listCourseWithTeacherId` - 获取教师课程列表
- `GET /api/teacher/detailCourse` - 获取课程详情
- `POST /api/teacher/addCourse` - 添加课程
- `POST /api/teacher/updateCourse` - 更新课程
- `POST /api/teacher/deleteCourse` - 删除课程

## 数据库设计

### 管理员表 (manager)

管理员表：
- id: 主键
- username: 用户名
- password: 密码（MD5加密）
- name: 姓名
- phone: 电话
- email: 邮箱

### 学生表 (student)

学生表：
- id: 主键
- username: 用户名
- password: 密码（MD5加密）
- name: 姓名
- phone: 电话
- email: 邮箱
- status: 状态（1-正常，0-删除）

### 教师表 (teacher)

教师表：
- id: 主键
- username: 用户名
- password: 密码（MD5加密）
- name: 姓名
- phone: 电话
- email: 邮箱
- status: 状态（1-正常，0-删除）

### 课程表 (course)

课程表：
- id: 主键
- name: 课程名称
- teacher_id: 教师ID
- credit: 学分
- capacity: 容量
- schedule: 上课时间
- location: 上课地点
- description: 课程描述
- status: 状态（1-正常，0-删除）

### 选课记录表 (selection)

选课记录表：
- id: 主键
- student_id: 学生ID
- course_id: 课程ID
- select_time: 选课时间

## 部署说明

### 环境要求

环境要求：
- JDK 1.8
- Maven 3.6.3
- MySQL 5.7.40
- Tomcat 8.5.50

### 数据库

执行`src/main/resources/data.sql`脚本创建数据库和初始化数据。

修改`src/main/resources/jdbc.properties`中的数据库配置。

### 打包部署

编译打包项目：
```bash
mvn clean package
```

将生成的WAR包部署到Tomcat服务器。

启动Tomcat并访问：
[http://localhost:8080/xuanke/](http://localhost:8080/xuanke/)

## 测试账号

### 管理员账号

管理员账号：
- 用户名：admin
- 密码：123456

### 教师账号

教师账号：
- 用户名：teacher1
- 密码：123456
- 用户名：teacher2
- 密码：123456

### 学生账号

学生账号：
- 用户名：student1
- 密码：123456
- 用户名：student2
- 密码：123456
