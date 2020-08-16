package ru.otus.raukhvarger.homework_5.utils.io;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class IOConsoleService implements IOProvider {
    private final Scanner sc;

    public IOConsoleService() {
        this.sc = new Scanner(System.in);
    }

    @Override
    public String read() {
        return sc.nextLine();
    }

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }
}
