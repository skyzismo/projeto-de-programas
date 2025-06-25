/**
 * Classe Banco que realiza a conexão com o banco de dados SQLite do sistema EasyRoom.
 * <p>
 * Esta classe é responsável por criar as 3 tabelas essenciais Sala, Usuario e Reserva para as principais
 * operações às funcionalidades do sistema.
 * </p>
 * 
 * @author Lucas Gadelha - 22050517
 * @version 1.0
 * @since 2025-06-23
 */

package br.ufam.icomp.reservas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Banco {
    /** URL de conexão com o banco de dados */
    private static final String URL = "jdbc:sqlite:reservas.db";

    /**
     * Estabelece conexão com o banco de dados.
     *
     * @return Connection objeto de conexão
     * @throws SQLException em caso de erro na conexão
     */

    static {
        try (Connection conn = conectar()) {
            if (conn != null) {
                Statement stmt = conn.createStatement();

                /**Cria tabela Sala */
                stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Sala (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT NOT NULL," +
                    "tipo TEXT NOT NULL" +
                    ")"
                );

                /**Cria tabela Usuario */
                stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Usuario (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT NOT NULL," +
                    "tipo TEXT NOT NULL" +
                    ")"
                );

                /**Cria tabela Reserva */
                stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Reserva (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "sala_id INTEGER NOT NULL," +
                    "usuario_id INTEGER NOT NULL," +
                    "data TEXT NOT NULL," +
                    "hora_inicio TEXT NOT NULL," +
                    "hora_fim TEXT NOT NULL," +
                    "FOREIGN KEY(sala_id) REFERENCES Sala(id)," +
                    "FOREIGN KEY(usuario_id) REFERENCES Usuario(id)" +
                    ")"
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}