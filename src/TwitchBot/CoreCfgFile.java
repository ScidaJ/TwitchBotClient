package TwitchBot;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

class CoreCfgFile {
    boolean isDefault = true;
    private static String CfgDirPath = System.getProperty("user.home") + File.separator + "AppData"
            + File.separator + "Roaming" + File.separator + "Twitch Bot"
            + File.separator + "cfg" + File.separator;
    static String CfgFilePath = System.getProperty("user.home") + File.separator +
            "AppData" + File.separator + "Roaming" + File.separator + "Twitch Bot"
            + File.separator + "cfg" + File.separator + "twitchbotcore.cfg";

    CoreCfgFile(){}

    /**
     * Checks if file is present, if not, then it creates it.
     * @return CFG file of twitchbot for use with other classes
     */
    File coreFileHandler() {
        File dir = new File(CfgDirPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        File cfg = new File(CfgFilePath);
        if (cfg.exists()) {
            isDefault = false;
            return cfg;
        } else {
            try {
                cfg.createNewFile();
                String text = "username:" + System.getProperty("line.separator") + "oAuthKey:" +
                        System.getProperty("line.separator") + "channel:" +
                        System.getProperty("line.separator") + "clientKey:";
                Files.write(Paths.get(CfgFilePath), text.getBytes());
                return cfg;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
