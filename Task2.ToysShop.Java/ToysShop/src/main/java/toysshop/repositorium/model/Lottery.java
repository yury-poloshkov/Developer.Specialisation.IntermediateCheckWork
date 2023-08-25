package toysshop.repositorium.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Lottery {

    private String name;

    private ArrayList<Toy> prizeFund;

    public String getName() {
        return name;
    }

    public ArrayList<Toy> getPrizeFund() {
        return prizeFund;
    }

    public Lottery(String name, ArrayList<Toy> prizeFund) {
        this.name = name;
        this.prizeFund = prizeFund;
    }
}
