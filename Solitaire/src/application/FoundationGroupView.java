package application;

import java.util.List;

import javafx.scene.layout.HBox;

public class FoundationGroupView extends HBox{

	public FoundationGroupView(List<FoundationView> foundations) {
		// set the spacing between the nodes
		this.setSpacing(10);
				
		for(int i=0;i<foundations.size();i++) {
			// add the nodes to the HBox container
			this.getChildren().add(foundations.get(i));
		}
	}	
}
