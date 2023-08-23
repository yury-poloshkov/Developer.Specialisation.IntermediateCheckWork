package toysshop.core.lottery;

import toysshop.core.Operation;
import toysshop.repositorium.DBConnector;

public class Run implements Operation {
    private String command = "RUN";
    private DBConnector db;

    public Run(DBConnector db) {
        this.db = db;
    }

    @Override
    public String toString() {
        return command;
    }
}
