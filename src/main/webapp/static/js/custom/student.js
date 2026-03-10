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
      case 'selectedCourses':
        loadSelectedCourseList();
        break;
    }
  });

  // 加载个人信息
  function loadDetail() {
    $.ajax({
      url: contextPath + '/api/student/detail',
      type: 'GET',
      dataType: 'json',
      success: function (response) {
        if (response.code === 200) {
          var student = response.data;
          $('#userId').val(student.id);
          $('#username').val(student.username);
          $('#password').val('');
          $('#name').val(student.name);
          $('#phone').val(student.phone);
          $('#email').val(student.email);
        }
      }
    });
  }

  // 加载可选课程
  function loadCourseList() {
    $.ajax({
      url: contextPath + '/api/student/listCourse',
      type: 'GET',
      dataType: 'json',
      success: function (response) {
        if (response.code === 200) {
          var courses = response.data;
          var html = '';
          courses.forEach(function (course) {
            var isFull = course.currentCount >= course.capacity;
            html += `
              <div class="col-md-4">
                <div class="card course-card">
                  <div class="card-body">
                    <h5 class="card-title mb-3">${course.name}</h5>
                    <p class="card-text"><strong>教师：</strong>${course.teacherName}</p>
                    <p class="card-text"><strong>学分：</strong>${course.credit}</p>
                    <p class="card-text"><strong>上课时间：</strong>${course.schedule}</p>
                    <p class="card-text"><strong>上课地点：</strong>${course.location}</p>
                    <p class="card-text"><strong>容量：</strong>${course.currentCount}/${course.capacity}</p>
                    <p class="card-text">${course.description}</p>
                    <button class="btn btn-primary w-100 ${isFull ? 'disabled' : ''}" ${isFull ? '' : 'onclick="selectCourse(' + course.id + ')"'}>
                    ${isFull ? '已满' : '选课'}
                    </button>
                  </div>
                </div>
              </div>
            `;
          });
          $('#courseList').html(html);
        }
      }
    });
  }

  // 加载已选课程
  function loadSelectedCourseList() {
    $.ajax({
      url: contextPath + '/api/student/listSelectionByStudentId',
      type: 'GET',
      dataType: 'json',
      success: function (response) {
        if (response.code === 200) {
          var selections = response.data;
          var html = '';
          selections.forEach(function (selection) {
            var selectTime = new Date(selection.selectTime).toLocaleString();
            html += `
              <tr>
                <td>${selection.courseName}</td>
                <td>${selection.teacherName}</td>
                <td>${selection.credit}</td>
                <td>${selectTime}</td>
                <td>
                  <button class="btn btn-danger btn-sm" onclick="dropCourse(${selection.id})">
                    <i class="fas fa-trash"></i> 退课
                  </button>
                </td>
              </tr>
            `;
          });
          $('#selectedCourseList').html(html);
        }
      }
    });
  }

  // 选课
  window.selectCourse = function (courseId) {
    $.ajax({
      url: contextPath + '/api/student/addCourse',
      type: 'POST',
      data: {courseId: courseId},
      dataType: 'json',
      success: function (response) {
        if (response.code === 200) {
          showSuccess('选课成功！');
          loadCourseList();
          loadSelectedCourseList();
        } else {
          showError(response.message);
        }
      }
    });
  }

  // 退课
  window.dropCourse = function (id) {
    if (confirm('确定要退课吗？')) {
      $.ajax({
        url: contextPath + '/api/student/deleteCourse',
        type: 'POST',
        data: {id: id},
        dataType: 'json',
        success: function (response) {
          if (response.code === 200) {
            showSuccess('退课成功！');
            loadCourseList();
            loadSelectedCourseList();
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
      url: contextPath + '/api/student/update',
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