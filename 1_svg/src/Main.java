public class Main {
    public static void main(String[] args){
        System.out.println("Hello word");
        Point p = new Point();
        p.x = 50F;
        p.y = 70.5F;

        System.out.println(p.toSVG());
    }
}