import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class getSquareSide {
float get_Square_Side(List<int[]>X)
{
    int size =X.size();
    if(size<=3)
    {
        if(size <= 1) return 0;
        if(size ==2)
            return getDistance(X.get(0),X.get(1));
        float d = Math.min(getDistance(X.get(0),X.get(1))
                        , getDistance(X.get(0),X.get(2)));
        d= Math.min(d, getDistance(X.get(1),X.get(2)));
        return d;
    }
    float l=get_Square_Side(X.subList(0,X.size()/2));
    float r=get_Square_Side(X.subList(X.size()/2,X.size()));
    float d = Math.min(l,r);

     List<int[]> pointsAroundMid = new ArrayList<>();
    int midX = X.get(X.size()/2)[0];
    for (int[] point : X) {
        if (Math.abs(point[0] - midX) < d) {
            pointsAroundMid.add(point);
        }
    }
    Collections.sort(pointsAroundMid, Comparator.comparingInt(point -> point[1]));
    float x = dOfPointsAcrossTheMid(pointsAroundMid,d);
    return Math.min(d,x);
}

    private float dOfPointsAcrossTheMid(List<int[]> Y, float delta) {

        for (int i = 0; i < Y.size(); i++) {
            for (int j = i + 1; j < Y.size()&&(j-i)<7 ; j++) {
                if ((Y.get(j)[1] - Y.get(i)[1]) >= delta) {
                    break;
                }
                delta = Math.min(delta, getDistance(Y.get(i), Y.get(j)));
            }
        }
        return delta;
    }

    private float getDistance(int[] firstP, int[]secondP)
{
    return (float) Math.sqrt(
            Math.pow((firstP[0]-secondP[0]),2)
          + Math.pow((firstP[1]-secondP[1]),2)
                          );
}
}
