package TwitchBot;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.Set;

class ComGrid {

    ComGrid(TabPane tabRoot, Tab tab, ComCfgProcess com){
        GridPane gridRoot = new GridPane();
        gridSetup(gridRoot);
        tab.setContent(gridRoot);
        tabRoot.getTabs().add(tab);
        saveCommands(gridRoot, com);
        displayCommands(gridRoot);
    }

    private void saveCommands(GridPane gridRoot, ComCfgProcess com){
        TextField newCommand = new TextField();
        Button saveNewCom = new Button();
        saveNewCom.setText("Add");
        newCommand.setMaxWidth(150);
        newCommand.setPromptText("(Trigger)  (Message)");
        GridPane.setConstraints(newCommand,0,0);
        GridPane.setConstraints(saveNewCom,0,1);
        saveNewCom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SaveCommand.saveNewCom(newCommand.getText());
                WindowCore.commands = com.getCommands();
                gridRoot.getChildren().remove(2);
                displayCommands(gridRoot);
            }
        });
        gridRoot.getChildren().add(newCommand);
        gridRoot.getChildren().add(saveNewCom);
    }

    private void displayCommands(GridPane gridRoot){
        Button commands = new Button("Show Commands");
        commands.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               Stage stage = new Stage();
               BorderPane showCommands = new BorderPane();
               commandsToText(showCommands);
               Scene scene = new Scene(showCommands);
               stage.setScene(scene);
               stage.sizeToScene();
               stage.show();
            }
        });
        GridPane.setConstraints(commands, 1, 1);
        gridRoot.getChildren().add(commands);
    }

    private void commandsToText(BorderPane showCommands){
        String comText = "";
        Label commands = new Label();
        Set<String> keysSet = WindowCore.commands.keySet();
        String[] keys = keysSet.toArray(new String[keysSet.size()]);
        Arrays.sort(keys);
        for(String key: keys){
            comText += key + ":\t\t" + WindowCore.commands.get(key) + "\n";
        }
        commands.setText(comText);
        commands.setWrapText(true);
        showCommands.setCenter(commands);
        commands.setTextAlignment(TextAlignment.JUSTIFY);
    }

    /**
     * Just a method to take some of the bulk of code out of the start method
     * @param gridRoot Base GridPane which is being configured
     */
    private void gridSetup(GridPane gridRoot){
        gridRoot.setPadding(new Insets(10, 10, 10, 10));
        gridRoot.setHgap(5);
        gridRoot.setVgap(5);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHgrow(Priority.ALWAYS);
        gridRoot.getColumnConstraints().addAll(column1);
    }
}
