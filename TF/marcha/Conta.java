public class Conta {
    private double saldo;
    private double limite;

    // Construtor
    public Conta(double saldo, double limite) {
        this.saldo = saldo;
        this.limite = limite;
    }

    // Getters e Setters
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    // Métodos para operações bancárias
    public void depositar(double valor) {
        this.saldo += valor;
    }

    public boolean sacar(double valor) {
        if (valor <= this.saldo + this.limite) {
            this.saldo -= valor;
            return true;
        } else {
            return false;
        }
    }
}
