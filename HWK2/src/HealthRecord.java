/* Name: Carlos A. Valdez : 101355229
   Description: HWK#2, consisting of 3 parts --- This is part3 ---> Digital Health Records
   Compiled on IntelliJ and using AutoGrader to test code submission
   Due: September 10th, 2021
   Resources used:

 */
import java.util.Calendar;
import java.util.Scanner;

// Create a class called HealthRecord --- Complete
public class HealthRecord {
    //Define private attributes for the class, first name, last name, and gender M or F
    private String firstName;
    private String lastName;
    private char gender;   // M or F

    //Define private attributes Dob (separating the month, day, and year), height (inches), weight (lbs)
    private int monthDoB;
    private int dayDoB;
    private int yearDoB;
    private int height; // inches
    private int weight; // lbs

    //Add a constructor that receives the data to properly initialize the classes' attributes
    public HealthRecord(String firstName, String lastName, char gender,
                        int monthDoB, int dayDoB, int yearDoB, int height, int weight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.monthDoB = monthDoB;
        this.dayDoB = dayDoB;
        this.yearDoB = yearDoB;
        this.height = height;
        this.weight = weight;
    }
    //Provide set and get methods.  Except for DoB, which you will provide:
    //separate getters for the DoB's but a single setter for the DoB's called setDOB
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setGender(char gender){
        this.gender = gender;
    }
    public void setDOB(int dayDoB, int monthDoB, int yearDoB){
        this.dayDoB = dayDoB;
        this.monthDoB = monthDoB;
        this.yearDoB = yearDoB;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public void setWeight(int weight){
        this.weight = weight;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public char getGender(){
        return gender;
    }
    public int getMonthDoB(){
        return monthDoB;
    }
    public int getDayDoB(){
        return dayDoB;
    }
    public int getYearDoB(){
        return yearDoB;
    }
    public int getHeight(){
        return height;
    }
    public int getWeight(){
        return weight;
    }
    // in addition provide other methods
    // getAge(), returns int of the person's age, considering ONLY YEAR (yearDoB) for this computation
        //you can for this this: import java.util.Calendar;
        //get current year: int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    public int getAge(){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - this.yearDoB;
    }
    // getMaximumHeartRate(), returns int corresponding to 220 - age (age obtained from getAge).
    public int getMaximumHeartRate(){
        return 220 - this.getAge();
    }
    // getTargetHeartRate(), returns String representing the target heart rate of the person
        //the output format should be "%d - %d", representing the min HR and the max HR recommended while exercising
        //The Minimum Target HR is 50% of the Maximum Heart Rate
        //The Maximum Target HR is 85% of the Maximum Heart Rate
    public String getTargetHeartRate(){
        return String.format("%d - %d", (int)(this.getMaximumHeartRate() * .50),
                                        (int)(this.getMaximumHeartRate() * .85));
    }
    // getBMI()
        //returns a decimal number representing the Body Mass Index
        //compute it as described on page 67
    public double getBMI(){
        //formula on page 67 is --- BMI = (weightInPounds * 703) / (heightInInches * heightInInches)
        return ((this.weight * 703) / (this.height * this.height));
    }
    // toString()
        //returns String, representing the obj
        //special method for printing, should return string to represent the current obj
        //string should be formatted:
            // Name: {lastName}, {firstName} Gender:{gender} DOB:{month}/{day}/{year}
            // AGE:{age} H:{height}in. W:{weight}lb., MHR:{maximumHeartRate},
            // THR:{targetHeartRate}, BMI:{BMI} --- Complete
            //{xxx} represent data from the object
            //date parts should have leading zeros and be format MM/DD/YYYY, i.e. (this would mean month and day to 2 precision)
            // April 1st, 2020 should be shown as 04/01/2020 --- Complete
            //Height,Weight,and MHR should be aligned to 3 digits --- Complete
            //Decimal numbers should have 2 decimal places and no thousandth delimiters
    public String toString(){
        return
                String.format("Name:%s, %s. Gender:%c, DOB:%02d/%02d/%d" +
                        " AGE: %d, H: %3din., W: %3dlbs., MHR:%3d, THR:%s, BMI:%.2f",
                        this.lastName, this.firstName, this.gender, this.monthDoB, this.dayDoB, this.yearDoB,
                        this.getAge(), this.height, this.weight, this.getMaximumHeartRate(), this.getTargetHeartRate(),
                        this.getBMI());
    }
    public static void main(String[] args) {
        //create an object of HealthRecord and create a new with the following on canvas
        HealthRecord hr;
        hr = new HealthRecord("John", "Doe", 'M', 1, 3, 1990, 70, 180);
        System.out.println(hr);
        hr = new HealthRecord("Jane","Doe",'F', 20, 5, 1995, 60, 150);
        System.out.println(hr);
    }
}
