package com.tssc.usedpustak;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.Attribute;
import com.amazonaws.services.simpledb.model.CreateDomainRequest;
import com.amazonaws.services.simpledb.model.Item;
import com.amazonaws.services.simpledb.model.PutAttributesRequest;
import com.amazonaws.services.simpledb.model.ReplaceableAttribute;
import com.amazonaws.services.simpledb.model.SelectRequest;
import com.amazonaws.services.simpledb.model.SelectResult;

public class InsertionClass {
   
	AmazonSimpleDBClient sdbClient;
	String nextToken;
	
	private static final String REG_DOMAIN = "USEDPUSTAK_BOOKS";
	
	public InsertionClass()
	{
		AWSCredentials credentials = new BasicAWSCredentials(Constants.ACCESS_KEY_ID, Constants.SECRET_KEY); 
		sdbClient = new AmazonSimpleDBClient(credentials);
		this.nextToken = null;
	}

	public void createDomain() {
		// TODO Auto-generated method stub
		
		CreateDomainRequest cr = new CreateDomainRequest(REG_DOMAIN);
		sdbClient.createDomain(cr);
		
	}

	public void AddToTable(String sid, String bname, float sprice,
			               String spages, String slanguage, String scondition,String sclass,String sboard, int savail) {
		// TODO Auto-generated method stub
		
		ReplaceableAttribute bid = new ReplaceableAttribute("SUBJECTID",sid,Boolean.TRUE);
		ReplaceableAttribute bname1 = new ReplaceableAttribute("SUBJECTNAME",bname,Boolean.TRUE);
		ReplaceableAttribute bprice = new ReplaceableAttribute("PRICE",Float.toString(sprice),Boolean.TRUE);
		
		ReplaceableAttribute bpages = new ReplaceableAttribute("PAGES",spages,Boolean.TRUE);
		ReplaceableAttribute blanguage = new ReplaceableAttribute("LANGUAGE",slanguage,Boolean.TRUE);
		ReplaceableAttribute bcondition = new ReplaceableAttribute("CONDITION",scondition,Boolean.TRUE);
		ReplaceableAttribute bclass = new ReplaceableAttribute("CLASS",sclass,Boolean.TRUE);
		ReplaceableAttribute bboard = new ReplaceableAttribute("BRANCH",sboard,Boolean.TRUE);
		ReplaceableAttribute bavail = new ReplaceableAttribute("AVAILABILITY",Integer.toString(savail),Boolean.TRUE);
		
		List<ReplaceableAttribute> attr = new ArrayList <ReplaceableAttribute>();
		
		attr.add(bid);
		attr.add(bname1);
		attr.add(bprice);
		attr.add(bpages);
		attr.add(blanguage);
		attr.add(bcondition);
		attr.add(bclass);
		attr.add(bboard);
		attr.add(bavail);
		
		PutAttributesRequest par  = new PutAttributesRequest(REG_DOMAIN,sid, attr);
		
		try {
			
			sdbClient.putAttributes(par);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception=" +e);
		}
		
	}

	
	//retrieval
	public List<Others1> getnames(String sclass, String sbranch) {
		// TODO Auto-generated method stub
		
		SelectRequest selectRequest = new SelectRequest(
				"select SUBJECTNAME from USEDPUSTAK_BOOKS where CLASS ='"+sclass+"' and BRANCH ='"+sbranch+"'").withConsistentRead(true);
		selectRequest.setNextToken(this.nextToken);

		SelectResult response = this.sdbClient.select(selectRequest);

		 List<String> ls= response.getItems(); 

		 return this.valuesGetting1(response.getItems());
	
		
	}

	private List<Others1> valuesGetting1(List<Item> items) {
		// TODO Auto-generated method stub
		ArrayList<Others1> alldata = new ArrayList<Others1>(items.size());

		for (Item item : items) {
			alldata.add(this.individulaData1(item));
		}

		System.out.println("all data size        " + alldata.size());
		for (int i = 0; i < alldata.size(); i++) {
			System.out.println(" name  " + alldata.get(i));
		}
		return alldata;
	}

	private Others1  individulaData1(Item item) {
		// TODO Auto-generated method stub
		
		return new Others1(this.getnames1(item));
	}
	private String getnames1(Item item) {
		// TODO Auto-generated method stub
		
		String nam=getAllStringAttribute1("SUBJECTNAME", item.getAttributes());
		return nam;
	}

	private String getAllStringAttribute1(String subjectName, List<Attribute> list) {
		// TODO Auto-generated method stub
		
		for (Attribute attrib : list) {
			if (attrib.getName().equals(subjectName)) {
				return attrib.getValue();
			}
		}

		return "";
	}
	
	public List<Others1> getDetails(String sname) {
		// TODO Auto-generated method stub
		
		SelectRequest selectRequest = new SelectRequest(
				"select * from USEDPUSTAK_BOOKS where SUBJECTNAME='"+sname+"' ").withConsistentRead(true);
		selectRequest.setNextToken(this.nextToken);

		SelectResult response = this.sdbClient.select(selectRequest);
		System.out.println("hello          " + response.getItems().toString());
		return response.getItems(); 
	   }


	public void updateTable(String sid, String sname, String class1, String branch, String availability) {
		// TODO Auto-generated method stub
		
		ReplaceableAttribute bbranch = new ReplaceableAttribute("BRANCH",branch,Boolean.TRUE);
		ReplaceableAttribute bavail = new ReplaceableAttribute("AVAILABILITY",availability,Boolean.TRUE);
		ReplaceableAttribute bclass = new ReplaceableAttribute("CLASS",class1,Boolean.TRUE);
		ReplaceableAttribute bname = new ReplaceableAttribute("SUBJECTNAME",sname,Boolean.TRUE);
		ReplaceableAttribute bsid = new ReplaceableAttribute("SUBJECTID",sid,Boolean.TRUE);

           List<ReplaceableAttribute> attr = new ArrayList <ReplaceableAttribute>();
		
		attr.add(bbranch);
		attr.add(bavail);
		attr.add(bclass);
		attr.add(bname);
		attr.add(bsid);

		PutAttributesRequest par  = new PutAttributesRequest(REG_DOMAIN,sid, attr);
		
		try {
			
			sdbClient.putAttributes(par);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception=" +e);
		}
		
	}

	
	
	
}
