package LW10e;

public class Main {
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        // Тестирование добавления элементов
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("5", 1);
        map.put("6", 2);
        map.put("7", 3);
        map.put("8", 4);
        map.put("9", 1);
        map.put("10", 2);
        map.put("11", 3);
        map.put("12", 4);        

        // Тестирование извлечения элементов
        System.out.println("Value for 'one': " + map.get("one")); //  1
        System.out.println("Value for 'two': " + map.get("two")); //  2
        System.out.println("Value for 'three': " + map.get("three")); //  3
        System.out.println("Value for 'four': " + map.get("four")); // 4
        System.out.println("Value for 'five': " + map.get("five")); // null

        // Проверка размера
        System.out.println("Size of map: " + map.size()); // 4

        // Тестирование метода toString
        System.out.println("Current map: " + map); 

        // Тестирование метода clear
        // map.clear();
        // System.out.println("Size after clear: " + map.size()); // 0
        // System.out.println("Is map empty? " + map.isEmpty()); // true

        // Добавление элементов после очистки
        map.put("five", 5);
        System.out.println("Value for 'five' after adding again: " + map.get("five")); // 5

        // check it
        MyIterator<String, Integer> it = new MyIterator<>(map);
        System.out.println(it.currentItem());
        it.next();
        System.out.println(it.currentItem());
        System.out.println(it.isDone());
        it.first();
        System.out.println(it.currentItem());
    }
}
