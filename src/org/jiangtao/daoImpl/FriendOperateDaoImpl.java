package org.jiangtao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.jiangtao.dao.FriendOperateDao;
import org.jiangtao.util.BaseConnection;

public class FriendOperateDaoImpl implements FriendOperateDao {
/**
 * insert into Friend(friend_user_id,friend_another_id) select 1,4 from dual where not exists(
select * from Friend where friend_user_id = 1 and friend_another_id=4); 
 */
	public FriendOperateDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean insertFrioend(int friend_user_id, int friend_another_id)
			throws Exception { 
		Connection connection = BaseConnection.getConnection();
		String sql = "insert into Friend(friend_user_id,friend_another_id) select "+
		friend_user_id+","+friend_another_id+" from dual where not exists( select * from Friend where friend_user_id = "+
				friend_user_id+" and friend_another_id = "+friend_another_id+")";
		System.out.println(sql);
		PreparedStatement ps = connection.prepareStatement(sql);
		int a = ps.executeUpdate();
		if (a>0) {
			return true;
		}
		return false ;
	}

}
