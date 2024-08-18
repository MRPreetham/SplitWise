package org.example.splitwise;

import org.example.splitwise.Commands.CommandsRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SplitWiseApplication implements CommandLineRunner {
    private final CommandsRegistry commandsRegistry;
    private final Scanner scanner;

    public SplitWiseApplication(CommandsRegistry commandsRegistry){
        this.commandsRegistry = commandsRegistry;
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        SpringApplication.run(SplitWiseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            System.out.println("Enter your input: ");
            String input = scanner.nextLine();
            commandsRegistry.execute(input);
        }
    }
}
