package parser.structureParser;

import parser.CommandTreeParser;
import parser.StructureInfoPackage;
import parser.node.Node;
import parser.node.control.RepeatNode;
/**
 * 
 * @author Junho Oh
 * @author Wenshun Liu
 *
 */
public class RepeatStructureParserHelper extends StructureParserHelper {

	public RepeatStructureParserHelper(CommandTreeParser treeMakingParser) {
		super(treeMakingParser);
	}
	
	@Override
	public Node parse(String command) {
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

	@Override
	public Node parseChildren(Node valueNode,
			StructureInfoPackage controlStructPackage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
