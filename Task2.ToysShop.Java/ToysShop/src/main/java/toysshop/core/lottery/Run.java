package toysshop.core.lottery;

import toysshop.core.Operation;

public class Run implements Operation {
    private String command = "RUN";
    @Override
    public String toString() {
        return command;
    }
}
