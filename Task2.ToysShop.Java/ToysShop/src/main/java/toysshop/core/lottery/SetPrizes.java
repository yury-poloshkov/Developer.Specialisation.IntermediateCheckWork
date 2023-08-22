package toysshop.core.lottery;

import toysshop.core.Operation;

public class SetPrizes implements Operation {
    private String command = "SET PRIZES";
    @Override
    public String toString() {
        return command;
    }
}
