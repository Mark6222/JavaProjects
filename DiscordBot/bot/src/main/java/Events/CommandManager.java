package Events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {
    @Override
    public void onGuildReady(GuildReadyEvent event) {
        event.getGuild().updateCommands().addCommands(
                Commands.slash("message", "send message with based bot")
                        .addOption(OptionType.STRING, "message", "message that will be sent")
                .addOptions(
                        new OptionData(OptionType.CHANNEL, "channel", "select channel")
                                .setChannelTypes()),

                Commands.slash("embed", "create a embed message")
                        .addOptions(
                                new OptionData(OptionType.STRING, "colour", "add colour")
                                        .addChoice("red", "red")
                                        .addChoice("blue", "blue")
                                        .addChoice("green", "green")
                                        .addChoice("orange", "orange")
                                        .addChoice("cyan", "cyan"))

                        .addOption(OptionType.STRING, "title", "set title")
                        .addOption(OptionType.STRING, "description", "set description")
                        .addOption(OptionType.STRING, "image-url", "set image")
                        .addOption(OptionType.STRING, "footer", "set footer")
                        .addOptions(
                                new OptionData(OptionType.CHANNEL, "channel", "select channel")
                                        .setChannelTypes()),
                Commands.slash("welcome", "welcome the bot"),
                Commands.slash("poll", "create a poll")
                        .addOption(OptionType.STRING,"question","question you want ask")
                        .addOption(OptionType.STRING,"choice1","poll option 1")
                        .addOption(OptionType.STRING,"emoji1","poll emoji for option 1")
                        .addOption(OptionType.STRING,"choice2","poll option 2")
                        .addOption(OptionType.STRING,"emoji2","poll emoji for option 2")
                        .addOption(OptionType.STRING,"choice3","poll option 3")
                        .addOption(OptionType.STRING,"emoji3","poll emoji for option 3")
                        .addOption(OptionType.STRING,"choice4","poll option 4")
                        .addOption(OptionType.STRING,"emoji4","poll emoji for option 4")
                        .addOption(OptionType.STRING,"choice5","poll option 5")
                        .addOption(OptionType.STRING,"emoji5","poll emoji for option 5")
                ).queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (command.equals("welcome")) {
            // run the slash
            String userTag = event.getUser().getAsTag();
            event.reply("Fuck you " + userTag + "**!").queue();
        }
        if (command.equals("message")) {
            // run the slash
            String userTag = event.getUser().getAsTag();
            String message = event.getOption("message").getAsString();
            TextChannel textChannel = event.getOption("channel").getAsChannel().asTextChannel();
            textChannel.sendMessage(message + "").queue();
            event.reply("Your message has been sent to " + textChannel.getName()).setEphemeral(true).queue();
        }
        if (command.equals("hello")) {
            // run the slash
            String userTag = event.getUser().getAsTag();
            Long id = event.getCommandIdLong();
            event.reply("Fuck off " + userTag + "**!");
            System.out.println(id);
        }
        if(command.equals("embed")){
            System.out.println(event.getOption("title").getAsString());
            EmbedBuilder eb = new EmbedBuilder();
            Color color = null;
            if(event.getOption("colour").equals("red")){color = Color.red;}
            if(event.getOption("colour").equals("blue")){color = Color.blue;}
            if(event.getOption("colour").equals("green")){color = Color.green;}
            if(event.getOption("colour").equals("orange")){color = Color.orange;}
            if(event.getOption("colour").equals("cyan")){color = Color.cyan;}
            if(color != null) {eb.setColor(color);}else {color = Color.green;eb.setColor(color);System.out.println(color);}
            if(event.getOption("title") != null) {
                eb.setTitle(event.getOption("title").getAsString(), null);
                System.out.println(event.getOption("title").getAsString());
            }
            if(event.getOption("description") != null) {
                eb.setDescription(event.getOption("description").getAsString());
            }
            if(event.getOption("image-url") != null) {
                eb.setImage(event.getOption("image-url").getAsString());
            }
            if(event.getOption("footer") != null) {
                eb.setFooter(event.getOption("footer").getAsString(), null);
            }
            TextChannel textChannel = event.getOption("channel").getAsChannel().asTextChannel();
            textChannel.sendMessageEmbeds(eb.build()).queue();
            event.reply("embed has been sent").setEphemeral(true).queue();
        }
        if(command.equals("poll")){
            EmbedBuilder eb = new EmbedBuilder();
            Color colour = Color.GREEN;
            String question;
            String emoji1 = "";
            String emoji2 = "";
            String emoji3 = "";
            String emoji4 = "";
            String emoji5 = "";
            String choice1 = "";
            String choice1Bar = "";
            String choice2 = "";
            String choice2Bar = "";
            String choice3 = "";
            String choice3Bar = "";
            String choice4 = "";
            String choice4Bar = "";
            String choice5 = "";
            String choice5Bar = "";
            eb.setColor(colour);
            if(event.getOption("question") != null){
                question = event.getOption("question").getAsString();
                eb.setTitle("Poll" + "\n" + question);
            }
            if(event.getOption("emoji1") != null){
                emoji1 = event.getOption("emoji1").getAsString();
            }
            if(event.getOption("emoji2") != null){
                emoji2 = event.getOption("emoji2").getAsString();
            }
            if(event.getOption("emoji3") != null){
                emoji3 = event.getOption("emoji3").getAsString();
            }
            if(event.getOption("emoji4") != null){
                emoji4 = event.getOption("emoji4").getAsString();
            }
            if(event.getOption("emoji5") != null){
                emoji5 = event.getOption("emoji5").getAsString();
            }
            if(event.getOption("choice1") != null){
                choice1 = event.getOption("choice1").getAsString();
            }
            if(event.getOption("choice2") != null){
                choice2 = event.getOption("choice2").getAsString();
            }
            if(event.getOption("choice3") != null){
                choice3 = event.getOption("choice3").getAsString();
            }
            if(event.getOption("choice4") != null){
                choice4 = event.getOption("choice4").getAsString();
            }
            if(event.getOption("choice5") != null){
                choice5 = event.getOption("choice5").getAsString();
            }
            eb.setDescription(emoji1 + "" + choice1 + '\n' + "      " + '\n'
                    + choice1Bar +'\n' + "      " + '\n'
                    + emoji2 + "" + choice2 + '\n' + "      " + '\n'
                    + choice1Bar +'\n' + "      " + '\n'
                    + emoji3 + "" + choice3 + '\n' + "      " + '\n'
                    + choice1Bar +'\n' + "      " + '\n'
                    + emoji4 + "" + choice4 + '\n' + "      " + '\n'
                    + choice1Bar +'\n' + "      " + '\n'
                    + emoji5 + "" + choice5 + '\n' + "      " + '\n'
                    + choice1Bar +'\n' + "      " + '\n'
            );
            TextChannel textChannel = event.getChannel().asTextChannel();
            textChannel.sendMessageEmbeds(eb.build()).setContent("poll")
                    .queue();
        }
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        System.out.println(event.getMessage().getAuthor().getAsTag() + " sent " + event.getMessage().getContentDisplay() + " " + event.getChannel().asTextChannel().getName());
        if((event.getMessage().getContentDisplay().equals("poll")) && (event.getMessage().getAuthor().getAsTag().equals("BasedBot#8574"))){
            event.getMessage().addReaction(Emoji.fromFormatted(":joy: "));
        }
    }

}