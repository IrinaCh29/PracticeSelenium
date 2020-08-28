import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Empty2Test {
  @BeforeMethod
  public void setUp(){
    System.out.println("Before Method from Empty2Test is working at the moment");
  }

  @Test
  public void emptyTest(){
    assert 1==0;
    System.out.println("Test Method from Empty2Test is working at the moment");
  }

  @AfterMethod
  public void tearDown(){
    System.out.println("After Method from Empty2Test is working at the moment");
  }
}