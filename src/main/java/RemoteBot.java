import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class RemoteBot extends TelegramLongPollingBot {
    private final MainPanel mainPanel;
    public RemoteBot(MainPanel mainPanel){
        this.mainPanel = mainPanel;
    }
    @Override
    public String getBotUsername() {
        return "YechielReminderBot";
    }

    @Override
    public String getBotToken() {
        return "5917192558:AAGJlzizYhgi-7bEbaa_46kVaca6XKuZyGk";
    }

    @Override
    public void onUpdateReceived(Update update) {
        long chatId;
        String toMove;
        if (update.hasCallbackQuery()){//button has clicked
            chatId=update.getCallbackQuery().getMessage().getChatId();
            toMove = update.getCallbackQuery().getData();

        }else {
            chatId = update.getMessage().getChatId();
            toMove = update.getMessage().getText();
        }
        switch (toMove){
            case "UL" -> {
                mainPanel.moveCar("s");
                mainPanel.moveCar("u");
                mainPanel.moveCar("l");
            }
            case "U" -> {
                mainPanel.moveCar("s");
                mainPanel.moveCar("u");
            }
            case "UR" -> {
                mainPanel.moveCar("s");
                mainPanel.moveCar("r");
                mainPanel.moveCar("u");
            }
            case "L" -> {
                mainPanel.moveCar("s");
                mainPanel.moveCar("l");
            }
            case "S" -> mainPanel.moveCar("s");
            case "R" -> {
                mainPanel.moveCar("s");
                mainPanel.moveCar("r");
            }
            case "DL" -> {
                mainPanel.moveCar("s");
                mainPanel.moveCar("d");
                mainPanel.moveCar("l");
            }
            case "D" -> {
                mainPanel.moveCar("s");
                mainPanel.moveCar("d");
            }
            case "DR" -> {
                mainPanel.moveCar("s");
                mainPanel.moveCar("r");
                mainPanel.moveCar("d");
            }
            case "S1"-> mainPanel.moveCar("s-");
            case "S2"-> mainPanel.moveCar("s+");
            case "P"->{
                mainPanel.moveCar("s");
                mainPanel.park();
            }
            default -> System.out.println("6");
        }
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        InlineKeyboardButton upLeft = new InlineKeyboardButton();
        upLeft.setText("Up left");
        upLeft.setCallbackData("UL");

        InlineKeyboardButton up = new InlineKeyboardButton();
        up.setText("Up");
        up.setCallbackData("U");

        InlineKeyboardButton UpRight = new InlineKeyboardButton();
        UpRight.setText("Up right");
        UpRight.setCallbackData("UR");

        InlineKeyboardButton left = new InlineKeyboardButton();
        left.setText("Left");
        left.setCallbackData("L");

        InlineKeyboardButton stop = new InlineKeyboardButton();
        stop.setText("Stop");
        stop.setCallbackData("S");

        InlineKeyboardButton right = new InlineKeyboardButton();
        right.setText("Right");
        right.setCallbackData("R");

        InlineKeyboardButton downLeft = new InlineKeyboardButton();
        downLeft.setText("Down left");
        downLeft.setCallbackData("DL");

        InlineKeyboardButton down = new InlineKeyboardButton();
        down.setText("Down");
        down.setCallbackData("D");

        InlineKeyboardButton downRight = new InlineKeyboardButton();
        downRight.setText("Down right");
        downRight.setCallbackData("DR");

        InlineKeyboardButton speed1 = new InlineKeyboardButton();
        speed1.setText("Speed --");
        speed1.setCallbackData("S1");

        InlineKeyboardButton speed2 = new InlineKeyboardButton();
        speed2.setText("Speed ++");
        speed2.setCallbackData("S2");

        InlineKeyboardButton park = new InlineKeyboardButton();
        park.setText("Park");
        park.setCallbackData("P");




        List<InlineKeyboardButton> topRow = List.of(upLeft,up,UpRight);
        List<InlineKeyboardButton> secondRow = List.of(left,stop,right);
        List<InlineKeyboardButton> thirdRow = List.of(downLeft,down,downRight);
        List<InlineKeyboardButton> fRow = List.of(speed1,park,speed2);
        List<List<InlineKeyboardButton>> keyBoard = List.of(topRow,secondRow,thirdRow,fRow);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyBoard);
        sendMessage.setText("Chose button");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        send(sendMessage);
    }
    public void send(SendMessage sendMessage){
        try {
            execute(sendMessage);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
