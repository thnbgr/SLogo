package parser.node;

public class Container<T>
{
	private T myValue;
	public T getValue() { return myValue; }
	public void setValue(T value) { myValue = value; }
}