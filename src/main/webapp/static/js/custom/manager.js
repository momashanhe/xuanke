$(document).ready(function () {
  // 切换标签
  $('.nav-link:not(.logout-link)').click(function (e) {
    e.preventDefault();
    var target = $(this).data('target');
    $('.nav-link').removeClass('active');
    $(this).addClass('active');
    $('.tab-content').hide();
    $('#' + target).show();

    // 加载对应内容
    switch (target) {
      case 'detail':
        loadDetail();
        break;
      case 'students':
        loadStudentList();
        break;
      case 'teachers':
        loadTeacherList();
        break;
    }
  });

  // 加载个人信息
  function loadDetail() {
    $.ajax({
      url: contextPath + '/api/manager/detail',
      type: 'GET',
      dataType: 'json',
      success: function (response) {
        if (response.code === 200) {
          var manager = response.data;
          $('#userId').val(manager.id);
          $('#username').val(manager.username);
          $('#password').val('');
          $('#name').val(manager.name);
          $('#phone').val(manager.phone);
          $('#email').val(manager.email);
        }
      }
    });
  }

  // 加载学生列表
  function loadStudentList() {
    $.ajax({
      url: contextPath + '/api/manager/listStudent',
      type: 'GET',
      dataType: 'json',
      success: function (response) {
        if (response.code === 200) {
          var students = response.data;
          var html = '';
          students.forEach(function (student) {
            html += `
              <tr>
                <td>${student.username}</td>
                <td>${student.name}</td>
                <td>${student.phone}</td>
                <td>${student.email}</td>
                <td>
                  <button class="btn btn-danger btn-sm" onclick="deleteStudent(${student.id})">
                    <i class="fas fa-trash"></i> 删除
                  </button>
                </td>
              </tr>
            `;
          });
          $('#studentList').html(html);
        }
      }
    });
  }

  // 加载教师列表
  function loadTeacherList() {
    $.ajax({
      url: contextPath + '/api/manager/listTeacher',
      type: 'GET',
      dataType: 'json',
      success: function (response) {
        if (response.code === 200) {
          var teachers = response.data;
          var html = '';
          teachers.forEach(function (teacher) {
            html += `
              <tr>
                <td>${teacher.username}</td>
                <td>${teacher.name}</td>
                <td>${teacher.phone}</td>
                <td>${teacher.email}</td>
                <td>
                  <button class="btn btn-danger btn-sm" onclick="deleteTeacher(${teacher.id})">
                    <i class="fas fa-trash"></i> 删除
                  </button>
                </td>
              </tr>
            `;
          });
          $('#teacherList').html(html);
        }
      }
    });
  }

  // 保存学生
  window.saveStudent = function () {
    var data = {
      username: $('#studentUsername').val(),
      password: $('#studentPassword').val(),
      name: $('#studentName').val(),
      phone: $('#studentPhone').val(),
      email: $('#studentEmail').val()
    };
    $.ajax({
      url: contextPath + '/api/manager/addStudent',
      type: 'POST',
      data: data,
      dataType: 'json',
      success: function (response) {
        if (response.code === 200) {
          $('#addStudentModal').modal('hide');
          $('#addStudentForm')[0].reset();
          showSuccess('添加成功！');
          loadStudentList();
        } else {
          showError(response.message);
        }
      }
    });
  }

  // 保存教师
  window.saveTeacher = function () {
    var data = {
      username: $('#teacherUsername').val(),
      password: $('#teacherPassword').val(),
      name: $('#teacherName').val(),
      phone: $('#teacherPhone').val(),
      email: $('#teacherEmail').val()
    };
    $.ajax({
      url: contextPath + '/api/manager/addTeacher',
      type: 'POST',
      data: data,
      dataType: 'json',
      success: function (response) {
        if (response.code === 200) {
          $('#addTeacherModal').modal('hide');
          $('#addTeacherForm')[0].reset();
          showSuccess('添加成功！');
          loadTeacherList();
        } else {
          showError(response.message);
        }
      }
    });
  }

  // 删除学生
  window.deleteStudent = function (id) {
    if (confirm('确定要删除该学生吗？')) {
      $.ajax({
        url: contextPath + '/api/manager/deleteStudent',
        type: 'POST',
        data: {id: id},
        dataType: 'json',
        success: function (response) {
          if (response.code === 200) {
            showSuccess('删除成功！');
            loadStudentList();
          } else {
            showError(response.message);
          }
        }
      });
    }
  }

  // 删除教师
  window.deleteTeacher = function (id) {
    if (confirm('确定要删除该教师吗？删除后该教师的课程也会被删除。')) {
      $.ajax({
        url: contextPath + '/api/manager/deleteTeacher',
        type: 'POST',
        data: {id: id},
        dataType: 'json',
        success: function (response) {
          if (response.code === 200) {
            showSuccess('删除成功！');
            loadTeacherList();
          } else {
            showError(response.message);
          }
        }
      });
    }
  }

  // 保存个人信息
  $('#detailForm').submit(function (e) {
    e.preventDefault();
    var data = {
      id: $('#userId').val(),
      password: $('#password').val(),
      name: $('#name').val(),
      phone: $('#phone').val(),
      email: $('#email').val()
    };
    $.ajax({
      url: contextPath + '/api/manager/update',
      type: 'POST',
      data: data,
      dataType: 'json',
      success: function (response) {
        if (response.code === 200) {
          showSuccess('更新成功！');
        } else {
          showError(response.message);
        }
      }
    });
  });

  // 初始加载个人信息
  loadDetail();
});