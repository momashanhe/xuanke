<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>选课 - 登录</title>
  <jsp:include page="/WEB-INF/common/common.jsp"/>
  <style>
    body {
      background-color: #f5f5f5;
      height: 100vh;
      display: flex;
      align-items: center;
      justify-content: center;
    }

    .login-card {
      width: 400px;
      box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
      border-radius: 8px;
      overflow: hidden;
    }

    .login-header {
      background-color: #007bff;
      color: white;
      padding: 20px;
      text-align: center;
    }

    .login-body {
      padding: 30px;
    }

    .role-select {
      margin: 20px 0;
    }

    .role-option {
      margin-right: 20px;
    }
  </style>
</head>
<body>
  <div class="login-card">
    <div class="login-header">
      <h3><i class="fas fa-graduation-cap"></i> 选课</h3>
    </div>
    <div class="login-body">
      <form id="loginForm">
        <div class="mb-3">
          <label for="username" class="form-label">用户名</label>
          <input type="text" class="form-control" id="username" name="username" required>
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">密码</label>
          <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <div class="role-select">
          <label class="form-label">角色选择</label>
          <div class="d-flex">
            <div class="role-option">
              <input type="radio" id="student" name="role" value="student" checked>
              <label for="student">学生</label>
            </div>
            <div class="role-option">
              <input type="radio" id="teacher" name="role" value="teacher">
              <label for="teacher">教师</label>
            </div>
            <div class="role-option">
              <input type="radio" id="manager" name="role" value="manager">
              <label for="manager">管理员</label>
            </div>
          </div>
        </div>
        <button type="submit" class="btn btn-primary w-100">登录</button>
      </form>
    </div>
  </div>

  <script>
    $(document).ready(function () {
      $('#loginForm').submit(function (e) {
        e.preventDefault();
        var formData = $(this).serialize();
        var role = $('input[name="role"]:checked').val();
        $.ajax({
          url: contextPath + '/api/' + role + '/login',
          type: 'POST',
          data: formData,
          dataType: 'json',
          success: function (response) {
            if (response.code === 200) {
              window.location.href = contextPath + '/view/' + role + '/home';
            } else {
              showError(response.message);
            }
          },
          error: function () {
            showError('登录失败，请重试');
          }
        });
      });
    });
  </script>
</body>
</html>