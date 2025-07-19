package hello.itemservice.validation;

import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageCodeResolverTest {
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodeResolverObject() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
        assertThat(messageCodes).containsExactly("required.item", "required");
//        new ObjectError("item" new String[]{"required.item", "required"});
    }

    @Test
    void messageCodesResolverField() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
        }
        // new BindingResult().rejectValue("itemName", "required");
        // new FieldError("item", "itemName", null, false, messageCodes, null, null, );
        assertThat(messageCodes).containsExactly(
                "required.item.itemName",
                        "required.itemName",
                        "required.java.lang.String",
                        "required"
        );
    }
}
