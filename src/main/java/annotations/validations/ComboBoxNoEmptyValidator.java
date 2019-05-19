package annotations.validations;

import annotations.ComboBoxNoEmpty;
import javafx.scene.control.ComboBox;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class ComboBoxNoEmptyValidator implements ConstraintValidator<ComboBoxNoEmpty, ComboBox> {

   public void initialize(ComboBoxNoEmpty constraint) {
   }

   public boolean isValid(ComboBox comboBox, ConstraintValidatorContext context) {
      return  Optional.ofNullable(comboBox.getValue()).isPresent();
   }
}
