package parser.structureParser;

import parser.CommandTreeParser;
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
	public IfStructureParserHelper(CommandTreeParser treeMakingParser) {
		super(treeMakingParser);
	}
	@Override
	public String getType() {
		return "if";
	}
	
	@Override
	public Node parseChildren(Node valueNode, StructureInfoPackage controlStructPackage) {
	
		IfNode ifNode = new IfNode();
		Node ifCommands = new Node();
		
		ifNode.addChild(valueNode);
		try{
			addChildCommands(ifCommands, controlStructPackage.getCommands().get(0));
			ifNode.addChild(ifCommands);
			return ifNode;
		}
		catch(Exception e){
			
		}
		return new Node();
	}


}
