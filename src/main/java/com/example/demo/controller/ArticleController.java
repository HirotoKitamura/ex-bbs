package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.Article;
import com.example.demo.domain.Comment;
import com.example.demo.form.ArticleForm;
import com.example.demo.form.CommentForm;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.CommentRepository;

/**
 * 記事のコントローラークラス.
 * 
 * @author hiroto.kitamura
 *
 */
@Controller
@Transactional
@RequestMapping("")
public class ArticleController {
	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private HttpSession session;

	@ModelAttribute
	private ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}

	@ModelAttribute
	private CommentForm setUpCommentForm() {
		return new CommentForm();
	}

	/**
	 * 記事一覧を表示.
	 * 
	 * @param model リクエストスコープ
	 * @return 記事一覧ページ
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleRepository.findAll();
		if (articleList.size() == 0) {
			model.addAttribute("noArticle", "記事がありません　投稿してね");
		}
		model.addAttribute("articleList", articleList);
		return "bbs";
	}

	/**
	 * 記事を投稿.
	 * 
	 * @param articleForm 投稿フォームの入力内容
	 * @return 記事一覧にリダイレクト
	 */
	@RequestMapping("insertArticle")
	public String insertArticle(@Validated ArticleForm articleForm, BindingResult result, Model model) {
		session.setAttribute("name", articleForm.getName());
		if (result.hasErrors()) {
			return index(model);
		}
		Article article = new Article();
		BeanUtils.copyProperties(articleForm, article);
		articleRepository.insert(article);
		return "redirect:/";
	}

	/**
	 * コメントを投稿.
	 * 
	 * @param commentForm 投稿フォームの入力内容
	 * @return 記事一覧にリダイレクト
	 */
	@RequestMapping("insertComment")
	public String insertComment(@Validated CommentForm commentForm, BindingResult result, Model model) {
		session.setAttribute("name", commentForm.getName());
		if (result.hasErrors()) {
			return index(model);
		}
		Comment comment = new Comment();
		BeanUtils.copyProperties(commentForm, comment);
//		comment.setContent(commentForm.getContent());
		commentRepository.insert(comment);
		if (!commentForm.IsSage()) {
			articleRepository.updateId(comment.getArticleId());
		}
		return "redirect:/";
	}

	/**
	 * 記事を削除.
	 * 
	 * @param articleId 記事のID hiddenで自動取得
	 * @return 記事一覧にリダイレクト
	 */
	@RequestMapping("deleteArticle")
	public String deleteArticle(String articleId) {
		articleRepository.deleteById(Integer.parseInt(articleId));
		return "redirect:/";
	}

	/**
	 * コメントを削除.
	 * 
	 * @param commentId コメントのID hiddenで自動取得
	 * @return 記事一覧にリダイレクト
	 */
	@RequestMapping("deleteComment")
	public String deleteComment(String commentId) {
		commentRepository.deleteById(Integer.parseInt(commentId));
		return "redirect:/";
	}

	/**
	 * いいねを付ける.
	 * 
	 * @param id いいねをつける記事ID
	 * @return 表示するいいねの数が入ったマップ
	 */
	@ResponseBody
	@RequestMapping(value = "addFav", method = RequestMethod.POST)
	synchronized public Map<String, Integer> addFav(String id) {
		Map<String, Integer> map = new HashMap<>();
		articleRepository.addFav(Integer.parseInt(id));
		map.put("fav", articleRepository.loadFav(Integer.parseInt(id)));
		return map;
	}
}