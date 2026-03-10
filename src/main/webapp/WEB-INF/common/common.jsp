<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Favicon -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
<!-- 设置上下文路径全局变量 -->
<script>
  contextPath = '${pageContext.request.contextPath}';
</script>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/vendor/bootstrap/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/vendor/font-awesome/css/all.min.css">
<!-- Custom CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/common/common.css">

<!-- 成功提示 -->
<div class="toast-container position-fixed top-50 start-50 translate-middle">
  <div id="successToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
    <div class="toast-header bg-success text-white">
      <strong class="me-auto">操作成功</strong>
      <button type="button" class="btn-close btn-close-white" data-bs-dismiss="toast" aria-label="Close"></button>
    </div>
    <div class="toast-body">
      操作成功！
    </div>
  </div>
</div>

<!-- 失败提示 -->
<div class="toast-container position-fixed top-50 start-50 translate-middle">
  <div id="errorToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
    <div class="toast-header bg-danger text-white">
      <strong class="me-auto">操作失败</strong>
      <button type="button" class="btn-close btn-close-white" data-bs-dismiss="toast" aria-label="Close"></button>
    </div>
    <div class="toast-body">
      操作失败！
    </div>
  </div>
</div>

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/static/js/vendor/jquery/jquery.min.js"></script>
<!-- Bootstrap JS -->
<script src="${pageContext.request.contextPath}/static/js/vendor/bootstrap/bootstrap.bundle.min.js"></script>
<!-- Custom JS -->
<script src="${pageContext.request.contextPath}/static/js/common/common.js"></script>