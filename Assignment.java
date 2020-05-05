import java.util.HashMap;

public class Assignment 
{
	private static HashMap<String, Expression> assignmentMap = new HashMap<>();
	
	private static Identifier identifier;
	private static Expression expression;
	
	public Assignment(String input) throws Exception 
	{
		String[] tokens = input.split("=");
		String idStr = tokens[0].trim();
		String exStr = tokens[1].trim();
		
		if(!(exStr.charAt(exStr.length() - 1) == ';'))
			throw new Exception("*** ERROR: Assignment missing ';' ***");
		
		identifier = new Identifier(idStr);
		expression = new Expression((exStr.substring(0, exStr.length() - 1)));
		
		assignmentMap.put(identifier.toString(), expression);
	}
	
	public static void assign(String key, Expression value)
	{
		assignmentMap.put(key, value);
	}
	
	public static Expression get(String key)
	{
		return assignmentMap.get(key);
	}
	
	public Identifier getIdentifier()
	{
		return identifier;
	}
	
	public Expression getExpression()
	{
		return expression;
	}
}