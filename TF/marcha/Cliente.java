import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {
    private Gerente gerente;
    private String login;
    private String senha;
    private List<Conta> contas;

    // Construtor
    public Cliente(String nome, String endereco, String rg, String cpf, String telefone, Gerente gerente, String login,
            String senha) {
        super(nome, endereco, rg, cpf, telefone);
        this.gerente = gerente;
        this.login = login;
        this.senha = senha;
        this.contas = new ArrayList<>();
    }

    // Getters e Setters
    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void adicionarConta(Conta conta) {
        this.contas.add(conta);
    }

    public boolean removerConta(Conta conta) {
        return this.contas.remove(conta);
    }

    // Método para verificar credenciais
    public boolean verificarCredenciais(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }
}
