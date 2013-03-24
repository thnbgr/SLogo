package parser.structureParser;

import parser.TreeMakingParser;
import parser.StructureInfoPackage;
import parser.node.Node;
import parser.node.control.IfNode;
/**
 * 
 * @author Junho Oh
 * @author Wenshun Liu
 *
 */
public class IfStructureParserHelper extends StructureParserHelper{
	private TreeMakingParser myParser;
	public IfStructureParserHelper(TreeMakingParser treeMakingParser) {
		myParser = treeMakingParser;
	}

	@Override
	public Node parser(String command) {
		try{
			StructureInfoPackage IfStructPackage = myParser.getSyntaxCheck().splitIfStructure(command);
			Node ifValueNode = myParser.encode(IfStructPackage.getValue()).getHead();
			Node ifCommands = new Node();
			for(String ifCommand : IfStructPackage.getCommands().get(0)){
				ifCommands.addChild(myParser.encode(ifCommand).getHead());
			}
			IfNode ifNode = new IfNode();
			ifNode.addChild(ifValueNode);
			ifNode.addChild(ifCommands);
			
			return ifNode;
		}
		catch(Exception e){
			
		}
		return null;
		
	}

}
