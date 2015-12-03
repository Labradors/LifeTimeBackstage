package org.jiangtao.lifetime.servlet.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.jiangtao.lifetime.bean.User;
import org.jiangtao.util.BaseDao;
import org.jiangtao.util.SendEmailValidate;

public class RetrievePasswordResuest extends HttpServlet {
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
		String userEmail = request.getParameter("userEmail");
		JSONObject object = new JSONObject();
		System.out.println(">>>>>>>>>>>>");
		@SuppressWarnings("unchecked")
		ArrayList<User> users = BaseDao.getListBySome(User.class, "user_email",
				userEmail);
		for (User user : users) {
			if (user != null) {
					try {
						SendEmailValidate.sendPassword(userEmail,
								user.getUser_password());
						System.out.println(user.getUser_email());
						System.out.println(user.getUser_password());
						object.put("flag", true);
						out.print(object);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} else {
				object.put("flag", false);
				out.print(object);
			}
		}
		out.flush();
		out.close();
	}

}
