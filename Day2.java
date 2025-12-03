package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException{
        System.out.println("Hello Day 2");
        
        File myFile = new File("Day2\\resources\\input.txt");
        Scanner myElfHelper = new Scanner(myFile);
        String [] ranges = myElfHelper.nextLine().split(",");
        ArrayList<Long[]> boundPairs = new ArrayList<>();
        for (String string : ranges) {
            String[] bounds = string.split("-");
            Long[] longifiedBounds = {Long.parseLong(bounds[0]), Long.parseLong(bounds[1])};
            boundPairs.add(longifiedBounds);
        }
        myElfHelper.close();
        
        //Printing
        // for (Long[] boundPair : boundPairs) {
        //     System.out.println("Lower Bound: " + boundPair[0] + ", Upper Bound: " + boundPair[1]);            
        // }
        part2(boundPairs);

        

    }

    public static void part1(ArrayList<Long[]> boundPairs){
        Long result = 0L;

        for (Long[] pair : boundPairs) {
            Long lowerBound = pair[0];
            Long upperBound = pair[1];

            int lowBoundLength = ((int) Math.log10(lowerBound))+1;
            int upBoundLength = ((int) Math.log10(upperBound))+1;

            if(lowBoundLength %2 ==1){
                if(upBoundLength %2 == 1){
                    continue;
                }

                lowerBound = Math.powExact(10L, upBoundLength-1);
                lowBoundLength = upBoundLength;
            } else if(upBoundLength%2 == 1){
                upperBound = Math.powExact(10L, lowBoundLength)-1L;
            }

            for (Long repeater : checkPair(lowerBound, upperBound, lowBoundLength/2, 2)) {
                result+=repeater;
            }
            System.out.println(lowerBound + " and " + upperBound);
        }
        System.out.println("Your grand total is: " + result);
    }


    public static void part2(ArrayList<Long[]> boundPairs){
        HashSet<Long> repeaters = new HashSet<Long>();
        for (Long[] pair : boundPairs) {
            Long lowerBound = pair[0];
            Long upperBound = pair[1];

            int lowBoundLength = ((int) Math.log10(lowerBound))+1;
            int upBoundLength = ((int) Math.log10(upperBound))+1;

            if(lowBoundLength != upBoundLength){
                Long tempUpper = Math.powExact(10L, lowBoundLength)-1L;
                
                ArrayList<Integer> factors = getFactors(lowBoundLength);
                for(int i = factors.size()-1; i > 0; i--){
                    int length = factors.get(factors.size()-1-i);
                    int reps = factors.get(i);

                    repeaters.addAll(checkPair(lowerBound, tempUpper, length, reps));
                }


                Long tempLower = tempUpper+1L;
                factors = getFactors(upBoundLength);
                for(int i = factors.size()-1; i > 0; i--){
                    int length = factors.get(factors.size()-1-i);
                    int reps = factors.get(i);

                    repeaters.addAll(checkPair(tempLower, upperBound, length, reps));
                }
            } else {
                ArrayList<Integer> factors = getFactors(lowBoundLength);
                for(int i = factors.size()-1; i > 0; i--){
                    int length = factors.get(factors.size()-1-i);
                    int reps = factors.get(i);

                    repeaters.addAll(checkPair(lowerBound, upperBound, length, reps));
                }
            }
        }


        Long total = 0L;
        for (Long additive : repeaters) {
            total += additive;
        }
        System.out.println("Your grand total is: " + total);
    }

    public static Set<Long> checkPair(Long lower, Long upper, int length, int repetitions){
        HashSet<Long> repeaters = new HashSet<>();
        Long repeatedSection = (long) (lower / Math.powExact(10L, ((int) (Math.log10(lower)) - length + 1)));
        //Long repeatedSection = lower / Math.powExact(10L, repetitions);
        while(true){
            Long candidate = repeatedSection;
            for(int i = 1; i < repetitions; i++){
                candidate*= Math.powExact(10L, length);
                candidate += repeatedSection;
            }
            
            if(candidate >= lower && candidate <= upper){
                repeaters.add(candidate);
            } 
            if(candidate>upper){
                break;
            }
            repeatedSection++;
        }
        
        return repeaters;
    }


    public static ArrayList<Integer> getFactors(int n) {
    Set<Integer> factors = new HashSet<>();
    for (int i = 1; i <= Math.sqrt(n); i++) {
        if (n % i == 0) {
            factors.add(i);
            factors.add(n / i);
        }
    }
    return new ArrayList<Integer>(factors);
}
}
