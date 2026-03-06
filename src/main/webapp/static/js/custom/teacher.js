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
      case 'courses':
        loadCourseList();
        break;
    }
  });

  // 加载个人信息
  function loadDetail() {
    $.ajax({
      url: contextPath + '/api/teacher/detail',
      type: 'GET',
      dataType: 'json',
      success: function (response) {
        if (response.code === 200) {
          var teacher = response.data;
          $('#userId').val(teacher.id);
          $('#username').val(teacher.username);
          $('#password').val('');
          $('#name').val(teacher.name);
          $('#phone').val(teacher.phone);
          $('#email').val(teacher.email);
        }
      }
    });
  }

  // 加载课程列表
  function loadCourseList() {
    $.ajax({
      url: contextPath + '/api/teacher/listCourseWithTeacherId',
      type: 'GET',
      dataType: 'json',
      success: function (response) {
        if (response.code === 200) {
          var courses = response.data;
          var html = '';
          courses.forEach(function (course) {
            html += `
              <tr>
                <td>${course.name}</td>
                <td>${course.credit}</td>
                <td>${course.capacity}</td>
                <td>${course.currentCount}</td>
                <td>${course.schedule}</td>
                <td>${course.location}</td>
                <td>
                  <button class="btn btn-primary btn-sm me-2" onclick="editCourse(${course.id})">
                    <i class="fas fa-edit"></i> 修改
                  </button>
                  <button class="btn btn-danger btn-sm" onclick="deleteCourse(${course.id})">
                    <i class="fas fa-trash"></i> 删除
                  </button>
                </td>
              </tr>
            `;
          });
          $('#courseList').html(html);
        }
      }
    });
  }

  // 保存课程
  window.saveCourse = function () {
    var data = {
      name: $('#courseName').val(),
      credit: $('#credit').val(),
      capacity: $('#capacity').val(),
      schedule: $('#schedule').val(),
      location: $('#location').val(),
      description: $('#description').val()
    };
    $.ajax({
      url: contextPath + '/api/teacher/addCourse',
      type: 'POST',
      data: data,
      dataType: 'json',
      success: function (response) {
        if (response.code === 200) {
          $('#addCourseModal').modal('hide');
          $('#addCourseForm')[0].reset();
          showSuccess('添加成功！');
          loadCourseList();
        } else {
          showError(response.message);
        }
      }
    });
  }

  // 编辑课程
  window.editCourse = function (id) {
    $.ajax({
      url: contextPath + '/api/teacher/detailCourse',
      type: 'GET',
      data: {id: id},
      dataType: 'json',
      success: function (response) {
        if (response.code === 200) {
          var course = response.data;
          $('#courseId').val(course.id);
          $('#editCourseName').val(course.name);
          $('#editCredit').val(course.credit);
          $('#editCapacity').val(course.capacity);
          $('#editSchedule').val(course.schedule);
          $('#editLocation').val(course.location);
          $('#editDescription').val(course.description);
          $('#editCourseModal').modal('show');
        }
      }
    });
  }

  // 更新课程
  window.updateCourse = function () {
    var data = {
      id: $('#courseId').val(),
      name: $('#editCourseName').val(),
      credit: $('#editCredit').val(),
      capacity: $('#editCapacity').val(),
      schedule: $('#editSchedule').val(),
      location: $('#editLocation').val(),
      description: $('#editDescription').val()
    };
    $.ajax({
      url: contextPath + '/api/teacher/updateCourse',
      type: 'POST',
      data: data,
      dataType: 'json',
      success: function (response) {
        if (response.code === 200) {
          $('#editCourseModal').modal('hide');
          showSuccess('更新成功！');
          loadCourseList();
        } else {
          showError(response.message);
        }
      }
    });
  }

  // 删除课程
  window.deleteCourse = function (id) {
    if (confirm('确定要删除该课程吗？删除后相关的选课记录也会被删除。')) {
      $.ajax({
        url: contextPath + '/api/teacher/deleteCourse',
        type: 'POST',
        data: {id: id},
        dataType: 'json',
        success: function (response) {
          if (response.code === 200) {
            showSuccess('删除成功！');
            loadCourseList();
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
      url: contextPath + '/api/teacher/update',
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