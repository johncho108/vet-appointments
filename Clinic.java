import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Clinic {
    private File patientFile;
    private static int day = 0;

    Clinic(File file) {
        this.patientFile = file;
        if (Clinic.day == 0) {
            Clinic.day = 1;
        }
    }

    Clinic (String fileName) {
        this(new File(fileName));  
    }

    public String nextDay(String fileName) throws Exception {
        File f = new File(fileName);
        return nextDay(f);
    }

    public String nextDay(File f) throws Exception {
        Scanner fileScan = null;
        Scanner input = new Scanner(System.in);
        double health;
        int painLevel;
        String name;
        String typeOfPet;
        String characteristic;
        String appointmentTime; 
        String appointmentString;
        String exitTime;
        int timeTaken = 0;

        fileScan = new Scanner(f);
        String line = null;
        String[] vals = null;
        String appointmentsText = "";
            
        while (fileScan.hasNextLine()) {
            line = fileScan.nextLine();
            vals = line.split(",");
            name = vals[0];
            typeOfPet = vals[1];
            characteristic = vals[2];
            appointmentTime = vals[3];

            if (!typeOfPet.equals("Dog") && !typeOfPet.equals("Cat")) {
                throw new InvalidPetException();
            }

            System.out.println("Consultation for " + name + " the " + typeOfPet
                + " at " + appointmentTime + ".\nWhat is the health of " + name + "?\n");

            while (true) {
                try {
                    health = input.nextDouble();
                    break;
                }
                catch (Exception e) {
                    System.out.println("Consultation for " + name + " the " + typeOfPet 
                        + " at " + appointmentTime + ".\nWhat is the health of " + name + "?\n");
                }
            }

            while (true) {
                System.out.println("On a scale of 1 to 10, how much pain is " + name + " in right now?");
                try {
                    painLevel = input.nextInt();
                    break;
                }
                catch (Exception e) {
                    continue;
                }
            }

            if (typeOfPet.equals("Dog")) {
                double droolLevel = Double.parseDouble(characteristic);
                Dog d = new Dog(name, health, painLevel, droolLevel);
                d.speak();
                timeTaken = d.treat();
            } else if (typeOfPet.equals("Cat")) {
                int miceCaught = Integer.parseInt(characteristic);
                Cat c = new Cat(name, health, painLevel, miceCaught);
                c.speak();
                timeTaken = c.treat();
            }

            exitTime = Clinic.addTime(appointmentTime, timeTaken);
            appointmentString = name + "," + typeOfPet + "," + characteristic + "," + Clinic.day + "," + appointmentTime + "," + exitTime + "," + health + "," + painLevel;
            appointmentsText += appointmentString + "\n";
        }
        Clinic.day += 1;
        fileScan.close();
        return appointmentsText;
    }
    
    public boolean addToFile(String patientInfo) throws FileNotFoundException {
        Scanner fileScan = null;
        PrintWriter filePrint = null;
        fileScan = new Scanner(this.patientFile);
        String line = null;
        String[] patientFileVals = null;
        String[] patientInfoVals = patientInfo.split(",");
        String patientInfoName = patientInfoVals[0];
        boolean isNewPatient = true;
        String appointmentInfo = "";

        while (fileScan.hasNextLine()) {
            line = fileScan.nextLine();
            patientFileVals = line.split(",");
            String patientFileName = patientFileVals[0];
            if (patientFileName == patientInfoName) {
                isNewPatient = false;
            }
        }

        try {
            filePrint = new PrintWriter(this.patientFile);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
        filePrint.println(patientInfo);
        filePrint.close();
        fileScan.close();
        return true;
    }
    
    private static String addTime(String timeIn, int treatmentTime) {
        String hours;
        String minutes;
        double hoursDouble;
        double minutesDouble;
        double timeInDouble;
        double timeOutDouble;
        int timeOutHoursInt;
        double timeOutMinutesDouble;
        int timeOutMinutesInt;
        String timeOut;

        if (timeIn.length() == 4) {
            hours = timeIn.substring(0, 2);
            minutes = timeIn.substring(2, 4);
        } else {
            hours = timeIn.substring(0, 1);
            minutes = timeIn.substring(1, 3);
        }
        hoursDouble = Double.parseDouble(hours);
        minutesDouble = Double.parseDouble(minutes);
        timeInDouble = hoursDouble + minutesDouble/60;
        timeOutDouble = timeInDouble + ((double)treatmentTime)/60;
        timeOutHoursInt = (int) timeOutDouble;
        timeOutMinutesDouble = (timeOutDouble - timeOutHoursInt)*60;
        timeOutMinutesInt = (int) Math.round(timeOutMinutesDouble);
        timeOut = Integer.toString(timeOutHoursInt) + Integer.toString(timeOutMinutesInt);
        return timeOut;
    }
}
