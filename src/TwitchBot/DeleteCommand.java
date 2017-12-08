package TwitchBot;

public class DeleteCommand {

    static void deleteCom(String comKey){
        ComCfgProcess.commands.remove(comKey);
        ComCfgProcess.getCommands();
    }

    void deleteDropDown(){}

}
