package org.jiangtao.lifetime.bean;

public class Friend {

	public Friend() {
	}
	private int friend_id;
	private int friend_user_id;
	private int friend_another_id;
	public int getFriend_id() {
		return friend_id;
	}
	public void setFriend_id(int friend_id) {
		this.friend_id = friend_id;
	}
	public int getFriend_user_id() {
		return friend_user_id;
	}
	public void setFriend_user_id(int friend_user_id) {
		this.friend_user_id = friend_user_id;
	}
	public int getFriend_another_id() {
		return friend_another_id;
	}
	public void setFriend_another_id(int friend_another_id) {
		this.friend_another_id = friend_another_id;
	}
	@Override
	public String toString() {
		return "Friend [friend_id=" + friend_id + ", friend_user_id="
				+ friend_user_id + ", friend_another_id=" + friend_another_id
				+ "]";
	}
	

}
