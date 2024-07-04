package com.example.springauthenticationwithmetamask;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MetaMaskAuthenticationProviderTest {
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private PhantomWalletAuthenticationProvider metamaskAuthenticationProvider;

    @Test
    void isSignatureValid() {
        when(userRepository.getUser("0x064Dc0591f0d640721033bC5A43D2a977a3Fb61a"))
                .thenReturn(new User("0x2F91E40327F291F6a8363ff8C3F450E8535568b4", 755402));
        assertTrue(
                metamaskAuthenticationProvider.isSignatureValid(
                        "0x2425e46b85d73c16db02015b5c58c1fcfc6eaae7934c3a8657ae57c408a3c7eb1b82e8f71ca3e87c3fd705074e19193a9f184e655c35b7cf9d8691750a20979f1b",
                        "0x064Dc0591f0d640721033bC5A43D2a977a3Fb61a",
                        755402)
        );
    }
}
