package com.ykdevs.springcognitosample.infra;

import com.ykdevs.springcognitosample.auth.CognitoProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminAddUserToGroupRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminDeleteUserRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminSetUserPasswordRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminUpdateUserAttributesRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;

@Component
public class CognitoIdentityProviderGateway {

    private CognitoProperty cognitoProperty;
    private CognitoIdentityProviderClient cognitoClient;

    @Autowired
    public CognitoIdentityProviderGateway(
            CognitoProperty cognitoProperty, CognitoIdentityProviderClient cognitoClient) {
        this.cognitoProperty = cognitoProperty;
        this.cognitoClient = cognitoClient;
    }

    public void createUser(String userName, String email) {
        AdminCreateUserRequest createUserRequest = AdminCreateUserRequest.builder()
                .userPoolId(this.cognitoProperty.getUserPoolId())
                .username(userName)
                .userAttributes(
                        AttributeType.builder()
                                .name("email")
                                .value(email)
                                .build(),
                        AttributeType.builder()
                                .name("email_verified")
                                .value("true")
                                .build()
                )
                .build();
        cognitoClient.adminCreateUser(createUserRequest);
    }

    public void setUsrPassword(String email, String password, Boolean isPermanent) {
        AdminSetUserPasswordRequest setUserPasswordRequest = AdminSetUserPasswordRequest.builder()
                .userPoolId(this.cognitoProperty.getUserPoolId())
                .username(email)
                .password(password)
                .permanent(isPermanent)
                .build();
        cognitoClient.adminSetUserPassword(setUserPasswordRequest);
    }

    public void deleteUser(String email) {
        AdminDeleteUserRequest deleteUserRequest = AdminDeleteUserRequest.builder()
                .userPoolId(this.cognitoProperty.getUserPoolId())
                .username(email)
                .build();
        cognitoClient.adminDeleteUser(deleteUserRequest);
    }

    public void updateEmail(String userName, String email) {
        AdminUpdateUserAttributesRequest updateUserRequest = AdminUpdateUserAttributesRequest.builder()
                .userPoolId(this.cognitoProperty.getUserPoolId())
                .username(userName)
                .userAttributes(
                        AttributeType.builder()
                                .name("email")
                                .value(email)
                                .build(),
                        AttributeType.builder()
                                .name("email_verified")
                                .value("true")
                                .build()
                 )
                .build();
        cognitoClient.adminUpdateUserAttributes(updateUserRequest);
    }

    public void addUserToGroup(String userName, String groupName) {
        AdminAddUserToGroupRequest addUserToGroupRequest = AdminAddUserToGroupRequest.builder()
                .userPoolId(this.cognitoProperty.getUserPoolId())
                .username(userName)
                .groupName(groupName)
                .build();
        cognitoClient.adminAddUserToGroup(addUserToGroupRequest);
    }
}
