import java.util.ArrayList;
import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) throws Exception {

        // Listas para armazenamento dos dados
        ArrayList<String> nomes = new ArrayList<>();
        ArrayList<Integer> codigos = new ArrayList<>();
        ArrayList<Double> precos = new ArrayList<>();
        ArrayList<Integer> quantidades = new ArrayList<>();

        String option;
        int totalProdutos = 0;
        int proxCodigo = 1;

        // Texto do Menu Principal
        String menu = "=== SISTEMA DE CONTROLE DE ESTOQUE ===\n\n" +
                "1. Cadastrar novo produto\n" +
                "2. Listar todos os produtos\n" +
                "3. Buscar produto por nome\n" +
                "4. Registrar entrada de estoque (Compra)\n" +
                "5. Registrar saída de estoque (Venda)\n" +
                "6. Excluir produto do sistema\n" +
                "7. Listar produtos com estoque zerado\n" +
                "8. Calcular valor total do estoque\n" +
                "9. Atualizar preço do produto\n" +
                "10. Sair\n\n" +
                "Escolha uma opção:";

        do {
            try {
                option = JOptionPane.showInputDialog(menu);

                // Tratamento caso o usuário feche a janela ou clique em Cancelar no Menu
                if (option == null) {
                    JOptionPane.showMessageDialog(null, "Programa Encerrado pelo Usuário.", "Despedida", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }

                int optionInteger = Integer.parseInt(option);

                if (optionInteger > 10 || optionInteger < 1) {
                    JOptionPane.showMessageDialog(null, "Opção Inválida", "Erro", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                switch (optionInteger) {

                    case 1: // CADASTRAR PRODUTO
                        try {
                            String nome = JOptionPane.showInputDialog("Digite o NOME do produto:");

                            if (nome == null || nome.trim().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Cadastro cancelado, nome não pode ser vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                                break;
                            }

                            String preco = JOptionPane.showInputDialog("Digite o PREÇO do produto:");
                            if (preco == null) {
                                break;
                            }

                            double precoNumero = Double.parseDouble(preco);

                            if (precoNumero < 0) {
                                JOptionPane.showMessageDialog(null, "Erro: Digite o valor do preço corretamente (não pode ser negativo).", "Erro", JOptionPane.ERROR_MESSAGE);
                                break;
                            }

                            codigos.add(proxCodigo);
                            nomes.add(nome);
                            precos.add(precoNumero);
                            quantidades.add(0);

                            String msgSucesso = String.format("Produto cadastrado com sucesso!\nNome: %s\nCódigo Gerado: %d\nPreço: R$ %.2f\nEstoque Inicial: %d",
                                    nomes.get(totalProdutos), codigos.get(totalProdutos), precos.get(totalProdutos), quantidades.get(totalProdutos));

                            JOptionPane.showMessageDialog(null, msgSucesso, "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                            totalProdutos++;
                            proxCodigo++;

                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Preço inválido! Digite apenas números e use pontos para centavos.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        break;

                    case 2: // LISTAR TODOS OS PRODUTOS
                        try {
                            if (totalProdutos == 0) {
                                JOptionPane.showMessageDialog(null, "Nenhum produto cadastrado ainda.", "Aviso", JOptionPane.WARNING_MESSAGE);
                                break;
                            }

                            String relatorio = "=== RELATÓRIO DE PRODUTOS EM ESTOQUE ===\n\n";
                            for (int i = 0; i < totalProdutos; i++) {
                                relatorio += String.format("Código: %d | Nome: %s | Preço: R$ %.2f | Quantidade: %d\n",
                                        codigos.get(i), nomes.get(i), precos.get(i), quantidades.get(i));
                            }

                            JOptionPane.showMessageDialog(null, relatorio, "Relatório Produtos", JOptionPane.INFORMATION_MESSAGE);

                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        break;

                    case 3: // BUSCAR PRODUTO POR NOME
                        try {
                            String nomeProduto = JOptionPane.showInputDialog("Digite o nome do produto que deseja pesquisar:");
                            if (nomeProduto == null) {
                                JOptionPane.showMessageDialog(null, "Nome não pode ser vazio");
                                break;
                            }

                            boolean encontrou = false;
                            String resultadosBusca = "=== PRODUTOS ENCONTRADOS ===\n\n";

                            for (int i = 0; i < nomes.size(); i++) {
                                String nAtual = nomes.get(i);

                                if (nAtual.toLowerCase().contains(nomeProduto.toLowerCase())) {
                                    resultadosBusca += String.format("Código: %d | Nome: %s | Preço: R$ %.2f | Quantidade: %d\n",
                                            codigos.get(i), nomes.get(i), precos.get(i), quantidades.get(i));
                                    encontrou = true;
                                }
                            }

                            if (encontrou) {
                                JOptionPane.showMessageDialog(null, resultadosBusca);
                            } else {
                                JOptionPane.showMessageDialog(null, "Nenhum produto com esse nome encontrado");
                            }

                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        break;

                    case 4: // REGISTRAR ENTRADA (COMPRA)
                        try {
                            int index = 0;
                            String resultado = "=== ADICIONAR PRODUTOS NO ESTOQUE ===\n\n";
                            String codigo = JOptionPane.showInputDialog("Digite o código do produto:");

                            if (codigo == null) {
                                JOptionPane.showMessageDialog(null, "Código vazio encerrando");
                                break;
                            }

                            Integer codigoCasted = Integer.parseInt(codigo);

                            if (codigos.contains(codigoCasted)) {
                                index = codigos.lastIndexOf(codigoCasted);
                            } else {
                                JOptionPane.showMessageDialog(null, "Produto não encontrado");
                                break;
                            }

                            String quantidadeAdicionada = JOptionPane.showInputDialog("Digite a quantidade comprada do produto");
                            if (quantidadeAdicionada == null) {
                                break;
                            }

                            Integer quantidadeCasted = Integer.parseInt(quantidadeAdicionada);

                            quantidades.set(index, quantidades.get(index) + quantidadeCasted);
                            resultado += "Adicionado " + quantidadeCasted + " Unidades para o produto: " + nomes.get(index);

                            JOptionPane.showMessageDialog(null, resultado);

                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        break;

                    case 5: // REGISTRAR SAÍDA (VENDA)
                        try {
                            int index = 0;
                            String resultado = "=== RETIRAR PRODUTOS DO ESTOQUE ===\n\n";
                            String codigo = JOptionPane.showInputDialog("Digite o código do produto:");

                            if (codigo == null) {
                                JOptionPane.showMessageDialog(null, "Código vazio encerrando");
                                break;
                            }

                            Integer codigoCasted = Integer.parseInt(codigo);

                            if (codigos.contains(codigoCasted)) {
                                index = codigos.lastIndexOf(codigoCasted);
                            } else {
                                JOptionPane.showMessageDialog(null, "Produto não encontrado");
                                break;
                            }

                            String quantidadeRemovida = JOptionPane.showInputDialog("Digite a quantidade vendida do produto");
                            if (quantidadeRemovida == null) {
                                break;
                            }

                            Integer quantidadeCasted = Integer.parseInt(quantidadeRemovida);

                            if (quantidadeCasted > quantidades.get(index)) {
                                JOptionPane.showMessageDialog(null, "Erro: a quantidade a ser vendida é maior que a em estoque", "Erro de Venda", JOptionPane.ERROR_MESSAGE);
                                break;
                            }

                            quantidades.set(index, quantidades.get(index) - quantidadeCasted);
                            resultado += "Removidas " + quantidadeCasted + " unidades para o produto: " + nomes.get(index) + " no estoque";

                            JOptionPane.showMessageDialog(null, resultado);

                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro");
                        }
                        break;

                    case 6: // EXCLUIR PRODUTO
                        try {
                            int index = 0;
                            String resultado = "=== EXCLUSÃO DE PRODUTOS === \n\n";
                            String codigo = JOptionPane.showInputDialog("Digite o código do produto:");

                            if (codigo == null) {
                                JOptionPane.showMessageDialog(null, "Código vazio encerrando");
                                break;
                            }

                            int codigoCasted = Integer.parseInt(codigo);

                            if (codigos.contains(codigoCasted)) {
                                index = codigos.lastIndexOf(codigoCasted);
                            } else {
                                JOptionPane.showMessageDialog(null, "Produto não encontrado");
                                break;
                            }

                            if (quantidades.get(index) != 0) {
                                JOptionPane.showMessageDialog(null, "AÇÃO PROIBIDA: Não é possível excluir produtos com estoque. Quantidade atual: " + quantidades.get(index));
                            } else {
                                String confirmacao = JOptionPane.showInputDialog("Tem certeza que deseja excluir o produto " + nomes.get(index) + " [1 - sim | 0 - não]");
                                if (confirmacao == null) {
                                    break;
                                }

                                int confirmacaoCasted = Integer.parseInt(confirmacao);

                                if (confirmacaoCasted < 0 || confirmacaoCasted > 1) {
                                    JOptionPane.showMessageDialog(null, "Opção inválida");
                                }

                                if (confirmacaoCasted == 1) {
                                    codigos.remove(index);
                                    nomes.remove(index);
                                    precos.remove(index);
                                    quantidades.remove(index);
                                    totalProdutos--;

                                    resultado += "Produto removido com sucesso";
                                    JOptionPane.showMessageDialog(null, resultado);
                                }
                            }

                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Digite apenas números para códigos");
                        }
                        break;

                    case 7: // LISTAR PRODUTOS COM ESTOQUE ZERADO
                        try {
                            String resultado = "=== PRODUTOS COM ESTOQUE ZERADO === \n\n";
                            boolean temZerado = false;

                            for (int i = 0; i < codigos.size(); i++) {
                                if (quantidades.get(i) == 0) {
                                    resultado += String.format("Nome: %s | Preço: R$ %.2f\n", nomes.get(i), precos.get(i));
                                    temZerado = true;
                                }
                            }

                            if (!temZerado) {
                                resultado += "Nenhum produto com estoque zerado no momento.";
                            }

                            JOptionPane.showMessageDialog(null, resultado);
                        }
                        catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro");
                        }
                        break;

                    case 8: // CALCULAR VALOR TOTAL DO ESTOQUE
                        try {
                            String resultado = "=== VALOR TOTAL DO ESTOQUE ===\n\n";
                            double soma = 0;

                            for (int i = 0; i < codigos.size(); i++) {
                                soma += precos.get(i) * quantidades.get(i);
                            }

                            resultado += String.format("O valor total do estoque somado é de: R$ %.2f", soma);
                            JOptionPane.showMessageDialog(null, resultado);
                        }
                        catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro");
                        }
                        break;

                    case 9: // ATUALIZAR PREÇO DO PRODUTO
                        try {
                            int index = 0;
                            String resultado = "=== ALTERAR VALOR PRODUTO ===\n\n";
                            String codigo = JOptionPane.showInputDialog("Digite o código do produto:");

                            if (codigo == null) {
                                JOptionPane.showMessageDialog(null, "Código vazio encerrando");
                                break;
                            }

                            int codigoCasted = Integer.parseInt(codigo);

                            if (codigos.contains(codigoCasted)) {
                                index = codigos.lastIndexOf(codigoCasted);
                            } else {
                                JOptionPane.showMessageDialog(null, "Código não encontrado");
                                break;
                            }

                            String novoPreco = JOptionPane.showInputDialog("Digite o novo preço do produto:");
                            if (novoPreco == null) {
                                break;
                            }

                            double novoPrecoParsed = Double.parseDouble(novoPreco);
                            if (novoPrecoParsed < 0) {
                                JOptionPane.showMessageDialog(null, "O preço não pode ser negativo.");
                                break;
                            }

                            precos.set(index, novoPrecoParsed);
                            resultado += String.format("SUCESSO: O preço do produto %s foi atualizado para R$ %.2f", nomes.get(index), novoPrecoParsed);

                            JOptionPane.showMessageDialog(null, resultado);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro ao atualizar o preço.");
                        }
                        break;

                    case 10: // SAIR
                        JOptionPane.showMessageDialog(null, "Obrigado por utilizar o sistema de estoque. Até logo!", "Despedida", JOptionPane.INFORMATION_MESSAGE);
                        return;

                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro: digite apenas números inteiros para escolher a opção do menu.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } while (true);
    }
}