package com.example.demo.form;

/**
 * コメントのフォーム.
 * 
 * @author hiroto.kitamura
 *
 */
public class CommentForm {
	/**
	 * 投稿者名.
	 */
	private String name;
	/**
	 * 投稿内容.
	 */
	private String content;
	/**
	 * このコメントをつける記事のID(hiddenで自動取得).
	 */
	private Integer articleId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	@Override
	public String toString() {
		return "CommentForm [name=" + name + ", content=" + content + ", articleId=" + articleId + "]";
	}

}
