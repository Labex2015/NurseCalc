package com.nursecalc.Domain;

/**
 * Created by 0118424 on 09/12/2014.
 */
public class Dosagem {

    private int id;
    private double remedio;
    private double solvente;
    private double quantidadeAtualRemedio;
    private double resultado;

    public Dosagem(double remedio, double solvente, double quantidadeAtualRemedio){
        this.remedio = remedio;
        this.solvente = solvente;
        this.quantidadeAtualRemedio = quantidadeAtualRemedio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRemedio() {
        return remedio;
    }

    public void setRemedio(double remedio) {
        this.remedio = remedio;
    }

    public double getSolvente() {
        return solvente;
    }

    public void setSolvente(double solvente) {
        this.solvente = solvente;
    }

    public double getQuantidadeAtualRemedio() {
        return quantidadeAtualRemedio;
    }

    public void setQuantidadeAtualRemedio(double quantidadeAtualRemedio) {
        this.quantidadeAtualRemedio = quantidadeAtualRemedio;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public double resultado(){

        this.resultado = this.quantidadeAtualRemedio * this.solvente /this.remedio;
        return this.resultado;
    }




}
