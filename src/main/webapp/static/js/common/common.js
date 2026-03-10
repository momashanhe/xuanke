// 显示成功提示
function showSuccess(message) {
  $('#successToast .toast-body').text(message);
  const successToast = document.getElementById('successToast');
  const toast = new bootstrap.Toast(successToast, {
    autohide: true,
    delay: 1000
  });
  toast.show();
}

// 显示失败提示
function showError(message) {
  $('#errorToast .toast-body').text(message);
  const successToast = document.getElementById('errorToast');
  const toast = new bootstrap.Toast(successToast, {
    autohide: true,
    delay: 1500
  });
  toast.show();
}