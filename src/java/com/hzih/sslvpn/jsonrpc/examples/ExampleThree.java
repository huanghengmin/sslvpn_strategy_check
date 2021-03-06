package com.hzih.sslvpn.jsonrpc.examples;


import com.hzih.sslvpn.jsonrpc.JSONRPC2Error;
import com.hzih.sslvpn.jsonrpc.JSONRPC2ParamsType;
import com.hzih.sslvpn.jsonrpc.JSONRPC2ParseException;
import com.hzih.sslvpn.jsonrpc.JSONRPC2Request;
import com.hzih.sslvpn.jsonrpc.util.NamedParamsRetriever;

import java.util.Map;


/** 
 * This example demonstrates the use of the utility classes 
 * {@link com.hzih.sslvpn.jsonrpc.util.PositionalParamsRetriever}
 * and {@link com.hzih.sslvpn.jsonrpc.util.NamedParamsRetriever} to
 * extract parameters from incoming requests and notifications with proper type 
 * and optional value checking.
 *
 * @author Vladimir Dzhuvinov
 */
public class ExampleThree {


	public static void main(String[] args) {
	
		// Request string with 3 positional parameters
		//    1. parameter "amount" - number, mandatory
		//    2. parameter "recipient" - string, mandatory
		//    3. parameter "confirmation" - boolean, optional
		String json = "{" +
		              "\"method\":\"makePayment\"," +
		              "\"params\":{\"amount\":175.05,\"recipient\":\"Penny Adams\"}," +
			      "\"id\":0," +
			      "\"jsonrpc\":\"2.0\"" +
			      "}";
	
		System.out.println("Parsing request JSON string: \n\n" + json + "\n\n");
		
		
		// Parse the request
		JSONRPC2Request request = null;
		
		try {
			request = JSONRPC2Request.parse(json);
			
		} catch (JSONRPC2ParseException e) {
			System.out.println(e);
			return;
		}
		
		String method = request.getMethod();
		JSONRPC2ParamsType paramsType = request.getParamsType();
		
		System.out.println("Received request for method \"" + method + "\" " +
		                   "with params of type " + paramsType);
		
		// Create named parameters instance from params Map
		Map<String,Object> params = request.getNamedParams();
		NamedParamsRetriever np = new NamedParamsRetriever(params);
		
		// Extract parameters
		try {
			// Extract mandatory "recipient" parameter
			String recipient = np.getString("recipient");
			System.out.println("\tparam \"recipient\": " + recipient);
			
			// Extract mandatory "amount" parameter
			double amount = np.getDouble("amount");
			System.out.println("\tparam \"amount\": " + amount);
			
			// Extract optional "confirmation" parameters, defaults to false
			// if undefined
			boolean confirmation = np.getOptBoolean("confirmation", false);
			System.out.println("\tparam \"confirmation\" [optional]: " + confirmation);
		
		} catch (JSONRPC2Error e) {
			// If a mandatory parameter is missing or there is a type mismatch
			// you get an exception
			System.out.println(e);
			return;
		}
	}
}
