package parser;

import parser.node.Node;
import parser.node.control.IfNode;

/*
 * This class is for handling if control statements. 
 * @author Junho Oh
 */
public class IfParser extends ControlParser{

	public IfParser() {
	}
	/*
	 *Will probably have to rename this.
	 *@param command - the syntactically correct command string. 
	 */
	@Override
	public void makeNode(String command){
		try{
			StructureInfoPackage IfStructPackage = getSyntaxCheck().splitIfStructure(command);
			Node ifValueNode = encode(IfStructPackage.getValue()).getHead();
			Node ifCommands = new Node();
			for(String ifCommand : IfStructPackage.getCommands().get(0)){
				ifCommands.addChild(encode(ifCommand).getHead());
			}
			IfNode ifNode = new IfNode();
			ifNode.addChild(ifValueNode);
			ifNode.addChild(ifCommands);
			
			 getCurNodes().add(ifNode);
		}
		catch(Exception e){
			
		}
		return new Node();
	}
}
