import java.util.*;
import java.io.*;

public class RotatedBS {

    public static int binarySearch(Integer [] a, int low, int high, int target) {

        while(low <= high) {
            int mid = (low + high) / 2; 
            if (a[mid] == target) {
                return mid; 
            }
            else if(a[low] <= a[mid]) {
                if (target >= a[low]) {
                    high = mid - 1;
                } 
                else {
                    low = mid + 1; 
                }
            }
            else if (target < a[mid]){
                high = mid - 1; 
            }
            else if (target <= a[high]) {
                low = mid + 1; 
            }
            else {
                high = mid - 1;
            }
        }
        
        return Arrays.asList(a).indexOf(target); 
    }

    public static int search(Integer [] a, int target) {
        return binarySearch(a, 0, a.length-1, target);
    }

    public static void interactiveBS() {
        Scanner input = new Scanner(System.in); 
        System.out.println("Enter array elements, separated by commas");
        String elements = input.next(); 
        String[] elementsArray = elements.split(","); 
        
        Integer [] searchArray = new Integer[elementsArray.length]; 
        for (int i=0; i<searchArray.length; i++) {
            searchArray[i] = Integer.parseInt(elementsArray[i]); 
        }

        System.out.println("\nEnter search element");
        int target = input.nextInt();

        System.out.println("\nYou are searching for " + target + " in array " + Arrays.toString(searchArray));

        int index = search(searchArray, target); 
 
        if (index == -1) {
            System.out.println("\n" + target + " not found.");
        }
        else {
            System.out.println("\n" + target + " is at index " + index + "");
        }

        input.close();
    }

    public static void fileBS() throws IOException {
        File inputFile = new File("input.txt"); 
        Scanner fileReader = new Scanner(inputFile); 

        // Create output file 
        File outputFile = new File("output.txt");

        // Check if file already exists. Delete if exists and create a new one
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
        
        // Read input file, execute and write the output to output.txt
        int numTests = Integer.parseInt(fileReader.nextLine()); 
 
        FileWriter fileWriter = new FileWriter(outputFile); 

        String [] forbiddenChars = { "[", ",", " ", "]", "(", ")" }; 

        for (int i = 0; i < numTests; i++) {
            List<Integer> searchArray = new ArrayList<>(); 
            String input = fileReader.nextLine();
            String targetStr = fileReader.nextLine();
            String array = input.substring(0, input.indexOf(")") + 1); 
            int target = Integer.parseInt(targetStr.trim());

            String current = "";
            for (int j = 1; j < array.length(); j++) {

                if ( Arrays.asList(forbiddenChars).indexOf(Character.toString(array.charAt(j))) == -1 ) {
                    current = current.concat(Character.toString(array.charAt(j)));
                }
                else {
                    searchArray.add(Integer.valueOf(current));
                    current = "";
                }
            }

            int index = search(searchArray.toArray(new Integer[0]), target); 

            fileWriter.write(index + "\n");
            
        }
        System.out.println("\nProgram outputs written to output.txt file in this folder.\n\s");

        fileReader.close();
        fileWriter.close();

    }

    public static void main(String[] args) throws IOException {
        System.out.println("\nRotated Binary Search\n");

        if (args.length == 0) {
            System.out.println("Run mode not specified. Please enter either \'file\' or \'interactive\' to run this program\n");
        }

        else {

            String runningMode = args[0].toLowerCase();
        
            if (runningMode.equals("interactive")) {
                interactiveBS();
            }

            else if (runningMode.equals("file")) {
                fileBS();
            }

            else {
                System.out.println("\nUnknown run mode. Please enter either \'file\' or \'interactive\' to run this program.\n");
            }
        }
    }
}