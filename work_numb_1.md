1.
```
public class Triangle 
{ 
   public static void main(String[] args) 
   { 
      for (int i = 1; i <= 4; i++) { 
         for (int j = 4; j > i; j--) { 
            System.out.print(" "); 
         } 
         for (int k = 1; k <= (2 * i - 1); k++) { 
            System.out.print("x"); 
         } 
         System.out.println(); 
      } 
   } 
}
```
2.
```
public class Staircase 
{ 
   public static void main(String[] args) 
   { 
      // Print the first two lines 
      System.out.println("  ____"); 
      System.out.println("  I__I___"); 
 
      // Loop to print the remaining steps 
      for (int i = 1; i <= 4; i++) { 
         System.out.print("  I"); 
         for (int j = 0; j < i * 2; j++) { 
            System.out.print("_"); 
         } 
         System.out.println("I___"); 
      } 
   } 
}
```
3.
```
public class Parallelogram 
{ 
   public static void main(String[] args) 
   { 
      System.out.println("      ______________________"); 
      System.out.println("     /                     /"); 
      System.out.println("    /    Parallelogram    /"); 
      System.out.println("   /                     /"); 
      System.out.println("  /_____________________/"); 
   } 
}
```
4.
```
public class Frost 
{ 
   public static void main(String[] args) 
   { 
      System.out.println("Two roads diverged in a wood, and I --"); 
      System.out.println("I took the one less traveled by,"); 
      System.out.println("And that has made all the difference."); 
      System.out.println("    by Robert Frost"); 
   } 
}
```
5.
```
public class PeterPiper 
{ 
   public static void main(String[] args) 
   { 
      System.out.println("Peter Piper picked a peck of pickled peppers."); 
      System.out.println("A peck of pickled peppers, Peter Piper picked."); 
      System.out.println("If Peter picked a peck of pickled peppers,"); 
      System.out.println("Where's the peck of pickled peppers Peter Piper picked?"); 
   } 
}
```
6.
var_1
```
System.out.println("    y | 1y | 2y | 3y | 4y | 5y ");        System.out.println("  ----|----|----|----|----|----");
        System.out.println("    1 |  1 |  2 |  3 |  4 |  5");        System.out.println("    2 |  2 |  4 |  6 |  8 | 10");
        System.out.println("    3 |  3 |  6 |  9 | 12 | 15");        System.out.println("    4 |  4 |  8 | 12 | 16 | 20");
        System.out.println("    5 |  5 | 10 | 15 | 20 | 25");        System.out.println("    6 |  6 | 12 | 18 | 24 | 30");
        System.out.println("    7 |  7 | 14 | 21 | 28 | 35");        System.out.println("    8 |  8 | 16 | 24 | 32 | 40");
        System.out.println("    9 |  9 | 18 | 27 | 36 | 45");        System.out.println("   10 | 10 | 20 | 30 | 40 | 50");
    }}
```
var_2
```
public class MultTable 
{ 
   public static void main(String[] args) 
   { 
      System.out.println("    y | 1y | 2y | 3y | 4y | 5y "); 
      System.out.println("  ----|----|----|----|----|----"); 
 
      for (int i = 1; i <= 10; i++) { 
         System.out.printf("%4d |", i); 
         for (int j = 1; j <= 5; j++) { 
            System.out.printf("%4d |", i * j); 
         } 
         System.out.println(); 
      } 
   } 
}
```
