package tools;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Data
@PropertySource(value = "classpath:config.properties")
@Component
public class PropertiesReader {
    @Autowired
    private Environment propertiesFile;

}
