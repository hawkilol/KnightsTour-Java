import com.KnightsTourFinal.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a posição inicial do cavalo: ");
        String pos = scanner.next();
        board.passeio(pos);

    }
}
