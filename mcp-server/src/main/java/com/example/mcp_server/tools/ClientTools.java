package com.example.mcp_server.tools;

import org.springframework.ai.tool.annotation.Tool;

import java.util.List;
import java.math.BigDecimal;

public class ClientTools {

    //Une simple liste des clients pour le test
    private List<Client> clients = List.of(
            new Client("Khaldi Hassam","GB12346",26),
            new Client("Salwa marjani","HA98209",25),
            new Client("Bachiri Sara","OP19821",28)
    );

    //Une simple liste des comptes pour le test
    private List<Account> accounts = List.of(
            new Account(clients.get(0), "ACC1", new BigDecimal("5000.00")),
            new Account(clients.get(1), "ACC2", new BigDecimal("3500.50")),
            new Account(clients.get(2), "ACC3", new BigDecimal("7200.75"))
    );

    /*
     * L'annotation @Tool sert à marquer cette méthode comme un outil (tool)
     * disponible pour l'agent IA. Cette annotation permet au système MCP
     * (Model Context Protocol) de découvrir automatiquement les fonctions
     * qui peuvent être appelées par l'agent.
     *
     * Le paramètre 'description' fournit une description claire de ce que
     * fait la fonction. Cette description aide l'agent IA à comprendre
     * quand et comment utiliser cet outil de manière appropriée.
     */
    @Tool(description = "Récupère la liste complète de tous les clients enregistrés dans le système")
    public List<Client> getAllClients(){
        return clients;
    }

    @Tool(description = "Recherche et retourne un client spécifique en utilisant son nom complet")
    public Client getClientByName(String name){
        return clients.stream().filter(c->c.name().equals(name))
                .findFirst().orElseThrow(()->new RuntimeException("client n'existe pas!"));
    }

    @Tool(description = "Obtient le CIN d'un client en utilisant son nom")
    public String getClientCIN(String client){
        return getClientByName(client).CIN();
    }

    @Tool(description = "Récupère la liste complète de tous les comptes bancaires du système")
    public List<Account> getAllAccounts(){
        return accounts;
    }

    @Tool(description = "Recherche un compte bancaire spécifique par son numéro de compte")
    public Account getAccountByNumber(String accountNumber){
        return accounts.stream().filter(a->a.accountNumber().equals(accountNumber))
                .findFirst().orElseThrow(()->new RuntimeException("compte n'existe pas!"));
    }

    @Tool(description = "Trouve le compte bancaire d'un client en utilisant son nom")
    public Account getAccountByClientName(String clientName){
        Client client = getClientByName(clientName);
        return accounts.stream().filter(a->a.client().equals(client))
                .findFirst().orElseThrow(()->new RuntimeException("compte pour ce client n'existe pas!"));
    }

    @Tool(description = "Obtient le solde d'un compte bancaire par son numéro de compte")
    public BigDecimal getBalance(String accountNumber){
        return getAccountByNumber(accountNumber).balance();
    }

    @Tool(description = "Obtient le solde du compte bancaire d'un client par son nom")
    public BigDecimal getBalanceByClientName(String clientName){
        return getAccountByClientName(clientName).balance();
    }
}

record Client(
        String name,
        String CIN,
        int Age
){}

record Account(
        Client client,
        String accountNumber,
        BigDecimal balance
){}