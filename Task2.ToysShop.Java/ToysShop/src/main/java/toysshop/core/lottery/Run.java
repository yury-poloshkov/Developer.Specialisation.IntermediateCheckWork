package toysshop.core.lottery;

import com.google.gson.Gson;
import toysshop.core.Operation;
import toysshop.repositorium.DBConnector;
import toysshop.repositorium.model.Lottery;
import toysshop.repositorium.model.Toy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Run implements Operation {
    private final String command = "RUN";
    private final DBConnector lotteryDB;
    private final DBConnector storageDB;

    public Run(DBConnector lotteryDB, DBConnector storageDB) {
        this.lotteryDB = lotteryDB;
        this.storageDB = storageDB;
    }

    public void run() {
        System.out.println("\033[H\033[2J");
        System.out.println("----- ПРОВЕДЕНИЕ РОЗЫГРЫША -----");
        List<String> lotteryLog = lotteryDB.readAll();
        List<String> storage = storageDB.readAll();
        Gson g = new Gson();
        try{
            if (!lotteryLog.isEmpty() && g.fromJson(lotteryLog.get(lotteryLog.size()-1), Lottery.class).getName().equals("PrizeFund")) {
                Lottery lottery = g.fromJson(lotteryLog.get(lotteryLog.size() - 1), Lottery.class);
                ArrayList<Toy> prize = new ArrayList<>();
                int totalPrizes = 0;
                int lotteryRuns = 0;
                if (!lottery.getPrizeFund().isEmpty()) {
                    for (Toy item : lottery.getPrizeFund()) {
                        totalPrizes += item.getCount();
                    }
                }
                if (totalPrizes != 0) {
                    while (lotteryRuns <= 0 || lotteryRuns > totalPrizes) {
                        try {
                            lotteryRuns = Integer.parseInt(prompt("Введите количество розыгрышей (max = " + totalPrizes + "): "));
                        } catch (Exception e) {
                            System.out.println("\tERROR: invalid data... try again");
                        }
                    }
                }else{
                    System.out.println("ERROR: PrizesFund is empty... set it by SET PRIZES option");
                }

                if (lotteryRuns != 0) {
                    System.out.println("----- РЕЗУЛЬТАТЫ РОЗЫГРЫША -----");
                    Lottery lotteryPrize = new Lottery();
                    Random rnd = new Random();
                    int current;
                    Toy currentToy;
                    for (int i = 0; i < lotteryRuns; i++) {
                        current = rnd.nextInt(totalPrizes - i) + 1;
                        for (Toy item : lottery.getPrizeFund()) {
                            if (current > 0 && current <= item.getCount()) {
                                currentToy = g.fromJson(storage.get(item.getId() - 1), Toy.class);
                                currentToy.setCount(currentToy.getCount() - 1);
                                storage.set(item.getId() - 1, g.toJson(currentToy));
                                lotteryPrize.setName("Prize №" + (i + 1));
                                currentToy.setCount(1);
                                prize.add(currentToy);
                                lotteryPrize.setPrizeFund(prize);
                                lotteryLog.add(g.toJson(lotteryPrize));
                                System.out.println(lotteryPrize);
                                prize.clear();
                                current -= item.getCount();
                                item.setCount(item.getCount() - 1);
                            }else {
                                current -= item.getCount();
                            }
                        }
                    }
                    storageDB.saveAll(storage);
                    lotteryLog.add(g.toJson(lottery));
                    lotteryDB.saveAll(lotteryLog);
                }
            }
        }catch (Exception e){
            System.out.println("\tERROR: PrizeFund is not set... Use SET PRIZES option");
            timeOut();
        }
    }

    @Override
    public String toString() {
        return command;
    }
}
