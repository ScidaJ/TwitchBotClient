package TwitchBot;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import static TwitchBot.MainConfigFile.CfgFilePath;

class MainCfgProcessing {

    /**
     * In program storage of username, oAuthKey, channel, and clientKey.
     */
    String username, oAuthKey, channel, clientKey;

    /**
     * Empty constructor for usable methods.
     */
    MainCfgProcessing(){}

    /**
     * Process data from cfg file that CoreCfgFile class returns
     */
    void getConfig(){
        String val;
        int splitAt;
        MainConfigFile file = new MainConfigFile();
        file.coreFileHandler();
        if(file.isDefault){
            this.username = "Bot Account";
            this.oAuthKey = "oauth:Bot Key";
            this.channel = "#(channel name)";
            this.clientKey = "Bot Account";
        } else {
            try(Stream<String> stream = Files.lines(Paths.get(CfgFilePath))){
                String[] array = stream.toArray(String[]::new);
                for(int i = 0; i < array.length; i++){
                    val = array[i];
                    splitAt = val.indexOf(':');
                    val = val.substring(splitAt + 1);
                    switch(i){
                        case(0):
                            this.username = val;
                            break;
                        case(1):
                            this.oAuthKey = val;
                            break;
                        case(2):
                            this.channel = val;
                            break;
                        case(3):
                            this.clientKey = val;
                            break;
                    }
                }
                stream.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
