package toysshop.core;

import java.util.ArrayList;
import java.util.Scanner;

public interface Operation {
//    String header = null;
//    ArrayList<Operation> options = null;
    default void run() {};
//        String userChoice = "None";
//        while (!userChoice.equals("EXIT")) {
//            showMenu(header, options);
//            userChoice = prompt("\\Введите команду> ").toUpperCase();
//            for (Operation option : options) {
//                if (userChoice.equals(option)) {
//                    option.run();
//                }
//            }
//        }


    default void showMenu(String header, ArrayList<Operation> options){
        System.out.println("\033[H\033[2J");
        System.out.flush();
        String sb = header + "\n";
        for (Operation option : options){
            sb += option.toString() + "\n";
        }
        sb += "------------------------------";
        System.out.println(sb);
    }
     default String prompt(String message){
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
     default void timeOut() {
        System.out.println("Press ENTER to continue");
        Scanner stopScn = new Scanner(System.in);
        stopScn.nextLine();
    }
}
