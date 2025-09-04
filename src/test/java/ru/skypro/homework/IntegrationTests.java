package ru.skypro.homework;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = HomeworkApplication.class)
@AutoConfigureWebMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private CommentRepository commentRepository;

    private String authToken;
    private Long userId;
    private Long adId;
    private Long commentId;

    @BeforeEach
    void setUp() {
        // Clean up test data
        commentRepository.deleteAll();
        advertisementRepository.deleteAll();
        userRepository.deleteAll();
    }

    // ==================== Authentication Flow Tests ====================

    @Test
    void testCase1_1_UserRegistration_HappyPath() throws Exception {
        UserRegistrationDto registrationDto = new UserRegistrationDto();
        registrationDto.setUsername("testuser");
        registrationDto.setEmail("test@example.com");
        registrationDto.setPassword("password123");

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is("User registered successfully")));

        // Verify user is created in database
        User createdUser = userRepository.findByEmail("test@example.com").orElse(null);
        assertNotNull(createdUser);
        assertEquals("testuser", createdUser.getUsername());
    }

    @Test
    void testCase1_2_UserRegistration_DuplicateUser() throws Exception {
        // Create existing user
        User existingUser = new User();
        existingUser.setUsername("existinguser");
        existingUser.setEmail("existing@example.com");
        existingUser.setPassword("password123");
        userRepository.save(existingUser);

        UserRegistrationDto registrationDto = new UserRegistrationDto();
        registrationDto.setUsername("newuser");
        registrationDto.setEmail("existing@example.com"); // Same email
        registrationDto.setPassword("password123");

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationDto)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error", is("User with this email already exists")));
    }

    @Test
    void testCase1_3_UserLogin_ValidCredentials() throws Exception {
        // Create user for login
        User user = new User();
        user.setUsername("loginuser");
        user.setEmail("login@example.com");
        user.setPassword("$2a$10$encodedPassword"); // BCrypt encoded
        User savedUser = userRepository.save(user);
        this.userId = savedUser.getId();

        UserLoginDto loginDto = new UserLoginDto();
        loginDto.setEmail("login@example.com");
        loginDto.setPassword("password123");

        MvcResult result = mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token", notNullValue()))
                .andReturn();

        String response = result.getResponse().getContentAsString();
        this.authToken = objectMapper.readTree(response).get("token").asText();
        assertNotNull(authToken);
    }

    // ==================== User Management Tests ====================

    @Test
    void testCase2_1_GetUserInformation() throws Exception {
        setupAuthenticatedUser();

        mockMvc.perform(get("/users/me")
                        .header("Authorization", "Bearer " + authToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("testuser")))
                .andExpected(jsonPath("$.email", is("test@example.com")))
                .andExpect(jsonPath("$.password").doesNotExist()); // Sensitive data not exposed
    }

    @Test
    void testCase2_2_UpdateUserInformation() throws Exception {
        setupAuthenticatedUser();

        String updatedData = "{\"email\":\"newemail@example.com\"}";

        mockMvc.perform(patch("/users/me")
                        .header("Authorization", "Bearer " + authToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedData))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("newemail@example.com")));

        // Verify database reflects changes
        User updatedUser = userRepository.findById(userId).orElse(null);
        assertNotNull(updatedUser);
        assertEquals("newemail@example.com", updatedUser.getEmail());
    }

    // ==================== Advertisement Management Tests ====================

    @Test
    void testCase3_1_CreateNewAdvertisement() throws Exception {
        setupAuthenticatedUser();

        MockMultipartFile image = new MockMultipartFile(
                "image", "test.jpg", "image/jpeg", "test image content".getBytes());

        AdCreationDto adDto = new AdCreationDto();
        adDto.setTitle("Test Advertisement");
        adDto.setDescription("Test Description");
        adDto.setPrice(100.0);

        MockMultipartFile adData = new MockMultipartFile(
                "adData", "", "application/json",
                objectMapper.writeValueAsString(adDto).getBytes());

        MvcResult result = mockMvc.perform(multipart("/ads")
                        .file(image)
                        .file(adData)
                        .header("Authorization", "Bearer " + authToken))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is("Test Advertisement")))
                .andExpect(jsonPath("$.imageUrl", notNullValue()))
                .andReturn();

        String response = result.getResponse().getContentAsString();
        this.adId = objectMapper.readTree(response).get("id").asLong();

        // Verify ad is created in database
        Advertisement createdAd = advertisementRepository.findById(adId).orElse(null);
        assertNotNull(createdAd);
        assertEquals("Test Advertisement", createdAd.getTitle());
    }

    @Test
    void testCase3_2_GetAdvertisementDetails() throws Exception {
        setupAuthenticatedUser();
        setupTestAdvertisement();

        mockMvc.perform(get("/ads/" + adId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(adId.intValue())))
                .andExpect(jsonPath("$.title", is("Test Advertisement")))
                .andExpect(jsonPath("$.description", notNullValue()))
                .andExpect(jsonPath("$.imageUrl", notNullValue()));
    }

    // ==================== Comment Management Tests ====================

    @Test
    void testCase4_1_AddCommentToAdvertisement() throws Exception {
        setupAuthenticatedUser();
        setupTestAdvertisement();

        CommentDto commentDto = new CommentDto();
        commentDto.setText("This is a test comment");

        MvcResult result = mockMvc.perform(post("/ads/" + adId + "/comments")
                        .header("Authorization", "Bearer " + authToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", is("This is a test comment")))
                .andExpect(jsonPath("$.userId", is(userId.intValue())))
                .andReturn();

        String response = result.getResponse().getContentAsString();
        this.commentId = objectMapper.readTree(response).get("id").asLong();

        // Verify comment is stored in database
        Comment createdComment = commentRepository.findById(commentId).orElse(null);
        assertNotNull(createdComment);
        assertEquals("This is a test comment", createdComment.getText());
    }

    @Test
    void testCase4_2_UpdateComment() throws Exception {
        setupAuthenticatedUser();
        setupTestAdvertisement();
        setupTestComment();

        String updatedText = "{\"text\":\"Updated comment text\"}";

        mockMvc.perform(patch("/ads/" + adId + "/comments/" + commentId)
                        .header("Authorization", "Bearer " + authToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedText))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", is("Updated comment text")))
                .andExpect(jsonPath("$.lastModified", notNullValue()));

        // Verify comment is updated in database
        Comment updatedComment = commentRepository.findById(commentId).orElse(null);
        assertNotNull(updatedComment);
        assertEquals("Updated comment text", updatedComment.getText());
    }

    @Test
    void testCase4_3_DeleteComment() throws Exception {
        setupAuthenticatedUser();
        setupTestAdvertisement();
        setupTestComment();

        mockMvc.perform(delete("/ads/" + adId + "/comments/" + commentId)
                        .header("Authorization", "Bearer " + authToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Comment deleted successfully")));

        // Verify comment is removed from database
        Comment deletedComment = commentRepository.findById(commentId).orElse(null);
        assertNull(deletedComment);
    }

    // ==================== Error Handling Tests ====================

    @Test
    void testCase5_1_UnauthorizedAccess() throws Exception {
        mockMvc.perform(get("/users/me"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error", is("Unauthorized access")));
    }

    @Test
    void testCase5_2_ResourceNotFound() throws Exception {
        setupAuthenticatedUser();

        mockMvc.perform(get("/ads/999999")
                        .header("Authorization", "Bearer " + authToken))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Resource not found")));
    }

    // ==================== Helper Methods ====================

    private void setupAuthenticatedUser() throws Exception {
        if (authToken == null) {
            User user = new User();
            user.setUsername("testuser");
            user.setEmail("test@example.com");
            user.setPassword("$2a$10$encodedPassword");
            User savedUser = userRepository.save(user);
            this.userId = savedUser.getId();

            UserLoginDto loginDto = new UserLoginDto();
            loginDto.setEmail("test@example.com");
            loginDto.setPassword("password123");

            MvcResult result = mockMvc.perform(post("/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(loginDto)))
                    .andReturn();

            String response = result.getResponse().getContentAsString();
            this.authToken = objectMapper.readTree(response).get("token").asText();
        }
    }

    private void setupTestAdvertisement() {
        if (adId == null) {
            Advertisement ad = new Advertisement();
            ad.setTitle("Test Advertisement");
            ad.setDescription("Test Description");
            ad.setPrice(100.0);
            ad.setUserId(userId);
            ad.setImageUrl("/images/test.jpg");
            Advertisement savedAd = advertisementRepository.save(ad);
            this.adId = savedAd.getId();
        }
    }

    private void setupTestComment() {
        if (commentId == null) {
            Comment comment = new Comment();
            comment.setText("Test comment");
            comment.setUserId(userId);
            comment.setAdvertisementId(adId);
            Comment savedComment = commentRepository.save(comment);
            this.commentId = savedComment.getId();
        }
    }
}
