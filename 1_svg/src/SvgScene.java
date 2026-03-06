public class SvgScene {
    private Polygon[] polygons = new Polygon[3];
    private int index = 0;

    public void addPolygn(Polygon p){
        polygons[index] = p;
        index++;

        if(index==3){
            index = 0;
        }
    }

}
