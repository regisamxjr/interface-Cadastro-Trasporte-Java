import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Janela extends JFrame {
        private JTextField codigo, peso, qtdPessoas, tempMin, tempMax;
        private JComboBox<String> situacaoComboBox, tipoCargaComboBox;
        private JCheckBox cargaPerigosaCheckBox;
        private JButton cadastrar, limpar, sair, atualizar, mostrarCadastros;
        private JTextArea mensagem;
        private ArrayList<Transportes> transportes;

        public Janela() {
                transportes = new ArrayList<>();
                setTitle("Cadastro de Transportes");
                setSize(900, 600);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLayout(new GridBagLayout());
                GridBagConstraints posicao = new GridBagConstraints();
                posicao.fill = GridBagConstraints.HORIZONTAL;
                posicao.insets = new Insets(5, 5, 5, 5);

                posicao.gridx = 0;
                posicao.gridy = 0;
                posicao.gridwidth = 2;
                JLabel titulo = new JLabel("CADASTRO DE TRANSPORTES ", JLabel.CENTER);
                titulo.setFont(new Font("Arial", Font.BOLD, 20));
                add(titulo, posicao);
                posicao.gridwidth = 1;

                posicao.gridx = 0;
                posicao.gridy = 1;
                add(new JLabel("Código: "), posicao);
                posicao.gridx = 1;
                codigo = new JTextField(10);
                add(codigo, posicao);

                posicao.gridx = 0;
                posicao.gridy = 2;
                add(new JLabel("Peso da carga: "), posicao);
                posicao.gridx = 1;
                peso = new JTextField(10);
                add(peso, posicao);

                posicao.gridx = 0;
                posicao.gridy = 3;
                add(new JLabel("Situação: "), posicao);
                posicao.gridx = 1;
                situacaoComboBox = new JComboBox<>(new String[]{"Pendente", "Alocado", "Terminado", "Cancelado"});
                add(situacaoComboBox, posicao);

                posicao.gridx = 0;
                posicao.gridy = 4;
                add(new JLabel("Tipo de Carga: "), posicao);
                posicao.gridx = 1;
                tipoCargaComboBox = new JComboBox<>(new String[]{"Pessoal", "Carga Inanimada", "Carga Viva"});
                add(tipoCargaComboBox, posicao);
                tipoCargaComboBox.addActionListener(e -> atualizarCamposAdicionais());

                posicao.gridx = 0;
                posicao.gridy = 5;
                add(new JLabel("Qtd. Pessoas: "), posicao);
                posicao.gridx = 1;
                qtdPessoas = new JTextField(10);
                add(qtdPessoas, posicao);

                posicao.gridx = 0;
                posicao.gridy = 6;
                add(new JLabel("Carga Perigosa: "), posicao);
                posicao.gridx = 1;
                cargaPerigosaCheckBox = new JCheckBox();
                add(cargaPerigosaCheckBox, posicao);

                posicao.gridx = 0;
                posicao.gridy = 7;
                add(new JLabel("Temp. Mínima: "), posicao);
                posicao.gridx = 1;
                tempMin = new JTextField(10);
                add(tempMin, posicao);

                posicao.gridx = 0;
                posicao.gridy = 8;
                add(new JLabel("Temp. Máxima: "), posicao);
                posicao.gridx = 1;
                tempMax = new JTextField(10);
                add(tempMax, posicao);

                posicao.gridx = 0;
                posicao.gridy = 9;
                posicao.gridwidth = 2;
                cadastrar = new JButton("Cadastrar");
                add(cadastrar, posicao);
                cadastrar.addActionListener(e -> cadastrarTransporte());

                posicao.gridy = 10;
                mostrarCadastros = new JButton("Mostrar Cadastros");
                add(mostrarCadastros, posicao);
                mostrarCadastros.addActionListener(e -> mostrarTransportesCadastrados());

                posicao.gridy = 11;
                atualizar = new JButton("Atualizar Transporte");
                add(atualizar, posicao);
                atualizar.addActionListener(e -> mostrarTransportesAtualizados());

                posicao.gridy = 12;
                limpar = new JButton("Limpar");
                add(limpar, posicao);
                limpar.addActionListener(e -> limparCampos());

                posicao.gridy = 13;
                sair = new JButton("Sair");
                add(sair, posicao);
                sair.addActionListener(e -> saida());

                posicao.gridy = 14;
                mensagem = new JTextArea(10, 40);
                mensagem.setEditable(false);
                mensagem.setLineWrap(true);
                mensagem.setWrapStyleWord(true);
                JScrollPane scrollPane = new JScrollPane(mensagem);
                add(scrollPane, posicao);

                atualizarCamposAdicionais(); // Inicializa os campos adicionais
        }

        public void atualizarCamposAdicionais() {
                String tipoCarga = (String) tipoCargaComboBox.getSelectedItem();
                boolean isPessoal = tipoCarga.equals("Pessoal");
                boolean isInanimada = tipoCarga.equals("Carga Inanimada");
                boolean isViva = tipoCarga.equals("Carga Viva");

                qtdPessoas.setVisible(isPessoal);
                cargaPerigosaCheckBox.setVisible(isInanimada);
                tempMin.setVisible(isViva);
                tempMax.setVisible(isViva);
        }

        public void limparCampos() {
                codigo.setText("");
                peso.setText("");
                situacaoComboBox.setSelectedIndex(0);
                tipoCargaComboBox.setSelectedIndex(0);
                qtdPessoas.setText("");
                cargaPerigosaCheckBox.setSelected(false);
                tempMin.setText("");
                tempMax.setText("");
                atualizarCamposAdicionais();
        }



        private void mostrarTransportesCadastrados() {
                if (transportes.isEmpty()) {
                        mensagem.setText("Nenhum transporte cadastrado.");
                } else {
                        StringBuilder sb = new StringBuilder();
                        for (Transportes transporte : transportes) {
                                sb.append(transporte).append("\n");
                        }
                        mensagem.setText(sb.toString());
                }
        }

        public void mostrarTransportesAtualizados() {
                try {
                        int codigoAtualizar = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do transporte a ser atualizado:"));
                        boolean encontrado = false;

                        for (Transportes transporte : transportes) {
                                if (transporte.getCodigo() == codigoAtualizar) {
                                        encontrado = true;

                                                String novaSituacao = (String) JOptionPane.showInputDialog(
                                                this,
                                                "Selecione a nova situação:",
                                                "Atualizar Situação",
                                                JOptionPane.QUESTION_MESSAGE,
                                                null,
                                                new String[]{"Pendente", "Alocado", "Terminado", "Cancelado"},
                                                transporte.getSituacao()
                                        );
                                        if (novaSituacao != null) {
                                                transporte.setSituacao(novaSituacao);
                                        }

                                        switch (transporte.getTipoCarga()) {
                                                case "Pessoal":
                                                        int novaQtdPessoas = Integer.parseInt(JOptionPane.showInputDialog("Digite a nova quantidade de pessoas:"));
                                                        transporte.setQtdPessoas(novaQtdPessoas);
                                                        break;
                                                case "Carga Inanimada":
                                                        int respostaPerigosa = JOptionPane.showConfirmDialog(this, "A carga é perigosa?", "Atualizar Carga Perigosa", JOptionPane.YES_NO_OPTION);
                                                        transporte.setCargaPerigosa(respostaPerigosa == JOptionPane.YES_OPTION);
                                                        break;
                                                case "Carga Viva":
                                                        double novaTempMin = Double.parseDouble(JOptionPane.showInputDialog("Digite a nova temperatura mínima:"));
                                                        double novaTempMax = Double.parseDouble(JOptionPane.showInputDialog("Digite a nova temperatura máxima:"));
                                                        transporte.setTempMin(novaTempMin);
                                                        transporte.setTempMax(novaTempMax);
                                                        break;
                                        }

                                        mensagem.setText("Transporte atualizado com sucesso!");
                                        break;
                                }
                        }

                        if (!encontrado) {
                                mensagem.setText("Erro: Transporte não encontrado para o código informado.");
                        }
                } catch (NumberFormatException ex) {
                        mensagem.setText("Erro: Verifique se todos os campos numéricos estão preenchidos corretamente.");
                }
        }

        public void cadastrarTransporte() {
                try {
                        int codigo = Integer.parseInt(this.codigo.getText());
                        double peso = Double.parseDouble(this.peso.getText());
                        String situacao = (String) situacaoComboBox.getSelectedItem();
                        String tipoCarga = (String) tipoCargaComboBox.getSelectedItem();

                        for (Transportes transporte : transportes) {
                                if (transporte.getCodigo() == codigo) {
                                        mensagem.setText("ERRO: Já existe um transporte cadastrado com este código.");
                                        return;
                                }
                        }

                        int qtdPessoasVal = 0;
                        boolean cargaPerigosaVal = false;
                        double tempMinVal = 0, tempMaxVal = 0;

                        switch (tipoCarga) {
                                case "Carga Pessoal":
                                        qtdPessoasVal = Integer.parseInt(qtdPessoas.getText());
                                        break;
                                case "Carga Inanimada":
                                        cargaPerigosaVal = cargaPerigosaCheckBox.isSelected();
                                        break;
                                case "Carga Viva":
                                        tempMinVal = Double.parseDouble(tempMin.getText());
                                        tempMaxVal = Double.parseDouble(tempMax.getText());
                                        break;
                        }

                        Transportes novoTransporte = new Transportes(codigo, situacao, peso, tipoCarga, qtdPessoasVal, cargaPerigosaVal, tempMinVal, tempMaxVal);
                        transportes.add(novoTransporte);
                        mensagem.setText("Transporte cadastrado com sucesso!");

                } catch (NumberFormatException ex) {
                        mensagem.setText("Erro: Verifique se todos os campos numéricos estão preenchidos corretamente.");
                }
        }

        public void saida() {
                System.exit(0);
        }
}
