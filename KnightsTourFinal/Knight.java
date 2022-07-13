package com.KnightsTourFinal;

public class Knight {

    private int file;
    private int row;

    protected int[][] lMoves= {{2, -1} ,{1,-2} ,{-1,-2} ,{-2,-1} ,{-2,1} ,{-1,2} ,{1,2} ,{2,1}}; // movimentos em L do cavalo, formato -> {{row, file} ,{row,file},...})

    public Knight(){
    }

    public int[][] getlMoves() {
        return lMoves;
    }

    public void setlMoves(int[][] lMoves) {
        this.lMoves = lMoves;
    }

    public int getRow() {
        return row;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
