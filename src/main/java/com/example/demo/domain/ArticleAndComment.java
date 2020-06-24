package com.example.demo.domain;

/**
 * 記事とコメントを合わせたドメインクラス.
 * 
 * @author hiroto.kitamura
 *
 */
public class ArticleAndComment {
	/**
	 * 記事のID.
	 */
	private Integer articleId;
	/**
	 * 記事の投稿者名.
	 */
	private String articleContributorName;
	/**
	 * 記事の内容.
	 */
	private String articleContent;
	/**
	 * コメントのID.
	 */
	private Integer commentId;
	/**
	 * コメントの投稿者名.
	 */
	private String commentContributorName;
	/**
	 * コメントの内容.
	 */
	private String commentContent;

	public ArticleAndComment() {
		// TODO Auto-generated constructor stub
	}

	public ArticleAndComment(Integer articleId, String articleContributorName, String articleContent, Integer commentId,
			String commentContributorName, String commentContent) {
		super();
		this.articleId = articleId;
		this.articleContributorName = articleContributorName;
		this.articleContent = articleContent;
		this.commentId = commentId;
		this.commentContributorName = commentContributorName;
		this.commentContent = commentContent;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getArticleContributorName() {
		return articleContributorName;
	}

	public void setArticleContributorName(String articleContributorName) {
		this.articleContributorName = articleContributorName;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getCommentContributorName() {
		return commentContributorName;
	}

	public void setCommentContributorName(String commentContributorName) {
		this.commentContributorName = commentContributorName;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	@Override
	public String toString() {
		return "ArticleAndComment [articleId=" + articleId + ", articleContributorName=" + articleContributorName
				+ ", articleContent=" + articleContent + ", commentId=" + commentId + ", commentContributorName="
				+ commentContributorName + ", commentContent=" + commentContent + "]";
	}

}
