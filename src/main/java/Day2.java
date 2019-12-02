import java.io.*;
import java.util.*;
import java.net.*;

public class Day2 {
    public static void main(String[] args) throws IOException {
        Day2 main = new Day2();
        File file = main.getFileFromResources("day2-input.txt");
        List<Integer> input = new ArrayList<>();
        readFile(file, input);
        List<Integer> modifiedInput = new ArrayList<>(input);
        modifiedInput.set(1, 12);
        modifiedInput.set(2, 2);
        System.out.println("Part 1: " + getAnswer(modifiedInput));

        for (int i = 0; i <= 99; i++) {
            for (int j = 0; j <= 99; j++) {
                List<Integer> curr = new ArrayList<>(input);
                curr.set(1, i);
                curr.set(2, j);
                if (getAnswer(curr) == 19690720) {
                    System.out.println("Part 2: noun is " + i + " verb is " + j);
                }
            }
        }
    }

    private static int getAnswer(List<Integer> input) {
        int op = 0;
        while (op + 3 < input.size()) {
            int opcode = input.get(op), first = input.get(input.get(op + 1)),
                    second = input.get(input.get(op + 2)), resPos = input.get(op + 3);
            if (opcode == 1) {
                input.set(resPos, first + second);
            } else if (opcode == 2) {
                input.set(resPos, first * second);
            } else break;
            op += 4;
        }
        return input.get(0);
    }
    private File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }

    private static void readFile(File file, List<Integer> input) throws IOException {
        if (file == null) return;

        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                for (String num : line.split(",")) input.add(Integer.parseInt(num));
            }
        }
    }
}
