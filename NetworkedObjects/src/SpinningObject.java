import java.util.Random;


public class SpinningObject extends SpaceObjectDecorator{

	public SpinningObject(SpaceObject decoratedObject) {
		super(decoratedObject);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void rotate(double d) {
		Random randNum = new Random();
		double randRotate = (double) randNum.nextInt((int) d);
		super.rotate(randRotate);
		System.out.println("Spinning Object Rottating");
	}

	@Override
	public Polygon getPoly() {
		// TODO Auto-generated method stub
		return decoratedObject.getPoly();
	}
	
	@Override
	public void collide(SpaceObject obj){
		super.collide(obj);
	}

}
