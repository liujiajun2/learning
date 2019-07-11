package xin.liujiajun.pattern.builder;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-21 08:21
 **/
public class PersonBuilder {

    private String name;
    private Integer age;
    private Integer gender;


    public static class Builder{
        private String name;
        private Integer age;
        private Integer gender;

        public Builder(){

        }

        public Builder withName(String name){
            this.name = name;
            return this;
        }
        public Builder withAge(Integer age){
            if (age == null || age < 0  || age > 150) {
                throw new IllegalArgumentException();
            }
            this.age = age;
            return this;
        }

        public Builder withGender(Integer gender){
            this.gender = gender;
            return this;
        }
        public PersonBuilder build(){
            return new PersonBuilder(this);
        }
    }
    public static Builder newBuilder(){
        return new PersonBuilder.Builder();
    }

    private PersonBuilder(Builder builder){
        name = builder.name;
        age = builder.age;
        gender = builder.gender;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getGender() {
        return gender;
    }

}
