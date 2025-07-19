package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz); // 자식 클래스까지 커버해줌
        // item == clazz
        // item == subItem
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;
        BindingResult result = (BindingResult) errors;

        if (!StringUtils.hasText(item.getItemName())) {
            result.rejectValue("itemName", "required");
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            result.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            result.rejectValue("quantity", "max", new Object[]{9999}, null);
        }

        // 특정 필드가 아닌 복합 폼 검증
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                result.reject("totalPriceMin", new Object[]{10000, resultPrice}, "값은 10000원을 초과해야 합니다.");
            }
        }

    }
}
