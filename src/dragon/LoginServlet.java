package dragon;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	dragon(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	dragon(request, response);
    }

    public void dragon(HttpServletRequest request, HttpServletResponse response) {
	// 设置返回文本类型
	response.setContentType("text/html;charset=UTF-8");
	getServerAndClientInformation(request, response);
	// 获取用户名参数
	String username = request.getParameter("username");
	// 获取密码参数
	String password = request.getParameter("password");
	String msg = "";
	// 如果参数任意一个为空则失败
	System.out.println("dragon " + username + " " + password);
	if (username == null || "".equals(username) || password == null || "".equals(password)) {
	    msg = "用户名或密码为空，请<a href=login.html>重新登录</a>";
	}
	// 如果用户名和密码不正确，也失败。这里规定用户名=admin，密码=123
	else if (!"admin".equals(username) || !"123".equals(password)) {
	    msg = "用户名或密码错误，请<a href=login.html>重新登录</a>";
	}
	// 登录成功，则打印登录成功信息
	else if ("admin".equals(username) && "123".equals(password)) {
	    msg = "登录成功，欢迎你：" + username;
	}
	PrintWriter out;
	try {
	    out = response.getWriter();
	    out.println("<HTML>");
	    out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
	    out.println("  <BODY>");
	    out.println(msg);
	    out.println("  </BODY>");
	    out.println("</HTML>");
	    out.flush();
	    out.close();// 关闭输出流
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void getServerAndClientInformation(HttpServletRequest request, HttpServletResponse response) {
	String clientIP = null;
	int clientPort = 0;
	String serverIP = null;
	int serverPort = 0;

	clientIP = request.getRemoteAddr();
	clientPort = request.getRemotePort();
	serverIP = request.getLocalAddr();
	serverPort = request.getLocalPort();

	System.out.println("clientIP: " + clientIP + " clientPort: " + clientPort);
	System.out.println("serverIP " + serverIP + " serverPort: " + serverPort);
    }

}
