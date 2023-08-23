import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        String inputFilePath = "/Users/meilyusuf2/workspace/input/alert.txt";

        try {
            Map<String, StringBuilder> messagesByBank = new HashMap<>();
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    String bank = parts[0];
                    String envi = parts[1];
                    String port = parts[2];
                    String description = parts[3];

                    StringBuilder messageBuilder = messagesByBank.getOrDefault(bank, new StringBuilder());
                    messageBuilder.append("-Envi ").append(envi).append(" Port ").append(port).append(" terpantau ").append(description).append("\n");
                    messagesByBank.put(bank, messageBuilder);
                }
            }

            for (Map.Entry<String, StringBuilder> entry : messagesByBank.entrySet()) {
                String bank = entry.getKey();
                StringBuilder messageBuilder = entry.getValue();

                String message = "Selamat Siang Rekan " + bank + ",\n\n" +
                        "Mohon bantuan untuk Sign on pada envi berikut:\n\n" +
                        messageBuilder.toString() + "\n";

                System.out.println(message);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}