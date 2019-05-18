package annotations.validations;

import annotations.ChoiceBoxNoEmpty;
import javafx.scene.control.ChoiceBox;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class ChoiceBoxNoEmptyValidator implements ConstraintValidator<ChoiceBoxNoEmpty, ChoiceBox> {

   public void initialize(ChoiceBoxNoEmpty constraint) {
   }


   public boolean isValid(ChoiceBox choiceBox, ConstraintValidatorContext context) {
      return  Optional.ofNullable(choiceBox.getValue()).isPresent();
   }
}
