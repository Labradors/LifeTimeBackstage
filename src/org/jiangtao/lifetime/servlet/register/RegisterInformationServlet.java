package org.jiangtao.lifetime.servlet.register;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.jiangtao.lifetime.bean.User;
import org.jiangtao.util.BaseDao;

/**
 * /registerinformation.action
 * 插入数据库
 * @author mr-jiang /registerinformation.action
 */
public class RegisterInformationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String imageUrl ="/home/mr-jiang/LifeTime/headImage"
			+ File.separator  + "c.jpg";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html:charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		String email = request.getParameter("email");
		System.out.println(userName+passWord+email);
		User user = fillUser(userName, passWord, email,imageUrl);
		@SuppressWarnings("unused")
		BaseDao baseDao = new BaseDao();
		/**
		 * 返回插入是否正确
		 */
		boolean isTrue =BaseDao.insert(user);
		System.out.println(isTrue);
		/**
		 * 获得其id值
		 */
		int user_id = 0;
		@SuppressWarnings("unchecked")
		ArrayList<User> idUser = BaseDao.getListBySome(User.class, "user_email", email);
		for(User user2:idUser){
			user_id = user2.getUser_id();
		}
		System.out.println(user_id);
		JSONObject object = new JSONObject();
		object.put("flag", isTrue);
		object.put("id", user_id);
		out.print(object);
		out.flush();
		out.close();
	}
	/**
	 * 填充user进行插入
	 * @param userName
	 * @param passWord
	 * @param email
	 * @return
	 */
	public static User fillUser(String userName,String passWord,String email,String url){
		User user = new User();
		user.setUser_name(userName);
		user.setUser_password(passWord);
		user.setUser_email(email);
		user.setUser_headpicture(url);
		Date date = new Date(2015-10-10);
		user.setUser_jointime(date);
		user.setUser_sex(null);
		user.setUser_phone(null);
		return user;
		
	}

}
