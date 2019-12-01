import java.io.*;
import java.util.*;
import java.net.*;

public class Day1 {
    public static void main(String[] args) throws IOException {
        Day1 main = new Day1();
        File file = main.getFileFromResources("day1-input.txt");
        int ans = 0;
        List<Integer> input = new LinkedList<>();
        readFile(file, input);
        for (int i : input) {
            ans += ((i / 3) - 2);
        }
        System.out.println("Part 1: " + ans);
        ans = 0;
        Map<Integer, Integer> dp = new HashMap<>();
        for (int i : input) {
            int res = dfs(i, dp);
            ans += res;
        }
        System.out.println("Part 2: " + ans);
    }

    private static int dfs(int i, Map<Integer, Integer> dp) {
        int ans = 0;
        if (dp.containsKey(i)) return dp.get(i);
        else if (i <= 0) return 0;
        else {
            int res = i / 3 - 2;
            if (res > 0) {
                ans += res;
                ans += dfs(res, dp);
            }
        }
        dp.put(i, ans);

        return ans;
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
                input.add(Integer.parseInt(line));
            }
        }
    }
}
