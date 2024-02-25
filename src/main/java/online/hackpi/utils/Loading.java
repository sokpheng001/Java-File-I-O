package online.hackpi.utils;

public class Loading {
    public static void Animation(){
        // Define the loading animation characters
        char[] animationChars = new char[]{'+', '-', '*', '/','o','\\'};

        // Define the number of iterations for the animation
        int numIterations = 20;

        // Display the loading animation
        for (int i = 0; i < numIterations; i++) {
            // Get the animation character based on the current iteration
            char animationChar = animationChars[i % 4];
            // Print the loading animation
            System.out.print("\rLoading " + animationChar);
        }
        System.out.println();

        // Print a completion message after the animation is finished
        System.out.println("\rLoading complete!");
    }
}
