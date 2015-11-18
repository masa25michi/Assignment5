package simpledatabase;
import java.util.ArrayList;

public class Selection extends Operator{
	
	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;
	
	private Tuple tuple=new Tuple(null);

	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();
	}
	/**
     * Get the tuple which match to the where condition
     * @return the tuple
     */
	@Override
	public Tuple next(){
		if(child==null)
		{
			return null;
		}
		tuple = child.next();
		if(tuple==null){
			return null;
		}
		
		if(whereAttributePredicate.isEmpty()||whereValuePredicate.isEmpty())
		{
			return tuple;
		}
		
		while(tuple!=null)
		{
			attributeList=tuple.getAttributeList();
			for(int i=0;i<attributeList.size();i++)
			{
				if(attributeList.get(i).getAttributeName().equals(whereAttributePredicate)&&attributeList.get(i).getAttributeValue().toString().equals(whereValuePredicate))
				{
					Tuple tuple1=new Tuple(attributeList);
					attributeList=new ArrayList<Attribute>();
					return tuple1;
				}
			}
			tuple=child.next();
		}
		return null;
	}

	
	
	
	
		
//		while(true)
//		{
//			if(tuple==null)
//			{
//				return null;
//			}
//			else
//			{
//				if(whereAttributePredicate.equals("")&&whereValuePredicate.equals(""))//for selecting from Student.csv
//				{
//					return tuple;
//				}
//				attributeList=tuple.getAttributeList();
//				for(int i=0;i<attributeList.size();i++)
//				{
//					if(tuple.getAttributeName(i).equals(whereAttributePredicate)==true)
//					{
//						if(tuple.getAttributeValue(i).toString().equals(whereValuePredicate)==true)
//						{
//							return tuple;
//						}
//					}
//				}
//			}
//		}
//	}
		
		
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		
		return(child.getAttributeList());
	}

	
}