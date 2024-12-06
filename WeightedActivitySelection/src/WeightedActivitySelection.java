import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class WeightedActivitySelection {

    //activity [0] start time
    //activity [1] end time
    //activity[2] weight of activity
    public int  selectActivities(int[][] activities)
    {
        sortActivities(activities);
        int[]dp = new int[activities.length+1];
        for(int i=0; i<activities.length; i++)
        {
            int LatestNonConflictActivity =LatestNonConflictActivity(activities,i);
            dp[i+1] = Math.max(dp[i],dp[LatestNonConflictActivity+1]+activities[i][2]);
        }
        return dp[activities.length];
    }

    private void sortActivities(int[][] activities)
    {
        Arrays.sort(activities, Comparator.comparingInt(activity -> activity[1]));
    }

    private int LatestNonConflictActivity(int[][] activities,int indexOfJob)
    {
        //use binary search to make its complexity O(nlgn) rather than lg(n^2)
        int start =0;
        int end = indexOfJob;
        int mid = 0;
        int startOfJob = activities[indexOfJob][0];
        while(start<=end)
        {
            mid = (start+end)/2;
            if(activities[mid][1]<= startOfJob) {
                if (mid == indexOfJob - 1 || activities[mid + 1][1] > startOfJob)
                    return mid;
                start = mid + 1;
            }
            else if(activities[mid][1]> startOfJob)
                end=mid-1;
        }
        return -1;
    }

}
