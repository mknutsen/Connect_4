import java.awt.Image;
import java.awt.event.MouseEvent;


public class Button implements Comparable<Button> {
	int startX, endX, startY, endY;
	Image image;
	public Button(int x, int y, int l, int w, Image i){
		startX = x;
		startY = y;
		endX = x+l;
		endY = y+w;
		image = i;
	}
	public boolean isInside(MouseEvent e){
		if(e.getX() >= startX && e.getX() <=endX && e.getY() <= endY && e.getY() >= startY){
			return true;
		}
		return false;
	}
	@Override
	public int compareTo(Button arg0) {
		Button x = (Button)arg0;
		if(startX == x.startX && startY == x.startY){
			return 1;
		}
		return 0;
	}
	
}
