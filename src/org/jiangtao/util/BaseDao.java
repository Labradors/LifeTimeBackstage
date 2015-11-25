package org.jiangtao.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * 万能Dao，一个类实现多张表的增删查改
 * 
 * @author mr-jiang
 * 
 */
public class BaseDao {
	/**
	 * 查询万能Dao
	 * 
	 * @param cl
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList getList(Class cl) {
		ArrayList ar = new ArrayList();
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select *  from " + cl.getSimpleName();
		Field[] fi = cl.getDeclaredFields();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Object ob = cl.newInstance();
				for (Field ff : fi) {
					ff.setAccessible(true);
					ff.set(ob, rs.getObject(ff.getName()));
				}
				ar.add(ob);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseConnection.closeRes(conn, ps, rs);
		}
		return ar;
	}

	/**
	 * 根据id查看数据库对象
	 * 
	 * @param cl
	 * @param i
	 * @return
	 */
	public static Object getObjectById(Class cl, int i) {
		Object object = null;
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Field[] fi = cl.getDeclaredFields();
		String sql = "select *  from " + cl.getSimpleName() + " where "
				+ fi[0].getName() + " = " + i;
		System.out.println(sql);
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				object = cl.newInstance();
				for (Field ff : fi) {
					ff.setAccessible(true);
					ff.set(object, rs.getObject(ff.getName()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseConnection.closeRes(conn, ps, rs);
		}
		return object;

	}
	/**
	 * 一个条件的查询
	 * 列名
	 * 值
	 * @param cl
	 * @param name
	 * @param value
	 * @return
	 */
	public static ArrayList getListBySome(Class cl ,String name,Object value){
		ArrayList ar = new ArrayList();
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Field[] fi = cl.getDeclaredFields();
		String sql = "select * from "+cl.getSimpleName()+" where "+name+" = '"+value+"'";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Object ob = cl.newInstance();
				for(Field ff : fi){
					ff.setAccessible(true);
					ff.set(ob, rs.getObject(ff.getName()));
				}
				ar.add(ob);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			BaseConnection.closeRes(conn, ps, rs);
		}
		return ar;
	
	}
	/**
	 * 未优化的添加方法
	 * @param ob
	 * @return
	 */
	public static  boolean insert(Object ob){
		boolean b = false;
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		Class cl = ob.getClass();
		Field[] fi = cl.getDeclaredFields();
		//insert into animals (name,age,anid) values(?,?,?)
		String sql = "insert into "+cl.getSimpleName()+" (";
		for(int i = 1;i<fi.length;i++){
			sql = sql+fi[i].getName();
			//4  0 1 2 3
			if(i!=fi.length-1){
				sql = sql+" , ";
			}
		}
		sql = sql+") values (";
		for(int i = 1;i<fi.length;i++){
			sql = sql+" ? ";
			if(i!=fi.length-1){
				sql = sql+" , ";
			}
		}
		sql = sql+")";
		try {
			ps = conn.prepareStatement(sql);
			for(int i = 1;i<fi.length;i++){
				fi[i].setAccessible(true);
				ps.setObject(i, fi[i].get(ob));
			}
			int a = ps.executeUpdate();
			if(a>0){
				b = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			BaseConnection.closeRes(conn, ps);
		}
		return b;
	}
	/**
	 * 优化后的添加方法
	 * @param ob
	 * @return
	 */
	public static boolean insert1(Object ob){
		boolean b = false;
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		Class cl = ob.getClass();
		Field[] fi = cl.getDeclaredFields();
		StringBuffer sb = new StringBuffer();
		//insert into animals (name,age,anid) values(?,?,?)
		sb.append("insert into ");
		sb.append(cl.getSimpleName());
		sb.append(" (");
		for(int i = 1;i<fi.length;i++){
			sb.append(fi[i].getName());
			if(i!=fi.length-1){
				sb.append(" , ");
			}
		}
		sb.append(") values (");
		for(int i = 1;i<fi.length;i++){
			sb.append(" ? ");
			if(i!=fi.length-1){
				sb.append(" , ");
			}
		}
		sb.append(" ) ");
		try {
			ps = conn.prepareStatement(sb.toString());
			for(int i = 1;i<fi.length;i++){
				fi[i].setAccessible(true);
				ps.setObject(i, fi[i].get(ob));
			}
			int a = ps.executeUpdate();
			if(a>0){
				b = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			BaseConnection.closeRes(conn, ps);
		}
		return b;
	}
	
/**
 * 更新数据的方法
 * @param ob
 * @return
 */
	public static boolean update(Object ob){
		boolean b = false;
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		Class cl = ob.getClass();
		Field[] fi = cl.getDeclaredFields();
		StringBuffer sb = new StringBuffer();
		//update animals set name = ?,age = ?,anid = ? where id = ?
		sb.append(" update ");
		sb.append(cl.getSimpleName());
		sb.append(" set ");
		for(int i = 1;i<fi.length;i++){
			fi[i].setAccessible(true);
			sb.append(fi[i].getName());
			sb.append(" = ? ");
			if(i!=fi.length-1){
				sb.append(" , ");
			}
		}
		sb.append(" where ");
		sb.append(fi[0].getName());
		sb.append("=?");
		
		try {
			ps = conn.prepareStatement(sb.toString());
			for(int i = 1;i<fi.length;i++){
				fi[i].setAccessible(true);
				ps.setObject(i, fi[i].get(ob));
			}
			fi[0].setAccessible(true);
			ps.setObject(fi.length, fi[0].get(ob));
			int a = ps.executeUpdate();
			if(a>0){
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			BaseConnection.closeRes(conn, ps);
		}
		return b;
	}
	/**
	 * 根据id删除
	 * @param cl
	 * @param id
	 * @return
	 */
	public static boolean delete(Class cl , int id){
		boolean b = false;
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		Field[] fi = cl.getDeclaredFields();
		String sql = "delete from "+cl.getSimpleName()+" where "+fi[0].getName()+" = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setObject(1, id);
			int a = ps.executeUpdate();
			if(a>0){
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			BaseConnection.closeRes(conn, ps);
		}
	return b ;
	
	}
	/**
	 * 条件删除
	 * @param cl
	 * @param name
	 * @param value
	 * @return
	 */
	public static boolean deleteBySome(Class cl , String name,Object value){
		boolean b = false;
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		Field[] fi = cl.getDeclaredFields();
		String sql = "delete from "+cl.getSimpleName()+" where "+name+" = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setObject(1, value);
			int a = ps.executeUpdate();
			if(a>0){
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			BaseConnection.closeRes(conn, ps);
		}
	return b ;
	
	}
}
