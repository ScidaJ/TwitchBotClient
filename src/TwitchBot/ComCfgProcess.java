package TwitchBot;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import static TwitchBot.ComCfgFile.CfgFilePath;


class ComCfgProcess {

    static Map<String,String> commands;

    /**
     * Retrieves the twitchbotcommands.cfg file and reads the commands contained in it. If it does not exist
     *  then it creates a skeleton file
     * @return A map of commands and their keys
     */
    void getCommands(){
        ComCfgFile commandsFile = new ComCfgFile();
        commandsFile.comFileHandler();
        Map<String, String> commands = new HashMap<>();
        String key, com;
        int splitAt;
        if(commandsFile.isEmpty) { //Checks if the cfg file is empty
            String defaultCommands = "help Displays all commands available from the bot" + System.getProperty("line.separator")
                    + "info Displays development information about the bot" + System.getProperty("line.separator");
            try {
                Files.write(Paths.get(CfgFilePath), defaultCommands.getBytes());
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        try{
            Stream<String> stream = Files.lines(Paths.get(CfgFilePath));
            String[] array = stream.toArray(String[]::new);
            for (String string: array) { //Splits the string by key
                splitAt = string.indexOf(' ');
                key = string.substring(0, splitAt);
                com = string.substring(splitAt + 1);
                commands.put(key, com);
            }
            this.commands = commands;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
