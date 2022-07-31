package by.epam.webtask.validator;

import by.epam.webtask.model.entity.Item;

import java.math.BigDecimal;

public final class ItemValidator {
    private ItemValidator() {
    }

    public static boolean isValidItem(Item item) {
        BigDecimal price = item.getPrice();
        String name = item.getName();
        return price != null && name != null && name.length() > 0 && price.compareTo(BigDecimal.ZERO) > 0;
    }
}