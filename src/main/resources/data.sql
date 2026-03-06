-- 创建数据库
CREATE DATABASE IF NOT EXISTS xuanke DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE xuanke;

-- 创建学生表
CREATE TABLE IF NOT EXISTS student
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(50) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100),
    status INT DEFAULT 1 COMMENT '状态：1-正常，0-删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- 创建教师表
CREATE TABLE IF NOT EXISTS teacher
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(50) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100),
    status INT DEFAULT 1 COMMENT '状态：1-正常，0-删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- 创建管理员表
CREATE TABLE IF NOT EXISTS manager
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(50) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- 创建课程表
CREATE TABLE IF NOT EXISTS course
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    teacher_id INT NOT NULL,
    credit INT NOT NULL,
    capacity INT NOT NULL,
    schedule VARCHAR(100) NOT NULL,
    location VARCHAR(100) NOT NULL,
    description TEXT,
    status INT DEFAULT 1 COMMENT '状态：1-正常，0-删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- 创建选课记录表
CREATE TABLE IF NOT EXISTS selection
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    select_time DATETIME NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- 管理员账号：manager / 123456
INSERT INTO manager (username, password, name, phone, email)
VALUES ('admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '13800138000', 'admin@example.com');

-- 教师账号：teacher1 / 123456, teacher2 / 123456
INSERT INTO teacher (username, password, name, phone, email)
VALUES ('teacher1', 'e10adc3949ba59abbe56e057f20f883e', '张老师', '13900139001', 'teacher1@example.com'),
       ('teacher2', 'e10adc3949ba59abbe56e057f20f883e', '李老师', '13900139002', 'teacher2@example.com');

-- 学生账号：student1 / 123456, student2 / 123456
INSERT INTO student (username, password, name, phone, email)
VALUES ('student1', 'e10adc3949ba59abbe56e057f20f883e', '学生1', '13700137001', 'student1@example.com'),
       ('student2', 'e10adc3949ba59abbe56e057f20f883e', '学生2', '13700137002', 'student2@example.com');

-- 插入课程数据
INSERT INTO course (name, teacher_id, credit, capacity, schedule, location, description, status)
VALUES ('高等数学', 1, 4, 50, '周一 1-2节', '教1-101', '高等数学基础课程', 1),
       ('大学物理', 1, 3, 40, '周三 3-4节', '教1-102', '大学物理基础课程', 1),
       ('程序设计基础', 2, 3, 30, '周二 5-6节', '教2-201', '程序设计入门课程', 1),
       ('数据结构', 2, 4, 35, '周四 1-2节', '教2-202', '数据结构与算法', 1);

-- 插入初始选课记录
INSERT INTO selection (student_id, course_id, select_time)
VALUES (1, 1, NOW()),
       (1, 3, NOW()),
       (2, 2, NOW()),
       (2, 4, NOW());
