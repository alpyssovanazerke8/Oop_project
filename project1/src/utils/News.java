package utils;

import enums.NewsType;

public class News {
	private String title;
	private String content;
	private NewsType newsType;
	
	public News() {
		
	}
	
	public News(String title, String content, NewsType newsType) {
		this.title = title;
		this.content = content;
		if (newsType == null) {
            throw new IllegalArgumentException("News type cannot be null");
        }
        this.newsType = newsType;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public NewsType getNewsType() {
		return newsType;
	}
	public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}
	
	
	
}
