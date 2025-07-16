package homeworks.homework06;

import java.util.Scanner;

public class App {
    // test data: Павел  Андреевич  =  10000;  Анна Петровна = 2000; Борис = 10
    // test data: Хлеб = 40; Молоко = 60; Торт = 1000; Кофе растворимый = 879; Масло = 150;
    /*
    test data:
    Павел Андреевич Хлеб
    Павел Андреевич Масло
    Анна Петровна Кофе растворимый
    Анна Петровна Молоко
    Анна Петровна Молоко
    Анна Петровна Молоко
    Анна Петровна Торт
    Борис Торт
    Павел Андреевич  Торт
    END
     */

    public static void main(String[] args) {
        Person[] persons = inputBuyers();
        Product[] products = inputProducts();
        inputBuy(persons, products);

    }

    private static Person[] inputBuyers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имена покупателей и их бюджет: ");
        String[] buyers = scanner.nextLine().split(";");

        Person[] persons = new Person[buyers.length];
        for (int i = 0; i < buyers.length; i++) {
            persons[i] = new Person(
                    buyers[i].split("=")[0].trim().replaceAll("\\s+", " "),
                    Integer.parseInt(buyers[i].split("=")[1].replaceAll("[^0-9]", ""))
            );
        }
        return persons;
    }

    private static Product[] inputProducts() {
        System.out.println("Введите ассортимент магазина и цены: ");
        Scanner scannerProducts = new Scanner(System.in);
        String[] inputListProducts = scannerProducts.nextLine().split(";");

        Product[] products = new Product[inputListProducts.length];
        for (int i = 0; i < inputListProducts.length; i++) {
            products[i] = new Product(
                    inputListProducts[i].split("=")[0].trim().replaceAll("\\s+", " "),
                    Integer.parseInt(inputListProducts[i].split("=")[1].replaceAll("[^0-9]", ""))
            );
        }
        return products;
    }

    private static void inputBuy(Person[] persons, Product[] products) {
        Scanner buyScanner = new Scanner(System.in);
        while (buyScanner.hasNextLine()) {
            String buy = buyScanner.nextLine();
            if (buy.equals("END")) {
                break;
            }
            Product selectedProduct = null;
            for (Product product : products) {
                if (buy.contains(product.getProductName())) {
                    selectedProduct = product;
                }
            }

            for (Person person : persons) {
                if (selectedProduct != null & buy.contains(person.getName())) {
                    person.addProduct(selectedProduct);
                } else if (selectedProduct == null & buy.contains(person.getName())) {
                    System.out.println("Выбранный покупателем товар не был найден в магазине");
                }
            }
        }
    }
}
