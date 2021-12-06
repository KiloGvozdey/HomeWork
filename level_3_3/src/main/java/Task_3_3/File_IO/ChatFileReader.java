package Task_3_3.File_IO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.List;

public class ChatFileReader {
    private final String userName;

    public ChatFileReader(String userName){
            this.userName = userName;
    }

    public List<String> read(int position){
        StringBuilder stringBuilder = new StringBuilder("");
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(String.format("Chat_(%s).txt", userName), "r")){
            randomAccessFile.seek(position);
            int i = randomAccessFile.read();
            while (i != -1){
                stringBuilder.append((char) i);
                i = randomAccessFile.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Arrays.asList(stringBuilder.toString().split("\n"));
    }

    public int getStartPosition(){
        int count = 0;
        int startPos;
        int startPosChar = 0;
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(String.format("Chat_(%s).txt", userName), "r")){
            randomAccessFile.seek(0);
           int i = randomAccessFile.read();
            while (i != -1){
                if((char) i == '\n') count++;
                i = randomAccessFile.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(count < 100){
            return startPosChar;
        } else {
            startPos = count - 100;
            count = 0;

            try (RandomAccessFile randomAccessFile = new RandomAccessFile(String.format("Chat_(%s).txt", userName), "r")){
                randomAccessFile.seek(0);
                int i = randomAccessFile.read();
                while (i != -1){
                    startPosChar++;
                    if((char) i == '\n') ++count;
                    i = randomAccessFile.read();
                    if(count > startPos) break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(startPosChar);
            return startPosChar;
        }

    }
}
