package TwitchBot;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static TwitchBot.CommandCfgFile.CfgFilePath;

class SaveCommand {

    static void saveNewCom(String command){
        if(command.contains("!")) {
            command = command.substring(1);
        }
        command += System.getProperty("line.separator");
        try{ //Attempting to write to .cfg file in Appdata
            Files.write(Paths.get(CfgFilePath), command.getBytes(), StandardOpenOption.APPEND);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
