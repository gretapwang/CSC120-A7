/**
 * Subclass of Building which sells coffee and has an inventory of ingredients
 * @author Greta Wang
 * @version 11/5/2025
 */
public class Cafe extends Building implements CafeRequirements {
    private int nCoffeeOunces; // The number of ounces of coffee remaining in inventory
    private int nSugarPackets; // The number of sugar packets remaining in inventory
    private int nCreams; // The number of "splashes" of cream remaining in inventory
    private int nCups; // The number of cups remaining in inventory

    /**
     * Constructor for Cafe
     * @param name Cafe's name
     * @param address Cafe's address
     * @param nFloors Number of floors
     * @param nCoffeeOunces Starting number of ounces of coffee
     * @param nSugarPackets Starting number of sugar packets
     * @param nCreams Starting number of splashes of cream
     * @param nCups Starting number of cups
     */
    public Cafe(String name, String address, int nFloors, int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups) {
        super(name, address, nFloors);
        if (nCoffeeOunces < 0 || nSugarPackets < 0 || nCreams < 0 || nCups < 0){
            throw new RuntimeException("Cannot initialize cafe with negative inventory.");
        }
        this.nCoffeeOunces = nCoffeeOunces;
        this.nSugarPackets = nSugarPackets;
        this.nCreams = nCreams;
        this.nCups = nCups;
        System.out.println("You have built a cafe: ☕");
    }

    /**
     * Decreases inventory by given amounts, restocking as needed
     * @param size Number of coffee ounces sold
     * @param nSugarPackets Number of sugar packets sold
     * @param nCreams Number of cream splashes sold
     */
    public void sellCoffee(int size, int nSugarPackets, int nCreams){
        if (size < 0 || nSugarPackets < 0 || nCreams < 0){
            throw new RuntimeException("Cannot sell drink with negative ingredient values.");
        }
        int coffeeRestock = 0;
        int sugarRestock = 0;
        int creamRestock = 0;
        int cupRestock = 0;
        if (this.nCoffeeOunces <= size){
            coffeeRestock = size + 100;
        }
        if (this.nSugarPackets <= nSugarPackets){
            sugarRestock = nSugarPackets + 50;
        }
        if (this.nCreams <= nCreams){
            creamRestock = nCreams + 50;
        }
        if (this.nCups <= 1){
            cupRestock = 30;
        }
        this.restock(coffeeRestock, sugarRestock, creamRestock, cupRestock);
        this.nCoffeeOunces -= size;
        this.nSugarPackets -= nSugarPackets;
        this.nCreams -= nCreams;
        this.nCups -= 1;
    }

    /**
     * Increases inventory by the given amounts
     * @param nCoffeeOunces Number of coffee ounces stocked
     * @param nSugarPackets Number of sugar packets stocked
     * @param nCreams Number of cream splashes stocked
     * @param nCups Number of cups stocked
     */
    private void restock(int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups){
        if (nCoffeeOunces < 0 || nSugarPackets < 0 || nCreams < 0 || nCups < 0){
            throw new RuntimeException("Cannot restock negative values.");
        }
        this.nCoffeeOunces += nCoffeeOunces;
        this.nSugarPackets += nSugarPackets;
        this.nCreams += nCreams;
        this.nCups += nCups;
    }
    
    /**
     * Tests Cafe class
     * @param args The command line arguments (ignored)
     */
    public static void main(String[] args) {
        Cafe compass = new Cafe("Compass Cafe", "7 Neilson Dr", 1, 10, 10, 10, 10);
        System.out.println(compass);
        System.out.println("Inventory: " + compass.nCoffeeOunces + " coffees, " + compass.nSugarPackets + " sugars, " + compass.nCreams + " creams, " + compass.nCups + " cups.");
        compass.sellCoffee(1, 1, 1);
        System.out.println("Inventory: " + compass.nCoffeeOunces + " coffees, " + compass.nSugarPackets + " sugars, " + compass.nCreams + " creams, " + compass.nCups + " cups.");
        compass.sellCoffee(9, 10, 2);
        System.out.println("Inventory: " + compass.nCoffeeOunces + " coffees, " + compass.nSugarPackets + " sugars, " + compass.nCreams + " creams, " + compass.nCups + " cups.");
    }
    
}
