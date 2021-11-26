package Task_3_3.File_IO;

import java.io.*;
import java.util.ArrayList;

public class ChatFileReader {
    private final File file;

    private final String userName;
    private final ArrayList<String> strings = new ArrayList<>();
    public ChatFileReader(String userName) {
        this.file = new File(String.format("Chat_(%s).txt", userName));
        this.userName = userName;
    }

    public ArrayList<String> read(){
        try(BufferedReader reader = new BufferedReader(new FileReader(this.file))){
            while (reader.ready()){
                strings.add(reader.readLine());
            }
            return strings;
        } catch (IOException e){
            throw new RuntimeException("SWW during a writing in file");
        }
    }

    public File getFile() {
        return file;
    }
}
