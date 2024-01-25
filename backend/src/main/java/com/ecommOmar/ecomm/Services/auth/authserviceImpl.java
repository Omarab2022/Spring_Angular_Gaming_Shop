package com.ecommOmar.ecomm.Services.auth;


import com.ecommOmar.ecomm.Entity.Order;
import com.ecommOmar.ecomm.Entity.User;
import com.ecommOmar.ecomm.Enums.OrderStatus;
import com.ecommOmar.ecomm.Enums.UserRole;
import com.ecommOmar.ecomm.Repository.OrderRepository;
import com.ecommOmar.ecomm.Repository.userRepository;
import com.ecommOmar.ecomm.dto.SignupRequest;
import com.ecommOmar.ecomm.dto.UserDto;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class authserviceImpl implements authservice{


	@Autowired
	private userRepository  userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder ;


	@Autowired
	private OrderRepository orderRepository ;

	public UserDto createUser(SignupRequest signupRequest){

		User user = new User();

		user.setEmail(signupRequest.getEmail());
		user.setName(signupRequest.getName());
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
		user.setRole(UserRole.CUSTUMER);


		User createduser =userRepository.save(user);

		Order order = new Order();
		order.setAmount(0L);
		order.setTotalAmount(0L);
		order.setDiscount(0L);
		order.setUser(createduser);
		order.setOrderStatus(OrderStatus.Pending);

		orderRepository.save(order);

		UserDto userDto = new UserDto();
		userDto.setId(createduser.getId());


		return userDto;

	}


	public Boolean hasUserWithEmail(String email){
		return userRepository.findFirstByEmail(email).isPresent();
	}


	@PostConstruct
	public void createAdminAccount(){
		User adminaccount = userRepository.findByRole(UserRole.ADMIN);

		if (adminaccount == null) {
			User user = new User();
			user.setEmail("admin@gmail.com");
			user.setName("admin");
			user.setRole(UserRole.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));

			userRepository.save(user);
		}
	}

}
