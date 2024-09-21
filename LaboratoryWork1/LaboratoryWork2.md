```
import java.io.IOException;


public class LaboratoryWork2 {
  public static void main(String[] args) throws IOException {
      if (args.length != 2 || !(args[0] instanceof String)) {
        throw new IOException("Exception: invalid arguments");
      }

      // default meanings
      int x = 1, i;
      double res;
      
      // convert to number whithout erorr
      try {
        x = Integer.parseInt(args[1]);
      } catch (Exception e) {}
      
      // count
      String[] tokens = args[0].split(" ");
      res = ChangeX(tokens[0], x);
      for (i = 1; i + 1 < tokens.length; i += 2) {
        switch (tokens[i]) {
            case "/":
                res /= ChangeX(tokens[i + 1], x);
                break;
            case "*":
                res *= ChangeX(tokens[i + 1], x);
                break;
            default:
                throw new IOException("Exception: invalid arguments");
        }
      }
      if (i != tokens.length) throw new IOException("Exception: invalid arguments");
      System.out.println("unser: " + res);
  }  
  
  public static int ChangeX(String arg, int x) {
    if (arg.equals("x")) {
      return x;
    } else {
      return Integer.parseInt(arg);
    }
  }
}
```