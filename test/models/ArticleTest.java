package models;
import java.util.*;

import org.junit.After;
import org.junit.Test;
import static org.fest.assertions.Assertions.*;
public class ArticleTest {
    @Test
    public void 初期値が空リストであること() {
        assertThat(Article.all()).isEmpty();
    }
    @Test
    public void addしたものがallで新しい順に取得できること() {
        {
            Article article = new Article();
            article.name = "1番目";
            Article.add(article);
        }
        {
            Article article = new Article();
            article.name = "2番目";
            Article.add(article);
        }
        {
            Article article = new Article();
            article.name = "3番目";
            Article.add(article);
        }
        assertThat(Article.all()).hasSize(3);
        assertThat(Article.all().get(0).name).isEqualTo("3番目");
        assertThat(Article.all().get(1).name).isEqualTo("2番目");
        assertThat(Article.all().get(2).name).isEqualTo("1番目");
    }
    
    @After
    public void teadDown() {
        Article.articles = new LinkedList<>();
    }
}