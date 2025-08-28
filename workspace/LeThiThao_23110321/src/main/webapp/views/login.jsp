<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Đăng nhập</title></head>
<body>
  <h2>Đăng nhập</h2>
  <c:if test="${alert != null}">
    <div style="color:red">${alert}</div>
  </c:if>
  <form action="login" method="post">
    <label>Tài khoản</label>
    <input type="text" name="username" />
    <br/>
    <label>Mật khẩu</label>
    <input type="password" name="password" />
    <br/>
    <label><input type="checkbox" name="remember" /> Ghi nhớ</label>
    <br/>
    <button type="submit">Đăng nhập</button>
  </form>
  <a href="${pageContext.request.contextPath }/register">Tạo tài khoản mới</a>
</body>
</html>
