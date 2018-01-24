import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


/*
This class takes the objects recieved from the PlacedParcel class as a parameter
and then visually represents them in three dimensions
*/
public class Display {
	ArrayList<PlacedParcel> boxArray = new ArrayList<PlacedParcel>();
	//xCamera and yCamera control the viewing angle of the cargo
	private float deltaX = 0;
	private int xCamera = -4;
	private int yCamera = 1;

	private static float spacing = 1.08f;

	private SimpleUniverse universe = new SimpleUniverse();
	public BranchGroup group = null;


	//Constructer that takes in an array filled with objects from PlacedParcel
	public Display(ArrayList boxArray){
		if(boxArray.size() > 0) {
			if (boxArray.get(0) instanceof PlacedParcel) {
				this.boxArray = boxArray;
			}
			if (boxArray.get(0) instanceof PlacedPentomino) {
				spacing = 1;
				this.boxArray = PentToParcel.convert(boxArray);
			}
		}
		GUI g = new GUI();
		print3DArray();
	}

	public void show(ArrayList boxArray){
		if(boxArray.size() > 0) {
			if (boxArray.get(0) instanceof PlacedParcel) {
				this.boxArray = boxArray;
			}
			if (boxArray.get(0) instanceof PlacedPentomino) {
				spacing = 1;
				this.boxArray = PentToParcel.convert(boxArray);
			}
		}
		group.detach();
		//universe.dispose();
		//universe = new SimpleUniverse();
		print3DArray();
	}

	/*
	This method takes the boxArray array and goes through each index of boxArray
	and visually represents each object in the indexes. It also assigns each object in
	boxArray a certain color depending on what package they are.
	*/
	private void print3DArray(){
		//creates the area in which the boxes are created
		group = new BranchGroup();
		group.setCapability(BranchGroup.ALLOW_DETACH);

		//sets the appearence that the coloringAttributes will be passed into for A,B, and C boxes
		Appearance appearanceA = new Appearance();
		Appearance appearanceB = new Appearance();
		Appearance appearanceC = new Appearance();

		//sets the coloringAttributes that color3f will be passed into for A,B, and C boxes
		ColoringAttributes coloringAttributesA = new ColoringAttributes();
		ColoringAttributes coloringAttributesB = new ColoringAttributes();
		ColoringAttributes coloringAttributesC = new ColoringAttributes();

		//sets the color of the boxes
		Color3f colorA = new Color3f(Color.yellow);
		Color3f colorB = new Color3f(Color.red);
		Color3f colorC = new Color3f(Color.green);

		//creates 3 colortribute objects using the colors
		coloringAttributesA.setColor(colorA);
		coloringAttributesB.setColor(colorB);
		coloringAttributesC.setColor(colorC);

		//creates appreance objects using the coloringAtributes objects
		appearanceA.setColoringAttributes(coloringAttributesA);
		appearanceB.setColoringAttributes(coloringAttributesB);
		appearanceC.setColoringAttributes(coloringAttributesC);

		/*
		This for loop goes throgh the indexes of boxArray and looks to see if they are of A,B, or C type,
		it then assigns their color and visually represents them in 3D
		*/
		for (int i = 0; i < boxArray.size(); i++) {
			Box box = new Box();
			if (boxArray.get(i).getName() == 'A') {
				box = new Box((float) boxArray.get(i).getLength() / (5f * spacing), (float) boxArray.get(i).getHeight() / (5f * spacing), (float) boxArray.get(i).getWidth() / (5f * spacing), appearanceA);
			}else if (boxArray.get(i).getName() == 'B'){
				box = new Box((float) boxArray.get(i).getLength() / (5f * spacing), (float) boxArray.get(i).getHeight() / (5f * spacing), (float) boxArray.get(i).getWidth() / (5f * spacing), appearanceB);
			}else if (boxArray.get(i).getName() == 'C'){
				box = new Box((float) boxArray.get(i).getLength() / (5f * spacing), (float) boxArray.get(i).getHeight() / (5f * spacing), (float) boxArray.get(i).getWidth() / (5f * spacing), appearanceC);
			}

			//Sets each object in boxArray onto its own vector
			TransformGroup tg = new TransformGroup();
			Transform3D transform = new Transform3D();
			Vector3f vector = new Vector3f((float)boxArray.get(i).getX() / 1.25f + (float) boxArray.get(i).getLength() / 5, (float)boxArray.get(i).getY() / 1.25f  +  ((float)boxArray.get(i).getHeight() / 5), (float) boxArray.get(i).getZ() / 1.25f + (float) boxArray.get(i).getWidth() / 5);
			transform.setTranslation(vector);
			tg.setTransform(transform);
			tg.addChild(box);
			group.addChild(tg);

		}

		Font3D font = new Font3D(new Font("font", Font.PLAIN, 1), new FontExtrusion());
		Text3D text = new Text3D(font, "Group 13!", new Point3f(0.0f, 3.5f, 0.0f));

		Transform3D transform2 = new Transform3D();
		Shape3D shape = new Shape3D();
		shape.setGeometry(text);
		group.addChild(shape);

		//This next bit of code allows us to move and set the angle in which we view the objects in boxArray
		Vector3f viewTranslation = new Vector3f();
		viewTranslation.z = 20f;
		viewTranslation.x = deltaX;
		viewTranslation.y = 8f;
		Transform3D viewTransform = new Transform3D();
		viewTransform.setTranslation(viewTranslation);

		Transform3D rotation = new Transform3D();
		rotation.rotY(yCamera * Math.PI / 4);
		rotation.mul(viewTransform);

		Transform3D rotation2 = new Transform3D();
		rotation2.rotX(xCamera * Math.PI / 32);
		rotation.mul(rotation2);

		//universe.getViewingPlatform().getViewPlatformTransform().setTransform(move);
		universe.getViewingPlatform().getViewPlatformTransform().setTransform(rotation);
		universe.getViewingPlatform().getViewPlatformTransform().getTransform(viewTransform);

		// adds the group of objects to the Universe
		universe.addBranchGraph(group);

	}

	/*
	Gui is for creating a little popup window with camera controls
	 */
	class GUI extends JFrame implements KeyListener{
		/*
		This class creates a seperate JFrame that is our user interface
		using buttons to change the viewing angle of the objects that
		were represented in 3D
		*/
		private int frameWidth = 1;
		private int frameHeight = 1;
		//private JButton moveLeft;
		//private JButton moveRight;
		public GUI(){

			//position the window in the top right corner
			GraphicsDevice screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			int x = (int) screen.getDefaultConfiguration().getBounds().getMaxX() - frameWidth - 70;
			this.setLocation(x, 0);

			this.setSize(frameWidth, frameHeight);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setTitle("Control Unit");
			this.setVisible(true);
			addKeyListener(this);
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				yCamera += 1;
				print3DArray();
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				yCamera -= 1;
				print3DArray();
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN){
				deltaX += 1;
				print3DArray();
			}
			if(e.getKeyCode() == KeyEvent.VK_UP){
				deltaX -= 1;
				print3DArray();
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
		}
		@Override
		public void keyTyped(KeyEvent e) {
		}
	}
}
