package eventloggerapp.xml;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    private String fileName;
    private File file;

    public FileEventLogger(String fileName){
        this.fileName = fileName;
    }

    public void init() throws IOException{
        this.file = new File(fileName);
        if(!file.canWrite()){
            throw new IOException("You have not a permission to log events in this file");
        }
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "FileEventLogger " +
                "fileName='" + fileName + '\'' +
                ", file=" + file +
                "\n";
    }
}
