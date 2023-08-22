package toysshop;

import toysshop.core.Operation;
import toysshop.core.lottery.Run;
import toysshop.core.lottery.SetPrizes;
import toysshop.core.storage.List;
import toysshop.core.storage.New;
import toysshop.core.storage.SetStock;
import toysshop.ui.LotteryView;
import toysshop.ui.MainView;
import toysshop.ui.StorageView;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Operation mainMenu = new MainView(initUI());
        mainMenu.run();
    }

    private static ArrayList<Operation> initUI() {
        ArrayList<Operation> ui = new ArrayList<>();
        ui.add(new StorageView(initStorage()));
        ui.add(new LotteryView(initLottery()));
        ui.add(new MainView(null));
        return ui;
    }

    private static ArrayList<Operation> initLottery() {
        ArrayList<Operation> lottery = new ArrayList<>();
        lottery.add(new SetPrizes());
        lottery.add(new Run());
        lottery.add(new MainView(null));
        return lottery;
    }

    private static ArrayList<Operation> initStorage(){
        ArrayList<Operation> storage = new ArrayList<>();
        storage.add(new List());
        storage.add(new New());
        storage.add(new SetStock());
        storage.add(new MainView(null));
        return storage;
    }
}
