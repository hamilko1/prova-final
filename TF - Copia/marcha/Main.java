import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Main {
    private static Banco banco = new Banco();
    private static Gerente gerente;
    private static Diretor diretor;
    private static Funcionario funcionarioLogado;
    private static Cliente clienteLogado;

    public static void main(String[] args) {
        // Criando alguns funcionários
        diretor = new Diretor("Ana", "Rua 1", "1234567", "11122233344", "1111-1111", "D001", "ana_login", "ana_senha");
        gerente = new Gerente("Carlos", "Rua 2", "2345678", "22233344455", "2222-2222", "G001", "carlos_login", "carlos_senha");

        // Adicionando funcionários ao banco
        banco.adicionarFuncionario(diretor.getMatricula(), diretor);
        banco.adicionarFuncionario(gerente.getMatricula(), gerente);

        // Inicializando a interface gráfica
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Banco Digital");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        JButton loginButton = new JButton("Login");
        JButton criarClienteButton = new JButton("Criar Cliente");
        JButton excluirClienteButton = new JButton("Excluir Cliente");
        JButton criarContaCorrenteButton = new JButton("Criar Conta Corrente");
        JButton criarContaPoupancaButton = new JButton("Criar Conta Poupança");
        JButton excluirContaButton = new JButton("Excluir Conta");
        JButton depositoButton = new JButton("Depósito");
        JButton saqueButton = new JButton("Saque");

        panel.add(loginButton);
        panel.add(criarClienteButton);
        panel.add(excluirClienteButton);
        panel.add(criarContaCorrenteButton);
        panel.add(criarContaPoupancaButton);
        panel.add(excluirContaButton);
        panel.add(depositoButton);
        panel.add(saqueButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        criarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarCliente();
            }
        });

        excluirClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirCliente();
            }
        });

        criarContaCorrenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarContaCorrente();
            }
        });

        criarContaPoupancaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarContaPoupanca();
            }
        });

        excluirContaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirConta();
            }
        });

        depositoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposito();
            }
        });

        saqueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saque();
            }
        });
    }

    private static void login() {
        JTextField loginField = new JTextField();
        JTextField senhaField = new JPasswordField();

        Object[] message = {
            "Login:", loginField,
            "Senha:", senhaField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String login = loginField.getText();
            String senha = senhaField.getText();
            
            funcionarioLogado = null;
            clienteLogado = null;

            // Verifica login de funcionários
            for (Funcionario funcionario : banco.getFuncionarios().values()) {
                if (funcionario.verificarCredenciais(login, senha)) {
                    funcionarioLogado = funcionario;
                    Logger.log("Funcionário " + funcionario.getNome() + " (login: " + login + ") fez login.");
                    break;
                }
            }

            // Verifica login de clientes
            if (funcionarioLogado == null) {
                for (Cliente c : banco.getClientes().values()) {
                    if (c.verificarCredenciais(login, senha)) {
                        clienteLogado = c;
                        Logger.log("Cliente " + c.getNome() + " (login: " + login + ") fez login.");
                        break;
                    }
                }
            }

            if (funcionarioLogado != null) {
                JOptionPane.showMessageDialog(null, "Bem-vindo, " + funcionarioLogado.getNome());
            } else if (clienteLogado != null) {
                JOptionPane.showMessageDialog(null, "Bem-vindo(a), " + clienteLogado.getNome());
            } else {
                JOptionPane.showMessageDialog(null, "Login ou senha incorretos.");
            }
        }
    }

    private static void criarCliente() {
        if (funcionarioLogado instanceof Gerente) {
            JTextField nomeField = new JTextField();
            JTextField enderecoField = new JTextField();
            JTextField rgField = new JTextField();
            JTextField cpfField = new JTextField();
            JTextField telefoneField = new JTextField();
            JTextField loginField = new JTextField();
            JTextField senhaField = new JPasswordField();

            Object[] message = {
                "Nome:", nomeField,
                "Endereço:", enderecoField,
                "RG:", rgField,
                "CPF:", cpfField,
                "Telefone:", telefoneField,
                "Login:", loginField,
                "Senha:", senhaField
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Criar Cliente", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String nome = nomeField.getText();
                String endereco = enderecoField.getText();
                String rg = rgField.getText();
                String cpf = cpfField.getText();
                String telefone = telefoneField.getText();
                String login = loginField.getText();
                String senha = senhaField.getText();

                Cliente novoCliente = ((Gerente) funcionarioLogado).criarNovoCliente(nome, endereco, rg, cpf, telefone, login, senha);
                banco.adicionarCliente(novoCliente.getCpf(), novoCliente);
                Logger.log("Gerente " + funcionarioLogado.getNome() + " criou novo cliente: " + nome);
                JOptionPane.showMessageDialog(null, "Cliente criado com sucesso: " + novoCliente.getNome());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Apenas gerentes podem criar clientes.");
        }
    }

    private static void excluirCliente() {
        if (funcionarioLogado instanceof Gerente) {
            JTextField cpfField = new JTextField();
            Object[] message = {
                "CPF do Cliente a ser excluído:", cpfField
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Excluir Cliente", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String cpf = cpfField.getText();
                if (((Gerente) funcionarioLogado).excluirCliente(banco, cpf)) {
                    Logger.log("Gerente " + funcionarioLogado.getNome() + " excluiu cliente com CPF: " + cpf);
                    JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir o cliente. CPF não encontrado.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Apenas gerentes podem excluir clientes.");
        }
    }

    private static void criarContaCorrente() {
        if (funcionarioLogado instanceof Gerente) {
            JTextField cpfField = new JTextField();
            JTextField saldoInicialField = new JTextField();
            JTextField limiteChequeEspecialField = new JTextField();
            JTextField taxaRendimentoField = new JTextField();

            Object[] message = {
                "CPF do Cliente:", cpfField,
                "Saldo Inicial:", saldoInicialField,
                "Limite do Cheque Especial:", limiteChequeEspecialField,
                "Taxa de Rendimento:", taxaRendimentoField
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Criar Conta Corrente", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String cpf = cpfField.getText();
                double saldoInicial = Double.parseDouble(saldoInicialField.getText());
                double limiteChequeEspecial = Double.parseDouble(limiteChequeEspecialField.getText());
                double taxaRendimento = Double.parseDouble(taxaRendimentoField.getText());

                Cliente cliente = banco.getCliente(cpf);
                if (cliente != null) {
                    ContaCorrente novaContaCorrente = ((Gerente) funcionarioLogado).criarContaCorrente(saldoInicial, limiteChequeEspecial, taxaRendimento);
                    cliente.adicionarConta(novaContaCorrente);
                    Logger.log("Gerente " + funcionarioLogado.getNome() + " criou conta corrente para cliente: " + cliente.getNome());
                    JOptionPane.showMessageDialog(null, "Conta Corrente criada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Apenas gerentes podem criar contas correntes.");
        }
    }

    private static void criarContaPoupanca() {
        if (funcionarioLogado instanceof Gerente) {
            JTextField cpfField = new JTextField();
            JTextField saldoInicialField = new JTextField();
            JTextField limiteSaqueField = new JTextField();

            Object[] message = {
                "CPF do Cliente:", cpfField,
                "Saldo Inicial:", saldoInicialField,
                "Limite de Saque:", limiteSaqueField
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Criar Conta Poupança", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String cpf = cpfField.getText();
                double saldoInicial = Double.parseDouble(saldoInicialField.getText());
                double limiteSaque = Double.parseDouble(limiteSaqueField.getText());

                Cliente cliente = banco.getCliente(cpf);
                if (cliente != null) {
                    ContaPoupanca novaContaPoupanca = ((Gerente) funcionarioLogado).criarContaPoupanca(saldoInicial, limiteSaque);
                    cliente.adicionarConta(novaContaPoupanca);
                    Logger.log("Gerente " + funcionarioLogado.getNome() + " criou conta poupança para cliente: " + cliente.getNome());
                    JOptionPane.showMessageDialog(null, "Conta Poupança criada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Apenas gerentes podem criar contas poupanças.");
        }
    }

    private static void excluirConta() {
        if (funcionarioLogado instanceof Gerente) {
            JTextField cpfField = new JTextField();
            Object[] message = {
                "CPF do Cliente:", cpfField
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Excluir Conta", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String cpf = cpfField.getText();
                Cliente cliente = banco.getCliente(cpf);
                if (cliente != null) {
                    if (cliente.getContas().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "O cliente não possui contas para excluir.");
                    } else {
                        String[] contas = new String[cliente.getContas().size()];
                        for (int i = 0; i < cliente.getContas().size(); i++) {
                            contas[i] = cliente.getContas().get(i).getClass().getSimpleName() + " - Saldo: " + cliente.getContas().get(i).getSaldo();
                        }

                        String contaParaExcluir = (String) JOptionPane.showInputDialog(null, "Selecione a conta para excluir:", "Excluir Conta", JOptionPane.QUESTION_MESSAGE, null, contas, contas[0]);
                        if (contaParaExcluir != null) {
                            int index = java.util.Arrays.asList(contas).indexOf(contaParaExcluir);
                            Conta conta = cliente.getContas().get(index);
                            if (((Gerente) funcionarioLogado).excluirConta(cliente, conta)) {
                                Logger.log("Gerente " + funcionarioLogado.getNome() + " excluiu conta de " + cliente.getNome());
                                JOptionPane.showMessageDialog(null, "Conta excluída com sucesso!");
                            } else {
                                JOptionPane.showMessageDialog(null, "Erro ao excluir a conta.");
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Apenas gerentes podem excluir contas.");
        }
    }

    private static void deposito() {
        if (clienteLogado != null) {
            String[] contas = new String[clienteLogado.getContas().size()];
            for (int i = 0; i < clienteLogado.getContas().size(); i++) {
                contas[i] = clienteLogado.getContas().get(i).getClass().getSimpleName() + " - Saldo: " + clienteLogado.getContas().get(i).getSaldo();
            }

            String contaSelecionada = (String) JOptionPane.showInputDialog(null, "Selecione a conta para operar:", "Depósito", JOptionPane.QUESTION_MESSAGE, null, contas, contas[0]);
            if (contaSelecionada != null) {
                int index = java.util.Arrays.asList(contas).indexOf(contaSelecionada);
                Conta conta = clienteLogado.getContas().get(index);

                JTextField valorField = new JTextField();
                Object[] message = {
                    "Valor para depositar:", valorField
                };

                int option = JOptionPane.showConfirmDialog(null, message, "Depósito", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    double valor = Double.parseDouble(valorField.getText());
                    conta.depositar(valor);
                    Logger.log("Cliente " + clienteLogado.getNome() + " depositou " + valor + " na conta " + conta.getClass().getSimpleName());
                    JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso! Saldo atual: " + conta.getSaldo());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Apenas clientes podem realizar depósitos.");
        }
    }

    private static void saque() {
        if (clienteLogado != null) {
            String[] contas = new String[clienteLogado.getContas().size()];
            for (int i = 0; i < clienteLogado.getContas().size(); i++) {
                contas[i] = clienteLogado.getContas().get(i).getClass().getSimpleName() + " - Saldo: " + clienteLogado.getContas().get(i).getSaldo();
            }

            String contaSelecionada = (String) JOptionPane.showInputDialog(null, "Selecione a conta para operar:", "Saque", JOptionPane.QUESTION_MESSAGE, null, contas, contas[0]);
            if (contaSelecionada != null) {
                int index = java.util.Arrays.asList(contas).indexOf(contaSelecionada);
                Conta conta = clienteLogado.getContas().get(index);

                JTextField valorField = new JTextField();
                Object[] message = {
                    "Valor para sacar:", valorField
                };

                int option = JOptionPane.showConfirmDialog(null, message, "Saque", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    double valor = Double.parseDouble(valorField.getText());
                    if (conta.sacar(valor)) {
                        Logger.log("Cliente " + clienteLogado.getNome() + " sacou " + valor + " da conta " + conta.getClass().getSimpleName());
                        JOptionPane.showMessageDialog(null, "Saque realizado com sucesso! Saldo atual: " + conta.getSaldo());
                    } else {
                        JOptionPane.showMessageDialog(null, "Saldo insuficiente!");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Apenas clientes podem realizar saques.");
        }
    }
}