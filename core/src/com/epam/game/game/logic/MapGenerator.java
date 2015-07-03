package com.epam.game.game.logic;

import com.epam.game.game.utils.Constants;

public class MapGenerator {

    static private int activeRow;
    static private int outDecIndex;
    static private int head;
    static private int tail;
    static private int firstID;

    static int[][] helpLine;
    private MapGenerator() {
    }

    private static int getOutDecIndex() {
        return outDecIndex--;
    }

    public static int[][] generateMaze(int[][] newArray){
        activeRow = 1;
        outDecIndex = -1;
        helpLine = new int[2][(newArray[0].length - 1) / 2];
        newArray = refreshValues(randomFloor(randomWall(setValues(createTemplate(newArray)))));
        while (activeRow < newArray.length - 2){
            newArray = refreshValues(randomFloor(randomWall(newArray)));
        }
        newArray = cleanMaze(createEdgeWalls(randomWall(newArray)));
        newArray = createExit(newArray);
        return cleanMaze(newArray);
    }

    private static int[][] createEdgeWalls(int[][] templateArray) {

        for (int i = 2; i < templateArray[activeRow].length - 1; i += 2) {
            if ((templateArray[activeRow][i - 1] != templateArray[activeRow][i + 1]) && (templateArray[activeRow][i] == Constants.WALL_TEXTURE_INDEX)){
                templateArray[activeRow][i] = Constants.GROUND_TEXTURE_INDEX;
                int changeID = templateArray[activeRow][i + 1];
                int newID = templateArray[activeRow][i - 1];
                for (int j = 1; j < templateArray[activeRow].length; j+=2) {
                    if(templateArray[activeRow][j] == changeID){
                        templateArray[activeRow][j] = newID;
                    }
                }
            }

        }
        return templateArray;
    }

    private static int[][] createTemplate(int[][] cleanArray){
        for (int i = 0; i < cleanArray.length; i++) {
            for (int j = 0; j < cleanArray[i].length; j++) {
                if((i == 0) || (j == 0) || (i == cleanArray.length - 1) || (j == cleanArray[i].length - 1) || ((j % 2 == 0) && (i % 2 == 0))){
                    cleanArray[i][j] = Constants.WALL_TEXTURE_INDEX;
                }
                else
                {
                    cleanArray[i][j] = Constants.GROUND_TEXTURE_INDEX;
                }
            }
        }
        return cleanArray;
    }

    private static int[][] setValues(int[][] templateArray){
        for (int i = 1; i < templateArray[activeRow].length; i+=2) {
            templateArray[activeRow][i] = getOutDecIndex();
        }
        return templateArray;
    }

    private static int[][] randomWall(int[][] templateArray){
        for (int i = 2; i < templateArray[activeRow].length - 1; i+=2) {
            if((templateArray[activeRow][i - 1] == templateArray[activeRow][i + 1]) || (createWall())){
                templateArray[activeRow][i] = Constants.WALL_TEXTURE_INDEX;

            }else
            {
                int chengeID = templateArray[activeRow][i + 1];
                int newID = templateArray[activeRow][i - 1];
                for (int j = 1; j < templateArray[activeRow].length; j+=2) {
                    if(templateArray[activeRow][j] == chengeID){
                        templateArray[activeRow][j] = newID;
                    }
                }
            }
        }
        return templateArray;
    }

    private static int[][] randomFloor(int[][] templateArray){

        for (int i = 0; i < helpLine[0].length; i++) {
            helpLine[0][i] = templateArray[activeRow][i * 2 + 1];
            helpLine[1][i] = i * 2 + 1;
        }
        helpLine = sortHelpLine(helpLine);

        head = 0;
        tail = 0;
        firstID = 0;
        while (head < helpLine[0].length) {
            while ((tail < helpLine[0].length) && (helpLine[0][tail] == helpLine[0][head])){
                tail++;
            }
            swapHelpLine(helpLine, firstID++, randomBetween(head, tail));
            head = tail;
        }
        for (int i = firstID; i < helpLine[0].length; i++) {
            if(createWall()){
                templateArray[activeRow + 1][helpLine[1][i]] = Constants.WALL_TEXTURE_INDEX;
            }
        }

        return templateArray;
    }

    private static int[][] refreshValues(int[][] templateArray){
        for (int i = 1; i < templateArray[activeRow].length; i+=2) {
            if (templateArray[activeRow + 1][i] == Constants.WALL_TEXTURE_INDEX){
                templateArray[activeRow + 2][i] = getOutDecIndex();
            }
            else
            {
                templateArray[activeRow + 2][i] = templateArray[activeRow][i];
            }
        }
        activeRow += 2;
        return templateArray;
    }

    public static int[][] cleanMaze(int[][] dirtyArray){
        for (int i = 0; i < dirtyArray.length; i++) {
            for (int j = 0; j < dirtyArray[i].length; j++) {
                if(dirtyArray[i][j] < 0)
                    dirtyArray[i][j] = Constants.GROUND_TEXTURE_INDEX;
            }
        }
        return dirtyArray;
    }

    private static boolean createWall(){
        return(Constants.RANDOM.nextBoolean());
    }

    private static int[][] sortHelpLine(int[][] helpLine){
        for (int i = 0; i < helpLine.length - 1; i++) {
            for (int j = i; j < helpLine.length; j++) {
                if (helpLine[0][i] > helpLine[0][j]){
                    helpLine = swapHelpLine(helpLine, i, j);
                }
            }
        }
        return helpLine;
    }
    private static int[][] swapHelpLine(int[][] helpLine, int a, int b){
        int helpVal = helpLine[0][a];
        int helpID = helpLine[1][a];
        helpLine[0][a] = helpLine[0][b];
        helpLine[1][a] = helpLine[1][b];
        helpLine[0][b] = helpVal;
        helpLine[1][b] = helpID;
        return helpLine;
    }
    private static int randomBetween(int a, int b){
        if(a == b){
            return(a);
        }else
        {
            return Constants.RANDOM.nextInt(Math.abs(b - a)) + Math.min(a, b);
        }
    }
    private static int[][] createExit(int[][] labyrinth){
        labyrinth[labyrinth.length - 2][labyrinth[0].length -2] = Constants.EXIT_TEXTURE_INDEX;
        return labyrinth;
    }


}