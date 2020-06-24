package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Article;
import com.example.demo.repository.ArticleRepository;

@Controller
@Transactional
@RequestMapping("")
public class ArticleController {
	@Autowired
	private ArticleRepository articleRepository;

	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleRepository.findAll();
		if (articleList.size() == 0) {
			model.addAttribute("noArticle", "記事がありません");
		}
		model.addAttribute("articleList", articleList);
		return "bbs";
	}
}
