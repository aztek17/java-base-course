package homeworks.homework13.utils;

import homeworks.homework13.exception.WrongConditionException;

public class ValidateValue {
    public <T> T validate(T value, ByCondition condition, String textMessage) {
        if (!condition.isOk((Number) value)) {
            throw new WrongConditionException(textMessage);
        }
        return value;
    }
}
