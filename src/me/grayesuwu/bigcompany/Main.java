package me.grayesuwu.bigcompany;

import com.mojang.authlib.UserType;
import com.mysql.jdbc.Connection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;


import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Daniel at 5/6/2020
 */
public class Main extends JavaPlugin {

    private Connection connection;
    public String host,database,username,password;
    public int port;

    @Override
    public void onEnable() {
        mysqlSetup();
        getLogger().info("This is still on development! Please expect some unintentional bugs.");
    }

    //Credentials listed are based on commonly used information. Credentials may differ depends on your own settings or server's settings
    public void mysqlSetup() {
        host = "localhost";
        port = 3306;
        database = "bigcompany";
        username = "root";
        password = "password";

        openConnection();
    }

    private void openConnection() {
        try {
            if (getConnection() != null && !getConnection().isClosed()) {
                return;
            }

            Class.forName("com.mysql.jdbc.driver");
            setConnection((Connection) DriverManager.getConnection("jdbc:mysql://" + this.host + ":"
                    + this.port + "/" + this.database, this.username, this.password));

            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Connection was established!");
        } catch (SQLException e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Failed while trying to establish connection!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Failed while trying to establish connection!");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
