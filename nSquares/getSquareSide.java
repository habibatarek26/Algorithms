import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class getSquareSide {
long get_Square_Side(List<long[]>X)
{
    long size =X.size();
    if(size<=3)
    {
        if(size <= 1) return 0;
        if(size ==2)
            return get_square_side(X.get(0),X.get(1));
        long d = Math.min(get_square_side(X.get(0),X.get(1))
                        , get_square_side(X.get(0),X.get(2)));
        d= Math.min(d, get_square_side(X.get(1),X.get(2)));
        return d;
    }
    long l=get_Square_Side(X.subList(0,X.size()/2));
    long r=get_Square_Side(X.subList(X.size()/2,X.size()));
    long d = Math.min(l,r);

    List<long[]> pointsAroundMid = new ArrayList<>();
    long midX = X.get(X.size()/2)[0];
    for (long[] point : X) {
        if (Math.abs(point[0] - midX) < d) {
            pointsAroundMid.add(point);
        }
    }
    Collections.sort(pointsAroundMid, Comparator.comparing(point -> point[1]));
    long x = dOfPointsAcrossTheMid(pointsAroundMid,d);
    return Math.min(d,x);
}

    private long dOfPointsAcrossTheMid(List<long[]> Y, long delta) {

        for (int i = 0; i < Y.size(); i++) {
            for (int j = i + 1; j < Y.size()&&(j-i)<7 ; j++) {
                if ((Y.get(j)[1] - Y.get(i)[1]) >= delta) {
                    break;
                }
                delta = Math.min(delta, get_square_side(Y.get(i), Y.get(j)));
            }
        }
        return delta;
    }

    private long get_square_side(long[] firstP, long[]secondP)
{
    return  (long)Math.max(Math.abs(firstP[0]-secondP[0]),Math.abs(firstP[1]-secondP[1]));
}
}
