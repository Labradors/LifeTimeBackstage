package org.jiangtao.lifetime.article;

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
 * 得到文章的图片
 * @author mr-jiang
 * /getArticleImage.action
 */
public class ArticleBitmap extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		InputStream in = null;
		ServletOutputStream out = null;

		try {
			// 设置响应的MIME类型
			// 图片响应类型
			String url = request.getParameter("user_image");
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
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InputStream in = null;
		ServletOutputStream out = null;

		try {
			// 设置响应的MIME类型
			// 图片响应类型
			String url = request.getParameter("user_image");
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
