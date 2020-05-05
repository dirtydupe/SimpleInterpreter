import java.util.HashMap;

public class Assignment 
{
	private static HashMap<String, Expression> assignmentMap = new HashMap<>();
	
	private static Identifier identifier;
	private static Expression expression;
	
	public Assignment(String input) throws Exception 
	{
		String[] tokens = input.split("=");
		
		if(!(tokens[1].charAt(tokens[1].length() - 1) == ';'))
			throw new Exception("*** ERROR: Assignment missing ';' ***");
		
		identifier = new Identifier(tokens[0].trim());
		expression = new Expression((tokens[1].substring(0, tokens[1].length() - 1)).trim());
		
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