package homeworks.homework06;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        inputBuyers();
        inputProducts();
    }

    private static void inputBuyers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имена покупателей и их бюджет: ");
        String[] buyers = scanner.nextLine().split(";"); // test data: Павел  Андреевич  =  10000;  Анна Петровна = 2000; Борис = 10

        Person[] persons = new Person[10];
        for (int i = 0; i < buyers.length; i++) {
            persons[i] = new Person(
                    buyers[i].split("=")[0].trim().replaceAll("\\s+", " "),
                    Integer.parseInt(buyers[i].split("=")[1].replaceAll("[^0-9]", ""))
            );
            System.out.println("Распарсенный покупатель " + persons[i]);
        }
    }

    private static void inputProducts() {
        System.out.println("Введите ассортимент магазина и цены: ");
        Scanner scannerProducts = new Scanner(System.in);
        String[] inputProducts = scannerProducts.nextLine().split(";"); // test data: Хлеб = 40; Молоко = 60; Торт = 1000; Кофе растворимый = 879; Масло = 150;

        Product[] products = new Product[10];
        for (int i = 0; i < inputProducts.length; i++) {
            products[i] = new Product(
                    inputProducts[i].split("=")[0].trim().replaceAll("\\s+", " "),
                    Integer.parseInt(inputProducts[i].split("=")[1].replaceAll("[^0-9]", ""))
            );
            System.out.println("Распарсенный товар " + products[i]);
        }
    }
}
