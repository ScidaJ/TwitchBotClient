package TwitchBot;
import com.cavariux.twitchirc.Core.TwitchBot;
import com.cavariux.twitchirc.Chat.Channel;

public class botCore extends TwitchBot{

    static botCore bot;
    private String channelJoin;

    /**
     * Constructor that sets the properties in the TwitchBot class.
     * @param username Username of bot account
     * @param oauth oauth key of bot account
     * @param client Client ID of bot account
     */
    botCore(String username, String oauth, String client){
        this.setUsername(username);
        this.setOauth_Key(oauth);
        this.setClientID(client);
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

    /**
     * Stops the bot, disconnects it from the channel
     */
    protected void botStop(){
        if(bot.isRunning()){
            bot.stop();
        }
    }
}
