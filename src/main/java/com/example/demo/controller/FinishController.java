package com.example.demo.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.UserTable;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class FinishController {
	
	private final UserService userService;
	
	/** コンストラクタインジェクション コンストラクタが1つの場合はアノテーションの省略が可能 */
	@Autowired
	public FinishController(UserService userService) {
		this.userService = userService;
	}
	
	// submitのname属性により使用するコントローラーを割り当てる
	@PostMapping(value = "/commit", params = "commit") //  name属性 = params 
	private String commit(@ModelAttribute User user, Model model) throws ParseException {
		
		// SQLインサート実行
		userService.insert(user);
		
		// SQLセレクト実行
		UserTable record = userService.findByNo(user.getNo());
		
		model.addAttribute("userTable", record);
		
		return "user/register/finish";
	}
	
	@PostMapping(value = "/commit", params = "back")
	private String back(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addFlashAttribute("user", user);
		
		return "redirect:/form";
	}
}
