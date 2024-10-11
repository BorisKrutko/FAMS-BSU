
import java.util.Arrays;
import java.util.function.Predicate;

class TaskGenericMetods {
    public static void main(String[] args) {
        // Тест для findInArray
        Integer[] intArray = {1, 2, 3, 3, 4, 5};
        System.out.println("Test findInArray:");
        System.out.println(findInArray(intArray, 3) == true ? "Passed" : "Failed");
        System.out.println(findInArray(intArray, 6) == false ? "Passed" : "Failed");

        // Тест для getQuantityOfElInArray
        System.out.println("\nTest getQuantityOfElInArray:");
        System.out.println(getQuantityOfElInArray(intArray, 3) == 2 ? "Passed" : "Failed");
        System.out.println(getQuantityOfElInArray(intArray, 6) == 0 ? "Passed" : "Failed");

        // Тест для getQuantityOfLargeElInArray
        System.out.println("\nTest getQuantityOfLargeElInArray:");
        System.out.println(getQuantityOfLargeElInArray(intArray, 3) == 2 ? "Passed" : "Failed");
        System.out.println(getQuantityOfLargeElInArray(intArray, 5) == 0 ? "Passed" : "Failed");

        // Тест для getSumElInArray
        System.out.println("\nTest getSumElInArray:");
        System.out.println(getSumElInArray(intArray) == 18.0 ? "Passed" : "Failed");
        Double[] doubleArray = {1.1, 2.2, 3.3};
        System.out.println(Math.abs(getSumElInArray(doubleArray) - 6.6) < 0.0001 ? "Passed" : "Failed");

        // Тест для getArithmeticMeanElInArray
        System.out.println("\nTest getArithmeticMeanElInArray:");
        System.out.println(getArithmeticMeanElInArray(intArray) == 3.0 ? "Passed" : "Failed");
        System.out.println(Math.abs(getArithmeticMeanElInArray(doubleArray) - 2.2) < 0.0001 ? "Passed" : "Failed");
        // Тест для проверки исключений 
        Integer[] arr = {};
        getArithmeticMeanElInArray(arr);

        // Тест для getQuantityOfElSuitableForCondition
        System.out.println("\nTest getQuantityOfElSuitableForCondition:");
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println(getQuantityOfElSuitableForCondition(intArray, isEven) == 2 ? "Passed" : "Failed");
        Predicate<Integer> isGreaterThanThree = n -> n > 3;
        System.out.println(getQuantityOfElSuitableForCondition(intArray, isGreaterThanThree) == 2 ? "Passed" : "Failed");
    }

    public static <T extends Comparable<T>> boolean findInArray(T[] arr, T value) {
        return Arrays.asList(arr).contains(value);
    }

    public static <T extends Comparable<T>> int getQuantityOfElInArray(T[] arr, T value) {
        return (int)Arrays.stream(arr).filter(element -> element.equals(value)).count();
    }

    public static <T extends Comparable<T>> int getQuantityOfLargeElInArray(T[] arr, T value) {
        return (int)Arrays.stream(arr).filter(element -> element.compareTo(value) > 0).count();
    }

    public static <T extends Number> double getSumElInArray(T[] arr) {
        return Arrays.stream(arr).mapToDouble(Number::doubleValue).sum();
    }
        
    public static <T extends Number> double getArithmeticMeanElInArray(T[] arr) {
        double unser = -1; 
        try {
            if (arr.length == 0) throw new IllegalArgumentException("Array is null");
            unser = Arrays.stream(arr).mapToDouble(Number::doubleValue).sum() / arr.length;
        } catch (IllegalArgumentException k) {System.out.println(k.getMessage());
        } catch (ArithmeticException a) {System.out.println("ArithmeticException");
        } catch (ClassCastException c) {System.out.println("ClassCastException");}
        return unser;
    }    

    public static <T extends Number> int getQuantityOfElSuitableForCondition(T[] arr, Predicate<T> condition){
        return (int)Arrays.stream(arr).filter(condition).count();
    }
}
