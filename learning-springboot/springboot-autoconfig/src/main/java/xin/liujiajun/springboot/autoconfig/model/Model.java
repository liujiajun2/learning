package xin.liujiajun.springboot.autoconfig.model;

/**
 * @author liujiajun
 * @create 2019-07-03 19:42
 **/
public class Model {

    private Integer id;
    private String name;

    public Model() {
    }

    public Model(Integer id) {
        this.id = id;
    }

    public Model(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
