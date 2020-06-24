package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Article;

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
	private final static RowMapper<Article> ARTICLE_ROWPAPPER = new BeanPropertyRowMapper<Article>(Article.class);
//			(rs, i) -> {
//		return new Article(rs.getInt("id"), rs.getString("name"), rs.getString("content"));
//	};

	/**
	 * 記事を記事ID(投稿日時の昇順に附番)の降順で全件検索.
	 * 
	 * @return 記事のリスト
	 */
	public List<Article> findAll() {
		String sql = "SELECT id,name,content FROM articles ORDER BY id DESC;";
		return template.query(sql, ARTICLE_ROWPAPPER);
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
}
