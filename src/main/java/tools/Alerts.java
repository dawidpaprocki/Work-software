package tools;

import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Alerts {

   @Autowired
   private static PropertiesReader propertiesFile;

    @Autowired
    public Alerts(PropertiesReader propertiesFile) {

        Alerts.propertiesFile = propertiesFile;
    }

    public static void alertShowAndWait(){
        Alert alertWrongCredentials = new Alert(Alert.AlertType.INFORMATION);
        alertWrongCredentials.setHeaderText(propertiesFile.getPropertiesFile().getProperty("wrongLoginDetails"));
        alertWrongCredentials.showAndWait();
    }
}
