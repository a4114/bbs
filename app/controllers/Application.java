package controllers;

import models.Article;

import play.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;


public class Application extends Controller {
	
	static Form<Article> articleForm = Form.form(Article.class);

    public static Result index() {
        return ok(index.render("MyBBS", articleForm, Article.all()));
    }

    //public static Result newArticle() {
        //return TODO;
   // }
    
    public static Result newArticle() {
        Form<Article> filledForm = articleForm.bindFromRequest(); // リクエストから入力された値をバインドしたformオブジェクトを取得
        if(filledForm.hasErrors()) { // エラーが有った場合
            return badRequest(
                  index.render("MyBBS", filledForm, Article.all()) // 400 Bad Requestを返しつつ、入力された値を元に掲示板ページを表示
            );
        } else {
            Article.add(filledForm.get()); // エラーがなければ、新たな記事を追加して(Article#add はまだ未実装)
            return redirect(routes.Application.index());  // indexページへリダイレクト
        }
    }

}

