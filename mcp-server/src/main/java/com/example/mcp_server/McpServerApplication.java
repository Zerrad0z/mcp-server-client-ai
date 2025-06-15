package com.example.mcp_server;

import com.example.mcp_server.tools.ClientTools;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class McpServerApplication {

	public static void main(String[] args) {SpringApplication.run(McpServerApplication.class, args);}

	/**
	 * Cette méthode crée et configure un MethodToolCallbackProvider qui permet au
	 * serveur MCP (Model Context Protocol) de découvrir et d'exposer automatiquement
	 * toutes les méthodes annotées avec @Tool dans la classe ClientTools.
	 *
	 * Le builder pattern est utilisé pour construire le provider avec les objets
	 * contenant les outils (tools). Tous les outils définis dans ClientTools seront
	 * automatiquement disponibles pour l'agent IA.
	 */
	@Bean
	public MethodToolCallbackProvider getMethodToolCallbackProvider(){
		return MethodToolCallbackProvider.builder()
				.toolObjects(new ClientTools())
				.build();
	}
}
