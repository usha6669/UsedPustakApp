package com.tssc.usedpustak;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.CreateDomainRequest;
import com.amazonaws.services.simpledb.model.PutAttributesRequest;
import com.amazonaws.services.simpledb.model.ReplaceableAttribute;
import com.amazonaws.services.simpledb.model.SelectRequest;
import com.amazonaws.services.simpledb.model.SelectResult;

public class RegistrationClass {

	AmazonSimpleDBClient sdbClient;
	String nextToken;
	
	private static final String REG_DOMAIN = "USEDPUSTAK_USERDETAILS";
	
	public RegistrationClass()
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

	public void AddToTable(String name, String address, String mobile,
			String email, String pwd) {
		// TODO Auto-generated method stub
		
		ReplaceableAttribute uname = new ReplaceableAttribute("USERNAME",name,Boolean.TRUE);
		ReplaceableAttribute uaddress = new ReplaceableAttribute("ADDRESS",address,Boolean.TRUE);
		ReplaceableAttribute umobile = new ReplaceableAttribute("MOBILENUMBER",mobile,Boolean.TRUE);
		ReplaceableAttribute uemail = new ReplaceableAttribute("E_MAIL",email,Boolean.TRUE);
		ReplaceableAttribute upwd = new ReplaceableAttribute("PASSWORD",pwd,Boolean.TRUE);
		
         List<ReplaceableAttribute> attr = new ArrayList <ReplaceableAttribute>();
		
		attr.add(uname);
		attr.add(uaddress);
		attr.add(umobile);
		attr.add(uemail);
		attr.add(upwd);
		
		PutAttributesRequest par  = new PutAttributesRequest(REG_DOMAIN,name, attr);
		try {
			
			sdbClient.putAttributes(par);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception=" +e);
		}
	}

	public int loginVerify(String email,String pwd) {
		// TODO Auto-generated method stub
		
		int flag = 0;
		SelectRequest selectRequest = new SelectRequest(
				"select * from USEDPUSTAK_USERDETAILS where E_MAIL='"+email+"' and PASSWORD='"+ pwd +"'").withConsistentRead(true);
		selectRequest.setNextToken(this.nextToken);

		SelectResult response = this.sdbClient.select(selectRequest);
		@SuppressWarnings("unchecked")
		List<String> ls = response.getItems();
		
		this.nextToken = response.getNextToken();
		System.out.println("@@" + this.nextToken);

		if (ls.size() > 0) {
			flag = 1;
		} else {
			flag = 0;
		}
		return flag;

	}

	public List<Others1> getDetails(String semail) {
		// TODO Auto-generated method stub
		SelectRequest selectRequest = new SelectRequest(
				"select * from USEDPUSTAK_USERDETAILS where E_MAIL ='"+semail+"'").withConsistentRead(true);
		selectRequest.setNextToken(this.nextToken);

		SelectResult response = this.sdbClient.select(selectRequest);
		System.out.println("hello          " + response.getItems().toString());
		return response.getItems(); 
		
	}

	public List<Others1> getDetails1(String uname) {
		// TODO Auto-generated method stub
		SelectRequest selectRequest = new SelectRequest(
				"select * from USEDPUSTAK_USERDETAILS where USERNAME ='"+uname+"'").withConsistentRead(true);
		selectRequest.setNextToken(this.nextToken);

		SelectResult response = this.sdbClient.select(selectRequest);
		System.out.println("hello          " + response.getItems().toString());
		return response.getItems(); 
	
	}
	
}
