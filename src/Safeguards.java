import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

/*
Safeguards stores the shifts data for all safe guards
and provides the method to calculate max coverage time after firing one minimum impacting guard.

First quickSort all the shifts by the values of start points.
Then, calculate the total coverage time and the number of "single covered time points" of each guard.
(By "single covered time points"", I mean time points, which are covered by only one person.)
At last, calculate the final result by minus the minimum number of single coverage time points of one guard from Total coverage time.
 */
public class Safeguards {

    private int[] starts;
    private int[] ends;
    private int numberofGuards;

    //construct safeguards by the shifts data from inputFile
    //initialize the properties accordingly
    public Safeguards(String inputFile) {
        try {
            FileReader fr = new FileReader(inputFile);
            BufferedReader br = new BufferedReader(fr);

            numberofGuards = Integer.parseInt(br.readLine());

            starts = new int[numberofGuards];
            ends = new int[numberofGuards];

            String guardsShift = br.readLine();
            int i = 0;

            //read guards' shift points from the file to Array
            while(guardsShift!=null) {
                String[] shiftPoints = guardsShift.split(" ");
                int shiftStartPoint = Integer.parseInt((shiftPoints[0]));
                int shiftEndPoint = Integer.parseInt((shiftPoints[1]));

                starts[i] = shiftStartPoint;
                ends[i++] = shiftEndPoint;

                guardsShift = br.readLine();
            }

            br.close();
            fr.close();

        } catch (Exception e) {
            System.out.println("Exception while parsing inputFile: " + e);
        }
    }

    public int[] getStarts() {
        return starts;
    }

    public int[] getEnds() {
        return ends;
    }

    public int getNumberofGuards() {
        return numberofGuards;
    }


    //Sort shifts based on the value of start points
    public void quickSortShift(int first, int last) {

        if (last - first < 2)
            return;

        int pivotStart;
        int pivotEnd;

        pivotStart = starts[first];
        pivotEnd = ends[first];

        int i = first;
        int j = last;

        while (i < j) {

            while(i < j && starts[--j] >= pivotStart);
            if (i < j) {
                starts[i] = starts[j];
                ends[i] = ends[j];
            }

            while(i < j && starts[++i] <= pivotStart);
            if (i < j) {
                starts[j] = starts[i];
                ends[j] = ends[i];
            }
        }

        starts[j] = pivotStart;
        ends[j] = pivotEnd;

        quickSortShift(first,i);
        quickSortShift(i+1,last);
    }

    //Calcalate the maximum coverage time after lay off the minimum impacting safeguard
    public int CalculateMaxTime() {

        quickSortShift(0,numberofGuards);

//        for (int i=0; i<numberofGuards; i++)
//            System.out.println(starts[i] + " - " + ends[i]);

        //Total time covered by all safeGuards before layoff
        int totalTime = ends[0] - starts[0];

        //the minimal time slot, indicating the minimum impacting safeguard
        int  minSingleSlot = totalTime;

        //how many time points are covered by the guard, himself, one person
        int[] singlePointsNumber = new int[numberofGuards];
        singlePointsNumber[0] = totalTime;

        //calculate the totalTime and singlePointsNumber[]
        int j = 0;
        for (int i = 1; i<numberofGuards; ++i) {

            if (starts[i] >= ends[j]) {
                totalTime += (ends[i] - starts[i]);
                //single covered points number for guard[j] is unchanged

                //single covered points number covered by guard[i]
                singlePointsNumber[i] = ends[i] - starts[i];
                j = i;
            } else {
                if (ends[i] <= ends[j])
                    singlePointsNumber[i] = 0;
                else {
                    totalTime += ends[i] - ends[j];
                    //single points number for guard[j] is changed
                    singlePointsNumber[j] = singlePointsNumber[j] - (ends[j] - starts[i]);
                    singlePointsNumber[i] = ends[i] - ends[j];
                    j = i;
                }
            }
        }

        //find out the minimum impacting safeGuard
        for (int i=0; i<numberofGuards; i++) {

//            System.out.println(singlePointsNumber[i]);

            if (singlePointsNumber[i] <=0) {
                minSingleSlot = 0;
                break;
            } else {
                if (singlePointsNumber[i] < minSingleSlot)
                    minSingleSlot = singlePointsNumber[i];
            }
        }

        System.out.println("The Minimum SingleSlot is: " + minSingleSlot);
        System.out.println("The Total Time is: " + totalTime);

        return totalTime - minSingleSlot;
    }
}
