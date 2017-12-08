package TwitchBot;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Map;
import java.util.Set;

import static TwitchBot.BotCore.bot;

class ConfigGridSetup {

    private TextField usernameText;
    private TextField oAuthText;
    private TextField channelText;
    private TextField clientKeyText;

    /**
     * Creates a new gridpane, adds the textfields, text, and buttons, and sets it as the content for the tab
     * @param tabRoot TabPane which tab is in
     * @param tab Tab that the content is added to
     * @param core Core CFG file process, needed to fill textfields.
     */
    ConfigGridSetup(Stage stage, TabPane tabRoot, Tab tab, MainCfgProcessing core){
        GridPane gridRoot = new GridPane();
        gridSetup(gridRoot);
        tab.setContent(gridRoot);
        tabRoot.getTabs().add(tab);
        usernameText(gridRoot, core);
        oauthText(gridRoot, core);
        channelText(gridRoot, core);
        clientKeyText(gridRoot, core);
        connectButton(stage, gridRoot, core);
        saveConfigButton(gridRoot, core);
    }

    /**
     * Just a method to take some of the bulk of code out of the start method
     * @param gridRoot Base GridPane which is being configured
     */
    private void gridSetup(GridPane gridRoot){
        gridRoot.setPadding(new Insets(10, 10, 10, 10));
        gridRoot.setHgap(5);
        gridRoot.setVgap(5);
    }

    /**
     * Method for username textfield and text
     * @param gridRoot Base GridPane that the field and text are being configured for
     * @param core Core file that is used to fill textfield by default
     */
    private void usernameText(GridPane gridRoot, MainCfgProcessing core){
        Label username = new Label("Username:");
        usernameText = new TextField();
        usernameText.setPromptText(core.username);
        GridPane.setConstraints(username, 0, 0);
        GridPane.setConstraints(usernameText, 1, 0);
        gridRoot.getChildren().add(username);
        gridRoot.getChildren().add(usernameText);
    }

    /**
     * Method for oauth textfield and text
     * @param gridRoot Base GridPane that the field and text are being configured for
     * @param core Core file that is used to fill textfield by default
     */
    private void oauthText(GridPane gridRoot, MainCfgProcessing core){
        Label oAuth = new Label("oAuth Key");
        oAuthText = new TextField();
        oAuthText.setPromptText(core.oAuthKey);
        GridPane.setConstraints(oAuth, 0 , 1);
        GridPane.setConstraints(oAuthText, 1, 1);
        gridRoot.getChildren().add(oAuth);
        gridRoot.getChildren().add(oAuthText);
    }

    /**
     * Method for channel textfield and text
     * @param gridRoot Base GridPane that the field and text are being configured for
     * @param core Core file that is used to fill textfield by default
     */
    private void channelText(GridPane gridRoot, MainCfgProcessing core){
        Label channel = new Label("Channel");
        channelText = new TextField();
        channelText.setPromptText(core.channel);
        GridPane.setConstraints(channel, 0, 2);
        GridPane.setConstraints(channelText, 1, 2);
        gridRoot.getChildren().add(channel);
        gridRoot.getChildren().add(channelText);
    }

    /**
     * Method for clientKey textfield and text
     * @param gridRoot Base GridPane that the field and text are being configured for
     * @param core Core file that is used to fill textfield by default
     */
    private void clientKeyText(GridPane gridRoot, MainCfgProcessing core){
        Label clientKey = new Label("Client Key");
        clientKeyText = new TextField();
        clientKeyText.setPromptText(core.clientKey);
        GridPane.setConstraints(clientKey,0, 3);
        GridPane.setConstraints(clientKeyText, 1, 3);
        gridRoot.getChildren().add(clientKey);
        gridRoot.getChildren().add(clientKeyText);
    }

    /**
     * Method for connection button
     * @param gridRoot Base GridPane that the button is being configured for
     * @param core Core file that is used to pass information to botCore class
     */
    private void connectButton(Stage stage , GridPane gridRoot, MainCfgProcessing core){
        Button connect = new Button();
        connect.setText("Connect");
        connect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Set commands = ComCfgProcess.commands.keySet();
                bot = new BotCore(core.username, core.oAuthKey, core.clientKey, commands);
                bot.setChannel(core.channel);
                stage.hide();
                bot.botRun();
            }
        });
        GridPane.setConstraints(connect, 0, 4);
        gridRoot.getChildren().add(connect);
    }

    /**
     * Method for save config button
     * @param gridRoot Base GridPane that the button is being configured for
     * @param core Core file object that refreshed the information in the bot after config is saved
     */
    private void saveConfigButton(GridPane gridRoot, MainCfgProcessing core){
        Button save = new Button();
        save.setText("Save Config");
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String[] updated = saveFields(core);
                SaveConfig.updateConfig(updated[0],
                        updated[1],
                        updated[2],
                        updated[3]);
                core.getConfig();
            }
        });
        GridPane.setConstraints(save, 1, 4);
        gridRoot.getChildren().add(save);
    }

    /**
     * Generates and returns a string array that contains the updated fields to save
     * @param core The core Config process to grab the unchanged fields
     * @return An updated string that contains the updated fields to update
     */
    private String[] saveFields(MainCfgProcessing core){
        String[] updated = new String[4];
        if(usernameText.getText().equals(core.username) || usernameText.getText().isEmpty()){
            updated[0] = core.username;
            System.out.println("username not changed");
        } else {
            updated[0] = usernameText.getText();
            System.out.println("username changed to " + usernameText.getText());
        }
        if(oAuthText.getText().equals(core.oAuthKey) || oAuthText.getText().isEmpty()){
            updated[1] = core.oAuthKey;
            System.out.println("oAuth not changed");
        } else {
            updated[1] = oAuthText.getText();
            System.out.println("oAuth changed to " + oAuthText.getText());
        }
        if(channelText.getText().equals(core.channel) || channelText.getText().isEmpty()){
            updated[2] = core.channel;
            System.out.println("channel not changed");
        } else {
            updated[2] = channelText.getText();
            System.out.println("channel updated to " + channelText.getText());
        }
        if(clientKeyText.getText().equals(core.clientKey) || clientKeyText.getText().isEmpty()){
            updated[3] = core.clientKey;
            System.out.println("clientKey not changed");
        } else {
            updated[3] = clientKeyText.getText();
            System.out.println("clientKey changed to " + clientKeyText.getText());
        }
        return updated;
    }
}
