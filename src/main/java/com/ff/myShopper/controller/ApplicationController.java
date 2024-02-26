package com.ff.myShopper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ff.myShopper.dto.OrderRequest;
import com.ff.myShopper.dto.ProductWithReview;
import com.ff.myShopper.dto.ResponseStructure;
import com.ff.myShopper.entity.Product;
import com.ff.myShopper.entity.Review;
import com.ff.myShopper.entity.Shopper;
import com.ff.myShopper.entity.UserOrder;
import com.ff.myShopper.service.OrderService;
import com.ff.myShopper.service.ProductService;
import com.ff.myShopper.service.ReviewService;
import com.ff.myShopper.service.ShopperService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/shopper")
@Tag(name="Shopper End-Points",description = "Shopper related ")
public class ApplicationController {

	@Autowired
	private ShopperService shopperService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Operation(description = "Save the shopper data and return the shopper data with ID",summary = "register as a shopper")
	@ApiResponses(value = @ApiResponse(description = "Created",responseCode = "201"))
	@PostMapping
	public ResponseEntity<ResponseStructure<Shopper>> registerShopper(@RequestBody Shopper shopper){
		return shopperService.saveShopper(shopper);
	}
	
	@Operation(description = "Returns User based on Email and Password",summary = "Shopper Login")
	@ApiResponses(value = @ApiResponse(description = "OK",responseCode = "200"))
	@PostMapping("/login/{email}/{password}")
	public ResponseEntity<ResponseStructure<Shopper>> loginShopper(@PathVariable String email,@PathVariable String password){
		return shopperService.loginShopper(email,password);
	}
	
	@Operation(description = "Save the product data and return the product data with ID",summary = "Save the product data")
	@ApiResponses(value = {@ApiResponse(description = "Created",responseCode = "201"),
							@ApiResponse(description = "Bad Request",responseCode = "400")})
	@PostMapping("/merchant/{id}/product")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@PathVariable Integer id, @RequestBody Product product){
		return productService.saveProduct(id,product);
	} 
	
	@Operation(description = "Specified customer id all products in order is stored into DB",summary = "Place order")
	@ApiResponses(value = {@ApiResponse(description = "Created",responseCode = "201"),
							@ApiResponse(description = "Bad Request",responseCode = "400")})
	@PostMapping("/customer/{id}/order")
	public ResponseEntity<ResponseStructure<UserOrder>> createOrder(@PathVariable Integer id, @RequestBody OrderRequest request){
		return orderService.createOrder(id,request);
	}
	
	@Operation(description = "Save the review data to corresponding product by using corresponding customer ID",
				summary = "Add a review to delivered product")
	@ApiResponses(value = {@ApiResponse(description = "Created",responseCode = "201"),
							@ApiResponse(description = "Bad Request",responseCode = "400")})
	@PostMapping("/customer/{customerId}/product/{productId}/review")
	public ResponseEntity<ResponseStructure<Review>> addReview(@PathVariable Integer customerId, @PathVariable Integer productId, @RequestBody Review review){
		return reviewService.addReview(customerId,productId,review);
	}
	
	@Operation(description = "returns all review for a product based on product id",
				summary = "Get all the reviews of a product")
	@ApiResponses(value = {@ApiResponse(description = "Created",responseCode = "201"),
							@ApiResponse(description = "Bad Request",responseCode = "400")})
	@GetMapping("/merchant/{merchantId}/product/{productId}")
	public ResponseEntity<ProductWithReview> findReviewOfMyProducts(@PathVariable Integer merchantId,@PathVariable Integer productId){ 
		return shopperService.findReviewOfMyProducts(merchantId,productId);
	}
	
	@Operation(description = "Specified user order retrieved from DB",
				summary = "Display order history")
	@ApiResponses(value = {@ApiResponse(description = "Created",responseCode = "201"),
							@ApiResponse(description = "Bad Request",responseCode = "400")})
	@GetMapping("/customer/{customerId}/orders")
	public ResponseEntity<ResponseStructure<List<UserOrder>>> findOrderByCustomerId(@PathVariable Integer customerId){
		return shopperService.findOrderByCustomerId(customerId);
	}
	
	
}
