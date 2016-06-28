package com.shop.action;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.shop.model.GetOrderRequest;

public class GetOrderItemsHandler implements RequestHandler<GetOrderRequest, ShoppingList> {

	private LambdaLogger logger;
	Region region = Region.getRegion(Regions.US_EAST_1);

	public ShoppingList handleRequest(GetOrderRequest request, Context lambdaContext) {
		logger = lambdaContext.getLogger();
		logger.log("GetOrderItemsHandler Input --->" + request);

		ShoppingList order = new ShoppingList();
		if (request != null) {
			String orderId = request.getOrderId();
			if (orderId != null && orderId.trim().length() > 0) {
				AmazonDynamoDBClient client = new AmazonDynamoDBClient();
				client.setRegion(region);
				DynamoDBMapper mapper = new DynamoDBMapper(client);

				order = mapper.load(ShoppingList.class, orderId);
				logger.log("Lambda Log --->" + order.getItems());
			}

		}
		return order;
	}

}
