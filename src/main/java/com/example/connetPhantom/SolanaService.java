package com.example.connetPhantom;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SolanaService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String solanaRpcUrl = "https://api.mainnet-beta.solana.com";

    public SolanaService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public JsonNode getAccountInfo(String publicKey) {
        String requestBody = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"getAccountInfo\",\"params\":[\"" + publicKey + "\", {\"encoding\": \"jsonParsed\"}]}";
        String response = restTemplate.postForObject(solanaRpcUrl, requestBody, String.class);

        try {
            return objectMapper.readTree(response);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
