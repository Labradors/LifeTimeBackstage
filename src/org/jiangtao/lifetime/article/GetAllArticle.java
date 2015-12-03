package org.jiangtao.lifetime.article;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jiangtao.daoImpl.AllDynamicDaoImpl;
import org.jiangtao.lifetime.bean.IndexDynamic;
/**
 * /getAllArticle.action
 * @author mr-jiang
 * 获取所有用户所发表的内容
 *
 */
public class GetAllArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html:charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		AllDynamicDaoImpl dynamicDaoImpl = AllDynamicDaoImpl.getInstance();
		ArrayList<IndexDynamic> dynamicsList = new ArrayList<>();
		try {
			dynamicsList = dynamicDaoImpl.getAllDynamics();
			for (IndexDynamic indexDynamic : dynamicsList) {
				System.out.println(indexDynamic.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray array = JSONArray.fromObject(dynamicsList);
		out.print(array);
		out.flush();
		out.close();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
