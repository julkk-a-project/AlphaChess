package window;


import controller.ShipPlacer;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import matrix.Matrix;

public class Tile extends StackPane {
	
	public boolean isOpen;
	private Rectangle border;
	private static Color color;
	private Text text = new Text();
	private int[] cords;
	private int x;
	private int y;
	
	/*		overview:		rep		AICanHit	use
	 *  	Water 		= 	0		true		empty tile
	 * 		Illogical	= 	1		false		for AI logic
	 * 		Hull 		=	2		false		ship part
	 * 		Hit 		=	3		false		broken ship part
	 * 		Miss 		=	4		false		a shot that landed in water */
	private static Color[] colorList = {												//CUSTOMIZED COLORS
									new Color(0, 0, 1, 1.0), 							//[0] water = blue
									new Color(1, 1, 1, 1.0),							//[1] illogical = black
									new Color(0.75, 0.75, 0.75, 1.0),					//[2] hull = gray
									new Color(1, 0, 0, 1.0),							//[3] hit = red
									new Color(1, 1, 0, 1.0),							//[4] miss = yellow
									new Color(0.96078431, 0.96078431, 0.86274510, 1.0)	//[5] default = beige	
									};
	
	
	
	public Tile(int x, int y) {
		cords = new int[2];
		cords[0] = x;
		cords[1] = y;
		this.x = x;
		this.y = y;
		border = new Rectangle(20,20);
		border.setFill(/*getColor()*/Color.GHOSTWHITE);
		border.setStroke(Color.BLACK);
		
		text.setFont(Font.font(14));
		text.setVisible(true);
		
		setAlignment(Pos.CENTER);
		getChildren().addAll(border, text);
		setOnMouseClicked(event -> open());
		//close();
	}
	
	
	
	public static Color getColor(int colorIndex) {
		color = colorList [colorIndex];
		return color;
	}



	public Rectangle setColor(Color color) {
		border.setFill(color);	
		return border;
	}

	
	
	public Rectangle setColorRep(int rep) {
		border.setFill(getColor(rep));	
		return border;
	}



	public void setOnAction(EventHandler<ActionEvent> eventHandler) {
		border.setOnMouseClicked(event -> open());
	}
	
	
	
	public void open() {
		System.out.println("openCalled");

		//When working correctly, color should change, and after shot is processed, color should...
		//...change to rep that tile has changed to.
		
		//FadeTransition ft = new FadeTransition(Duration.seconds(0.6),setColorRep(5));
		//ft.setToValue(1);
		//ft.play();
		
		
		if((main.Main.myMatrix.getTile(x, y).getRep() == 0 && main.Main.gameMode == "2") || (main.Main.itMatrix.getTile(x, y).getRep() == 0 && main.Main.gameMode != "2")) {
			if (main.Main.itMatrix.isLeagalCord(x,y) && controller.Controller.canCord()) {
				controller.Controller.cords = cords;
				System.out.println("coordsChanged");
				controller.Controller.waker();
				
			}
			else if (main.Main.gameMode == "2") {
				ShipPlacer.setCords(x,y);
				controller.Controller.waker();
			}
			
			else {
				System.out.println("coordsNotChanged");
				//window.Window.draw();
			}
			
			if(main.Main.hasBuffer()) {
				String string = main.Main.getBuffer(); 
				window.Window.appendLog(string);
				
			}
		}
				
	}
	
	
	
	public void close() {
		FadeTransition ft = new FadeTransition(Duration.seconds(0.6));
		ft.setToValue(0);
		ft.play();
	}



	public void draw(Matrix matrix) {
		setColorRep(matrix.getTile(x,y).getRep());
		
		
	}
	
}
