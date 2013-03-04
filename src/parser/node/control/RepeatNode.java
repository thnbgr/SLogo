package parser.node.control;

import parser.node.BinaryNode;
import parser.node.Container;
import parser.node.Node;
import java.util.Queue;
import parser.EncodeTree;
import java.util.ArrayList;

public class RepeatNode extends BinaryNode {

	public RepeatNode() {
	}
	@Override
	public void evaluate(){
		//move this to model. or have an arraylist as value of this node
		Container<ArrayList<EncodeTree>> myContainer = new Container<ArrayList<EncodeTree>>();
		int count = 0;
		getLeft().evaluate();
		if(getLeft().getContainer().getValue() instanceof Double){
			count = (Integer) getLeft().getContainer().getValue();
		}
		while(count > 0){
			this.evaluate();
		}
		setContainer(myContainer);
		
		//RepeatNode.getContainer().getValue()
	}
	
}
