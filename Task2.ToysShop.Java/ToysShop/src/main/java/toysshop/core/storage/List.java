package toysshop.core.storage;

import toysshop.core.Operation;

public class List implements Operation {
    private String command = "LIST";
    @Override
    public String toString() {
        return command;
    }
}
