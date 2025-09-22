import homeworks.homework14.App;
import homeworks.homework14.Person;
import homeworks.homework14.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class AppTests {

    @Test
    public void testInputBuyersPositive() {
        Person[] expectedPerson = new Person[]{
                new Person("Павел Андреевич", 10000),
                new Person("Анна Петровна", 2000)
        };
        Person[] actualPersonList = App.inputBuyers("Павел  Андреевич  =  10000;  Анна Петровна = 2000");
        Assertions.assertArrayEquals(expectedPerson, actualPersonList);
    }

    @Test
    public void testEmptyName() {
        Person[] actualPersonList = App.inputBuyers(" =  10000;  Анна Петровна = 2000");
        Assertions.assertNull(actualPersonList[0].getName());
    }

    @Test
    public void testNegativeMoneyValue() {
        Person[] actualPersonList = App.inputBuyers("Анна Петровна = -2000");
        Assertions.assertEquals(actualPersonList[0].getAmountMoney(), 0);
    }

    @Test
    public void testEmptyProductsName() {
        Assertions.assertThrows(RuntimeException.class, () -> App.inputProducts("= 40; Молоко = 60; Торт = 1000"));
    }

    @Test
    @Disabled
    public void testNegativeProductsPrice() {
        Assertions.assertThrows(RuntimeException.class, () -> App.inputProducts("Молоко = -60; Торт = 1000"));
    }

    @Test
    public void testInputProductsPositive() {
        Product[] expectedPerson = new Product[]{
                new Product("Хлеб", 40),
                new Product("Молоко", 60),
                new Product("Торт", 1000),
                new Product("Кофе растворимый", 879),
                new Product("Масло", 150),
        };
        Product[] actualPersonList = App.inputProducts("Хлеб = 40; Молоко = 60; Торт = 1000; Кофе растворимый = 879; Масло = 150;");
        Assertions.assertArrayEquals(expectedPerson, actualPersonList);
    }

}
