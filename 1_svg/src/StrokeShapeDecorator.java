import java.util.Locale;

public class StrokeShapeDecorator extends ShapeDecorator{
    private final String color;
    private final double width;

    public StrokeShapeDecorator(Shape decoratedShapes, String color, double width) {
        super(decoratedShapes);
        this.color = color;
        this.width = width;
    }

    @Override
    public String toSvg() {
        return this.decoratedShapes.toSvg().replace("/>",
                String.format(Locale.ENGLISH, " stroke=\"%s\" stroke-width=\"%f\" />",
                        this.color, this.width));
    }
}
