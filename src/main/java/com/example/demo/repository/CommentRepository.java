package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
	 * コメントのRowMapper.
	 */
	private static final RowMapper<Comment> COMMENT_ROWMAPPER = new BeanPropertyRowMapper<Comment>(Comment.class);

	/**
	 * 記事IDからその記事についたコメントをIDの降順で取得.
	 * 
	 * @param articleId 記事ID
	 * @return 取得されたコメントのリスト
	 */
	public List<Comment> findByArticleId(Integer articleId) {
		String sql = "SELECT id,name,content,article_id FROM comments WHERE article_id=:articleId ORDER BY id DESC;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		return template.query(sql, param, COMMENT_ROWMAPPER);
	}

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
