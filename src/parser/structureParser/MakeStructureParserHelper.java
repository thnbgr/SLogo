package parser.structureParser;

import parser.EncodeTree;
import parser.TreeMakingParser;
import parser.node.Node;
import parser.node.VariableNode;

public class MakeStructureParserHelper extends StructureParserHelper{
	private TreeMakingParser myParser;
	public MakeStructureParserHelper(TreeMakingParser treeMakingParser) {
		myParser = treeMakingParser;
	}
	
	@Override
	public Node parser(String command) {
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

}
