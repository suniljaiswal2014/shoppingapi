package com.wheatroot.shoppingapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_payment")
public class UserPayment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String payment_type;
	private String provider;
	private String account_number;
	private String expiry_date;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
	public UserPayment() {
		
	}
	public UserPayment(Long id, String payment_type, String provider, String account_number, String expiry_date,
			User user) {
		super();
		this.id = id;
		this.payment_type = payment_type;
		this.provider = provider;
		this.account_number = account_number;
		this.expiry_date = expiry_date;
		this.user = user;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "UserPayment [id=" + id + ", payment_type=" + payment_type + ", provider=" + provider
				+ ", account_number=" + account_number + ", expiry_date=" + expiry_date + ", user=" + user + "]";
	}
}
