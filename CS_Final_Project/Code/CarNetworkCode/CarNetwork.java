
public class CarNetwork {//// ccc ensure no change of direction, heirarchy of routes, new cars putting in request in heirarchy, destinations for different cars on same path, null pointer instead of printing unsolvable
	
	public static Grid myGrid;
	public static void main(String args[]) {
		myGrid = new Grid();
        // 38 mm
        //8.5 mm
        // 25 mm
        for(int i=0; i<4; i++){
            double inc=i*(8.5+25);
            new Path(new Intersection(new Location(myGrid,20,20+inc )),new Intersection(new Location(myGrid,(20+38.5+38.5+8.5+8.5),20+inc)),5);
        }
        for(int i=0; i<3; i++){
            double inc=i*(8.5+38.5);
            new Path(new Intersection(new Location(myGrid,20+inc,20)),new Intersection(new Location(myGrid,20+inc,20+3*(8.5+25))),5);
        }
		/*for(int i=50; i<750; i+=70){
		Path s=new Path(new Intersection(new Location(myGrid,i,50)),new Intersection(new Location(myGrid,i,750)),1);
        Path t=new Path(new Intersection(new Location(myGrid,49,i)),new Intersection(new Location(myGrid,750,i)),1);
        }*/
		
		/*Path q=new Path(new Intersection(new Location(myGrid,40,40)),new Intersection(new Location(myGrid,700,40)),2);
		Path u=new Path(new Intersection(new Location(myGrid,40,700)),new Intersection(new Location(myGrid,700,700)),1);
		Path p=new Path(new Intersection(new Location(myGrid,40,40)),new Intersection(new Location(myGrid,40,700)),2);
		Path r=new Path(new Intersection(new Location(myGrid,700,40)),new Intersection(new Location(myGrid,700,700)),4);
        Path rasdf=new Path(new Intersection(new Location(myGrid,30,0)),new Intersection(new Location(myGrid,800,800)),4);
		Path pp=new Path(new Intersection(new Location(myGrid,50,400)),new Intersection(new Location(myGrid,750,430)),1);
		Path rr=new Path(new Intersection(new Location(myGrid,200,0)),new Intersection(new Location(myGrid,800,800)),1);
		Path rrr=new Path(new Intersection(new Location(myGrid,0,300)),new Intersection(new Location(myGrid,750,300)),1);
        Path xx=new Path(new Intersection(new Location(myGrid,200,0)),new Intersection(new Location(myGrid,800,550)),3);
		Path xxx=new Path(new Intersection(new Location(myGrid,0,300)),new Intersection(new Location(myGrid,750,0)),1);
        Path qa=new Path(new Intersection(new Location(myGrid,240,200)),new Intersection(new Location(myGrid,300,100)),1);
		Path ua=new Path(new Intersection(new Location(myGrid,80,20)),new Intersection(new Location(myGrid,260,700)),1);
        Path pa=new Path(new Intersection(new Location(myGrid,700,700)),new Intersection(new Location(myGrid,550,0)),1);
        Path qaa=new Path(new Intersection(new Location(myGrid,640,200)),new Intersection(new Location(myGrid,300,300)),5);
		Path uaa=new Path(new Intersection(new Location(myGrid,60,20)),new Intersection(new Location(myGrid,760,700)),1);
        Path paa=new Path(new Intersection(new Location(myGrid,200,700)),new Intersection(new Location(myGrid,550,400)),1);*/
		/**/
        
        myGrid.setup();
		Location l=new Location(myGrid,800,0);
        Location l2=new Location(myGrid,200,490);
		Location d=new Location(myGrid,0,800);
		Location d2=new Location(myGrid,0,0);
        Location ea=new Location(myGrid,600,490);
        Location ea2=new Location(myGrid,0,490);
		Car c=new Car(l,d);
		//Car g=new Car(l2,d2);
        //Car e=new Car(ea,ea2);
        
		while(true){
            
		myGrid.update();
		}
	}
	
}
