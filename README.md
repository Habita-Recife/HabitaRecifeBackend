# 🏢 Habita Recife

## 📊 Visão Geral
A Plataforma de Gestão Condominial é uma solução abrangente para administração de condomínios, oferecendo ferramentas para gestão de moradores, controle financeiro, comunicação, tarefas administrativas e manutenção.

## 👥 Público-Alvo
Conjuntos habitacionais da cidade do Recife e seus respectivos síndicos e moradores.

## ⚙️ Funcionalidades

#### 👤 Cadastro e gestão de moradores:
- Cadastro completo de moradores e seus dependentes.
- Histórico e informações de contato.
- Controle de inadimplência.

#### 📈 Controle financeiro:
- Lançamento de receitas e despesas.
- Emissão de relatórios financeiros detalhados.
- Controle de inadimplência, cobrança e multas.
- Arrecadação de taxas condominiais.

#### 💬 Comunicação:
- Canal de comunicação com envio de avisos e comunicados aos moradores.
- Canal de recebimento de reclamações, postagem e divulgação de serviços e reserva de espaços.
- Agendamento de assembleias pelo síndico online e presenciais.
- Vitrine para divulgação de serviços de moradores.

#### 🗒️ Tarefas administrativas:
- Geração de relatórios para prestação de contas.
- Gerenciamento de documentos e contratos.

#### ⚖️ Manutenção:
- Abertura e acompanhamento de solicitações de manutenção.
- Histórico de manutenções realizadas.

#### ⛓️ Acesso e Segurança:
- Histórico de entradas e saídas (moradores e visitantes).
- Cadastro de visitantes e controle de acesso com QR Code.
- Cadastro de moradores e veículos.

#### 💳 Outras Funcionalidades:
- Geração de renda e abatimento de contas para moradores desempregados (parcerias).
- Mercado para anúncios de moradores.

## 🌐 Arquitetura e Stack

A plataforma é desenvolvida utilizando tecnologias modernas e robustas:
- 🔦 **Frontend**: React, para uma interface responsiva e amigável.
- 🚀 **Backend**: Spring, para uma arquitetura escalável e segura.
- 💾 **Banco de dados**: MySQL, para armazenamento e gerenciamento de dados.

## ⚡ Execução do Código

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   ```
2. Acesse o diretório do projeto:
   ```bash
   cd seu-repositorio
   ```
3. Configure o banco de dados no `application.properties` ou `application.yml` , exemplo:
   ```properties
   # Configuração do banco de dados MySQL
   spring.datasource.url=jdbc:mysql://localhost:3307/habita_recife_backend?createDatabaseIfNotExist=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true
   spring.datasource.username=seu-usuario
   spring.datasource.password=sua-senha-mysql
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

   # Configuração do Hibernate e JPA
   spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
   spring.jpa.hibernate.ddl-auto=update
   ```

4. Compile e execute o projeto:
   ```bash
   mvn spring-boot:run
   ```

## 💰 Banco de Dados
Representação no Mermaid

# Habita-Recife-Backend

```mermaid
classDiagram
	%% Grupo 1: Entidades principais
	class Condominio {
    	+int id_unidade
    	+string endereco
    	+string nome_condominio
    	+int numero_apartamento
	}

	class Sindico {
    	+int id_sindico
    	+string nome_sindico
    	+string telefone_sindico
    	+int id_unidade
	}

	class Morador {
    	+string cpf_morador
    	+string nome_morador
    	+string veiculo
    	+string[] membros
    	+int id_unidade
    	+int id_Financeiro
	}

	class Prefeitura {
    	+int id_prefeitura
    	+int id_relatorio
	}

	class Notificacao {
    	+int id_notificacao
    	+string tipoNotificacao
	}


	class Porteiro{
    	+int id_porteiro
    	+string nome_porteiro
    	+string cpf_porteiro
	}

	%% Grupo 2: Gestão financeira
	class Conta_Bancaria {
    	+int id_conta_bancaria
    	+string banco
    	+string numero_conta
    	+string agencia
	}

	class Financeiro{
    	+int id_financeiro
    	+string tipo
    	+double valor
    	+int id_sindico
	}

	%% Grupo 3: Comunicação e Relatórios
	class Relatorio {
    	+int id_relatorio
    	+string conteudo
    	+int id_sindico
	}

	class Mensagem {
    	+int id_mensagem
    	+string conteudo
    	+string tipo
    	+int id_sindico
	}


	class Fluxo {
    	+int id_fluxo
    	+enum tipoFluxo
    	+enum statusFluxo
    	+datetime data_fluxo
	}


	class Vitrine {
    	+int id_vitrine
    	+string nome_produto
    	+TipoVitrine tipoVitrine
    	+double valor_produto
	}

	class Visitante {
    	+int id_visitante
    	+string rg_visitante
    	+string nome_visitante
    	+string numero_visitante
	}

	class Solicitacao {
    	+int id_solicitacao
    	+string titulo
    	+string conteudo
    	+enum tipo_solicitacao
    	+enum status_solicitacao
	}

	class Servico {
    	+int id_servico
    	+enum tipoServico
    	+double valorServico
    	+int funcionariosAlocados
    	+datetime dataContrato
	}

	class ConfirmacaoServico  {
    	+int id_confirmacao_servico
    	+string statusConfirmacao
    	+string tipolocal
    	+date dataReservada
	}


	class Relatorio {
    	+int id_relatorio
    	+string conteudo
    	+int id_sindico
	}

	
	class Empresa {
    	+int id_empresa
    	+string nome_empresa
	}

	%% Relacionamentos organizados
	Condominio "1" *--> "1" Sindico : gerenciado_por
	Condominio "1" *--> "N" Morador : habita
	Condominio "1" *--> "1" Porteiro: possui

	Porteiro"1" *--> "N" Fluxo: controla
	Visitante"1" *--> "N" Fluxo: é aceito 
	Morador"1" *--> "N" Fluxo: é aceito 




	Sindico "1" *--> "1" Financeiro: gerencia
	Sindico "1" *--> "N" Relatorio : emite
	Sindico "1" *--> "N" Mensagem : envia
	Sindico "1" *--> "N" Solicitacao: recebe
	Sindico "1" *--> "N" Vitrine: Posta
	Sindico "1" *--> "N" Servico: solicita
	Sindico "1" *--> "N" Notificacao: recebe

	Financeiro"1" *--> "1" Conta_Bancaria : possui

	Servico"1" *--> "N" ConfirmacaoServico: emite
	ConfirmacaoServico "1" *--> "N" Morador : envia
	ConfirmacaoServico "1" *--> "N" Empresa: envia


	Morador "1" *--> "N" Notificacao: recebe
	Morador "1" *--> "N" Financeiro: paga
	Morador "1" *--> "N" Solicitacao: faz


	Prefeitura "1" *--> "1" Relatorio : recebe

```


## 📃 Observações
Este documento está em constante atualização.

