package org.jiangtao.lifetime.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.jiangtao.bean.OtherUser;
import org.jiangtao.lifetime.bean.User;
import org.jiangtao.util.BaseDao;
/**
 * 点击用户头像，查看用户信息
 * @author mr-jiang
 *
 */
public class obtainOtherUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html:charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String user_id = request.getParameter("user_id");
		PrintWriter out = response.getWriter();
		OtherUser user = new OtherUser();
		User obUser = (User) BaseDao.getObjectById(User.class, Integer.parseInt(user_id));
		System.out.println(obUser.toString());
		user.setUser_id(obUser.getUser_id());
		user.setUser_name(obUser.getUser_name());
		user.setUser_headpicture(obUser.getUser_headpicture());
		user.setUser_time(String.valueOf(obUser.getUser_jointime()));
		user.setUser_sex(obUser.getUser_sex());
		JSONObject object = JSONObject.fromObject(user.toString());
		out.print(object);
		out.flush();
		out.close();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html:charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String user_id = request.getParameter("user_id");
		PrintWriter out = response.getWriter();
		OtherUser user = new OtherUser();
		User obUser = (User) BaseDao.getObjectById(User.class, Integer.parseInt(user_id));
		System.out.println(obUser.toString());
		user.setUser_id(obUser.getUser_id());
		user.setUser_name(obUser.getUser_name());
		user.setUser_headpicture(obUser.getUser_headpicture());
		user.setUser_time(String.valueOf(obUser.getUser_jointime()));
		user.setUser_sex(obUser.getUser_sex());
		JSONObject object = new JSONObject();
		object.put("user", user);
		out.print(object);
		out.flush();
		out.close();
	}

}
