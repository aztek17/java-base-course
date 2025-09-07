package homeworks.homework13.utils;

@FunctionalInterface
public interface ByCondition <T extends Number> {
    boolean isOk(T value);
}
