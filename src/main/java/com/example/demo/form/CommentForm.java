package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
	@NotBlank(message = "名前を入力してください")
	@Size(max = 50, message = "名前は50文字以内で入力してください")
	private String name;
	/**
	 * 投稿内容.
	 */
	@NotBlank(message = "内容を入力してください")
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
