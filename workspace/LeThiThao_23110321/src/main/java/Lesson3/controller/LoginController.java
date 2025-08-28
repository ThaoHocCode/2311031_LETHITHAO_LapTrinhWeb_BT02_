package Lesson3.controller;
import Lesson3.service.*;
import Lesson3.model.User;
import Lesson3.utils.Constant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession(false);
    if (session != null && session.getAttribute("account") != null) {
      resp.sendRedirect(req.getContextPath()+ "/waiting"); // theo slide :contentReference[oaicite:14]{index=14}
      return;
    }
    // Check cookie "username" (remember me)
    Cookie[] cookies = req.getCookies();
    if (cookies != null) {
      for (Cookie c : cookies) {
        if (Constant.COOKIE_REMEMBER.equals(c.getName())) {
          session = req.getSession(true);
          session.setAttribute(Constant.SESSION_USERNAME, c.getValue());
          resp.sendRedirect(req.getContextPath()+ "/waiting"); // theo slide :contentReference[oaicite:15]{index=15}
          return;
        }
      }
    }
    req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");

    String username = req.getParameter("username");
    String password = req.getParameter("password");
    boolean isRememberMe = "on".equals(req.getParameter("remember"));

    if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
      req.setAttribute("alert", "Tài khoản hoặc mật khẩu không được rỗng"); // theo slide :contentReference[oaicite:16]{index=16}
      req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
      return;
    }

    UserService service = new UserServiceImpl();
    User user = service.login(username, password);

    if (user != null) {
      HttpSession session = req.getSession(true);
      session.setAttribute("account", user);
      if (isRememberMe) saveRememberMe(resp, username); // theo slide :contentReference[oaicite:17]{index=17}
      resp.sendRedirect(req.getContextPath()+"/waiting");
    } else {
      req.setAttribute("alert", "Tài khoản hoặc mật khẩu không đúng"); // theo slide :contentReference[oaicite:18]{index=18}
      req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }
  }

  private void saveRememberMe(HttpServletResponse response, String username) {
    Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
    cookie.setMaxAge(30*60); // 30 phút, giống slide :contentReference[oaicite:19]{index=19}
    response.addCookie(cookie);
  }
}
