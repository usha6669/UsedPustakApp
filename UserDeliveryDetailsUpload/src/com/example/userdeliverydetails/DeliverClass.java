package com.example.userdeliverydetails;

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

public class DeliverClass {

	AmazonSimpleDBClient sdbClient;
	String nextToken;
	
	private static final String REG_DOMAIN = "USEDPUSTAK_DELIVERDETAILS";
	
	public DeliverClass()
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

	public List<Others1> getnames() {
		// TODO Auto-generated method stub
		
		SelectRequest selectRequest = new SelectRequest(
				"select USERNAME from USEDPUSTAK_DELIVERDETAILS").withConsistentRead(true);
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


	private Others1 individulaData1(Item item) {
		// TODO Auto-generated method stub
		return new Others1(this.getnames1(item));
	}

	private String getnames1(Item item) {
		// TODO Auto-generated method stub
		String nam=getAllStringAttribute1("USERNAME", item.getAttributes());
		return nam;
	}


	private String getAllStringAttribute1(String usernameAttribute,
			List<Attribute> list) {
		// TODO Auto-generated method stub
		for (Attribute attrib : list) {
			if (attrib.getName().equals(usernameAttribute)) {
				return attrib.getValue();
			}
		}

		return "";

	}
	
	

	public List<Others1> getDetails(String sname) {
		// TODO Auto-generated method stub
		
		SelectRequest selectRequest = new SelectRequest(
				"select * from USEDPUSTAK_DELIVERDETAILS where USERNAME='"+sname+"'").withConsistentRead(true);
		selectRequest.setNextToken(this.nextToken);

		SelectResult response = this.sdbClient.select(selectRequest);
		System.out.println("hello          " + response.getItems().toString());
		return response.getItems(); 
	   }

	public void updateTable(String uname, String status) {
		// TODO Auto-generated method stub
		
		ReplaceableAttribute name1 = new ReplaceableAttribute("USERNAME",uname,Boolean.TRUE);
		ReplaceableAttribute status1 = new ReplaceableAttribute("STATUS",status,Boolean.TRUE);
		
         List<ReplaceableAttribute> attr = new ArrayList <ReplaceableAttribute>();
		
		attr.add(name1);
		attr.add(status1);
		
		PutAttributesRequest par  = new PutAttributesRequest(REG_DOMAIN,uname, attr);
		
		try {
			
			sdbClient.putAttributes(par);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception=" +e);
		}
		
	}

	
		
	}
	
	
	
	

