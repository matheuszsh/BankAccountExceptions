package model.entities;

import model.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BankAccount {
    private Integer numero;
    private String titular;
    private Double saldo;
    private Double limiteSaque;
    private final Date dataDeCriacao;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public BankAccount(Integer numero, String titular, Double saldo, Double limiteSaque)throws DomainException {
        if (saldo < 0 || limiteSaque < 0 || limiteSaque > 10000){
            throw new DomainException("Erro: valor inválido.");
        }
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
        this.limiteSaque = limiteSaque;
        this.dataDeCriacao = new Date();
    }

    @Override
    public String toString() {
        return "Dados Da Conta:" + "\n\n" +
                "Numero: " + numero + '\n' +
                "Titular: " + titular + '\n' +
                "Data de Criação: " + sdf.format(dataDeCriacao) + '\n' +
                "Saldo: " + String.format("R$ %.2f", saldo) + '\n' +
                "Limite de Saque: " + String.format("R$ %.2f", limiteSaque) + '\n';
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Double getSaldo() {
        return saldo;
    }

    public Double getLimiteSaque() {
        return limiteSaque;
    }

    public void setLimiteSaque(Double limiteSaque) {
        this.limiteSaque = limiteSaque;
    }

    public Date getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void deposito(Double valor){
        this.saldo += valor;
    }

    public void saque(Double valor)throws DomainException{
        if (valor > saldo){
            throw new DomainException("Erro: Saldo Insuficiente.");
        }
        if (valor > this.limiteSaque){
            throw new DomainException("Erro: O valor para saque excede o limite disponível.");
        }
        this.saldo -= valor;
    }
}
