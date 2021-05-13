package hackerrank;

import java.util.HashMap;

public class FurnitureOrder implements FurnitureOrderInterface {
    /**
     * TODO: Create a map of Furniture items to order quantities
     */
	HashMap<Furniture, Integer> furnitureItems = new HashMap();

    /**
     * Initialize a new mapping of Furniture types to order quantities.
     */
    FurnitureOrder() {
        // TODO: Complete the constructor
    }

    public void addToOrder(final Furniture type, final int furnitureCount) {
        // TODO: Complete the method
    	furnitureItems.put(type, furnitureCount);
    }

    public HashMap<Furniture, Integer> getOrderedFurniture() {
        // TODO: Complete the method
        return furnitureItems;
    }

    public float getTotalOrderCost() {
        // TODO: Complete the method
        return furnitureItems.values().stream().reduce(0, Integer::sum);
    }

    public int getTypeCount(Furniture type) {
        // TODO: Complete the method
        return furnitureItems.size();
    }

    public float getTypeCost(Furniture type) {
        // TODO: Complete the method
    	return type.cost();
      //  return -1.0f;
    }

    public int getTotalOrderQuantity() {
        // TODO: Complete the method
        return -1;
    }
}