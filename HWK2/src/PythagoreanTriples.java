/* Name: Carlos A. Valdez : 101355229
   Description: HWK#2, consisting of 3 parts --- This is part1 -- Pythagorean Triples
   Compiled on IntelliJ and using AutoGrader to test code submission
   Due: September 10th, 2021

   Resources used:
   -https://mathworld.wolfram.com/PythagoreanTriple.html#:~:text=%2C%20are%20(3%2C%204%2C,%2C%20A046084%2C%20and%20A009000).
            I used this website to determine the first couple pythagorean triples to know what to input for
            our min and max values during testing phase
   -Our textbook for style/basic fundamentals and understanding of classes, etc.
   -https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#format-java.util.Locale-java.lang.String-java.lang.Object...-
            For string documentation provided by instructor
 */

// Part 1 of HWK2 -- PythagoreanTriples
// Create a new java Class called PythagoreanTriples         ---Complete
public class PythagoreanTriples {   // class PythagoreanTriples
    /* Define a method isTriple that receives 3 int numbers  ---Complete
     * return either true or false if a^2 + b^2 = c^2        ---Complete
     */
    public static boolean isTriple (int a, int b, int c) {
        if (a * a + b * b == c * c)
            return true;
        else {
            return false;
        }
    }

    /* Define a method listOfTriples that
     * takes six ints aMin, bMin, cMin, aMax, bMax, and cMax            ---- Complete
     * Returns a String that lists all the Pythagorean Triples between  ---- Complete
     * [aMin, aMax], [bMin, bMax], [cMin, cMax]                         ---- Complete   */
    public String listOfTriples (int aMin, int bMin, int cMin, int aMax, int bMax, int cMax) {
        //need to create a string to contain the triples ranging from these parameters
        //then do for loops to analyze the ranges provided and test them into our isTriple method
        String theTriples = "";
        for (int i = aMin; i < aMax; i++) {
            for (int j = bMin; j < bMax; j++) {
                for (int k = cMin; k < cMax; k++) {
                    if (isTriple(i, j, k)) {
                        theTriples += i + " " + j + " " + k + " ";
                    }
                }
            }
        }
        return theTriples;
    }

    // Add a main method, create an object of PythagoreanTriple and try the listOfTriples method. --- Complete
    public static void main (String[] args) { // main
        int aMin = 3, bMin = 4, cMin = 5;     // setting min value of lowest possible pythagorean triple
        int aMax = 21, bMax = 28, cMax = 35;  // setting max values, these can go higher if need
        PythagoreanTriples MyPTripObj = new PythagoreanTriples();   // creating the PythagoreanTriple object named MyPTripObj

        String output = MyPTripObj.listOfTriples(aMin, bMin, cMin, aMax, bMax, cMax);
        String[] outputArray = output.split(" ");
        for (int i = 0; i < outputArray.length; i += 3) {
            System.out.println(outputArray[i] + " " + outputArray[i + 1] + " " + outputArray[i + 2]);
        }
    }
}
