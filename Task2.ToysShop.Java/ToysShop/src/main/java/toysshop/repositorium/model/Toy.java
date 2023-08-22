package toysshop.repositorium.model;

public class Toy {

    private Integer id;
    private final String name;
    private Integer count;

    public Toy(String name, Integer count) {
        this.name = name;
        this.count = count;
    }

    public Toy(String name) {
        this(name, null);
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Toy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
