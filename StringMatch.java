import java.util.*;
import java.io.*;

public class StringMatch {
    
    public static int stringMatch(String text, String pattern) {
        
        int n = text.length(); 
        int m = pattern.length();

        for (int i = 0; i < n - m + 1; i++) {
            
            int j = 0; 

            while ( (j < m) && ( Character.toString(pattern.charAt(j)).equals(Character.toString(text.charAt(i+j))) ) )  {
                j++; 
            }

            if (j == m) {
                return i;
            }

        }
        return -1;
    }

    public static void interactiveStrMatch() {

        Scanner input = new Scanner(System.in); 
        System.out.println("\nEnter text to search in...");
        String text = input.nextLine(); 

        System.out.println("\nEnter pattern to search...");
        String pattern = input.nextLine(); 

        int index = stringMatch(text, pattern);

        if (index == -1) {
            System.out.println("\nSearch pattern not found.\n");
        }
        else {
            System.out.println("\nSearch pattern found, starting at index " + index + "\n");
        }

        input.close();

    }

    public static void fileStrMatch() throws IOException {
        
        File inputFile = new File("input.txt"); 
        Scanner fileReader = new Scanner(inputFile);

        File outputFile = new File("output.txt"); 

        if (outputFile.exists()) {
            System.out.print("Output file already exists. Deleting existing file...");
            if (outputFile.delete()) {
                System.out.print(" done.\n");
            }
            else {
                System.out.print(" unsuccessful.\n");
                fileReader.close();
                return;
            } 
        }

        System.out.print("\nCreating new output file...");
        if (outputFile.createNewFile()) {
            System.out.print(" done.\n");
        }
        else {
            System.out.print(" unsuccessful.\n");
            fileReader.close();
            return;
        }

        FileWriter fileWriter = new FileWriter(outputFile);

        int numMatches = Integer.parseInt(fileReader.nextLine().trim());

        for (int i = 0; i < numMatches; i++) {
            
            String text = fileReader.nextLine().trim(); 
            String pattern = fileReader.nextLine().trim(); 

            int startIndex = stringMatch(text, pattern); 

            fileWriter.write(startIndex + "\n");

        }

        System.out.println("\nStarting indices written to output.txt \n");

        fileReader.close();
        fileWriter.close();

    }

    public static void main(String[] args) throws IOException {
        System.out.println("\nString Matching Algorithm");
        
        if (args.length == 0) {
            System.out.println("Unknown run mode. Please enter either \'file\' or \'interactive\' to run this program\n");
            return;
        }

        else {

            String runningMode = args[0].toLowerCase();

            if (runningMode.equals("interactive")) {
                interactiveStrMatch();
            }

            else if (runningMode.equals("file")) {
                fileStrMatch();
            }

            else {
                System.out.println("Unknown run mode. Please enter either \'file\' or \'interactive\' to run this program\n");
            }

        }
    }
}
