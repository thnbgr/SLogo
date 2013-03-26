package parser.structureParser;

import parser.CommandTreeParser;
import parser.StructureInfoPackage;
import parser.node.Node;
import java.util.ArrayList;

/**
 * 
 * @author Junho Oh
 * @author Wenshun Liu
 *
 */
public abstract class StructureParserHelper {
	private CommandTreeParser myParser;
	public StructureParserHelper(CommandTreeParser treeParser){
		myParser = treeParser;
	}
	
	public Node parse(String command){
		try{
			StructureInfoPackage controlStructPackage = myParser.getSyntaxSpliter().splitControlStructure(getType(), command);
			Node controlValueNode = myParser.encode(controlStructPackage.getValue()).getHead();
			return parseChildren(controlValueNode, controlStructPackage);
		}
		catch(Exception e){	
			
		}
		return new Node();
	}
	public void addChildCommands(Node node, ArrayList<String> childCommands) throws Exception{
		for(String command : childCommands){
			node.addChild(myParser.encode(command).getHead());
		}
	}
	public abstract Node parseChildren(Node valueNode, StructureInfoPackage controlStructPackage);
	public abstract String getType();
	
}
