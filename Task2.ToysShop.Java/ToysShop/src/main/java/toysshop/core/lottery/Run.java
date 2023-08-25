package toysshop.core.lottery;

import toysshop.core.Operation;
import toysshop.repositorium.DBConnector;

public class Run implements Operation {
    private String command = "RUN";
    private DBConnector lotteryDB;
    private DBConnector storageDB;

    public Run(DBConnector lotteryDB, DBConnector storageDB) {
        this.lotteryDB = lotteryDB;
        this.storageDB = storageDB;
    }

    public Run(DBConnector lotteryDB) {
        this.lotteryDB = lotteryDB;
    }

    @Override
    public String toString() {
        return command;
    }
}
