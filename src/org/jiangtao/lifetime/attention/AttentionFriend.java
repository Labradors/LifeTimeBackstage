package org.jiangtao.lifetime.attention;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.jiangtao.daoImpl.FriendOperateDaoImpl;
import org.jiangtao.lifetime.bean.Friend;
import org.jiangtao.util.BaseDao;
/**
 * /attentionUser.action
 * @author mr-jiang
 * 关注用户界面
 */
public class AttentionFriend extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html:charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String friend_user_id = request.getParameter("friend_user_id");
		String friend_another_id = request.getParameter("friend_another_id");
		System.out.println(friend_user_id);
		System.out.println(friend_another_id);
		FriendOperateDaoImpl daoImpl = new FriendOperateDaoImpl();
		
		try {
			boolean isSuccess =  daoImpl.insertFrioend(Integer.parseInt(friend_user_id),
					Integer.parseInt(friend_another_id));
			System.out.println(isSuccess);
			if (isSuccess) {
				JSONObject object =new JSONObject();
				object.put("isSuccess", "true");
				out.print(object);
			}else {
				JSONObject object =new JSONObject();
				object.put("isSuccess", "false");
				out.print(object);
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}

}
