public class ContaCorrente extends Conta {
    private double rendimentoCDB;

    // Construtor
    public ContaCorrente(double saldo, double limite, double rendimentoCDB) {
        super(saldo, limite);
        this.rendimentoCDB = rendimentoCDB;
    }

    // Método para simular rendimento diário
    public void simularRendimentoDiario() {
        this.setSaldo(this.getSaldo() + (this.getSaldo() * rendimentoCDB));
    }

    // Método para realizar depósito
    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            this.setSaldo(this.getSaldo() + valor);
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    // Método para realizar saque
    @Override
    public boolean sacar(double valor) {
        double limite = this.getSaldo() + this.getLimite();
        if (valor > 0 && valor <= limite) {
            this.setSaldo(this.getSaldo() - valor);
            return true;
        } else {
            System.out.println("Saldo insuficiente.");
            return false;
        }
    }
}
