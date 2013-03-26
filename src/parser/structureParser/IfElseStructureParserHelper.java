package parser.structureParser;

import parser.TreeMakingParser;
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
	private TreeMakingParser myParser;
	public IfElseStructureParserHelper(TreeMakingParser treeMakingParser) {
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
			Node ifElseCommands = new Node();
			for(String ifElseCommand : IfElseStructPackage.getCommands().get(1)){
				ifElseCommands.addChild(myParser.encode(ifElseCommand).getHead());
			}
			IfElseNode ifElseNode = new IfElseNode();
			ifElseNode.addChild(ifValueNode);
			ifElseNode.addChild(ifCommands);
			ifElseNode.addChild(ifElseCommands);
			
			return ifElseNode;
		}
		catch(Exception e){	
		}
		return null;
	}

}
