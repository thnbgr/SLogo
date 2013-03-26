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
		try {
			String[] commandParts = command.split(" ");
			String varName = commandParts[1];
			String varValue = "";
			for(int i = 2; i < commandParts.length; i++){
				varValue += commandParts[i] + " ";
			}
			//System.out.println(varValue);
			EncodeTree variableTree = myParser.encode(varValue);
			variableTree.getHead().evaluate();
			VariableNode aVariable = new VariableNode(variableTree.getHead().getValue(), varName);
			myParser.addVariable(aVariable);
		} catch (Exception e) {
			e.printStackTrace();
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
