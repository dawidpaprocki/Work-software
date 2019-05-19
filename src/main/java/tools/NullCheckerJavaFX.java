package tools;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class NullCheckerJavaFX {
    @Autowired
    private PropertiesReader propertiesFile;

    public boolean nullChecker(ComboBox comboBox) {

        return Optional.ofNullable(comboBox.getValue()).isPresent() &&
                !(comboBox.getValue().toString().equals(propertiesFile.getPropertiesFile().getProperty("choiceInformation")));
    }
    public boolean nullChecker(ChoiceBox choiceBox) {
        return Optional.ofNullable(choiceBox.getValue()).isPresent() &&
                !(choiceBox.getValue().toString().equals(propertiesFile.getPropertiesFile().getProperty("choiceInformation")));
    }
}
