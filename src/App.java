import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> nomes = new ArrayList<>();
        ArrayList<Integer> codigos = new ArrayList<>();
        ArrayList<Double> precos = new ArrayList<>();
        ArrayList<Integer> quantidades = new ArrayList<>();

        String option;
        int totalProdutos = 0;
        int proxCodigo = 1;

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

               
                if (option == null) {
                    JOptionPane.showMessageDialog(null, "Programa Encerrado pelo Usuário.", "Despedida", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }

                int optionInteger = Integer.parseInt(option);

                if (optionInteger > 10 || optionInteger < 1) {
                    JOptionPane.showMessageDialog(null, "Opção Invalida", "Erro", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                switch (optionInteger) {

                    case 1: 
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

                            JOptionPane.showMessageDialog(null, 
                                "Produto cadastrado com sucesso!\n" +
                                "Nome: " + nomes.get(totalProdutos) + "\n" +
                                "Código Gerado: " + codigos.get(totalProdutos), "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                            totalProdutos++;
                            proxCodigo++;

                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Preço inválido! Digite apenas números e use pontos para centavos.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        break; 

                    case 2:

                        try {
                            if (totalProdutos == 0) {
                                JOptionPane.showMessageDialog(null, "Nenhum produto cadastrado ainda.", "Aviso", JOptionPane.WARNING_MESSAGE);
                                break; 
                            }

                            String relatorio = "=== RELATÓRIO DE PRODUTOS EM ESTOQUE ===\n\n";

                            for (int i = 0; i < totalProdutos; i++) {
                                
                                relatorio += "Código: " + codigos.get(i) + " | Nome: " + nomes.get(i) + " | Preço: R$ " + precos.get(i) + " | Quantidade: " + quantidades.get(i) + "\n";
                            }

                            JOptionPane.showMessageDialog(null, relatorio, "Relatório Produtos", JOptionPane.INFORMATION_MESSAGE);

                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    
                    case 3:

                        try{
                            String nomeProduto = JOptionPane.showInputDialog("Digite o nome do produto que deseja pesquisar:");

                            boolean encontrou = false;

                            if (nomeProduto == null){
                                JOptionPane.showMessageDialog(null, "Nome não pode ser vazio");
                                break;
                            }

                            String resultadosBusca = "=== PRODUTOS ENCONTRADOS ===\n\n";

                            for (int i = 0; i < nomes.size(); i++) {
                                String nAtual = nomes.get(i);

                                    if (nAtual.toLowerCase().contains(nomeProduto.toLowerCase())){
                                        resultadosBusca += "Código: " + codigos.get(i) + " | Nome: " + nomes.get(i) + " | Preço: R$ " + precos.get(i) + " | Quantidade: " + quantidades.get(i) + "\n";

                                        encontrou = true;
                                    }
                                
                            }

                            if (encontrou) {
                                JOptionPane.showMessageDialog(null, resultadosBusca);
                            }

                            else{
                                JOptionPane.showMessageDialog(null, "Nenhum produto com esse nome encontrado");
                            }

                        }

                        catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro", "Erro", JOptionPane.ERROR_MESSAGE);
                        }

                        break;
                    
                    case 4:

                        try {
                            
                            int index = 0;

                            String resultado = "=== ADICIONAR PRODUTOS NO ESTOQUE ===\n\n";

                            String codigo = JOptionPane.showInputDialog("Digite o codigo do produto:");

                            if (codigo == null) {
                                JOptionPane.showMessageDialog(null, "Codigo vazio encerrando");
                                break;
                            }

                            Integer codigoCasted = Integer.parseInt(codigo);

                            if (codigos.contains(codigoCasted)){
                                    
                                    index = codigos.lastIndexOf(codigoCasted);
                                }

                             else {
                                    JOptionPane.showMessageDialog(null, "Produto não encontrado");
                                    break;
                                }
                            

                            String quantidadeAdicionada = JOptionPane.showInputDialog("Digite a quantidade comprada do produto");

                            Integer quantidadeCasted = Integer.parseInt(quantidadeAdicionada);

                            quantidades.set(index,quantidades.get(index) + quantidadeCasted);

                            resultado += "Adicionado " + quantidadeCasted + " Unidades para o produto: " + nomes.get(index);

                            JOptionPane.showMessageDialog(null, resultado);

                        } 

                        catch (Exception e) {
                            
                            JOptionPane.showMessageDialog(null, "Erro", "Erro", JOptionPane.ERROR_MESSAGE);
                        }

                        break;
                    
                    case 5:

                        try {
                            
                            int index = 0;

                            String resultado = "=== RETIRAR PRODUTOS DO ESTOQUE ===\n\n";

                            String codigo = JOptionPane.showInputDialog("Digite o codigo do produto:");

                            if (codigo == null) {
                                JOptionPane.showMessageDialog(null, "Codigo vazio encerrando");
                                break;
                            }

                            Integer codigoCasted = Integer.parseInt(codigo);

                            if (codigos.contains(codigoCasted)){
                                    
                                    index = codigos.lastIndexOf(codigoCasted);
                                }

                             else {
                                    JOptionPane.showMessageDialog(null, "Produto não encontrado");
                                    break;
                                }
                            

                            String quantidadeRemovida = JOptionPane.showInputDialog("Digite a quantidade vendida do produto");

                            Integer quantidadeCasted = Integer.parseInt(quantidadeRemovida);

                            
                            if (quantidadeCasted > quantidades.get(index)) {
                                JOptionPane.showMessageDialog(null, "Erro a quantidade a ser vendida é maior que a em estoque", "Erro de Venda", JOptionPane.ERROR_MESSAGE);
                                break;
                            }

                            quantidades.set(index,quantidades.get(index) - quantidadeCasted);

                            resultado += "Removidos " + quantidadeCasted + " unidades para o produto: " + nomes.get(index) + " no estoque";

                            JOptionPane.showMessageDialog(null, resultado);
                        } 

                        catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro");
                        }

                        break;
                    
                    case 6:

                        try {

                            int index = 0;
                            
                            String resultado = "=== EXCLUSÃO DE PRODUTOS === \n\n";

                            String codigo = JOptionPane.showInputDialog("Digite o codigo do produto:");

                            if (codigo == null) {
                                JOptionPane.showMessageDialog(null, "Codigo vazio encerrando");
                                break;
                            }

                            int codigoCasted = Integer.parseInt(codigo);

                            if (codigos.contains(codigoCasted)){
                                        
                                index = codigos.lastIndexOf(codigoCasted);
                            }

                            else {

                                JOptionPane.showMessageDialog(null, "Produto não encontrado");
                                break;
                            }

                            if (quantidades.get(index) != 0) {
                                JOptionPane.showMessageDialog(null, "AÇÃO PROIBIDA: Não é possível excluir produtos com estoque. Quantidade atual: " + quantidades.get(index));
                            }

                            else {

                                String confirmacao = JOptionPane.showInputDialog("Tem certeza que deseja exlcuir o produto " + nomes.get(index) + " [1 - sim | 0 -  não]");
                                int confirmacaoCasted = Integer.parseInt( acao);

                                if (confirmacaoCasted < 0 || confirmacaoCasted > 1) {
                                    JOptionPane.showMessageDialog(null, "Opção invalida");
                                }

                                if (confirmacaoCasted == 1) {
                                    codigos.remove(index);
                                    nomes.remove(index);
                                    precos.remove(index);
                                    quantidades.remove(index);
                                    totalProdutos --;

                                    resultado += "Produto removido com sucesso";

                                    

                                    JOptionPane.showMessageDialog(null,resultado);
                                }
                            }

                        }

                        catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Digite apenas números para codigos");
                        }

                        break;


                    
                    case 7:

                        try {
                            String resultado = "=== LISTAR PRODUTOS === \n\n";

                            for (int i = 0; i < codigos.size(); i++) {
                                if (quantidades.get(i) == 0){
                                    resultado += "Nome: " + nomes.get(i) + " | Preço: R$ " + precos.get(i) +  "\n";
                                }
                            }

                            JOptionPane.showMessageDialog(null, resultado);
                        } 

                        catch (Exception e) {
                           JOptionPane.showMessageDialog(null, "Erro");
                        }

                        break;
                    
                    case 8:

                        try {
                            String resultado = "=== SOMA DOS PREÇOS ===\n\n";
                            double soma = 0;

                            for (int i = 0; i < codigos.size(); i++) {
                                soma += precos.get(i) * quantidades.get(i);
                            }

                            resultado += "O valor total do estoque somado é de: " + soma + "R$";

                            JOptionPane.showMessageDialog(null, resultado);
                        } 

                        catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro");
                        }

                        break;

                    case 9:

                        try {

                            int index = 0;
                            String resultado = "=== ALTERAR VALOR PRODUTO ===\n\n";

                            String codigo = JOptionPane.showInputDialog("Digite o codigo do produto:");

                            if (codigo == null) {
                                JOptionPane.showMessageDialog(null, "Codigo vazio encerrando");
                                break;
                            }

                            int codigoCasted = Integer.parseInt(codigo);

                            if (codigos.contains(codigoCasted)){
                                        
                                index = codigos.lastIndexOf(codigoCasted);
                            }

                            else{
                                JOptionPane.showMessageDialog(null, "Codigo não encontrado");
                                break;
                            }

                            String novoPreco = JOptionPane.showInputDialog("Digite o novo preço do produto:");

                            if (novoPreco == null){
                                JOptionPane.showMessageDialog(null, "O novo valor do produto não pode ser vazio");
                            }

                            double novoPrecoParsed= Double.parseDouble(novoPreco);

                            precos.set(index, novoPrecoParsed);

                            resultado += "SUCESSO: O preço do produto" + nomes.get(index) + "foi atualizado para R$ " + novoPrecoParsed;

                            JOptionPane.showMessageDialog(null, resultado);

                        }

                        catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro");
                        }
                        
                        break;

                    case 10:
                        JOptionPane.showMessageDialog(null, "Obrigado por utilizar o sistema de estoque. Até logo!", "Despedida", JOptionPane.INFORMATION_MESSAGE);
                        
                        break;
                }

                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro: digite apenas números inteiros para escolher a opção do menu.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } while (true);
    }
}