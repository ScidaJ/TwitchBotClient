package TwitchBot;

import java.io.File;


class ComCfgFile {

    boolean isEmpty = true;
    static String CfgFilePath = System.getProperty("user.home") + File.separator +
            "AppData" + File.separator + "Roaming" + File.separator + "Twitch Bot"
            + File.separator + "cfg" + File.separator + "twitchbotcommands.cfg";

    ComCfgFile(){}

    File comFileHandler(){
        File cfg = new File(CfgFilePath);
        if(!cfg.exists()){
            try{
                cfg.createNewFile();
                return cfg;
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
        } else {
            isEmpty = false;
            return cfg;
        }
    }
}
