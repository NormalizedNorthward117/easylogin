package com.example.easylogin_PGC.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.easylogin_PGC.model.dao.UserRepository;
import com.example.easylogin_PGC.model.entity.User;

@Controller
public class LoginController {
	@Autowired
	UserRepository userRepos;
	
	//　トップページへの遷移を担うindexメソッド
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	// ログイン後のページ遷移を担うloginメソッド
	//String型の戻り値を返す　引数は3種
	@RequestMapping("/login")
	public String login(
	/*　　RequestParamアノテーション
	 *付与された引数＝クライアントからのリクエストを設定
	 *RequestParam("HTML定義のname属性名")と指定する
	 */
			@RequestParam("username") String userName,
			@RequestParam("password") String password,
	//Model＝レスポンスとしてクライアントに返すためのオブジェクト定義
			Model m) {
		
		String message = "Welcome!　";
		
		List<User> users = userRepos.findByUserNameAndPassword(userName, password);
		//↑UserRepositoryリポジトリのfindByUserNameAndPasswordメソッドを呼び出し、
		//Userテーブルの一覧を取得
		
		if(users.size() > 0) {
			User user = users.get(0);
			message += user.getFullName();
	//Listで取得したUserテーブルに一致する名前があるなら
	//messageとしてそれを表示
			
		} else {
			message += "guest";
		}//userNameが無かった時にguest扱いする
		
		m.addAttribute("message", message);
	/*　addAttributeメソッドで、HTMLにおいて
	 * "message"というキー文字列の入力＝変数messageの値の出力
	 * となる
	 */

		return "login";
	}

}
