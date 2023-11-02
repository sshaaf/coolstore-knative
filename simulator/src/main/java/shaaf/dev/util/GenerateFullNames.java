package shaaf.dev.util;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@RegisterForReflection
public class GenerateFullNames {

    private static Map<Integer, String> fNames = null;

    private static Map<Integer, String> lNames = null;


    public GenerateFullNames(String fNameFile, String lNameFile) {

        // Read the values from file and add them into a static Map.
        // Idea is to not read the file everytime.
        fNames = Collections.unmodifiableMap(getMapFromFile(fNameFile));
        lNames = Collections.unmodifiableMap(getMapFromFile(lNameFile));
    }

    public Map<Integer, String> getMapFromFile(String fileName) {
        Map<Integer, String> temp = new HashMap<>();
        int count = 0;

        try (InputStream inputStream = getClass().getResourceAsStream(fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            for (String line; (line = br.readLine()) != null; ) {
                temp.put(count, line);
                count++;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }



    public String getNextLastName() {
        return lNames.get(new Random().nextInt(lNames.size()));
    }

    public String getNextFirstName() {
        return fNames.get(new Random().nextInt(fNames.size()));
    }


    public String getNextFullName() {
        return getNextFirstName() + " " + getNextLastName();
    }

}
