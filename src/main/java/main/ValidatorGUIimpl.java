package main;

import javafx.geometry.Bounds;
import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Field;
import java.util.Set;
@Component
public class ValidatorGUIimpl  implements ValidatorGUI {

    @Autowired
    Validator validator;
    @Autowired
    ValidatorFactory validatorFactory;

    public Boolean validateObject(Object clazz) {
        Set<ConstraintViolation<Object>> validation = validator.validate(clazz);
        if(!validation.isEmpty()) {
            showValidationErrors(validation);
            return false;
        }else {
            return true;
        }
    }

    private void showValidationErrors(Set<ConstraintViolation<Object>> validation) {
        validation.forEach(v -> {
            String property = v.getPropertyPath().toString();
            Object object = v.getLeafBean();
            try {
                Field field = object.getClass().getField(property);
                Control control = (Control) field.get(object);
                informationAboutErrors(control,v.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void informationAboutErrors(Control control, String message) {
        control.setTooltip(new Tooltip(message));
        Bounds boundsInScene = control.localToScreen(control.getBoundsInLocal());
        control.getTooltip().show(control, boundsInScene.getMinX(), boundsInScene.getMaxY());
        control.getTooltip().setAutoHide(true);
    }

}
