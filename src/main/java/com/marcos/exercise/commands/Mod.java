package com.marcos.exercise.commands;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Mod extends AbstractCommand {

    private Integer number;

    public Mod(Integer number){
        this.number = number;
    }

    public List<Integer> run(Stream<Integer> sequence) {
        return sequence.map(value -> number % value).collect(Collectors.toList());
    }

}
