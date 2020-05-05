public class Token 
{
	protected String tokenStr;
	
	public static final Token openParen = new Token("("),
							closingParen = new Token(")"),
							combine = new Token("+"),
							negative = new Token("-"),
							multiply = new Token("*"),
							assign = new Token("=");
	
	public Token(String tokenStr)
	{
		this.tokenStr = tokenStr;
	}
	
	public String toString()
	{
		return tokenStr;
	}
	
	public boolean equals(String str)
	{
		if(str == null)
			return false;

		return tokenStr.equals(str);
	}
	
	public boolean equals(char ch)
	{
		return tokenStr.equals(ch + "");
	}
	
	public boolean equals(Token token)
	{
		if(token == null)
			return false;

		return token.toString().equals(tokenStr);
	}
}

class Literal extends Token
{
	private int value;
	
	public Literal(String tokenStr)
	{
		super(tokenStr);
		value = Integer.parseInt(tokenStr);
	}
	
	public int getValue()
	{
		return value;
	}
}