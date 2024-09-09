```
import java.io.IOException; 
 
 
public class LaboratoryWork1 { 
  public static void main(String[] args) throws IOException { 
      if (args.length != 2) { 
        throw new IOException("Exception: invalid arguments"); 
      } 
       
      // default meanings 
      double number = 1; 
      double precession = 1; 
       
      // convert to number whithout erorr 
      try { 
        number = Double.parseDouble(args[0]); 
      } catch (Exception e) {} 
      try { 
        number = Double.parseDouble(args[0]); 
      } catch (Exception e) {} 
       
      // count 
      int n = 1; 
      double resault = 0, t_colculation = AmountCalculation(number, n); 
      while (Math.abs(t_colculation) < precession) { 
        resault += t_colculation; 
        n++; 
        t_colculation = AmountCalculation(number, n); 
      } 
      System.out.println("sum " + resault); 
    }   
 
    public static double AmountCalculation(double x, int n) { 
      double res = Math.pow(x, 2 * n) / (Math.pow(2, n) * factorial(n));  
      return res; 
    } 
 
    public static int factorial(int n) { 
      int res = 1; 
      for(int i = 1; i <= n; i++) { 
        res *= i; 
      } 
      return res; 
    } 
}
```
