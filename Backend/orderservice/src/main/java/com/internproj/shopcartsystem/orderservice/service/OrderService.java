package com.internproj.shopcartsystem.orderservice.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internproj.shopcartsystem.orderservice.entities.OrderDetails;
import com.internproj.shopcartsystem.orderservice.entities.TransactionDetails;
import com.internproj.shopcartsystem.orderservice.externalservice.InventoryService;
import com.internproj.shopcartsystem.orderservice.repositories.OrderRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class OrderService {
	
	private static final String ORDER_PLACED = "Placed";
	private static final String KEY = "rzp_test_cl3nZjitl1ABkw";
	private static final String KEY_SECRET = "FSitF1EFVrIjwxdOoUJplXxn";
	private static final String CURRENCY = "INR";
	
	@Autowired
	OrderRepository repo;
	
	@Autowired
	private InventoryService service;

	public String placeOrder(OrderDetails order) {
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
	    String strDate = formatter.format(date);
	    order.setOrder_date(strDate);
		repo.save(order);
		service.updateQuantity(order.getProductId(),order.getQuantity());
		return "Your product is on the way";
	}
	
	public String cancelOrder(int orderId) {
		repo.deleteById(orderId);
		OrderDetails order = repo.findById(orderId).orElse(null);
		return "Order is cancelled successfully";
	}
	


	public TransactionDetails createTransaction(Double amount) {
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("amount", (amount*100));
			jsonObject.put("currency", CURRENCY);
			jsonObject.put("amount", amount);
			RazorpayClient razorpayClient = new RazorpayClient(KEY,KEY_SECRET);
			Order order = razorpayClient.orders.create(jsonObject);
			//System.out.println(order );
			return prepareTransactionDetails(order);
		} catch (RazorpayException e) {
			e.printStackTrace();
			//System.out.println("failed");
		}
		return null;
	}
	
	private TransactionDetails prepareTransactionDetails(Order order) {
		String orderId = order.get("id");
		String currency = order.get("currency");
		Integer amount = order.get("amount");
		
		TransactionDetails transactionDetails = new TransactionDetails(orderId,currency,amount,KEY);
		return transactionDetails;
	}
	
	public List<OrderDetails> viewOrdersByUserName(String name) {
		return repo.findAllByNameOfUser(name);
	}
	
}
