package online.hackpi.utils;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessCommand {
    public static void run() throws IOException, InterruptedException {
        try {
            // Command to start a command line process
            String command = "ls -l"; // Example command to list files in the current directory

            // Start the command line process
            ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s+"));
            Process process = processBuilder.start();

            // Read the output of the process
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            // Wait for the process to finish
            int exitCode = process.waitFor();
            System.out.println("Exited with error code " + exitCode);
            reader.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
