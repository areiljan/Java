package ee.taltech.iti0202.lotr;

public class Ring {
    private Type type;
    private Material material;

    /**
     * This method constructs the ring object.
     *
     * @param type The first object.
     * @param material The second object.
     */
    public Ring(Type type, Material material) {
        this.type = type;
        this.material = material;
    }

    /**
     * This method returns the type of the given ring.
     *
     * @return type.
     */
    public Type getType() {
        return type;
    }

    /**
     * This method returns the material of the given ring.
     *
     * @return material.
     */
    public Material getMaterial() {
        return material;
    }
    public enum Type {
        THE_ONE, GOLDEN, NENYA, OTHER
    }
    public enum Material {
        GOLD, SILVER, BRONZE, PLASTIC, DIAMOND
    }

}
