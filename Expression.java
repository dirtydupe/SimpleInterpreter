public class Expression 
{
	private int value;
	private String inputString;
	private int strIndex;
	private int strLength;
	private Token token;
	
	public Expression(String inputString)
	{
		this.inputString = inputString;
		strIndex = 0;
		strLength = inputString.length();
		nextToken();
		
		value = eval();
	}
	
	public int getValue()
	{
		return value;
	}
	
	private void nextToken()
	{
		char c;
		
		do
		{
			if(strIndex == strLength)
			{
				token = null;
				return;
			}
			
			c = inputString.charAt(strIndex++);

		} while(Character.isWhitespace(c));
		
		if(Token.openParen.equals(c))
			token = Token.openParen;
		
		else if(Token.closingParen.equals(c))
			token = Token.closingParen;
		
		else if(Token.combine.equals(c))
			token = Token.combine;
		
		else if(Token.negative.equals(c))
			token = Token.negative;
		
		else if(Token.multiply.equals(c))
			token = Token.multiply;
		
		else if(Character.isLetter(c) || c == '_')
		{
			StringBuffer sb = new StringBuffer();
			
			sb.append(c);

			if((strIndex + 1) < strLength)
			{
				c = inputString.charAt(strIndex++);
				
				while((Character.isDigit(c) || Character.isLetter(c) || c == '_') && strIndex < strLength)
				{
					sb.append(c);
					c = inputString.charAt(strIndex++);
				}
				
				strIndex--;
				
				if((strIndex + 1) == strLength)
				{
					c = inputString.charAt(strIndex++);
					
					if(Character.isDigit(c) || Character.isLetter(c) || c == '_')
						sb.append(c);
				}
			}
			else if((strIndex + 1) == strLength)
			{
				c = inputString.charAt(strIndex++);
				
				if(Character.isDigit(c) || Character.isLetter(c) || c == '_')
					sb.append(c);	
				else
					strIndex--;
			}
				
			token = new Identifier(sb.toString());
			
			//System.out.println("indentifier: " + sb.toString() + " length: " + sb.toString().length());// TESTING***
		}
		
		else if(c == 0)
		{
			if((strIndex + 1) < strLength)
			{
				c = inputString.charAt(strIndex++);
				
				if(Character.isDigit(c))
					throw new RuntimeException("*** ERROR: SYNTAX ERROR ***");
				
				strIndex--;
			}
			else
				token = new Literal(c + "");
		}
		
		else if(Character.isDigit(c))
		{
			StringBuffer sb = new StringBuffer();
			sb.append(c);
			
			if((strIndex + 1) < strLength)
			{
				c = inputString.charAt(strIndex++);
				
				while(Character.isDigit(c) && strIndex < strLength)
				{
					sb.append(c);
					c = inputString.charAt(strIndex++);
				}
				
				strIndex--;
				
				if((strIndex + 1) == strLength)
				{
					c = inputString.charAt(strIndex++);
					
					if(Character.isDigit(c))
						sb.append(c);
				}
			}
			else if((strIndex + 1) == strLength)
			{
				c = inputString.charAt(strIndex++);
				
				if(Character.isDigit(c))
					sb.append(c);
				else
					strIndex--;
			}
			
			token = new Literal(sb.toString());		
		}		
		//System.out.println("token: " + token.toString() + 
		//		" length: " + token.toString().length());// TESTING***
	}
	
	private void match(Token token)
	{
		if(this.token.equals(token))
			nextToken();
		
		else
			throw new RuntimeException("*** ERROR: SYNTAX ERROR ***");
	}
	
	private int eval()
	{
		int x = exp();
		
		if(token == null)
			return x;
	    else 
	        throw new RuntimeException("*** ERROR: SYNTAX ERROR ***");
	}
	
	private int exp()
	{
		int x = term();
		
		while(Token.combine.equals(token) || Token.negative.equals(token))
	    {
			Token op = token;
	        nextToken();
	        int y = term();
	        x = apply(op, x, y);
	    }
		
		return x;
	}
	
	private int term()
	{
		int x = factor();
		
		while(Token.multiply.equals(token))
	    {
	        Token op = token;
	        nextToken();
	        int y = factor();
	        x = apply(op, x, y);
	    }
		
		return x;
	}
	
	private int factor()
	{
		int x;
		
		//System.out.println("in factor: token = " + token.toString());	//TESTING***

		if(Token.openParen.equals(token))
		{
			nextToken();
			x = exp();
			match(Token.closingParen);
			return x;
		}

		else if(token instanceof Literal)
		{
			x = ((Literal)token).getValue();
			nextToken();
			return x;
		}
		
		else if(token instanceof Identifier)
		{
			//System.out.println(token.toString());		//TESTING***
			x = ((Identifier)token).getValue().getValue();
			nextToken();
			return x;
		}
		
		else
			throw new RuntimeException("*** ERROR: SYNTAX ERROR ***");
	}      	  
	
	private static int apply(Token op, int x, int y)
	{
		int z = 0;
		
		//System.out.println("in apply: x = " + x + "  y = " + y  + 
		//					" : x " + op.toString() + " y");	//TESTING***
		
	    if(Token.combine.equals(op))
	    	z = x + y;
	    
	    if(Token.negative.equals(op))
			z = x - y;
	    
	    if(Token.multiply.equals(op))
	    	z = x * y;
	    	
	    return z;
	}
}