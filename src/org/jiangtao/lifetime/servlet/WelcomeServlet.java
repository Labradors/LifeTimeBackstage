package org.jiangtao.lifetime.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 默认欢迎界面的动态变化 welcome.action
 * 
 * @author mr-jiang
 * 
 */
public class WelcomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InputStream in = null;
		ServletOutputStream out = null;

		try {
			// 设置响应的MIME类型
			// 图片响应类型
			response.setContentType("image/jpeg");
			out = response.getOutputStream();
			char c = (char) (Math.random() * 26 + 'a');
			in = new FileInputStream(new File("/home/mr-jiang/LifeTime/welcome"
					+ File.separator + c + ".jpg"));
			byte[] b = new byte[1024];
			int reader = 0;
			while ((reader = in.read(b)) != -1) {
				out.write(b, 0, reader);
			}
		} finally {
			if (in != null) {
				in.close();
			}

			if (out != null) {
				out.close();
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
