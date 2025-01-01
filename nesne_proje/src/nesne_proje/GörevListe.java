package nesne_proje;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GörevListe {
    private JFrame frame;
    private JPanel taskPanel;
    private JTextField taskInput;

    public GörevListe() {
        
        frame = new JFrame("Görev Listesi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
      
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
    
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        taskInput = new JTextField();
        JButton addButton = new JButton("Ekle");
        inputPanel.add(taskInput, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);
     
        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        taskPanel.setBackground(new Color(225, 203, 177));
        
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        taskInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTask(taskInput.getText());
                taskInput.setText("");
            }
        });
 
        
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTask(taskInput.getText());
                taskInput.setText("");
            }
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    
    private void addTask(String taskText) {
        if (taskText.isEmpty()) {
            return;
        }

        JPanel taskRow = new JPanel();
        taskRow.setLayout(new BorderLayout());
        taskRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); 
 
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        textPanel.setBackground(new Color(225, 203, 177));
        textPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        JLabel taskLabel = new JLabel("<html><div style='width:300px;'>" + taskText + "</div></html>");
        taskLabel.setFont(new Font("Arial", Font.BOLD, 15));
        taskLabel.setPreferredSize(new Dimension(250, 20));
        textPanel.add(taskLabel);
        
        JCheckBox checkBox = new JCheckBox();
        checkBox.setBackground(new Color(225, 203, 177));
        
        checkBox.addItemListener(e -> {
            if (checkBox.isSelected()) {
                taskLabel.setText("<html><s>" + taskText + "</s></html>");
            } else {
                taskLabel.setText("<html>" + taskText + "</html>");
            }
        });

        JButton deleteButton = new JButton("X");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 20));
        deleteButton.setMargin(new Insets(1, 5, 1, 5));

        taskRow.add(textPanel, BorderLayout.CENTER);
        taskRow.add(checkBox, BorderLayout.WEST);
        taskRow.add(deleteButton, BorderLayout.EAST);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskPanel.remove(taskRow);
                taskPanel.revalidate();
                taskPanel.repaint();
            }
        });

        taskPanel.add(taskRow);
        taskPanel.revalidate();
        taskPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GörevListe::new);
    }
}
