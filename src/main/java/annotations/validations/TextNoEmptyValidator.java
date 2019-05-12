package annotations.validations;

import annotations.TextFieldNoEmpty;
import javafx.scene.control.TextField;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class TextNoEmptyValidator implements ConstraintValidator<TextFieldNoEmpty, TextField> {
   public void initialize(TextFieldNoEmpty constraint) {
   }

   @Override
   public boolean isValid(TextField textField, ConstraintValidatorContext context) {
      Optional<TextField> NullPointCheck = Optional.ofNullable(textField);
      return NullPointCheck.isPresent() && textField.getText().length() > 0;
   }
}
