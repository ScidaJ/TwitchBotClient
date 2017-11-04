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

    /**
     * Constructor for command grid objects
     * @param tabRoot The tabpane in which the grid is on
     * @param tab The current tab the grid is stored in
     * @param com Command cfg process
     */
    ComGrid(TabPane tabRoot, Tab tab, ComCfgProcess com){
        GridPane gridRoot = new GridPane();
        gridSetup(gridRoot);
        tab.setContent(gridRoot);
        tabRoot.getTabs().add(tab);
        saveCommands(gridRoot, com);
        displayCommands(gridRoot);
    }

    /**
     * Sets up the save commands grid block
     * @param gridRoot The gridpane that the grid block is put on
     * @param com Command cfg process
     */
    private void saveCommands(GridPane gridRoot, ComCfgProcess com){
        TextField newCommand = new TextField();
        Button saveNewCom = new Button();
        saveNewCom.setText("Add");
        newCommand.setMaxWidth(150);
        newCommand.setPromptText("(Trigger)  (Message)"); //Show in the save commands textbox
        GridPane.setConstraints(newCommand,0,0); //Establishing where the elements are placed in the grid
        GridPane.setConstraints(saveNewCom,0,1);
        saveNewCom.setOnAction(new EventHandler<ActionEvent>() { //Action on button press
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

    /**
     * Method to create new window to display commands on
     * @param gridRoot Gridpane for the button to be placed on
     */
    private void displayCommands(GridPane gridRoot){
        Button commands = new Button("Show Commands");
        commands.setOnAction(new EventHandler<ActionEvent>() { //Action on button press
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

    /**
     * Converts the commands to a string to be displayed
     * @param showCommands Pane in which to put the commands
     */
    private void commandsToText(BorderPane showCommands){
        String comText = "";
        Label commands = new Label();
        Set<String> keysSet = WindowCore.commands.keySet();
        String[] keys = keysSet.toArray(new String[keysSet.size()]);
        Arrays.sort(keys);
        for(String key: keys){ //For each loop
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
