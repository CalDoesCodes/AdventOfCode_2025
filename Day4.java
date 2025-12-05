package Day4;

import java.io.BufferedWriter;
import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) throws Exception{
        System.out.println("Hello Day 4");

        File myFile = new File("Day4\\resources\\input.txt");
        
        
        Scanner myElfHelper = new Scanner(myFile);
         //part1(myElfHelper);
        myElfHelper.close();
       
        part2(myFile);
        //myElfHelper.close();
    }

    public static void part1(Scanner myElfHelper){
        String previousLine = "";
        String currentLine = "";
        String nextLine = myElfHelper.nextLine();
        
        int availableRolls = 0;

        int yIndex = 0;
        while(myElfHelper.hasNextLine()) {
            previousLine = currentLine;
            currentLine = nextLine;
            nextLine = myElfHelper.nextLine();
            ArrayList<Integer> prevIndexes = findRollIndexes(previousLine);
            ArrayList<Integer> currentIndexes = findRollIndexes(currentLine);
            ArrayList<Integer> nextIndexes =findRollIndexes(nextLine);

            
            

            for (Integer index : currentIndexes) {
                int adjacentRolls = 0;
                for(int prevInd = -1; prevInd <= 1; prevInd++){
                    if(prevIndexes.contains(index + prevInd)){
                        adjacentRolls++;
                    }
                }

                if(currentIndexes.contains(index-1))
                    adjacentRolls++;

                if(currentIndexes.contains(index+1))
                    adjacentRolls++;

                for(int nextInd = -1; nextInd <= 1; nextInd++){
                    if(nextIndexes.contains(index + nextInd)){
                        adjacentRolls++;
                    }
                }
                if(adjacentRolls < 4){
                    System.out.println("Found a roll available at (" + index + ", " + yIndex + ")");
                    availableRolls++;
                }

            }

            yIndex++;
        }

        ArrayList<Integer> prevIndexes = findRollIndexes(currentLine);
        ArrayList<Integer> currentIndexes = findRollIndexes(nextLine);
        //yIndex++;

        for (Integer index : currentIndexes) {
            int adjacentRolls = 0;
            for(int prevInd = -1; prevInd <= 1; prevInd++){
                if(prevIndexes.contains(index + prevInd)){
                    adjacentRolls++;
                }
            }

            if(currentIndexes.contains(index-1))
                adjacentRolls++;

            if(currentIndexes.contains(index+1))
                adjacentRolls++;

            if(adjacentRolls < 4){
                    availableRolls++;
                    System.out.println("Found a roll available at (" + index + ", " + yIndex + ")");
                }
        }


        System.out.println("There are a total of: **" + availableRolls + "** rolls available!");
       
    }


    public static void part2(File myFile) throws IOException{
        File copy = new File("Day4\\resources\\workingFile.txt");
        Files.copy(myFile.toPath(), copy.toPath(), StandardCopyOption.REPLACE_EXISTING);
        
        
        int total = 0;
        while(true){
            File currentWriteFile = new File("Day4\\resources\\writeFile.txt");
            FileWriter fw = new FileWriter(currentWriteFile.getPath());
            BufferedWriter bufferedWriter = new BufferedWriter(fw);
            Scanner myElfHelper = new Scanner(copy);
            int removals = 0;
            StringBuilder previousLine = new StringBuilder("");
            StringBuilder currentLine = new StringBuilder("");
            StringBuilder nextLine = new StringBuilder(myElfHelper.nextLine());
            
            while(myElfHelper.hasNextLine()){
                previousLine = currentLine;
                currentLine = nextLine;
                nextLine = new StringBuilder(myElfHelper.nextLine());
                ArrayList<Integer> prevIndexes = findRollIndexes(previousLine.toString());
                ArrayList<Integer> currentIndexes = findRollIndexes(currentLine.toString());
                ArrayList<Integer> nextIndexes =findRollIndexes(nextLine.toString());

                for (Integer index : currentIndexes) {
                    int adjacentRolls = 0;
                    //Check previous Row
                    for(int prevInd = -1; prevInd <= 1; prevInd++){
                        if(prevIndexes.contains(index + prevInd)){
                            adjacentRolls++;
                        }
                    }
                    
                    //Check Current Row
                    if(currentIndexes.contains(index-1))
                    adjacentRolls++;

                    if(currentIndexes.contains(index+1))
                        adjacentRolls++;

                    //Check next Row
                    for(int nextInd = -1; nextInd <= 1; nextInd++){
                        if(nextIndexes.contains(index + nextInd)){
                            adjacentRolls++;
                        }
                    }

                    if(adjacentRolls < 4){
                        //System.out.println("Found a roll available at (" + index + ", " + yIndex + ")");
                        removals++;

                        currentLine.setCharAt(index, '.');
                    }
                }
                bufferedWriter.write(currentLine.toString());
                bufferedWriter.newLine();
            }
            
            ArrayList<Integer> prevIndexes = findRollIndexes(currentLine.toString());
            ArrayList<Integer> currentIndexes = findRollIndexes(nextLine.toString());
            currentLine = nextLine;
            for (Integer index : currentIndexes) {
                int adjacentRolls = 0;
                //Check previous Row
                for(int prevInd = -1; prevInd <= 1; prevInd++){
                    if(prevIndexes.contains(index + prevInd)){
                        adjacentRolls++;
                    }
                }
                
                //Check Current Row
                if(currentIndexes.contains(index-1))
                adjacentRolls++;

                if(currentIndexes.contains(index+1))
                    adjacentRolls++;

                if(adjacentRolls < 4){
                    //System.out.println("Found a roll available at (" + index + ", " + yIndex + ")");
                    removals++;

                    currentLine.setCharAt(index, '.');
                }
            }
            bufferedWriter.write(currentLine.toString());
            bufferedWriter.close();
            myElfHelper.close();
            fw.close();
            
            Files.copy(currentWriteFile.toPath(),
            copy.toPath(), 
            StandardCopyOption.REPLACE_EXISTING);
            
            //Files.delete(currentWriteFile.toPath());

            total += removals;
            if(removals == 0)
                break;
        }
        System.out.println(total);
        
    }
    public static ArrayList<Integer> findRollIndexes(String line){
        ArrayList<Integer> indexes = new ArrayList<>();

        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) == '@'){
                indexes.add(i);
            }
        }
        return indexes;
    }
}
