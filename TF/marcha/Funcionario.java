public class Funcionario extends Pessoa {
    private String matricula;
    private String login;
    private String senha;

    // Construtor
    public Funcionario(String nome, String endereco, String rg, String cpf, String telefone, String matricula,
            String login, String senha) {
        super(nome, endereco, rg, cpf, telefone);
        this.matricula = matricula;
        this.login = login;
        this.senha = senha;
    }

    // Getters e Setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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

    // Método para verificar credenciais
    public boolean verificarCredenciais(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }
}
