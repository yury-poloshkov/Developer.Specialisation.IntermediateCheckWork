package toysshop.ui;

import toysshop.core.Operation;

import java.util.ArrayList;
import java.util.List;

public class MainView implements Operation {
    private String command = "EXIT";
    private String header = "----- Магазин игрушек v.2023.08.20 -----\n" +
            "--- ГЛАВНОЕ МЕНЮ: ---";
    private ArrayList<Operation> options;

    public MainView(ArrayList<Operation> options) {
        this.options = options;
    }

    public void run(){
        showMenu(header, options);
        String userChoice = prompt("\\Введите команду> ").toUpperCase();
        while (!userChoice.equals("EXIT")) {
            for (Operation option : options) {
                if (userChoice.equals(option.toString())) {
                    option.run();
                }
            }
            showMenu(header, options);
            userChoice = prompt("\\Введите команду> ").toUpperCase();
        }
        System.out.println("\n----- Завершение работы приложения -----");
    }

    @Override
    public String toString() {
        return command;
    }
}
