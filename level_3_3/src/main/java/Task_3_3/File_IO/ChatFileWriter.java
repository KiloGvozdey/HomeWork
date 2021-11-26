package Task_3_3.File_IO;

import java.io.*;

public class ChatFileWriter {
    private final File file;
    private final String userName;

    public ChatFileWriter(String userName) {
        this.file = new File(String.format("Chat_(%s).txt", userName));
        this.userName = userName;
    }

    public void write(String message){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(this.file, true))){
            writer.write(message);
        } catch (IOException e){
            throw new RuntimeException("SWW during a writing in file");
        }
    }

    public File getFile() {
        return file;
    }
}
