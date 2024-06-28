import java.util.HashMap;
import java.util.Map;

public class Banco {
    private Map<String, Cliente> clientes;
    private Map<String, Funcionario> funcionarios;

    // Construtor
    public Banco() {
        this.clientes = new HashMap<>();
        this.funcionarios = new HashMap<>();
    }

    // Métodos para gerenciar clientes e funcionários
    public void adicionarCliente(String cpf, Cliente cliente) {
        this.clientes.put(cpf, cliente);
    }

    public void adicionarFuncionario(String matricula, Funcionario funcionario) {
        this.funcionarios.put(matricula, funcionario);
    }

    public Cliente getCliente(String cpf) {
        return this.clientes.get(cpf);
    }

    public Funcionario getFuncionario(String matricula) {
        return this.funcionarios.get(matricula);
    }

    public Map<String, Cliente> getClientes() {
        return this.clientes;
    }

    public Map<String, Funcionario> getFuncionarios() {
        return this.funcionarios;
    }

    public boolean removerFuncionario(String matricula) {
        if (funcionarios.containsKey(matricula)) {
            funcionarios.remove(matricula);
            return true;
        }
        return false;
    }

    public boolean removerCliente(String cpf) {
        if (clientes.containsKey(cpf)) {
            clientes.remove(cpf);
            return true;
        }
        return false;
    }

}
