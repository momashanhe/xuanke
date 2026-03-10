<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>教师首页 - 选课</title>
  <jsp:include page="/WEB-INF/common/common.jsp"/>
</head>
<body>
  <div class="container-fluid">
    <div class="row">
      <!-- 侧边栏 -->
      <div class="col-md-3 sidebar p-4">
        <h3 class="text-center mb-5"><i class="fas fa-chalkboard-teacher"></i> 教师中心</h3>
        <ul class="nav flex-column">
          <li class="nav-item">
            <a class="nav-link active" href="#detail" data-target="detail">个人信息</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#courses" data-target="courses">课程管理</a>
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
            <!-- 课程管理 -->
            <div id="courses" class="tab-content" style="display: none;">
              <div class="d-flex justify-content-between mb-4">
                <h5>课程管理</h5>
                <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addCourseModal">
                  <i class="fas fa-plus"></i> 新增课程
                </button>
              </div>
              <div class="table-responsive">
                <table class="table table-striped">
                  <thead>
                  <tr>
                    <th>课程名称</th>
                    <th>学分</th>
                    <th>容量</th>
                    <th>当前人数</th>
                    <th>上课时间</th>
                    <th>上课地点</th>
                    <th>操作</th>
                  </tr>
                  </thead>
                  <tbody id="courseList">
                  <!-- 课程列表将通过AJAX动态生成 -->
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- 新增课程模态框 -->
  <div class="modal fade" id="addCourseModal" tabindex="-1" aria-labelledby="addCourseModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="addCourseModalLabel">新增课程</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form id="addCourseForm">
            <div class="mb-3">
              <label for="courseName" class="form-label">课程名称</label>
              <input type="text" class="form-control" id="courseName" required>
            </div>
            <div class="mb-3">
              <label for="credit" class="form-label">学分</label>
              <input type="number" class="form-control" id="credit" min="1" max="6" required>
            </div>
            <div class="mb-3">
              <label for="capacity" class="form-label">容量</label>
              <input type="number" class="form-control" id="capacity" min="1" required>
            </div>
            <div class="mb-3">
              <label for="schedule" class="form-label">上课时间</label>
              <input type="text" class="form-control" id="schedule" required>
            </div>
            <div class="mb-3">
              <label for="location" class="form-label">上课地点</label>
              <input type="text" class="form-control" id="location" required>
            </div>
            <div class="mb-3">
              <label for="description" class="form-label">课程描述</label>
              <textarea class="form-control" id="description" rows="3"></textarea>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
          <button type="button" class="btn btn-primary" onclick="saveCourse()">保存</button>
        </div>
      </div>
    </div>
  </div>

  <!-- 修改课程模态框 -->
  <div class="modal fade" id="editCourseModal" tabindex="-1" aria-labelledby="editCourseModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="editCourseModalLabel">修改课程</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form id="editCourseForm">
            <input type="hidden" id="courseId">
            <div class="mb-3">
              <label for="editCourseName" class="form-label">课程名称</label>
              <input type="text" class="form-control" id="editCourseName" required>
            </div>
            <div class="mb-3">
              <label for="editCredit" class="form-label">学分</label>
              <input type="number" class="form-control" id="editCredit" min="1" max="6" required>
            </div>
            <div class="mb-3">
              <label for="editCapacity" class="form-label">容量</label>
              <input type="number" class="form-control" id="editCapacity" min="1" required>
            </div>
            <div class="mb-3">
              <label for="editSchedule" class="form-label">上课时间</label>
              <input type="text" class="form-control" id="editSchedule" required>
            </div>
            <div class="mb-3">
              <label for="editLocation" class="form-label">上课地点</label>
              <input type="text" class="form-control" id="editLocation" required>
            </div>
            <div class="mb-3">
              <label for="editDescription" class="form-label">课程描述</label>
              <textarea class="form-control" id="editDescription" rows="3"></textarea>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
          <button type="button" class="btn btn-primary" onclick="updateCourse()">保存</button>
        </div>
      </div>
    </div>
  </div>

  <!-- 教师 -->
  <script src="${pageContext.request.contextPath}/static/js/custom/teacher.js"></script>
</body>
</html>