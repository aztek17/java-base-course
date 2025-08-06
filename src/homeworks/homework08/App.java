package homeworks.homework08;

import java.io.*;

public class App {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/input.txt"))) {
            Person[] persons = inputBuyers(reader);
            Product[] products = inputProducts(reader);
            inputBuy(persons, products, reader);
            for (Person person : persons) {
                writeToFile(person.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Person[] inputBuyers(BufferedReader reader) throws IOException {
        String[] buyers = reader.readLine().split(";");
        Person[] persons = new Person[buyers.length];
        for (int i = 0; i < buyers.length; i++) {
            persons[i] = createPerson(buyers[i]);
        }
        return persons;
    }


    private static Product[] inputProducts(BufferedReader reader) throws IOException {
        String[] inputListProducts = reader.readLine().split(";");
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

    private static void inputBuy(Person[] persons, Product[] products, BufferedReader reader) throws IOException {
        while (true) {
            String buy = reader.readLine();
            if (buy == null || buy.equalsIgnoreCase("END")) {
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
                    writeToFile("Выбранный покупателем товар не был найден в магазине");
                }
            }
        }
    }

    private static Person createPerson(String buyer) throws IOException {
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

    public static void writeToFile(String text) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("src/output.txt", true))) {
            writer.write(String.valueOf(text));
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
