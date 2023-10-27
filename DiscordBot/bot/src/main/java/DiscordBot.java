import Events.CommandManager;
import Events.InteractionEventListener;
import Events.MessageEventListener;
import Events.ReadyEventListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class DiscordBot {

    public DiscordBot() throws LoginException {
        final String Token = "MTA1NDc3MzY1NDI1NDI2NDQ0MQ.GalP3U.aNDNBpnVY-8O2erSEm3g_jwu4xmOIatgxZkEow";
        JDABuilder jdaBuilder = JDABuilder.createDefault(Token);
        jdaBuilder.setStatus(OnlineStatus.ONLINE);
        jdaBuilder.setActivity(Activity.watching("You"));
        JDA jda = jdaBuilder
                .addEventListeners(new InteractionEventListener(), new MessageEventListener(), new ReadyEventListener(), new CommandManager())
                .build();
    }

    public static void main(String[] args) {
        try {
            DiscordBot bot = new DiscordBot();
        } catch (LoginException e){
            System.out.println("ERROR: Provided bot token is invalid!");
        }
    }
}
