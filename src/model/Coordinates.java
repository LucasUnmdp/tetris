package model;

public class Coordinates {
    private int i;
    private int j;

    public Coordinates(int i, int j){
        this.i=i;
        this.j=j;
    }

    public void sum(int i,int j){
        this.i+=i;
        this.j+=j;
    }

    public void sub(int i, int j){
        this.i-=i;
        this.j-=j;
    }
    public void setCoords(int i, int j){
        this.i=i;
        this.j=j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public boolean same(){
        return i==j;
    }

    @Override
    public boolean equals(Object obj) {
        boolean flag = true;
        Coordinates o = (Coordinates)obj;
        if (o.getI()!= i || o.getJ()!=j)
            flag=false;
        return flag;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }

    public void swap(){
        int x=i;
        i=j;
        j=x;
    }
}
