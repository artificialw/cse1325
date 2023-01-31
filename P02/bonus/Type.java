public enum Type {
    Horse(6), Fish(2), Turtle(52), Wolf(4);
    private double lifespan;

    Type(double lifespan) {
        this.lifespan = lifespan;
    }

    public double lifespan() {
        return lifespan;
    }
}
