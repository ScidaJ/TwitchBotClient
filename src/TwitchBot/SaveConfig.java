package TwitchBot;

import java.nio.file.Files;
import java.nio.file.Paths;

import static TwitchBot.CoreCfgFile.CfgFilePath;


class SaveConfig {

    /**
     * Called when the save button is pressed in the window.
     * @param username Username currently in the username textfield.
     * @param oAuth oAuth key currently in oAuth key textfield
     * @param channel channel currently in channel textfield
     * @param clientKey clientID currently in clientkey textfield
     */
    static void updateConfig(String username, String oAuth, String channel, String clientKey){

        String text = "username:" + username + System.getProperty("line.separator") + "oAuthKey:" +
                oAuth + System.getProperty("line.separator") + "channel:" + channel +
                System.getProperty("line.separator") + "clientKey:" + clientKey;
        try {
            Files.write(Paths.get(CfgFilePath), text.getBytes());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
