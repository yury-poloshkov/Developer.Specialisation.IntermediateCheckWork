package toysshop.repositorium.model;

public class Toy {

    private Integer id;
    private String name;
    private Integer count;

    public Toy(Integer id, String name, Integer count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public Toy(String name, Integer count) {
        this.name = name;
        this.count = count;
    }

    public Toy() {
    }

    public Toy(String name) {
        this(name, 0);
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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
        return  "id=" + id +
                ", наименование='" + name + '\'' +
                ", количество=" + count;
    }
}
