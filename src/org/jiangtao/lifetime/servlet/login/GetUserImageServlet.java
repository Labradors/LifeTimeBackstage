package org.jiangtao.lifetime.servlet.login;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取用户头像
 * 
 * @author mr-jiang
 * 
 */
public class GetUserImageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		InputStream in = null;
		ServletOutputStream out = null;

		try {
			// 设置响应的MIME类型
			// 图片响应类型
			String url = request.getParameter("headImageUri");
			response.setContentType("image/jpeg");
			System.out.println(url);
			out = response.getOutputStream();
			in = new FileInputStream(new File(url));
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

}
