package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Comment;

/**
 * コメントのリポジトリクラス.
 * 
 * @author hiroto.kitamura
 *
 */
@Repository
public class CommentRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * コメントをデータベースに挿入.
	 * 
	 * @param comment 挿入するコメント
	 */
	public void insert(Comment comment) {
		String sql = "INSERT INTO comments (name,content,article_id) VALUES(:name,:content,:articleId);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
		template.update(sql, param);
	}
}
