import java.io.File;
import java.util.Scanner;

public class Interpreter 
{
	public static void main(String args[]) throws Exception
	{
		Assignment assignment = null;
		Scanner scanner;
		
		if(args.length > 0)
		{
			String line;
			File file = new File(args[0]);
			scanner = new Scanner(file);
			
			while(scanner.hasNextLine())
			{
				line = scanner.nextLine();
				assignment = new Assignment(line);
				
				System.out.println(assignment.getIdentifier().toString() + " = " +
						assignment.getExpression().getValue());
			}		
		}
		else
		{
			String input;
			boolean loop = true;
			scanner = new Scanner(System.in);
		
			do
			{
				System.out.print("> ");					// Interpreter prompt
				input = scanner.nextLine();				// Retrieve input string
			
				if(input.equals("quit"))
					loop = false;
				else
				{
					assignment = new Assignment(input);
					System.out.println(assignment.getIdentifier().toString() + " = " +
						assignment.getExpression().getValue());
				}	
			} 
			while(loop);	
		}
		
		scanner.close();
	}
}