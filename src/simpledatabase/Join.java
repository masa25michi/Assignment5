package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;
	
	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
	}

	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		ArrayList<String> attributeName=new ArrayList<String>();
		if(tuples1.isEmpty())
		{
			ArrayList<Tuple> tuples2 = new ArrayList<Tuple>();
			Tuple tupleL=leftChild.child.next();
			Tuple tupleR=rightChild.next();
			if(tupleL!=null && tupleL.getAttributeList()!=null)
			{
				for(int i=0;i<tupleL.getAttributeList().size();i++)
				{
					attributeName.add(tupleL.getAttributeList().get(i).getAttributeName());
					
				}
			}
			while(tupleL!=null)
			{
				tuples2.add(tupleL); 
				tupleL=leftChild.child.next();
				
			}
			ArrayList<Attribute> attributelist;
			String common="";
			int commonIndexR=0;
			int commonIndexL=0;
			while(tupleR!=null)
			{
				for(int i=0;i<tuples2.size();i++)
				{
					attributelist = new ArrayList<Attribute>();
					if(common.isEmpty())
					{
						for(int j=0;j<tupleR.getAttributeList().size();j++)
						{
							common=tupleR.getAttributeList().get(j).getAttributeName();
							if(attributeName.contains(common))
							{
								commonIndexR=j;
								commonIndexL=attributeName.indexOf(common);
								break;
							}
						}
					}
					
					attributelist=tuples2.get(i).getAttributeList();
					if(attributelist.get(commonIndexL).getAttributeValue().toString().equals(tupleR.getAttributeList().get(commonIndexR).getAttributeValue().toString()))
					{
						newAttributeList = new ArrayList<Attribute>();
						newAttributeList.addAll(attributelist);
						for(int z=0;z<tupleR.getAttributeList().size();z++){
							if(z!=commonIndexR)
							{
								newAttributeList.add(tupleR.getAttributeList().get(z));
							}
						}

						Tuple tuple=new Tuple(newAttributeList);
						tuples1.add(tuple);
					}
				}
				tupleR=rightChild.next();
			}
		}
		
		if(tuples1.isEmpty()==false)
		{
			return tuples1.remove(0);
		}
		return null;
	}
		
		

	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList(); 
		else
			return(newAttributeList);
	}

}