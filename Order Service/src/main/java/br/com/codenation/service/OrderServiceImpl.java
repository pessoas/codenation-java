package br.com.codenation.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

public class OrderServiceImpl implements OrderService {

	private ProductRepository productRepository = new ProductRepositoryImpl();

	/**
	 * Calculate the sum of all OrderItems
	 */
	@Override
	public Double calculateOrderValue(List<OrderItem> items) {

		return items.stream()
				.filter(e -> {
					return this.checkId(e.getProductId());
				})
				.mapToDouble(e -> {
					Product prod;
					if (productRepository.findById(e.getProductId()).isPresent()) {
						prod = productRepository.findById(e.getProductId()).get();
						if(productRepository.findById(e.getProductId()).get().getIsSale()){
							return prod.getValue() * e.getQuantity() * 0.8;
						} else {
							return prod.getValue() * e.getQuantity();
						}
					}
					return 0;
				}).sum();
	}

	/**
	 * Map from idProduct List to Product Set
	 */
	@Override
	public Set<Product> findProductsById(List<Long> ids) {

		return ids.stream()
				.filter(this::checkId)
				.map(e -> {
					if (productRepository.findById(e).isPresent()) {
						return productRepository.findById(e).get();
					} return  null;
				})
				.collect(Collectors.toSet());
	}

	/**
	 * Calculate the sum of all Orders(List<OrderIten>)
	 */
	@Override
	public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
		return orders.stream()
				.mapToDouble(this::calculateOrderValue)
				.sum();
	}

	/**
	 * Group products using isSale attribute as the map key
	 */
	@Override
	public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {
		//Set<Product> products = this.findProductsById(productIds);

		return  this.findProductsById(productIds).stream()
				.collect(Collectors.groupingBy(Product::getIsSale));

		/*
		return productIds.stream()
				.filter(this::checkId)
				.collect(Collectors.groupingBy(e -> {
					return productRepository.findById(e).get().getIsSale();
				}), this::findProductsById);*/
		//return null;
	}



	public boolean checkId(Long id) {
		return productRepository.findById(id).isPresent();
	}



}