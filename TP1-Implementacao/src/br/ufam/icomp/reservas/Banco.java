package br.ufam.icomp.reservas;

import java.sql.*;

public class Banco {
    private static final String URL = "jdbc:sqlite:reservas.db";

    static {
        try (Connection conn = DriverManager.getConnection(URL); Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS Sala (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, tipo TEXT)");
            stmt.execute("CREATE TABLE IF NOT EXISTS Usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, cargo TEXT)");
            stmt.execute("CREATE TABLE IF NOT EXISTS Reserva (id INTEGER PRIMARY KEY AUTOINCREMENT, sala_id INTEGER, usuario_id INTEGER, data TEXT, hora TEXT, FOREIGN KEY(sala_id) REFERENCES Sala(id), FOREIGN KEY(usuario_id) REFERENCES Usuario(id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}