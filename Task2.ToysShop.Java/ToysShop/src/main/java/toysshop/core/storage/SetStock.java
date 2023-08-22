package toysshop.core.storage;

import toysshop.core.Operation;

public class SetStock implements Operation {
    private String command = "SET STOCK";
    @Override
    public String toString() {
        return command;
    }

}
