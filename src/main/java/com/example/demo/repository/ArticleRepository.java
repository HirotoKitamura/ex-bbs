package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
	private final static RowMapper<Article> ARTICLE_ROWPAPPER = (rs, i) -> {
		return new Article(rs.getInt("id"), rs.getString("name"), rs.getString("content"));
	};

	/**
	 * 記事を記事ID(投稿日時の昇順に附番)の降順で全件検索.
	 * 
	 * @return 記事のリスト
	 */
	public List<Article> findAll() {
		String sql = "SELECT id,name,content FROM articles ORDER BY id DESC;";
		return template.query(sql, ARTICLE_ROWPAPPER);
	}
}
