public class SolidFillShapeDecoratr extends ShapeDecorator{

    private String color;

    public SolidFillShapeDecoratr(Shape decoratedShapes, String color) {
        super(decoratedShapes);
        this.color = color;
    }

    @Override
    public String toSvg() {
        return decoratedShapes.toSvg().replace("/>",
                String.format("fill=\"%s\"/>",color));
    }
}
