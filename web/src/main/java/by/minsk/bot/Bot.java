package by.minsk.bot;

import by.minsk.config.BotConfig;
import by.minsk.dto.CityDTO;
import by.minsk.impl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
public class Bot extends TelegramLongPollingBot {
    private final CityServiceImpl cityService;
    @Autowired
    public Bot(CityServiceImpl cityService) {
        this.cityService = cityService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String text;
        CityDTO cityDTO = cityService.getByName(message.getText());
        if (cityDTO != null) {
            text = cityDTO.getDescription();
            sendMessage(message, text);
        } else {
            text = "Такого города нет в списке(";
            sendMessage(message, text);
        }

    }

    @Override
    public String getBotUsername() {
        return BotConfig.USERNAME;
    }

    @Override
    public String getBotToken() {
        return BotConfig.TOKEN;
    }

    private void sendMessage(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }
}
