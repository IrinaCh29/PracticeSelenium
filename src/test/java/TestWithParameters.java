import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestWithParameters {

  @Parameters({"waitTimeOutInSeconds"})
  @Test
  public static void testWithParametersAnnotation(String waitTimeOutInSeconds){
    System.out.println("TimeOut: " + waitTimeOutInSeconds);
  }

  @Test
  public static void testWithParametersInListener(){
  }
}