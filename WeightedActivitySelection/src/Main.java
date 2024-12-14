import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        WeightedActivitySelection a = new WeightedActivitySelection();
        int [][]activities =a.readActivitiesFromFile(args[0]);
        a.writeOutputToFile(args[0], Integer.toString(a.selectActivities(activities)), "21010445");
    }
}