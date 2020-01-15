package com.marcos.exercise.commands;

import java.util.List;
import java.util.stream.Stream;

public abstract class AbstractCommand {

    abstract public List<Integer> run(Stream<Integer> sequence);

}
