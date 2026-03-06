<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>管理员首页 - 选课</title>
  <jsp:include page="/WEB-INF/common/common.jsp"/>
</head>
<body>
  <div class="container-fluid">
    <div class="row">
      <!-- 侧边栏 -->
      <div class="col-md-3 sidebar p-4">
        <h3 class="text-center mb-5"><i class="fas fa-user-cog"></i> 管理员中心</h3>
        <ul class="nav flex-column">
          <li class="nav-item">
            <a class="nav-link active" href="#detail" data-target="detail">个人信息</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#students" data-target="students">学生管理</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#teachers" data-target="teachers">教师管理</a>
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
            <!-- 学生管理 -->
            <div id="students" class="tab-content" style="display: none;">
              <div class="d-flex justify-content-between mb-4">
                <h5>学生管理</h5>
                <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addStudentModal">
                  <i class="fas fa-plus"></i> 新增学生
                </button>
              </div>
              <div class="table-responsive">
                <table class="table table-striped">
                  <thead>
                  <tr>
                    <th>用户名</th>
                    <th>姓名</th>
                    <th>电话</th>
                    <th>邮箱</th>
                    <th>操作</th>
                  </tr>
                  </thead>
                  <tbody id="studentList">
                  <!-- 学生列表将通过AJAX动态生成 -->
                  </tbody>
                </table>
              </div>
            </div>
            <!-- 教师管理 -->
            <div id="teachers" class="tab-content" style="display: none;">
              <div class="d-flex justify-content-between mb-4">
                <h5>教师管理</h5>
                <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addTeacherModal">
                  <i class="fas fa-plus"></i> 新增教师
                </button>
              </div>
              <div class="table-responsive">
                <table class="table table-striped">
                  <thead>
                  <tr>
                    <th>用户名</th>
                    <th>姓名</th>
                    <th>电话</th>
                    <th>邮箱</th>
                    <th>操作</th>
                  </tr>
                  </thead>
                  <tbody id="teacherList">
                  <!-- 教师列表将通过AJAX动态生成 -->
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- 新增学生模态框 -->
  <div class="modal fade" id="addStudentModal" tabindex="-1" aria-labelledby="addStudentModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="addStudentModalLabel">新增学生</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form id="addStudentForm">
            <div class="mb-3">
              <label for="studentUsername" class="form-label">用户名</label>
              <input type="text" class="form-control" id="studentUsername" required>
            </div>
            <div class="mb-3">
              <label for="studentPassword" class="form-label">密码</label>
              <input type="password" class="form-control" id="studentPassword" required>
            </div>
            <div class="mb-3">
              <label for="studentName" class="form-label">姓名</label>
              <input type="text" class="form-control" id="studentName" required>
            </div>
            <div class="mb-3">
              <label for="studentPhone" class="form-label">电话</label>
              <input type="text" class="form-control" id="studentPhone">
            </div>
            <div class="mb-3">
              <label for="studentEmail" class="form-label">邮箱</label>
              <input type="email" class="form-control" id="studentEmail">
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
          <button type="button" class="btn btn-primary" onclick="saveStudent()">保存</button>
        </div>
      </div>
    </div>
  </div>

  <!-- 新增教师模态框 -->
  <div class="modal fade" id="addTeacherModal" tabindex="-1" aria-labelledby="addTeacherModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="addTeacherModalLabel">新增教师</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form id="addTeacherForm">
            <div class="mb-3">
              <label for="teacherUsername" class="form-label">用户名</label>
              <input type="text" class="form-control" id="teacherUsername" required>
            </div>
            <div class="mb-3">
              <label for="teacherPassword" class="form-label">密码</label>
              <input type="password" class="form-control" id="teacherPassword" required>
            </div>
            <div class="mb-3">
              <label for="teacherName" class="form-label">姓名</label>
              <input type="text" class="form-control" id="teacherName" required>
            </div>
            <div class="mb-3">
              <label for="teacherPhone" class="form-label">电话</label>
              <input type="text" class="form-control" id="teacherPhone">
            </div>
            <div class="mb-3">
              <label for="teacherEmail" class="form-label">邮箱</label>
              <input type="email" class="form-control" id="teacherEmail">
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
          <button type="button" class="btn btn-primary" onclick="saveTeacher()">保存</button>
        </div>
      </div>
    </div>
  </div>

  <!-- 管理员 -->
  <script src="${pageContext.request.contextPath}/static/js/custom/manager.js"></script>
</body>
</html>