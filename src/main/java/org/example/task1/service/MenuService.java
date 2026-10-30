package org.example.task1.service;

import org.example.task1.entity.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MenuService {
    private final List<Person> persons = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private static final Pattern EMAIL = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    public void start(){
        while (true){
            System.out.println("""
                ===== МЕНЮ =====
                1. Добавить данные вручную
                2. Загрузить данные из файла
                3. Обработать данные
                0. Выход
                """);
            System.out.print("Выберите пункт меню: ");
            int command;
            try{
                command = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Введите номер пункта меню");
                continue;
            }

            switch (command) {
                case 1 -> addPerson();
                case 2 -> loadDataFromFile();
                case 3 -> processData();
                case 0 -> {
                    System.out.println("Выход из меню");
                    return;
                }
                default -> System.out.println("Неизвестная команда");

            }
        }
    }

    private void addPerson(){
        String name;
        while (true) {
            System.out.print("Введите имя пользователя: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Имя не может быть пустым");
                continue;
            }
            break;
        }
        String email;
        while (true) {
            System.out.print("Введите почту пользователя: ");
            email = scanner.nextLine().trim();
            if (!EMAIL.matcher(email).matches()) {
                System.out.println("Неверный формат почты, пример - test@mail.ru");
                continue;
            }
            break;
        }
        int id;
        while (true) {
            System.out.print("Введите идентификатор пользователя: ");
            String idInput = scanner.nextLine().trim();
            try {
                id = Integer.parseInt(idInput);
                if (id <= 0) {
                    System.out.println("Идентификатор должен быть больше 0");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введите целое число");
            }
        }
        persons.add(new Person(name, email, id));
        System.out.println("Пользователь добавлен");
    }

    private void processData() {
        if (persons.isEmpty()) {
            System.out.println("Список пользоветелей пуст");
            return;
        }

        System.out.println("Обработанный список пользоветелей где email содержит mail и данные в верхнем регистре");

        persons.stream()
                .filter(p -> p.getEmail().contains("mail"))
                .map(p -> new Person(
                        p.getName().toUpperCase(),
                        p.getEmail().toUpperCase(),
                        p.getId()
                ))
                .sorted(Comparator.comparing(Person::getName))
                .forEach(System.out::println);
    }

    public void loadDataFromFile() {
        System.out.print("Введите путь к файлу: ");
        String filePath = scanner.nextLine();

        if (filePath.isEmpty()) {
            System.out.println("Путь не должен быть пустым");
            return;
        }

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null){
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s");
                if (parts.length != 3) {
                    System.out.println("Неверный формат данных - необходимо name, mail, id");
                    continue;
                }
                String name = parts[0];
                String email = parts[1];
                String idStr = parts[2];

                if (name.isEmpty()) {
                    System.out.println("Имя не может быть пустым");
                    continue;
                }
                if (!EMAIL.matcher(email).matches()) {
                    System.out.println("Неверный формат почты, пример - test@mail.ru");
                    continue;
                }
                int id;
                try {
                    id = Integer.parseInt(idStr);
                    if (id <= 0) {
                        System.out.println("Идентификатор должен быть больше 0");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Введите целое число");
                    continue;
                }
                persons.add(new Person(name, email, id));
            }
            System.out.println("Пользователи успешно загружены");
        }catch (FileNotFoundException e){
            System.out.println("Файл не найден");
        }catch (IOException e){
            System.out.println("Ошибка при чтении файла" + e.getMessage());
        }
    }
}
