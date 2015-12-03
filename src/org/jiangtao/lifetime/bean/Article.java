package org.jiangtao.lifetime.bean;

import java.sql.Timestamp;

public class Article {
	private int article_id;
	private String article_content;
	private Timestamp article_time;
	private int article_user_id;
	private String article_image;
	private int article_love_number;
	private int article_comment_number;

	public Article() {
	}

	public int getArticle_love_number() {
		return article_love_number;
	}

	public void setArticle_love_number(int article_love_number) {
		this.article_love_number = article_love_number;
	}

	public int getArticle_comment_number() {
		return article_comment_number;
	}

	public void setArticle_comment_number(int article_comment_number) {
		this.article_comment_number = article_comment_number;
	}

	public int getArticle_id() {
		return article_id;
	}

	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}

	public String getArticle_content() {
		return article_content;
	}

	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}

	public Timestamp getArticle_time() {
		return article_time;
	}

	public void setArticle_time(Timestamp time) {
		this.article_time =  time;
	}

	public int getArticle_user_id() {
		return article_user_id;
	}

	public void setArticle_user_id(int article_user_id) {
		this.article_user_id = article_user_id;
	}
	public String getArticle_image() {
		return article_image;
	}

	public void setArticle_image(String article_image) {
		this.article_image = article_image;
	}

	@Override
	public String toString() {
		return "Article [article_id=" + article_id + ", article_content="
				+ article_content + ", article_time=" + article_time
				+ ", article_user_id=" + article_user_id + ", article_image="
				+ article_image + ", article_love_number="
				+ article_love_number + ", article_comment_number="
				+ article_comment_number + "]";
	}

}
