public class Cat extends Pet {
    private int miceCaught;
    private final int DEFAULT_MICECAUGHT = 0;

    public Cat(String name, double health, int painLevel, int miceCaught) {
        super(name, health, painLevel);
        if(miceCaught < 0) {
            this.miceCaught = 0;
        } else {
            this.miceCaught = miceCaught;
        }
    }

    public Cat(String name, double health, int painLevel) {
        super(name, health, painLevel);
        this.miceCaught = DEFAULT_MICECAUGHT;
    }

    public int getMiceCaught() {
        return this.miceCaught;
    }

    public int treat() {
        double timeTaken;
        int timeTakenInt;
        if(this.miceCaught < 4) {
            timeTaken = (this.getPainLevel()*2)/this.getHealth();
            timeTakenInt = (int) Math.ceil(timeTaken);
        } else if (this.miceCaught >= 4 && this.miceCaught <= 7) {
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
            output = "MEOW ";
        } else {
            output = "meow ";
        }
        for(int i = 0; i < this.miceCaught; i++) {
            System.out.print(output);
        }
        System.out.println("");
    }

    public boolean equals(Object o) {
        if (o instanceof Cat) {
            Cat c = (Cat) o;
            return this.getName().equals(c.getName()) && this.miceCaught == c.miceCaught;
        }
        return false;
    }
}
