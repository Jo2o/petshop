package sk.gw.jo2o.petshop.model;

public enum AnimalCategory {

    DOGS(1, "dogs"),
    CATS(2, "cats"),
    OTHER(3, "other");

    private final int num;
    private final String name;

    AnimalCategory(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

}
