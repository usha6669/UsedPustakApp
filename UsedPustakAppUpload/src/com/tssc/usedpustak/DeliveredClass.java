package com.tssc.usedpustak;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.CreateDomainRequest;
import com.amazonaws.services.simpledb.model.PutAttributesRequest;
import com.amazonaws.services.simpledb.model.ReplaceableAttribute;

public class DeliveredClass {
	
	

	AmazonSimpleDBClient sdbClient;
	String nextToken;
	
	private static final String REG_DOMAIN = "USEDPUSTAK_DELIVERDETAILS";
	
	public DeliveredClass()
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

	public void Addtotable(String name, String email, String address,
			String mobile, String bname, String price, String quantity) {
		// TODO Auto-generated method stub
		
		ReplaceableAttribute uname = new ReplaceableAttribute("USERNAME",name,Boolean.TRUE);
		ReplaceableAttribute uemail = new ReplaceableAttribute("EMAIL",email,Boolean.TRUE);
		ReplaceableAttribute uaddress = new ReplaceableAttribute("ADDRESS",address,Boolean.TRUE);
		
		ReplaceableAttribute umobile = new ReplaceableAttribute("MOBILENUMBER",mobile,Boolean.TRUE);
		ReplaceableAttribute usname = new ReplaceableAttribute("BOOK",bname,Boolean.TRUE);
		ReplaceableAttribute uprice = new ReplaceableAttribute("PRICE",price,Boolean.TRUE);
		ReplaceableAttribute uquantity = new ReplaceableAttribute("QUANTITY",quantity,Boolean.TRUE);
		
		
		List<ReplaceableAttribute> attr = new ArrayList <ReplaceableAttribute>();
		
		attr.add(uname);
		attr.add(uemail);
		attr.add(uaddress);
		attr.add(umobile);
		attr.add(usname);
		attr.add(uprice);
		attr.add(uquantity);
		
		
		PutAttributesRequest par  = new PutAttributesRequest(REG_DOMAIN,name, attr);
		
		try {
			
			sdbClient.putAttributes(par);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception=" +e);
		}
		
		
	}
	
	

}
