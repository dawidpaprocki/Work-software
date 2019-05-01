package main;

import fxControllers.SecurityPrivilegesSetup;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

@ComponentScan({"crud", "fxControllers", "main", "crud.model","enums"})
@EntityScan("crud/model")
@EnableJpaRepositories("crud.repository")
@SpringBootApplication
public class Main extends Application {
    private ConfigurableApplicationContext springContext;
    private Parent rootNode;
    private FXMLLoader fxmlLoader;
    @Autowired
    private SecurityPrivilegesSetup securityPrivilegesSetup;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        springContext = SpringApplication.run(Main.class);
        initSecurity();
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        fxmlLoader.setLocation(getClass().getResource("/UserInterfaceFXML/mainUIFXML.fxml"));
        rootNode = fxmlLoader.load();
        primaryStage.setTitle("Organizacja pracy");
        Scene scene = new Scene(rootNode);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void initSecurity() {
        SecurityContextHolder.setStrategyName("MODE_GLOBAL");
        initAnonymous();
    }

    public static void initAnonymous() {
        AnonymousAuthenticationToken auth = new AnonymousAuthenticationToken(
                "anonymous", "anonymous",
                AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS"));

        SecurityContextHolder.getContext().setAuthentication(auth);
    }
    public static void logout(){
        SecurityContextHolder.clearContext();
        initAnonymous();
    }


    @Override
    public void stop() {
        springContext.stop();
    }
}
