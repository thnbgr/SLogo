package parser;

import command.CommandBundle;

public class FDEncodeParser extends EncodeParser {

	//math operation map. key 
	@Override
	//encode returns an encodetree
	public void encode(CommandBundle myPackage) {
		EncodeTree fdTree = new EncodeTree("fd");
		String[] command = myPackage.getStringCommand().split(" ");
		
		//TODO: now does not deal with things like fd add 50 10
		//command = "fd add 50 10" <- assume this is command
		//command = "fd 50" 
		
		double fdValue = Double.parseDouble(myPackage.getStringCommand().split(" ")[1]);
		
		
		fdTree.myHead.myLeft = new Node(fdValue);
		Node fdPosition = new Node("position");
		fdTree.myHead.myRight = fdPosition; //TODO: should be able to add all nodes with a while/for loop
												// instead of adding manually.
		fdPosition.myLeft = new Node(myPackage.getProcessable().getLocation().getX());
		fdPosition.myLeft.myLeft = new Node(myPackage.getProcessable().getLocation().getY());
		Node fdTowards = new Node("tw");
		fdPosition.myRight = fdTowards;
		
	}
}
