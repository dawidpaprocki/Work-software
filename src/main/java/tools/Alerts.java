package tools;

import javafx.scene.control.Alert;
import org.springframework.core.env.Environment;

public class Alerts {

    public static void alertShowAndWait(Environment propertiesFileEnvironment){
        Alert alertWrongCredentials = new Alert(Alert.AlertType.INFORMATION);
        alertWrongCredentials.setHeaderText(propertiesFileEnvironment.getProperty("wrongLoginDetails"));
        alertWrongCredentials.showAndWait();
    }
}
