package toysshop.core.storage;

import com.google.gson.Gson;
import toysshop.core.Operation;
import toysshop.repositorium.DBConnector;
import toysshop.repositorium.model.Toy;

import java.util.List;

public class AddNew implements Operation {
    private final String command = "NEW";
    private final DBConnector db;

    public AddNew(DBConnector db) {
        this.db = db;
    }

    public void run(){
        System.out.println("\033[H\033[2J");
        System.out.println("----- ДОБАВЛЕНИЕ НОВОГО ТОВАРА -----");
        String name = prompt("Введите наименование новой игрушки: ").toUpperCase();
        if (!db.isExist(name)) {
            int count = -1;
            while (count < 0) {
                try {
                    count = Integer.parseInt(prompt("Введите количество игрушек: "));
                } catch (NumberFormatException e) {
                    System.out.println("ERROR: Invalid data format... try again");
                }
            }
            Toy newToy = new Toy(db.newIndex(), name, count);
            Gson g = new Gson();
            db.add(List.of(g.toJson(newToy)));
        } else {
            System.out.printf("ERROR: %s already exists in database... Use SET STOCK option to apply changes\n", name);
            timeOut();
        }
    }
    @Override
    public String toString() {
        return command;
    }
}
