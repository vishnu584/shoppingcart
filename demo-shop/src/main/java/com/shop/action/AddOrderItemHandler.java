package com.shop.action;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.SaveBehavior;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddOrderItemHandler implements RequestHandler<ShoppingList, String>{

	private  LambdaLogger logger;
	 Region region = Region.getRegion(Regions.US_EAST_1);
	public String handleRequest(ShoppingList order, Context lambdaContext) {
		logger = lambdaContext.getLogger();
		logger.log("Add Item -->"+order);
		String response = "Fail";
		if(order != null){
			AmazonDynamoDBClient client = new AmazonDynamoDBClient();
			client.setRegion(region);
			DynamoDBMapper mapper = new DynamoDBMapper(client, new DynamoDBMapperConfig(SaveBehavior.APPEND_SET));
			mapper.save(order);
			response = "Successfully added item to order";
		}
		return response;
	}

}
