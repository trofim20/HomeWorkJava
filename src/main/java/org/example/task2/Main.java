package org.example.task2;

public class Main {
    public static void main(String[] args) {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        System.out.println("Добавление переменных");
        list.customAdd(0);
        list.customAdd(1);
        list.customAdd(2);
        list.customAdd(3);
        list.customAdd(4);
        System.out.println(list);
        System.out.println("Размер "+list.customSize());

        System.out.println("\nДобавление переменных в по индексу на индекс 4 добавляем 6");
        list.customAdd(4,6);
        System.out.println(list);
        System.out.println("Размер "+list.customSize());

        System.out.println("\nУдаление переменной по значению 3");
        list.customRemove(3);
        System.out.println(list);

        System.out.println("\nОбновление переменной по индексу 0 значение 10");
        list.customSet(0,10);
        System.out.println(list);
        System.out.println("Получаем по индексу 0 значение" +list.customGet(0));

        System.out.println("\nДобавление переменных для расширения массива");
        list.customAdd(0);
        list.customAdd(1);
        System.out.println(list);
        System.out.println("Размер "+list.customSize());

        System.out.println("\nОчищение коллекции");
        list.customClear();
        System.out.println(list);
        System.out.println("Размер "+list.customSize());
    }
}