package edu.java.bot;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.strategy.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommandChain {
    List<Strategy> commands;

    public CommandChain(@Autowired ApplicationContext applicationContext) {
        commands = new ArrayList<>(applicationContext.getBeansOfType(Strategy.class).values());
    }

    String execute(Update update) {

    }
}
