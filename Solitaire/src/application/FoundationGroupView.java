package application;

import java.util.List;

import javafx.scene.layout.HBox;

public class FoundationGroupView extends HBox{

	private List<FoundationView> foundationViews;
	
	public FoundationGroupView(List<FoundationView> foundationViews) {
		this.foundationViews = foundationViews;
		
		// set the spacing between the nodes
		this.setSpacing(10);
				
		for(int i=0;i<this.foundationViews.size();i++) {
			// add the nodes to the HBox container
			this.getChildren().add(this.foundationViews.get(i));
		}
	}	

	public List<FoundationView> getFoundationViews() {
		return foundationViews;
	}
}
