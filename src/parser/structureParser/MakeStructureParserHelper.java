package parser.structureParser;

import parser.CommandTreeParser;
import parser.StructureInfoPackage;
import parser.node.EncodeTree;
import parser.node.Node;
import parser.node.VariableNode;

public class MakeStructureParserHelper extends StructureParserHelper{

	public MakeStructureParserHelper(CommandTreeParser treeMakingParser) {
		super(treeMakingParser);
	}
	
	@Override
	public Node parse(String command) {
		try {
			String[] commandParts = command.split(" ");
			String varName = commandParts[1];
			String varValue = "";
			for(int i = 2; i < commandParts.length; i++){
				varValue += commandParts[i] + " ";
			}
			EncodeTree variableTree = getParser().encode(varValue);
			variableTree.getHead().evaluate();
			VariableNode aVariable = new VariableNode(variableTree.getHead().getValue(), varName);
			getParser().addVariable(aVariable);
			return aVariable;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Node();
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
