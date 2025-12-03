package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException{
        System.out.println("Hello Day 3");

        File myFile = new File("Day3\\resources\\input.txt");
        Scanner myElfHelper = new Scanner(myFile);

        part2(myElfHelper);

        myElfHelper.close();
    }

    public static void part1(Scanner myElfHelper){
        int total = 0;
        while(myElfHelper.hasNextLine()){
            int firstDigit = 1;
            int secondDigit = 1;

            String line = myElfHelper.nextLine();
            String workingRange = line.substring(0, line.length()-1);
            int firstIndex = 0;
            for(int i = 9; i > 0; i--){
                int index = workingRange.indexOf("" + i);
                //System.out.println("current Index: "+ index);
                if(index != -1){
                    firstIndex = index;
                    firstDigit = i;
                    break;
                }
            }

            workingRange = line.substring(firstIndex+1);
            //System.out.println(workingRange);
            //int secondIndex = 0;
            for(int i = 9; i > 0; i--){
                int index = workingRange.indexOf("" + i);
                if(index != -1){
                    secondDigit = i;
                    break;
                    //System.out.println("ah");
                }
            }
            int bestVoltage = firstDigit*10 +secondDigit;
            total+= bestVoltage;
             
            System.out.println("Line: " +  line + ", best voltage: " + bestVoltage);
        }
        System.out.println("Your total is: " + total);
    }
    public static void part2(Scanner myElfHelper){
        long total = 0;
        while(myElfHelper.hasNextLine()){
            String line = myElfHelper.nextLine();
            int[] bestCombo = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
            int currIndex = 0;

            for(int batteryIndex = 0; batteryIndex < bestCombo.length; batteryIndex++){
                String workingRange = line.substring(currIndex, line.length()-(bestCombo.length-1-batteryIndex));
                for(int i = 9; i > 0; i--){
                    int index = workingRange.indexOf("" + i);
                    if(index != -1){
                        currIndex += index+1;
                        bestCombo[batteryIndex] = i;
                        break;
                        //System.out.println("ah");
                    }
                }
            }

            Long finalVoltage = 0L;
            for (int digit : bestCombo) {
                finalVoltage*=10L;
                finalVoltage+= (long) digit;
            }
            total += finalVoltage;
            System.out.println("Line: " +  line + ", best voltage: " + finalVoltage);
        }
        System.out.println("Your total is: " + total);
    }
}
