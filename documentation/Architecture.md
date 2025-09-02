# Test Cases for Full Sequence Diagram

## Test Suite: Authentication Flows

### Test Case 1.1: User Registration - Happy Path
- **Objective**: Verify successful user registration
- **Preconditions**: User is not registered in the system
- **Test Steps**:
    1. Send POST request to `/register` with valid registration data
    2. Verify response status code is 201
    3. Verify user is created in the database
- **Expected Results**:
    - User is successfully registered
    - Response contains success status
    - User data is stored in the database

### Test Case 1.2: User Registration - Duplicate User
- **Objective**: Prevent duplicate user registration
- **Preconditions**: User with same email/username exists
- **Test Steps**:
    1. Send POST request to `/register` with existing user's email/username
    2. Verify response status code is 409
- **Expected Results**:
    - Registration is rejected
    - Appropriate error message is returned
    - No duplicate user is created

### Test Case 1.3: User Login - Valid Credentials
- **Objective**: Verify successful login with valid credentials
- **Preconditions**: User is registered in the system
- **Test Steps**:
    1. Send POST request to `/login` with valid credentials
    2. Verify response status code is 200
    3. Verify response contains authentication token
- **Expected Results**:
    - User is authenticated
    - Valid JWT token is returned
    - Token contains correct user claims

## Test Suite: User Management

### Test Case 2.1: Get User Information
- **Objective**: Verify retrieval of authenticated user's information
- **Preconditions**: User is logged in
- **Test Steps**:
    1. Send GET request to `/users/me` with valid auth token
    2. Verify response status code is 200
    3. Verify response contains correct user data
- **Expected Results**:
    - User data is returned
    - Sensitive information (password) is not exposed
    - Response matches user's actual data

### Test Case 2.2: Update User Information
- **Objective**: Verify user can update their information
- **Preconditions**: User is logged in
- **Test Steps**:
    1. Send PATCH request to `/users/me` with updated data
    2. Verify response status code is 200
    3. Verify database reflects the changes
- **Expected Results**:
    - User data is updated
    - Response contains updated user information
    - Changes are persisted in the database

## Test Suite: Advertisement Management

### Test Case 3.1: Create New Advertisement
- **Objective**: Verify creation of new advertisement
- **Preconditions**: User is logged in
- **Test Steps**:
    1. Send POST request to `/ads` with ad data and image
    2. Verify response status code is 201
    3. Verify ad is created in the database
    4. Verify image is stored in file storage
- **Expected Results**:
    - New ad is created with provided data
    - Image is properly stored
    - Response contains created ad details

### Test Case 3.2: Get Advertisement Details
- **Objective**: Verify retrieval of advertisement details
- **Preconditions**: Advertisement exists in the system
- **Test Steps**:
    1. Send GET request to `/ads/{id}`
    2. Verify response status code is 200
    3. Verify response contains correct ad details
- **Expected Results**:
    - Correct ad details are returned
    - Response includes all required fields
    - Image URL is accessible

## Test Suite: Comment Management

### Test Case 4.1: Add Comment to Advertisement
- **Objective**: Verify adding comment to an advertisement
- **Preconditions**: User is logged in, advertisement exists
- **Test Steps**:
    1. Send POST request to `/ads/{id}/comments` with comment text
    2. Verify response status code is 200
    3. Verify comment is stored in the database
- **Expected Results**:
    - Comment is successfully added
    - Response contains created comment details
    - Comment is associated with correct ad and user

### Test Case 4.2: Update Comment
- **Objective**: Verify comment can be updated
- **Preconditions**: User is logged in, comment exists
- **Test Steps**:
    1. Send PATCH request to `/ads/{adId}/comments/{commentId}` with updated text
    2. Verify response status code is 200
    3. Verify comment is updated in the database
- **Expected Results**:
    - Comment text is updated
    - Last modified timestamp is updated
    - Response contains updated comment

### Test Case 4.3: Delete Comment
- **Objective**: Verify comment can be deleted
- **Preconditions**: User is logged in, comment exists
- **Test Steps**:
    1. Send DELETE request to `/ads/{adId}/comments/{commentId}`
    2. Verify response status code is 200
    3. Verify comment is removed from the database
- **Expected Results**:
    - Comment is deleted
    - Response indicates successful deletion
    - Comment is no longer retrievable

## Test Suite: Error Handling

### Test Case 5.1: Unauthorized Access
- **Objective**: Verify unauthorized access is prevented
- **Preconditions**: User is not logged in
- **Test Steps**:
    1. Send GET request to `/users/me` without authentication token
    2. Verify response status code is 401
- **Expected Results**:
    - Access is denied
    - Appropriate error message is returned
    - No sensitive data is exposed

### Test Case 5.2: Resource Not Found
- **Objective**: Verify proper handling of non-existent resources
- **Preconditions**: User is logged in
- **Test Steps**:
    1. Send GET request to `/ads/999999` (non-existent ID)
    2. Verify response status code is 404
- **Expected Results**:
    - Appropriate "not found" response is returned
    - Response includes meaningful error message

These test cases cover the main flows shown in the sequence diagram, including authentication, user management, advertisement management, and comment management, along with error handling scenarios.