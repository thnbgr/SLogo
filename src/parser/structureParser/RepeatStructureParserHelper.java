package parser.structureParser;

import parser.Parser;
import parser.StructureInfoPackage;
import parser.node.Node;
import parser.node.control.RepeatNode;

public class RepeatStructureParserHelper extends StructureParserHelper {
	private Parser myParser;
	public RepeatStructureParserHelper(Parser parser) {
		myParser = parser;
	}
	
	@Override
	public Node parser(String command) {
		try{
			StructureInfoPackage repeatStructPackage = myParser.getSyntaxCheck().splitRepeatStructure(command);
			Node repeatValueNode = myParser.encode(repeatStructPackage.getValue()).getHead();
			Node repeatCommands = new Node();
			for(String repeatCommand : repeatStructPackage.getCommands().get(0)){
				repeatCommands.addChild(myParser.encode(repeatCommand).getHead());
			}
			RepeatNode repeatNode = new RepeatNode();
			repeatNode.addChild(repeatValueNode);
			repeatNode.addChild(repeatCommands);
			
			return repeatNode;
		}
		catch(Exception e){
			
		}
		return null;
	}

}
