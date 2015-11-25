package org.jiangtao.lifetime.servlet.register;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.jiangtao.lifetime.bean.ValidateCodeList;
import org.jiangtao.util.SendEmailValidate;

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
		ValidateCodeList codeList = ValidateCodeList.getInstance();
		PrintWriter out = response.getWriter();
		String userEmail = request.getParameter("email");
		System.out.println(userEmail);
		if (userEmail != null) {
			int random = (int) ((Math.random() * 9 + 1) * 100000);
			codeList.validateList.add(0, String.valueOf(random));
			System.out.println(codeList.validateList.get(0));
			try {
				SendEmailValidate.sendEmail(userEmail,
						codeList.validateList.get(0));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/**
			 * 1.发送验证码到本地
			 */
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("email", codeList.validateList.get(0));
			out.print(jsonObject);
			codeList.validateList.clear();
		}
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
