package toysshop.ui;

import toysshop.core.Operation;

import java.util.ArrayList;

public class StorageView implements Operation {
    private final String command = "STORAGE";
    private final String header = "----- Магазин игрушек v.2023.08.20 -----\n" +
            "--- МЕНЮ УПРАВЛЕНИЯ ЗАПАСАМИ ---";
    private final ArrayList<Operation> options;

    public StorageView(ArrayList<Operation> options) {
        this.options = options;
    }

    public void run(){
        showMenu(header, options);
        String userChoice = prompt("\\Введите команду> ").toUpperCase();
        while (!userChoice.equals("EXIT")) {
            for (Operation option : options) {
                if (userChoice.equals(option.toString())) {
                    option.run();
                    timeOut();
                }
            }
            showMenu(header, options);
            userChoice = prompt("\\Введите команду> ").toUpperCase();
        }
    }

    @Override
    public String toString() {
        return command;
    }
}
