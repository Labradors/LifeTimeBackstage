package org.jiangtao.lifetime.servlet.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.jiangtao.lifetime.bean.User;
import org.jiangtao.util.BaseDao;
import org.jiangtao.util.Config;

public class LoginRequest extends HttpServlet {

	private static final long serialVersionUID = 1L;
	String userEmail = null;
	String passWord = null;
	JSONObject userObject = null;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html:charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		userEmail = request.getParameter("userEmail");
		passWord = request.getParameter("passWord");
		System.out.println(userEmail);
		System.out.println(passWord);
		@SuppressWarnings("unchecked")
		ArrayList<User> users = BaseDao.getListBySome(User.class, "user_email",
				userEmail);
		User user = new User();
		user = getUser(users);
		/**
		 * date日期配置
		 */
		JsonConfig jsonConfig = Config.getConfig();
		userObject = JSONObject.fromObject(user , jsonConfig);
		System.out.println(userObject);
		out.print(userObject);
		out.flush();
		out.close();
	}

	/**
	 * 解析对象，如果数据存在，解析后返回对象，如果不存在，返回null，客户端提示没有此用户，请注册。
	 */
	public User getUser(ArrayList<User> users) {
		for (User user : users) {
			if (user.getUser_email().equals(userEmail)) {
				if (user.getUser_password().equals(passWord)) {
					return user;
				}
			}
		}
		return null;
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	doPost(request, response);
	}
}
