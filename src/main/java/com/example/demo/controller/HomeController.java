package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class HomeController {
	
	private final UserService userService;
	
	@Autowired
	public HomeController(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * <h2>[Get処理]</h2><br>
	 * <br>
	 * 入力画面を返す。
	 * @param user ユーザー情報
	 * @return 遷移先
	 */
	@GetMapping("/form")
	private String readForm(@ModelAttribute User user, Model model) {	// htmlで指定した th:object を受け取る
		int newUserNo = userService.getNewNo();
		user.setNo(newUserNo);
		return "user/register/form";	// templateフォルダを起点に遷移先のファイルを指定(.html省略可)
	}
	
	/**
	 * <h2>[Post処理]</h2><br>
	 * <br>
	 * 入力内容確認画面を返す。年齢項目に入力された値が0未満の場合、エラー画面を返す。
	 * @param user
	 * @param model
	 * @return
	 */
	@PostMapping("/form")
	private String confirm(@ModelAttribute User user, Model model) {
		if (user.getAge() < 0) {
			user.setErrMsg("年齢の値が不正です。");
			
			model.addAttribute("message", "エラーです。");
			
			return "user/register/irregular";
		}
		
		return "user/register/confirm";
	}
	
}
