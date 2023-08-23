package toysshop;

import toysshop.core.Operation;
import toysshop.core.lottery.Run;
import toysshop.core.lottery.SetPrizes;
import toysshop.core.storage.ShowAll;
import toysshop.core.storage.AddNew;
import toysshop.core.storage.SetStock;
import toysshop.repositorium.DBConnector;
import toysshop.ui.LotteryView;
import toysshop.ui.MainView;
import toysshop.ui.StorageView;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String storagePath = "storage.json";
        DBConnector storageDB = new DBConnector(storagePath);
        String lotteryPath = "lottery.json";
        DBConnector lotteryDB = new DBConnector(lotteryPath);
        Operation mainMenu = new MainView(initUI(storageDB, lotteryDB));
        mainMenu.run();
    }

    private static ArrayList<Operation> initUI(DBConnector storageDB, DBConnector lotteryDB) {
        ArrayList<Operation> mainUI = new ArrayList<>();
        mainUI.add(new StorageView(initStorage(storageDB)));
        mainUI.add(new LotteryView(initLottery(lotteryDB)));
        mainUI.add(new MainView(null));
        return mainUI;
    }

    private static ArrayList<Operation> initLottery(DBConnector lotteryDB) {
        ArrayList<Operation> lotteryUI = new ArrayList<>();
        lotteryUI.add(new SetPrizes(lotteryDB));
        lotteryUI.add(new Run(lotteryDB));
        lotteryUI.add(new MainView(null));
        return lotteryUI;
    }

    private static ArrayList<Operation> initStorage(DBConnector storageDB){
        ArrayList<Operation> storageUI = new ArrayList<>();
        storageUI.add(new ShowAll(storageDB));
        storageUI.add(new AddNew(storageDB));
        storageUI.add(new SetStock(storageDB));
        storageUI.add(new MainView(null));
        return storageUI;
    }
}
