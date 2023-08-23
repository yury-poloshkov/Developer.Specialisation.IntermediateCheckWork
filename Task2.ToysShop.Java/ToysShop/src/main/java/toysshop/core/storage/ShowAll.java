package toysshop.core.storage;

import com.google.gson.Gson;
import toysshop.core.Operation;
import toysshop.repositorium.DBConnector;
import toysshop.repositorium.model.Toy;

import java.util.List;

public class ShowAll implements Operation {
    private String command = "LIST";
    private DBConnector db;

    public ShowAll(DBConnector db) {
        this.db = db;
    }

    public void run(){
        List<String> toysStock = db.readAll();
        if (!toysStock.isEmpty()) {
            Gson g = new Gson();
            System.out.println("\033[H\033[J");
            System.out.flush();
            System.out.println("----- СКЛАДСКОЙ ЗАПАС -----");
            for (String item:toysStock) System.out.println(g.fromJson(item, Toy.class));
        }
    }
    @Override
    public String toString() {
        return command;
    }
}
