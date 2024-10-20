package main;

import model.FamilyTree;
import model.Person;
import service.FileOperations;
import service.FileOperationsImpl;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        FamilyTree familyTree = new FamilyTree();

        // Создаем людей
        Person алексей = new Person("Алексей", 1970);
        Person екатерина = new Person("Екатерина", 1975);
        Person анна = new Person("Анна", 2000);

        // Устанавливаем родительские связи
        анна.setMother(екатерина);
        анна.setFather(алексей);
        алексей.addChild(анна);
        екатерина.addChild(анна);

        // Добавляем людей в древо
        familyTree.addPerson(алексей);
        familyTree.addPerson(екатерина);
        familyTree.addPerson(анна);

        // Сортировка по имени
        System.out.println("Сортировка по имени:");
        familyTree.sortByName();
        for (Person person : familyTree) {
            System.out.println(person.getName() + " - " + person.getBirthYear());
        }

        // Сортировка по дате рождения
        System.out.println("\nСортировка по дате рождения:");
        familyTree.sortByBirthYear();
        for (Person person : familyTree) {
            System.out.println(person.getName() + " - " + person.getBirthYear());
        }

        // Сохранение семейного древа в файл
        FileOperations fileOps = new FileOperationsImpl();
        try {
            fileOps.saveToFile(familyTree, "familyTree.dat");
            System.out.println("\nСемейное древо сохранено в файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Загрузка семейного древа из файла
        FamilyTree loadedFamilyTree = null;
        try {
            loadedFamilyTree = fileOps.loadFromFile("familyTree.dat");
            System.out.println("Семейное древо загружено из файла.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Проверка загруженного древа
        if (loadedFamilyTree != null) {
            System.out.println("\nЗагруженные люди:");
            for (Person person : loadedFamilyTree) {
                System.out.println(person.getName() + ", родился в " + person.getBirthYear());
            }
        }
    }
}
