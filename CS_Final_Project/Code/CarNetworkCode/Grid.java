import java.util.*;
import java.awt.Color;
import java.util.Date;


public class Grid {
	
    
	private ArrayList<Car> myCars;
	private ArrayList<Intersection> myIntersections;
	private ArrayList<Path> myPaths;
	private ArrayList<Path> removePaths;
	private ArrayList<Intersection> removeIntersections;
	private double clock; // this will reset after a new set of instructions are generated and will be used for all cars to reference
	
	private ArrayList<Car> deleteQueue;
	private ArrayList<Path> delayQueue;
    private long janTime;//in ms since 1970 12am January 1
    private double time;//in seconds
    private int SadiTime=0;//in frame units

	public Grid(){
        this.janTime=System.currentTimeMillis();
        this.time=(double)(System.currentTimeMillis()-janTime)/1000.0;
		StdDraw.setCanvasSize(700,700);
		StdDraw.setXscale(0,150);
        StdDraw.setYscale(0,150);
		StdDraw.enableDoubleBuffering();
		this.myCars = new ArrayList<Car>();
		this.deleteQueue = new ArrayList<Car>();
		this.myIntersections = new ArrayList<Intersection>();
		this.myPaths = new ArrayList<Path>();
		this.delayQueue=new ArrayList<Path>();
		this.removePaths = new ArrayList<Path>();
		this.removeIntersections=new ArrayList<Intersection>();
	}
	
	public void setup() {
        
        Collections.sort(myCars);
    //time=System.nanoTime();
	this.time=(double)(System.currentTimeMillis()-janTime)/1000.0;
        
	for(int i=0;i<myPaths.size();i++) {
			myPaths.get(i).intersects();
		 //This is the update loop like in FishTank
	}
	
	for(int i=delayQueue.size()-1;i>=0;i--) {
		myPaths.add(delayQueue.get(i));
		delayQueue.remove(delayQueue.get(i));
}
	
	for(int i=removePaths.size()-1;i>=0;i--) {
		Path r=removePaths.get(i);
		myPaths.remove(r);
		r.getEnd().removePath(r);
		r.getStart().removePath(r);
		removePaths.remove(r);
	
	 //This is the update loop like in FishTank
}
        
	for(int i=myIntersections.size()-1;i>=0;i--) {
		for (int j=0;j<i;j++) {
			myIntersections.get(i).tryToEat(myIntersections.get(j));
		}
	}
	this.removeDelayedInt();
    /*for(Path p: myPaths) {
        p.show();
		System.out.println((Location)(p.getStart())+" , "+(Location)(p.getEnd()));
	}
        StdDraw.show();
	while(System.currentTimeMillis()>0){}*/
        
}
	public void removeDelayedInt() {
		
		for(int i=removeIntersections.size()-1;i>=0;i--) {
			myIntersections.remove(removeIntersections.get(i));
			this.removeIntersections.remove(i);
		}
	}
    
    
	public void update() {
         
        Collections.sort(myCars);
        this.time=(double)(System.currentTimeMillis()-janTime)/1000.0;
        
        
            
		this.show();
        
		for(int i=myPaths.size()-1;i>=0;i--) {
		  myPaths.get(i).update();
		}
        System.out.println("pathsize: "+myPaths.size());
        //StdDraw.show();
		for(int i=myIntersections.size()-1;i>=0;i--) {
			myIntersections.get(i).update();
			//System.out.println(myIntersections.get(i).nodeValue());
			
			 //This is the update loop like in FishTank
		}
		StdDraw.setPenRadius();
        
		for(int i=myCars.size()-1;i>=0;i--) {
            
			myCars.get(i).update();
			 //This is the update loop like in FishTank
		}
        
		/*for(int aa=0; aa<10000;aa++) {
			for (int bb=0;bb<5000;bb++) {int c=bb+aa;c=c*c;StdDraw.setPenColor(0,0,0);}
		}*/
		StdDraw.show();
	}
    
    public double getTime(){
        return this.time;
    }
    
    
	/*public void update2() {
		this.show();
		for(int i=myPaths.size()-1;i>=0;i--) {
		myPaths.get(i).update();
		}
		for(int i=myIntersections.size()-1;i>=0;i--) {
			myIntersections.get(i).update();
			//System.out.println(myIntersections.get(i).nodeValue());
			
			 //This is the update loop like in FishTank
		}
		StdDraw.setPenRadius();
		//for(int aa=0; aa<10000;aa++) {
			//for (int bb=0;bb<5000;bb++) {int c=bb+aa;c=c*c;StdDraw.setPenColor(0,0,0);}
		//}
		StdDraw.show();
	}*/
	public void intersectionUpdate(){
        
        

	for(int i=myIntersections.size()-1;i>=0;i--) {
		for (int j=0;j<i;j++) {
			myIntersections.get(j).tryToEat(myIntersections.get(i));
		}
	}
        
        for(int i=myPaths.size()-1;i>=0;i--) {
		myPaths.get(i).update(false);
		}
        this.removeDelayedInt();
    }
	public void show() {
		StdDraw.setPenColor(new Color(255,0,0));
		StdDraw.filledRectangle(500,500,500,500);
	}
	
    
	
	public void addCar(Car c) {
		this.myCars.add(c);
	}
	public void addPath(Path p) {
		this.myPaths.add(p);
	}
	public void delayAddPath(Path p) {
		this.delayQueue.add(p);
	}
	public void addIntersection(Intersection i) {
		this.myIntersections.add(i);
	}
	
	public void removeCar(Car c) {
		this.myCars.remove(c);
	}
	public void removePath(Path p) {
		this.myPaths.remove(p);
	}
	public void delayRemovePath(Path p) {
		this.removePaths.add(p);
		/*myPaths.remove(p);
		p.getEnd().removePath(p);
		p.getStart().removePath(p);
		removePaths.remove(p);*/
	}
	public void delayRemoveIntersection(Intersection i) {
		this.removeIntersections.add(i);
	}
	public void removeIntersection(Intersection i) {
		this.myIntersections.remove(i);
	}
	
	public void requestRoute(Car c, Intersection start, Intersection end) {
		//ArrayList<Path> cRoute = c.getOptimalPath();
		//check for problems with this path and modify this and other paths as necessary
	}
	
	public void collisionCheck() {
		
	}
	

	
	public ArrayList<Path> getMyPaths() {
		return this.myPaths;
	}
	public ArrayList<Car> getMyCars(){
		return this.myCars;
	}
	public ArrayList<Intersection> getMyIntersections(){
		return this.myIntersections;
	}
}
