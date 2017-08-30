package TwitchBot;
import com.cavariux.twitchirc.Chat.User;
import com.cavariux.twitchirc.Core.TwitchBot;
import com.cavariux.twitchirc.Chat.Channel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;

public class BotCore extends TwitchBot{

    static BotCore bot;
    private String channelJoin;
    private Set<String> commands;


    /**
     * Constructor that sets the properties in the TwitchBot class.
     * @param username Username of bot account
     * @param oauth oauth key of bot account
     * @param client Client ID of bot account
     */
    BotCore(String username, String oauth, String client, Set commands){
        this.setUsername(username);
        this.setOauth_Key(oauth);
        this.setClientID(client);
        this.commands = commands;
    }

    /**
     * Method used by the bot to connect to the Twitch channel
     */
    void botRun(){
        bot.connect();
        Channel channel = bot.joinChannel(channelJoin);
        bot.sendMessage("Hi, I'm connected!", channel);
        bot.start();
    }

    /**
     * Sets that channel that the bot is supposed to join
     * @param channel String that has the channel name
     */
    void setChannel(String channel){
        this.channelJoin = channel;
    }

    @Override
    public void onCommand(User user, Channel channel, String command){
        if(command.equals("kill")){
            if(user.isMod(channel)) {
                botKill(channel);
            }
        } else if(command.equals("stop")) {
            botStop();
        } else if(command.equals("help")) {
            String message = "";
            String[] tempCommands = commands.toArray(new String[commands.size()]);
            for (int i = 0; i < commands.size(); i++) {
                if (!tempCommands[i].equals("help")) {
                    message += "!" + tempCommands[i] + " " + WindowCore.commands.get(tempCommands[i]) + " | ";
                }
            }
            this.sendMessage(message, channel);
        } else if(command.equals("info")){
            this.sendMessage("Hello! I am a TwitchBot made by ThatGuyinPJs, developed in Java! If you want to " +
                    "learn more about the libraries used or how he made me, go to this link: https://github.com/jrs8979/TwitchBotClient " +
                    "to see the GitHub repository of the project. If you have any suggestions, or encounter any bugs, please let" +
                    " Jacob know. Or, if you want to make your own changes, do not hesitate to make a fork.", channel);
        } else if(commands.contains(command)){
            this.sendMessage(WindowCore.commands.get(command), channel);
        }
    }

    /**
     * Kills the bot process. Other ways to do this are to close from system tray, or to kill process in task manager
     */
    private void botKill(Channel channel){
        if(bot.isRunning()){
            this.sendMessage("Goodbye", channel);
            botStop();
            System.exit(0);
        }
    }

    /**
     * Stops the bot, disconnects it from the channel
     */
    private void botStop(){
        if(bot.isRunning()){
            bot.stop();
        }
    }
}
