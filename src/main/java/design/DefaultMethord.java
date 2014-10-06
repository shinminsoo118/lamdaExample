package design;

import lombok.extern.slf4j.Slf4j;

/**
 * default methord의 상속관계를 확인해 봄
 */
@Slf4j
public class DefaultMethord {

    public interface Fly {
        default void takeOff() { System.out.println("Fly::takeOff"); }
        default void land() { System.out.println("Fly::land"); }
        default void turn() { System.out.println("Fly::turn"); }
        default void cruise() { System.out.println("Fly::cruise"); }
    }

    public interface FaskFly extends Fly {
        default void takeOff() { System.out.println("FastFly::takeOff"); }
        default void cruise() { System.out.println("FastFly::cruise"); }
    }

    public class Vehicle {
        public void turn() { System.out.println("Vehicle::turn"); }
    }

    public class SeaPlane extends Vehicle implements FaskFly {
        public void cruise() {
            FaskFly.super.cruise();
        }

        public void land() {
            FaskFly.super.land();
        }
    }

    public void useClasses() {
        SeaPlane plane = new SeaPlane();
        plane.takeOff();
        plane.land();
        plane.turn();
        plane.cruise();
    }

    public static void main(String[] args) {
        DefaultMethord methord = new DefaultMethord();
        methord.useClasses();
    }

}
