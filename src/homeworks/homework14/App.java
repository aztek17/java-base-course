package homeworks.homework14;

import java.util.Scanner;

public class App {
    // test data: Павел  Андреевич  =  10000;  Анна Петровна = 2000; Борис = 10
    // test data: Хлеб = 40; Молоко = 60; Торт = 1000; Кофе растворимый = 879; Масло = 150;
    /*
    test data:
    Павел Андреевич Хлеб
    Павел Андреевич Масло
    Павел Андреевич Масло
    Анна Петровна Молоко
    Анна Петровна Молоко
    Анна Петровна Молоко
    Анна Петровна Молоко
    Борис Торт
    Павел Андреевич  Торт
    END
     */

    public static void main(String[] args) {
    }

    public static Person[] inputBuyers(String rawBuyers) {
        String[] buyers = rawBuyers.split(";");

        Person[] persons = new Person[buyers.length];
        for (int i = 0; i < buyers.length; i++) {
            persons[i] = new Person(
                    buyers[i].split("=")[0].trim().replaceAll("\\s+", " "),
                    Integer.parseInt(buyers[i].split("=")[1].trim().replaceAll("^-?\\\\d+", ""))
            );
        }
        return persons;
    }

    public static Product[] inputProducts(String rawListProducts) {
        String[] inputListProducts = rawListProducts.split(";");

        Product[] products = new Product[inputListProducts.length];
        for (int i = 0; i < inputListProducts.length; i++) {
            products[i] = new Product(
                    inputListProducts[i].split("=")[0].trim().replaceAll("\\s+", " "),
                    Integer.parseInt(inputListProducts[i].split("=")[1].replaceAll("[^0-9-]", ""))
            );
        }
        return products;
    }

    public static void inputBuy(Person[] persons, Product[] products) {
        System.out.println("Покупатели выбирают товары, начните вводить список покупателей и выбранные товары построчно");
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
