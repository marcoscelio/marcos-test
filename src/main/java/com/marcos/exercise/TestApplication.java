package com.marcos.exercise;

import com.marcos.exercise.commands.AbstractCommand;
import com.marcos.exercise.commands.CommandFactory;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TestApplication {

    private static InputValidator inputValidator = InputValidator.getInstance();

    private static List<Integer> sequence;
    private static LinkedList<List<Integer>> sequenceHistory = new LinkedList<List<Integer>>();

    private static boolean isRunning = true;

    public static void main(String[] args) {
        run(args);
    }

    private static void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext() && isRunning) {
            boolean isAnyCommandRun = false;
            String value = scanner.nextLine();

            if(value == null || value.isEmpty() || value.trim().equals("")){
                continue;
            }

            if (inputValidator.isSequence(value)) {
                sequence = Arrays.stream(value.split(";")).map(number -> Integer.parseInt(number))
                        .collect(Collectors.toList());
                isAnyCommandRun = true;
                System.out.println(sequence);
                continue;
            } else {
                if (sequence == null) {
                    System.out.println("It is required to input a number sequence");
                    isAnyCommandRun = true;
                    continue;
                }

                if (inputValidator.isUndo(value)) {
                    sequence.clear();
                    if(!sequenceHistory.isEmpty()){
                        sequence.addAll(sequenceHistory.pollLast());
                    }
                    isAnyCommandRun = true;
                    System.out.println(sequence);
                    continue;
                }

                if (inputValidator.isQuit(value)) {
                    isRunning = false;
                    isAnyCommandRun = true;
                    break;
                }

                if (inputValidator.isCommand(value)) {
                    AbstractCommand command = CommandFactory.getCommand(value);
                    sequenceHistory.add(sequence);
                    sequence = command.run(sequence.stream());
                    isAnyCommandRun = true;
                    System.out.println(sequence);
                    continue;
                }
            }

            if(!isAnyCommandRun){
                System.out.println("No command identified!");
                continue;
            }
        }
    }

    private AbstractCommand getOperation(String value) {
        String operation = value.substring(0, 1);
        return CommandFactory.getCommand(operation);
    }


}
