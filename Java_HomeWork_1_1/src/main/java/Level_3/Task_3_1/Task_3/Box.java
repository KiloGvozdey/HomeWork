package Level_3.Task_3_1.Task_3;

import java.util.ArrayList;

public class Box <T extends Fruit>{
    private final ArrayList<T> list;
    public Box() {
        this.list = new ArrayList<>();
    }

    public ArrayList<T> getList() {
        return list;
    }

    public float getWeight(){
        float weight = 0.0F;
        for (T t : list) {
            weight += t.getWeight();
        }
        return weight;
    }
    public void addToBox(T t){
        list.add(t);
    }

    public boolean compare(Box box){
        return getWeight() == box.getWeight();
    }

    public void migrateFruit(Box<T> box){
        box.getList().addAll(this.list);
        this.list.clear();
    }


}
