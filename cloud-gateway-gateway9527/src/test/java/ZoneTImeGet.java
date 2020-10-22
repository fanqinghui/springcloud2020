import java.time.ZonedDateTime;

/**
 * @author fqh
 * @Description:
 * @date 2020/10/21下午11:19
 */
public class ZoneTImeGet {
  //2020-10-21T23:19:43.700+08:00[Asia/Shanghai]
  public static void main(String[] args) {
    ZonedDateTime now = ZonedDateTime.now();
    System.out.println(now);
  }
}
