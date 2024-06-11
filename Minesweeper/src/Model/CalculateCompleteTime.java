package Model;

import View.GameFrame;
import View.PanelNotification;

public class CalculateCompleteTime {
    private int completeTime; 
    
    public CalculateCompleteTime() {
        this.completeTime = 0;
    }
    
    public int calculateCompletionTime(GameFrame gameFrame, PanelNotification panelNotification) {
        int totalTime = gameFrame.getTotalTime();
        int cTimeLeft = panelNotification.getcTimeLeft();
        
        completeTime = totalTime - cTimeLeft;
        
        return completeTime;
    }
}
