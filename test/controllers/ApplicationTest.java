package controllers;
import java.util.ArrayList;

import java.util.HashMap;
//import java.util.List;
import java.util.Map;

import models.Article;

import org.codehaus.jackson.JsonNode;
import org.junit.*;

import controllers.routes.ref;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import play.data.Form;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

    @Test 
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }
    
    @Test
    public void renderTemplate() {
    	Form<Article> testForm = Form.form(Article.class);
        Content html = views.html.index.render("Your new application is ready.", testForm, Article.all());
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Your new application is ready.");
    }
    
    @Test
    public void 名前欄が空のときバリデーションエラーのメッセージがでる() {
        Map<String, String> data = new HashMap<>();
        data.put("name", null);
        data.put("mail", "hoge@dwango.co.jp");
        data.put("isMailPrivate", "true");
        data.put("content", "ほげー");
      
        start(fakeApplication());
        Application.articleForm.bind(data);
        Result result = callAction(ref.Application.newArticle());
        assertThat(status(result)).isEqualTo(400);
        assertThat(contentAsString(result)).contains("名前欄は必須です");
    }
  
   
}
