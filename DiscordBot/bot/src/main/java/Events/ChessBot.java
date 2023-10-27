package Events;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ChessBot extends ListenerAdapter {

    public static final String POLL_COMMAND = "!poll";
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User author = event.getAuthor();
        MessageChannel channel = event.getChannel();
        Message message = event.getMessage();

        if (author.isBot()) {
            return;
        }

        String content = message.getContentRaw();
        if (content.startsWith(POLL_COMMAND)) {
            String[] parts = content.split(" ", 3);
            if (parts.length >= 3) {
                sendPoll(channel, parts[1], parts[2]);
            } else {
                channel.sendMessage("Invalid poll format. Use !poll [question] [option1] [option2] ...").queue();
            }
        }
    }

    private void sendPoll(MessageChannel channel, String question, String options) {
        TextChannel textChannel = (TextChannel) channel;
        MessageEmbed embed;
        embed = new MessageEmbed(null, "Poll: " + question, null, null, null, 0, null, null, null, null, null, null, null);
        String[] optionArray = options.split(" ");
        for (int i = 0; i < optionArray.length; i++) {
            embed.getFields().add(new Field("Option " + (i + 1), optionArray[i], false));
        }
        Message pollMessage = textChannel.sendMessage((CharSequence) embed).complete();
        String emojiIdentifier = "👍";

// Create an Emoji instance
        Emoji emoji = Emoji.fromUnicode(emojiIdentifier);
        for (int i = 0; i < optionArray.length; i++) {
            pollMessage.addReaction(emoji).queue();
        }
    }

    public static void main(String[] args) throws Exception {
        JDABuilder builder = JDABuilder.createDefault("MTA1NDc3MzY1NDI1NDI2NDQ0MQ.GalP3U.aNDNBpnVY-8O2erSEm3g_jwu4xmOIatgxZkEow");
        builder.addEventListeners(new ChessBot());
        builder.build();
    }
}
