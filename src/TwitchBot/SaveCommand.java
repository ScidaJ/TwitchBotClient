package TwitchBot;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static TwitchBot.ComCfgFile.CfgFilePath;

class SaveCommand {

    static void saveNewCom(String command){
        if(command.contains("!")) {
            command = command.substring(1);
        }
        command += System.getProperty("line.separator");
        try{
            Files.write(Paths.get(CfgFilePath), command.getBytes(), StandardOpenOption.APPEND);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
