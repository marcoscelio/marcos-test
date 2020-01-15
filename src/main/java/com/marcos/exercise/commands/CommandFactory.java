package com.marcos.exercise.commands;

public class CommandFactory {

    private CommandFactory() {
    }

    public static AbstractCommand getCommand(String value) {
        String operation = value.substring(0, 1);
        Integer number = Integer.parseInt(value.substring(1));
        switch (operation) {
            case Operation.ADD:
                return new Add(number);
            case Operation.MULTIPLY:
                return new Multiply(number);
            case Operation.MOD:
                return new Mod(number);
            default:
                return null;
        }
    }

}
