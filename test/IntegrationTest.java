import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import static org.fluentlenium.core.filter.FilterConstructor.*;

public class IntegrationTest {

    /**
     * add your integration test here
     * in this example we just check if the welcome page is being shown
     */   
    @Test
    public void test() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333");
                assertThat(browser.pageSource()).contains("MyBBS");
            }
        });
    }
    
    @Test
    public void メアド非表示になるか() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333");
                browser.fill("#name").with("hoge");
                browser.fill("#mail").with("hoge@dwango");
                browser.click("#isMailPrivate");
                browser.fill("#content").with("ほげー");
                browser.submit("form");
                browser.await();
                assertThat(browser.url()).isEqualTo("http://localhost:3333/");
                assertThat(browser.pageSource()).contains("秘密");
            }
        });
    }
  
}
