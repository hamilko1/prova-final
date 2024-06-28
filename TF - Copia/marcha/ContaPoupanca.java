public class ContaPoupanca extends Conta {

    // Construtor
    public ContaPoupanca(double saldo, double limite) {
        super(saldo, limite);
    }

    // Método para simular rendimento diário
    public void simularRendimentoDiario() {
        this.setSaldo(this.getSaldo() + (this.getSaldo() * 0.0005)); // Exemplo de rendimento diário de 0.05%
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
        if (valor > 0 && valor <= this.getLimite() && valor <= this.getSaldo()) {
            this.setSaldo(this.getSaldo() - valor);
            return true;
        } else {
            System.out.println("Valor de saque inválido.");
            return false;
        }
    }
}
