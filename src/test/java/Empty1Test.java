import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Empty1Test {

  @BeforeMethod
  public void setUp(){
    System.out.println("Before Method from Empty1Test is working at the moment");
  }

  @Test
  public void emptyTest(){
    assert 1==1;
    System.out.println("Test Method from Empty1Test is working at the moment");
  }

  @AfterMethod
  public void tearDown(){
    System.out.println("After Method from Empty1Test is working at the moment");
  }
}