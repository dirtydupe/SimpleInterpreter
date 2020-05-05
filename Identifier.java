public class Identifier extends Token
{	
	public Identifier(String input)
	{
		super(input);
		parseTokens(input.trim().toCharArray());
	}
	
	public String toString()
	{
		return super.toString();
	}
	
	public Expression getValue()
	{
		return Assignment.get(this.toString());
	}
	
	private void parseTokens(char[] input)
	{
		try
		{
			if(!Character.isLetter(input[0]) || input[0] == '_')
				throw new Exception("*** ERROR: Invalid Identifier ***");
			
			for(int i = 1; i < input.length; i++)
			{
				if((Character.isLetter(input[i])) || (Character.isDigit(input[i])) 
						|| input[i] == '_')
					continue;
				else
					throw new Exception("*** ERROR: Invalid Identifier ***");
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}
}