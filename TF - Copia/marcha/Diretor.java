public class Diretor extends Funcionario {
    // Construtor e outros métodos existentes

    public Diretor(String nome, String endereco, String rg, String cpf, String telefone, String matricula, String login,
            String senha) {
        super(nome, endereco, rg, cpf, telefone, matricula, login, senha);
    }

    public Gerente criarNovoGerente(String nome, String endereco, String rg, String cpf, String telefone, String login,
            String senha) {
        return new Gerente(nome, endereco, rg, cpf, telefone, gerarMatricula(), login, senha);
    }

    public boolean excluirGerente(Banco banco, String matricula) {
        return banco.removerFuncionario(matricula);
    }

    private String gerarMatricula() {
        // Gerar uma nova matrícula para o gerente
        // Implementação simples para gerar uma matrícula única
        return "G" + (int) (Math.random() * 1000);
    }
}
