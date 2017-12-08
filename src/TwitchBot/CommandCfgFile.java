package TwitchBot;

import java.io.File;


class CommandCfgFile {

    boolean isEmpty = true;
    static String CfgFilePath = System.getProperty("user.home") + File.separator +
            "AppData" + File.separator + "Roaming" + File.separator + "Twitch Bot"
            + File.separator + "cfg" + File.separator + "twitchbotcommands.cfg";

    /**
     * Default constructor for access of files and isEmpty attribute
     */
    CommandCfgFile(){}

    /**
     * Either creates a new file or verifies that it exists
     * @return returns the cfg file
     */
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
