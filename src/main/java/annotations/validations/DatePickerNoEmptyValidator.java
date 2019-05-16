package annotations.validations;

import annotations.DatePickerNoEmpty;
import javafx.scene.control.DatePicker;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.Optional;

public class DatePickerNoEmptyValidator implements ConstraintValidator<DatePickerNoEmpty, DatePicker> {
   public void initialize(DatePickerNoEmpty constraint) {
   }

   @Override
   public boolean isValid(DatePicker datePicker, ConstraintValidatorContext context) {
      Optional<LocalDate> NullPointCheck = Optional.ofNullable(datePicker.getValue());
      return NullPointCheck.isPresent() && String.valueOf(datePicker.getValue()).length() > 0;
   }
}
