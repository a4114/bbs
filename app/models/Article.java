package models;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import play.data.validation.Constraints.*;


public class Article {
  @Required(message="名前欄は必須です")
  public String name;
  
  @Required(message="メール欄は必須です")
  @Email(message="メールの形式で入力してください")
  public String mail;
  public Boolean isMailPrivate;
  public Calendar postTime;
  
  @Required(message="内容は必須です")
  public String  content;
  static DateFormat format = SimpleDateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM);
 
  static List<Article> articles = new LinkedList<>();
  public static void add(Article article) {
	  if (article.isMailPrivate == null) {
         article.isMailPrivate = false;
     }
     article.postTime = Calendar.getInstance();
     articles.add(0, article);
  }
  
  
  public String showMail() {
	    return this.isMailPrivate ? "秘密" : this.mail;
	}
  
  public String getTimeString() {
	   Date date = new Date(this.postTime.getTimeInMillis());
	   return format.format(date);
	}
  
  public static List<Article> all() {
      return articles;
  }
  // public String validate() {
	//    if(content == null) {
	  //      return "内容は必須です";
	   // }
	    //return null;
	//} 


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public String getMail() {
	return mail;
}


public void setMail(String mail) {
	this.mail = mail;
}


public Boolean getIsMailPrivate() {
	return isMailPrivate;
}


public void setIsMailPrivate(Boolean isMailPrivate) {
	this.isMailPrivate = isMailPrivate;
}


public String getContent() {
	return content;
}


public void setContent(String content) {
	this.content = content;
}
     
}