public class Point{
    private double x;
    private double y;
    Point(double xIn, double yIn){
        this.x=xIn;
        this.y=yIn;
    }
    public double slope(Point p){
        double m=0;
        double dY=p.getY()-this.y;
        double dX=p.getX()-this.x;
        if(dX==0){
            if(dY==0){
                return Double.POSITIVE_INFINITY;
            }
            if(dY>0){
                return Double.POSITIVE_INFINITY;
            }
            if(dY<0){
                return Double.NEGATIVE_INFINITY;
            }
        }
            return dY/dX;
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public String toString(){
        String xs=Double.toString((this.x));
        String ys=Double.toString((this.y));
        return ("("+xs+", "+ys+")");
    }
}

//m=double.MAX_VALUE
//m=y/x
//x=y/m