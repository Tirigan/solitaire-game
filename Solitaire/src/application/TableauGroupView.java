package application;

import java.util.List;

import javafx.scene.layout.HBox;

public class TableauGroupView extends HBox {

	private List<TableauView> tableauViews;
	
	public TableauGroupView(List<TableauView> tableauViews) {
		this.tableauViews = tableauViews;
		
		// set the spacing between the nodes
		this.setSpacing(10);
				
		for(int i=0;i<this.tableauViews.size();i++) {
			// add the nodes to the HBox container
			this.getChildren().add(this.tableauViews.get(i).getParent());
		}
	}	

	public List<TableauView> tableauViews() {
		return tableauViews;
	}
}

