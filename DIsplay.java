import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Display {
	PlacedBox[] boxArray;
	public Display(PlacedBox[] boxArray){
		this.boxArray = boxArray;
		print3DArray();
	}

	private void print3DArray(){
		SimpleUniverse universe = new SimpleUniverse();
		BranchGroup group = new BranchGroup();

		for (int i = 0; i < boxArray.length; i++) {

			Box box = new Box((float) boxArray[i].getHeight(), (float) boxArray[i].getWidth(), (float) boxArray[i].getLength(), null);

			TransformGroup tg = new TransformGroup();
			Transform3D transform = new Transform3D();
			Vector3f vector = new Vector3f( (float) boxArray[i].getX(), (float) boxArray[i].getY(), (float) boxArray[i].getZ());
			transform.setTranslation(vector);
			tg.setTransform(transform);
			tg.addChild(box);

			group.addChild(tg);

		}
		Vector3f viewTranslation = new Vector3f();
		viewTranslation.z = 40f;
		viewTranslation.x = 0f;
		viewTranslation.y = 6f;
		Transform3D viewTransform = new Transform3D();
		viewTransform.setTranslation(viewTranslation);
		Transform3D rotation = new Transform3D();
		rotation.rotX(-Math.PI / -6.0d);
		rotation.rotY(-Math.PI / 4.0d);
		rotation.mul(viewTransform);
		universe.getViewingPlatform().getViewPlatformTransform().setTransform(rotation);
		universe.getViewingPlatform().getViewPlatformTransform().getTransform(viewTransform);

		Color3f light1Color = new Color3f(.1f, 1.4f, .1f); // green light
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
		Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
		DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
		light1.setInfluencingBounds(bounds);
		group.addChild(light1);
		// add the group of objects to the Universe
		universe.addBranchGraph(group);
	}
}