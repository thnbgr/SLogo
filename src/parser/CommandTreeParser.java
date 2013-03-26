package parser;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import parser.node.*;
import parser.node.control.*;
import parser.node.turtleCommand.*;
import parser.structureParser.IfElseStructureParserHelper;
import parser.structureParser.IfStructureParserHelper;
import parser.structureParser.MakeStructureParserHelper;
import parser.structureParser.RepeatStructureParserHelper;
import parser.structureParser.StructureParserHelper;
import parser.structureParser.ToStructureParserHelper;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Junho Oh
 * @author Wenshun Liu
 *
 *         This class is responsible for taking in a command string and parsing
 *         it into a tree structure for the model to be able to evaluate.
 */
public class CommandTreeParser {
	public static final String COMMAND_PROPERTIES_FILE_NAME 
										= "src/commandProperties.csv";
	private CSVTable myCSVTable;
	private ArrayList<VariableNode> myVariables;
	private ArrayList<CustomCommandNode> myCustomCommands;
	private String[] myControlTypes = { "if", "ifelse", "make", "to",
			"repeat" };
	private StructureParserHelper[] myStructureParser = {
			new IfStructureParserHelper(this),
			new IfElseStructureParserHelper(this),
			new MakeStructureParserHelper(this),
			new ToStructureParserHelper(this),
			new RepeatStructureParserHelper(this) };
	private Map<String, StructureParserHelper> myStructureParserMap =
							new HashMap<String, StructureParserHelper>();
	// if view gets around to making workspaces...add these to a map k=workspace
	// id, v=arraylist
	private SyntaxCheck mySyntaxCheck;
	private SyntaxSpliter mySyntaxSpliter;

	// have it get passed in
	/**
	 * Parses the user input command into a EncodeTree. For later decoding
	 * use.
	 * @param fileName The information file of each Node in the tree.
	 */
	public CommandTreeParser(String fileName) {
		myCSVTable = new CSVTable(COMMAND_PROPERTIES_FILE_NAME);
		myVariables = new ArrayList<VariableNode>();
		myCustomCommands = new ArrayList<CustomCommandNode>();
		for (int i = 0; i < myControlTypes.length; ++i) {
			myStructureParserMap.put(myControlTypes[i],
					myStructureParser[i]);
		}
	}

	/**
	 * Bonds the CommandTreeParser with the given SyntaxCheck.
	 * @param s The given SyntaxCheck
	 */
	public void setSyntaxCheck(SyntaxCheck s) {
		mySyntaxCheck = s;
	}
	
	public void setSyntaxSpliter(SyntaxSpliter s){
		mySyntaxSpliter = s;
	}

	/**
	 * Encodes the user input String into an EncodeTree
	 * @param command The user inputString
	 * @return an EncodeTree parsed from the command
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	public EncodeTree encode(String command) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, SecurityException,
			InvocationTargetException, IOException {
		Queue<String> myCommandParts = new LinkedList<String>();
		myCommandParts.addAll(Arrays.asList(command.split(" ")));
		Queue<Node> myCurNodes = new LinkedList<Node>();
		Node curNode = new Node();

		while (myCommandParts.size() > 0) {
			String curValue = myCommandParts.remove();
			if (myStructureParserMap.containsKey(curValue)) {
				String makeCommand = curValue;
				while (!mySyntaxCheck.syntaxCheck(makeCommand)) {
					makeCommand += " " + myCommandParts.remove();
				}
				Node temp = myStructureParserMap.
					get(curValue).parse(makeCommand);
				if (!curValue.equals("to")) {
					myCurNodes.add(temp);
				}
			}

			else {
				Node temp = null;
				if (myCSVTable.returnCSVRow(curValue) == null) {
					temp = new ValueNode(Integer.parseInt(curValue));
				} else {
					Class<?> headClass = 
							Class.forName(myCSVTable.returnCSVRow(
							curValue).getCommandFilePath());
					temp = (Node) headClass.getConstructors()[0].newInstance();
					if (temp instanceof TurtleCommandNode) {
						((TurtleCommandNode) temp).setName(curValue);
					}
					temp.setNumArgs(myCSVTable.returnCSVRow(curValue)
							.getCommandNumArgs());
				}
				myCurNodes.add(temp);
			}

		}
		EncodeTree returnTree = new EncodeTree();
		if (!myCurNodes.isEmpty()) {
			curNode = myCurNodes.remove();
			curNode.makeTree(myCurNodes);
		}
		returnTree = new EncodeTree(curNode);
		return returnTree;
	}

	/**
	 * Adds a Variable Node to the list of variables stored.
	 * @param aVariable the Variable Node to be added.
	 */
	public void addVariable(VariableNode aVariable) {
		myVariables.add(aVariable);
	}

	/**
	 * Get the list of Variable Nodes.
	 * @return The list of Variable Nodes
	 */
	public ArrayList<VariableNode> getVariables() {
		return myVariables;
	}

	/**
	 * Get the list of CustomCommandNodes.
	 * @return The list of CustomCommandNodes
	 */
	public ArrayList<CustomCommandNode> getCustomCommands() {
		return myCustomCommands;
	}

	/**
	 * Adds a CustomCommandNode to the list stored.
	 * @param n The CustomCommandNode to be added
	 */
	public void addCustomCommands(CustomCommandNode n) {
		myCustomCommands.add(n);
	}

	/**
	 * Get the SyntaxCheck.
	 * @return the SyntaxCheck
	 */
	public SyntaxCheck getSyntaxCheck() {
		return mySyntaxCheck;
	}

	/**
	 * Get the SyntaxSpliter.
	 * @return The SyntaxSpliter
	 */
	public SyntaxSpliter getSyntaxSpliter() {
		return mySyntaxSpliter;
	}
}
