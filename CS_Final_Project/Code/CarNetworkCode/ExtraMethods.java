import java.util.*;
import java.util.Collections;
public class ExtraMethods{
    public static void main(String args[]){
        
        
        //sortAddAsc test code
        ArrayList<LineSegment> als = new ArrayList<LineSegment>();
        als.add(new LineSegment(new Point(1,1),new Point(5,1)));
        als.add(new LineSegment(new Point(6,1),new Point(8,1)));
        System.out.println(parseSpeed(6.5,als));
        
        
        
        
        
    }
    /*public static void sortAddAsc(Comparable add, ArrayList<Comparable> list){ //sorted add ascengind
        if(list.size()!=0){
        int ind = recurse(add,list,0,list.size()-1);
        
        int index=ind;
        if(ind>=list.size()){
            list.add(add);
        }
        else{
            while(add.compareTo(list.get(index))==0){
                if(index>=list.size()-1){
                    ind= list.size();
                    break;
                }
                else{
                    index++;
                }
            }
            ind = index;
        
        
            if(index>=list.size()){
                list.add(add);
            }
            else{
                list.add(index,add);
            }
        }
                    }
        else{
            list.add(add);
        }
        
        

    }*/
    
    public static void printAL(ArrayList ls){
        for(int i=0;i<ls.size();i++){
            System.out.println("index: "+i+",  "+ls.get(i));
        }
    }
    
    public static void printLSarray(ArrayList<LineSegment> ls){
        for(int i=0;i<ls.size();i++){
               if(i==ls.size()-1){
                   System.out.println(ls.get(i));
               }
               else{
            System.out.print(ls.get(i)+", ");}
        }
    }
    
    public static String getLSarrayString(ArrayList<LineSegment> ls){
        String str="";
        for(int i=0;i<ls.size();i++){
               if(i==ls.size()-1){
                   str+=(ls.get(i));
               }
               else{
            str+=(ls.get(i)+", ");}
        }
        return str;
    }
    
    public static double parseSpeed(double time, ArrayList<LineSegment> ls){
        for(int i=0;i<ls.size();i++){
            //System.out.println(ls.get(i).range()+" in "+time);   
            if(ls.get(i).getPointByX(time)!=null){
                return ls.get(i).getSlope();
            }

        }
        return Double.NaN;
    }
    public static double parseLoc(double time, ArrayList<LineSegment> ls){//, boolean tru){//tru used for testing purposes
        //System.out.println("$#@");
        for(int i=0;i<ls.size();i++){
            //if(tru){
             //System.out.println(ls.get(i).range()+" in "+time);   
            //}
            if(ls.get(i).getPointByX(time)!=null){
                //System.out.println(ls.get(i).getPointByX(time).getY());
                return (ls.get(i).getPointByX(time).getY());
            }

        }
        return Double.NaN;
    }
    
    private static int recurse(Comparable add, ArrayList list, int left, int right){ //used in sortAddAsc above
        int mid = left + (right - left)/2;
        
        if(add.compareTo(list.get(mid))==1){
            if((right-mid)<=1){
                if(add.compareTo(list.get(right))==1){
                    return right+1;
                }
                else{
                return right;}
            }
            else{
            return recurse(add,list,mid,right);}
        }
        else if(add.compareTo(list.get(mid))==0){
            return mid;
            
        }
        else if(add.compareTo(list.get(mid))==-1){
            if((mid-left)<=1){
                if(add.compareTo(list.get(left))==-1){
                    return left;}
                else{
                    return mid;
                }
            }
            else{
            return recurse(add,list,left,mid);}
        }
        else{
            return -1;}
        
    }
    

}