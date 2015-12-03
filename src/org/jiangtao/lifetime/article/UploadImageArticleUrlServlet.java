package org.jiangtao.lifetime.article;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import net.sf.json.JSONObject;

import org.jiangtao.lifetime.bean.Article;
import org.jiangtao.util.BaseDao;
import org.jiangtao.util.ConstantValues;

@MultipartConfig
public class UploadImageArticleUrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		// 获取文章内容
		String article_content = request.getParameter("article_content");
		String user_id = request.getParameter("user_id");
		Part part = request.getPart("article_image");
		String file_name = request.getParameter("file_name");
		String imageUri = ConstantValues.baseUri + file_name;
		System.out.println(user_id + "    " + file_name + "   " + imageUri);
		part.write(imageUri);
		// 插入数据库
		BaseDao baseDao = new BaseDao();
		Article article = new Article();
		article.setArticle_image(imageUri);
		article.setArticle_user_id(Integer.valueOf(user_id));
		article.setArticle_content(article_content);
		boolean isInsertRight = baseDao.insert1(article);
		JSONObject object = new JSONObject();
		object.put("flag", isInsertRight);
		out.print(object);
		out.flush();
		out.close();
	}

	/**
	 * 获取服务器错误
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println(request.getParameter("user_id"));
		// 获取文章内容
		String article_content = request.getParameter("article_content");
		System.out.println(article_content);
		String user_id = request.getParameter("user_id");
		Part part = request.getPart("article_image");
		String file_name = request.getParameter("file_name");
		if (part != null) {
			String imageUri = ConstantValues.baseUri + file_name;
			System.out.println(user_id + "    " + file_name + "   " + imageUri);
			part.write(imageUri);
		}
		// 插入数据库
		BaseDao baseDao = new BaseDao();
		Article article = new Article();
		Timestamp sTimestamp = new Timestamp(System.currentTimeMillis());
		article.setArticle_time(sTimestamp);
		article.setArticle_image(ConstantValues.baseUri + file_name);
		article.setArticle_user_id(Integer.parseInt(user_id.trim()));
		article.setArticle_content(article_content);
		boolean isInsertRight = baseDao.insert1(article);
		JSONObject object = new JSONObject();
		object.put("flag", isInsertRight);
		out.print(object);
		out.flush();
		out.close();
	}

}
