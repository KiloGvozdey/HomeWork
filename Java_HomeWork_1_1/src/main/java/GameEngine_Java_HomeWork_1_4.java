import java.util.Random;
import java.util.Scanner;

public class GameEngine_Java_HomeWork_1_4 {
    public static void main(String[] args) {

            System.out.println("Please enter the field size: ");
            Scanner scannerFieldSize = new Scanner(System.in);
            int size = scannerFieldSize.nextInt();
            char[][] field = getGameField(size);
            printGameField(field);
            while (true) {
                doPlayerTurn(field, 'X');
                printGameField(field);
                if (isDraw(field)) {
                    System.out.println("Draw in game. Please try again.");
                    break;
                }
                if (isWin(field, 'X')) {
                    System.out.println("Congratulation!!!! You are winner!=)");
                    break;
                }
                doBlockHuman(field, 'X', 'O');
                printGameField(field);
                if (isWin(field, 'O')) {
                    System.out.println("Sorry you lose. Please try again...");
                    break;
                }
                if (isDraw(field)) {
                    System.out.println("Draw in game. Please try again.");
                    break;
                }
            }
        }
        static char[][] getGameField ( int size){
            char[][] gameField = new char[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    gameField[i][j] = '-';
                }
            }
            return gameField;
        }
        static void printGameField ( char[][] field){
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field.length; j++) {
                    System.out.print(field[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }
            System.out.println();
        }
        static void doPlayerTurn ( char[][] field, char playerChar){
            int vertical;
            int horizontal;
            do {
                System.out.println("Please chose coordinate for your char...");
                horizontal = getCoordinates('H', field);
                vertical = getCoordinates('V', field);
            } while (isNotEmptyCell(field, horizontal, vertical));
            field[horizontal][vertical] = playerChar;
        }
        static void doAITurn ( char[][] field, char aiChar){
            int vertical;
            int horizontal;
            Random random = new Random();
            do {
                horizontal = random.nextInt(field.length);
                vertical = random.nextInt(field.length);
            } while (isNotEmptyCell(field, horizontal, vertical));
            field[horizontal][vertical] = aiChar;
        }
        static boolean isWin ( char[][] field, char c){
            int count = 0;
            int countForWin = 0;
            if (field.length == 3) countForWin = 3;
            if (field.length == 5) countForWin = 4;
            /**
             * Проверка горизонталей на победу
             */
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field.length; j++) {
                    if (field[i][j] == c) count++;
                    if (count == countForWin) return true;
                }
                count = 0;
            }
            /**
             * Проверка вертикалей на победу
             */
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field.length; j++) {
                    if (field[j][i] == c) count++;
                    if (count == countForWin) return true;
                }
                count = 0;
            }
            /**
             * Проверка диагонали на победу
             */
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field.length; j++) {
                    if (i == j && field[i][j] == c) count++;
                    if (count == countForWin) return true;
                }
                count = 0;
            }
            /**
             * Проверка другой диагонали на победу
             */
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field.length; j++) {
                    if (i + j == field.length - 1 && field[i][j] == c) count++;
                    if (count == countForWin) return true;
                }
                count = 0;
            }
            /**
             * Если ни одно из условий не выполнено, метод возвратит False
             */
            return false;
        }
        static int getCoordinates ( char c, char[][] field){
            int value;
            do {
                System.out.printf("Введите значение для %s-координаты %n", c);
                Scanner scanner = new Scanner(System.in);
                value = scanner.nextInt() - 1;
            } while (value > field.length - 1 || value < 0);
            return value;
        }
        static boolean isEmptyCell ( char[][] field, int h, int v){
            return field[h][v] == '-';
        }
        static boolean isNotEmptyCell ( char[][] field, int h, int v){
            return !isEmptyCell(field, h, v);
        }
        static boolean isDraw ( char[][] field){
            for (int h = 0; h < field.length; h++) {
                for (int v = 0; v < field.length; v++) {
                    if (isEmptyCell(field, h, v)) return false;
                }
            }
            return true;
        }
        static void doBlockHuman ( char[][] field, char playerChar, char AIChar){
            int count = 0;
            int countToBlock = 0;
            if (field.length == 3) countToBlock = 2;
            if (field.length == 5) countToBlock = 3;
            int horizontal, vertical;
            /**
             * Проверка горизонталей на опасность проигрыша
             */
            for (int h = 0; h < field.length; h++) {
                for (int v = 0; v < field.length; v++) {
                    if (field[h][v] == playerChar) count++;
                    if (count == countToBlock) {
                        for (int k = 0; k < field.length; k++) {
                            if (isEmptyCell(field, h, k)) {
                                field[h][k] = AIChar;
                                return;
                            }
                        }
                    }
                }
                count = 0;
            }
            horizontal = 0;
            /**
             * Проверка вертикалей на опасность проигрыша
             */
            for (int h = 0; h < field.length; h++) {
                for (int v = 0; v < field.length; v++) {
                    if (field[v][h] == playerChar) count++;
                    if (count == countToBlock) {
                        for (int k = 0; k < field.length; k++) {
                            if (isEmptyCell(field, k, h)) {
                                field[k][h] = AIChar;
                                return;
                            }
                        }
                    }
                }
                count = 0;
            }
            vertical = 0;
            /**
             * Проверка диагонали на опасность проигрыша
             */
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field.length; j++) {
                    if (i == j && field[i][j] == playerChar) count++;
                    if (count == countToBlock) {
                        for (int h = 0; h < field.length; h++) {
                            for (int v = 0; v < field.length; v++) {
                                if (h == v && isEmptyCell(field, h, v)) {
                                    field[h][v] = AIChar;
                                    return;
                                }
                            }
                        }
                    }
                }
            }
            count = 0;
            /**
             * Проверка другой диагонали на опасность проигрыша
             */
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field.length; j++) {
                    if (i + j == field.length - 1 && field[i][j] == playerChar) count++;
                    if (count == countToBlock) {
                        for (int h = 0; h < field.length; h++) {
                            for (int v = 0; v < field.length; v++) {
                                if (h + v == field.length - 1 && isEmptyCell(field, h, v)) {
                                    field[h][v] = AIChar;
                                    return;
                                }
                            }
                        }
                    }
                }
            }
            count = 0;
            doAITurn(field, AIChar);

        }
    }

