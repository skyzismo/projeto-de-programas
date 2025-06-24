
Coloque os seguintes arquivos nesse pacote:

- `Main.java`
- `InterfacePrincipal.java`
- `Banco.java`
- `TelaSala.java`
- `TelaUsuario.java`
- `TelaReserva.java`
- `TelaConsulta.java`
- `TelaRelatorio.java`

---

## 🛠️ Configuração do Projeto

1. **Crie um projeto Java vazio** no seu editor.
2. **Crie o pacote** `br.ufam.icomp.reservas`.
3. **Adicione todos os arquivos .java** nesse pacote.
4. **Adicione o driver JDBC do SQLite** ao seu projeto:
   - Baixe: https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/
   - Versão recomendada: `sqlite-jdbc-3.36.0.3.jar`
   - Adicione ao build path do seu projeto.

---

## ▶️ Execução

1. **Compile o projeto**
2. **Execute a classe `Main.java`**
3. A janela principal será exibida com os botões:
   - Gerenciar Salas
   - Gerenciar Usuários
   - Reservar Sala
   - Consultar Reservas
   - Gerar Relatórios

---

## 🧪 Banco de Dados

O sistema cria o arquivo `reservas.db` automaticamente na pasta do projeto na primeira execução.

Tabelas criadas:

- `Sala(id, nome, tipo)`
- `Usuario(id, nome, cargo)`
- `Reserva(id, sala_id, usuario_id, data, hora)`

---

## 🖨️ Relatórios

A tela de relatório lista reservas por **sala**, organizadas por **data/hora** e **nome de usuário**, ideal para afixação nas portas.

---

## 📌 Observações

- As datas devem estar no formato: `YYYY-MM-DD`
- As horas devem estar no formato: `HH:MM`
- O sistema não valida conflitos de reservas — isso pode ser estendido futuramente.

---

## 🧹 Limpeza do banco (opcional)

Para resetar o banco, basta excluir o arquivo `reservas.db`.

---

## 💬 Dúvidas?

Abra um *issue* ou envie uma mensagem!

