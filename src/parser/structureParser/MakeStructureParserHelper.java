package parser.structureParser;

import parser.EncodeTree;
import parser.CommandTreeParser;
import parser.StructureInfoPackage;
import parser.node.Node;
import parser.node.VariableNode;

public class MakeStructureParserHelper extends StructureParserHelper{

	public MakeStructureParserHelper(CommandTreeParser treeMakingParser) {
		super(treeMakingParser);
	}
	
	@Override
	public Node parse(String command) {
		System.out.println("ai");
		try {
			String[] commandParts = command.split(" ");
			String varName = commandParts[1];
			String varValue = "";
			for(int i = 2; i < commandParts.length; i++){
				varValue += commandParts[i] + " ";
			}
			//System.out.println(varValue);
			EncodeTree variableTree = getParser().encode(varValue);
			variableTree.getHead().evaluate();
			VariableNode aVariable = new VariableNode(variableTree.getHead().getValue(), varName);
			getParser().addVariable(aVariable);
			return aVariable;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Deprecated
	public Node parseChildren(Node valueNode, StructureInfoPackage controlStructPackage) {
		return null;
	}

	@Deprecated
	public String getType() {
		return null;
	}

}
