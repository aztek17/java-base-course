package homeworks.homework10.task2;

public class App {
    public static void main(String[] args) {
        Pair<Integer, String> pair = Pair.of(1, "hello");
        Integer i = pair.getFirst();
        String s = pair.getSecond();
        System.out.println(i + "\n" + s);

        Pair<Integer, String> pair2 = Pair.of(1, "hello");
        boolean mustBeTrue = pair.equals(pair2);

        boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode();
        System.out.println(mustBeTrue + "\n" + mustAlsoBeTrue);

    }

}
