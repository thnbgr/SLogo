package EncodeParser;

import command.CommandBundle;

public class FDEncodeParser extends EncodeParser {

	@Override
	public void encode(CommandBundle myPackage) {
		EncodeTree fdTree = new EncodeTree("fd");
		//TODO: now does not deal with things like fd add 50 10
		double fdValue = Double.parseDouble(myPackage.getStringCommand().split(" ")[1]);
		fdTree.myHead.myLeft = new Node(fdValue);
		Node fdPosition = new Node("ps");
		fdTree.myHead.myRight = fdPosition; //TODO: should be able to add all nodes with a while/for loop
												// instead of adding manually.
		fdPosition.myLeft = new Node(myPackage.getProcessable().getLocation().getX());
		fdPosition.myLeft.myLeft = new Node(myPackage.getProcessable().getLocation().getY());
		Node fdTowards = new Node("tw");
		fdPosition.myRight = fdTowards;
		
	}
}
