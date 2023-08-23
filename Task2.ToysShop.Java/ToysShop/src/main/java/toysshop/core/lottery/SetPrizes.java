package toysshop.core.lottery;

import toysshop.core.Operation;
import toysshop.repositorium.DBConnector;

public class SetPrizes implements Operation {
    private String command = "SET PRIZES";

    private DBConnector db;

    public SetPrizes(DBConnector db) {
        this.db = db;
    }
    @Override
    public String toString() {
        return command;
    }
}
