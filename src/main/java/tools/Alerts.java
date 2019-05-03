package tools;

import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:config.properties", encoding="UTF-8")
public class Alerts {

    private static Environment propertiesFile;

    @Autowired
    public Alerts(Environment propertiesFile) {
        Alerts.propertiesFile = propertiesFile;
    }

    public static void alertShowAndWait(){
        Alert alertWrongCredentials = new Alert(Alert.AlertType.INFORMATION);
        alertWrongCredentials.setHeaderText(propertiesFile.getProperty("wrongLoginDetails"));
        alertWrongCredentials.showAndWait();
    }
}
