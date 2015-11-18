package simpledatabase;
import java.util.ArrayList;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;
	private Type type;

	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		
	}
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		if(tuplesResult.isEmpty())
		{
			int commonIndex=0;
			Tuple tuple = child.next();
			if(tuple!=null&&tuple.getAttributeList()!=null)
			{
				for(int i=0;i<tuple.getAttributeList().size();i++)
				{
					if(tuple.getAttributeList().get(i).getAttributeName().toString().equals(orderPredicate))
					{
						type=tuple.getAttributeList().get(i).getAttributeType();
						commonIndex=i;
					}
				}
			}
			while(tuple!=null)
			{
				boolean check=false;
				if(tuplesResult.isEmpty())
				{
					tuplesResult.add(tuple);
				}
				else
				{
					if(type!=null)
					{
						switch(type.type)
						{
							case INTEGER: //if comparators are integer!!!!
								
								for(int i=0;i<tuplesResult.size();i++)
								{
										if((Integer)(tuplesResult.get(i).getAttributeList().get(commonIndex).getAttributeValue())>(Integer)(tuple.getAttributeList().get(commonIndex).getAttributeValue()))
										{
											tuplesResult.add(i,tuple);
											check=true;
											break;
										}
								}
								if(check==false){
									int lastIndex=tuplesResult.size();
									tuplesResult.add(lastIndex, tuple);
									break;
								}
								else
								{
									break;
								}
							case DOUBLE:
								for(int i=0;i<tuplesResult.size();i++)
								{
										if((Integer)(tuplesResult.get(i).getAttributeList().get(commonIndex).getAttributeValue())>(Integer)(tuple.getAttributeList().get(commonIndex).getAttributeValue()))
										{
											tuplesResult.add(i,tuple);
											check=true;
											break;
										}
								}
								if(check==false){
									int lastIndex=tuplesResult.size();
									tuplesResult.add(lastIndex, tuple);
									break;
								}
								else
								{
									break;
								}
							case LONG:
								for(int i=0;i<tuplesResult.size();i++)
								{
										if((Integer)(tuplesResult.get(i).getAttributeList().get(commonIndex).getAttributeValue())>(Integer)(tuple.getAttributeList().get(commonIndex).getAttributeValue()))
										{
											tuplesResult.add(i,tuple);
											check=true;
											break;
										}
								}
								if(check==false){
									int lastIndex=tuplesResult.size();
									tuplesResult.add(lastIndex, tuple);
									break;
								}
								else
								{
									break;
								}
							case SHORT:
								for(int i=0;i<tuplesResult.size();i++)
								{
										if((Integer)(tuplesResult.get(i).getAttributeList().get(commonIndex).getAttributeValue())>(Integer)(tuple.getAttributeList().get(commonIndex).getAttributeValue()))
										{
											tuplesResult.add(i,tuple);
											check=true;
											break;
										}
								}
								if(check==false){
									int lastIndex=tuplesResult.size();
									tuplesResult.add(lastIndex, tuple);
									break;
								}
								else
								{
									break;
								}
							case FLOAT:
								for(int i=0;i<tuplesResult.size();i++)
								{
										if((Integer)(tuplesResult.get(i).getAttributeList().get(commonIndex).getAttributeValue())>(Integer)(tuple.getAttributeList().get(commonIndex).getAttributeValue()))
										{
											tuplesResult.add(i,tuple);
											check=true;
											break;
										}
								}
								if(check==false){
									int lastIndex=tuplesResult.size();
									tuplesResult.add(lastIndex, tuple);
									break;
								}
								else
								{
									break;
								}
							case STRING: 
								for(int i=0;i<tuplesResult.size();i++)
								{
									if(tuplesResult.get(i).getAttributeList().get(commonIndex).getAttributeValue().toString().compareTo(tuple.getAttributeList().get(commonIndex).getAttributeValue().toString())>0)
									{
										tuplesResult.add(i,tuple);
										break;
									}
									else if(tuplesResult.get(i).getAttributeList().get(commonIndex).getAttributeValue().toString().compareTo(tuple.getAttributeList().get(commonIndex).getAttributeValue().toString())<0)
									{
										tuplesResult.add(i+1,tuple);
										break;
									}
								}
								break;
						}
					}
				}
				tuple=child.next();

			}
		}
		if(tuplesResult.isEmpty()==false)
		{
			return tuplesResult.remove(0);
		}
		return null;
		
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}