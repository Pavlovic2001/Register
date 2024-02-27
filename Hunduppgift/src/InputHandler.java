//Alexander alpa7946

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class InputHandler {
    private static final String PROMPT_ENDING = "?>";
    private static ArrayList <InputStream> list = new ArrayList<>();
    private Scanner input;

    public InputHandler()
    {
        this(System.in);
    }

    public InputHandler(InputStream is)
    {
        if(list.contains(is))
        {
            throw new IllegalStateException("fel");
        }

        else
        {
            list.add(is);
            input = new Scanner(is);
        }

    }

    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt + PROMPT_ENDING);
            if (input.hasNextInt()) {
                int number = input.nextInt();
                input.nextLine();
                return number;
            } else {
                System.out.println("Error: Please enter a valid number");
                input.nextLine();
            }
        }
    }

    public double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt + PROMPT_ENDING);
            if (input.hasNextDouble()) {
                double number = input.nextDouble();
                input.nextLine();
                return number;
            } else {
                System.out.println("Error: Please enter a valid number");
                input.nextLine();
            }
        }
    }

    public String readLine(String prompt) {
        while (true) {
            System.out.print(prompt + PROMPT_ENDING);
            String line = input.nextLine().trim();
            if (!line.isEmpty()) {
                return line.toLowerCase();
            } else {
                System.out.println("Error: the input can't be empty");
            }
        }
    }

    public void close() {
        if (input != null) {
            input.close();
        }
    }

}

