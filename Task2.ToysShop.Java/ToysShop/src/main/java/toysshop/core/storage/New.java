package toysshop.core.storage;

import toysshop.core.Operation;

public class New implements Operation {
    private String command = "NEW";
    @Override
    public String toString() {
        return command;
    }
}
