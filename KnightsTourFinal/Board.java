package com.KnightsTourFinal;

import java.util.HashMap;

public class Board{

    private String[][] board = {{"a8","b8","c8","d8","e8","f8","g8","h8"},
                       {"a7","b7","c7","d7","e7","f7","g7","h7"},
                       {"a6","b6","c6","d6","e6","f6","g6","h6"},
                       {"a5","b5","c5","d5","e5","f5","g5","h5"},
                       {"a4","b4","c4","d4","e4","f4","g4","h4"},
                       {"a3","b3","c3","d3","e3","f3","g3","h3"},
                       {"a2","b2","c2","d2","e2","f2","g2","h2"},
                       {"a1","b1","c1","d1","e1","f1","g1","h1"}};

    public int[][] board1 = {{0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0}};
    HashMap<Integer, String> mapOrder;

    public Board(){
        this.mapOrder = new HashMap<Integer, String>();

    }
    // Verifica se as coordenadas estão dentro do tabuleiro que o cavalo ainda não passou, ou seja 0
    public boolean valido(int nextRow, int nextFile, int[][] board1){
        if(nextFile <=7 && nextRow<=7 && nextFile >= 0  && nextRow>=0 && board1[nextRow][nextFile] == 0){
            return true;
        }
        return false;

    }

    public boolean passeioCavalo(int currRow, int currFile, int[][] L, int move){
        if (move == 65){ //já que o metodo é iniciado com move = 2 o move 1 não foi contabilizado logo o movimento 64 ocorre no move 65
            return true;
        }

        for(int i = 0; i<L.length; ++i){
            //Realizam um dos 8 movimentos em L do cavalo descritos em lMoves na classe Knight (lMoves= {{-2,+1} ,{-2,-1} ,{+2,+1} ,{+2,-1} ,{+1,+2} ,{-1,+2} ,{+1,-2} ,{-1,-2}}; // formato -> {{row, file} ,{row,file},...})
            int nextRow = currRow + L[i][0];
            int nextFile = currFile + L[i][1];


            if(valido(nextRow, nextFile, board1)){// Se a posição for valida ela vai receber a contagem atual
                board1[nextRow][nextFile] = move;

                //Verifica se o proximo do proximo é valido
                if(passeioCavalo(nextRow, nextFile, L, move + 1)){
                    return true;
                }
                else{
                    //Realiza o backtracking, limpando a sequencia de movimentos que levaram ao fracasso, continuando a partir do movimento conhecido como seguro por enquanto
                    //Lembrando que a variavel move é resgatada já que ela foi incrementada dentro da recursividade que retornou false
                    board1[nextRow][nextFile] = 0;

                }
            }
        }
        return false;
    }

    public void passeio(String pos){
        Knight knight = new Knight();
        //Traduzem a notação de xadrez para as cordenadas da matriz manipulando a tabela ASCII, sendo board[row][file]
        int currFile = pos.charAt(0) - 97;
        int currRow = 8 - (pos.charAt(1)- '0');
        int[][] L = knight.getlMoves();

        int move =  2; //Faz com que o proximo movimento do passeio seja o movimento 2
        board1[currRow][currFile] = 1;//Coloca o primeiro movimento antes do tour
        passeioCavalo(currRow, currFile, L, move);

        //Imprime o tabuleiro com os movimentos
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board[i].length; j++){
                System.out.print(board[i][j]+"("+board1[i][j]+") ");
                mapOrder.put(board1[i][j], board[i][j]);//Organiza o numero do movimento com a posição no tabuleiro
            }
            System.out.println();
        }
        System.out.println("\nSequencia de movimentos do passeio: ");
        //Imprime os movimentos do passeio na sequencia
        for(int i = 1; i<mapOrder.size()+1; i++){
            System.out.println(mapOrder.get(i));
        }
    }
}
