package ua.ithillel.mealapp.db;

import org.apache.ibatis.jdbc.ScriptRunner;
import ua.ithillel.mealapp.exception.DbInitException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;

public class DbSchemaInitializer {
    public static void init(Connection connection, String filePath) throws DbInitException {
        try (Reader reader = new BufferedReader(new FileReader(filePath))) {
            ScriptRunner scriptRunner = new ScriptRunner(connection);

            scriptRunner.runScript(reader);
        } catch (IOException e) {
            throw new DbInitException("Unable to init database: " + e.getMessage(), e);
        }
    }
}
