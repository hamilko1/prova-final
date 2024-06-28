import java.util.ArrayList;
import java.util.List;

public class Gerente extends Funcionario {
    private List<Cliente> clientes;

    // Construtor
    public Gerente(String nome, String endereco, String rg, String cpf, String telefone, String matricula, String login,
            String senha) {
        super(nome, endereco, rg, cpf, telefone, matricula, login, senha);
        this.clientes = new ArrayList<>();
    }

    // Métodos para gerenciar clientes
    public void adicionarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void removerCliente(Cliente cliente) {
        this.clientes.remove(cliente);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    // Métodos para criar e excluir contas
    public ContaCorrente criarContaCorrente(double saldoInicial, double limiteChequeEspecial, double taxaRendimento) {
        return new ContaCorrente(saldoInicial, limiteChequeEspecial, taxaRendimento);
    }

    public ContaPoupanca criarContaPoupanca(double saldoInicial, double limiteSaque) {
        return new ContaPoupanca(saldoInicial, limiteSaque);
    }

    public boolean excluirConta(Cliente cliente, Conta conta) {
        return cliente.removerConta(conta);
    }

    public Cliente criarNovoCliente(String nome, String endereco, String rg, String cpf, String telefone, String login,
            String senha) {
        Cliente novoCliente = new Cliente(nome, endereco, rg, cpf, telefone, this, login, senha);
        this.adicionarCliente(novoCliente);
        return novoCliente;
    }

    // Método para excluir um cliente
    public boolean excluirCliente(Banco banco, String cpf) {
        Cliente cliente = banco.getCliente(cpf);
        if (cliente != null) {
            banco.getClientes().remove(cpf);
            return true;
        }
        return false;
    }
}
