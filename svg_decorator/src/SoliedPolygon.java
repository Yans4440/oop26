public class SoliedPolygon extends  Polygon{
    private String color;

    public SoliedPolygon(Vec2[] points, String color) {
        super(points);
        this.color = color;
    }

    @Override
    public String toSvg() {
        return super.toSvg().replace("/>",
                String.format("fill=\"%s\"/>",color));
    }



}
