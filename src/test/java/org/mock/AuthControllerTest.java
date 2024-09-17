package org.mock;

import com.movieReservation.DTOs.requestsDTO.UserRegisterRequest;
import com.movieReservation.services.AuthService;
import com.movieReservation.services.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.startsWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AuthControllerTest {
	/* Integration Tests */
	@Mock
	private UserRepository userRepository;
	@MockBean
	AuthService authService;
	@Autowired
	private MockMvc mockMvc;

	@Test
		void WHEN_the_User_get_LogUp_with_POST_THEN_the_state_is_200() throws Exception {
		// When
			//doNothing().when(authService.registerUser(any()));

		// Then

			mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
							.content("{ " +
									"\"name\": \"John\", " +
									"\"lastName\": \"Doe\", " +
									"\"email\": \"john.doe@example.com\", " +
									"\"rol\": [\"USER\"], " +
									"\"password\": \"password123\"" +
									"}")
							.contentType(MediaType.APPLICATION_JSON))
							.andExpect(status().isCreated());

		verify(authService, times(1)).registerUser(any());

		}
	/*
		@Test
	void WHEN_the_User_get_LogIn_with_POST_THEN_get_jwt() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/auth/log-in")
						.content("{ " +
								"\"email\": \"john.doe@example.com\", " +
								"\"password\": \"password123\"" +
								"}")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(assertNotNull().);


	}


	 */
}
