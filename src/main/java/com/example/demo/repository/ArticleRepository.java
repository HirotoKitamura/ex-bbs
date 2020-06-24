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

import com.example.demo.domain.Article;
import com.example.demo.domain.ArticleAndComment;

/**
 * 記事のリポジトリクラス.
 * 
 * @author hiroto.kitamura
 *
 */
@Repository
public class ArticleRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 記事のRowMapper.
	 */
//	private final static RowMapper<Article> ARTICLE_ROWPAPPER = new BeanPropertyRowMapper<Article>(Article.class);
	private final static RowMapper<ArticleAndComment> ARTICLE_AND_COMMENT_ROWMAPPER = new BeanPropertyRowMapper<>(
			ArticleAndComment.class);
//			(rs, i) -> {
//		return new Article(rs.getInt("id"), rs.getString("name"), rs.getString("content"));
//	};

	/**
	 * 記事を記事ID(投稿日時の昇順に附番)の降順で全件検索.
	 * 
	 * @return 記事のリスト
	 */
//	public List<Article> findAll() {
//		String sql = "SELECT id,name,content FROM articles ORDER BY id DESC;";
//		return template.query(sql, ARTICLE_ROWPAPPER);
//	}

	/**
	 * 記事とコメントを記事ID(投稿日時の昇順に附番)の降順、次いでコメントIDの降順で全件検索.
	 * 
	 * OUTER JOINでコメントがない記事も検索できる
	 * 
	 * @return 記事とコメントのリスト
	 */
	public List<ArticleAndComment> findAll() {
		String sql = "SELECT a.id article_id, a.name article_contributor_name"
				+ ", a.content article_content, c.id comment_id, c.name comment_contributor_name"
				+ ", c.content comment_content FROM articles a LEFT JOIN comments c"
				+ " ON a.id = c.article_id ORDER BY a.id DESC, c.id DESC;";
		return template.query(sql, ARTICLE_AND_COMMENT_ROWMAPPER);
	}

	/**
	 * 記事をデータベースに挿入.
	 * 
	 * @param article 挿入する記事
	 */
	public void insert(Article article) {
		String sql = "INSERT INTO articles (name,content) VALUES (:name,:content);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		template.update(sql, param);
	}

	/**
	 * IDから記事をコメントごと削除.
	 * 
	 * @param id 削除する記事のID
	 */
	public void deleteById(Integer id) {
		String sql = "DELETE FROM articles WHERE id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
}
