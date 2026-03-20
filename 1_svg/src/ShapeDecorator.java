public class ShapeDecorator implements Shape{
    protected Shape decoratedShapes;

    public ShapeDecorator(Shape decoratedShapes){
        this.decoratedShapes = decoratedShapes;
    }

    @Override
    public BoundingBox boundingBox() {
        return decoratedShapes.boundingBox();
    }

    @Override
    public String toSvg() {
        return decoratedShapes.toSvg();
    }
}
