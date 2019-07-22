import java.io.BufferedWriter;
import java.io.FileWriter;

public class GuardingTime {

    public static void main(String[] args) {

        for (int i=1; i<=10; i++) {

            System.out.println("Scenario " + i);

            String inputFile = String.valueOf(i)+".in";
            String outputFile = String.valueOf(i)+".out";

            Safeguards guards = new Safeguards(inputFile);

            int finalCoverageTime = guards.CalculateMaxTime();

            System.out.println("The final coverage Time after layoff is: " + finalCoverageTime);

            output(outputFile,finalCoverageTime);
        }

    }

    private static void output(String fileName, int number) {
        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(String.valueOf(number));

            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("Exception while writing number to file");
        }
    }
}
