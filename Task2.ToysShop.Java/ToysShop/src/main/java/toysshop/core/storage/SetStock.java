package toysshop.core.storage;

import com.google.gson.Gson;
import toysshop.core.Operation;
import toysshop.repositorium.DBConnector;
import toysshop.repositorium.model.Toy;

import java.util.List;

public class SetStock implements Operation {
    private final String command = "SET STOCK";

    private final DBConnector db;

    public SetStock(DBConnector db) {
        this.db = db;
    }

    public void run(){
        System.out.println("\033[H\033[J");
        System.out.flush();
        System.out.println("----- ИЗМЕНЕНИЕ СКЛАДСКОГО ЗАПАСА -----");
        List<String> toysStock = db.readAll();
        if (!toysStock.isEmpty()) {
            String strID = prompt("Введите id товара: ");
            if (strID.matches("\\d{1,6}")) {
                int currentID = Integer.parseInt(strID);
                if (currentID < toysStock.size()) {
                    int newCount = -1;
                    while (newCount < 0) {
                        try {
                            newCount = Integer.parseInt(prompt("Введите количество игрушек: "));
                        } catch (NumberFormatException e) {
                            System.out.println("ERROR: Invalid data format... try again");
                        }
                    }
                    Gson g = new Gson();
                    Toy currentToy = g.fromJson(toysStock.get(currentID-1), Toy.class);
                    currentToy.setCount(newCount);
                    toysStock.set(currentID-1,g.toJson(currentToy));
                    db.saveAll(toysStock);
                } else {
                    System.out.printf("ERROR: Wrong id = %d. Max id allowed %d.\n", currentID, toysStock.size()+1);
                }
            }else {
                System.out.println("ERROR: Invalid data format... try again");
            }
        }else {
            System.out.println("ERROR: Storage is empty... Add new toys by NEW option\n");
        }
    }
    @Override
    public String toString() {
        return command;
    }

}
