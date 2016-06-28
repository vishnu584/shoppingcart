package com.shop.action;

import java.util.Set;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="ShoppingList")
public class ShoppingList {

	private String listId;
	private Set<String> items;

	@DynamoDBHashKey(attributeName="ListId")
	public String getListId() {
		return listId;
	}

	public void setListId(String listId) {
		this.listId = listId;
	}

	@DynamoDBAttribute(attributeName="items")
	public Set<String> getItems() {
		return items;
	}

	public void setItems(Set<String> items) {
		this.items = items;
	}

}
