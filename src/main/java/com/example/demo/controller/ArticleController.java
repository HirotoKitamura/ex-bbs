package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Article;
import com.example.demo.domain.ArticleAndComment;
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

	/**
	 * 記事一覧を表示.
	 * 
	 * @param model リクエストスコープ
	 * @return 記事一覧ページ
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<ArticleAndComment> artComList = articleRepository.findAll();
		// 記事とコメントをJOINで全取得 記事IDの降順、次いでコメントIDの降順なのでコメントは記事ごとにまとまっている
		if (artComList.size() == 0) {
			model.addAttribute("noArticle", "記事がありません　投稿してね");
		}
		List<Article> articleList = new ArrayList<Article>();
		for (int i = 0; i < artComList.size(); i++) {
			ArticleAndComment artCom = artComList.get(i);
			if (i == 0 || artCom.getArticleId() != artComList.get(i - 1).getArticleId()) {
				// 1つ前に読んだコメントと記事IDが違ったら新たな記事クラスを作成
				articleList.add(new Article(artCom.getArticleId(), artCom.getArticleContributorName(),
						artCom.getArticleContent(), new ArrayList<Comment>()));
			}
			if (artCom.getCommentContent() != null) {// コメントがない記事はコメントリストに何も入れない 入れるとエラーを吐く
				articleList.get(articleList.size() - 1).getCommentList().add(new Comment(artCom.getCommentId(),
						artCom.getCommentContributorName(), artCom.getCommentContent(), artCom.getArticleId()));
			}
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
	public String insertArticle(ArticleForm articleForm) {
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
	public String insertComment(CommentForm commentForm) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(commentForm, comment);
//		comment.setContent(commentForm.getContent());
		commentRepository.insert(comment);
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
}
