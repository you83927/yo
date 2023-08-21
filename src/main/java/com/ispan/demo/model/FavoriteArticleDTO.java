package com.ispan.demo.model;

public class FavoriteArticleDTO {
	private Article article;
    private User user;
    
    public FavoriteArticleDTO(Article article, User user) {
        this.article = article;
        this.user = user;
    }

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    
}
