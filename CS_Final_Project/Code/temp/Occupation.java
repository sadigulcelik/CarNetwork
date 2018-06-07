import java.util.*;
import java.util.Collections;
public class Occupation{
    
    public static void main(String[] args){
        ArrayList<LineSegment> ls = new ArrayList<LineSegment>();
        ls.add(new LineSegment(new Point(-1,0), new Point(3,5)));
        ls.add(new LineSegment(new Point(3,5), new Point(8,7)));
        ls.add(new LineSegment(new Point(8,7), new Point(20,10)));
        ls.add(new LineSegment(new Point(19,9.4), new Point(24,9.4)));
        
        ExtraMethods.printAL(ls);
        /*ls.add(new LineSegment(new Point(-1,10), new Point(3,5)));
        ls.add(new LineSegment(new Point(3,5), new Point(8,3)));
        ls.add(new LineSegment(new Point(8,3), new Point(20,0)));*/
        
        
        Occupation occ = new Occupation(ls);
        
        Occupation next = new Occupation(new Path(10),occ,0,true,2,new Car(0.5));
        ExtraMethods.printAL(next.getLS());
        System.out.println(next.getEndTime());

    }
    
    //vvv below variables are for continuous occupation
    private double speed;
    private boolean direction;
    private double beginTime;
    private double endTime;
    //^^^
    
    private boolean continuous;
    private Path myPath;
    private ArrayList<LineSegment> lineSegs;
    
    public Occupation(){
        continuous=false;
        lineSegs=new ArrayList<LineSegment>();
    }
    public Occupation(ArrayList<LineSegment> ls){
        continuous=false;
        lineSegs=new ArrayList<LineSegment>(ls);
    }
    
    public void add(Occupation addend){
        for(LineSegment l: addend.getLS()){
            ExtraMethods.sortAddAsc(l,this.lineSegs);
        }
    }
    
    public Occupation(Path p,Occupation existingOcc,double enterTime,Boolean dir,double sp, Car c){ //continuous occupation constructor from Path getTime
        
        continuous=true;
        ArrayList<LineSegment>EOlineSegs=new ArrayList<LineSegment>(existingOcc.getLS());
        this.beginTime=enterTime;
        this.direction=dir;
        this.myPath=p;
        this.speed=sp;
        this.lineSegs=new ArrayList<LineSegment>();
        Point start;
        Point end;
        
        if(dir){
            start=new Point(enterTime,0);
                end = new Point(enterTime+p.getDistance()/sp,p.getDistance());
        }
        else{
            start=new Point(enterTime,p.getDistance());
            end = new Point(enterTime+p.getDistance()/sp,0);
        }
        LineSegment curSeg=new LineSegment(start,end);
        if(EOlineSegs.size()==0){//if there are no existing occupations
        this.lineSegs.add(curSeg);
        //System.out.println("hey"+curSeg);
        endTime=end.getX();
        }
        else{
  //there are existing occupations
        Point curStart=new Point(start);
        boolean keep=true;
            Line finishLine;
            if(dir){
                finishLine = new Line(new Point(enterTime,p.getDistance()),0);}
            else{
                finishLine = new Line(new Point(enterTime,0),0);
            }

            boolean endOfFollow=false;
            Line temp;
            boolean segReachedEnd=false;
            
        while(keep){
            LineSegment collider=curSeg.first(EOlineSegs);
            //System.out.println("hey");
            if (collider==null){
                if(segReachedEnd){
                    lineSegs.add(curSeg);
                    endTime=end.getX();
                    keep=false;
                    break;
                }
                else if(endOfFollow){
                    lineSegs.add(curSeg);
                    double slop=sp;
                    if(!dir){
                        slop*=-1;
                    }
                    Line tempest=new Line(curSeg.rightEndPoint(),slop);
                    end=tempest.getIntersection(finishLine);
                    curSeg=new LineSegment(curSeg.rightEndPoint(),end);
                    
                    
                    endOfFollow=false;
                    segReachedEnd=true;
                    
                }
            }
            else{
                segReachedEnd=false;
                //System.out.println(c.getTD());
                if((collider.isOppositeSlope(curSeg))){
                    this.endTime=Double.POSITIVE_INFINITY;
                    keep=false;
                }
                else if(curSeg.getSlope()<0&&collider.getSlope()<curSeg.getSlope()){
                    this.endTime=Double.POSITIVE_INFINITY;
                    keep=false;
                }
                else if(curSeg.getSlope()>0&&collider.getSlope()>curSeg.getSlope()){
                    this.endTime=Double.POSITIVE_INFINITY;
                    keep=false;
                }
                else{
                    
                    Point collision=collider.getIntersection(curSeg);
                    Point holder=curSeg.endPointMinus(c.getTD(), collider);

                    lineSegs.add(new LineSegment(curStart,holder));
                    curStart=holder;
                           temp = new Line(curStart,collider.getSlope());
                        end=temp.getPointByX(collider.rightEndPoint().getX());//finishLine.getIntersection(temp);
                    curSeg=new LineSegment(curStart,end);

                    endOfFollow=true;
                    
                    
                    //System.out.println("hey"+curSeg);
                       }
                    
                    
                
            }
            
        }
        
        }
        
    }//ADD CHECKING IF COLLISION IS RESOLVED SO U CAN GO UP
    
        
    public double getEndTime(){
        return this.endTime;
    }
    
    public ArrayList<LineSegment> getLS(){
        
        return this.lineSegs;
    }
    
    
}