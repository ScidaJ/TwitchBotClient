package TwitchBot;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import static TwitchBot.ComCfgFile.CfgFilePath;


class ComCfgProcess {

    Map getCommands(){
        ComCfgFile commandsFile = new ComCfgFile();
        commandsFile.comFileHandler();
        Map<String, String> commands = new HashMap<>();
        String key, com;
        int splitAt;
        if(commandsFile.isEmpty) {
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
            for (String string: array) {
                splitAt = string.indexOf(' ');
                key = string.substring(0, splitAt);
                com = string.substring(splitAt + 1);
                commands.put(key, com);
            }
            return commands;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
