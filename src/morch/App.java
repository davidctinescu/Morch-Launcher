package morch;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class App {
    private JFrame frame;
    private JComboBox<String> versionComboBox;
    private JButton launchButton;

    private String[] versions;

    public App() {
        frame = new JFrame("morch launcher");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        versions = getVersions();
        versionComboBox = new JComboBox<>(versions);
        panel.add(versionComboBox);

        launchButton = new JButton("Launch");
        launchButton.addActionListener(e -> launchMinecraft());
        panel.add(launchButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private String[] getVersions() {
        List<String> versionList = new ArrayList<>();
        String path = new File("").getAbsolutePath();
        File versionsDir = new File(path + "/resources");
        System.out.println("yes, exists!: " +versionsDir.toString());
        
        if (versionsDir.exists() && versionsDir.isDirectory()) {
            File[] versionFolders = versionsDir.listFiles(File::isDirectory);
            System.out.println("yes, exists!: " +versionFolders.toString());
            if (versionFolders != null) {
                for (File versionFolder : versionFolders) {
                    File[] jarFiles = versionFolder.listFiles((dir, name) -> name.endsWith(".jar"));
                    System.out.println("yes, exists!: " +jarFiles.toString());
                    if (jarFiles != null && jarFiles.length > 0) {
                        versionList.add(versionFolder.getName());
                    }
                }
            }
        }

        return versionList.toArray(new String[0]);
    }

    private void launchMinecraft() {
        String selectedVersion = (String) versionComboBox.getSelectedItem();
        String jarPath = new File("").getAbsolutePath() + "/resources/" + selectedVersion + "/" + selectedVersion + ".jar";

        try {
            Runtime.getRuntime().exec("/usr/bin/java -jar " + jarPath);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error launching Minecraft: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }
}

