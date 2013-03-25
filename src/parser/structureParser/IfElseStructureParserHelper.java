package parser.structureParser;

import parser.CommandTreeParser;
import parser.StructureInfoPackage;
import parser.node.Node;
import parser.node.control.IfElseNode;
/**
 * 
 * @author Junho Oh
 * @author Wenshun Liu
 *
 */
public class IfElseStructureParserHelper extends StructureParserHelper {
	private CommandTreeParser myParser;
	public IfElseStructureParserHelper(CommandTreeParser treeMakingParser) {
		myParser = treeMakingParser;
	}

	@Override
	public Node parser(String command) {
		try{
			StructureInfoPackage IfElseStructPackage = myParser.getSyntaxCheck().splitIfElseStructure(command);
			Node ifValueNode = myParser.encode(IfElseStructPackage.getValue()).getHead();
			Node ifCommands = new Node();
			for(String ifCommand : IfElseStructPackage.getCommands().get(0)){
				ifCommands.addChild(myParser.encode(ifCommand).getHead());
			}
			IfElseNode ifElseNode = new IfElseNode();
			ifElseNode.addChild(ifValueNode);
			ifElseNode.addChild(ifCommands);
			
			return ifElseNode;
		}
		catch(Exception e){	
		}
		return null;
	}

}
