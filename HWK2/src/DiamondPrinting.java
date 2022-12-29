/* Name: Carlos A. Valdez : 101355229
   Description: HWK#2, consisting of 3 parts --- This is part2 ---> Diamond Printing Program
   Compiled on IntelliJ and using AutoGrader to test code submission
   Due: September 10th, 2021

   Resources used:
   -https://www.geeksforgeeks.org/c-program-print-diamond-shape/
            to understand the logic behind how to divide the halves of the pyramid
            --this one is different however, in the middle section of the pyramid being duplicated
            ---I had to split mine into a top middle and bottom portion

 */
import java.util.Scanner;
// Part 2 of HWK2 --- Diamond Printing
// Create a new java Class called DiamondPrinting  ---  Complete
public class DiamondPrinting {

    /* Define a public method called getCharSequence, that takes two args: A String, and an int
           Returns a string repeating the string arg as many times as the int argument
           Examples getCharSequence("a", 5) will return "aaaaa"
     */
    public String getCharSequence (String theChar, int theInt) {
        //Need a string variable to simply concatenate the char to
        String theSequence = "";
        //Now we need to simply add that char * the number of times passed to the string
        for (int i = 0; i < theInt; i++) {
            theSequence += theChar;
        }
        return theSequence;
    }

    /* Define a public method called getDiamond that
        1) Takes an int representing how many lines should the diamond use.  In the example above,
        it would correspond to 5.
            1) The number of lines must be an odd number, between 1 and 99.
        2) It returns a String, representing the diamond
            1) If the input is invalid (3.1.1), just return an empty string
        3) You will need to use the getCharSequence method defined in (2)

     */
    public String getDiamond(int numOfLines){
        // numOfLines must be odd, so must test case against even numbers --- Complete
        // numOfLines must be within bound of 1 - 99                      --- Complete
        // we need to be able to draw the portions of the diamond         --- Complete
            // can do this using spaces/stars/the string itself
        String theString = "";      // string to make pyramid
        String spaces = "";         // string for spaces in a pyramid
        String stars = "";          // string for stars (*) in a pyramid
        if (numOfLines % 2 == 0){
            System.out.println("Invalid input, the input must be an odd number!");
            return "Thank you";
            }   // Could have combined them in one if, but wanted the separation
                // so user could identify the issue they made
        if (numOfLines < 1 || numOfLines > 99){
            System.out.println("Invalid input, the input must be within' 1-99");
            return "Thank you";
            }
        int theSpaces = (int) numOfLines / 2;       //num of spaces in open space of pyramids
        for (int i = 1; i <= numOfLines; i += 2){
            spaces = getCharSequence(" ", theSpaces--);    //getting spaces
            stars = getCharSequence("*", i);               //getting stars
            theString += String.format("%S%S\n", spaces, stars);  //must combine both space and stars (can format the string)
        }
        theSpaces = 1;                              // we need to print the bottom portion of the diamond as well
        for (int i = numOfLines - 2; i >= 1; i -= 2){
            spaces = getCharSequence(" ", theSpaces++);    //getting spaces
            stars = getCharSequence("*", i);               //getting stars
            theString += String.format("%S%S\n", spaces, stars);   //must combine both space and stars
        }
        return theString;               // actually prints the diamond
    }

    public static void main (String[] args) {
    // in main we need input from user for how many lines they would like to design the pyramid
        System.out.print("Please enter the number of lines for pyramid, between 1-99 and odd only please: ");
        Scanner input = new Scanner(System.in);  // must create Scanner object
        int userLineInput = input.nextInt();     // stored in userLineInput

        DiamondPrinting diamondPrint = new DiamondPrinting();   // creating DiamondPrinting object
        System.out.print(diamondPrint.getDiamond(userLineInput));
    }
}

