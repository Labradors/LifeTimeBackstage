package org.jiangtao.lifetime.servlet.android.register;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.jiangtao.register.validate.SendEmailValidate;

/**
 * /sendvalidate.action
 * 
 * @author mr-jiang 发送验证码
 */
public class SendValidate extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html:charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String userEmail = request.getParameter("mEmail");
		System.out.println(userEmail);
		if (userEmail != null) {
			int random = (int) ((Math.random() * 9 + 1) * 100000);
			try {
				SendEmailValidate.sendEmail(userEmail, String.valueOf(random));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/**
			 * 1.发送验证码到本地
			 */
			JSONObject mEmail = JSONObject.fromObject(random);
			out.print(mEmail);
		}
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
