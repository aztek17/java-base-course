package homeworks.homework07;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    // test data: Павел  Андреевич  =  3000, 45; Дмитрий Сергеевич = 500, 50; Анна Петровна = 2000, 70; Дед Евгений = 650, 88; Никита = 10, 5; Борис = 10000, 8
    // test data: Хлеб = 40; Молоко = 60; Масло = 100; Торт = 800, 15%; Кофе растворимый = 432, 50%; Кукуруза = 340, 20%
    /*
    test data:
    Павел Андреевич Хлеб
    Павел Андреевич Торт
    Дмитрий Сергеевич Торт
    Анна Петровна Кофе растворимый
    Анна Петровна Молоко
    Анна Петровна Торт
    Борис Торт
    Павел Андреевич  Торт
    Борис Кофе растворимый
    Борис Молоко
    Борис Масло
    Борис Хлеб
    Дед Евгений Молоко
    Дед Евгений Кофе растворимый
    Никита Хлеб
    END
     */

    public static void main(String[] args) {
        Person[] persons = inputBuyers();
        Product[] products = inputProducts();
        printDiscountSign(products);
        inputBuy(persons, products);
        for (Person person : persons) {
            System.out.println(person);
        }
    }

    private static Person[] inputBuyers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имена покупателей и их бюджет: ");
        String[] buyers = scanner.nextLine().split(";");

        Person[] persons = new Person[buyers.length];
        for (int i = 0; i < buyers.length; i++) {
            persons[i] = createPerson(buyers[i]);
        }
        return persons;
    }

    private static Product[] inputProducts() {
        System.out.println("Введите ассортимент магазина и цены: ");
        Scanner scannerProducts = new Scanner(System.in);
        String[] inputListProducts = scannerProducts.nextLine().split(";");

        Product[] products = new Product[inputListProducts.length];
        for (int i = 0; i < inputListProducts.length; i++) {
            if (!inputListProducts[i].contains("%")) {
                products[i] = new Product(
                        inputListProducts[i].split("=")[0].trim().replaceAll("\\s+", " "),
                        Integer.parseInt(inputListProducts[i]
                                .split("=")[1]
                                .split(",")[0]
                                .replaceAll("[^0-9]", ""))
                );
            } else {
                products[i] = new DiscountProduct(
                        inputListProducts[i].split("=")[0].trim().replaceAll("\\s+", " "),
                        Integer.parseInt(inputListProducts[i].split("=")[1].split(",")[0].replaceAll("[^0-9]", "")),
                        Integer.parseInt(inputListProducts[i].split("=")[1].split(",")[1].replaceAll("[^0-9]", "")),
                        true
                );
            }
        }
        return products;
    }

    private static void inputBuy(Person[] persons, Product[] products) {
        System.out.println("Покупатели выбирают товары, начните вводить список покупателей и выбранные товары построчно");
        Scanner buyScanner = new Scanner(System.in);
        while (buyScanner.hasNextLine()) {
            String buy = buyScanner.nextLine();
            if (buy.equalsIgnoreCase("END")) {
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

    private static void printDiscountSign(Product[] products) {
        List<DiscountProduct> discountProducts = new ArrayList<>();
        List<Product> baseProducts = new ArrayList<>();
        for (Product product : products) {
            if (product instanceof DiscountProduct) {
                discountProducts.add((DiscountProduct) product);
            } else {
                baseProducts.add(product);
            }
        }
        System.out.println("Обычные продукты: " + baseProducts);
        System.out.println("Акционные продукты: " + discountProducts);
    }

    private static Person createPerson(String buyer) {
        String personName = buyer
                .split("=")[0]
                .trim()
                .replaceAll("\\s+", " ");
        int personCash = Integer.parseInt(buyer
                .split("=")[1]
                .trim()
                .split(",")[0]
                .trim()
                .replaceAll("^-?\\\\d+", ""));
        int personAge = Integer.parseInt(buyer
                .split("=")[1]
                .trim()
                .split(",")[1]
                .trim());

        if (personAge <= 17) {
            return new Child(personName, personCash, personAge);
        } else if (personAge >= 65) {
            return new Pensioner(personName, personCash, personAge);
        } else {
            return new Adult(personName, personCash, personAge);
        }
    }
}
