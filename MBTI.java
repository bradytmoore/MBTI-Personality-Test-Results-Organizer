import java.io.*;
import java.util.Scanner;
/*
Brady Moore

2/9/2022

CSCI 211
 */

public class MBTI {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner inFile = new Scanner(new File("src/main/java/MBTI_Results.csv"));
        String[] oneline;
        /*
        I ran into multiple errors with the array being size 71 so I had to change it to 72
         */
        String[][] students = new String[Integer.parseInt(inFile.nextLine())][72];

        int count = 0;
        String myType = "INTP";


        //TODO: Step through MTBI results file and add each student's personality type to element 71 (i.e., element 71 for each row)
        //Be sure to check for your last name.  If it is you, assign your personality type to the variable myType

        /*
        1. Populating the students array
        2. Creating a "regex" for each answer to be inserted into individual elements

         */

        while (inFile.hasNextLine()) {
            for (int row = 0; row < students.length; row++) {

                /*
                1. populating oneline array with the answers to be used later in the students array
                2. Also setting the scores array to be equal to oneline so that printPersonalityTypes has access to the results
                 */
                oneline = inFile.nextLine().split(",");
                String [] scores = oneline;
                for (int col = 0; col < students[row].length - 1; col++) {
                    students[row][col] = String.valueOf(oneline[col]);
                    students[row][71] = personalityType(scores);


                }
            }
        }




        //This method is given and should print out the student's last name,
        //their answers to the 70 questions, and
        //their 4-letter personality type

        System.out.println(printPersonalityTypes(students));

        //TODO: This System.out.println assumes you have assigned myType above
        System.out.println("\n**********************\n\nMy Personality Type: " + myType);

        //TODO: Implement the following methods invoked in the System.out.println
        System.out.println("\n**********************\n\nClass Summary:" + printClassSummary(students));

        System.out.println("\n**********************\n\nThose with my personality type:" + printSimilarPersonality(students, myType));

        System.out.println("\n**********************\n\nQuestion Summary:" + printQuestionSummary(students));
    }



    /**
     * method printPersonalityTypes
     *
     * @param students
     * @return String
     * <p>
     * Outputs data from MBTI_Results as well as the MBTI 4-letter personality type
     */
    public static String printPersonalityTypes(String[][] students) {
        String output = "";
        for (int i = 0; i < students.length; i++) {
            output += students[i][0] + ": ";
            for (int j = 1; j < students[i].length; j++) {
                if (j < students[i].length - 1)
                    output += students[i][j] + ", ";
                else
                    output += students[i][j] + "\n";
            }
        }

        return output;

    }

    /**
     * method: personalityType
     *
     * @param scores (1-D array)
     * @return String
     *
     *
     *
     */





    public static String personalityType(String[] scores) {



        // TODO: For this one student's answers on the 70 question test, determine their 4-letter personality type

        StringBuilder pType = new StringBuilder("");

        /*
        1. Creating a StringBuilder for pType so that I can use toString in the return statement
        2. Making a for loop with switch statments to populate a StringBuilder for the 4-letter personality types
        3. Using the scores array within for loops to iterate through MBTI_Results.csv
         */

        int aLet1 = 0;
        int bLet1 = 0;
        int aLet2 = 0;
        int bLet2 = 0;
        int aLet3 = 0;
        int bLet3 = 0;
        int aLet4 = 0;
        int bLet4 = 0;

        for (int i = 1; i < scores.length; i += 7) {

            switch (scores[i]) {
                case "A":
                    aLet1++;
                    break;
                case "B":
                    bLet1++;
                    break;
            }
        }
        if (aLet1 > bLet1) {
            pType.append("E");
        } else if (bLet1 >= aLet1) {
            pType.append("I");
        }

        for (int i = 2; i < scores.length; i += 7) {

            switch (scores[i]) {
                case "A":
                    aLet2++;
                    break;
                case "B":
                    bLet2++;
                    break;
            }
            for (int j = 3; j < scores.length; j += 7) {

                switch (scores[j]) {
                    case "A":
                        aLet2++;
                        break;
                    case "B":
                        bLet2++;
                        break;
                }
            }
        }
        if (aLet2 > bLet2) {
            pType.append("S");
        } else if (bLet2 >= aLet2) {
            pType.append("N");
        }
            for (int i = 4; i < scores.length; i += 7) {

                switch (scores[i]) {
                    case "A":
                        aLet3++;
                        break;
                    case "B":
                        bLet3++;
                        break;
                }
                for (int j = 5; j < scores.length; j += 7) {

                    switch (scores[j]) {
                        case "A":
                            aLet3++;
                            break;
                        case "B":
                            bLet3++;
                            break;
                    }
                }
            }
            if (aLet3 > bLet3) {
                pType.append("T");
            } else if (bLet3 >= aLet3) {
                pType.append("F");
            }
        for (int i = 6; i < scores.length; i += 7) {

            switch (scores[i]) {
                case "A":
                    aLet4++;
                    break;
                case "B":
                    bLet4++;
                    break;
            }
            for (int j = 7; j < scores.length; j += 7) {

                switch (scores[j]) {
                    case "A":
                        aLet4++;
                        break;
                    case "B":
                        bLet4++;
                        break;
                }
            }
        }
        if (aLet4 > bLet4) {
            pType.append("J");
        } else if (bLet4 >= aLet4) {
            pType.append("P");
        }


        //referencing toString method
        return pType.toString();

    }

    /**
     * method:  printClassSummary
     * @param students
     * @return String
     *
     */
    public static String printClassSummary(String [][] students) {
        String output = "\n";
        
        /*
        1. Making a for loop to iterate through row(71) of students and find duplicated within the personalityTypes array
        2. Once a duplicate is found the value for count is stored
        3. Using a for loop to create the output and print class summary matched with count
         */

        String [] personalityTypes = {"INTJ (Architect): " , "INTP (Logician): ", "INFJ (Advocate): ", "INFP (Mediator): ", "ISTJ (Logistician): ", "ISFJ (Defender): ", "ISTP (Virtuoso): ", "ISFP (Adventurer): ", "ENTJ (Commander): ", "ENTP (Debator): ", "ENFJ (Protagonist): ", "ENFP (Campaigner): ", "ESTJ (Executive): ", "ESFJ (Consul): ", "ESTP (Entrepreneur): ", "ESFP (Entertainer): "};
        int [] count = new int[personalityTypes.length];

        for (int i = 0; i < students.length; i++) {
                for (int j = 0; j < personalityTypes.length; j++) {
                    if (personalityTypes[j].contains(students[i][71])) {
                        count[j]++;
                    }
            }
        }
        for (int i = 0; i < count.length; i++){
            output += personalityTypes[i] + count[i] + "\n";
        }
        // TODO: Use the two arrays above to give a class summary (i.e., count) of the 16 possible personality types
        //       You need to output the personality type followed by the count

        return output;
    }

    /**
     *
     * @param students
     * @param myType
     * @return
     *
     */
    public static String printSimilarPersonality(String [][] students, String myType) {
        String output = "\n";

        // TODO: Determine the names of all the students that have the same personality type as you.
        // DO NOT include your name in this output.

        /*
        1. Hard coding myType and myName for use in if statements
        2. Finding my name from element 0 of students.length and pulling it from the output
        3. Else if to compare myType= "INTP" to strings that match within element 71 of students
         */

        myType = "INTP";
        String myName = "Moore";

        for (int i = 0; i < students.length; i++) {
                    if(students[i][0].contains(myName)){
                        output+= "";
                    }
                    else if(students[i][71].contains(myType)){
                        output += students[i][0] + "\n";
                    }


            }

        return output;
    }

    /**
     *
     * @param students
     * @return
     * @throws FileNotFoundException
     *
     */

    public static String printQuestionSummary(String [][] students) throws FileNotFoundException {
        String output = "\n";
        // TODO: Use the MBTI_Questions.txt file to output the Question,
        //       answer A (as well as the class count for those who answered A for this question), and
        //       answer B (as well as the class count for those who answered B for this question).

        /*
        1. Using a scanner to iterate through the MBTI_Questions.txt
        2. Using if statements to find if elements of the students array contain either "A" or "B"
        3. Updating the count value every time one of these letters are found
        4. Formatting the output statement within a whileloop utilizing the countA and countB variables
         */

        int countA [] = new int[72];
        int countB [] = new int[72];
        Scanner qScan = new Scanner(new File("/Users/bradymoore/Desktop/211/MBTI_MOORE/src/main/java/MBTI_Questions"));
            for (int i = 0; i < students.length; i ++){
                for (int r = 1; r < students[i].length -1; r++){
                    if(students[i][r].contains("A")){
                        countA[r]++;
                    }
                        else if(students[i][r].contains("B")){
                            countB[r]++;
                    }
                }
            }

        while(qScan.hasNextLine()){
            for (int r = 1; r < 71; r++) {
                output += qScan.nextLine() + " " + qScan.nextLine() + " (" + countA[r] + ") " + qScan.nextLine() + " (" + countB[r] + ") \n";

            }

        }

        return output;
    }


}
