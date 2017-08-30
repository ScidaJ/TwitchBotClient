package TwitchBot;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;

public class WindowCore extends Application{

    TrayIcon trayIcon = null;
    static Map<String, String> commands;

    /**
     * Base method for Window
     * @param primaryStage Window.
     */
    public void start(Stage primaryStage){
        Group root = new Group();
        gridTabSetup(primaryStage, root);
        Scene scene = new Scene(root, 600, 500);
        try {
            systemTraySetup();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        primaryStage.setTitle("Twitch Bot");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
    }

    /**
     * Base method for system tray icon and what is does.
     * @throws IOException In case the icon is not there.
     */
    private void systemTraySetup() throws IOException{
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        SystemTray tray = SystemTray.getSystemTray();
        Image image = ImageIO.read(getClass().getResource("/resources/FFXrxPK0_400x400.png"));
        PopupMenu popup = new PopupMenu();
        MenuItem close = new MenuItem();
        close.setLabel("Close");
        close.addActionListener(actionListener);
        popup.add(close);
        trayIcon = new TrayIcon(image, "TwitchBot", popup);
        trayIcon.setImageAutoSize(true);
        try{
            tray.add(trayIcon);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void gridTabSetup(Stage primaryStage, Group root){
        CoreCfgProcess core = new CoreCfgProcess();
        core.getConfig();
        ComCfgProcess com = new ComCfgProcess();
        commands = com.getCommands();
        TabPane tabRoot = new TabPane();
        Tab gridPane1 = new Tab();
        tabRoot.setMaxWidth(600);
        gridPane1.setText("Connection Configuration");
        Tab gridPane2 = new Tab();
        gridPane2.setText("Commands");
        tabRoot.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        ConfigGrid gridConfig = new ConfigGrid(primaryStage , tabRoot, gridPane1, core);
        ComGrid gridCommand = new ComGrid(tabRoot, gridPane2, com);
        root.getChildren().add(tabRoot);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
