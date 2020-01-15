package com.marcos.exercise;

import com.marcos.exercise.commands.Operation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputValidator {

    private static InputValidator instance;
    private List<String> operations = new ArrayList<>();
    private List<String> signs = new ArrayList<>();

    private InputValidator() {
        //operations
        operations.add(Operation.ADD);
        operations.add(Operation.MOD);
        operations.add(Operation.MULTIPLY);
        //signs
        signs.add("+");
        signs.add("-");
        signs.add("%");
        signs.add("*");
        signs.add(".");
        signs.add("=");
    }

    public static InputValidator getInstance() {
        if (instance == null) {
            instance = new InputValidator();
        }
        return instance;
    }

    public boolean isSequence(String value) {
        String values[] = value.split(";");
        return Arrays.stream(values).allMatch(number -> isInteger(number))
                && !(values.length == 1 && signs.stream().anyMatch(item -> values[0].contains(item)));
    }

    public boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean isPositiveInteger(String value) {
        try {
            return Integer.parseInt(value) > 0;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public boolean isCommand(String value) {
        String operation = value.substring(0, 1);
        return operations.stream().anyMatch(item -> item.equals(operation)) && isPositiveInteger(value.substring(1));
    }

    public boolean isQuit(String value) {
        return value.equals(Operation.QUIT);
    }

    public boolean isUndo(String value) {
        return value.equals(Operation.UNDO);
    }

}
