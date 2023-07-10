import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class LightsController extends Thread {

	final int TIME = 2000;								// amount of time for all the traffic lights
	final int INTERVALS = 200;							// blinking period for pedestrians traffic light
	final int AMOUNT = 5;								// number of blinks
	
    @FXML
    private Canvas canv;
    private GraphicsContext gc;
    private int x, y;									// will hold the size of the canvas

    
    public void initialize() {
    	gc = canv.getGraphicsContext2D();
    	start();
    }
    
    @Override
    public void run() {
    	super.run();
    	this.x = (int) canv.getWidth();					// store the canvas width
    	this.y = (int) canv.getHeight();				// store the canvas height
    	Boolean junction = true;						// infinite loop
    	int mode = 0;									// mode indicator
    	
    	gc.setFill(Color.BLACK);
    	gc.fillRect(x/2 - 22, 98, 34, 54);				// BORDER upper rectangle - red
    	gc.fillRect(x/2 - 22, y - 127, 34, 54);			// BORDER lower rectangle - red
    	gc.fillRect(x - 82, y/2 - 57, 34, 54);			// BORDER right rectangle - red
    	gc.fillRect(38, y/2 - 57, 34, 54);				// BORDER left rectangle - red
    	
    	gc.fillRect(x/2 - 22, 153, 34, 54);				// BORDER upper rectangle - green
    	gc.fillRect(x/2 - 22, y - 72, 34, 54);			// BORDER lower rectangle - green
    	gc.fillRect(x - 82, y/2 - 2, 34, 54);			// BORDER right rectangle - green
    	gc.fillRect(38, y/2 - 2, 34, 54);				// BORDER left rectangle - green
    	 	
    	gc.fillOval(x/2 - 26, 8, 44, 44);				// BORDER upper circle - red
    	gc.fillOval(x/2 - 26, y - 217, 44, 44);			// BORDER lower circle - red
    	gc.fillOval(x - 86, y/2 - 147, 44, 44);			// BORDER right circle - red
    	gc.fillOval(34, y/2 - 147, 44, 44);				// BORDER left circle - red
    	
    	gc.fillOval(x/2 - 26, 53, 44, 44);				// BORDER upper circle - green
    	gc.fillOval(x/2 - 26, y - 172, 44, 44);			// BORDER lower circle - green
    	gc.fillOval(x - 86, y/2 - 102, 44, 44);			// BORDER right circle - green
    	gc.fillOval(34, y/2 - 102, 44, 44);				// BORDER left circle - green
    	
    	while(junction) {
    	/***********************************************************************************************/
    		if(mode == 0) {												// cars can go from left to right and vice versa
    			gc.setFill(Color.WHITE);
    			gc.fillRect(x/2 - 20, 100, 30, 50);						// upper rectangle - red
    			gc.fillRect(x/2 - 20, y - 125, 30, 50);					// lower rectangle - red
    			gc.setFill(Color.RED);
    			gc.fillRect(x - 80, y/2 - 55, 30, 50);					// right rectangle - red
    			gc.fillRect(40, y/2 - 55, 30, 50);						// left rectangle - red
    	
    	/***********************************************************************************************/
    			
    			new Thread(() -> {										// blinking thread
                    for( int i =0; i<AMOUNT; i++) {
                    	gc.setFill(Color.GREEN);
            			gc.fillRect(x/2 - 20, 155, 30, 50);				// upper rectangle - green
            			gc.fillRect(x/2 - 20, y - 70, 30, 50);			// lower rectangle - green
            			try {
                            Thread.sleep(INTERVALS);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        gc.setFill(Color.WHITE);
                		gc.fillRect(x/2 - 20, 155, 30, 50);				// upper rectangle - green
                		gc.fillRect(x/2 - 20, y - 70, 30, 50);			// lower rectangle - green
               			try {
                               Thread.sleep(INTERVALS);
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                    }	// end of for
                }).start();
    			
    			gc.setFill(Color.WHITE);
    			gc.fillRect(x - 80, y/2, 30, 50);						// right rectangle - green
    			gc.fillRect(40, y/2, 30, 50);							// left rectangle - green
    	
    	/***********************************************************************************************/
    
    			gc.setFill(Color.RED);
    			gc.fillOval(x/2 - 24, 10, 40, 40);						// upper circle - red
    			gc.fillOval(x/2 - 24, y - 215, 40, 40);					// lower circle - red
    			gc.setFill(Color.WHITE);
    			gc.fillOval(x - 84, y/2 - 145, 40, 40);					// right circle - red
    			gc.fillOval(36, y/2 - 145, 40, 40);						// left circle - red

    	/***********************************************************************************************/
    	
    			gc.setFill(Color.WHITE);
    			gc.fillOval(x/2 - 24, 55, 40, 40);						// upper circle - green
    			gc.fillOval(x/2 - 24, y - 170, 40, 40);					// lower circle - green
    			gc.setFill(Color.GREEN);
    			gc.fillOval(x - 84, y/2 - 100, 40, 40);					// right circle - green
    			gc.fillOval(36, y/2 - 100, 40, 40);						// left circle - green
    	
    			mode = 1;
    		}
    		else {														// cars can go from up to down and vice versa
    			gc.setFill(Color.RED);
    			gc.fillRect(x/2 - 20, 100, 30, 50);						// upper rectangle - red
	    		gc.fillRect(x/2 - 20, y - 125, 30, 50);					// lower rectangle - red
	    		gc.setFill(Color.WHITE);
	    		gc.fillRect(x - 80, y/2 - 55, 30, 50);					// right rectangle - red
	    		gc.fillRect(40, y/2 - 55, 30, 50);						// left rectangle - red
	    	
    	/***********************************************************************************************/
    	
	    		gc.setFill(Color.WHITE);		
	    		gc.fillRect(x/2 - 20, 155, 30, 50);						// upper rectangle - green
	    		gc.fillRect(x/2 - 20, y - 70, 30, 50);					// lower rectangle - green
	    		new Thread(() -> {										// blinking thread
                    for( int i =0; i<AMOUNT; i++) {
                    	gc.setFill(Color.GREEN);
                    	gc.fillRect(x - 80, y/2, 30, 50);				// right rectangle - green
        	    		gc.fillRect(40, y/2, 30, 50);					// left rectangle - green
            			try {
                            Thread.sleep(INTERVALS);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        gc.setFill(Color.WHITE);
                        gc.fillRect(x - 80, y/2, 30, 50);				// right rectangle - green
        	    		gc.fillRect(40, y/2, 30, 50);					// left rectangle - green
               			try {
                               Thread.sleep(INTERVALS);
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                    }	// end of for
                }).start();
	    	
    	/***********************************************************************************************/
    
	    		gc.setFill(Color.WHITE);
	    		gc.fillOval(x/2 - 24, 10, 40, 40);				// upper circle - red
	    		gc.fillOval(x/2 - 24, y - 215, 40, 40);			// lower circle - red
	    		gc.setFill(Color.RED);
	    		gc.fillOval(x - 84, y/2 - 145, 40, 40);			// right circle - red
	    		gc.fillOval(36, y/2 - 145, 40, 40);				// left circle - red

    	/***********************************************************************************************/
    	
	    		gc.setFill(Color.GREEN);
	    		gc.fillOval(x/2 - 24, 55, 40, 40);				// upper circle - green
	    		gc.fillOval(x/2 - 24, y - 170, 40, 40);			// lower circle - green
	    		gc.setFill(Color.WHITE);
	    		gc.fillOval(x - 84, y/2 - 100, 40, 40);			// right circle - green
	    		gc.fillOval(36, y/2 - 100, 40, 40);				// left circle - green
	    	
	    		mode = 0;
    		}
    		try {
				sleep(TIME);									// current mode will stay TIME mili seconds
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}	// end of while
    
    	
    }	 // end of run
    
}
