<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>学生首页 - 选课</title>
  <jsp:include page="/WEB-INF/common/common.jsp"/>
</head>
<body>
  <div class="container-fluid">
    <div class="row">
      <!-- 侧边栏 -->
      <div class="col-md-3 sidebar p-4">
        <h3 class="text-center mb-5"><i class="fas fa-user-graduate"></i> 学生中心</h3>
        <ul class="nav flex-column">
          <li class="nav-item">
            <a class="nav-link active" href="#detail" data-target="detail">个人信息</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#courses" data-target="courses">可选课程</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#selectedCourses" data-target="selectedCourses">已选课程</a>
          </li>
          <li class="nav-item mt-5">
            <a class="nav-link text-danger logout-link" href="${pageContext.request.contextPath}/view/common/logout"><i class="fas fa-sign-out-alt"></i> 退出登录</a>
          </li>
        </ul>
      </div>
      <!-- 主内容区 -->
      <div class="col-md-9 content p-4">
        <div class="card">
          <div class="card-header bg-primary text-white">
            <h4><i class="fas fa-graduation-cap"></i> 选课</h4>
          </div>
          <div class="card-body">
            <!-- 个人信息 -->
            <div id="detail" class="tab-content active">
              <h5 class="mb-4">个人信息</h5>
              <form id="detailForm">
                <input type="hidden" id="userId">
                <div class="mb-3">
                  <label for="username" class="form-label">用户名</label>
                  <input type="text" class="form-control" id="username" disabled>
                </div>
                <div class="mb-3">
                  <label for="password" class="form-label">密码（为空则不修改）</label>
                  <input type="password" class="form-control" id="password">
                </div>
                <div class="mb-3">
                  <label for="name" class="form-label">姓名</label>
                  <input type="text" class="form-control" id="name">
                </div>
                <div class="mb-3">
                  <label for="phone" class="form-label">电话</label>
                  <input type="text" class="form-control" id="phone">
                </div>
                <div class="mb-3">
                  <label for="email" class="form-label">邮箱</label>
                  <input type="email" class="form-control" id="email">
                </div>
                <button type="submit" class="btn btn-primary">保存修改</button>
              </form>
            </div>
            <!-- 可选课程 -->
            <div id="courses" class="tab-content" style="display: none;">
              <h5 class="mb-4">可选课程</h5>
              <div class="row" id="courseList">
                <!-- 可选课程将通过AJAX动态生成 -->
              </div>
            </div>
            <!-- 已选课程 -->
            <div id="selectedCourses" class="tab-content" style="display: none;">
              <h5 class="mb-4">已选课程</h5>
              <div class="table-responsive">
                <table class="table table-striped">
                  <thead>
                  <tr>
                    <th>课程名称</th>
                    <th>教师</th>
                    <th>学分</th>
                    <th>选课时间</th>
                    <th>操作</th>
                  </tr>
                  </thead>
                  <tbody id="selectedCourseList">
                  <!-- 已选课程将通过AJAX动态生成 -->
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- 学生 -->
  <script src="${pageContext.request.contextPath}/static/js/custom/student.js"></script>
</body>
</html>