package org.jiangtao.lifetime.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jiangtao.lifetime.bean.User;
import org.jiangtao.util.BaseDao;

/**
 * /registerinformation.action
 * 插入数据库
 * @author mr-jiang /registerinformation.action
 */
public class RegisterInformationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html:charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println("你好");
		/**
		 * 查询表中所有的信息
		 */
		
		/*BaseDao bdBaseDao = new BaseDao();
		@SuppressWarnings("unchecked")
		ArrayList<User> users = bdBaseDao.getList(User.class);
		if (users!=null) {
			for (User user:users) {
				System.out.println("是否为空");
				System.out.println(user.getUser_name());
			}
		}*/
		
		/**
		 * 根据id查询表中的数据
		 */
		
		/*BaseDao bdBaseDao = new BaseDao();
		Object object = bdBaseDao.getObjectById(User.class, 1000);
		System.out.println(object.toString());*/
		
		/**
		 * 根据单个条件查询，字段=值
		 */
//		BaseDao bdBaseDao = new BaseDao();
//		@SuppressWarnings("unchecked")
//		ArrayList<User> users = bdBaseDao.getListBySome(User.class, "user_email", "jiangtao103cp@163.com");
//		if (users!=null) {
//			for (User user:users) {
//				System.out.println("是否为空");
//				System.out.println(user.toString());
//			}
//		}
		
		/**
		 * 更新数据
		 */
		BaseDao bdBaseDao = new BaseDao();
		User user = new User();
		user.setUser_email("jiangtao103cp@gmail.com");
		boolean b = bdBaseDao.update(user);
		System.out.println(b);
		out.flush();
		out.close();
	}

}
