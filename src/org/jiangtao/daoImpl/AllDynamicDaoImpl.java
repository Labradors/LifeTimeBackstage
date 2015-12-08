package org.jiangtao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.jiangtao.dao.AllDynamicDao;
import org.jiangtao.lifetime.bean.AllDynamicString;
import org.jiangtao.lifetime.bean.IndexDynamic;
import org.jiangtao.util.BaseConnection;

public class AllDynamicDaoImpl implements AllDynamicDao {
private static AllDynamicDaoImpl dynamicDaoImpl;
	private AllDynamicDaoImpl() {
	
	}
	public static AllDynamicDaoImpl getInstance(){
		if (dynamicDaoImpl==null) {
			dynamicDaoImpl = new AllDynamicDaoImpl();
		}
		return dynamicDaoImpl;
	}

	@Override
	public ArrayList<AllDynamicString> getAllDynamics() throws Exception {
		ArrayList<AllDynamicString> indexDynamicsList = new ArrayList<>();
		Connection connection = BaseConnection.getConnection();
		/**
		 * 如果根据时间排序
		 * order by article_id desc
		 */
		String sql = "select user_name,user_headpicture, article_id,article_user_id,article_time,article_content,article_image,article_love_number, "+
"article_comment_number from User,Article where user_id = article_user_id  ";
		System.out.println(sql);
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
		AllDynamicString daDynamic = new AllDynamicString();
		daDynamic.setUser_name(resultSet.getString("user_name"));
		daDynamic.setUser_headpicture(resultSet.getString("user_headpicture"));
		daDynamic.setArticle_id(resultSet.getInt("article_id"));
		daDynamic.setArticle_user_id(resultSet.getInt("article_user_id"));
		Timestamp timestamp = resultSet.getTimestamp("article_time");
		daDynamic.setArticle_time(timestamp.toString());
		daDynamic.setArticle_content(resultSet.getString("article_content"));
		daDynamic.setArticle_image(resultSet.getString("article_image"));
		daDynamic.setArticle_love_number(resultSet.getInt("article_love_number"));
		daDynamic.setArticle_comment_number(resultSet.getInt("article_comment_number"));
		indexDynamicsList.add(daDynamic);
		}
		return indexDynamicsList;
	}
	
	

}
