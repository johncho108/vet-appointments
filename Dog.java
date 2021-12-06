public class Dog extends Pet {
    private double droolRate;
    private final double DEFAULT_DROOLRATE = 5;

    public Dog(String name, double health, int painLevel, double droolRate) {
        super(name, health, painLevel);
        if(droolRate <= 0) {
            this.droolRate = 0.5;
        } else {
            this.droolRate = droolRate;
        }
    }

    public Dog(String name, double health, int painLevel) {
        super(name, health, painLevel);
        this.droolRate = DEFAULT_DROOLRATE;
    }

    public double getDroolRate() {
        return this.droolRate;
    }

    public int treat() {
        double timeTaken;
        int timeTakenInt;
        if(this.droolRate < 3.5) {
            timeTaken = (this.getPainLevel()*2)/this.getHealth();
            timeTakenInt = (int) Math.ceil(timeTaken);
        } else if (this.droolRate >= 3.5 && this.droolRate <= 7.5) {
            timeTaken = this.getPainLevel()/this.getHealth();
            timeTakenInt = (int) Math.ceil(timeTaken);
        } else {
            timeTaken = this.getPainLevel()/(this.getHealth()*2);
            timeTakenInt = (int) Math.ceil(timeTaken);
        }
        super.heal();
        return timeTakenInt;
    }

    public void speak() {
        super.speak();
        String output;
        if (this.getPainLevel() > 5) {
            output = "BARK ";
        } else {
            output = "bark ";
        }
        for (int i = 0; i < this.getPainLevel(); i++) {
            System.out.print(output);
        }
        System.out.println("");
    }

    public boolean equals(Object o) {
        if (o instanceof Dog) {
            Dog d = (Dog) o;
            return this.getName().equals(d.getName()) && this.droolRate == d.droolRate;
        }
        return false;
    }
}
