package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1 {

    public static void main(String[] args) throws FileNotFoundException{
        System.out.println("Hello Day 1");
        
        int startingPosition = 50;
        int maxNum = 99;

        File myFile = new File("Day1\\resources\\input.txt");
        
        
        Scanner myElfHelper = new Scanner(myFile);

        part1(myElfHelper, startingPosition, maxNum);
        
        myElfHelper.close();
        
        
        Scanner myDwarvenHelper = new Scanner(myFile);
        
        part2(myDwarvenHelper, startingPosition, maxNum);

        myDwarvenHelper.close();
    }


    public static void part1(Scanner myElfHelper, int currentPosition, int maxNum){
        //Count number of times it lands on zero
        int zeros = 0;
        while(myElfHelper.hasNext()){
            String currString = myElfHelper.next();

            int sign = (currString.charAt(0) == 'R') ? 1 : -1;
            int turn = sign * Integer.parseInt(currString.substring(1));
            currentPosition += turn;
            if((currentPosition)%(maxNum+1) == 0){
                zeros++;
            }
            if(currentPosition < 0){
                currentPosition = (maxNum+1) - (Math.abs(currentPosition)%100);
            } else if(currentPosition >maxNum){
                currentPosition%=(maxNum+1);
            }
        }
        System.out.println("Your zero count is: " + zeros);
    }

    public static void part2(Scanner myElfHelper, int currentPosition, int maxNum){
        int touchesOnZero = 0;

        while(myElfHelper.hasNext()){
            String currString = myElfHelper.next();

            int sign = (currString.charAt(0) == 'R') ? 1 : -1;
            int turn = sign * Integer.parseInt(currString.substring(1));

            int startPosition = currentPosition;
            
            touchesOnZero+= Math.abs(turn/(maxNum+1));

            currentPosition+=(turn%(maxNum+1));
            if(currentPosition < 0){
                currentPosition+=maxNum+1;
                if(startPosition!=0){
                    touchesOnZero++;
                }
            } else if(currentPosition>=maxNum+1){
                currentPosition%=(maxNum+1);
                if(startPosition != 0){
                    touchesOnZero++;
                }
            } else if(currentPosition == 0){
                touchesOnZero++;
            }
        }
        System.out.println("Your lock touched zero a whole **" + touchesOnZero + "** times!!");
    }
}


