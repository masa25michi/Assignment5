package simpledatabase;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Table extends Operator{
	private BufferedReader br = null;
	private boolean getAttribute=false;
	private Tuple tuple;
	private String tupleLine;
	private String attributeLine;
	private String dataTypeLine;
	
	public Table(String from){
		this.from = from;
		//Create buffer reader
		try{
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+from+".csv")));
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
	}

	/**
     * Create a new tuple and return the tuple to its parent.
     * Set the attribute list if you have not prepare the attribute list
     * @return the tuple
     */
	@Override
	public Tuple next(){
		try {
			
			if(br.ready()==false){
				return null;
			}
			else
			{
				if(getAttribute==false)
				{
					tupleLine="";
					attributeLine="";
					dataTypeLine="";
					int i=0;
					while(br.ready())
					{
						if(i==0)
						{
							 attributeLine=br.readLine();
						}
						else if(i==1)
						{
							 dataTypeLine=br.readLine(); 
						}
						else
						{
							tupleLine=br.readLine();
							break;
						}
						i++;
					}
					tuple=new Tuple(attributeLine,dataTypeLine,tupleLine);
					tuple.setAttributeName();
					tuple.setAttributeType();
					tuple.setAttributeValue();
					getAttribute=true;
					return tuple;
				}
				else
				{
					tupleLine=br.readLine();
					tuple=new Tuple(attributeLine,dataTypeLine,tupleLine);
					tuple.setAttributeName();
					tuple.setAttributeType();
					tuple.setAttributeValue();
					
					return tuple;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return tuple.getAttributeList();
	}
	
}