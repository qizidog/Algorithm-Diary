package zuoshencode.stackQueue;

import java.util.LinkedList;

/**
 * @author : qizidog
 * @date : 2021-03-09 00:55
 * @description :
 * 猫狗队列
 **/

public class CatDogQueue {

    private class Wrapper{
        private Pet pet = null;
        private int version = 0;

        public Wrapper(Pet pet, int version) {
            this.pet = pet;
            this.version = version;
        }

        public Pet getPet() {
            return pet;
        }

        public int getVersion() {
            return version;
        }
    }

    private LinkedList<Wrapper> catQueue = new LinkedList<>();
    private LinkedList<Wrapper> dogQueue = new LinkedList<>();
    private int version = 0;

    public void add(Pet pet){
        if (pet instanceof Cat){
            catQueue.offer(new Wrapper(pet, version++));
        } else if (pet instanceof Dog) {
            dogQueue.offer(new Wrapper(pet, version++));
        } else{
            throw new RuntimeException("请传入猫狗对象！");
        }
    }

    public Pet pollAll(){
        if (!isEmpty()){
            Wrapper cpeek = catQueue.peek();
            Wrapper dpeek = dogQueue.peek();
            if (cpeek==null && dpeek==null){
                return null;
            }
            if (cpeek==null){
                return dpeek.getPet();
            }
            if (dpeek==null) {
                return cpeek.getPet();
            }
            return cpeek.getVersion()<dpeek.getVersion()
                    ?pollCat():pollDog();
        }
        return null;
    }

    public Dog pollDog(){
        if (!dogQueue.isEmpty()) {
            return (Dog) dogQueue.poll().getPet();
        }
        return null;
    }

    public Cat pollCat(){
        if (!catQueue.isEmpty()) {
            return (Cat) catQueue.poll().getPet();
        }
        return null;
    }

    public boolean isEmpty(){
        return catQueue.isEmpty() && dogQueue.isEmpty();
    }

    public boolean isDogEmpty(){
        return dogQueue.isEmpty();
    }

    public boolean isCatEmpty() {
        return catQueue.isEmpty();
    }

    public static void main(String[] args) {
        CatDogQueue catDogQueue = new CatDogQueue();
        catDogQueue.add(new Cat("甜五花"));
        catDogQueue.add(new Cat("甜三花"));
        catDogQueue.add(new Dog("小小"));
        catDogQueue.add(new Cat("甜二花"));
        catDogQueue.add(new Dog("猪猪"));
        catDogQueue.add(new Dog("丑丑"));

        System.out.println(catDogQueue.pollCat());  // 甜五花
        System.out.println(catDogQueue.pollDog());  // 小小
        System.out.println(catDogQueue.pollCat());  // 甜三花
        System.out.println(catDogQueue.pollAll());  // 甜二花
        System.out.println(catDogQueue.pollDog());  // 猪猪
        System.out.println(catDogQueue.pollCat());  // null
    }
}

class Pet {
    private String type;

    public Pet() {
    }

    public Pet(String type) {
        this.type = type;
    }
    public String getPetType() {
        return this.type;
    }
}
class Dog extends Pet {
    private String name;
    public Dog() {
        super("dog");
    }
    public Dog(String name) {
        super();
        this.name = name;
    }
    @Override
    public String toString() {
        return "Dog{" + name + '}';
    }
}
class Cat extends Pet {
    private String name;
    public Cat() {
        super("cat");
    }
    public Cat(String name) {
        super();
        this.name = name;
    }
    @Override
    public String toString() {
        return "Cat{" + name + '}';
    }
}
