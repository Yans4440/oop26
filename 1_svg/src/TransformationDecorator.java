import java.util.Locale;

public class TransformationDecorator extends ShapeDecorator{
    private String transform;

    public TransformationDecorator(Shape decoratedShapes, Vec2 translation, double rotation, Vec2 scale) {
        super(decoratedShapes);
        Builder builder = new Builder().translate(translation, rotation, scale);
        this.transform = builder.build();
    }

    @Override
    public String toSvg() {
        return this.decoratedShapes.toSvg().replace("/>",
                String.format(" transform=\"%s\" />", transform));
    }
    public static class Builder{
        private Vec2 translation;
        private Double rotation;
        private Vec2 scale;

        public Builder translate(Vec2 translation, double rotation, Vec2 scale){
            this.translation = translation;
            this.rotation = rotation;
            this.scale = scale;
            return this;
        }
        public String build(){
            String result = "";
            if(translation!=null){
                result = String.format(Locale.ENGLISH,
                        "translate(%f %f)", translation.x(), translation.y());

            }
            if(rotation!=null){
                result += String.format(Locale.ENGLISH,
                        " rotate(%f)", rotation);
            }
            if(scale!=null){
                result += String.format(Locale.ENGLISH,
                        " scale(%f %f)", scale.x(), scale.y());
            }


            return result;
        }

    }

}
