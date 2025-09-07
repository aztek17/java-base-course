package homeworks.homework13.utils;

import homeworks.homework13.exception.WrongConditionException;

public class CheckCondition<T> {
    public T validate(T value, ByCondition condition) {
        if (!condition.isOk((Number) value)) {
            throw new WrongConditionException("Ошибка проверки условия: ");
        }
        return value;
    }
}
