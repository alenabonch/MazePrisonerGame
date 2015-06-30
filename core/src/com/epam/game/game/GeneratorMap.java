package com.epam.game.game;

import com.epam.game.game.logic.Constants;



public class GeneratorMap {

    static private int _activeRow;
    static private int _outiDecIndex;
    static private int _head;
    static private int _tail;
    static private int _firstID;

    static int[][] _helpLine;
    private GeneratorMap() {
    }

    private static int getOutiDecIndex() {
        return _outiDecIndex--;
    }

    public static int[][] generateLabyrinth(int[][] newArray){
        _activeRow = 1;
        _outiDecIndex = -1;
        _helpLine = new int[2][(newArray[0].length - 1) / 2];
        newArray = refreshValues(randomFlor(randomWall(setValues(creetcreateTemplate(newArray)))));
        while (_activeRow < newArray.length - 2){
            newArray = refreshValues(randomFlor(randomWall(newArray)));
        }
        newArray = cleanLabyrinth(finalCreetWall(randomWall(newArray)));
        newArray = createExit(newArray);
        return cleanLabyrinth(newArray);
    }

    private static int[][] finalCreetWall(int[][] templateArray) {

        for (int i = 2; i < templateArray[_activeRow].length - 1; i += 2) {
            if ((templateArray[_activeRow][i - 1] != templateArray[_activeRow][i + 1]) && (templateArray[_activeRow][i] == Constants.WALL_TEXTURE_INDEX)){
                templateArray[_activeRow][i] = Constants.GROUND_TEXTURE_INDEX;
                int chengeID = templateArray[_activeRow][i + 1];
                int newID = templateArray[_activeRow][i - 1];
                for (int j = 1; j < templateArray[_activeRow].length; j+=2) {
                    if(templateArray[_activeRow][j] == chengeID){
                        templateArray[_activeRow][j] = newID;
                    }
                }
            }

        }
        return templateArray;
    }

    private static int[][] creetcreateTemplate(int[][] cleanArray){
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
        for (int i = 1; i < templateArray[_activeRow].length; i+=2) {
            templateArray[_activeRow][i] = getOutiDecIndex();
        }
        return templateArray;
    }

    private static int[][] randomWall(int[][] templateArray){
        for (int i = 2; i < templateArray[_activeRow].length - 1; i+=2) {
            if((templateArray[_activeRow][i - 1] == templateArray[_activeRow][i + 1]) || (doTheWall())){
                templateArray[_activeRow][i] = Constants.WALL_TEXTURE_INDEX;

            }else
            {
                int chengeID = templateArray[_activeRow][i + 1];
                int newID = templateArray[_activeRow][i - 1];
                for (int j = 1; j < templateArray[_activeRow].length; j+=2) {
                    if(templateArray[_activeRow][j] == chengeID){
                        templateArray[_activeRow][j] = newID;
                    }
                }
            }
        }
        return templateArray;
    }

    private static int[][]	randomFlor(int[][] templateArray){

        for (int i = 0; i < _helpLine[0].length; i++) {
            _helpLine[0][i] = templateArray[_activeRow][i * 2 + 1];
            _helpLine[1][i] = i * 2 + 1;
        }
        _helpLine = sortHelpLine(_helpLine);

        _head = 0;
        _tail = 0;
        _firstID = 0;
        while (_head < _helpLine[0].length) {
            while ((_tail < _helpLine[0].length) && (_helpLine[0][_tail] == _helpLine[0][_head])){
                _tail++;
            }
            swapHelpLine(_helpLine, _firstID++, randomBetween(_head , _tail));
            _head = _tail;
        }
        for (int i = _firstID; i < _helpLine[0].length; i++) {
            if(doTheWall()){
                templateArray[_activeRow + 1][_helpLine[1][i]] = Constants.WALL_TEXTURE_INDEX;
            }
        }

        return templateArray;
    }

    private static int[][] refreshValues(int[][] templateArray){
        for (int i = 1; i < templateArray[_activeRow].length; i+=2) {
            if (templateArray[_activeRow + 1][i] == Constants.WALL_TEXTURE_INDEX){
                templateArray[_activeRow + 2][i] = getOutiDecIndex();
            }
            else
            {


                templateArray[_activeRow + 2][i] = templateArray[_activeRow][i];
            }
        }
        _activeRow += 2;
        return templateArray;
    }

    public static int[][] cleanLabyrinth(int[][] drityArray){
        for (int i = 0; i < drityArray.length; i++) {
            for (int j = 0; j < drityArray[i].length; j++) {
                if(drityArray[i][j] < 0)
                    drityArray[i][j] = Constants.GROUND_TEXTURE_INDEX;
            }
        }
        return drityArray;
    }

    private static boolean doTheWall(){
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