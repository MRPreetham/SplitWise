package org.example.splitwise.Commands;

public interface Command {
    boolean validate(String input);
    void execute(String input);
}
