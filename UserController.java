package jp.co.internous.grapes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.grapes.model.domain.MstUser;
import jp.co.internous.grapes.model.form.UserForm;
import jp.co.internous.grapes.model.mapper.MstUserMapper;
import jp.co.internous.grapes.model.session.LoginSession;

@Controller
@RequestMapping("/grapes/user")//(/grapes/user/)でアクセスできるよう設定する
public class UserController {
	
	@Autowired
	private MstUserMapper mstUserMapper;
	
	@Autowired
	private LoginSession loginSession;
	
	//新規ユーザー登録画面で表示するものを取得
	@RequestMapping("/")
	public String index(Model m) {
		m.addAttribute("loginSession",loginSession);

		return "register_user";
	}
	
	// ユーザー名重複 
	@RequestMapping("/duplicatedUserName")
	@ResponseBody
	public boolean duplicatedUserName(@RequestParam String userName) {
		int count = mstUserMapper.findCountByUserName(userName);
		return count > 0;
	}
	
	//ユーザー登録
	@RequestMapping("/register")
	@ResponseBody
	public boolean register(@RequestBody UserForm f) {
		MstUser user = new MstUser(f);
		
		int count = mstUserMapper.insert(user);
		
		return count > 0;
	}
	
}
