package util;

import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {

    protected static final Logger logger = Logger.getLogger(WriteToFile.class);


    public void writeStr(String str) {
        try {
            FileWriter fw = new FileWriter("src/main/resources/outputfile.txt",true);
            fw.write(str);
            fw.write("\n");
            fw.close();
            logger.info("Successfully wrote to the file.");
        } catch (IOException e) {
            logger.error("An error occurred while writing to the file", e);
        }
    }
}

