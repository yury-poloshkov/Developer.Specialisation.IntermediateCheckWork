package toysshop.repositorium;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnector {
    private final String path;

    public DBConnector(String path) {
        this.path = path;
        connectDB();
    }
    public void connectDB() {
        try {
            File db = new File(path);
            if (db.createNewFile()) {
                System.out.println("New DB created");
            }
            else {
                System.out.println("DB already exists");
            }
        }
        catch (Exception e) {
            //noinspection ThrowablePrintedToSystemOut
            System.err.println(e);
        }
    }

    public void add(List<String> lines) {
        try (FileWriter writer = new FileWriter(path, true)) {
            for (String line : lines) {
                writer.write(line + '\n');
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void saveAll(List<String> lines) {
        try (FileWriter writer = new FileWriter(path, false)) {
            for (String line : lines) {
                writer.write(line + '\n');
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<String> readAll() {
        List<String> lines = new ArrayList<>();
        try {
            FileReader fr = new FileReader(path);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            if (line != null) {
                lines.add(line);
            }
            while (line != null) {
                line = reader.readLine();
                if (line != null) {
                    lines.add(line);
                }
            }
            fr.close();
        } catch (IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        return lines;
    }
    public int newIndex(){
        return readAll().size()+1;
    }

    public boolean isExist(String name){
        List<String> storage = readAll();
        for (String line: storage) {
            if (line.contains(name)) return true;
        }
        return false;
    }
}
