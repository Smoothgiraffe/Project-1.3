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

/*
This class takes the objects recieved from the PlacedBox class as a parameter
and then visually represents them in three dimensions
*/
public class Display {
	PlacedBox[] boxArray;
	//xCamera and yCamera control the viewing angle of the cargo
	private int xCamera = -6;
	private int yCamera =  4;

	//Constructor that takes in an array filled with objects from PlacedBox
	public Display(PlacedBox[] boxArray){
		this.boxArray = boxArray;
		print3DArray();
		GUI g = new GUI();
	}
	/*
	This method takes the boxArray array and goes through each index of boxArray
	and visually represents each object in the indexes. It also assigns each object in
	boxArray a certain color depending on what package they are.
	*/
	private void print3DArray(){
		//creates the area in which the boxes are created
		SimpleUniverse universe = new SimpleUniverse();
		BranchGroup group = new BranchGroup();

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

		coloringAttributesA.setColor(colorA);
		coloringAttributesB.setColor(colorB);
		coloringAttributesC.setColor(colorC);
		appearanceA.setColoringAttributes(coloringAttributesA);
		appearanceB.setColoringAttributes(coloringAttributesB);
		appearanceC.setColoringAttributes(coloringAttributesC);

		/*
		This for loop goes throgh the indexes of boxArray and looks to see if they are of A,B, or C type,
		it then assigns their color and visually represents them in 3D
		*/
		for (int i = 0; i < boxArray.length; i++) {
			Box box = new Box();
			System.out.println("i'm " + boxArray[i].getName());
			if (boxArray[i].getName() == 'A') {
				box = new Box((float) boxArray[i].getArrayHeight(), (float) boxArray[i].getArrayWidth(), (float) boxArray[i].getArrayLength(), appearanceA);
			}else if (boxArray[i].getName() == 'B'){
				System.out.println("im Red now");
				box = new Box((float) boxArray[i].getArrayHeight(), (float) boxArray[i].getArrayWidth(), (float) boxArray[i].getArrayLength(), appearanceB);
			}else if (boxArray[i].getName() == 'C'){
				System.out.println("im Green now");
				box = new Box((float) boxArray[i].getArrayHeight(), (float) boxArray[i].getArrayWidth(), (float) boxArray[i].getArrayLength(), appearanceC);
			}

			//Sets each object in boxArray onto its own vector
			TransformGroup tg = new TransformGroup();
			Transform3D transform = new Transform3D();
			Vector3f vector = new Vector3f( (float) boxArray[i].getX(), (float) boxArray[i].getY(), (float) boxArray[i].getZ());
			transform.setTranslation(vector);
			tg.setTransform(transform);
			tg.addChild(box);

			group.addChild(tg);

		}
		//This next bit of code allows us to move and set the angle in which we view the objects in boxArray
		Vector3f viewTranslation = new Vector3f();
		viewTranslation.z = 40f;
		viewTranslation.x = 0f;
		viewTranslation.y = 6f;
		Transform3D viewTransform = new Transform3D();
		viewTransform.setTranslation(viewTranslation);
		Transform3D rotation = new Transform3D();
		rotation.rotX(-Math.PI / xCamera);
		rotation.rotY(-Math.PI / yCamera);
		rotation.mul(viewTransform);
		universe.getViewingPlatform().getViewPlatformTransform().setTransform(rotation);
		universe.getViewingPlatform().getViewPlatformTransform().getTransform(viewTransform);

		//This sets the lighting of our universe so our objects are visible
		Color3f light1Color = new Color3f(.1f, 1.4f, .1f); // green light
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
		Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
		DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
		light1.setInfluencingBounds(bounds);
		group.addChild(light1);

		// adds the group of objects to the Universe
		universe.addBranchGraph(group);
	}


		class GUI extends JFrame{
			/*
			This class creates a seperate JFrame that is our user interface
			using buttons to change the viewing angle of the objects that
			were represented in 3D
			*/
			private int frameWidth = 300;
			private int frameHeight = 300;
			private JButton moveLeft;
			private JButton moveRight;
			public GUI(){
				createComponent();

				this.setSize(frameWidth, frameHeight);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setTitle("Control Unit");
				this.setLocationRelativeTo(null);
				this.setVisible(true);


			}

			public void createComponent(){
				createButtons();
				createRadio();

				JPanel panel = new JPanel();
				panel.setLayout(new BorderLayout());

				panel.add(moveLeft, BorderLayout.WEST);
				panel.add(moveRight, BorderLayout.EAST);
				this.add(panel);
			}

			public void createButtons(){
				 moveLeft = new JButton("Left 90");
				 moveRight = new JButton("Right 90");

				moveLeft.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e){
						yCamera = yCamera + 8;
						xCamera = xCamera + 40;
						print3DArray();

				}
					});

				moveRight.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e){
						yCamera = yCamera - 8;
						xCamera = xCamera - 40;
						print3DArray();

				}
					});

			}

			public void createRadio(){

			}


		}


	public static void main(String[] args){
		PlacedBox box = new PlacedBox( 3,2,2,'a',1,2,1);
		PlacedBox box1 = new PlacedBox( 1,2,1,'a',1,5,6);
		PlacedBox box2 = new PlacedBox( 1,1,1,'a',1,1,1);
		PlacedBox[] array = {box,box1,box2};


		Display display = new Display(array);
	}
}
