package toysshop.repositorium.model;

import java.util.ArrayList;

public class Lottery {

    private String name;

    private ArrayList<Toy> prizeFund;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrizeFund(ArrayList<Toy> prizeFund) {
        this.prizeFund = prizeFund;
    }

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

    public Lottery() {
    }

    @Override
    public String toString() {
        return "Lottery{" +
                "name='" + name + '\'' +
                ", prizeFund=" + prizeFund +
                '}';
    }
}
