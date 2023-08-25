package toysshop.core.lottery;

import com.google.gson.Gson;
import toysshop.core.Operation;
import toysshop.repositorium.DBConnector;
import toysshop.repositorium.model.Lottery;
import toysshop.repositorium.model.Toy;

import java.util.ArrayList;
import java.util.List;

public class SetPrizes implements Operation {
    private final String command = "SET PRIZES";

    private final DBConnector lotteryDB;
    private final DBConnector storageDB;

    public SetPrizes(DBConnector lotteryDB, DBConnector storageDB) {
        this.lotteryDB = lotteryDB;
        this.storageDB = storageDB;
    }
    public void run(){
        System.out.println("----- ФОРМИРОВАНИЕ ПРИЗОВОГО ФОНДА -----");
        List<String> currentStock = storageDB.readAll();
        if (!currentStock.isEmpty()){
            Gson g = new Gson();
            if (prompt("Отобразить складские остатки (y/n): ").equalsIgnoreCase("y")){
                System.out.println("----- СКЛАДСКОЙ ЗАПАС -----");
                for (String item:currentStock) System.out.println(g.fromJson(item, Toy.class));
                System.out.println("-----------------------------");
            }
            ArrayList<Toy> prizeFund = new ArrayList<>();
            String[] prizeToy;
            Toy choosenToy;
            String choosingToy = prompt("Введите через запятую id и количество игрушек, \n" +
                    "передаваемых в призовой фонд (exit для завершения): ").toLowerCase();
            while (!choosingToy.equals("exit")){
                try {
                    prizeToy = choosingToy.split(",\s+");
                    choosenToy = g.fromJson(currentStock.get(Integer.parseInt(prizeToy[0])-1), Toy.class);
                    int count = Integer.parseInt(prizeToy[1]);
                    if ((count < choosenToy.getCount()) && count > 0){
                        if (!isFunfed(choosenToy, prizeFund)){
                            choosenToy.setCount(count);
                            prizeFund.add(choosenToy);
                            System.out.printf("\tToy = %s count = %d added to PrizeFund\n\n", choosenToy.getName(),choosenToy.getCount());
                        }else{
                            System.out.printf("\tERROR: %s already present in PrizeFund\n\n", choosenToy.getName());
                        }
                    }else {
                        System.out.println("\tERROR: unsufficient stock\n");
                    }
                }catch (Exception e){
                    System.out.println("\tERROR: invalid data... try again\n");
                }
                choosingToy = prompt("Введите через запятую id и количество игрушек, \n" +
                        "передаваемых в призовой фонд (exit для завершения): ").toLowerCase();
            }
            if (!prizeFund.isEmpty()){
                Lottery lottery = new Lottery("PrizeFund", prizeFund);
                lotteryDB.add(List.of(g.toJson(lottery)));
            }
        }else{
            System.out.println("\tERROR: no toys at storage");
        }
    }

    private boolean isFunfed(Toy choosenToy, ArrayList<Toy> prizeFund) {
        for (Toy toy:prizeFund){
            if (toy.getId() == choosenToy.getId()) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return command;
    }
}
