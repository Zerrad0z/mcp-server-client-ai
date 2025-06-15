package com.example.mcp_client.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
public class AIAgent {
    private ChatClient chatClient;


    public AIAgent(ChatClient.Builder chatClient,ToolCallbackProvider toolCallbackProvider) {
        this.chatClient = chatClient
                .defaultToolCallbacks(toolCallbackProvider)
                .defaultSystem("...................")
                .build();
    }


    public String askLLM(String query){
        return chatClient.prompt()
                .user(query)
                .call()
                .content();
    }
}
